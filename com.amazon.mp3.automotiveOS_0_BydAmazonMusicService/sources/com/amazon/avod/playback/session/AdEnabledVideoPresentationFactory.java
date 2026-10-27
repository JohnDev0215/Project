package com.amazon.avod.playback.session;

import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.AdEnabledVideoPlayer;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachine;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.AdTransitioner;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.ads.internal.adplaybackstatemachine.AdEnabledPlaybackSharedContext;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.diagnostics.DiagnosticsOverlayToggler;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.amazon.avod.media.playback.SinglePlayerVideoPresentation;
import com.amazon.avod.media.playback.UserWatchSessionIdManager;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationFactoryBase;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.playback.config.PlayerLifecycleConfig;
import com.amazon.avod.playback.smoothstream.ServerInsertedManifestTimelineManager;
import com.amazon.avod.playback.smoothstream.SmoothStreamingVideoPlayer;
import com.amazon.avod.qahooks.QAFailoverFeature;
import com.amazon.avod.qahooks.VerificationLogger;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.File;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class AdEnabledVideoPresentationFactory extends VideoPresentationFactoryBase {
    private final AdEnabledPlaybackStateMachineFactory mAdEnabledPlaybackStateMachineFactory;

    @Deprecated
    private final Provider<AdEnabledVideoPlayer> mAdEnabledVideoPlayerProvider;
    private final Provider<AdPlanFactory> mAdPlanFactoryProvider;
    private final AdUriProxy mAdUriProxy;
    private final AdsConfig mAdsConfig;
    private final AdvertisingIdCollector mAdvertisingIdCollector;
    private final AmazonVideoPlayerFactory mAmazonVideoPlayerFactory;
    private final DiagnosticsOverlayToggler mDiagnosticsToggler;
    private final ExecutorService mExecutor;
    private final MediaSystemSharedDependencies mMediaSystemSharedDependencies;
    private final PlaybackSessionFactory mPlaybackSessionFactory;
    private PlayerLifecycleConfig mPlayerLifecycleConfig;
    private final SinglePlayerVideoPresentationFactory mSinglePlayerVideoPresentationFactory;
    private final UserWatchSessionIdManager mUserWatchSessionIdManager;
    private final Provider<VolumeManager> mVolumeManagerProvider;

    public AdEnabledVideoPresentationFactory(@Nonnull PlaybackSessionFactory playbackSessionFactory, @Nonnull AmazonVideoPlayerFactory amazonVideoPlayerFactory, @Nonnull @Deprecated Provider<AdEnabledVideoPlayer> provider, @Nonnull Provider<AdPlanFactory> provider2, @Nonnull Provider<VolumeManager> provider3, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull ExecutorService executorService, @Nonnull AdUriProxy adUriProxy) {
        this(playbackSessionFactory, amazonVideoPlayerFactory, provider, provider2, provider3, advertisingIdCollector, executorService, adUriProxy, AdsConfig.getInstance(), new DiagnosticsOverlayToggler(), new AdEnabledPlaybackStateMachineFactory(), new SinglePlayerVideoPresentationFactory(), null, MediaSystemSharedDependencies.getInstance(), UserWatchSessionIdManager.getInstance());
    }

    AdEnabledVideoPresentationFactory(@Nonnull PlaybackSessionFactory playbackSessionFactory, @Nonnull AmazonVideoPlayerFactory amazonVideoPlayerFactory, @Nonnull @Deprecated Provider<AdEnabledVideoPlayer> provider, @Nonnull Provider<AdPlanFactory> provider2, @Nonnull Provider<VolumeManager> provider3, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull ExecutorService executorService, @Nonnull AdUriProxy adUriProxy, @Nonnull AdsConfig adsConfig, @Nonnull DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull AdEnabledPlaybackStateMachineFactory adEnabledPlaybackStateMachineFactory, @Nonnull SinglePlayerVideoPresentationFactory singlePlayerVideoPresentationFactory, @Nullable PlayerLifecycleConfig playerLifecycleConfig, @Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies, @Nonnull UserWatchSessionIdManager userWatchSessionIdManager) {
        super(Lists.newArrayList("video/aiv-asin"));
        this.mPlaybackSessionFactory = (PlaybackSessionFactory) Preconditions.checkNotNull(playbackSessionFactory, "playbackSessionFactory");
        this.mAmazonVideoPlayerFactory = (AmazonVideoPlayerFactory) Preconditions.checkNotNull(amazonVideoPlayerFactory, "amazonVideoPlayerFactory");
        this.mAdEnabledVideoPlayerProvider = (Provider) Preconditions.checkNotNull(provider, "adEnabledVideoPlayerProvider");
        this.mAdPlanFactoryProvider = (Provider) Preconditions.checkNotNull(provider2, "adPlanFactoryProvider");
        this.mVolumeManagerProvider = (Provider) Preconditions.checkNotNull(provider3, "volumeManagerProvider");
        this.mAdvertisingIdCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollectorProvider");
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "executor");
        this.mAdUriProxy = (AdUriProxy) Preconditions.checkNotNull(adUriProxy, "adUriProxy");
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
        this.mDiagnosticsToggler = (DiagnosticsOverlayToggler) Preconditions.checkNotNull(diagnosticsOverlayToggler, "diagnosticsOverlayToggler");
        this.mAdEnabledPlaybackStateMachineFactory = (AdEnabledPlaybackStateMachineFactory) Preconditions.checkNotNull(adEnabledPlaybackStateMachineFactory, "adEnabledPlaybackStateMachineFactory");
        this.mSinglePlayerVideoPresentationFactory = (SinglePlayerVideoPresentationFactory) Preconditions.checkNotNull(singlePlayerVideoPresentationFactory, "singlePlayerVideoPresentationFactory");
        this.mPlayerLifecycleConfig = playerLifecycleConfig;
        this.mMediaSystemSharedDependencies = (MediaSystemSharedDependencies) Preconditions.checkNotNull(mediaSystemSharedDependencies, "mediaSystemSharedDependencies");
        this.mUserWatchSessionIdManager = (UserWatchSessionIdManager) Preconditions.checkNotNull(userWatchSessionIdManager, "userWatchSessionIdManager");
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationFactory
    @Nullable
    public VideoPresentation newVideoPresentation(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nonnull VideoOptions videoOptions) {
        Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        Preconditions.checkNotNull(videoOptions, "videoOptions");
        PlayerLifecycleConfig playerLifecycleConfig = getPlayerLifecycleConfig();
        if (!supportsMimeType(videoSpecification.getMimeType())) {
            return null;
        }
        if (this.mMediaSystemSharedDependencies.isSDKPlayer() || playerLifecycleConfig.isDAGBasedPlaybackEnabled(videoSpecification.isLiveStream())) {
            return newDAGBasedVideoPresentation(videoSpecification, file, videoOptions);
        }
        return newPLSMBasedVideoPresentation(videoSpecification, file, videoOptions);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Nullable
    private VideoPresentation newDAGBasedVideoPresentation(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nonnull VideoOptions videoOptions) {
        PlaybackSession playbackSessionNewPlaybackSession = this.mPlaybackSessionFactory.newPlaybackSession(true, videoOptions);
        PlaybackSessionResources resources = playbackSessionNewPlaybackSession.getResources();
        PlaybackSessionContext context = playbackSessionNewPlaybackSession.getContext();
        AmazonVideoPlayer amazonVideoPlayerNewAmazonVideoPlayer = this.mAmazonVideoPlayerFactory.newAmazonVideoPlayer(playbackSessionNewPlaybackSession, videoSpecification);
        QAFailoverFeature.getInstance().prepare(amazonVideoPlayerNewAmazonVideoPlayer);
        ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager = new ServerInsertedManifestTimelineManager(playbackSessionNewPlaybackSession.getContext(), amazonVideoPlayerNewAmazonVideoPlayer.getEventReporter(), videoSpecification.getDuration());
        AdPlaybackStateMachineContext adEnabledPlaybackSharedContext = new AdEnabledPlaybackSharedContext(this.mAdUriProxy, new AdTransitioner(this.mExecutor), amazonVideoPlayerNewAmazonVideoPlayer, amazonVideoPlayerNewAmazonVideoPlayer.getDiagnosticController(), videoSpecification, resources.getEventReporter(), context, this.mAdsConfig);
        AdEnabledVideoPlayer adEnabledVideoPlayer = this.mAdEnabledVideoPlayerProvider.get();
        AdPlaybackStateMachine adPlaybackStateMachineNewDAGBasedAdEnabledStateMachine = this.mAdEnabledPlaybackStateMachineFactory.newDAGBasedAdEnabledStateMachine(adEnabledPlaybackSharedContext, this.mVolumeManagerProvider.get(), this.mAdvertisingIdCollector, this.mDiagnosticsToggler, playbackSessionNewPlaybackSession, adEnabledVideoPlayer, serverInsertedManifestTimelineManager, amazonVideoPlayerNewAmazonVideoPlayer.getPlaybackExperienceController());
        playbackSessionNewPlaybackSession.getContext().setAdEnabledPlaybackManager(adPlaybackStateMachineNewDAGBasedAdEnabledStateMachine);
        adEnabledVideoPlayer.initialize(amazonVideoPlayerNewAmazonVideoPlayer, adPlaybackStateMachineNewDAGBasedAdEnabledStateMachine, serverInsertedManifestTimelineManager);
        adEnabledPlaybackSharedContext.initialize(adPlaybackStateMachineNewDAGBasedAdEnabledStateMachine, adEnabledVideoPlayer);
        return new MultiPlayerVideoPresentation(playbackSessionNewPlaybackSession, videoSpecification, amazonVideoPlayerNewAmazonVideoPlayer, amazonVideoPlayerNewAmazonVideoPlayer.getEventReporter(), amazonVideoPlayerNewAmazonVideoPlayer.getDiagnosticController(), playbackSessionNewPlaybackSession.getResources().getVideoPlayerLifecyleEventHandler(), new VerificationLogger(), file, adEnabledVideoPlayer, adPlaybackStateMachineNewDAGBasedAdEnabledStateMachine, videoOptions, this.mUserWatchSessionIdManager);
    }

    @Nullable
    @Deprecated
    private VideoPresentation newPLSMBasedVideoPresentation(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nonnull VideoOptions videoOptions) {
        Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        Preconditions.checkNotNull(videoOptions, "videoOptions");
        if (!supportsMimeType(videoSpecification.getMimeType())) {
            return null;
        }
        PlaybackSession playbackSessionNewPlaybackSession = this.mPlaybackSessionFactory.newPlaybackSession(false, videoOptions);
        SmoothStreamingVideoPlayer smoothStreamingVideoPlayerNewSmoothStreamingVideoPlayer = this.mAmazonVideoPlayerFactory.newSmoothStreamingVideoPlayer(playbackSessionNewPlaybackSession, videoSpecification);
        QAFailoverFeature.getInstance().prepare(smoothStreamingVideoPlayerNewSmoothStreamingVideoPlayer);
        VideoPresentation videoPresentationNewSinglePlayerVideoPresentation = this.mSinglePlayerVideoPresentationFactory.newSinglePlayerVideoPresentation(videoSpecification, videoOptions, smoothStreamingVideoPlayerNewSmoothStreamingVideoPlayer, smoothStreamingVideoPlayerNewSmoothStreamingVideoPlayer.getEventReporter(), smoothStreamingVideoPlayerNewSmoothStreamingVideoPlayer.getDiagnosticController(), newLifecycleEventHandler(), file, new VerificationLogger(), this.mUserWatchSessionIdManager);
        String offerType = videoOptions.getOfferType();
        Set<String> blackListAdTypes = this.mAdsConfig.getBlackListAdTypes();
        if (offerType == null || blackListAdTypes.contains(offerType)) {
            DLog.logf("Not creating AdEnabledVideoPresentation: offerType: %s, blackList: %s", offerType, blackListAdTypes);
            return videoPresentationNewSinglePlayerVideoPresentation;
        }
        AdEnabledVideoPlayer adEnabledVideoPlayer = this.mAdEnabledVideoPlayerProvider.get();
        ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager = new ServerInsertedManifestTimelineManager(playbackSessionNewPlaybackSession.getContext(), smoothStreamingVideoPlayerNewSmoothStreamingVideoPlayer.getEventReporter(), videoSpecification.getDuration());
        AdPlaybackStateMachine adPlaybackStateMachineNewPLSMBasedAdEnabledStateMachine = this.mAdEnabledPlaybackStateMachineFactory.newPLSMBasedAdEnabledStateMachine(this.mExecutor, this.mAdPlanFactoryProvider.get(), this.mAdUriProxy, this.mVolumeManagerProvider.get(), this.mAdvertisingIdCollector, this.mDiagnosticsToggler, videoPresentationNewSinglePlayerVideoPresentation, offerType, adEnabledVideoPlayer, playbackSessionNewPlaybackSession, serverInsertedManifestTimelineManager, smoothStreamingVideoPlayerNewSmoothStreamingVideoPlayer.getPlaybackExperienceController());
        com.amazon.avod.media.ads.internal.MultiPlayerVideoPresentation multiPlayerVideoPresentation = new com.amazon.avod.media.ads.internal.MultiPlayerVideoPresentation(videoPresentationNewSinglePlayerVideoPresentation, adEnabledVideoPlayer, adPlaybackStateMachineNewPLSMBasedAdEnabledStateMachine, playbackSessionNewPlaybackSession.getResources().getTimelineMonitor(), videoOptions);
        adEnabledVideoPlayer.initialize(smoothStreamingVideoPlayerNewSmoothStreamingVideoPlayer, adPlaybackStateMachineNewPLSMBasedAdEnabledStateMachine, serverInsertedManifestTimelineManager);
        return multiPlayerVideoPresentation;
    }

    @Deprecated
    protected static class SinglePlayerVideoPresentationFactory {
        protected SinglePlayerVideoPresentationFactory() {
        }

        @Nonnull
        @Deprecated
        protected VideoPresentation newSinglePlayerVideoPresentation(@Nonnull VideoSpecification videoSpecification, @Nonnull VideoOptions videoOptions, @Nonnull VideoPlayer videoPlayer, @Nonnull VideoPresentationEventReporter videoPresentationEventReporter, @Nonnull DiagnosticsController diagnosticsController, @Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nullable File file, @Nonnull VerificationLogger verificationLogger, @Nonnull UserWatchSessionIdManager userWatchSessionIdManager) {
            return new SinglePlayerVideoPresentation((VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification"), (VideoOptions) Preconditions.checkNotNull(videoOptions, "videoOptions"), (VideoPlayer) Preconditions.checkNotNull(videoPlayer, "videoPlayer"), (VideoPresentationEventReporter) Preconditions.checkNotNull(videoPresentationEventReporter, "videoPresentationEventReporter"), (DiagnosticsController) Preconditions.checkNotNull(diagnosticsController, "diagnosticsController"), (VideoPlayerLifecyleEventHandler) Preconditions.checkNotNull(videoPlayerLifecyleEventHandler, "videoPlayerLifecyleEventHandler"), file, (VerificationLogger) Preconditions.checkNotNull(verificationLogger, "verificationLogger"), userWatchSessionIdManager);
        }
    }

    private PlayerLifecycleConfig getPlayerLifecycleConfig() {
        if (this.mPlayerLifecycleConfig == null) {
            this.mPlayerLifecycleConfig = PlayerLifecycleConfig.getInstance();
        }
        return this.mPlayerLifecycleConfig;
    }
}
