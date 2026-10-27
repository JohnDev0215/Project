package com.amazon.avod.content;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.content.downloading.ContentAccessorFactory;
import com.amazon.avod.content.event.ContentEventSessionStarting;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.FatalContentEventError;
import com.amazon.avod.content.event.ManifestRefreshEvent;
import com.amazon.avod.content.performance.PerformanceMonitor;
import com.amazon.avod.content.readytowatch.ReadyToWatch;
import com.amazon.avod.content.readytowatch.ReadyToWatchFactory;
import com.amazon.avod.content.smoothstream.PlaybackSessionProtocolFactory;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.StartAudioBitrateSelectionMode;
import com.amazon.avod.content.smoothstream.StreamSelections;
import com.amazon.avod.content.smoothstream.StreamSelector;
import com.amazon.avod.content.smoothstream.StreamingUtils;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.RefreshableManifest;
import com.amazon.avod.content.smoothstream.manifest.RefreshableManifestValidator;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerInterface;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.content.smoothstream.manifest.acquisition.PsshFragmentAcquirer;
import com.amazon.avod.content.smoothstream.manifest.resilience.MalformedManifestEventProxy;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamper;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.smoothstream.quality.VideoResolutionFilter;
import com.amazon.avod.content.smoothstream.storage.WriterScopedContentStore;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.content.urlvending.ContentUrlSelectorFactory;
import com.amazon.avod.content.urlvending.ContentUrlSwitchingPolicyFactory;
import com.amazon.avod.content.urlvending.QoeConfig;
import com.amazon.avod.content.urlvending.QoeEvaluator;
import com.amazon.avod.content.urlvending.QoeEvaluatorFactory;
import com.amazon.avod.content.urlvending.QoeEvaluatorInterface;
import com.amazon.avod.content.urlvending.QoeEventTranslator;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackPerformanceReport;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.throughput.BandwidthStats;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingContentSession implements ContentSession {
    private ContentAccessor mAccessor;
    private final ContentAccessorFactory mAccessorFactory;
    private final int mAppUid;
    private int mAudioStartFragmentIndex;
    private AudioVideoUrls mAudioVideoUrls;
    private Set<String> mAvailableAudioLanguages;
    private BandwidthStats mBandwidthStats;
    private QualityLevelClamper mClamper;
    private final QualityLevelClamperFactory mClamperFactory;
    private final SmoothStreamingPlaybackConfig mConfig;
    private final ContentManagementEventBus mContentEventDispatcher;
    private final String mContentSessionUUID;
    private final ContentUrlSelectorFactory mContentUrlSelectorFactory;
    private String mContentUrlSetId;
    private final ContentUrlSwitchingPolicyFactory mContentUrlSwitchingPolicyFactory;
    private ContentSessionContext mContext;
    private String mCurrentAudioLanguage;
    private final SmoothStreamingContentSessionEventTranslator mEventTranslator;
    private final Heuristics mHeuristics;
    private long mLastManifestRefreshTimeInMillis;
    private final PlaybackNativeLibrariesLoader mLibraryLoader;
    private final LifecycleProfiler mLifecycleProfiler;
    private final ScheduledExecutorService mLiveCacheExecutor;
    private final LiveStreamingPlaybackConfig mLiveStreamingPlaybackConfig;
    private final LiveWindowDuration mLiveWindowDuration;
    private final MalformedManifestEventProxy mMalformedManifestEventProxy;
    private RefreshableManifest mManifest;
    private final ManifestAcquirerInterface mManifestAcquirer;
    private final ManifestCapturerInterface mManifestCapturer;
    private final float mManifestDownloadFactor;
    private ScheduledFuture<?> mManifestRefreshFuture;
    private final int mMaxNumberOfCdnErrorAllowedForManifestRefresh;
    private final NetworkHistoryManager mNetworkHistoryManager;
    private final PerformanceMonitor mPerformanceMonitor;
    private PlayableContent mPlayableContent;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private long mPlayerTimelineMillis;
    private final MediaProfiler mProfiler;
    private PlaybackSessionProtocol mProtocol;
    private final PsshFragmentAcquirer mPsshAudioFragmentAcquirer;
    private final PsshFragmentAcquirer mPsshVideoFragmentAcquirer;
    private final QoeEvaluatorInterface mQoeEvaluator;
    private final QoeEventTranslator mQoeEventTranslator;
    private ReadyToWatch mReadyToWatch;
    private final ReadyToWatchFactory mReadyToWatchFactory;
    private RefreshableManifestValidator mRefresableManifestValidator;
    private final ScheduledExecutorService mRefreshManifestExecutorService;
    private final Runnable mRefreshManifestRunnable;
    private final RefreshableManifestConfig mRefreshableManifestConfig;
    private boolean mReportedManifestFormat;
    private final WriterScopedContentStore mScopedContentStore;
    private StreamSelections mSelectedStreams;
    private final PlaybackSessionProtocolFactory mSessionProtocolFactory;
    private ContentSessionType mSessionType;
    private final boolean mShouldRefreshManifestInCdnError;
    private StartAudioBitrateSelectionMode mStartAudioBitrateSelectionMode;
    private File mStoragePath;
    private StoredContentInfo mStoredContentInfo;
    private final StreamSelector mStreamSelector;
    private VideoSpecification mVideoSpecification;
    private int mVideoStartFragmentIndex;
    private volatile boolean mIsRestarting = false;
    private final Object mStartStopMutex = new Object();
    private final Stopwatch mManifestAcquiryStopwatch = Stopwatch.createUnstarted(Tickers.androidTicker());
    private ContentUrlSelector mContentUrlSelector = null;
    private boolean mIsCancelled = false;
    private volatile boolean mIsActive = false;
    private volatile boolean mIsShutdown = false;
    private DrmScheme mDrmScheme = null;
    private final ReentrantLock mRefreshTaskLock = new ReentrantLock();
    private PlayerBindState mPlayerBindState = PlayerBindState.UNATTACHED;
    private ContentException mLastFatalError = null;
    private final AtomicInteger mCdnErrorReceivedDuringManifestRefresh = new AtomicInteger();

    private enum ErrorType {
        FATAL_ERROR,
        RETRIABLE_ERROR
    }

    private static class StoredContentInfo {
        private final TimeSpan mLastAvailableTime;
        private final long mRemainingNeededSizeInBytes;
        private final long mTotalNeededSizeInBytes;

        public StoredContentInfo(long j, long j2, @Nonnull TimeSpan timeSpan) {
            this.mRemainingNeededSizeInBytes = j;
            this.mTotalNeededSizeInBytes = j2;
            this.mLastAvailableTime = timeSpan;
        }

        public long getRemainingNeededSizeInBytes() {
            return this.mRemainingNeededSizeInBytes;
        }

        public long getTotalNeededSizeInBytes() {
            return this.mTotalNeededSizeInBytes;
        }

        public TimeSpan getLastAvailableTime() {
            return this.mLastAvailableTime;
        }
    }

    public SmoothStreamingContentSession(NetworkHistoryManager networkHistoryManager, @Nonnull ContentManagementEventBus contentManagementEventBus, ContentAccessorFactory contentAccessorFactory, ManifestAcquirerInterface manifestAcquirerInterface, PsshFragmentAcquirer psshFragmentAcquirer, PsshFragmentAcquirer psshFragmentAcquirer2, PlaybackSessionProtocolFactory playbackSessionProtocolFactory, ReadyToWatchFactory readyToWatchFactory, StreamSelector streamSelector, MediaProfiler mediaProfiler, WriterScopedContentStore writerScopedContentStore, QualityLevelClamperFactory qualityLevelClamperFactory, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, ContentUrlSelectorFactory contentUrlSelectorFactory, ContentUrlSwitchingPolicyFactory contentUrlSwitchingPolicyFactory, PerformanceMonitor performanceMonitor, Heuristics heuristics, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, @Nonnull LifecycleProfiler lifecycleProfiler, @Nullable LiveWindowDuration liveWindowDuration, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface, int i, @Nonnull ScheduledExecutorService scheduledExecutorService2) {
        this.mNetworkHistoryManager = networkHistoryManager;
        this.mAccessorFactory = contentAccessorFactory;
        ContentManagementEventBus contentManagementEventBus2 = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "eventBus");
        this.mContentEventDispatcher = contentManagementEventBus2;
        this.mManifestAcquirer = manifestAcquirerInterface;
        this.mPsshAudioFragmentAcquirer = psshFragmentAcquirer;
        this.mPsshVideoFragmentAcquirer = psshFragmentAcquirer2;
        this.mSessionProtocolFactory = playbackSessionProtocolFactory;
        this.mReadyToWatchFactory = readyToWatchFactory;
        this.mStreamSelector = streamSelector;
        this.mProfiler = mediaProfiler;
        this.mScopedContentStore = writerScopedContentStore;
        this.mClamperFactory = qualityLevelClamperFactory;
        this.mLibraryLoader = playbackNativeLibrariesLoader;
        this.mEventTranslator = new SmoothStreamingContentSessionEventTranslator(contentManagementEventBus2);
        this.mContentUrlSelectorFactory = contentUrlSelectorFactory;
        this.mContentUrlSwitchingPolicyFactory = contentUrlSwitchingPolicyFactory;
        this.mPerformanceMonitor = performanceMonitor;
        this.mHeuristics = heuristics;
        this.mConfig = smoothStreamingPlaybackConfig;
        this.mLiveStreamingPlaybackConfig = liveStreamingPlaybackConfig;
        this.mLifecycleProfiler = (LifecycleProfiler) Preconditions.checkNotNull(lifecycleProfiler, "lifecycleProfiler");
        this.mLiveWindowDuration = liveWindowDuration;
        this.mRefreshManifestExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "refreshManifestExecutorService");
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
        this.mManifestCapturer = (ManifestCapturerInterface) Preconditions.checkNotNull(manifestCapturerInterface, "manifestCapturer");
        this.mAppUid = i;
        this.mRefreshableManifestConfig = RefreshableManifestConfig.getInstance();
        this.mContentSessionUUID = UUID.randomUUID().toString();
        this.mManifestDownloadFactor = RefreshableManifestValidator.RefreshableManifestValidatorConfig.getInstance().getManifestReDownloadFactor();
        this.mMalformedManifestEventProxy = new MalformedManifestEventProxy(contentManagementEventBus2, smoothStreamingPlaybackConfig);
        this.mShouldRefreshManifestInCdnError = liveStreamingPlaybackConfig.shouldRefreshManifestInCdnError();
        this.mMaxNumberOfCdnErrorAllowedForManifestRefresh = liveStreamingPlaybackConfig.getMaxNumberOfCdnErrorAllowedForManifestRefresh();
        this.mRefreshManifestRunnable = new Runnable() { // from class: com.amazon.avod.content.SmoothStreamingContentSession.1
            @Override // java.lang.Runnable
            public void run() throws ContentException {
                if (!SmoothStreamingContentSession.this.mIsShutdown) {
                    if (SmoothStreamingContentSession.this.mManifest != null) {
                        try {
                            SmoothStreamingContentSession.this.cleanRefreshTask();
                            SmoothStreamingContentSession.this.mManifest.refresh(SmoothStreamingContentSession.this.acquireManifest(true), SmoothStreamingContentSession.this.mRefresableManifestValidator, true, SmoothStreamingContentSession.this.mContentUrlSelector.getCurrentContentUrl());
                            DLog.devf("manifest refreshed");
                            SmoothStreamingContentSession.this.mCdnErrorReceivedDuringManifestRefresh.set(0);
                            SmoothStreamingContentSession.this.postManifestRefreshEvent();
                            return;
                        } catch (RefreshableManifestValidator.InvalidManifestRefreshOperationException e) {
                            SmoothStreamingContentSession.this.handleInvalidManifestRefreshOperationException(e);
                            return;
                        } catch (ContentException e2) {
                            DLog.warnf("manifest refresh exception %s", e2.getMessage());
                            if (SmoothStreamingContentSession.this.allowManifestRefreshForErrorCode(e2)) {
                                SmoothStreamingContentSession smoothStreamingContentSession = SmoothStreamingContentSession.this;
                                smoothStreamingContentSession.scheduleManifestRefreshIfNecessary(smoothStreamingContentSession.mManifest, true, SmoothStreamingContentSession.this.mManifest.getMinimumUpdatePeriod().getTotalMilliseconds());
                            }
                            if (ContentException.ContentError.LIVE_STALE_MANIFEST.equals(e2.getErrorCode())) {
                                DLog.logf("refresh manifest with last load latency %d", Long.valueOf((long) (SmoothStreamingContentSession.this.mManifest.getMinimumUpdatePeriod().getTotalMilliseconds() * SmoothStreamingContentSession.this.mManifestDownloadFactor)));
                                SmoothStreamingContentSession smoothStreamingContentSession2 = SmoothStreamingContentSession.this;
                                smoothStreamingContentSession2.scheduleManifestRefreshIfNecessary(smoothStreamingContentSession2.mManifest, true, (long) (SmoothStreamingContentSession.this.mManifest.getMinimumUpdatePeriod().getTotalMilliseconds() * SmoothStreamingContentSession.this.mManifestDownloadFactor));
                                return;
                            }
                            return;
                        }
                    }
                    DLog.warnf("Cannot refresh a 'null' manifest.");
                    SmoothStreamingContentSession smoothStreamingContentSession3 = SmoothStreamingContentSession.this;
                    smoothStreamingContentSession3.rescheduleManifestRefresh(smoothStreamingContentSession3.mRefreshableManifestConfig.getRetriableTaskDelayInMillis().longValue());
                    return;
                }
                DLog.warnf("Not scheduling manifest refresh as ContentSession has been shutdown");
            }
        };
        this.mLiveCacheExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService2, "liveCacheExecutor");
        QoeEvaluator qoeEvaluatorNewQoeEvaluator = QoeEvaluatorFactory.INSTANCE.newQoeEvaluator(heuristics == null ? QoeConfig.INSTANCE : heuristics.getQoeConfig());
        this.mQoeEvaluator = qoeEvaluatorNewQoeEvaluator;
        QoeEventTranslator qoeEventTranslator = new QoeEventTranslator(qoeEvaluatorNewQoeEvaluator);
        this.mQoeEventTranslator = qoeEventTranslator;
        qoeEventTranslator.registerForEvents(contentManagementEventBus2);
    }

    public void initialize(VideoSpecification videoSpecification, ContentSessionType contentSessionType, AudioVideoUrls audioVideoUrls, File file, @Nonnull DrmScheme drmScheme, @Nullable String str) {
        this.mVideoSpecification = videoSpecification;
        this.mStoragePath = file;
        this.mSessionType = contentSessionType;
        this.mAudioVideoUrls = audioVideoUrls;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
        this.mContentUrlSetId = str;
        this.mRefresableManifestValidator = shouldValidateManifest() ? new RefreshableManifestValidator(this.mPlaybackEventReporter, this.mContentEventDispatcher, this.mVideoSpecification) : null;
    }

    private boolean shouldValidateManifest() {
        return !this.mAudioVideoUrls.getContentUrls().isEmpty() && this.mVideoSpecification.isLiveStream();
    }

    @Override // com.amazon.avod.content.ContentSession
    public void setListener(ContentSessionEventListener contentSessionEventListener) {
        this.mEventTranslator.setListener(contentSessionEventListener);
    }

    @Override // com.amazon.avod.content.ContentSession
    public void begin() throws MediaException {
        MediaProfiler mediaProfiler;
        String str;
        synchronized (this.mStartStopMutex) {
            if (!this.mIsCancelled && !this.mIsActive) {
                this.mProfiler.start("ContentSession.begin");
                this.mProfiler.start("ContentSession.begin.loadLibraries");
                if (!this.mLibraryLoader.waitForInitialization()) {
                    throw new ContentException(ContentException.ContentError.ERROR_LOADING_NATIVE_LIBRARIES, "Failed to load the playback native libraries!");
                }
                this.mProfiler.stop("ContentSession.begin.loadLibraries");
                this.mEventTranslator.begin();
                this.mMalformedManifestEventProxy.begin();
                try {
                    try {
                        Preconditions.checkNotNull(this.mAudioVideoUrls, "cannot have a null AudioVideoUrls.");
                        ContentUrlSelector contentUrlSelectorNewContentUrlSelector = this.mContentUrlSelectorFactory.newContentUrlSelector(this.mAudioVideoUrls, this.mContentEventDispatcher, this.mConfig.isHttpsFallbackAllowed(), this.mVideoSpecification.isLiveStream(), this.mContentUrlSetId);
                        this.mContentUrlSelector = contentUrlSelectorNewContentUrlSelector;
                        contentUrlSelectorNewContentUrlSelector.setContentUrlSwitchingPolicy(this.mContentUrlSwitchingPolicyFactory.newContentUrlSwitchingPolicyForManifest());
                        DLog.logf("Starting ContentSession for %s using url %s, session type %s", this.mVideoSpecification, this.mContentUrlSelector.getCurrentContentUrl(), this.mSessionType);
                        this.mQoeEventTranslator.begin(this.mContentUrlSelector);
                        this.mQoeEvaluator.begin(this.mVideoSpecification, getMaxFrontBufferSizeMillis(this.mHeuristics), this.mNetworkHistoryManager, this.mQoeEventTranslator, this.mSessionType, this.mContentUrlSelector.getCurrentContentUrl());
                        this.mPlayableContent = PlayableContent.newPlayableContent(this.mVideoSpecification);
                        if (this.mConfig.shouldUseManifestContentStore()) {
                            this.mScopedContentStore.setIsWritable(true);
                            acquireManifestAndInitializeModels();
                            this.mScopedContentStore.begin(this.mPlayableContent, this.mSessionType, this.mContentEventDispatcher, this.mContext);
                        } else {
                            this.mScopedContentStore.begin(this.mPlayableContent, this.mSessionType, this.mContentEventDispatcher, this.mContext);
                            acquireManifestAndInitializeModels();
                        }
                        startDownloader();
                        if (this.mContext.getSessionType() == ContentSessionType.LIVE_CACHE) {
                            startLiveCacheTicker();
                        }
                        setProtectionHeader();
                        this.mContentEventDispatcher.postEvent(new ManifestRefreshEvent(this.mContext.getContent(), this.mContext.getSessionType(), TimeSpan.fromMilliseconds(this.mPlayerTimelineMillis)));
                        this.mIsActive = true;
                        mediaProfiler = this.mProfiler;
                        str = "ContentSession.begin";
                    } catch (ContentException e) {
                        handleContentException(e);
                        mediaProfiler = this.mProfiler;
                        str = "ContentSession.begin";
                    }
                    mediaProfiler.stop(str);
                } catch (Throwable th) {
                    this.mProfiler.stop("ContentSession.begin");
                    throw th;
                }
            }
        }
    }

    private long getMaxFrontBufferSizeMillis(Heuristics heuristics) {
        if (this.mVideoSpecification.isLiveStream()) {
            return TimeUnit.SECONDS.toMillis(this.mLiveStreamingPlaybackConfig.getLiveStreamingFutureBufferSizeSeconds(heuristics));
        }
        return TimeUnit.SECONDS.toMillis(this.mConfig.getStreamingFutureBufferSizeSeconds(heuristics));
    }

    private void startLiveCacheTicker() {
        final long totalMilliseconds = this.mConfig.getLiveCacheTickerInterval().getTotalMilliseconds();
        this.mLiveCacheExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.SmoothStreamingContentSession.2
            @Override // java.lang.Runnable
            public void run() {
                while (!SmoothStreamingContentSession.this.mContext.getState().isBoundToPlayer()) {
                    try {
                        SmoothStreamingContentSession.this.mAccessor.notifyLivePointUpdated(SmoothStreamingContentSession.this.mContext.getState().getMediaTimeWindowEndNanos());
                        Thread.sleep(totalMilliseconds);
                    } catch (InterruptedException e) {
                        DLog.warnf("LiveCache ticker interrupted: %s", e);
                        return;
                    }
                }
            }
        });
    }

    @Override // com.amazon.avod.content.ContentSession
    public void refreshManifest() throws MediaException {
        MediaProfiler mediaProfiler;
        String str;
        synchronized (this.mStartStopMutex) {
            if (!this.mIsCancelled) {
                try {
                    if (this.mIsActive) {
                        try {
                            try {
                                this.mProfiler.start("ContentSession.refreshManifest");
                                this.mProtocol.stop();
                                this.mAccessor.stop(true);
                                this.mContentUrlSelector.setContentUrlSwitchingPolicy(this.mContentUrlSwitchingPolicyFactory.newContentUrlSwitchingPolicyForManifest());
                                DLog.logf("Refreshing ContentSession for %s using url %s, session type %s", this.mVideoSpecification, this.mContentUrlSelector.getCurrentContentUrl(), this.mSessionType);
                                cancelFutureRefresh();
                                this.mManifest.refresh(acquireManifest(true), this.mRefresableManifestValidator, this.mContentUrlSelector.getCurrentContentUrl());
                                postManifestRefreshEvent();
                                initializeModels();
                                startDownloader();
                                setProtectionHeader();
                                mediaProfiler = this.mProfiler;
                                str = "ContentSession.refreshManifest";
                            } catch (ContentException e) {
                                handleContentException(e);
                                mediaProfiler = this.mProfiler;
                                str = "ContentSession.refreshManifest";
                            }
                        } catch (RefreshableManifestValidator.InvalidManifestRefreshOperationException e2) {
                            handleInvalidManifestRefreshOperationException(e2);
                            mediaProfiler = this.mProfiler;
                            str = "ContentSession.refreshManifest";
                        }
                        mediaProfiler.stop(str);
                    }
                } catch (Throwable th) {
                    this.mProfiler.stop("ContentSession.refreshManifest");
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00a2 A[Catch: all -> 0x0193, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x001e, B:8:0x0020, B:10:0x0024, B:12:0x002c, B:13:0x004b, B:15:0x0076, B:17:0x0082, B:19:0x0086, B:25:0x009c, B:27:0x00a2, B:29:0x00af, B:31:0x00b3, B:32:0x00be, B:36:0x00c7, B:38:0x00fc, B:39:0x0101, B:23:0x008e, B:40:0x0108, B:42:0x010c, B:43:0x0114, B:45:0x0118, B:46:0x011f, B:48:0x0127, B:50:0x012b, B:52:0x0146, B:53:0x0155, B:55:0x0159, B:56:0x015c, B:58:0x0164, B:60:0x016c, B:61:0x0171, B:62:0x0174, B:63:0x0191), top: B:69:0x0007, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b3 A[Catch: all -> 0x0193, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x001e, B:8:0x0020, B:10:0x0024, B:12:0x002c, B:13:0x004b, B:15:0x0076, B:17:0x0082, B:19:0x0086, B:25:0x009c, B:27:0x00a2, B:29:0x00af, B:31:0x00b3, B:32:0x00be, B:36:0x00c7, B:38:0x00fc, B:39:0x0101, B:23:0x008e, B:40:0x0108, B:42:0x010c, B:43:0x0114, B:45:0x0118, B:46:0x011f, B:48:0x0127, B:50:0x012b, B:52:0x0146, B:53:0x0155, B:55:0x0159, B:56:0x015c, B:58:0x0164, B:60:0x016c, B:61:0x0171, B:62:0x0174, B:63:0x0191), top: B:69:0x0007, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fc A[Catch: all -> 0x0193, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x001e, B:8:0x0020, B:10:0x0024, B:12:0x002c, B:13:0x004b, B:15:0x0076, B:17:0x0082, B:19:0x0086, B:25:0x009c, B:27:0x00a2, B:29:0x00af, B:31:0x00b3, B:32:0x00be, B:36:0x00c7, B:38:0x00fc, B:39:0x0101, B:23:0x008e, B:40:0x0108, B:42:0x010c, B:43:0x0114, B:45:0x0118, B:46:0x011f, B:48:0x0127, B:50:0x012b, B:52:0x0146, B:53:0x0155, B:55:0x0159, B:56:0x015c, B:58:0x0164, B:60:0x016c, B:61:0x0171, B:62:0x0174, B:63:0x0191), top: B:69:0x0007, inners: #2 }] */
    @Override // com.amazon.avod.content.ContentSession
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void end(boolean r20) throws com.amazon.avod.content.ContentException {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.SmoothStreamingContentSession.end(boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Manifest acquireManifest(boolean z) throws ContentException {
        this.mProfiler.start("ContentSession.begin.getManifest");
        Stopwatch stopwatchStart = Stopwatch.createUnstarted(Tickers.androidTicker()).start();
        String encodeId = this.mAudioVideoUrls.getEncodeId() != null ? this.mAudioVideoUrls.getEncodeId() : "";
        ManifestAcquirerInterface manifestAcquirerInterface = this.mManifestAcquirer;
        ContentUrlSelector contentUrlSelector = this.mContentUrlSelector;
        File file = this.mStoragePath;
        PlayableContent playableContent = this.mPlayableContent;
        ContentSessionContext contentSessionContext = this.mContext;
        Manifest manifestFromUrl = manifestAcquirerInterface.getManifestFromUrl(contentUrlSelector, encodeId, file, playableContent, contentSessionContext != null ? contentSessionContext.getEffectiveSessionType() : this.mSessionType, z, this.mDrmScheme, this.mLifecycleProfiler, this.mContentSessionUUID, this.mVideoSpecification.getContentType());
        this.mProfiler.stop("ContentSession.begin.getManifest");
        if (this.mVideoSpecification.isLiveStream()) {
            this.mManifestAcquiryStopwatch.reset().start();
        }
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long jElapsed = stopwatchStart.elapsed(timeUnit);
        this.mMalformedManifestEventProxy.setLastSuccessfulManifestDownloadTimeNanos(timeUnit.toNanos(jElapsed));
        scheduleManifestRefreshIfNecessary(manifestFromUrl, this.mVideoSpecification.isLiveStream(), jElapsed);
        return manifestFromUrl;
    }

    private void initializeModels() throws ContentException {
        Set<String> manifestAudioLanguages;
        this.mProfiler.start("ContentSession.begin.initializeModels");
        if (this.mConfig.allowListingLanguagesAcrossAudioFormats()) {
            manifestAudioLanguages = this.mStreamSelector.getManifestAudioLanguagesAcrossAudioFormats(this.mManifest);
        } else {
            manifestAudioLanguages = this.mStreamSelector.getManifestAudioLanguages(this.mManifest, this.mVideoSpecification.getAudioFormat());
        }
        this.mAvailableAudioLanguages = manifestAudioLanguages;
        StreamSelections streamSelectionsSelectStreams = selectStreams();
        this.mSelectedStreams = streamSelectionsSelectStreams;
        DLog.logf("Selected streams: %s", streamSelectionsSelectStreams);
        StreamIndex videoStream = this.mSelectedStreams.getVideoStream();
        StreamIndex audioStream = this.mSelectedStreams.getAudioStream();
        this.mCurrentAudioLanguage = audioStream.getLanguage();
        QALog qALogNewQALog = QALog.newQALog(PlaybackQAEvent.PLAYBACK_AUDIO_CODEC_CHANGED);
        PlaybackQAMetric playbackQAMetric = PlaybackQAMetric.FOURCC;
        qALogNewQALog.addMetric((QALog.QALoggableMetric) playbackQAMetric, this.mSelectedStreams.getStartAudioQualityLevel().getFourCC()).send();
        QALog.newQALog(PlaybackQAEvent.PLAYBACK_VIDEO_CODEC_CHANGED).addMetric((QALog.QALoggableMetric) playbackQAMetric, videoStream.getFourCC()).send();
        Heuristics heuristics = this.mHeuristics;
        if (heuristics == null || heuristics.getHeuristicsPlaybackConfig() == null) {
            this.mClamper = this.mClamperFactory.newServerConfigurableQualityLevelClamper(this.mManifest);
        } else {
            this.mClamper = this.mClamperFactory.newServerConfigurableQualityLevelClamper(this.mManifest, this.mHeuristics.getHeuristicsPlaybackConfig());
        }
        this.mQoeEvaluator.setClamper(this.mClamper);
        VideoResolutionFilter videoResolutionFilter = new VideoResolutionFilter(this.mClamper, videoStream);
        this.mBandwidthStats = BandwidthStats.newInstance(this.mAppUid);
        ContentSessionContext contentSessionContextCreate = ContentSessionContext.create(this.mPlayableContent, this.mVideoSpecification, this.mSessionType, this.mManifest, this.mAudioVideoUrls, this.mSelectedStreams, videoResolutionFilter.getResolutions(0), this.mConfig, this.mHeuristics, this.mManifestAcquiryStopwatch, this.mLiveStreamingPlaybackConfig, this.mStoragePath, this.mNetworkHistoryManager, this.mLiveWindowDuration, this.mBandwidthStats, this.mContentUrlSelector, this.mPlaybackEventReporter, this.mClamperFactory, this.mClamper, this.mPlayerBindState, this.mQoeEvaluator, this.mScopedContentStore.getContentStoreType(this.mSessionType));
        this.mContext = contentSessionContextCreate;
        long jResolveTimeToVideoStreamBoundary = StreamingUtils.resolveTimeToVideoStreamBoundary(contentSessionContextCreate.getState().resolveToMediaTimeWindowBoundary(this.mVideoSpecification.getStartTime().getTotalNanoSeconds(), this.mVideoSpecification.isLiveStream()), videoStream, audioStream);
        this.mVideoStartFragmentIndex = videoStream.getChunkIndexFromNanos(jResolveTimeToVideoStreamBoundary);
        this.mAudioStartFragmentIndex = audioStream.getChunkIndexFromNanos(jResolveTimeToVideoStreamBoundary);
        this.mContext.getState().setPlayPositionInNanos(videoStream.getChunkTimeInNanos(this.mVideoStartFragmentIndex));
        this.mContext.getState().updateConsumptionHead(videoStream.getIndex(), this.mVideoStartFragmentIndex);
        this.mContext.getState().updateConsumptionHead(audioStream.getIndex(), this.mAudioStartFragmentIndex);
        this.mContentUrlSelector.setContentUrlSwitchingPolicy(this.mContentUrlSwitchingPolicyFactory.newContentUrlSwitchingPolicyForFragment());
        if (!this.mIsActive) {
            this.mAccessor = this.mAccessorFactory.newContentAccessor(this.mContext, this.mContentUrlSelector);
        }
        this.mPerformanceMonitor.begin(this.mAccessor, this.mContext.getState(), videoStream);
        this.mReadyToWatch = this.mReadyToWatchFactory.newReadyToWatch(this.mContext, this.mAccessor);
        if (!this.mIsActive) {
            this.mProtocol = this.mSessionProtocolFactory.newPlaybackSessionProtocol(this.mContext, this.mAccessor, this.mReadyToWatch);
        } else {
            this.mProtocol.initialize(this.mContext, this.mAccessor, this.mReadyToWatch);
            this.mProtocol.start(new TimeSpan(this.mContext.getState().getMediaTimeWindowEndNanos()));
        }
        this.mProfiler.stop("ContentSession.begin.initializeModels");
        this.mContentEventDispatcher.postEvent(new ContentEventSessionStarting(this.mContext, this.mProtocol, this.mContentUrlSelector.getCurrentContentUrl()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleManifestRefreshIfNecessary(Manifest manifest, boolean z, long j) {
        long totalMilliseconds = manifest.getMinimumUpdatePeriod().getTotalMilliseconds();
        long minUpdatePeriodToEnableManifestRefreshThresholdMillis = this.mConfig.getMinUpdatePeriodToEnableManifestRefreshThresholdMillis();
        if (!z || totalMilliseconds <= 0 || totalMilliseconds >= minUpdatePeriodToEnableManifestRefreshThresholdMillis) {
            return;
        }
        scheduleManifestRefresh(Math.max(0L, totalMilliseconds - j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rescheduleManifestRefresh(long j) {
        cancelFutureRefresh();
        scheduleManifestRefresh(j);
    }

    private boolean scheduleManifestRefresh(long j) {
        if (this.mRefreshTaskLock.tryLock()) {
            try {
                try {
                    ScheduledFuture<?> scheduledFuture = this.mManifestRefreshFuture;
                    if (scheduledFuture == null || scheduledFuture.isDone() || this.mManifestRefreshFuture.getDelay(TimeUnit.MILLISECONDS) > j) {
                        ScheduledFuture<?> scheduledFuture2 = this.mManifestRefreshFuture;
                        if ((scheduledFuture2 == null || scheduledFuture2.isDone()) ? true : this.mManifestRefreshFuture.cancel(true)) {
                            DLog.logf("scheduling a new manifest refresh task, delayInMillis: %d", Long.valueOf(j));
                            this.mManifestRefreshFuture = this.mRefreshManifestExecutorService.schedule(this.mRefreshManifestRunnable, j, TimeUnit.MILLISECONDS);
                            return true;
                        }
                    }
                } catch (RejectedExecutionException e) {
                    DLog.warnf("scheduling a task on a halted executor, message: %s", e.getMessage());
                }
            } finally {
                this.mRefreshTaskLock.unlock();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanRefreshTask() {
        this.mRefreshTaskLock.lock();
        try {
            this.mManifestRefreshFuture = null;
        } finally {
            this.mRefreshTaskLock.unlock();
        }
    }

    private void cancelFutureRefresh() {
        if (this.mRefreshTaskLock.tryLock()) {
            try {
                if (this.mManifestRefreshFuture != null) {
                    DLog.devf("canceling manifest refresh task");
                    this.mManifestRefreshFuture.cancel(true);
                    this.mManifestRefreshFuture = null;
                }
            } finally {
                this.mRefreshTaskLock.unlock();
            }
        }
    }

    private void startDownloader() throws ContentException {
        this.mProfiler.start("ContentSession.begin.startDownloader");
        this.mAccessor.start(this.mContext);
        if (BandwidthStats.isSupported()) {
            this.mBandwidthStats.start();
        }
        this.mProfiler.stop("ContentSession.begin.startDownloader");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setProtectionHeader() throws com.amazon.avod.content.ContentException {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.SmoothStreamingContentSession.setProtectionHeader():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleInvalidManifestRefreshOperationException(RefreshableManifestValidator.InvalidManifestRefreshOperationException invalidManifestRefreshOperationException) throws ContentException {
        String str = String.format(Locale.US, "invalid manifest refresh exception, message: %s, errorType: %s", invalidManifestRefreshOperationException.getMessage(), invalidManifestRefreshOperationException.getErrorType());
        DLog.warnf(str);
        this.mManifestCapturer.uploadFromException(invalidManifestRefreshOperationException);
        this.mContentEventDispatcher.postEvent(new AloysiusDiagnosticEvent("UploadedManifestRefreshExceptionToS3", str, AloysiusDiagnosticsState.Discrete));
        if (invalidManifestRefreshOperationException.isFatal() && !this.mRefreshableManifestConfig.suppressManifestValidationFatalErrors()) {
            postFatalError(invalidManifestRefreshOperationException);
            end(true);
        } else {
            rescheduleManifestRefresh(this.mRefreshableManifestConfig.getRetriableTaskDelayInMillis().longValue());
        }
    }

    private void handleContentException(ContentException contentException) throws ContentException {
        if (handleContentExceptionErrorCodes(contentException) != ErrorType.FATAL_ERROR) {
            return;
        }
        this.mLastFatalError = contentException;
        postFatalError(contentException);
        end(true);
        throw contentException;
    }

    private ErrorType handleContentExceptionErrorCodes(@Nonnull ContentException contentException) {
        Preconditions.checkNotNull(contentException, "exception");
        this.mManifestCapturer.uploadFromException(contentException);
        this.mContentEventDispatcher.postEvent(new AloysiusDiagnosticEvent("UploadedManifestRefreshExceptionToS3", "ContentException with error code: " + contentException.getErrorCode(), AloysiusDiagnosticsState.Discrete));
        if (contentException.getErrorCode().equals(ContentException.ContentError.RETRY_STOP)) {
            DLog.warnf("Swallowing content exception as a stop in retries indicates origin failover trigger");
            this.mIsRestarting = true;
            return ErrorType.RETRIABLE_ERROR;
        }
        return ErrorType.FATAL_ERROR;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean allowManifestRefreshForErrorCode(@Nonnull ContentException contentException) {
        Preconditions.checkNotNull(contentException, "exception");
        ContentException.ContentError errorCode = contentException.getErrorCode();
        this.mManifestCapturer.uploadFromException(contentException);
        this.mContentEventDispatcher.postEvent(new AloysiusDiagnosticEvent("UploadedManifestRefreshExceptionToS3", "ContentException with error code: " + errorCode, AloysiusDiagnosticsState.Discrete));
        if (!errorCode.equals(ContentException.ContentError.RETRY_STOP)) {
            return this.mShouldRefreshManifestInCdnError && errorCode.equals(ContentException.ContentError.CDN_ERROR) && this.mCdnErrorReceivedDuringManifestRefresh.getAndIncrement() < this.mMaxNumberOfCdnErrorAllowedForManifestRefresh;
        }
        this.mIsRestarting = true;
        return true;
    }

    private void postFatalError(ContentException contentException) {
        ContentManagementEventBus contentManagementEventBus = this.mContentEventDispatcher;
        PlayableContent playableContent = this.mPlayableContent;
        ContentSessionType contentSessionType = this.mSessionType;
        String consumptionId = getConsumptionId();
        ContentSessionContext contentSessionContext = this.mContext;
        contentManagementEventBus.postEvent(new FatalContentEventError(playableContent, contentSessionType, contentException, consumptionId, contentSessionContext != null ? contentSessionContext.getEffectiveSessionType() : this.mSessionType));
    }

    private void reportManifestWarning(boolean z, boolean z2) {
        if (z && z2) {
            return;
        }
        if (z || z2) {
            String str = String.format(Locale.US, "Warning! Video Spec isLive = %s, Manifest isDynamic = %s. This may not be a fatal", Boolean.valueOf(z), Boolean.valueOf(z2));
            DLog.errorf(str);
            this.mPlaybackEventReporter.reportError("manifest_error", str, "");
            this.mContentEventDispatcher.postEvent(new AloysiusDiagnosticEvent("Manifest-VideoSpec-Discrepancy", str, AloysiusDiagnosticsState.Discrete));
        }
    }

    private void reportStitchedManifest(boolean z) {
        this.mPlaybackEventReporter.reportMetric(QOSEventName.PlaybackSession.toString(), "StreamMetaData", null, String.format(Locale.US, "Has stitched ads: %b", Boolean.valueOf(z)), null);
    }

    private void reportManifestFormat(Boolean bool) {
        if (this.mRefreshableManifestConfig.isManifestFormatReportingEnabled()) {
            if (this.mRefreshableManifestConfig.isManifestFormatReportingEnabledForEachManifestRefresh() || !this.mReportedManifestFormat) {
                String str = String.format(Locale.US, "isPatternTemplateManifest: %s", bool);
                this.mPlaybackEventReporter.reportMetric(QOSEventName.PlaybackSession.toString(), "PatternTemplateManifest", null, str, null);
                this.mContentEventDispatcher.postEvent(new AloysiusDiagnosticEvent("PatternTemplateManifest", str, AloysiusDiagnosticsState.Discrete));
                this.mReportedManifestFormat = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postManifestRefreshEvent() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.mPlayerTimelineMillis + (jCurrentTimeMillis - this.mLastManifestRefreshTimeInMillis);
        this.mPlayerTimelineMillis = j;
        this.mContentEventDispatcher.postEvent(new ManifestRefreshEvent(this.mPlayableContent, this.mSessionType, TimeSpan.fromMilliseconds(j)));
        this.mLastManifestRefreshTimeInMillis = jCurrentTimeMillis;
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean isActive() {
        return this.mIsActive;
    }

    @Override // com.amazon.avod.content.ContentSession
    public ContentManagementEventBus getEventDispatcher() {
        return this.mContentEventDispatcher;
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean isEncryptedContent() {
        Preconditions.checkState(this.mContext != null, "Must call begin() before isEncryptedContent()!");
        return this.mContext.getManifest().isEncrypted();
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public byte[] getVideoDefaultKeyId() {
        Preconditions.checkState(this.mSelectedStreams != null, "Must call begin() before getVideoDefaultKeyId()!");
        return this.mSelectedStreams.getVideoStream().getDefaultKeyId();
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public byte[] getAudioDefaultKeyId() {
        Preconditions.checkState(this.mSelectedStreams != null, "Must call begin() before getAudioDefaultKeyId()!");
        return this.mSelectedStreams.getAudioStream().getDefaultKeyId();
    }

    @Override // com.amazon.avod.content.ContentSession
    public String getEncodedEncryptionHeader() throws ContentException {
        Preconditions.checkState(this.mContext != null, "Must call begin() before getEncodedEncryptionHeader()!");
        ProtectionHeader videoProtectionHeader = this.mContext.getState().getVideoProtectionHeader();
        if (videoProtectionHeader == null) {
            return null;
        }
        return videoProtectionHeader.getBase64EncodedData();
    }

    @Override // com.amazon.avod.content.ContentSession
    public ContentSessionContext getContext() {
        Preconditions.checkState(this.mContext != null, "Must call begin() before getContext()!");
        return this.mContext;
    }

    @Override // com.amazon.avod.content.ContentSession
    public ReadyToWatch getReadyToWatch() {
        Preconditions.checkState(this.mReadyToWatch != null, "Must call begin() before getReadyToWatch()!");
        return this.mReadyToWatch;
    }

    @Override // com.amazon.avod.content.ContentSession
    public PlaybackSessionProtocol getProtocol() {
        Preconditions.checkState(this.mProtocol != null, "Must call begin() before getProtocol()!");
        return this.mProtocol;
    }

    @Override // com.amazon.avod.content.ContentSession
    public void setVideoQualityOverride(@Nullable QualityLevel qualityLevel) {
        Preconditions.checkState(this.mContext != null, "Must call begin() before setVideoQualityOverride()!");
        this.mContext.getState().setVideoQualityOverride(qualityLevel);
    }

    @Override // com.amazon.avod.content.ContentSession
    public void setPlaybackPerformanceReport(PlaybackPerformanceReport playbackPerformanceReport) {
        Preconditions.checkState(this.mContext != null, "Must call begin() before setPlaybackPerformanceReport()!");
        this.mPerformanceMonitor.setPlaybackPerformanceReport(playbackPerformanceReport);
    }

    @Override // com.amazon.avod.content.ContentSession
    public void setPlaybackRestrictedToBufferedContent(boolean z) {
        Preconditions.checkState((this.mContext == null || this.mAccessor == null) ? false : true, "Must call begin() before setPlaybackRestrictedToBufferedContent()!");
        this.mContext.getState().setRestrictPlaybackToBufferedContent(z);
        this.mAccessor.onContentContextChanged();
    }

    @Override // com.amazon.avod.content.ContentSession
    public long getDownloadedTimeAfterPositionInNanos(long j) {
        Preconditions.checkState(this.mAccessor != null, "Must call begin() before getDownloadedTimeAfterPosition()!");
        return this.mAccessor.getDownloadedTimeAfterPositionInNanos(j);
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean isFullyDownloadedFromNanos(long j) {
        Preconditions.checkState(this.mAccessor != null, "Must call begin() before isFullyDownloadedFrom()!");
        return this.mAccessor.isFullyDownloadedFromNanos(j);
    }

    @Override // com.amazon.avod.content.ContentSession
    public long getRemainingNeededSizeInBytes() {
        Preconditions.checkState(this.mAccessor != null, "Must call begin() before getRemainingNeededSizeInBytes()!");
        StoredContentInfo storedContentInfo = this.mStoredContentInfo;
        if (storedContentInfo != null) {
            return storedContentInfo.getRemainingNeededSizeInBytes();
        }
        return this.mAccessor.getRemainingNeededSizeInBytes();
    }

    @Override // com.amazon.avod.content.ContentSession
    public long getTotalNeededSizeInBytes() {
        Preconditions.checkState(this.mAccessor != null, "Must call begin() before getTotalNeededSizeInBytes()!");
        StoredContentInfo storedContentInfo = this.mStoredContentInfo;
        if (storedContentInfo != null) {
            return storedContentInfo.getTotalNeededSizeInBytes();
        }
        return this.mAccessor.getTotalNeededSizeInBytes();
    }

    @Override // com.amazon.avod.content.ContentSession
    public TimeSpan getLastAvailableTime() {
        Preconditions.checkState(this.mAccessor != null, "Must call begin() before getLastAvailableTime()!");
        StoredContentInfo storedContentInfo = this.mStoredContentInfo;
        if (storedContentInfo != null) {
            return storedContentInfo.getLastAvailableTime();
        }
        return new TimeSpan(this.mAccessor.getLastAvailableTimeInNanos());
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nonnull
    public Set<String> getAvailableAudioLanguages() {
        Preconditions.checkState(this.mAccessor != null, "Must call begin() before getAvailableAudioLanguages()!");
        return this.mAvailableAudioLanguages;
    }

    @Override // com.amazon.avod.content.ContentSession
    public long getTimeWindowStartMillis() throws ContentException {
        Preconditions.checkState(this.mAccessor != null, "Must call begin() before getTimeWindowStartMillis()!");
        return this.mContext.getState().getEpochUTCTimeWindowStartMillis();
    }

    @Override // com.amazon.avod.content.ContentSession
    public long getTimeWindowEndMillis() throws ContentException {
        Preconditions.checkState((this.mVideoSpecification == null || this.mManifest == null || this.mManifestAcquiryStopwatch == null || this.mContext == null) ? false : true, "Must call begin() before getTimeWindowEndMillis()!");
        return this.mContext.getState().getEpochUTCTimeWindowEndMillis();
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public String getCurrentAudioLanguage() {
        Preconditions.checkState(this.mAccessor != null, "Must call begin() before getCurrentAudioLanguage()!");
        return this.mCurrentAudioLanguage;
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nonnull
    public ContentUrlSelector getContentUrlSelector() {
        Preconditions.checkState(this.mContentUrlSelector != null, "Must call begin() before getContentUrlSelector()!");
        return this.mContentUrlSelector;
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean shouldSwitchAudioAdaptationSet() {
        Preconditions.checkState(this.mSelectedStreams != null, "Must call begin() before shouldDownshiftAudioAdaptationSet()!");
        if (!ContentSessionType.isStreamingSession(this.mSessionType) || this.mVideoSpecification.isLiveStream() || !ContentUrl.isDashUrl(this.mAudioVideoUrls.getContentUrls().get(0)) || !this.mConfig.isAudioAdaptationSetSwitchingEnabled() || this.mStartAudioBitrateSelectionMode != StartAudioBitrateSelectionMode.NETWORK_HISTORY_BASED) {
            return false;
        }
        try {
            return selectStreams().getAudioStream().getSortedQualityLevels(0)[0].getBitrate() != this.mSelectedStreams.getAudioStream().getSortedQualityLevels(0)[0].getBitrate();
        } catch (ContentException e) {
            DLog.exceptionf(e, "StreamSelector threw exception while detecting audio downshift, proceeding with playback", new Object[0]);
            return false;
        }
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean verifyAudioCachedContent(long j) throws ContentException {
        Preconditions.checkState(this.mIsActive, "Must call begin() before verifying cached content");
        StreamIndex audioStream = this.mSelectedStreams.getAudioStream();
        return this.mScopedContentStore.isFragmentAvailable(this.mContext, new SmoothStreamingURI(audioStream, this.mSelectedStreams.getStartAudioQualityLevel(), audioStream.getChunkIndexFromNanos(StreamingUtils.resolveTimeToVideoStreamBoundary(this.mContext.getState().resolveToMediaTimeWindowBoundary(j, this.mVideoSpecification.isLiveStream()), this.mSelectedStreams.getVideoStream(), audioStream))));
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nonnull
    public ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList() {
        Preconditions.checkNotNull(this.mAudioVideoUrls, "AudioVideoUrls is null inside ContentSession");
        return this.mAudioVideoUrls.getAudioTrackMetadataList();
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean isRestarting() {
        return this.mIsRestarting;
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public String getAuditPingUrl(@Nonnull String str) {
        Preconditions.checkNotNull(str, "adAgencyName");
        AudioVideoUrls audioVideoUrls = this.mAudioVideoUrls;
        if (audioVideoUrls != null) {
            return audioVideoUrls.getAuditPingUrl(str);
        }
        return null;
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public AudioVideoUrls getAudioVideoUrls() {
        return this.mAudioVideoUrls;
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public String getConsumptionId() {
        return getContentUrlSelector().getCurrentContentUrl().getSessionId();
    }

    @Override // com.amazon.avod.content.ContentSession
    public void notifyPlayerBindStateChange(@Nonnull PlayerBindState playerBindState) {
        this.mPlayerBindState = (PlayerBindState) Preconditions.checkNotNull(playerBindState, "playerBindState");
        this.mContext.notifyPlayerBindStateChanged(playerBindState);
        ContentSessionType contentSessionType = this.mSessionType;
        if ((contentSessionType == ContentSessionType.LIVE_CACHE || contentSessionType == ContentSessionType.STREAMING_CONTINUOUS) && playerBindState == PlayerBindState.ATTACHED_LOADED) {
            this.mQoeEvaluator.begin(this.mVideoSpecification, getMaxFrontBufferSizeMillis(this.mHeuristics), this.mNetworkHistoryManager, this.mQoeEventTranslator, ContentSessionType.STREAMING, this.mContentUrlSelector.getCurrentContentUrl());
            QualityLevelClamper qualityLevelClamper = this.mClamper;
            if (qualityLevelClamper != null) {
                this.mQoeEvaluator.setClamper(qualityLevelClamper);
            }
        }
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public ContentException getLastFatalError() {
        return this.mLastFatalError;
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nonnull
    public ManifestCapturerInterface getManifestCapturer() {
        return this.mManifestCapturer;
    }

    private StreamSelections selectStreams() throws ContentException {
        MediaQuality mediaQuality;
        Heuristics heuristics = this.mHeuristics;
        HeuristicsPlaybackConfig heuristicsPlaybackConfig = heuristics != null ? heuristics.getHeuristicsPlaybackConfig() : null;
        if (heuristicsPlaybackConfig != null && heuristicsPlaybackConfig.isNetworkHistoryBasedAudioAdaptationSetSelectionEnabled()) {
            this.mStartAudioBitrateSelectionMode = StartAudioBitrateSelectionMode.NETWORK_HISTORY_BASED;
        } else {
            this.mStartAudioBitrateSelectionMode = this.mConfig.getStartAudioBitrateSelectionMode();
        }
        boolean zIsDashUrl = ContentUrl.isDashUrl(this.mAudioVideoUrls.getContentUrls().get(0));
        ContentSessionContext contentSessionContext = this.mContext;
        if (contentSessionContext != null) {
            mediaQuality = contentSessionContext.getState().getMediaQuality();
        } else if (this.mVideoSpecification.getAudioFormat() == AudioFormat.AC_3_7_1) {
            mediaQuality = MediaQuality.HIGH;
        } else {
            mediaQuality = this.mVideoSpecification.getMediaQuality();
        }
        return this.mStreamSelector.selectStreams(zIsDashUrl, this.mManifest, this.mSessionType, this.mVideoSpecification.getAudioFormat(), this.mVideoSpecification.getAudioLanguage(), this.mVideoSpecification.getAudioTrackIds(), this.mVideoSpecification.getPreferredAudioTrackIds(), this.mVideoSpecification.getPrimaryAudioTrackId(), this.mNetworkHistoryManager, this.mHeuristics, this.mStartAudioBitrateSelectionMode, this.mStoragePath, this.mAudioVideoUrls.getEncodeId(), this.mPlayableContent, mediaQuality);
    }

    private void acquireManifestAndInitializeModels() throws ContentException {
        this.mManifest = RefreshableManifest.newManifest(acquireManifest(this.mVideoSpecification.isLiveStream()), this.mContentEventDispatcher);
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.mLastManifestRefreshTimeInMillis = jCurrentTimeMillis;
        this.mPlayerTimelineMillis = jCurrentTimeMillis - this.mManifest.getAvailabilityStartTimeMillis();
        reportStitchedManifest(this.mManifest.hasStitchedAds());
        reportManifestWarning(this.mVideoSpecification.isLiveStream(), this.mManifest.isDynamic());
        reportManifestFormat(this.mManifest.isPatternTemplateManifest());
        initializeModels();
    }

    public static class RefreshableManifestConfig extends MediaConfigBase {
        private final ConfigurationValue<Boolean> mEnableManifestFormatReporting;
        private final ConfigurationValue<Boolean> mEnableManifestFormatReportingForEachManifestRefresh;
        private final ConfigurationValue<Long> mRetriableTaskDelayInMillis;
        private final ConfigurationValue<Boolean> mSuppressManifestValidationFatalErrors;

        private static final class InstanceHolder {
            private static final RefreshableManifestConfig INSTANCE = new RefreshableManifestConfig();

            private InstanceHolder() {
            }
        }

        public static RefreshableManifestConfig getInstance() {
            return InstanceHolder.INSTANCE;
        }

        private RefreshableManifestConfig() {
            this.mRetriableTaskDelayInMillis = newLongConfigValue("playback.refreshablemanifest.retryTaskDelayInMillis", 1000L);
            this.mSuppressManifestValidationFatalErrors = newBooleanConfigValue("playback.refreshablemanifest.suppressManifestValidationFatalErrors", false);
            this.mEnableManifestFormatReporting = newBooleanConfigValue("playback_shouldLimitManifestFormatReporting", true);
            this.mEnableManifestFormatReportingForEachManifestRefresh = newBooleanConfigValue("playback_enableManifestFormatReportingForEachManifestRefresh", false);
        }

        public boolean isManifestFormatReportingEnabledForEachManifestRefresh() {
            return this.mEnableManifestFormatReportingForEachManifestRefresh.getValue().booleanValue();
        }

        public boolean isManifestFormatReportingEnabled() {
            return this.mEnableManifestFormatReporting.getValue().booleanValue();
        }

        public Long getRetriableTaskDelayInMillis() {
            return this.mRetriableTaskDelayInMillis.getValue();
        }

        public boolean suppressManifestValidationFatalErrors() {
            return this.mSuppressManifestValidationFatalErrors.getValue().booleanValue();
        }
    }
}
