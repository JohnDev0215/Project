package com.amazon.avod.media.ads.internal;

import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.ads.api.Beacon;
import com.amazon.avod.ads.api.Duration;
import com.amazon.avod.ads.api.TEVSClientDecorator;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.http.AdTEVSClient;
import com.amazon.avod.ads.http.BoltHttpAdClient;
import com.amazon.avod.ads.http.DefaultAdHttpClient;
import com.amazon.avod.ads.http.HttpParameters;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.amazon.avod.ads.parser.vast.VastTrackingEventType;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.event.CdnSwitchedEvent;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.EventMessage;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.http.HttpRequestBuilder;
import com.amazon.avod.http.HttpStatusCodeException;
import com.amazon.avod.http.RequestBuildException;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdError;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.ads.internal.state.PrimaryPlayerStateTracker;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.monitoring.TimelineMonitoringTask;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusAdIdentifierReporter;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.amazon.avod.playback.event.playback.AdTrackingAuditPingEvent;
import com.amazon.avod.playback.event.playback.AudioTrackChangeEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.event.playback.PlaybackStopEvent;
import com.amazon.avod.playback.event.playback.TrackingUriRefreshEvent;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LiveAdTrackingManager {
    private final String mAdAgencyForAuditPing;
    private final AdHttpClient mAdHttpClient;
    private final AdPlaybackStateMachineContext mAdPlaybackStateMachineContext;
    private final AdvertisingIdCollector mAdvertisingIdCollector;
    private final AloysiusAdIdentifierReporter mAloysiusAdIdIdentifierReporter;
    private final int mAuditPingStartCode;
    private final int mAuditPingStopCode;
    private final int mAuditPingUooStatusOptInCode;
    private final int mAuditPingUooStatusOptOutCode;
    private ContentSession mContentSession;
    private final ContentManagementEventBus mEventBus;
    private final ExecutorService mExecutorService;
    private final ReentrantLock mFetchLock;
    private boolean mHasReportedMissingTEVSEndpoint;
    private boolean mHasReportedTEVSEndpointUrl;
    private final boolean mIsAuditPingFeatureEnabled;
    private final PlaybackEventTransport mPlaybackEventTransport;
    private final PrimaryPlayerStateTracker mPrimaryPlayerStateTracker;
    private final Set<String> mSafeListTitleIdsForTEVSReporting;
    private final boolean mShouldReportTEVSEmptyAvailsResponse;
    private final List<TimelineMonitoringTask> mStaleTimelineMonitoringTasks;
    private final AdTEVSClient mTevsClient;
    private final TimelineMonitor mTimelineMonitor;
    private VideoSpecification mVideoSpecification;

    public LiveAdTrackingManager(@Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull AloysiusAdIdentifierReporter aloysiusAdIdentifierReporter, @Nonnull TimelineMonitor timelineMonitor, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull ExecutorService executorService, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull ContentManagementEventBus contentManagementEventBus) {
        this(playbackEventTransport, aloysiusAdIdentifierReporter, timelineMonitor, adPlaybackStateMachineContext, executorService, advertisingIdCollector, new AdTEVSClient(ServiceClient.getInstance(), null), PlaybackConfig.INSTANCE, contentManagementEventBus, AdsConfig.getInstance().shouldReportTEVSEmptyAvailsResponse(), AdsConfig.getInstance().getSafeListedTitleIdsForTEVSReporting());
    }

    @VisibleForTesting
    LiveAdTrackingManager(@Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull AloysiusAdIdentifierReporter aloysiusAdIdentifierReporter, @Nonnull TimelineMonitor timelineMonitor, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull ExecutorService executorService, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull AdTEVSClient adTEVSClient, @Nonnull PlaybackConfig playbackConfig, @Nonnull ContentManagementEventBus contentManagementEventBus, boolean z, @Nonnull Set<String> set) {
        DLog.devf("LiveAdTrackingManager: new instance created");
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mAloysiusAdIdIdentifierReporter = (AloysiusAdIdentifierReporter) Preconditions.checkNotNull(aloysiusAdIdentifierReporter, "aloysiusAdIdentifierReporter");
        this.mTimelineMonitor = (TimelineMonitor) Preconditions.checkNotNull(timelineMonitor, "timelineMonitor");
        this.mAdPlaybackStateMachineContext = (AdPlaybackStateMachineContext) Preconditions.checkNotNull(adPlaybackStateMachineContext, "adPlaybackStateMachineContext");
        this.mAdvertisingIdCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollectorProvider");
        this.mExecutorService = (ExecutorService) Preconditions.checkNotNull(executorService, "executorService");
        this.mStaleTimelineMonitoringTasks = Lists.newLinkedList();
        this.mTevsClient = (AdTEVSClient) Preconditions.checkNotNull(adTEVSClient, "adTEVSClient");
        this.mAdHttpClient = createAdHttpClient();
        this.mShouldReportTEVSEmptyAvailsResponse = z;
        this.mSafeListTitleIdsForTEVSReporting = (Set) Preconditions.checkNotNull(set, "safeListTitleIdsForTEVSReporting");
        this.mFetchLock = new ReentrantLock();
        this.mPrimaryPlayerStateTracker = new PrimaryPlayerStateTracker();
        Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mIsAuditPingFeatureEnabled = playbackConfig.isAuditPingEnabled();
        this.mAuditPingStartCode = playbackConfig.getAuditPingStartCode();
        this.mAuditPingStopCode = playbackConfig.getAuditPingStopCode();
        this.mAuditPingUooStatusOptOutCode = playbackConfig.getAuditPingUooStatusOptOutCode();
        this.mAuditPingUooStatusOptInCode = playbackConfig.getAuditPingUooStatusOptInCode();
        this.mHasReportedMissingTEVSEndpoint = false;
        this.mHasReportedTEVSEndpointUrl = false;
        this.mAdAgencyForAuditPing = playbackConfig.getSupportedAdAgencyForAuditPing();
        this.mEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "eventBus");
        init();
    }

    @VisibleForTesting
    protected TEVSClientDecorator constructTEVSDecorator() {
        String consumptionId = getConsumptionId();
        AdvertisingIdCollector.AdvertisingInfo advertisingInfo = this.mAdvertisingIdCollector.get(AdsConfig.getInstance().getGPAIDFetchTimeOut().getTotalMilliseconds(), false);
        if (Strings.isNullOrEmpty(advertisingInfo.getAdId())) {
            DLog.errorf("LiveAdTrackingManager: cant retrieve GPAID");
            this.mAdPlaybackStateMachineContext.getPrimaryEventReporter().reportError(AdError.LIVE_TEVS_ADVERTISING_ID_ERROR.toString(), "LiveAdTrackingManager: cant retrieve GPAID", null);
            return new TEVSClientDecorator("null", Boolean.valueOf(advertisingInfo.isOptOut()), consumptionId);
        }
        return new TEVSClientDecorator(advertisingInfo.getAdId(), Boolean.valueOf(advertisingInfo.isOptOut()), consumptionId);
    }

    @Nullable
    private String getConsumptionId() {
        ContentSession contentSession = this.mContentSession;
        if (contentSession == null) {
            return null;
        }
        return contentSession.getConsumptionId();
    }

    public void init() {
        if (this.mAdPlaybackStateMachineContext.getAdsConfig().isTrackingEnabled()) {
            DLog.devf("LiveAdTrackingManager: initializing, event bus instance: %s", this.mPlaybackEventTransport);
            this.mPlaybackEventTransport.registerEventBusHandler(this);
            this.mEventBus.registerEventBusHandler(this);
        }
        PlaybackSessionContext playbackSessionContext = this.mAdPlaybackStateMachineContext.getPlaybackSessionContext();
        this.mContentSession = playbackSessionContext.getContentSession();
        this.mVideoSpecification = playbackSessionContext.getVideoSpec();
    }

    @Subscribe
    public void handlePlaybackStartEvent(@Nonnull PlaybackStartEvent playbackStartEvent) {
        VideoSpecification videoSpecification;
        DLog.devf("LiveAdTrackingManager: playback start event received");
        if (this.mAdPlaybackStateMachineContext.getAdsConfig().shouldRefreshTrackingUriForLive() && (videoSpecification = this.mVideoSpecification) != null && videoSpecification.isLiveStream()) {
            DLog.devf("LiveAdTrackingManager: posting tracking uri refresh event");
            this.mPlaybackEventTransport.postEvent(new TrackingUriRefreshEvent(TimeSpan.ZERO));
        }
        PrimaryPlayerStateTracker.PlayerState playerCurrentState = this.mPrimaryPlayerStateTracker.getPlayerCurrentState();
        if (this.mPrimaryPlayerStateTracker.isSessionTransition(PrimaryPlayerStateTracker.PlayerStateTransitions.STARTING)) {
            String auditPingUrl = this.mContentSession.getAuditPingUrl(this.mAdAgencyForAuditPing);
            if (checkAuditPingUrl(auditPingUrl)) {
                if (playerCurrentState == PrimaryPlayerStateTracker.PlayerState.UNINITIALIZED) {
                    sendAuditPing(playbackStartEvent, auditPingUrl);
                    reportInfo(String.format(Locale.US, "audit ping , auditpingurl = %s, event = %s", String.valueOf(auditPingUrl), playbackStartEvent.toString()), "auditPing");
                }
                reportAdTrackingAuditPingEvent(playbackStartEvent.getConsumptionId());
                return;
            }
            return;
        }
        DLog.devf("skipped START audit pings for invalid player transitions");
    }

    @Subscribe
    public void handlePlaybackRestartEvent(@Nonnull PlaybackRestartEvent playbackRestartEvent) {
        this.mPrimaryPlayerStateTracker.isSessionTransition(PrimaryPlayerStateTracker.PlayerStateTransitions.RESTARTING);
    }

    @Subscribe
    public void handleAudioChangeRestartEvent(@Nonnull AudioTrackChangeEvent audioTrackChangeEvent) {
        this.mPrimaryPlayerStateTracker.isSessionTransition(PrimaryPlayerStateTracker.PlayerStateTransitions.RESTARTING);
    }

    @Subscribe
    public void handleCdnSwitchedEvent(CdnSwitchedEvent cdnSwitchedEvent) {
        ContentSession contentSession = this.mContentSession;
        if (contentSession == null || !checkAuditPingUrl(contentSession.getAuditPingUrl(this.mAdAgencyForAuditPing))) {
            return;
        }
        reportAdTrackingAuditPingEvent(cdnSwitchedEvent.getNewContentUrl().getSessionId());
    }

    @Subscribe
    public void handlePlaybackStopEvent(@Nonnull PlaybackStopEvent playbackStopEvent) {
        if (this.mPrimaryPlayerStateTracker.isSessionTransition(PrimaryPlayerStateTracker.PlayerStateTransitions.STOPPING)) {
            String auditPingUrl = this.mContentSession.getAuditPingUrl(this.mAdAgencyForAuditPing);
            if (checkAuditPingUrl(auditPingUrl)) {
                sendAuditPing(playbackStopEvent, auditPingUrl);
                reportInfo(String.format(Locale.US, "audit ping , auditpingurl = %s, event = %s", String.valueOf(auditPingUrl), playbackStopEvent.toString()), "auditPing");
            }
            if (this.mAdPlaybackStateMachineContext.getAdsConfig().shouldDeinitOnPlaybackStop()) {
                deInit();
                return;
            }
            return;
        }
        DLog.devf("skipped STOP audit pings for invalid player transitions");
    }

    public void deInit() {
        if (this.mAdPlaybackStateMachineContext.getAdsConfig().isTrackingEnabled()) {
            DLog.devf("LiveAdTrackingManager: deInit");
            this.mPlaybackEventTransport.unregisterEventBusHandler(this);
            this.mEventBus.unregisterEventBusHandler(this);
        }
    }

    @Subscribe
    public void handleFragmentEventMessageEvent(@Nonnull EventMessage eventMessage) {
        if (this.mAdPlaybackStateMachineContext.getAdsConfig().shouldRefreshTrackingUriOnEmsg()) {
            DLog.devf("LiveAdTrackingManager: EventMessage received, presentation delta time: %s", eventMessage.getPresentationTimeDelta());
            deployBeacons(getTEVSEndPoint());
        }
    }

    @Subscribe
    public void handleTrackingUriRefreshMessageEvent(@Nonnull TrackingUriRefreshEvent trackingUriRefreshEvent) {
        try {
            DLog.devf("LiveAdTrackingManager: TrackingUriRefreshEvent received, event time stamp: %s", trackingUriRefreshEvent.getEventTimeStamp());
            String tEVSEndPoint = getTEVSEndPoint();
            deployBeacons(tEVSEndPoint);
            if (!this.mHasReportedTEVSEndpointUrl) {
                this.mHasReportedTEVSEndpointUrl = true;
                reportInfo(Objects.toString(tEVSEndPoint), "LIVE_TEVS_URL");
            }
        } finally {
            setUpNextFetch();
        }
    }

    @Nullable
    private String getTEVSEndPoint() {
        ContentSession contentSession = this.mContentSession;
        if (contentSession == null || contentSession.getContentUrlSelector() == null) {
            return null;
        }
        return this.mContentSession.getContentUrlSelector().getCurrentContentUrl().getAdsTrackingEndpoint();
    }

    private void deployBeacons(@Nullable final String str) {
        this.mExecutorService.submit(new Runnable() { // from class: com.amazon.avod.media.ads.internal.LiveAdTrackingManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (LiveAdTrackingManager.this.mFetchLock.tryLock()) {
                        try {
                            try {
                                if (!Strings.isNullOrEmpty(str)) {
                                    List beaconList = LiveAdTrackingManager.this.getBeaconList(str);
                                    LiveAdTrackingManager.this.reScheduleTimelineEvents(beaconList);
                                    if (beaconList.size() == 0) {
                                        LiveAdTrackingManager.this.reportTEVSResponseStatus(str);
                                    }
                                } else if (!LiveAdTrackingManager.this.mHasReportedMissingTEVSEndpoint) {
                                    LiveAdTrackingManager.this.mHasReportedMissingTEVSEndpoint = true;
                                    LiveAdTrackingManager.this.reportWarning("unable to find TEVS end point from the audio video url", AdError.LIVE_TEVS_TRACKING_REQUEST_ERROR);
                                }
                            } catch (RequestBuildException e) {
                                LiveAdTrackingManager.this.reportError("Invalid Request", AdError.LIVE_TEVS_TRACKING_REQUEST_ERROR, e);
                            } catch (MalformedURLException e2) {
                                LiveAdTrackingManager.this.reportError("Malformed Url", AdError.LIVE_TEVS_MALFORMED_URI_ERROR, e2);
                            }
                        } catch (BoltException e3) {
                            if (e3.getCause() instanceof HttpStatusCodeException) {
                                LiveAdTrackingManager.this.reportError("Server response exception", AdError.LIVE_TEVS_TRACKING_REQUEST_ERROR, e3.getCause());
                            } else {
                                LiveAdTrackingManager.this.reportError("Server response exception", AdError.LIVE_TEVS_TRACKING_REQUEST_ERROR, e3);
                            }
                        }
                    }
                } finally {
                    LiveAdTrackingManager.this.mFetchLock.unlock();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Beacon> getBeaconList(String str) throws MalformedURLException, BoltException, RequestBuildException {
        this.mTevsClient.decorateTEVSRequest(constructTEVSDecorator());
        return this.mTevsClient.getBeaconList(str, this.mAdHttpClient);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportError(String str, AdError adError, Throwable th) {
        DLog.exceptionf(th, "LiveAdTrackingManager: %s", str);
        this.mAdPlaybackStateMachineContext.getPrimaryEventReporter().reportError(adError.toString(), th.getMessage(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWarning(String str, AdError adError) {
        DLog.warnf(str);
        this.mAdPlaybackStateMachineContext.getPrimaryEventReporter().reportMetric(new MetricsBuilder().eventType("AdEvent").eventSubtype(adError.toString()).note(str));
    }

    private void reportInfo(String str, String str2) {
        DLog.warnf(str);
        this.mAdPlaybackStateMachineContext.getPrimaryEventReporter().reportMetric(new MetricsBuilder().eventType(QOSEventName.Information.toString()).eventSubtype(str2).note(str));
    }

    private void setUpNextFetch() {
        DLog.devf("LiveAdTrackingManager: setting up next fetch");
        VideoPlayer primaryPlayer = this.mAdPlaybackStateMachineContext.getPrimaryPlayer();
        final TimeSpan timeSpan = new TimeSpan(TimeUnit.MILLISECONDS.toNanos(primaryPlayer.getCurrentPosition() + this.mAdPlaybackStateMachineContext.getAdsConfig().getTEVSRequestFrequency().getTotalMilliseconds()));
        try {
            this.mTimelineMonitor.scheduleTask(primaryPlayer, new TimelineMonitoringTask(new Runnable() { // from class: com.amazon.avod.media.ads.internal.LiveAdTrackingManager.2
                @Override // java.lang.Runnable
                public void run() {
                    LiveAdTrackingManager.this.mPlaybackEventTransport.postEvent(new TrackingUriRefreshEvent(timeSpan));
                }
            }, timeSpan, false, true));
        } catch (IllegalStateException unused) {
            DLog.warnf("timeline monitor is halted, no TEVS refresh tasks scheduled");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reScheduleTimelineEvents(List<Beacon> list) {
        try {
            removeTimeLineEvents(this.mStaleTimelineMonitoringTasks);
            List<TimelineMonitoringTask> listAddTimelineEvents = addTimelineEvents(list);
            this.mStaleTimelineMonitoringTasks.clear();
            this.mStaleTimelineMonitoringTasks.addAll(listAddTimelineEvents);
        } catch (IllegalStateException unused) {
            DLog.warnf("timeline monitor is halted, no TEVS beacon tasks scheduled");
        }
    }

    private List<TimelineMonitoringTask> addTimelineEvents(List<Beacon> list) {
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        for (final Beacon beacon : list) {
            if (beacon.getOffset() != null && beacon.getOffset().getDuration() != null) {
                TimelineMonitoringTask timelineMonitoringTask = new TimelineMonitoringTask(new Runnable() { // from class: com.amazon.avod.media.ads.internal.LiveAdTrackingManager.3
                    @Override // java.lang.Runnable
                    public void run() {
                        LiveAdTrackingManager.this.sendBeacon(beacon);
                    }
                }, TimeSpan.fromMilliseconds(beacon.getOffset().getDuration().getTotalMilliseconds()), true, true);
                this.mTimelineMonitor.scheduleTask(this.mAdPlaybackStateMachineContext.getPrimaryPlayer(), timelineMonitoringTask);
                linkedListNewLinkedList.add(timelineMonitoringTask);
            }
        }
        return linkedListNewLinkedList;
    }

    private void removeTimeLineEvents(List<TimelineMonitoringTask> list) {
        Iterator<TimelineMonitoringTask> it = list.iterator();
        while (it.hasNext()) {
            this.mTimelineMonitor.removeTask(this.mAdPlaybackStateMachineContext.getPrimaryPlayer(), it.next());
        }
    }

    private boolean checkAuditPingUrl(@Nullable String str) {
        if (!this.mIsAuditPingFeatureEnabled) {
            return false;
        }
        ContentSession contentSession = this.mContentSession;
        if (contentSession == null || contentSession.getContentUrlSelector() == null) {
            DLog.devf("content session not set up yet");
            reportInfo("content session not set up yet", "auditPingError");
            return false;
        }
        if (Strings.isNullOrEmpty(str)) {
            DLog.warnf("audit ping url not available");
            return false;
        }
        DLog.devf("audit ping available");
        return true;
    }

    private void sendAuditPing(PlaybackEvent playbackEvent, String str) {
        String strConstructNielsenAuditPingUrl = constructNielsenAuditPingUrl(playbackEvent, str, TimeSpan.fromMilliseconds(System.currentTimeMillis()).getTotalSeconds());
        DLog.logf("Attempting audit ping on %s", strConstructNielsenAuditPingUrl);
        if (Strings.isNullOrEmpty(strConstructNielsenAuditPingUrl)) {
            return;
        }
        pingBeacon(new Beacon(new VastTracking(VastTrackingEventType.auditPingEvent, null, URI.create(strConstructNielsenAuditPingUrl)), this.mAdHttpClient));
    }

    private void pingBeacon(final Beacon beacon) {
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.LiveAdTrackingManager.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DLog.devf("LiveAdTrackingManager: submitting beacon request, type: %s", beacon.getEventType());
                    beacon.pingEvent();
                    LiveAdTrackingManager.this.reportQuartilesInfoToQos(beacon);
                    QALog.newQALog(PlaybackQAEvent.AUDIT_PING_SENT).addMetric(PlaybackQAMetric.AUDIT_PING_URL, beacon.getEvent().getUri()).send();
                } catch (AdNetworkException e) {
                    LiveAdTrackingManager.this.reportError(String.format(Locale.US, "Failure to send audit beacon for %s", beacon.getEvent().getUri()), AdError.AUDIT_PING_NETWORK_ERROR, e);
                    QALog.newQALog(PlaybackQAEvent.AUDIT_PING_ERROR).addMetric(PlaybackQAMetric.AUDIT_PING_URL, beacon.getEvent().getUri()).send();
                }
            }
        });
    }

    @VisibleForTesting
    String constructNielsenAuditPingUrl(PlaybackEvent playbackEvent, String str, long j) {
        int i;
        int i2;
        String userWatchSessionId = this.mAdPlaybackStateMachineContext.getPrimaryEventReporter().getUserWatchSessionId();
        String primitiveSessionId = this.mAdPlaybackStateMachineContext.getPrimaryEventReporter().getPrimitiveSessionId();
        AdvertisingIdCollector.AdvertisingInfo advertisingInfo = this.mAdvertisingIdCollector.get(this.mAdPlaybackStateMachineContext.getAdsConfig().getGPAIDFetchTimeOut().getTotalMilliseconds(), false);
        String adId = advertisingInfo.getAdId();
        Boolean boolValueOf = Boolean.valueOf(advertisingInfo.isOptOut());
        if (playbackEvent instanceof PlaybackStartEvent) {
            i = this.mAuditPingStartCode;
        } else {
            if (!(playbackEvent instanceof PlaybackStopEvent)) {
                return null;
            }
            i = this.mAuditPingStopCode;
        }
        if (boolValueOf == null || !boolValueOf.booleanValue()) {
            i2 = this.mAuditPingUooStatusOptInCode;
        } else {
            i2 = this.mAuditPingUooStatusOptOutCode;
            adId = "";
        }
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        HashFunction hashFunctionSha256 = Hashing.sha256();
        Charset charset = Charsets.UTF_8;
        return builderBuildUpon.appendQueryParameter("streamid", hashFunctionSha256.hashString(primitiveSessionId, charset).toString()).appendQueryParameter("sessionid", Hashing.sha256().hashString(userWatchSessionId, charset).toString()).appendQueryParameter("pingtype", String.valueOf(i)).appendQueryParameter("uoo", String.valueOf(i2)).appendQueryParameter("createtm", String.valueOf(j)).appendQueryParameter("devid", adId).build().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBeacon(final Beacon beacon) {
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.LiveAdTrackingManager.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DLog.devf("LiveAdTrackingManager: submitting beacon request, type: %s", beacon.getEventType());
                    ContentSession contentSession = LiveAdTrackingManager.this.mAdPlaybackStateMachineContext.getPlaybackSessionContext().getContentSession();
                    beacon.sendEvent(LiveAdTrackingManager.this.getPlayHead(), null, contentSession != null ? contentSession.getConsumptionId() : null);
                    LiveAdTrackingManager.this.reportQuartilesInfoToQos(beacon);
                } catch (AdNetworkException e) {
                    LiveAdTrackingManager.this.reportError(String.format(Locale.US, "Failure to send timed based beacon for %d", Long.valueOf(beacon.getOffset().getDuration().getTotalMilliseconds())), AdError.LIVE_TEVS_TRACKING_REQUEST_ERROR, e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportTEVSResponseStatus(@Nonnull String str) {
        String titleId = this.mAdPlaybackStateMachineContext.getVideoSpecification().getTitleId();
        if (this.mShouldReportTEVSEmptyAvailsResponse || this.mSafeListTitleIdsForTEVSReporting.contains(titleId)) {
            this.mAdPlaybackStateMachineContext.getPrimaryEventReporter().reportMetric(new MetricsBuilder().eventType("AdEvent").eventSubtype(AdError.LIVE_TEVS_EMPTY_AVAILS_RESPONSE.name()).note(str));
            DLog.logf("LiveAdTrackingManager: received empty avails for the TEVS request : %s", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportQuartilesInfoToQos(Beacon beacon) {
        DLog.devf(beacon.getEventType().toString());
        this.mAdPlaybackStateMachineContext.getPrimaryEventReporter().reportMetric(new MetricsBuilder().eventType("AdEvent").eventSubtype(beacon.getEventType().toString()).note(beacon.getEvent().getUri().toString()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Duration getPlayHead() {
        return new Duration(this.mAdPlaybackStateMachineContext.getPrimaryPlayer().getCurrentPosition());
    }

    private AdHttpClient createAdHttpClient() {
        AdsConfig adsConfig = this.mAdPlaybackStateMachineContext.getAdsConfig();
        String property = System.getProperty("http.agent");
        if (Strings.isNullOrEmpty(property)) {
            property = adsConfig.getUserAgent();
        }
        String strSanitizeUserAgent = HttpRequestBuilder.sanitizeUserAgent(property);
        HttpParameters httpParametersBuild = new HttpParameters.Builder().followRedirects(true).withBeaconRetryCount(adsConfig.getBeaconRetries()).withBeaconTimeout((int) adsConfig.getLiveBeaconTimeout().getTotalMilliseconds()).withRequestRetryCount(adsConfig.getRequestRetries()).withRequestTimeout((int) adsConfig.getRequestTimeout().getTotalMilliseconds()).withUserAgent(strSanitizeUserAgent).build();
        DLog.devf("userAgent for Ads Beacon = %s", strSanitizeUserAgent);
        return adsConfig.isBoltHttpClientEnabledForAds() ? new BoltHttpAdClient(httpParametersBuild) : new DefaultAdHttpClient(httpParametersBuild, new PersistentAndroidCookieStore());
    }

    private void reportAdTrackingAuditPingEvent(@Nullable String str) {
        String auditPingUrl = this.mContentSession.getAuditPingUrl(this.mAdAgencyForAuditPing);
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(this.mAdPlaybackStateMachineContext.getPrimaryPlayer().getCurrentPosition());
        String userWatchSessionId = this.mAdPlaybackStateMachineContext.getPrimaryEventReporter().getUserWatchSessionId();
        String primitiveSessionId = this.mAdPlaybackStateMachineContext.getPrimaryEventReporter().getPrimitiveSessionId();
        String titleId = this.mAdPlaybackStateMachineContext.getVideoSpecification().getTitleId();
        if (str == null) {
            PlaybackEventReporter primaryEventReporter = this.mAdPlaybackStateMachineContext.getPrimaryEventReporter();
            if (titleId == null) {
                titleId = "";
            }
            primaryEventReporter.reportError("AdIdentifierMissingConsumptionId", "Missing consumptionId", null, titleId, false);
        }
        this.mAloysiusAdIdIdentifierReporter.onAdTrackingAuditPingEvent(new AdTrackingAuditPingEvent(timeSpanFromMilliseconds, auditPingUrl, str == null ? "MissingConsumptionId" : str, this.mAdAgencyForAuditPing, userWatchSessionId, primitiveSessionId));
    }
}
