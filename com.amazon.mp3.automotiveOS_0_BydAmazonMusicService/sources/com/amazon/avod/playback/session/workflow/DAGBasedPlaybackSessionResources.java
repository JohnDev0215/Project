package com.amazon.avod.playback.session.workflow;

import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.LiveWindowDuration;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.drm.DrmLicenseAcquirer;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.pmet.PmetAcquisitionEventListener;
import com.amazon.avod.media.playback.reporting.PlayerHealthReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.service.AVODContentUrlPolicyManager;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.session.ConsumptionIdProvider;
import com.amazon.avod.playback.session.PlaybackSessionFailureManager;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.smoothstream.SmoothStreamingBitrateChangeAdapter;
import com.amazon.avod.playback.smoothstream.SmoothStreamingContentEventAdapter;
import com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector;
import com.amazon.avod.playback.smoothstream.fsm.VideoPlaybackEngineFactory;
import com.amazon.avod.playback.subtitles.SubtitlesEngine;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.amazon.avod.userdownload.PVDownloadManagerPlayerShim;
import com.amazon.avod.userdownload.PVDownloadsPlayerShim;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class DAGBasedPlaybackSessionResources implements PlaybackSessionResources {
    private final AdEventTransport mAdEventTransport;
    private final AdPlanFactory mAdPlanFactory;
    private final AdUriProxy mAdUriProxy;
    private final SmoothStreamingBitrateChangeAdapter mBitrateChangeAdapter;
    private final VideoCacheManager mCacheManager;
    private final SmoothStreamingContentEventAdapter mContentEventAdapter;
    private final ContentManagementEventBus mContentManagementEventBus;
    private final ContentSessionFactory mContentSessionFactory;
    private final AVODContentUrlPolicyManager mContentUrlPolicyManager;
    private final DiagnosticDataCollector mDataCollector;
    private final DownloadService mDownloadService;
    private final BaseDrmSystem mDrmSystem;
    private final SmoothStreamingVideoPresentationEventReporter mEventReporter;
    private final ExecutorService mExecutor;
    private final IdentityShim mIdentity;
    private final DrmLicenseAcquirer mLicenseAcquirer;
    private final LifecycleProfiler mLifecycleProfiler;
    private final LiveWindowDuration mLiveWindowDuration;
    private final PlaybackConfig mPlaybackConfig;
    private final PlaybackSupportEvaluator mPlaybackEvaluator;
    private final PlaybackEventTransport mPlaybackEventTransport;
    private final PlaybackMediaEventReporters mPlaybackMediaEventReporters;
    private final PlaybackMediaEventReporters mPlaybackMediaEventReportersAd;
    private final PlaybackMediaEventReporters.Factory mPlaybackMediaEventReportersFactory;
    private final PlaybackMediaEventReporters mPlaybackMediaEventReportersMain;
    private final PlaybackSessionFailureManager mPlaybackSessionFailureManager;
    private final PlayerHealthReporter mPlayerHealthReporter;
    private final PmetAcquisitionEventListener mPmetAcquisitionEventListener;
    private final MediaProfiler mProfiler;
    private final RendererSchemeController mRendererSchemeController;
    private final SubtitlesEngine mSubtitlesEngine;
    private final TimelineMonitor mTimelineMonitor;
    private final PVDownloadManagerPlayerShim mUserDownloadManager;
    private final VideoPlaybackEngineFactory mVideoPlaybackEngineFactory;
    private final VideoPlayerLifecyleEventHandler mVideoPlayerLifecyleEventHandler;

    public DAGBasedPlaybackSessionResources(@Nonnull ExecutorService executorService, @Nonnull ContentSessionFactory contentSessionFactory, @Nonnull MediaProfiler mediaProfiler, @Nonnull VideoPlaybackEngineFactory videoPlaybackEngineFactory, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull DiagnosticDataCollector diagnosticDataCollector, @Nonnull SmoothStreamingBitrateChangeAdapter smoothStreamingBitrateChangeAdapter, @Nonnull SmoothStreamingContentEventAdapter smoothStreamingContentEventAdapter, @Nonnull AVODContentUrlPolicyManager aVODContentUrlPolicyManager, @Nonnull VideoCacheManager videoCacheManager, @Nonnull DownloadService downloadService, @Nonnull SmoothStreamingVideoPresentationEventReporter smoothStreamingVideoPresentationEventReporter, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull BaseDrmSystem baseDrmSystem, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull SubtitlesEngine subtitlesEngine, @Nonnull LifecycleProfiler lifecycleProfiler, @Nonnull LiveWindowDuration liveWindowDuration, @Nonnull AdUriProxy adUriProxy, @Nonnull AdPlanFactory adPlanFactory, @Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nonnull TimelineMonitor timelineMonitor, @Nonnull PlaybackMediaEventReporters.Factory factory, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull AdEventTransport adEventTransport, @Nonnull PlaybackSessionFailureManager playbackSessionFailureManager, @Nonnull ConsumptionIdProvider consumptionIdProvider) {
        this(executorService, contentSessionFactory, mediaProfiler, videoPlaybackEngineFactory, playbackEventTransport, diagnosticDataCollector, smoothStreamingBitrateChangeAdapter, smoothStreamingContentEventAdapter, aVODContentUrlPolicyManager, videoCacheManager, downloadService, smoothStreamingVideoPresentationEventReporter, playbackSupportEvaluator, baseDrmSystem, new DrmLicenseAcquirer(baseDrmSystem), PVDownloadsPlayerShim.getInstance().getPlayerDownloadManagerProxy(), MediaSystemSharedDependencies.getInstance().getIdentityShim(), PlaybackConfig.INSTANCE, rendererSchemeController, subtitlesEngine, lifecycleProfiler, liveWindowDuration, adUriProxy, adPlanFactory, videoPlayerLifecyleEventHandler, timelineMonitor, factory, contentManagementEventBus, adEventTransport, playbackSessionFailureManager, factory.create(contentManagementEventBus, playbackEventTransport, null, consumptionIdProvider, true), factory.create(null, null, adEventTransport, consumptionIdProvider, false), factory.create(contentManagementEventBus, playbackEventTransport, adEventTransport, consumptionIdProvider), new PmetAcquisitionEventListener(contentManagementEventBus));
    }

    DAGBasedPlaybackSessionResources(@Nonnull ExecutorService executorService, @Nonnull ContentSessionFactory contentSessionFactory, @Nonnull MediaProfiler mediaProfiler, @Nonnull VideoPlaybackEngineFactory videoPlaybackEngineFactory, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull DiagnosticDataCollector diagnosticDataCollector, @Nonnull SmoothStreamingBitrateChangeAdapter smoothStreamingBitrateChangeAdapter, @Nonnull SmoothStreamingContentEventAdapter smoothStreamingContentEventAdapter, @Nonnull AVODContentUrlPolicyManager aVODContentUrlPolicyManager, @Nonnull VideoCacheManager videoCacheManager, @Nonnull DownloadService downloadService, @Nonnull SmoothStreamingVideoPresentationEventReporter smoothStreamingVideoPresentationEventReporter, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull BaseDrmSystem baseDrmSystem, @Nonnull DrmLicenseAcquirer drmLicenseAcquirer, @Nonnull PVDownloadManagerPlayerShim pVDownloadManagerPlayerShim, @Nonnull IdentityShim identityShim, @Nonnull PlaybackConfig playbackConfig, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull SubtitlesEngine subtitlesEngine, @Nonnull LifecycleProfiler lifecycleProfiler, @Nonnull LiveWindowDuration liveWindowDuration, @Nonnull AdUriProxy adUriProxy, @Nonnull AdPlanFactory adPlanFactory, @Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nonnull TimelineMonitor timelineMonitor, @Nonnull PlaybackMediaEventReporters.Factory factory, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull AdEventTransport adEventTransport, @Nonnull PlaybackSessionFailureManager playbackSessionFailureManager, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters2, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters3, @Nonnull PmetAcquisitionEventListener pmetAcquisitionEventListener) {
        this.mVideoPlaybackEngineFactory = (VideoPlaybackEngineFactory) Preconditions.checkNotNull(videoPlaybackEngineFactory, "videoPlaybackEngineFactory");
        this.mContentSessionFactory = (ContentSessionFactory) Preconditions.checkNotNull(contentSessionFactory, "contentSessionFactory");
        this.mBitrateChangeAdapter = (SmoothStreamingBitrateChangeAdapter) Preconditions.checkNotNull(smoothStreamingBitrateChangeAdapter, "bitrateChangeAdapter");
        this.mContentEventAdapter = (SmoothStreamingContentEventAdapter) Preconditions.checkNotNull(smoothStreamingContentEventAdapter, "contentEventAdapter");
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "eventTransport");
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "executor");
        this.mDataCollector = (DiagnosticDataCollector) Preconditions.checkNotNull(diagnosticDataCollector, "dataCollector");
        this.mContentUrlPolicyManager = (AVODContentUrlPolicyManager) Preconditions.checkNotNull(aVODContentUrlPolicyManager, "contentUrlPolicyManager");
        this.mProfiler = (MediaProfiler) Preconditions.checkNotNull(mediaProfiler, "profiler");
        this.mCacheManager = (VideoCacheManager) Preconditions.checkNotNull(videoCacheManager, "cacheManager");
        this.mDownloadService = (DownloadService) Preconditions.checkNotNull(downloadService, "downloadService");
        SmoothStreamingVideoPresentationEventReporter smoothStreamingVideoPresentationEventReporter2 = (SmoothStreamingVideoPresentationEventReporter) Preconditions.checkNotNull(smoothStreamingVideoPresentationEventReporter, "eventReporter");
        this.mEventReporter = smoothStreamingVideoPresentationEventReporter2;
        this.mPlaybackEvaluator = (PlaybackSupportEvaluator) Preconditions.checkNotNull(playbackSupportEvaluator, "playbackEvaluator");
        this.mLicenseAcquirer = (DrmLicenseAcquirer) Preconditions.checkNotNull(drmLicenseAcquirer, "licenseAcquirer");
        this.mUserDownloadManager = (PVDownloadManagerPlayerShim) Preconditions.checkNotNull(pVDownloadManagerPlayerShim, "userDownloadManager");
        this.mIdentity = (IdentityShim) Preconditions.checkNotNull(identityShim, "identity");
        this.mPlaybackConfig = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        this.mSubtitlesEngine = (SubtitlesEngine) Preconditions.checkNotNull(subtitlesEngine, "subtitlesEngine");
        this.mLifecycleProfiler = (LifecycleProfiler) Preconditions.checkNotNull(lifecycleProfiler, "lifecycleProfiler");
        this.mLiveWindowDuration = (LiveWindowDuration) Preconditions.checkNotNull(liveWindowDuration, "liveWindowDuration");
        this.mDrmSystem = (BaseDrmSystem) Preconditions.checkNotNull(baseDrmSystem, "drmSystem");
        this.mAdUriProxy = (AdUriProxy) Preconditions.checkNotNull(adUriProxy, "adUriProxy");
        this.mAdPlanFactory = (AdPlanFactory) Preconditions.checkNotNull(adPlanFactory, "adPlanFactory");
        this.mVideoPlayerLifecyleEventHandler = (VideoPlayerLifecyleEventHandler) Preconditions.checkNotNull(videoPlayerLifecyleEventHandler, "videoPlayerLifecyleEventHandler");
        this.mTimelineMonitor = (TimelineMonitor) Preconditions.checkNotNull(timelineMonitor, "timelineMonitor");
        this.mPlaybackMediaEventReportersFactory = (PlaybackMediaEventReporters.Factory) Preconditions.checkNotNull(factory, "playbackMediaEventReportersFactory");
        ContentManagementEventBus contentManagementEventBus2 = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentManagementEventBus");
        this.mContentManagementEventBus = contentManagementEventBus2;
        this.mAdEventTransport = (AdEventTransport) Preconditions.checkNotNull(adEventTransport, "adEventTransport");
        this.mPlaybackSessionFailureManager = (PlaybackSessionFailureManager) Preconditions.checkNotNull(playbackSessionFailureManager, "playbackSessionFailureManager");
        this.mPlaybackMediaEventReportersMain = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "playbackMediaEventReportersMain");
        this.mPlaybackMediaEventReportersAd = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters2, "playbackMediaEventReportersAd");
        PlaybackMediaEventReporters playbackMediaEventReporters4 = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters3, "playbackMediaEventReporters");
        this.mPlaybackMediaEventReporters = playbackMediaEventReporters4;
        this.mPmetAcquisitionEventListener = (PmetAcquisitionEventListener) Preconditions.checkNotNull(pmetAcquisitionEventListener, "pmetAcquisitionEventListener");
        this.mPlayerHealthReporter = new PlayerHealthReporter(contentManagementEventBus2, smoothStreamingVideoPresentationEventReporter2, playbackMediaEventReporters4);
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public SmoothStreamingBitrateChangeAdapter getBitrateChangeAdapter() {
        return this.mBitrateChangeAdapter;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public SmoothStreamingContentEventAdapter getContentEventAdapter() {
        return this.mContentEventAdapter;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public ContentSessionFactory getContentSessionFactory() {
        return this.mContentSessionFactory;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public DiagnosticDataCollector getDataCollector() {
        return this.mDataCollector;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public ExecutorService getExecutor() {
        return this.mExecutor;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public PlaybackEventTransport getPlaybackEventTransport() {
        return this.mPlaybackEventTransport;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public MediaProfiler getProfiler() {
        return this.mProfiler;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public AVODContentUrlPolicyManager getContentUrlPolicyManager() {
        return this.mContentUrlPolicyManager;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public VideoCacheManager getCacheManager() {
        return this.mCacheManager;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public DownloadService getDownloadService() {
        return this.mDownloadService;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public SmoothStreamingVideoPresentationEventReporter getEventReporter() {
        return this.mEventReporter;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public PlaybackSupportEvaluator getPlaybackSupportEvaluator() {
        return this.mPlaybackEvaluator;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public VideoPlaybackEngineFactory getVideoPlaybackEngineFactory() {
        return this.mVideoPlaybackEngineFactory;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public BaseDrmSystem getDrmSystem() {
        return this.mDrmSystem;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public DrmLicenseAcquirer getLicenseAcquirer() {
        return this.mLicenseAcquirer;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public PVDownloadManagerPlayerShim getDownloadManager() {
        return this.mUserDownloadManager;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public IdentityShim getIdentity() {
        return this.mIdentity;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public PlaybackConfig getPlaybackConfig() {
        return this.mPlaybackConfig;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public RendererSchemeController getRendererSchemeController() {
        return this.mRendererSchemeController;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public SubtitlesEngine getSubtitlesEngine() {
        return this.mSubtitlesEngine;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public LifecycleProfiler getLifecycleProfiler() {
        return this.mLifecycleProfiler;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public LiveWindowDuration getLiveWindowDuration() {
        return this.mLiveWindowDuration;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public AdUriProxy getAdUriProxy() {
        return this.mAdUriProxy;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public AdPlanFactory getAdPlanFactory() {
        return this.mAdPlanFactory;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public VideoPlayerLifecyleEventHandler getVideoPlayerLifecyleEventHandler() {
        return this.mVideoPlayerLifecyleEventHandler;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public TimelineMonitor getTimelineMonitor() {
        return this.mTimelineMonitor;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public ContentManagementEventBus getContentManagementEventBus() {
        return this.mContentManagementEventBus;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public AdEventTransport getAdEventBus() {
        return this.mAdEventTransport;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public PlaybackSessionFailureManager getPlaybackSessionFailureManager() {
        return this.mPlaybackSessionFailureManager;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    @Deprecated
    public PlaybackMediaEventReporters getMediaEventReportersMain() {
        return this.mPlaybackMediaEventReportersMain;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    @Deprecated
    public PlaybackMediaEventReporters getMediaEventReportersAd() {
        return this.mPlaybackMediaEventReportersAd;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public PlaybackMediaEventReporters getMediaEventReporters() {
        return this.mPlaybackMediaEventReporters;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public PmetAcquisitionEventListener getPmetAcquisitionEventListener() {
        return this.mPmetAcquisitionEventListener;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionResources
    @Nonnull
    public PlayerHealthReporter getPlayerHealthReporter() {
        return this.mPlayerHealthReporter;
    }
}
