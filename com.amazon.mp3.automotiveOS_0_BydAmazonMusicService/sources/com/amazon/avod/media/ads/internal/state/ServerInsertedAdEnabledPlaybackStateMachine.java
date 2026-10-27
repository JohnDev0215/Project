package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.StateBuilder;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdEnabledVideoPlayer;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.AdTransitioner;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.ads.internal.LiveAdTrackingManager;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.ads.internal.state.TransitionState;
import com.amazon.avod.media.diagnostics.DiagnosticsOverlayToggler;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.pmet.PmetAcquisitionEventListener;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDiagnosticsReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.state.PlayerStateType;
import com.amazon.avod.media.playback.state.StandardPlayerStateType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggers;
import com.amazon.avod.media.playback.state.trigger.SeekTrigger;
import com.amazon.avod.media.playback.state.trigger.StandardPlayerTriggerType;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.playback.PresentationEvent;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.playback.smoothstream.ServerInsertedManifestTimelineManager;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.pmet.PresentationMetrics;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class ServerInsertedAdEnabledPlaybackStateMachine extends AdEnabledPlaybackStateMachine {
    public ServerInsertedAdEnabledPlaybackStateMachine(ExecutorService executorService, AdPlanFactory adPlanFactory, AdUriProxy adUriProxy, VolumeManager volumeManager, @Nonnull AdvertisingIdCollector advertisingIdCollector, DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull VideoPresentation videoPresentation, @Nonnull String str, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer, @Nonnull PlaybackSession playbackSession, @Nonnull ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager) {
        PlaybackMediaEventReporters mediaEventReportersAd;
        AdEnabledPlaybackSharedContext adEnabledPlaybackSharedContext = new AdEnabledPlaybackSharedContext(new VideoPlayerLifecyleEventHandler(), adUriProxy, new AdTransitioner(executorService), executorService, videoPresentation.getPlayer(), videoPresentation.getDiagnosticsController(), videoPresentation.getSpecification(), str, playbackSession.getContext());
        TimelineMonitor timelineMonitor = playbackSession.getResources().getTimelineMonitor();
        AdBreakSelector adBreakSelector = new AdBreakSelector();
        AdvertisingIdCollector advertisingIdCollector2 = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollectorProvider");
        AdsConfig adsConfig = AdsConfig.getInstance();
        PlaybackEventTransport playbackEventTransport = playbackSession.getResources().getPlaybackEventTransport();
        AdEventTransport adEventBus = playbackSession.getResources().getAdEventBus();
        if (AloysiusConfig.getInstance().shouldUnifyMediaEventReporters()) {
            mediaEventReportersAd = playbackSession.getResources().getMediaEventReporters();
        } else {
            mediaEventReportersAd = playbackSession.getResources().getMediaEventReportersAd();
        }
        this(adEnabledPlaybackSharedContext, timelineMonitor, executorService, adPlanFactory, adBreakSelector, volumeManager, advertisingIdCollector2, adsConfig, diagnosticsOverlayToggler, videoPresentation, str, adEnabledVideoPlayer, playbackEventTransport, adEventBus, mediaEventReportersAd, playbackSession.getResources().getPmetAcquisitionEventListener(), playbackSession.getResources().getContentManagementEventBus(), serverInsertedManifestTimelineManager, AloysiusConfig.getInstance().shouldUnifyMediaEventReporters());
    }

    ServerInsertedAdEnabledPlaybackStateMachine(AdPlaybackStateMachineContext adPlaybackStateMachineContext, TimelineMonitor timelineMonitor, ExecutorService executorService, AdPlanFactory adPlanFactory, AdBreakSelector adBreakSelector, VolumeManager volumeManager, @Nonnull AdvertisingIdCollector advertisingIdCollector, AdsConfig adsConfig, DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull VideoPresentation videoPresentation, @Nonnull String str, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull AdEventTransport adEventTransport, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull PmetAcquisitionEventListener pmetAcquisitionEventListener, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager, boolean z) {
        super(adPlaybackStateMachineContext, timelineMonitor, executorService, adPlanFactory, adBreakSelector, volumeManager, advertisingIdCollector, adsConfig, diagnosticsOverlayToggler, videoPresentation, str, adEnabledVideoPlayer, playbackEventTransport, adEventTransport, playbackMediaEventReporters, pmetAcquisitionEventListener, contentManagementEventBus, serverInsertedManifestTimelineManager, z);
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void initialize() {
        if (!this.mShouldUnifyMediaEventReporters) {
            this.mPlaybackMediaEventReporters.initialize(this.mAdEnabledVideoPlayer, null, false);
            this.mPlaybackMediaEventReporters.setCyclicReportingPaused(true);
        }
        this.mPmetAcquisitionEventListener.initialize();
        this.mContext.initialize(this, this.mAdEnabledVideoPlayer);
        ActiveState activeState = new ActiveState(this.mContext);
        ShutdownState shutdownState = new ShutdownState(this.mContext);
        AdEnabledPlaybackState adEnabledPlaybackState = new AdEnabledPlaybackState(StandardPlayerStateType.UNINITIALIZED, this.mContext);
        AdEnabledPlaybackState adEnabledPlaybackState2 = new AdEnabledPlaybackState(AdEnabledPlayerStateType.WAITING_FOR_PRIMARY_PLAYER, this.mContext);
        WaitingForAdsState waitingForAdsState = new WaitingForAdsState(AdEnabledPlayerStateType.WAITING_FOR_AD_PLAN, this.mContext, this.mOfferType);
        AsyncAdPlanRetriever asyncAdPlanRetriever = new AsyncAdPlanRetriever(this.mContext, this.mPlanFactory, this.mExecutor, new AdEnabledPlaybackTimer(this.mConfig.getAdClipPrepareTimeout()), this.mOfferType, waitingForAdsState, this.mAdEventTransport, this.mServerInsertedManifestTimelineManager, this.mPlaybackEventTransport);
        this.mContext.getPresentationEventListener().addConcreteListener(asyncAdPlanRetriever);
        this.mContext.getPlaybackSessionContext().setAsyncAdPlanRetriever(asyncAdPlanRetriever);
        PreparingState preparingState = new PreparingState(this.mContext, asyncAdPlanRetriever);
        PreparedState preparedState = new PreparedState(StandardPlayerStateType.PREPARED, this.mContext, this.mConfig.getPreInitAdPreloadingEnabled());
        PreparedSeekingState preparedSeekingState = new PreparedSeekingState(this.mContext);
        ServerInsertedAdCheckPrerollState serverInsertedAdCheckPrerollState = new ServerInsertedAdCheckPrerollState(this.mContext, this.mBreakSelector, this.mPlaybackMediaEventReporters.getContentReporter());
        PrimaryContentState primaryContentState = new PrimaryContentState(this.mContext, this.mPlaybackMediaEventReporters.getContentReporter());
        ServerInsertedMonitoringPrimaryState serverInsertedMonitoringPrimaryState = new ServerInsertedMonitoringPrimaryState(this.mContext, this.mTimelineMonitor, this.mBreakSelector, new LiveAdTrackingManager(this.mPlaybackEventTransport, this.mPlaybackMediaEventReporters.getAloysiusAdIdentifierReporter(), this.mTimelineMonitor, this.mContext, this.mAdExecutorService, this.mAdvertisingIdCollector, this.mEventBus), this.mServerInsertedManifestTimelineManager);
        ServerInsertedSeekState serverInsertedSeekState = new ServerInsertedSeekState(this.mContext, this.mBreakSelector, this.mServerInsertedManifestTimelineManager);
        ErrorState errorState = new ErrorState(this.mContext);
        ServerInsertedAdBreakState serverInsertedAdBreakState = new ServerInsertedAdBreakState(this.mContext, this.mPlaybackEventTransport, this.mPlaybackMediaEventReporters, this.mTimelineMonitor, this.mServerInsertedManifestTimelineManager);
        AdBreakErrorState adBreakErrorState = new AdBreakErrorState(this.mContext);
        ServerInsertedAdClipState serverInsertedAdClipState = new ServerInsertedAdClipState(this.mContext, this.mDiagnosticsToggler, this.mAdEventTransport, this.mPlaybackMediaEventReporters, this.mTimelineMonitor, this.mVolumeManager, this.mPlaybackEventTransport, this.mServerInsertedManifestTimelineManager);
        PlayClipState playClipState = new PlayClipState(this.mContext, this.mConfig, this.mTimelineMonitor, this.mVolumeManager, this.mPlaybackEventTransport);
        ClipErrorState clipErrorState = new ClipErrorState(this.mContext);
        PreparingClipState preparingClipState = new PreparingClipState(this.mContext, this.mConfig.getAdClipPrepareTimeout());
        PostPrimaryContentState postPrimaryContentState = new PostPrimaryContentState(this.mContext, this.mBreakSelector);
        CompletedState completedState = new CompletedState(this.mContext);
        PausedState pausedState = new PausedState(this.mContext);
        AdPlaybackStateMachineContext adPlaybackStateMachineContext = this.mContext;
        TransitionState.TransitionType transitionType = TransitionState.TransitionType.TRANSITION_IN;
        TransitionState transitionState = new TransitionState(adPlaybackStateMachineContext, transitionType, this.mConfig.getAdTransitionInTime());
        TransitionState transitionState2 = new TransitionState(this.mContext, TransitionState.TransitionType.TRANSITION_OUT_FROM_PRIMARY, this.mConfig.getAdTransitionOutTime());
        TransitionState transitionState3 = new TransitionState(this.mContext, transitionType, this.mConfig.getAdTransitionInTime());
        AdPauseState adPauseState = new AdPauseState(this.mContext);
        setupState(shutdownState);
        setupState(adPauseState);
        setupState(activeState).registerTransition(StandardPlayerTriggerType.SHUTDOWN, shutdownState).registerTransition(StandardPlayerTriggerType.ERROR, errorState);
        setupState(adEnabledPlaybackState, activeState).registerTransition(StandardPlayerTriggerType.PREPARE, preparingState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilder = setupState(preparingState, activeState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType = AdEnabledPlayerTriggerType.AD_PLAN_READY;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition = stateBuilder.registerTransition(adEnabledPlayerTriggerType, adEnabledPlaybackState2);
        StandardPlayerTriggerType standardPlayerTriggerType = StandardPlayerTriggerType.PREPARED;
        stateBuilderRegisterTransition.registerTransition(standardPlayerTriggerType, waitingForAdsState);
        setupState(waitingForAdsState, activeState).registerTransition(adEnabledPlayerTriggerType, preparedState);
        setupState(adEnabledPlaybackState2, activeState).registerTransition(standardPlayerTriggerType, preparedState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilder2 = setupState(preparedState, activeState);
        StandardPlayerTriggerType standardPlayerTriggerType2 = StandardPlayerTriggerType.SEEK;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition2 = stateBuilder2.registerTransition(standardPlayerTriggerType2, preparedSeekingState);
        StandardPlayerTriggerType standardPlayerTriggerType3 = StandardPlayerTriggerType.PLAY;
        stateBuilderRegisterTransition2.registerTransition(standardPlayerTriggerType3, serverInsertedAdCheckPrerollState);
        setupState(preparedSeekingState, activeState).registerTransition(standardPlayerTriggerType2, preparedSeekingState).registerTransition(standardPlayerTriggerType, preparedState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition3 = setupState(serverInsertedAdCheckPrerollState, activeState).registerTransition(standardPlayerTriggerType3, primaryContentState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType2 = AdEnabledPlayerTriggerType.BEGIN_AD_BREAK;
        stateBuilderRegisterTransition3.registerTransition(adEnabledPlayerTriggerType2, serverInsertedAdBreakState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilder3 = setupState(serverInsertedAdBreakState, activeState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType3 = AdEnabledPlayerTriggerType.PLAY_AD;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition4 = stateBuilder3.registerTransition(adEnabledPlayerTriggerType3, serverInsertedAdClipState).registerTransition(AdEnabledPlayerTriggerType.NO_MORE_ADS_SKIP_TRANSITION, primaryContentState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType4 = AdEnabledPlayerTriggerType.NEXT_AD_CLIP_SERVER_INSERTED;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition5 = stateBuilderRegisterTransition4.registerTransition(adEnabledPlayerTriggerType4, serverInsertedAdClipState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType5 = AdEnabledPlayerTriggerType.NO_MORE_ADS;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition6 = stateBuilderRegisterTransition5.registerTransition(adEnabledPlayerTriggerType5, transitionState3);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType6 = AdEnabledPlayerTriggerType.SKIP_CURRENT_AD_BREAK;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition7 = stateBuilderRegisterTransition6.registerTransition(adEnabledPlayerTriggerType6, transitionState3);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType7 = AdEnabledPlayerTriggerType.AD_CLIP_ERROR;
        stateBuilderRegisterTransition7.registerTransition(adEnabledPlayerTriggerType7, transitionState3);
        setupState(adBreakErrorState, serverInsertedAdBreakState);
        setupState(transitionState2, serverInsertedAdBreakState).registerTransition(adEnabledPlayerTriggerType3, serverInsertedAdClipState);
        setupState(transitionState3, serverInsertedAdBreakState).registerTransition(adEnabledPlayerTriggerType5, primaryContentState).registerTransition(adEnabledPlayerTriggerType6, primaryContentState).registerTransition(adEnabledPlayerTriggerType7, primaryContentState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition8 = setupState(serverInsertedAdClipState, serverInsertedAdBreakState).registerTransition(standardPlayerTriggerType2, serverInsertedAdClipState).registerTransition(AdEnabledPlayerTriggerType.PREPARE_AD, preparingClipState);
        StandardPlayerTriggerType standardPlayerTriggerType4 = StandardPlayerTriggerType.COMPLETED;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition9 = stateBuilderRegisterTransition8.registerTransition(standardPlayerTriggerType4, serverInsertedAdClipState).registerTransition(adEnabledPlayerTriggerType4, serverInsertedAdClipState);
        StandardPlayerTriggerType standardPlayerTriggerType5 = StandardPlayerTriggerType.PAUSE;
        stateBuilderRegisterTransition9.registerTransition(standardPlayerTriggerType5, adPauseState);
        setupState(preparingClipState, serverInsertedAdClipState).registerTransition(standardPlayerTriggerType, transitionState).registerTransition(adEnabledPlayerTriggerType6, transitionState3).registerTransition(adEnabledPlayerTriggerType7, clipErrorState);
        setupState(transitionState, serverInsertedAdClipState).registerTransition(standardPlayerTriggerType, playClipState);
        setupState(clipErrorState, serverInsertedAdClipState).registerTransition(adEnabledPlayerTriggerType7, adBreakErrorState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition10 = setupState(primaryContentState, activeState).registerTransition(adEnabledPlayerTriggerType2, serverInsertedAdBreakState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType8 = AdEnabledPlayerTriggerType.MONITOR_PRIMARY_CONTENT;
        stateBuilderRegisterTransition10.registerTransition(adEnabledPlayerTriggerType8, serverInsertedMonitoringPrimaryState).registerTransition(standardPlayerTriggerType2, serverInsertedSeekState).registerTransition(standardPlayerTriggerType4, postPrimaryContentState);
        setupState(serverInsertedSeekState, primaryContentState).registerTransition(adEnabledPlayerTriggerType8, serverInsertedMonitoringPrimaryState).registerTransition(adEnabledPlayerTriggerType2, serverInsertedAdBreakState);
        setupState(serverInsertedMonitoringPrimaryState, primaryContentState).registerTransition(standardPlayerTriggerType5, pausedState).registerTransition(standardPlayerTriggerType3, primaryContentState).registerTransition(adEnabledPlayerTriggerType, serverInsertedMonitoringPrimaryState);
        setupState(pausedState, serverInsertedMonitoringPrimaryState);
        setupState(postPrimaryContentState, activeState).registerTransition(standardPlayerTriggerType4, completedState).registerTransition(adEnabledPlayerTriggerType2, serverInsertedAdBreakState);
        setupState(errorState, activeState);
        setupState(completedState, activeState);
        start(adEnabledPlaybackState);
        doTrigger(new PrepareTrigger(this.mPrimaryVideoPresentation));
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackStateMachine, com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void seekTo(TimeSpan timeSpan) {
        doTrigger(new SeekTrigger(timeSpan));
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackStateMachine, com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void play() {
        getContext().getPrimaryPlayer().start();
        doTrigger(PlayerTriggers.PLAY);
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackStateMachine, com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void pause() {
        getContext().getPrimaryPlayer().pause();
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackStateMachine, com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onPrepared(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
        AloysiusDiagnosticsReporter diagnosticsReporter = this.mPlaybackMediaEventReporters.getDiagnosticsReporter();
        PlaybackPmetMetricReporter playbackPmetMetricReporter = PlaybackPmetMetricReporter.getInstance();
        PlaybackEventReporter primaryEventReporter = this.mContext.getPrimaryEventReporter();
        PresentationEvent.PresentationType presentationType = PresentationEvent.PresentationType.PLSM;
        diagnosticsReporter.handlePresentationEvent(new PresentationEvent(presentationType));
        playbackPmetMetricReporter.reportPresentationMetrics(PresentationMetrics.PLSM);
        primaryEventReporter.reportMetric("AdEvent", QOSEventName.PresentationType.toString(), null, presentationType.toString(), null);
        super.onPrepared(videoPresentation, playbackDataSource);
    }
}
