package com.amazon.avod.media.ads.internal.adplaybackstatemachine;

import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.StateBuilder;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdEnabledVideoPlayer;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.LiveAdTrackingManager;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.ads.internal.state.ActiveState;
import com.amazon.avod.media.ads.internal.state.AdBreakErrorState;
import com.amazon.avod.media.ads.internal.state.AdClipState;
import com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState;
import com.amazon.avod.media.ads.internal.state.AdEnabledPlayerTriggerType;
import com.amazon.avod.media.ads.internal.state.AdPauseState;
import com.amazon.avod.media.ads.internal.state.ClientInsertedAdBreakState;
import com.amazon.avod.media.ads.internal.state.ClipErrorState;
import com.amazon.avod.media.ads.internal.state.PausedState;
import com.amazon.avod.media.ads.internal.state.PlayClipState;
import com.amazon.avod.media.ads.internal.state.PostPrimaryContentState;
import com.amazon.avod.media.ads.internal.state.PreparingClipState;
import com.amazon.avod.media.ads.internal.state.SeekState;
import com.amazon.avod.media.ads.internal.state.ShutdownState;
import com.amazon.avod.media.ads.internal.state.TransitionState;
import com.amazon.avod.media.diagnostics.DiagnosticsOverlayToggler;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.pmet.PmetAcquisitionEventListener;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.state.PlayerStateType;
import com.amazon.avod.media.playback.state.StandardPlayerStateType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.StandardPlayerTriggerType;
import com.amazon.avod.playback.session.PlaybackSession;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ClientInsertedAdEnabledPlaybackStateMachine extends AdEnabledPlaybackStateMachine {
    public ClientInsertedAdEnabledPlaybackStateMachine(@Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull AdBreakSelector adBreakSelector, @Nonnull VolumeManager volumeManager, @Nonnull AdsConfig adsConfig, @Nonnull DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull PlaybackSession playbackSession, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer) {
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
        this(advertisingIdCollector2, adPlaybackStateMachineContext2, timelineMonitor, adBreakSelector2, volumeManager2, adsConfig2, diagnosticsOverlayToggler2, playbackEventTransport, mediaEventReportersAd, playbackSession.getResources().getAdEventBus(), (VideoPlayerLifecyleEventHandler) Preconditions.checkNotNull(playbackSession.getResources().getVideoPlayerLifecyleEventHandler(), "videoPlayerLifecyleEventHandler"), adEnabledVideoPlayer, playbackSession.getResources().getPmetAcquisitionEventListener(), playbackSession.getResources().getContentManagementEventBus(), Boolean.valueOf(AloysiusConfig.getInstance().shouldUnifyMediaEventReporters()));
    }

    public ClientInsertedAdEnabledPlaybackStateMachine(@Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull TimelineMonitor timelineMonitor, @Nonnull AdBreakSelector adBreakSelector, @Nonnull VolumeManager volumeManager, @Nonnull AdsConfig adsConfig, @Nonnull DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull AdEventTransport adEventTransport, @Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer, @Nonnull PmetAcquisitionEventListener pmetAcquisitionEventListener, @Nonnull ContentManagementEventBus contentManagementEventBus, Boolean bool) {
        super(advertisingIdCollector, adPlaybackStateMachineContext, timelineMonitor, adBreakSelector, volumeManager, adsConfig, diagnosticsOverlayToggler, playbackEventTransport, playbackMediaEventReporters, adEventTransport, videoPlayerLifecyleEventHandler, adEnabledVideoPlayer, pmetAcquisitionEventListener, contentManagementEventBus, bool);
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
        CheckPrerollState checkPrerollState = new CheckPrerollState(this.mContext, this.mBreakSelector);
        PrimaryContentState primaryContentState = new PrimaryContentState(this.mContext, this.mPlaybackEventTransport, this.mPlaybackMediaEventReporters.getContentReporter());
        AdPlaybackStateMachineContext adPlaybackStateMachineContext = this.mContext;
        TimelineMonitor timelineMonitor = this.mTimelineMonitor;
        MonitoringPrimaryState monitoringPrimaryState = new MonitoringPrimaryState(adPlaybackStateMachineContext, timelineMonitor, this.mBreakSelector, new LiveAdTrackingManager(this.mPlaybackEventTransport, this.mAloysiusAdIdentifierReporter, timelineMonitor, adPlaybackStateMachineContext, this.mAdExecutor, this.mAdvertisingIdCollector, this.mEventBus));
        SeekState seekState = new SeekState(this.mContext, this.mBreakSelector);
        AdEnabledPlaybackState adEnabledPlaybackState3 = new AdEnabledPlaybackState(StandardPlayerStateType.ERROR, this.mContext);
        ClientInsertedAdBreakState clientInsertedAdBreakState = new ClientInsertedAdBreakState(this.mContext, this.mPlaybackEventTransport, this.mPlaybackMediaEventReporters, this.mTimelineMonitor);
        AdBreakErrorState adBreakErrorState = new AdBreakErrorState(this.mContext);
        AdClipState adClipState = new AdClipState(this.mContext, this.mDiagnosticsToggler, this.mAdEventTransport, this.mPlaybackMediaEventReporters);
        PlayClipState playClipState = new PlayClipState(this.mContext, this.mConfig, this.mTimelineMonitor, this.mVolumeManager, this.mPlaybackEventTransport);
        ClipErrorState clipErrorState = new ClipErrorState(this.mContext);
        PreparingClipState preparingClipState = new PreparingClipState(this.mContext, this.mConfig.getAdClipPrepareTimeout());
        PostPrimaryContentState postPrimaryContentState = new PostPrimaryContentState(this.mContext, this.mBreakSelector);
        AdEnabledPlaybackState adEnabledPlaybackState4 = new AdEnabledPlaybackState(StandardPlayerStateType.COMPLETED, this.mContext);
        PausedState pausedState = new PausedState(this.mContext);
        TransitionState transitionState = new TransitionState(this.mContext, TransitionState.TransitionType.TRANSITION_OUT_FROM_CLIP, this.mConfig.getAdTransitionOutTime());
        AdPlaybackStateMachineContext adPlaybackStateMachineContext2 = this.mContext;
        TransitionState.TransitionType transitionType = TransitionState.TransitionType.TRANSITION_IN;
        TransitionState transitionState2 = new TransitionState(adPlaybackStateMachineContext2, transitionType, this.mConfig.getAdTransitionInTime());
        TransitionState transitionState3 = new TransitionState(this.mContext, TransitionState.TransitionType.TRANSITION_OUT_FROM_PRIMARY, this.mConfig.getAdTransitionOutTime());
        TransitionState transitionState4 = new TransitionState(this.mContext, transitionType, this.mConfig.getAdTransitionInTime());
        AdPauseState adPauseState = new AdPauseState(this.mContext);
        setupState(shutdownState);
        setupState(adPauseState);
        setupState(activeState).registerTransition(StandardPlayerTriggerType.SHUTDOWN, shutdownState).registerTransition(StandardPlayerTriggerType.ERROR, adEnabledPlaybackState3);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilder = setupState(adEnabledPlaybackState, activeState);
        StandardPlayerTriggerType standardPlayerTriggerType = StandardPlayerTriggerType.PREPARED;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition = stateBuilder.registerTransition(standardPlayerTriggerType, adEnabledPlaybackState2);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType = AdEnabledPlayerTriggerType.PLAY_PRE_ROLLS;
        stateBuilderRegisterTransition.registerTransition(adEnabledPlayerTriggerType, checkPrerollState);
        setupState(adEnabledPlaybackState2, activeState).registerTransition(adEnabledPlayerTriggerType, checkPrerollState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilder2 = setupState(checkPrerollState, activeState);
        StandardPlayerTriggerType standardPlayerTriggerType2 = StandardPlayerTriggerType.PLAY;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition2 = stateBuilder2.registerTransition(standardPlayerTriggerType2, primaryContentState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType2 = AdEnabledPlayerTriggerType.BEGIN_AD_BREAK;
        stateBuilderRegisterTransition2.registerTransition(adEnabledPlayerTriggerType2, clientInsertedAdBreakState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilder3 = setupState(clientInsertedAdBreakState, activeState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType3 = AdEnabledPlayerTriggerType.PLAY_AD;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition3 = stateBuilder3.registerTransition(adEnabledPlayerTriggerType3, transitionState3).registerTransition(AdEnabledPlayerTriggerType.NO_MORE_ADS_SKIP_TRANSITION, primaryContentState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType4 = AdEnabledPlayerTriggerType.NO_MORE_ADS;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition4 = stateBuilderRegisterTransition3.registerTransition(adEnabledPlayerTriggerType4, transitionState4);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType5 = AdEnabledPlayerTriggerType.SKIP_CURRENT_AD_BREAK;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition5 = stateBuilderRegisterTransition4.registerTransition(adEnabledPlayerTriggerType5, transitionState4);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType6 = AdEnabledPlayerTriggerType.AD_CLIP_ERROR;
        stateBuilderRegisterTransition5.registerTransition(adEnabledPlayerTriggerType6, transitionState4);
        setupState(adBreakErrorState, clientInsertedAdBreakState);
        setupState(transitionState3, clientInsertedAdBreakState).registerTransition(adEnabledPlayerTriggerType3, adClipState);
        setupState(transitionState4, clientInsertedAdBreakState).registerTransition(adEnabledPlayerTriggerType4, primaryContentState).registerTransition(adEnabledPlayerTriggerType5, primaryContentState).registerTransition(adEnabledPlayerTriggerType6, primaryContentState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition6 = setupState(adClipState, clientInsertedAdBreakState).registerTransition(AdEnabledPlayerTriggerType.PREPARE_AD, preparingClipState);
        StandardPlayerTriggerType standardPlayerTriggerType3 = StandardPlayerTriggerType.COMPLETED;
        stateBuilderRegisterTransition6.registerTransition(standardPlayerTriggerType3, adClipState).registerTransition(adEnabledPlayerTriggerType5, transitionState4).registerTransition(adEnabledPlayerTriggerType6, clipErrorState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition7 = setupState(playClipState, adClipState).registerTransition(adEnabledPlayerTriggerType5, transitionState4).registerTransition(adEnabledPlayerTriggerType6, transitionState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType7 = AdEnabledPlayerTriggerType.SKIP_CURRENT_AD_CLIP;
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition8 = stateBuilderRegisterTransition7.registerTransition(adEnabledPlayerTriggerType7, transitionState).registerTransition(standardPlayerTriggerType3, transitionState);
        StandardPlayerTriggerType standardPlayerTriggerType4 = StandardPlayerTriggerType.PAUSE;
        stateBuilderRegisterTransition8.registerTransition(standardPlayerTriggerType4, adPauseState);
        setupState(transitionState, playClipState).registerTransition(standardPlayerTriggerType3, adClipState).registerTransition(adEnabledPlayerTriggerType5, transitionState4).registerTransition(adEnabledPlayerTriggerType7, adClipState).registerTransition(adEnabledPlayerTriggerType6, clipErrorState);
        setupState(preparingClipState, adClipState).registerTransition(standardPlayerTriggerType, transitionState2).registerTransition(adEnabledPlayerTriggerType5, transitionState4).registerTransition(adEnabledPlayerTriggerType6, clipErrorState);
        setupState(transitionState2, adClipState).registerTransition(standardPlayerTriggerType, playClipState);
        setupState(clipErrorState, adClipState).registerTransition(adEnabledPlayerTriggerType6, adBreakErrorState);
        StateBuilder<PlayerStateType, PlayerTriggerType> stateBuilderRegisterTransition9 = setupState(primaryContentState, activeState).registerTransition(adEnabledPlayerTriggerType2, clientInsertedAdBreakState);
        AdEnabledPlayerTriggerType adEnabledPlayerTriggerType8 = AdEnabledPlayerTriggerType.MONITOR_PRIMARY_CONTENT;
        stateBuilderRegisterTransition9.registerTransition(adEnabledPlayerTriggerType8, monitoringPrimaryState).registerTransition(StandardPlayerTriggerType.SEEK, seekState).registerTransition(standardPlayerTriggerType3, postPrimaryContentState);
        setupState(seekState, primaryContentState).registerTransition(adEnabledPlayerTriggerType8, monitoringPrimaryState).registerTransition(adEnabledPlayerTriggerType2, clientInsertedAdBreakState);
        setupState(monitoringPrimaryState, primaryContentState).registerTransition(standardPlayerTriggerType4, pausedState).registerTransition(standardPlayerTriggerType2, primaryContentState).registerTransition(AdEnabledPlayerTriggerType.AD_PLAN_READY, monitoringPrimaryState);
        setupState(pausedState, monitoringPrimaryState);
        setupState(postPrimaryContentState, activeState).registerTransition(standardPlayerTriggerType3, adEnabledPlaybackState4).registerTransition(adEnabledPlayerTriggerType2, clientInsertedAdBreakState);
        setupState(adEnabledPlaybackState3, activeState);
        setupState(adEnabledPlaybackState4, activeState);
        start(adEnabledPlaybackState);
    }
}
