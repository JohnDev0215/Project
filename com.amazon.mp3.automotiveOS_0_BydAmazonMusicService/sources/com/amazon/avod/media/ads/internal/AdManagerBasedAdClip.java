package com.amazon.avod.media.ads.internal;

import android.text.TextUtils;
import com.amazon.avod.ads.api.AdInfo;
import com.amazon.avod.ads.api.AdInfoErrorCode;
import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.ads.api.AdSkipInfo;
import com.amazon.avod.ads.api.Beacon;
import com.amazon.avod.ads.api.CreativeLinear;
import com.amazon.avod.ads.api.Duration;
import com.amazon.avod.ads.api.MediaFile;
import com.amazon.avod.ads.parser.vast.VastTrackingEventType;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.DataRate;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.MimeTypes;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.framework.uriproxy.EnhancedURI;
import com.amazon.avod.media.framework.util.HostNameMatcher;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.UserWatchSessionIdManager;
import com.amazon.avod.media.playback.VideoAttributes;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationFactory;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.android.AndroidVideoPlayerV2Config;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.monitoring.TimelineMonitoringTask;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDiagnosticsReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackStateTracker;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.reporting.aloysius.util.AloysiusUtil;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleTimerMetric;
import com.amazon.avod.playback.PlaybackAdStateChangeListener;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.playback.AdPlaybackCompletedEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackLoadedEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackPausedEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackPlayingEvent;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.session.ConsumptionIdProvider;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.visualon.OSMPUtils.voMimeTypes;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdManagerBasedAdClip implements AdClip {
    private static final List<String> SUPPORTED_MIME_TYPES = Lists.newArrayList(voMimeTypes.VOVIDEO_MP4, "image/jpeg", "image/png");
    private CacheStatus mAdClipCacheStatus;
    private TimeSpan mAdClipStartTime;
    private final AdErrorReporter mAdErrorSender;
    private AdInfo mAdInfo;
    private final PlaybackAdStateChangeListener mAdStateChangeListener;
    private final AdsConfig mAdsConfig;
    private AloysiusPlaybackReporter mAloysiusPlaybackReporter;
    private final AloysiusPlaybackStateTracker mAloysiusPlaybackStateTracker;
    private final AndroidVideoPlayerV2Cache mAndroidVideoPlayerV2Cache;
    private final List<VastTrackingEventType> mBeaconList;
    private int mCacheBitrateKbps;
    private ConsumptionIdProvider mConsumptionIdProvider;
    private CreativeLinear mCreativeLinear;
    private int mEstimatedSize;
    private final ExecutorService mExecutor;
    private List<Object> mExtensions;
    private final boolean mIsAndroidVideoPlayerV2CacheEnabled;
    private boolean mIsPersistent;
    private boolean mIsPlayed;
    private boolean mIsPrepared;
    private final AdClipMediaFileChooser mMediaFileChooser;
    private URI mMediaUri;
    private AdSkipInfo mParentSkipInfo;
    private EnhancedURI mPreloadedUri;
    private VideoPresentation mPresentation;
    private final VideoPresentationFactory mPresentationFactory;

    @Nonnull
    private final QOSCommunicationService mQOSCommunicationService;
    private PlaybackEventReporter mReporter;
    private MediaFile mSelectedMediaFile;
    private final long mStrictModeTrustIntervalForBeaconReportingInMillis;
    private final AdUriProxy mUriProxy;
    private final UserWatchSessionIdManager mUserWatchSessionIdManager;

    public AdManagerBasedAdClip(VideoPresentationFactory videoPresentationFactory, ExecutorService executorService, AdUriProxy adUriProxy, AdsConfig adsConfig, AdClipMediaFileChooser adClipMediaFileChooser, @Nonnull QOSCommunicationService qOSCommunicationService, @Nonnull AndroidVideoPlayerV2Cache androidVideoPlayerV2Cache) {
        this(videoPresentationFactory, executorService, adUriProxy, new AdErrorReporter(executorService), adsConfig, adClipMediaFileChooser, qOSCommunicationService, androidVideoPlayerV2Cache, AndroidVideoPlayerV2Config.getInstance(), AloysiusPlaybackStateTracker.getInstance(), UserWatchSessionIdManager.getInstance(), AdsConfig.getInstance().getStrictModeTrustIntervalForBeaconReportingInMillis());
    }

    AdManagerBasedAdClip(VideoPresentationFactory videoPresentationFactory, ExecutorService executorService, AdUriProxy adUriProxy, AdErrorReporter adErrorReporter, AdsConfig adsConfig, AdClipMediaFileChooser adClipMediaFileChooser, @Nonnull QOSCommunicationService qOSCommunicationService, @Nonnull AndroidVideoPlayerV2Cache androidVideoPlayerV2Cache, @Nonnull AndroidVideoPlayerV2Config androidVideoPlayerV2Config, @Nonnull AloysiusPlaybackStateTracker aloysiusPlaybackStateTracker, @Nonnull UserWatchSessionIdManager userWatchSessionIdManager, long j) {
        this.mIsPlayed = false;
        this.mIsPrepared = false;
        this.mIsPersistent = false;
        this.mAdClipCacheStatus = CacheStatus.UNCACHED;
        this.mBeaconList = new ArrayList();
        this.mAdStateChangeListener = new PlaybackAdStateChangeListener() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.15
            @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
            public void onAdPlaybackLoaded() {
                AdPlaybackLoadedEvent adPlaybackLoadedEvent = new AdPlaybackLoadedEvent();
                AdManagerBasedAdClip.this.mQOSCommunicationService.getEventTransport().postEvent(adPlaybackLoadedEvent);
                if (AdManagerBasedAdClip.this.mAloysiusPlaybackReporter != null) {
                    AdManagerBasedAdClip.this.mAloysiusPlaybackReporter.handlePlaybackLoadedEvent(adPlaybackLoadedEvent);
                }
            }

            @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
            public void onAdPlaybackPaused() {
                AdPlaybackPausedEvent adPlaybackPausedEvent = new AdPlaybackPausedEvent();
                AdManagerBasedAdClip.this.mQOSCommunicationService.getEventTransport().postEvent(adPlaybackPausedEvent);
                if (AdManagerBasedAdClip.this.mAloysiusPlaybackReporter != null) {
                    AdManagerBasedAdClip.this.mAloysiusPlaybackReporter.handlePlaybackPausedEvent(adPlaybackPausedEvent);
                }
            }

            @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
            public void onAdPlaybackPlaying() {
                AloysiusPlaybackStateTracker.PlaybackState playerCurrentState = AdManagerBasedAdClip.this.mAloysiusPlaybackStateTracker.getPlayerCurrentState();
                if (AdManagerBasedAdClip.this.mAdsConfig.shouldSuppressAdClipStartStopReporting() && (!AdManagerBasedAdClip.this.mAdsConfig.shouldReportPlaybackStartEventDuringAds() || playerCurrentState != AloysiusPlaybackStateTracker.PlaybackState.AdPaused)) {
                    if (AdManagerBasedAdClip.this.mAdsConfig.isAdsAloysiusStateTransitionEnabled() && playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.AdPaused) {
                        AdManagerBasedAdClip.this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.AdResume);
                        return;
                    }
                    return;
                }
                AdPlaybackPlayingEvent adPlaybackPlayingEvent = new AdPlaybackPlayingEvent();
                AdManagerBasedAdClip.this.mQOSCommunicationService.getEventTransport().postEvent(adPlaybackPlayingEvent);
                if (AdManagerBasedAdClip.this.mAloysiusPlaybackReporter != null) {
                    AdManagerBasedAdClip.this.mAloysiusPlaybackReporter.handlePlaybackPlayingEvent(adPlaybackPlayingEvent);
                }
            }

            @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
            public void onAdPlaybackCompleted() {
                if (AdManagerBasedAdClip.this.mAdsConfig.shouldSuppressAdClipStartStopReporting()) {
                    return;
                }
                AdPlaybackCompletedEvent adPlaybackCompletedEvent = new AdPlaybackCompletedEvent();
                AdManagerBasedAdClip.this.mQOSCommunicationService.getEventTransport().postEvent(adPlaybackCompletedEvent);
                if (AdManagerBasedAdClip.this.mAloysiusPlaybackReporter != null) {
                    AdManagerBasedAdClip.this.mAloysiusPlaybackReporter.handlePlaybackCompletedEvent(adPlaybackCompletedEvent);
                }
            }
        };
        this.mPresentationFactory = videoPresentationFactory;
        this.mExecutor = executorService;
        this.mUriProxy = adUriProxy;
        this.mAdErrorSender = adErrorReporter;
        this.mAdsConfig = adsConfig;
        this.mMediaFileChooser = adClipMediaFileChooser;
        this.mQOSCommunicationService = (QOSCommunicationService) Preconditions.checkNotNull(qOSCommunicationService, "qosCommunicationService");
        this.mAndroidVideoPlayerV2Cache = (AndroidVideoPlayerV2Cache) Preconditions.checkNotNull(androidVideoPlayerV2Cache, "exoPlayerCache");
        this.mIsAndroidVideoPlayerV2CacheEnabled = ((AndroidVideoPlayerV2Config) Preconditions.checkNotNull(androidVideoPlayerV2Config, "androidVideoPlayerV2Config")).isAndroidVideoPlayerV2CacheEnabled();
        this.mAloysiusPlaybackStateTracker = (AloysiusPlaybackStateTracker) Preconditions.checkNotNull(aloysiusPlaybackStateTracker, "aloysiusPlaybackStateTracker");
        this.mUserWatchSessionIdManager = (UserWatchSessionIdManager) Preconditions.checkNotNull(userWatchSessionIdManager, "userWatchSessionIdManager");
        this.mConsumptionIdProvider = new ConsumptionIdProvider();
        this.mStrictModeTrustIntervalForBeaconReportingInMillis = j;
    }

    public void initialize(CreativeLinear creativeLinear, @Nullable AdSkipInfo adSkipInfo, @Nonnull TimeSpan timeSpan) {
        this.mCreativeLinear = (CreativeLinear) Preconditions.checkNotNull(creativeLinear);
        this.mAdInfo = (AdInfo) Preconditions.checkNotNull(creativeLinear.getAdInfo());
        this.mCacheBitrateKbps = this.mAdsConfig.getCacheBitrateKbps();
        this.mParentSkipInfo = adSkipInfo;
        this.mAdClipStartTime = (TimeSpan) Preconditions.checkNotNull(timeSpan, "adClipStartTime");
        List<MediaFile> mediaFiles = this.mCreativeLinear.getMediaFiles();
        if (mediaFiles != null) {
            DLog.logf("Available Ad MediaFiles:");
            Iterator<MediaFile> it = mediaFiles.iterator();
            while (it.hasNext()) {
                DLog.logf(it.next().toString());
            }
        }
        this.mExtensions = this.mCreativeLinear.getExtensions();
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public VideoPlayer getPlayer() {
        VideoPresentation videoPresentation = this.mPresentation;
        if (videoPresentation != null) {
            return videoPresentation.getPlayer();
        }
        return null;
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public AdSkipInfo getAdSkipInfo() {
        Preconditions.checkNotNull(this.mCreativeLinear, "getAdSkipInfo should be called after clip is initialized");
        if (this.mAdsConfig.getAdMasterEnabled()) {
            return this.mAdsConfig.getAdSkipInfo();
        }
        AdSkipInfo adSkipInfo = this.mParentSkipInfo;
        if (adSkipInfo != null) {
            return adSkipInfo;
        }
        if (this.mAdsConfig.shouldSkipAds()) {
            return new AdSkipInfo(false, 0L, 0L);
        }
        return this.mCreativeLinear.getAdSkipInfo();
    }

    public boolean infoUrlIsSafe() {
        String infoUrl = getInfoUrl();
        if (infoUrl == null) {
            return true;
        }
        Set<String> blacklistUrls = this.mAdsConfig.getBlacklistUrls();
        Set<String> whitelistUrls = this.mAdsConfig.getWhitelistUrls();
        try {
            return !whitelistUrls.isEmpty() ? new HostNameMatcher(whitelistUrls).matches(infoUrl) : !new HostNameMatcher(blacklistUrls).matches(infoUrl);
        } catch (MalformedURLException e) {
            DLog.exceptionf(e, "Error while checking if an ad's click through URL (%s) was safe", infoUrl);
            return false;
        }
    }

    public void sendError(AdInfoErrorCode adInfoErrorCode) {
        this.mAdErrorSender.sendError(this.mAdInfo, adInfoErrorCode);
        this.mQOSCommunicationService.getEventTransport().postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new ContentException(convertAdInfoErrorToContentError(adInfoErrorCode), String.format(Locale.US, "AdInfoErrorCode: %s", adInfoErrorCode))));
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip$16, reason: invalid class name */
    static /* synthetic */ class AnonymousClass16 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode;

        static {
            int[] iArr = new int[AdInfoErrorCode.values().length];
            $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode = iArr;
            try {
                iArr[AdInfoErrorCode.XML_PARSING_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.VAST_SCHEMA_VALIDATION_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.VAST_VERSION_NOT_SUPPORTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.WRAPPER_HTTP_TIMEOUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.MEDIA_FILE_URI_TIMEOUT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.MEDIA_FILE_NOT_FOUND.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.GENERAL_WRAPPER_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.MEDIA_FILE_NOT_SUPPORTED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.AD_TYPE_UNSUPPORTED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.PLAYER_EXPECTING_DIFFERENT_LINEARITY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.PLAYER_EXPECTING_DIFFERENT_DURATION.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.PLAYER_EXPECTING_DIFFERENT_SIZE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.WRAPPER_LIMIT_REACHED.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.NO_ADS_AFTER_FOLLOWING_WRAPPER.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.GENERAL_LINEAR_ERROR.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.MEDIA_FILE_DISPLAY_PROBLEM.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.GENERAL_NONLINEAR_ERROR.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.NONLINEAR_DIMENSIONS_PROBLEM.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.NONLINEAR_FETCH_PROBLEM.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.NONLINEAR_NOT_SUPPORTED.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.GENERAL_COMPANION_ADS_ERROR.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.COMPANION_ADS_DIMENSIONS_PROBLEM.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.COMPANION_ADS_REQUIRED_BUT_NOT_DISPLAYABLE.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.COMPANION_ADS_FETCH_PROBLEM.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.COMPANION_ADS_RESOURCE_NOT_SUPPORTED.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.UNDEFINED_ERROR.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[AdInfoErrorCode.GENERAL_VPAID_ERROR.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
        }
    }

    private ContentException.ContentError convertAdInfoErrorToContentError(AdInfoErrorCode adInfoErrorCode) {
        switch (AnonymousClass16.$SwitchMap$com$amazon$avod$ads$api$AdInfoErrorCode[adInfoErrorCode.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return ContentException.ContentError.MALFORMED_MANIFEST;
            case 4:
            case 5:
                return ContentException.ContentError.NETWORK_ERROR;
            case 6:
                return ContentException.ContentError.FILE_MISSING;
            default:
                return ContentException.ContentError.UNKNOWN_ERROR;
        }
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public String getInfoUrl() {
        if (this.mAdsConfig.getAdMasterEnabled()) {
            return this.mAdsConfig.getAdMasterInjectedLearnMoreURL();
        }
        if (this.mCreativeLinear.getClickThroughUri() != null) {
            return this.mCreativeLinear.getClickThroughUri().toString();
        }
        return null;
    }

    @Override // com.amazon.avod.media.ads.AdClip
    @Nullable
    public String getContentType() {
        return this.mCreativeLinear.getContentType();
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public boolean isPlayed() {
        return this.mIsPlayed;
    }

    @Override // com.amazon.avod.media.ads.AdClip
    @Nonnull
    public CacheStatus getCacheStatus() {
        if (this.mIsAndroidVideoPlayerV2CacheEnabled) {
            return this.mAndroidVideoPlayerV2Cache.getCacheStatus(this.mPreloadedUri);
        }
        return this.mUriProxy.contains(this.mPreloadedUri) ? CacheStatus.FULLY_CACHED : CacheStatus.UNCACHED;
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public TimeSpan getDuration() {
        return TimeSpan.fromMilliseconds(this.mCreativeLinear.getDuration().getTotalMilliseconds());
    }

    public void sendStartEvent(String str, String str2) {
        this.mConsumptionIdProvider.setConsumptionId(str2);
        sendImpression(str, this.mConsumptionIdProvider.getConsumptionId());
        sendEvent(VastTrackingEventType.creativeView, str);
        sendEvent(VastTrackingEventType.start, str);
        sendAdCacheQos();
    }

    public void sendCompleteEvent(String str) {
        sendEvent(VastTrackingEventType.complete, str);
    }

    public void sendCloseEvent(String str) {
        sendEvent(VastTrackingEventType.close, str);
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public void sendPauseEvent(String str) {
        sendEvent(VastTrackingEventType.pause, str);
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public void sendResumeEvent(String str) {
        sendEvent(VastTrackingEventType.resume, str);
    }

    public void sendSkipClipEvent(String str) {
        sendEvent(VastTrackingEventType.skip, str);
        sendAdSkipQos();
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public void sendMuteEvent(String str) {
        sendEvent(VastTrackingEventType.mute, str);
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public void sendUnmuteEvent(String str) {
        sendEvent(VastTrackingEventType.unmute, str);
    }

    public void scheduleTimelineEvents(TimelineMonitor timelineMonitor) {
        List<Beacon> timeBasedBeacons = this.mCreativeLinear.getTimeBasedBeacons();
        final String consumptionId = this.mConsumptionIdProvider.getConsumptionId();
        for (final Beacon beacon : timeBasedBeacons) {
            timelineMonitor.scheduleTask(getPlayer(), new TimelineMonitoringTask(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.3
                @Override // java.lang.Runnable
                public void run() {
                    AdManagerBasedAdClip.this.sendBeacon(beacon, consumptionId);
                }
            }, TimeSpan.fromMilliseconds(beacon.getOffset().getDuration(this.mCreativeLinear.getDuration().getTotalMilliseconds()).getTotalMilliseconds())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendStartEventForPrimary(@Nonnull String str, @Nonnull Duration duration, String str2) {
        Preconditions.checkNotNull(str, "offerType");
        Preconditions.checkNotNull(duration, "duration");
        sendImpression(str, duration, str2);
        sendEvent(VastTrackingEventType.creativeView, str);
        sendEvent(VastTrackingEventType.start, str);
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public void scheduleTimelineEventsWithPrimaryPlayer(@Nonnull TimelineMonitor timelineMonitor, @Nonnull final VideoPlayer videoPlayer, @Nonnull final PlaybackEventReporter playbackEventReporter, @Nonnull final String str) {
        Preconditions.checkNotNull(timelineMonitor, "TimelineMonitor");
        Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
        Preconditions.checkNotNull(videoPlayer, "primaryPlayer");
        Preconditions.checkNotNull(str, "offerType");
        List<Beacon> timeBasedBeacons = this.mCreativeLinear.getTimeBasedBeacons();
        final long totalMilliseconds = getAdClipStartTime().getTotalMilliseconds();
        long totalMilliseconds2 = totalMilliseconds + this.mCreativeLinear.getDuration().getTotalMilliseconds();
        timelineMonitor.scheduleTask(videoPlayer, new TimelineMonitoringTask(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.4
            @Override // java.lang.Runnable
            public void run() {
                ContentSession contentSession = videoPlayer.getContentSession();
                String consumptionId = contentSession != null ? contentSession.getConsumptionId() : null;
                AdManagerBasedAdClip.this.mConsumptionIdProvider.setConsumptionId(consumptionId);
                AdManagerBasedAdClip.this.sendStartEventForPrimary(str, new Duration(totalMilliseconds), consumptionId);
            }
        }, TimeSpan.fromMilliseconds(totalMilliseconds), true, true, this.mStrictModeTrustIntervalForBeaconReportingInMillis));
        timelineMonitor.scheduleTask(videoPlayer, new TimelineMonitoringTask(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.5
            @Override // java.lang.Runnable
            public void run() {
                AdManagerBasedAdClip.this.sendCompleteEvent(str);
            }
        }, TimeSpan.fromMilliseconds(totalMilliseconds2), true, true, this.mStrictModeTrustIntervalForBeaconReportingInMillis));
        for (final Beacon beacon : timeBasedBeacons) {
            timelineMonitor.scheduleTask(videoPlayer, new TimelineMonitoringTask(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.6
                @Override // java.lang.Runnable
                public void run() {
                    AdManagerBasedAdClip.this.sendBeaconForPrimary(beacon, videoPlayer, playbackEventReporter);
                }
            }, TimeSpan.fromMilliseconds(beacon.getOffset().getDuration(this.mCreativeLinear.getDuration().getTotalMilliseconds()).getTotalMilliseconds() + totalMilliseconds), true, true, this.mStrictModeTrustIntervalForBeaconReportingInMillis));
        }
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public void preload(TimeSpan timeSpan, boolean z, String str) {
        if (this.mPreloadedUri != null) {
            return;
        }
        List<MediaFile> listFindValidMediaFiles = findValidMediaFiles(this.mCreativeLinear.getMediaFiles());
        if (listFindValidMediaFiles.isEmpty()) {
            DLog.warnf("Ad pre-loading failed. Could not find any valid MediaFiles for: %s", this.mAdInfo.getAdId());
            return;
        }
        MediaFile mediaFileFindMediaFileOfQuality = this.mMediaFileChooser.findMediaFileOfQuality(listFindValidMediaFiles, this.mCacheBitrateKbps);
        this.mSelectedMediaFile = mediaFileFindMediaFileOfQuality;
        if (mediaFileFindMediaFileOfQuality == null) {
            DLog.warnf("Ad pre-loading failed. Could not find a suitable MediaFile for: %s", this.mAdInfo.getAdId());
            return;
        }
        int iEstimateFileSize = estimateFileSize(mediaFileFindMediaFileOfQuality, this.mCreativeLinear.getDuration());
        EnhancedURI enhancedUri = getEnhancedUri(this.mSelectedMediaFile, getUniqueIdForAd(), getAdServer(), this.mAdsConfig.isAdIdBasedCachingEnabled());
        this.mPreloadedUri = enhancedUri;
        DLog.logf("Ad cache pre-loading URI %s identifier %s, persistence %s", enhancedUri.getURI(), this.mPreloadedUri.getIdentifier(), Boolean.toString(z));
        this.mEstimatedSize = iEstimateFileSize;
        this.mIsPersistent = z;
        if (this.mIsAndroidVideoPlayerV2CacheEnabled) {
            this.mAndroidVideoPlayerV2Cache.cache(this.mPreloadedUri, str);
        } else {
            this.mUriProxy.load(this.mPreloadedUri, timeSpan, iEstimateFileSize, z, str);
        }
    }

    public void releasePresentation() {
        this.mPresentation.terminate(false);
        this.mPresentation.getPlayer().clearAllListeners();
        this.mUserWatchSessionIdManager.remove(this.mPresentation.getSpecification().getUrl());
        this.mPresentation = null;
    }

    public boolean createPresentation(String str, String str2, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters) throws AdException {
        String string;
        this.mAloysiusPlaybackReporter = ((PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "playbackMediaEventReporters")).getPlaybackReporter();
        URI uriLookup = this.mUriProxy.lookup(this.mPreloadedUri, this.mIsPersistent);
        if (this.mIsAndroidVideoPlayerV2CacheEnabled) {
            CacheStatus cacheStatus = this.mAndroidVideoPlayerV2Cache.getCacheStatus(this.mPreloadedUri);
            if (cacheStatus == CacheStatus.FULLY_CACHED) {
                string = this.mPreloadedUri.getURI().toString();
                this.mAdClipCacheStatus = cacheStatus;
            } else {
                MediaFile mediaFileFindBestMediaFile = findBestMediaFile(this.mCreativeLinear.getMediaFiles());
                this.mSelectedMediaFile = mediaFileFindBestMediaFile;
                URI uri = mediaFileFindBestMediaFile.getUri();
                this.mMediaUri = uri;
                string = uri.toString();
            }
            DLog.logf("Ad cache status %s uri %s", cacheStatus, string);
        } else if (uriLookup == null) {
            this.mUriProxy.cancel(this.mPreloadedUri);
            this.mUriProxy.delayLoad(this.mPreloadedUri, this.mEstimatedSize, this.mIsPersistent, str2);
            this.mPreloadedUri = null;
            if (this.mIsPersistent && this.mAdsConfig.getSkipMissedPrimePreRollAds()) {
                return false;
            }
            MediaFile mediaFileFindBestMediaFile2 = findBestMediaFile(this.mCreativeLinear.getMediaFiles());
            this.mSelectedMediaFile = mediaFileFindBestMediaFile2;
            URI uri2 = mediaFileFindBestMediaFile2.getUri();
            this.mMediaUri = uri2;
            string = uri2.toString();
            DLog.logf("Ad cache miss %s", string);
        } else {
            string = uriLookup.toString();
            this.mAdClipCacheStatus = CacheStatus.FULLY_CACHED;
            DLog.logf("Ad cache hit %s", string);
        }
        VideoResolution videoResolution = new VideoResolution(this.mSelectedMediaFile.getWidth(), this.mSelectedMediaFile.getHeight());
        VideoAttributes.Builder builderNewBuilder = VideoAttributes.newBuilder();
        builderNewBuilder.setResolution(videoResolution);
        if (this.mSelectedMediaFile.getBitrateKbps() != null) {
            builderNewBuilder.setBitrate(new DataRate(this.mSelectedMediaFile.getBitrateKbps().intValue(), DataUnit.KILOBITS));
        }
        VideoSpecification.Builder reportingTag = VideoSpecification.newBuilder().setAttributes(builderNewBuilder.build()).setAudioFormat(AudioFormat.STEREO).setMediaQuality(MediaQuality.HIGHEST).setMimeType(this.mSelectedMediaFile.getMimeType()).setStartTime(TimeSpan.ZERO).setDuration(getDuration()).setUrl(string).setReportingTag("AD:" + this.mAdInfo.getAdId());
        EnhancedURI enhancedURI = this.mPreloadedUri;
        VideoSpecification videoSpecificationBuild = reportingTag.setAdIdentifier(enhancedURI != null ? enhancedURI.getIdentifier() : null).build();
        this.mUserWatchSessionIdManager.set(string, str);
        VideoPresentation videoPresentationNewVideoPresentation = this.mPresentationFactory.newVideoPresentation(videoSpecificationBuild, null, VideoOptions.newBuilder().setSessionContext(ImmutableMap.of()).build());
        if (videoPresentationNewVideoPresentation == null) {
            throw new AdException("Failed creating video presentation.");
        }
        this.mPresentation = videoPresentationNewVideoPresentation;
        VideoPlayer player = videoPresentationNewVideoPresentation.getPlayer();
        if (player == null) {
            return true;
        }
        player.addListener(this.mAdStateChangeListener);
        return true;
    }

    @Nullable
    public VideoPresentation getPresentation() {
        return this.mPresentation;
    }

    public void setPlayed(boolean z) {
        this.mIsPlayed = z;
    }

    @Override // com.amazon.avod.media.ads.AdClip
    public boolean isPrepared() {
        return this.mIsPrepared;
    }

    public void onPrepared() {
        DLog.logf("AdManagerBasedAdClip onPrepared called with clip %s", this);
        this.mIsPrepared = true;
        this.mReporter = this.mPresentation.getReporter();
    }

    @Nullable
    public MediaFile getSelectedMediaFile() {
        return this.mSelectedMediaFile;
    }

    @Override // com.amazon.avod.media.ads.AdClip
    @Nullable
    public String getAdId() {
        AdInfo adInfo = this.mAdInfo;
        if (adInfo != null) {
            return adInfo.getAdId();
        }
        return null;
    }

    @Override // com.amazon.avod.media.ads.AdClip
    @Nullable
    public String getAdSystem() {
        AdInfo adInfo = this.mAdInfo;
        if (adInfo == null) {
            return null;
        }
        return adInfo.getAdServer();
    }

    @Override // com.amazon.avod.media.ads.AdClip
    @Nonnull
    public List<Object> getExtensions() {
        return this.mExtensions;
    }

    @Nonnull
    public TimeSpan getAdClipStartTime() {
        return this.mAdClipStartTime;
    }

    @Nullable
    public String getAdServer() {
        AdInfo adInfo = this.mAdInfo;
        if (adInfo != null) {
            return adInfo.getAdServer();
        }
        return null;
    }

    private String getUniqueIdForAd() {
        return !Strings.isNullOrEmpty(this.mCreativeLinear.getCreativeId()) ? this.mCreativeLinear.getCreativeId() : getAdId();
    }

    private List<MediaFile> findValidMediaFiles(List<MediaFile> list) {
        ArrayList arrayListNewArrayList = Lists.newArrayList(list);
        Iterator it = arrayListNewArrayList.iterator();
        while (it.hasNext()) {
            if (!SUPPORTED_MIME_TYPES.contains(((MediaFile) it.next()).getMimeType())) {
                it.remove();
            }
        }
        Collections.sort(arrayListNewArrayList, new Comparator<MediaFile>() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.7
            @Override // java.util.Comparator
            public int compare(MediaFile mediaFile, MediaFile mediaFile2) {
                return Integer.compare(AdManagerBasedAdClip.SUPPORTED_MIME_TYPES.indexOf(mediaFile.getMimeType()), AdManagerBasedAdClip.SUPPORTED_MIME_TYPES.indexOf(mediaFile2.getMimeType()));
            }
        });
        return arrayListNewArrayList;
    }

    private MediaFile findBestMediaFile(List<MediaFile> list) throws AdException {
        if (list.isEmpty()) {
            this.mAdErrorSender.sendError(this.mAdInfo, AdInfoErrorCode.GENERAL_LINEAR_ERROR);
            this.mQOSCommunicationService.getEventTransport().postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new ContentException(ContentException.ContentError.FILE_MISSING, "No media files found")));
            Preconditions.checkState(!list.isEmpty(), "No media files found");
        }
        List<MediaFile> listFindValidMediaFiles = findValidMediaFiles(list);
        if (listFindValidMediaFiles.isEmpty()) {
            this.mAdErrorSender.sendError(this.mAdInfo, AdInfoErrorCode.MEDIA_FILE_NOT_SUPPORTED);
            this.mQOSCommunicationService.getEventTransport().postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new ContentException(ContentException.ContentError.FILE_MISSING, "No appropriate media files present.")));
            throw new AdException("No appropriate media files present.");
        }
        if (this.mIsAndroidVideoPlayerV2CacheEnabled) {
            ArrayList arrayList = new ArrayList();
            for (MediaFile mediaFile : listFindValidMediaFiles) {
                CacheStatus cacheStatus = this.mAndroidVideoPlayerV2Cache.getCacheStatus(getEnhancedUri(mediaFile, getUniqueIdForAd(), getAdServer(), this.mAdsConfig.isAdIdBasedCachingEnabled()));
                if (cacheStatus.getPercentCached() > 0.0f) {
                    arrayList.add(new CachedMediaFile(mediaFile, cacheStatus));
                }
            }
            if (arrayList.size() > 0) {
                Collections.sort(arrayList, new Comparator() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip$$ExternalSyntheticLambda0
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return AdManagerBasedAdClip.lambda$findBestMediaFile$0((AdManagerBasedAdClip.CachedMediaFile) obj, (AdManagerBasedAdClip.CachedMediaFile) obj2);
                    }
                });
                CachedMediaFile cachedMediaFile = (CachedMediaFile) arrayList.get(arrayList.size() - 1);
                this.mAdClipCacheStatus = cachedMediaFile.mCacheStatus;
                return cachedMediaFile.mMediaFile;
            }
        } else {
            for (MediaFile mediaFile2 : listFindValidMediaFiles) {
                if (this.mUriProxy.contains(getEnhancedUri(mediaFile2, getUniqueIdForAd(), getAdServer(), this.mAdsConfig.isAdIdBasedCachingEnabled()))) {
                    return mediaFile2;
                }
            }
        }
        return this.mMediaFileChooser.findBestMediaFileForBandwidth(listFindValidMediaFiles);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$findBestMediaFile$0(CachedMediaFile cachedMediaFile, CachedMediaFile cachedMediaFile2) {
        return Float.compare(cachedMediaFile.mCacheStatus.getPercentCached(), cachedMediaFile2.mCacheStatus.getPercentCached());
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CachedMediaFile {
        private final CacheStatus mCacheStatus;
        private final MediaFile mMediaFile;

        CachedMediaFile(@Nonnull MediaFile mediaFile, @Nonnull CacheStatus cacheStatus) {
            this.mMediaFile = (MediaFile) Preconditions.checkNotNull(mediaFile, "mediaFile");
            this.mCacheStatus = (CacheStatus) Preconditions.checkNotNull(cacheStatus, "cacheStatus");
        }
    }

    private void sendImpression(@Nonnull String str, String str2) {
        Preconditions.checkNotNull(str, "offerType");
        sendImpression(str, getPlayHead(), str2);
    }

    private void sendImpression(@Nonnull final String str, @Nonnull final Duration duration, final String str2) {
        Preconditions.checkNotNull(str, "offerType");
        Preconditions.checkNotNull(duration, "duration");
        DLog.logf("Sending VAST event(impression) for ad(%s)", this.mAdInfo.getAdId());
        this.mBeaconList.add(VastTrackingEventType.impression);
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.8
            @Override // java.lang.Runnable
            public void run() {
                String str3;
                Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
                long jElapsed = stopwatchCreateStarted.elapsed(TimeUnit.MILLISECONDS);
                try {
                    AdManagerBasedAdClip.this.mAdInfo.sendImpression(duration, AdManagerBasedAdClip.this.mMediaUri, str2);
                    str3 = "Success";
                } catch (AdNetworkException e) {
                    DLog.exceptionf(e, "Failure to send impression for %s", AdManagerBasedAdClip.this.mAdInfo.getAdId());
                    AdManagerBasedAdClip.this.sendImpressionFailureQos(e.getMessage());
                    str3 = "Failure";
                }
                long jElapsed2 = stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS);
                AdManagerBasedAdClip adManagerBasedAdClip = AdManagerBasedAdClip.this;
                adManagerBasedAdClip.reportPmetMetric("TrackingAdImpression", adManagerBasedAdClip.getImpressionPmetTypeList(str, str3), jElapsed, jElapsed2);
            }
        });
    }

    private void sendEvent(final VastTrackingEventType vastTrackingEventType, final String str) {
        DLog.logf("Sending VAST event(%s) for ad(%s)", vastTrackingEventType, this.mAdInfo.getAdId());
        this.mBeaconList.add(vastTrackingEventType);
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.9
            @Override // java.lang.Runnable
            public void run() throws Exception {
                String str2;
                Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
                long jElapsed = stopwatchCreateStarted.elapsed(TimeUnit.MILLISECONDS);
                try {
                    AdManagerBasedAdClip.this.mCreativeLinear.sendTrackingEvent(vastTrackingEventType, AdManagerBasedAdClip.this.getPlayHead(), AdManagerBasedAdClip.this.mMediaUri, AdManagerBasedAdClip.this.mConsumptionIdProvider.getConsumptionId());
                    str2 = "Success";
                } catch (AdNetworkException e) {
                    DLog.exceptionf(e, "Failure to send VAST beacon of type(%s) for ad(%s)", vastTrackingEventType, AdManagerBasedAdClip.this.mAdInfo.getAdId());
                    AdManagerBasedAdClip.this.sendImpressionFailureQos(e.getMessage());
                    str2 = "Failure";
                }
                long jElapsed2 = stopwatchCreateStarted.stop().elapsed(TimeUnit.MILLISECONDS);
                AdManagerBasedAdClip adManagerBasedAdClip = AdManagerBasedAdClip.this;
                adManagerBasedAdClip.reportPmetMetric("TrackingAdEvent", adManagerBasedAdClip.getEventPmetTypeList(str, str2, vastTrackingEventType), jElapsed, jElapsed2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendImpressionFailureQos(String str) {
        sendImpressionFailureQos("AdEvent", "AdBeaconError", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendImpressionFailureQos(@Nonnull String str, String str2, String str3) {
        sendImpressionFailureQos(str, str2, str3, this.mReporter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendImpressionFailureQos(@Nonnull final String str, final String str2, final String str3, final PlaybackEventReporter playbackEventReporter) {
        DLog.logf("Sending Ad QOS event (%s) for subEvent (%s)", str, str2);
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.10
            @Override // java.lang.Runnable
            public void run() {
                PlaybackEventReporter playbackEventReporter2 = playbackEventReporter;
                if (playbackEventReporter2 != null) {
                    playbackEventReporter2.reportMetric(str, str2, null, str3, null);
                }
            }
        });
    }

    private void sendAdCacheQos() {
        final String str;
        if (this.mAdClipCacheStatus.isFullyCached()) {
            str = "AdCacheHit";
        } else {
            str = this.mAdClipCacheStatus.isUncached() ? "AdCacheMiss" : "AdCachePartialHit";
        }
        DLog.logf("Sending Ad QOS event(%s) for subEvent(%s)", "AdEvent", str);
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.11
            @Override // java.lang.Runnable
            public void run() {
                if (AdManagerBasedAdClip.this.mReporter != null) {
                    AdManagerBasedAdClip.this.mReporter.reportMetric("AdEvent", str, null, AdManagerBasedAdClip.this.mAdClipCacheStatus.toString(), null);
                }
            }
        });
    }

    private void sendAdSkipQos() {
        final String str = String.format(Locale.US, "AdPlayHead: %d, AdClipDuration: %d", Long.valueOf(getPlayHead().getTotalMilliseconds()), Long.valueOf(getDuration().getTotalMilliseconds()));
        DLog.logf("Sending Ad QOS event(%s) for subEvent(%s) : %s", "AdEvent", "AdClipSkipped", str);
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.12
            @Override // java.lang.Runnable
            public void run() {
                if (AdManagerBasedAdClip.this.mReporter != null) {
                    AdManagerBasedAdClip.this.mReporter.reportMetric("AdEvent", "AdClipSkipped", null, str, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBeacon(final Beacon beacon, final String str) {
        DLog.logf("Sending VAST event(%s) for ad(%s)", beacon.getEventType(), this.mAdInfo.getAdId());
        this.mBeaconList.add(beacon.getEventType());
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    beacon.sendEvent(AdManagerBasedAdClip.this.getPlayHead(), AdManagerBasedAdClip.this.mMediaUri, str);
                    AdManagerBasedAdClip.this.reportQuartilesInfoToQos(beacon);
                } catch (AdNetworkException e) {
                    String str2 = String.format(Locale.US, "Failure to send timed based beacon for %s", AdManagerBasedAdClip.this.mAdInfo.getAdId());
                    DLog.exceptionf(e, str2, new Object[0]);
                    AdManagerBasedAdClip.this.sendImpressionFailureQos("AdEvent", beacon.getEventType().name(), str2);
                }
            }
        });
    }

    public void sendBeaconForPrimary(@Nonnull final Beacon beacon, @Nonnull final VideoPlayer videoPlayer, @Nonnull final PlaybackEventReporter playbackEventReporter) {
        Preconditions.checkNotNull(beacon, "beacon");
        Preconditions.checkNotNull(videoPlayer, "primaryPlayer");
        Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
        DLog.logf("Sending VAST event(%s) for ad(%s)", beacon.getEventType(), this.mAdInfo.getAdId());
        this.mBeaconList.add(beacon.getEventType());
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdClip.14
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ContentSession contentSession = videoPlayer.getContentSession();
                    String consumptionId = contentSession != null ? contentSession.getConsumptionId() : null;
                    AdManagerBasedAdClip.this.mConsumptionIdProvider.setConsumptionId(consumptionId);
                    beacon.sendEvent(new Duration(videoPlayer.getCurrentPosition()), AdManagerBasedAdClip.this.mMediaUri, consumptionId);
                    AdManagerBasedAdClip.this.reportQuartilesInfoToQos(beacon, playbackEventReporter, videoPlayer);
                } catch (AdNetworkException e) {
                    String str = String.format("Failure to send timed based beacon for %s", AdManagerBasedAdClip.this.mAdInfo.getAdId());
                    DLog.exceptionf(e, str, new Object[0]);
                    AdManagerBasedAdClip.this.sendImpressionFailureQos("AdEvent", beacon.getEventType().name(), str, playbackEventReporter);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportQuartilesInfoToQos(Beacon beacon) {
        reportQuartilesInfoToQos(beacon, this.mReporter, getPlayer());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportQuartilesInfoToQos(Beacon beacon, @Nullable PlaybackEventReporter playbackEventReporter, @Nullable VideoPlayer videoPlayer) {
        DLog.devf(beacon.getEventType().toString());
        AloysiusDiagnosticsReporter aloysiusDiagnosticsReporter = AloysiusUtil.getAloysiusDiagnosticsReporter(videoPlayer);
        Locale locale = Locale.US;
        Object[] objArr = new Object[4];
        objArr[0] = beacon.getEvent().getUri();
        objArr[1] = this.mAdInfo.getAdId();
        objArr[2] = Long.valueOf(videoPlayer == null ? -1L : videoPlayer.getCurrentPosition());
        objArr[3] = Long.valueOf(videoPlayer != null ? videoPlayer.getCurrentAbsolutePosition() : -1L);
        String str = String.format(locale, "Beacon URI: %s AdClip: %s RelativeTime: %d AbsoluteTime: %d", objArr);
        if (playbackEventReporter != null) {
            playbackEventReporter.reportMetric(new MetricsBuilder().eventType("AdEvent").eventSubtype(beacon.getEventType().toString()).note(str));
        }
        if (aloysiusDiagnosticsReporter != null) {
            aloysiusDiagnosticsReporter.handleDiagnosticsEvent(new AloysiusDiagnosticEvent("AdTrackingBeaconSent", str, AloysiusDiagnosticsState.Discrete));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Duration getPlayHead() {
        if (getPlayer() != null) {
            return new Duration(getPlayer().getCurrentPosition());
        }
        return new Duration(0L);
    }

    private int estimateFileSize(MediaFile mediaFile, Duration duration) {
        String mimeType = mediaFile.getMimeType();
        if (MimeTypes.isImageType(mimeType)) {
            return ((mediaFile.getHeight() * mediaFile.getWidth()) * 4) / 20;
        }
        if (MimeTypes.isVideoType(mimeType)) {
            return (int) DataUnit.KILOBITS.toBytes(mediaFile.getBitrateKbps().intValue() * ((int) duration.getTotalSeconds()));
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportPmetMetric(String str, ImmutableList<String> immutableList, long j, long j2) {
        Profiler.reportTimerMetric(new SimpleTimerMetric(str, immutableList, j, j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImmutableList<String> getImpressionPmetTypeList(String str, String str2) {
        return ImmutableList.of("Metric", str, str2, String.format(Locale.US, "%s-%s", str, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImmutableList<String> getEventPmetTypeList(String str, String str2, VastTrackingEventType vastTrackingEventType) {
        String string = vastTrackingEventType.toString();
        Locale locale = Locale.US;
        return ImmutableList.of("Metric", str, string, str2, String.format(locale, "%s-%s-%s", str, string, str2), String.format(locale, "%s-%s", str, str2));
    }

    @Nonnull
    static EnhancedURI getEnhancedUri(@Nonnull MediaFile mediaFile, @Nullable String str, @Nullable String str2, boolean z) {
        String str3;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !z) {
            str3 = null;
        } else {
            str3 = str2 + "-" + str;
        }
        return new EnhancedURI(mediaFile.getUri(), str3);
    }

    public String toString() {
        return "AdClip { Id: " + getAdId() + ", StartTime: " + getAdClipStartTime() + ", Duration: " + getDuration();
    }
}
