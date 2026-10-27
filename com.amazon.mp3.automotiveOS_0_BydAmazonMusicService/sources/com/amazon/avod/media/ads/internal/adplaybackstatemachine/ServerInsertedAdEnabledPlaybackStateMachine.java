package com.amazon.avod.media.ads.internal.adplaybackstatemachine;

import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.StateBuilder;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdEnabledVideoPlayer;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.EmptyAdPlan;
import com.amazon.avod.media.ads.internal.LiveAdTrackingManager;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.ads.internal.state.ActiveState;
import com.amazon.avod.media.ads.internal.state.AdBreakErrorState;
import com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState;
import com.amazon.avod.media.ads.internal.state.AdEnabledPlayerTriggerType;
import com.amazon.avod.media.ads.internal.state.AdPauseState;
import com.amazon.avod.media.ads.internal.state.ClipErrorState;
import com.amazon.avod.media.ads.internal.state.PausedState;
import com.amazon.avod.media.ads.internal.state.PlayClipState;
import com.amazon.avod.media.ads.internal.state.PostPrimaryContentState;
import com.amazon.avod.media.ads.internal.state.PreparingClipState;
import com.amazon.avod.media.ads.internal.state.ServerInsertedAdBreakState;
import com.amazon.avod.media.ads.internal.state.ServerInsertedAdClipState;
import com.amazon.avod.media.ads.internal.state.ServerInsertedSeekState;
import com.amazon.avod.media.ads.internal.state.ShutdownState;
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
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ServerInsertedAdEnabledPlaybackStateMachine extends AdEnabledPlaybackStateMachine {
    private AdPlan mCurrentAdPlan;
    private final ServerInsertedManifestTimelineManager mServerInsertedManifestTimelineManager;

    public ServerInsertedAdEnabledPlaybackStateMachine(@Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull AdBreakSelector adBreakSelector, @Nonnull VolumeManager volumeManager, @Nonnull AdsConfig adsConfig, @Nonnull DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull PlaybackSession playbackSession, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer, @Nonnull ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager) {
        PlaybackMediaEventReporters mediaEventReportersAd;
        AdvertisingIdCollector advertisingIdCollector2 = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
        AdPlaybackStateMachineContext adPlaybackStateMachineContext2 = (AdPlaybackStateMachineContext) Preconditions.checkNotNull(adPlaybackStateMachineContext, "context");
        TimelineMonitor timelineMonitor = (TimelineMonitor) Preconditions.checkNotNull(playbackSession.getResources().getTimelineMonitor(), "timelineMonitor");
        AdBreakSelector adBreakSelector2 = new AdBreakSelector();
        VolumeManager volumeManager2 = (VolumeManager) Preconditions.checkNotNull(volumeManager, "volumeManager");
        AdsConfig adsConfig2 = AdsConfig.getInstance();
        DiagnosticsOverlayToggler diagnosticsOverlayToggler2 = (DiagnosticsOverlayToggler) Preconditions.checkNotNull(diagnosticsOverlayToggler, "diagnosticsToggler");
        PlaybackEventTransport playbackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackSession.getResources().getPlaybackEventTransport(), "playbackEventTransport");
        if (AloysiusConfig.getInstance().shouldUnifyMediaEventReporters()) {
            mediaEventReportersAd = playbackSession.getResources().getMediaEventReporters();
        } else {
            mediaEventReportersAd = playbackSession.getResources().getMediaEventReportersAd();
        }
        this(advertisingIdCollector2, adPlaybackStateMachineContext2, timelineMonitor, adBreakSelector2, volumeManager2, adsConfig2, diagnosticsOverlayToggler2, playbackEventTransport, mediaEventReportersAd, playbackSession.getResources().getAdEventBus(), (VideoPlayerLifecyleEventHandler) Preconditions.checkNotNull(playbackSession.getResources().getVideoPlayerLifecyleEventHandler(), "videoPlayerLifecyleEventHandler"), adEnabledVideoPlayer, playbackSession.getResources().getPmetAcquisitionEventListener(), playbackSession.getResources().getContentManagementEventBus(), AloysiusConfig.getInstance().shouldUnifyMediaEventReporters(), serverInsertedManifestTimelineManager);
    }

    public ServerInsertedAdEnabledPlaybackStateMachine(@Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull TimelineMonitor timelineMonitor, @Nonnull AdBreakSelector adBreakSelector, @Nonnull VolumeManager volumeManager, @Nonnull AdsConfig adsConfig, @Nonnull DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull AdEventTransport adEventTransport, @Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer, @Nonnull PmetAcquisitionEventListener pmetAcquisitionEventListener, @Nonnull ContentManagementEventBus contentManagementEventBus, boolean z, @Nonnull ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager) {
        super(advertisingIdCollector, adPlaybackStateMachineContext, timelineMonitor, adBreakSelector, volumeManager, adsConfig, diagnosticsOverlayToggler, playbackEventTransport, playbackMediaEventReporters, adEventTransport, videoPlayerLifecyleEventHandler, adEnabledVideoPlayer, pmetAcquisitionEventListener, contentManagementEventBus, Boolean.valueOf(z));
        this.mCurrentAdPlan = EmptyAdPlan.INSTANCE;
        this.mServerInsertedManifestTimelineManager = (ServerInsertedManifestTimelineManager) Preconditions.checkNotNull(serverInsertedManifestTimelineManager, "serverInsertedManifestTimelineManager");
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void initialize() {
        if (!this.mShouldUnifyMediaEventReporters) {
            this.mPlaybackMediaEventReporters.initialize(this.mAdEnabledVideoPlayer, null, false);
        }
        this.mPmetAcquisitionEventListener.initialize();
        ActiveState activeState = new ActiveState(this.mContext);
        ShutdownState shutdownState = new ShutdownState(this.mContext);
        AdEnabledPlaybackState adEnabledPlaybackState = new AdEnabledPlaybackState(StandardPlayerStateType.UNINITIALIZED, this.mContext);
        AdEnabledPlaybackState adEnabledPlaybackState2 = new AdEnabledPlaybackState(StandardPlayerStateType.PREPARED, this.mContext);
        ServerInsertedAdCheckPrerollState serverInsertedAdCheckPrerollState = new ServerInsertedAdCheckPrerollState(this.mContext, this.mBreakSelector, this.mPlaybackMediaEventReporters.getContentReporter());
        PrimaryContentState primaryContentState = new PrimaryContentState(this.mContext, this.mPlaybackEventTransport, this.mPlaybackMediaEventReporters.getContentReporter());
        AdPlaybackStateMachineContext adPlaybackStateMachineContext = this.mContext;
        TimelineMonitor timelineMonitor = this.mTimelineMonitor;
        ServerInsertedMonitoringPrimaryState serverInsertedMonitoringPrimaryState = new ServerInsertedMonitoringPrimaryState(adPlaybackStateMachineContext, timelineMonitor, this.mBreakSelector, new LiveAdTrackingManager(this.mPlaybackEventTransport, this.mAloysiusAdIdentifierReporter, timelineMonitor, adPlaybackStateMachineContext, this.mAdExecutor, this.mAdvertisingIdCollector, this.mEventBus), this.mServerInsertedManifestTimelineManager);
        ServerInsertedSeekState serverInsertedSeekState = new ServerInsertedSeekState(this.mContext, this.mBreakSelector, this.mServerInsertedManifestTimelineManager);
        AdEnabledPlaybackState adEnabledPlaybackState3 = new AdEnabledPlaybackState(StandardPlayerStateType.ERROR, this.mContext);
        ServerInsertedAdBreakState serverInsertedAdBreakState = new ServerInsertedAdBreakState(this.mContext, this.mPlaybackEventTransport, this.mPlaybackMediaEventReporters, this.mTimelineMonitor, this.mServerInsertedManifestTimelineManager);
        AdBreakErrorState adBreakErrorState = new AdBreakErrorState(this.mContext);
        ServerInsertedAdClipState serverInsertedAdClipState = new ServerInsertedAdClipState(this.mContext, this.mDiagnosticsToggler, this.mAdEventTransport, this.mPlaybackMediaEventReporters, this.mTimelineMonitor, this.mVolumeManager, this.mPlaybackEventTransport, this.mServerInsertedManifestTimelineManager);
        PlayClipState playClipState = new PlayClipState(this.mContext, this.mConfig, this.mTimelineMonitor, this.mVolumeManager, this.mPlaybackEventTransport);
        ClipErrorState clipErrorState = new ClipErrorState(this.mContext);
        PreparingClipState preparingClipState = new PreparingClipState(this.mContext, this.mConfig.getAdClipPrepareTimeout());
        PostPrimaryContentState postPrimaryContentState = new PostPrimaryContentState(this.mContext, this.mBreakSelector);
        AdEnabledPlaybackState adEnabledPlaybackState4 = new AdEnabledPlaybackState(StandardPlayerStateType.COMPLETED, this.mContext);
        PausedState pausedState = new PausedState(this.mContext);
        AdPlaybackStateMachineContext adPlaybackStateMachineContext2 = this.mContext;
        TransitionState.TransitionType transitionType = TransitionState.TransitionType.TRANSITION_IN;
        TransitionState transitionState = new TransitionState(adPlaybackStateMachineContext2, transitionType, this.mConfig.getAdTransitionInTime());
        TransitionState transitionState2 = new TransitionState(this.mContext, TransitionState.TransitionType.TRANSITION_OUT_FROM_PRIMARY, this.mConfig.getAdTransitionOutTime());
        TransitionState transitionState3 = new TransitionState(this.mContext, transitionType, this.mConfig.getAdTransitionInTime());
        AdPauseState adPauseState = new AdPauseState(this.mContext);
        setupState(shutdownState);
        setupState(adPauseState);
        setupState(activeState).registerTransition(StandardPlayerTriggerType.SHUTDOWN, shutdownState).registerTransition(StandardPlayerTriggerType.ERROR, adEnabledPlaybackState3);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilder = setupState(adEnabledPlaybackState, activeState);
        StandardPlayerTriggerType standardPlayerTriggerType = StandardPlayerTriggerType.PREPARED;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition = stateBuilder.registerTransition(standardPlayerTriggerType, adEnabledPlaybackState2);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType = AdEnabledPlayerTriggerType.PLAY_PRE_ROLLS;
        stateBuilderRegisterTransition.registerTransition(adEnabledPlayerTriggerType, serverInsertedAdCheckPrerollState);
        setupState(adEnabledPlaybackState2, activeState).registerTransition(adEnabledPlayerTriggerType, serverInsertedAdCheckPrerollState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilder2 = setupState(serverInsertedAdCheckPrerollState, activeState);
        StandardPlayerTriggerType standardPlayerTriggerType2 = StandardPlayerTriggerType.PLAY;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition2 = stateBuilder2.registerTransition(standardPlayerTriggerType2, primaryContentState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType2 = AdEnabledPlayerTriggerType.BEGIN_AD_BREAK;
        stateBuilderRegisterTransition2.registerTransition(adEnabledPlayerTriggerType2, serverInsertedAdBreakState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilder3 = setupState(serverInsertedAdBreakState, activeState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType3 = AdEnabledPlayerTriggerType.PLAY_AD;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition3 = stateBuilder3.registerTransition(adEnabledPlayerTriggerType3, serverInsertedAdClipState).registerTransition(AdEnabledPlayerTriggerType.NO_MORE_ADS_SKIP_TRANSITION, primaryContentState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType4 = AdEnabledPlayerTriggerType.NEXT_AD_CLIP_SERVER_INSERTED;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition4 = stateBuilderRegisterTransition3.registerTransition(adEnabledPlayerTriggerType4, serverInsertedAdClipState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType5 = AdEnabledPlayerTriggerType.NO_MORE_ADS;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition5 = stateBuilderRegisterTransition4.registerTransition(adEnabledPlayerTriggerType5, transitionState3);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType6 = AdEnabledPlayerTriggerType.SKIP_CURRENT_AD_BREAK;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition6 = stateBuilderRegisterTransition5.registerTransition(adEnabledPlayerTriggerType6, transitionState3);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType7 = AdEnabledPlayerTriggerType.AD_CLIP_ERROR;
        stateBuilderRegisterTransition6.registerTransition(adEnabledPlayerTriggerType7, transitionState3);
        setupState(adBreakErrorState, serverInsertedAdBreakState);
        setupState(transitionState2, serverInsertedAdBreakState).registerTransition(adEnabledPlayerTriggerType3, serverInsertedAdClipState);
        setupState(transitionState3, serverInsertedAdBreakState).registerTransition(adEnabledPlayerTriggerType5, primaryContentState).registerTransition(adEnabledPlayerTriggerType6, primaryContentState).registerTransition(adEnabledPlayerTriggerType7, primaryContentState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilder4 = setupState(serverInsertedAdClipState, serverInsertedAdBreakState);
        StandardPlayerTriggerType standardPlayerTriggerType3 = StandardPlayerTriggerType.SEEK;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition7 = stateBuilder4.registerTransition(standardPlayerTriggerType3, serverInsertedAdClipState).registerTransition(AdEnabledPlayerTriggerType.PREPARE_AD, preparingClipState);
        StandardPlayerTriggerType standardPlayerTriggerType4 = StandardPlayerTriggerType.COMPLETED;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition8 = stateBuilderRegisterTransition7.registerTransition(standardPlayerTriggerType4, serverInsertedAdClipState).registerTransition(adEnabledPlayerTriggerType4, serverInsertedAdClipState);
        StandardPlayerTriggerType standardPlayerTriggerType5 = StandardPlayerTriggerType.PAUSE;
        stateBuilderRegisterTransition8.registerTransition(standardPlayerTriggerType5, adPauseState);
        setupState(preparingClipState, serverInsertedAdClipState).registerTransition(standardPlayerTriggerType, transitionState).registerTransition(adEnabledPlayerTriggerType6, transitionState3).registerTransition(adEnabledPlayerTriggerType7, clipErrorState);
        setupState(transitionState, serverInsertedAdClipState).registerTransition(standardPlayerTriggerType, playClipState);
        setupState(clipErrorState, serverInsertedAdClipState).registerTransition(adEnabledPlayerTriggerType7, adBreakErrorState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition9 = setupState(primaryContentState, activeState).registerTransition(adEnabledPlayerTriggerType2, serverInsertedAdBreakState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType8 = AdEnabledPlayerTriggerType.MONITOR_PRIMARY_CONTENT;
        stateBuilderRegisterTransition9.registerTransition(adEnabledPlayerTriggerType8, serverInsertedMonitoringPrimaryState).registerTransition(standardPlayerTriggerType3, serverInsertedSeekState).registerTransition(standardPlayerTriggerType4, postPrimaryContentState);
        setupState(serverInsertedSeekState, primaryContentState).registerTransition(adEnabledPlayerTriggerType8, serverInsertedMonitoringPrimaryState).registerTransition(adEnabledPlayerTriggerType2, serverInsertedAdBreakState);
        setupState(serverInsertedMonitoringPrimaryState, primaryContentState).registerTransition(standardPlayerTriggerType5, pausedState).registerTransition(standardPlayerTriggerType2, primaryContentState).registerTransition(AdEnabledPlayerTriggerType.AD_PLAN_READY, serverInsertedMonitoringPrimaryState);
        setupState(pausedState, serverInsertedMonitoringPrimaryState);
        setupState(postPrimaryContentState, activeState).registerTransition(standardPlayerTriggerType4, adEnabledPlaybackState4).registerTransition(adEnabledPlayerTriggerType2, serverInsertedAdBreakState);
        setupState(adEnabledPlaybackState3, activeState);
        setupState(adEnabledPlaybackState4, activeState);
        start(adEnabledPlaybackState);
    }

    @Override // com.amazon.avod.media.ads.internal.adplaybackstatemachine.AdEnabledPlaybackStateMachine, com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void seekTo(TimeSpan timeSpan) {
        doTrigger(new SeekTrigger(timeSpan));
    }

    @Override // com.amazon.avod.media.ads.internal.adplaybackstatemachine.AdEnabledPlaybackStateMachine, com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void play() {
        getContext().getPrimaryPlayer().start();
        doTrigger(PlayerTriggers.PLAY);
    }

    @Override // com.amazon.avod.media.ads.internal.adplaybackstatemachine.AdEnabledPlaybackStateMachine, com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void pause() {
        getContext().getPrimaryPlayer().pause();
    }

    @Override // com.amazon.avod.media.ads.internal.adplaybackstatemachine.AdEnabledPlaybackStateMachine, com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void updateAdPlan(@Nonnull AdPlan adPlan) {
        Preconditions.checkNotNull(adPlan, "adPlan");
        this.mServerInsertedManifestTimelineManager.notifyAdPlanRetrievalComplete(adPlan, this.mContext);
        this.mCurrentAdPlan = adPlan;
    }

    @Override // com.amazon.avod.media.ads.internal.adplaybackstatemachine.AdEnabledPlaybackStateMachine, com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onPrepared(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
        AloysiusDiagnosticsReporter diagnosticsReporter = this.mPlaybackMediaEventReporters.getDiagnosticsReporter();
        PlaybackPmetMetricReporter playbackPmetMetricReporter = PlaybackPmetMetricReporter.getInstance();
        PlaybackEventReporter primaryEventReporter = this.mContext.getPrimaryEventReporter();
        PresentationEvent.PresentationType presentationType = PresentationEvent.PresentationType.DAG;
        diagnosticsReporter.handlePresentationEvent(new PresentationEvent(presentationType));
        playbackPmetMetricReporter.reportPresentationMetrics(PresentationMetrics.DAG);
        primaryEventReporter.reportMetric("AdEvent", QOSEventName.PresentationType.toString(), null, presentationType.toString(), null);
        this.mServerInsertedManifestTimelineManager.notifyAdPlanRetrievalComplete(this.mCurrentAdPlan);
        super.onPrepared(videoPresentation, playbackDataSource);
    }

    @Override // com.amazon.avod.media.ads.internal.adplaybackstatemachine.AdEnabledPlaybackStateMachine, com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onStarted(@Nonnull VideoPresentation videoPresentation, @Nonnull PlaybackDataSource playbackDataSource) {
        super.onStarted(videoPresentation, playbackDataSource);
    }
}
