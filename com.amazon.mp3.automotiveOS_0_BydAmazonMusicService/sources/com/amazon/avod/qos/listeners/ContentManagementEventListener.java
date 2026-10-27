package com.amazon.avod.qos.listeners;

import android.content.Context;
import androidx.core.os.EnvironmentCompat;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.event.CdnSwitchedEvent;
import com.amazon.avod.content.event.ContentEventBufferFullnessChanged;
import com.amazon.avod.content.event.ContentEventErrorBase;
import com.amazon.avod.content.event.ContentEventFragmentConsumed;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.content.event.ContentEventFragmentRequested;
import com.amazon.avod.content.event.ContentEventSecondaryDownloadEligibilityStateChanged;
import com.amazon.avod.content.event.ContentEventSessionStarting;
import com.amazon.avod.content.event.ContentEventStartDownloading;
import com.amazon.avod.content.event.ContentEventStopDownloading;
import com.amazon.avod.content.event.ContentStoreEvent;
import com.amazon.avod.content.event.FatalContentEventError;
import com.amazon.avod.content.event.FragmentInformationHolder;
import com.amazon.avod.content.event.RetriableContentEventError;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.StreamSelections;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.qos.ErrorReportingConfiguration;
import com.amazon.avod.qos.EventThrottle;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.qos.WanFragmentDownloadCounter;
import com.amazon.avod.qos.internal.LowMemoryReceiver;
import com.amazon.avod.qos.listeners.fragmentreporter.DownloadedVideoFragmentReporter;
import com.amazon.avod.qos.listeners.fragmentreporter.FragmentReporter;
import com.amazon.avod.qos.listeners.fragmentreporter.SubmittedToRendererVideoFragmentReporter;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.qos.reporter.ContentManagementEventReporter;
import com.amazon.avod.qos.reporter.SmoothStreamingReporter;
import com.amazon.avod.util.DataUnit;
import com.amazon.avod.util.Preconditions2;
import com.amazon.avod.util.sequence.ExponentialSequence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.eventbus.Subscribe;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentManagementEventListener extends QosEventListenerBase {
    private static final ImmutableMap<ContentSessionType, DeliveryType> CONSUMPTION_TYPE_MAP;
    static final ImmutableMap<ContentException.ContentError, String> ERROR_SUBTYPE_MAP = new ImmutableMap.Builder().put(ContentException.ContentError.DISK_ERROR, "DiskIOError").put(ContentException.ContentError.DISK_FULL, "DiskIOError").put(ContentException.ContentError.FILE_MISSING, "DiskIOError").put(ContentException.ContentError.DOWNLOAD_NOT_OWNED, "NoRightsNotOwned").put(ContentException.ContentError.STREAMING_NOT_OWNED, "NoRightsNotOwned").put(ContentException.ContentError.SERVICE_ERROR, "NetworkError").put(ContentException.ContentError.NETWORK_ERROR, "NetworkError").put(ContentException.ContentError.CDN_ERROR, "NetworkError").put(ContentException.ContentError.DATA_CONNECTION_UNAVAILABLE_ERROR, "DataConnectionUnavailableError").put(ContentException.ContentError.NO_AVAILABLE_DOWNLOAD_RIGHTS, "ConcurrencyError").put(ContentException.ContentError.NO_AVAILABLE_ONLINE_STREAMS, "ConcurrencyError").put(ContentException.ContentError.TEMPORARILY_UNAVAILABLE, "ConcurrencyError").put(ContentException.ContentError.UNKNOWN_ERROR, "DownloadErrorUnknown").build();
    private long mAudioBufferFullnessInNanoseconds;
    private int mAudioDownloadCompletionPercent;
    private ContentSessionContext mContext;
    private TimeSpan mDownloadStartTime;
    private final AtomicBoolean mDownloadStartedEventSent;
    private final ErrorReportingConfiguration mErrorConfig;
    private final TimeSpan mFragmentDownloadTimeReportingThreshold;
    private boolean mIsDownloadingOverWan;
    private final LiveStreamingPlaybackConfig mLiveStreamingPlaybackConfig;
    private final LowMemoryReceiver mLowMemReceiver;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private String mQosNote;
    private ContentManagementEventReporter mReporter;
    private final EventThrottle mRetriableErrorEventThrottle;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingConfig;
    private SmoothStreamingReporter mSmoothStreamingReporter;
    private long mVideoBufferFullnessInNanoseconds;
    private int mVideoDownloadCompletionPercent;
    private FragmentReporter mVideoFragmentReporter;
    private final WanFragmentDownloadCounter mWanTracker;

    static {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        ContentSessionType contentSessionType = ContentSessionType.STREAMING;
        DeliveryType deliveryType = DeliveryType.STREAMING;
        ImmutableMap.Builder builderPut = builder.put(contentSessionType, deliveryType).put(ContentSessionType.STREAMING_CONTINUOUS, deliveryType);
        ContentSessionType contentSessionType2 = ContentSessionType.DOWNLOAD;
        DeliveryType deliveryType2 = DeliveryType.DOWNLOAD;
        ImmutableMap.Builder builderPut2 = builderPut.put(contentSessionType2, deliveryType2).put(ContentSessionType.PROGRESSIVE_PLAYBACK, deliveryType2);
        ContentSessionType contentSessionType3 = ContentSessionType.CONTENT_CACHE;
        DeliveryType deliveryType3 = DeliveryType.PRECACHE;
        CONSUMPTION_TYPE_MAP = (ImmutableMap) Preconditions2.checkFullKeyMapping(ContentSessionType.class, builderPut2.put(contentSessionType3, deliveryType3).put(ContentSessionType.LIVE_CACHE, deliveryType3).build());
    }

    public ContentManagementEventListener(Context context) {
        this(NetworkConnectionManager.getInstance(), new LowMemoryReceiver(context), QoSConfig.INSTANCE.getFragmentDownloadTimeThreshold(), SmoothStreamingPlaybackConfig.INSTANCE, LiveStreamingPlaybackConfig.getInstance());
    }

    ContentManagementEventListener(NetworkConnectionManager networkConnectionManager, LowMemoryReceiver lowMemoryReceiver, TimeSpan timeSpan, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, LiveStreamingPlaybackConfig liveStreamingPlaybackConfig) {
        this.mErrorConfig = new ErrorReportingConfiguration();
        this.mWanTracker = new WanFragmentDownloadCounter();
        this.mDownloadStartedEventSent = new AtomicBoolean(false);
        this.mRetriableErrorEventThrottle = new EventThrottle(new ExponentialSequence(1, 2.0d, 60));
        this.mVideoBufferFullnessInNanoseconds = 0L;
        this.mAudioBufferFullnessInNanoseconds = 0L;
        this.mIsDownloadingOverWan = false;
        this.mNetworkConnectionManager = networkConnectionManager;
        this.mLowMemReceiver = lowMemoryReceiver;
        this.mSmoothStreamingConfig = smoothStreamingPlaybackConfig;
        this.mFragmentDownloadTimeReportingThreshold = timeSpan;
        this.mLiveStreamingPlaybackConfig = liveStreamingPlaybackConfig;
    }

    public void bind(EventDispatcher eventDispatcher, ContentManagementEventReporter contentManagementEventReporter, SmoothStreamingReporter smoothStreamingReporter) {
        this.mReporter = contentManagementEventReporter;
        this.mSmoothStreamingReporter = smoothStreamingReporter;
        super.bind(eventDispatcher);
        this.mLowMemReceiver.register(this.mReporter);
    }

    @Override // com.amazon.avod.qos.listeners.QosEventListenerBase
    public void release() {
        this.mLowMemReceiver.unregister();
        super.release();
    }

    public void setQosNote(@Nullable String str) {
        this.mQosNote = str;
    }

    public void setAudioLanguage(@Nullable String str) {
        this.mReporter.setAudioLanguage(str);
    }

    public void setContentSessionType(@Nonnull ContentSessionType contentSessionType) {
        Preconditions.checkNotNull(contentSessionType);
        this.mReporter.setDeliveryType(CONSUMPTION_TYPE_MAP.get(contentSessionType));
    }

    @Subscribe
    public void handleSessionStartingEvent(ContentEventSessionStarting contentEventSessionStarting) {
        int streamingFutureBufferSizeSeconds;
        this.mContext = contentEventSessionStarting.getContext();
        if (this.mReporter.isAudioTrackSwitching()) {
            this.mReporter.handleAudioTrackSwitch((String) MoreObjects.firstNonNull(this.mContext.getStreamSelections().getAudioStream().getLanguage(), EnvironmentCompat.MEDIA_UNKNOWN), contentEventSessionStarting.getContext().getContentUrl().getSessionId());
        }
        if (this.mReporter.isAudioAdaptationSetSwitching()) {
            StreamSelections streamSelections = this.mContext.getStreamSelections();
            this.mReporter.handleAudioAdaptationSetSwitch(streamSelections.getAudioStream().getFourCC(), streamSelections.getStartAudioQualityLevel().getBitrate());
        }
        this.mReporter.setDuration(this.mContext.getDuration());
        ContentUrl initialUrl = contentEventSessionStarting.getInitialUrl();
        this.mReporter.setInitialCdnConnection(initialUrl.getSessionId(), initialUrl.getCdnName(), initialUrl.getUrl(), initialUrl.getOrigin());
        if (this.mContext.getSpecification().isLiveStream()) {
            streamingFutureBufferSizeSeconds = this.mLiveStreamingPlaybackConfig.getLiveStreamingFutureBufferSizeSeconds(this.mContext.getHeuristics());
        } else {
            streamingFutureBufferSizeSeconds = this.mSmoothStreamingConfig.getStreamingFutureBufferSizeSeconds(this.mContext.getHeuristics());
        }
        this.mSmoothStreamingReporter.setMaxBufferFullnessMillis(TimeSpan.fromSeconds(streamingFutureBufferSizeSeconds).getTotalMilliseconds());
        if (this.mContext.getSpecification().isLiveStream()) {
            this.mVideoFragmentReporter = new SubmittedToRendererVideoFragmentReporter(this.mSmoothStreamingReporter);
        } else {
            this.mVideoFragmentReporter = new DownloadedVideoFragmentReporter(this.mSmoothStreamingReporter);
        }
    }

    @Subscribe
    public void handleCdnSwitch(CdnSwitchedEvent cdnSwitchedEvent) {
        ContentUrl newContentUrl = cdnSwitchedEvent.getNewContentUrl();
        ContentUrl oldContentUrl = cdnSwitchedEvent.getOldContentUrl();
        String sessionId = newContentUrl.getSessionId();
        String cdnName = newContentUrl.getCdnName();
        this.mReporter.reportCdnSwitch(sessionId, cdnName, newContentUrl.getUrl(), String.format(Locale.US, "newUrlSessId: %s | newCdnName: %s | oldUrlSessId: %s | oldCdnName: %s | failureCause: %s | failoverType: %s | failoverMode: %s", sessionId, cdnName, oldContentUrl.getSessionId(), oldContentUrl.getCdnName(), cdnSwitchedEvent.getFailureCause().getMessage(), cdnSwitchedEvent.getFailoverType(), cdnSwitchedEvent.getFailoverMode()), newContentUrl.getOrigin());
    }

    @Subscribe
    public void handleDownloadingEvent(ContentEventStartDownloading contentEventStartDownloading) {
        this.mDownloadStartTime = new TimeSpan(contentEventStartDownloading.getEpochTimeInNanos());
    }

    @Subscribe
    public void handleFragmentRequestedEvent(ContentEventFragmentRequested contentEventFragmentRequested) {
        FragmentInformationHolder fragmentInformationHolder = contentEventFragmentRequested.getFragmentInformationHolder();
        if (fragmentInformationHolder.isVideo()) {
            this.mReporter.reportCurrentBitrateKbps((long) DataUnit.BITS.toKiloBits(fragmentInformationHolder.getBitrate()));
            if (this.mDownloadStartedEventSent.compareAndSet(false, true)) {
                this.mReporter.reportDownloadStartMetrics(fragmentInformationHolder.getChunkIndex() != 0, this.mDownloadStartTime, this.mContext.getSessionType() == ContentSessionType.CONTENT_CACHE ? "Precache" : String.format(Locale.US, "%s | %s", this.mContext.getStreamSelections().toString(), this.mQosNote));
            }
        }
    }

    @Subscribe
    public void handleStopEvent(ContentEventStopDownloading contentEventStopDownloading) {
        if (this.mDownloadStartedEventSent.get()) {
            TimeSpan timeSpan = new TimeSpan(contentEventStopDownloading.getEpochTimeInNanos());
            this.mReporter.reportDownloadSessionMetric(this.mDownloadStartTime, timeSpan, this.mWanTracker.getNotes());
            this.mSmoothStreamingReporter.reportSessionEnd(timeSpan);
        }
    }

    @Subscribe
    public void handleRetriableErrorEvent(RetriableContentEventError retriableContentEventError) {
        if (this.mRetriableErrorEventThrottle.shouldSendEvent()) {
            this.mRetriableErrorEventThrottle.onEvent();
            processError(retriableContentEventError, String.format(Locale.US, "%s (tryCount = %d)", retriableContentEventError.getErrorCode(), Integer.valueOf(retriableContentEventError.getRetryCount())));
        }
    }

    @Subscribe
    public void handleContentStoreEvent(ContentStoreEvent contentStoreEvent) {
        this.mReporter.reportContentStoreEvent(contentStoreEvent.getContentStoreInfo());
    }

    @Subscribe
    public void handleErrorEvent(FatalContentEventError fatalContentEventError) {
        processError(fatalContentEventError, fatalContentEventError.getErrorCode().name());
    }

    private void processError(ContentEventErrorBase contentEventErrorBase, String str) {
        String url;
        String string = contentEventErrorBase.getException().toString();
        ImmutableMap<ContentException.ContentError, String> immutableMap = ERROR_SUBTYPE_MAP;
        String str2 = immutableMap.get(contentEventErrorBase.getErrorCode());
        String str3 = str2 == null ? immutableMap.get(ContentException.ContentError.UNKNOWN_ERROR) : str2;
        int severityFor = this.mErrorConfig.getSeverityFor(contentEventErrorBase.getException(), contentEventErrorBase.isRetriable(), contentEventErrorBase.getException().getStatusCode());
        ContentUrl url2 = contentEventErrorBase.getUrl();
        SmoothStreamingURI smoothStreamingUri = contentEventErrorBase.getSmoothStreamingUri();
        if (url2 == null) {
            url = null;
        } else if (smoothStreamingUri != null) {
            url = smoothStreamingUri.getAbsoluteUrl(url2.getUrl());
        } else {
            url = url2.getUrl();
        }
        this.mReporter.onError(str3, string, severityFor, str, url);
    }

    private String getMediaQualityName() {
        return this.mContext.getState().getMediaQuality().name();
    }

    @Subscribe
    public void handleFragmentDownloadedEvent(ContentEventFragmentDownloaded contentEventFragmentDownloaded) {
        Preconditions.checkState(this.mVideoFragmentReporter != null, "mVideoFragmentReporter should be created before");
        FragmentInformationHolder fragmentInformationHolder = contentEventFragmentDownloaded.getFragmentInformationHolder();
        boolean zHasWANConnection = this.mNetworkConnectionManager.hasWANConnection();
        if (this.mIsDownloadingOverWan != zHasWANConnection) {
            this.mReporter.reportWanUsage(fragmentInformationHolder.getChunkIndex(), getMediaQualityName());
            this.mIsDownloadingOverWan = zHasWANConnection;
        }
        this.mWanTracker.onFragmentDownloaded(zHasWANConnection);
        if (fragmentInformationHolder.isVideo()) {
            this.mVideoFragmentReporter.onFragmentDownloaded(contentEventFragmentDownloaded);
            this.mVideoDownloadCompletionPercent = contentEventFragmentDownloaded.getDownloadPercentage();
        } else if (fragmentInformationHolder.isAudio()) {
            this.mAudioDownloadCompletionPercent = contentEventFragmentDownloaded.getDownloadPercentage();
        }
        this.mReporter.reportDownloadProgress(Math.min(this.mVideoDownloadCompletionPercent, this.mAudioDownloadCompletionPercent));
        TimeSpan timeSpan = new TimeSpan(contentEventFragmentDownloaded.getDownloadTimeInNanos());
        if (this.mFragmentDownloadTimeReportingThreshold.compareTo(timeSpan) <= 0 && fragmentInformationHolder.getAbsoluteUrl() != null) {
            this.mReporter.reportSlowFragmentDownload(fragmentInformationHolder.getAbsoluteUrl(), timeSpan.getTotalMilliseconds());
        }
        this.mRetriableErrorEventThrottle.reset();
    }

    @Subscribe
    public void handleFragmentConsumedEvent(ContentEventFragmentConsumed contentEventFragmentConsumed) {
        Preconditions.checkState(this.mVideoFragmentReporter != null, "mVideoFragmentReporter should be created before");
        this.mVideoFragmentReporter.onFragmentConsumed(contentEventFragmentConsumed);
    }

    @Subscribe
    public void handleBufferFullnessEvent(ContentEventBufferFullnessChanged contentEventBufferFullnessChanged) {
        if (contentEventBufferFullnessChanged.getStream().isVideo()) {
            this.mVideoBufferFullnessInNanoseconds = contentEventBufferFullnessChanged.getBufferFullnessInNanos();
        } else {
            this.mAudioBufferFullnessInNanoseconds = contentEventBufferFullnessChanged.getBufferFullnessInNanos();
        }
        this.mSmoothStreamingReporter.reportBufferFullnessInNanos(Math.min(this.mVideoBufferFullnessInNanoseconds, this.mAudioBufferFullnessInNanoseconds));
    }

    @Subscribe
    public void handleSecondaryDownloadEligibilityStateChangedEvent(ContentEventSecondaryDownloadEligibilityStateChanged contentEventSecondaryDownloadEligibilityStateChanged) {
        if (contentEventSecondaryDownloadEligibilityStateChanged.stateChangeDueToSeek() || contentEventSecondaryDownloadEligibilityStateChanged.areSecondaryDownloadsAllowed()) {
            return;
        }
        int i = contentEventSecondaryDownloadEligibilityStateChanged.isLastCancellation() ? 3 : 4;
        Iterator<DownloadRequest> it = contentEventSecondaryDownloadEligibilityStateChanged.getCancelledRequests().iterator();
        while (it.hasNext()) {
            this.mReporter.reportError("SecondaryDownloadCancelled", null, it.next().getUrl(), i);
        }
    }
}
