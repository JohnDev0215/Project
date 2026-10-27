package com.amazon.avod.playback.session;

import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.LiveWindowDuration;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.service.AVODContentUrlPolicyManager;
import com.amazon.avod.playback.config.PlayerLifecycleConfig;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.session.workflow.PlayerLifecycleWorkflow;
import com.amazon.avod.playback.session.workflow.TaskSchedulerBasedPlaybackSession;
import com.amazon.avod.playback.smoothstream.SmoothStreamingBitrateChangeAdapter;
import com.amazon.avod.playback.smoothstream.SmoothStreamingContentEventAdapter;
import com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector;
import com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollectorProvider;
import com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleStateMachine;
import com.amazon.avod.playback.smoothstream.fsm.VideoPlaybackEngineFactory;
import com.amazon.avod.playback.subtitles.SubtitlesEngine;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackSessionFactory {
    private final Provider<AdPlanFactory> mAdPlanFactoryProvider;
    private final AdUriProxy mAdUriProxy;
    private final VideoCacheManager mCacheManager;
    private final ContentSessionFactory mContentSessionFactory;
    private final DownloadService mDownloadService;
    private final BaseDrmSystem mDrmSystem;
    private ContentManagementEventBus mEventBus;
    private final PlaybackConfig mPlaybackConfig = PlaybackConfig.INSTANCE;
    private final PlaybackSupportEvaluator mPlaybackEvaluator;
    private final Provider<PlaybackMediaEventReporters.Factory> mPlaybackMediaEventReportersFactoryProvider;
    private final MediaProfiler mProfiler;
    private final QOSCommunicationService mQOSCommunicationService;
    private final RendererSchemeController mRendererSchemeController;
    private final Provider<SmoothStreamingVideoPresentationEventReporter> mReporterProvider;
    private final MediaSystemSharedContext mSharedContext;
    private final Provider<ContentUrlFetcher> mUrlFetcherProvider;

    PlaybackSessionFactory(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull ContentSessionFactory contentSessionFactory, @Nonnull MediaProfiler mediaProfiler, @Nonnull BaseDrmSystem baseDrmSystem, @Nonnull VideoCacheManager videoCacheManager, @Nonnull DownloadService downloadService, @Nonnull Provider<SmoothStreamingVideoPresentationEventReporter> provider, @Nonnull Provider<ContentUrlFetcher> provider2, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull Provider<AdPlanFactory> provider3, @Nonnull AdUriProxy adUriProxy, @Nonnull Provider<PlaybackMediaEventReporters.Factory> provider4, @Nonnull QOSCommunicationService qOSCommunicationService) {
        this.mSharedContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "sharedContext");
        this.mContentSessionFactory = (ContentSessionFactory) Preconditions.checkNotNull(contentSessionFactory, "contentSessionFactory");
        this.mProfiler = (MediaProfiler) Preconditions.checkNotNull(mediaProfiler, "profiler");
        this.mDrmSystem = (BaseDrmSystem) Preconditions.checkNotNull(baseDrmSystem, "drmSystem");
        this.mCacheManager = (VideoCacheManager) Preconditions.checkNotNull(videoCacheManager, "cacheManager");
        this.mDownloadService = (DownloadService) Preconditions.checkNotNull(downloadService, "downloadService");
        this.mReporterProvider = (Provider) Preconditions.checkNotNull(provider, "reporterProvider");
        this.mUrlFetcherProvider = (Provider) Preconditions.checkNotNull(provider2, "urlFetcherProvider");
        this.mPlaybackEvaluator = (PlaybackSupportEvaluator) Preconditions.checkNotNull(playbackSupportEvaluator, "playbackEvaluator");
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        this.mAdPlanFactoryProvider = (Provider) Preconditions.checkNotNull(provider3, "adPlanFactoryProvider");
        this.mAdUriProxy = (AdUriProxy) Preconditions.checkNotNull(adUriProxy, "adUriProxy");
        this.mPlaybackMediaEventReportersFactoryProvider = (Provider) Preconditions.checkNotNull(provider4, "playbackMediaEventReportersFactoryProvider");
        this.mQOSCommunicationService = (QOSCommunicationService) Preconditions.checkNotNull(qOSCommunicationService, "qosCommunicationService");
    }

    @Nonnull
    public PlaybackSession newPlaybackSession(boolean z, @Nonnull VideoOptions videoOptions) {
        Preconditions.checkNotNull(videoOptions, "videoOptions");
        PlaybackEventTransport playbackEventTransport = new PlaybackEventTransport();
        this.mEventBus = new ContentManagementEventBus();
        AdEventTransport eventTransport = this.mQOSCommunicationService.getEventTransport();
        DiagnosticDataCollector diagnosticDataCollectorNewCollector = new DiagnosticDataCollectorProvider(playbackEventTransport).newCollector();
        SmoothStreamingVideoPresentationEventReporter smoothStreamingVideoPresentationEventReporter = this.mReporterProvider.get();
        SmoothStreamingBitrateChangeAdapter smoothStreamingBitrateChangeAdapter = new SmoothStreamingBitrateChangeAdapter(playbackEventTransport, smoothStreamingVideoPresentationEventReporter, SmoothStreamingPlaybackConfig.INSTANCE);
        SmoothStreamingContentEventAdapter smoothStreamingContentEventAdapter = new SmoothStreamingContentEventAdapter(playbackEventTransport);
        AVODContentUrlPolicyManager aVODContentUrlPolicyManager = new AVODContentUrlPolicyManager(this.mUrlFetcherProvider.get(), playbackEventTransport, this.mCacheManager, MediaSystemSharedDependencies.getInstance().getPlaybackEnvelopeValidator(), PlaybackResourcesV2Config.getInstance());
        VideoPlaybackEngineFactory videoPlaybackEngineFactory = new VideoPlaybackEngineFactory(this.mProfiler, this.mDrmSystem, playbackEventTransport, this.mRendererSchemeController);
        SubtitlesEngine subtitlesEngine = new SubtitlesEngine();
        LiveWindowDuration liveWindowDuration = new LiveWindowDuration();
        TimelineMonitor timelineMonitor = new TimelineMonitor();
        PlaybackSessionFailureManager playbackSessionFailureManager = new PlaybackSessionFailureManager(this.mRendererSchemeController, smoothStreamingVideoPresentationEventReporter, this.mCacheManager);
        if (z) {
            return new TaskSchedulerBasedPlaybackSession(new PlayerLifecycleWorkflow(PlayerLifecycleConfig.getInstance()), this.mSharedContext.getExecutorService(), this.mContentSessionFactory, this.mProfiler, videoPlaybackEngineFactory, playbackEventTransport, diagnosticDataCollectorNewCollector, smoothStreamingBitrateChangeAdapter, smoothStreamingContentEventAdapter, aVODContentUrlPolicyManager, this.mCacheManager, this.mDownloadService, smoothStreamingVideoPresentationEventReporter, this.mPlaybackEvaluator, this.mDrmSystem, this.mRendererSchemeController, subtitlesEngine, liveWindowDuration, this.mAdUriProxy, this.mAdPlanFactoryProvider.get(), new VideoPlayerLifecyleEventHandler(), videoOptions, timelineMonitor, this.mPlaybackMediaEventReportersFactoryProvider.get(), this.mEventBus, eventTransport, playbackSessionFailureManager, this.mPlaybackConfig);
        }
        return new PlayerLifecycleStateMachine(this.mSharedContext.getExecutorService(), this.mContentSessionFactory, this.mProfiler, videoPlaybackEngineFactory, playbackEventTransport, diagnosticDataCollectorNewCollector, smoothStreamingBitrateChangeAdapter, smoothStreamingContentEventAdapter, aVODContentUrlPolicyManager, this.mCacheManager, this.mDownloadService, smoothStreamingVideoPresentationEventReporter, this.mPlaybackEvaluator, this.mDrmSystem, this.mRendererSchemeController, subtitlesEngine, liveWindowDuration, videoOptions, this.mPlaybackMediaEventReportersFactoryProvider.get(), this.mEventBus, timelineMonitor, eventTransport, playbackSessionFailureManager, this.mPlaybackConfig);
    }
}
