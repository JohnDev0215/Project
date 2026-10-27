package com.amazon.mp3.view.stage;

import android.content.Context;
import android.os.Handler;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleObserver;
import com.amazon.mp3.cloudqueue.PlaybackActionCapability;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.InterludeMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.PlaybackActionCapabilityQueryable;
import com.amazon.mp3.dialog.fragment.HawkfireAllDevicesUpsellDialogFragment;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.platform.providers.DefaultDispatcherProvider;
import com.amazon.mp3.platform.providers.DispatcherProvider;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.ActionResult;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.mp3.view.player.PlayerViewEnabledStateManager;
import com.amazon.mp3.voice.NowPlayingVoiceAccessProvider;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.voice.AlexaEntryPoint;
import com.amazon.music.voice.VoiceManagerSingleton;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: StageViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 c2\u00020\u0001:\u0001cB\u0019\u0012\u0006\u0010+\u001a\u00020*\u0012\b\b\u0002\u0010`\u001a\u00020_¢\u0006\u0004\ba\u0010bJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u001c\u0010\n\u001a\u00020\t2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u0002H\u0002J\b\u0010\u0013\u001a\u00020\u0002H\u0002J\u0006\u0010\u0014\u001a\u00020\u0002J\u0006\u0010\u0015\u001a\u00020\u0002J\u0006\u0010\u0016\u001a\u00020\u0002J\u0006\u0010\u0017\u001a\u00020\u0002J\u0006\u0010\u0018\u001a\u00020\u0002J\u0006\u0010\u0019\u001a\u00020\u0002J\u0010\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u001aJ\u0010\u0010\u001d\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u001aJ\u0006\u0010\u0006\u001a\u00020\u0002J\u0010\u0010 \u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eJ\u0006\u0010!\u001a\u00020\tJ\u0006\u0010\"\u001a\u00020\u0002J\u001a\u0010%\u001a\u00020\u00022\b\u0010$\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010\u001b\u001a\u00020\u001aJ\u0006\u0010&\u001a\u00020\u0002J\u0006\u0010'\u001a\u00020\u0002J\u0006\u0010(\u001a\u00020\u0002J\u0006\u0010)\u001a\u00020\tR\u0017\u0010+\u001a\u00020*8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u0016\u0010/\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u001c\u00102\u001a\n 1*\u0004\u0018\u00010\u000b0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u0018\u0010:\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R\u0018\u0010<\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010;R$\u0010>\u001a\u0004\u0018\u00010=8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\"\u0010E\u001a\u00020D8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bE\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR$\u0010L\u001a\u0004\u0018\u00010K8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR\"\u0010V\u001a\u00020U8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\bV\u0010W\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u0014\u0010]\u001a\u00020\\8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010^¨\u0006d"}, d2 = {"Lcom/amazon/mp3/view/stage/StageViewModel;", "Landroidx/lifecycle/LifecycleObserver;", "", "updatePlayerControls", "Lcom/amazon/music/media/playback/PlayStatus;", "playStatus", "updatePlaybackButton", "status1", "status2", "", "isEquivalentPlayStatus", "", "action", "Lcom/amazon/mp3/cloudqueue/PlaybackActionCapability;", "getActionCapability", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellReason;", "upsellReason", "displayBlockingUpsell", "refreshBottomButtonsVisibility", "resetOverlayState", "onPlayStatusChanged", "onMediaItemChanged", "onCreate", "onDestroy", "updateStageOverflowMenuSecondaryIcon", "onPlayPauseButtonClick", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interactionType", "onNextButtonClick", "onPrevButtonClick", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "entityEligibilitiesProvider", "isPrevButtonEnabled", "isNextButtonEnabled", "resetAndUpdatePlaybackState", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "sendUiClickEvent", "updateLikeButton", "initializeAlexa", "onAlexaButtonClick", "isAdOrInterLude", "Lcom/amazon/mp3/view/stage/StageViewPresenter;", "stageViewPresenter", "Lcom/amazon/mp3/view/stage/StageViewPresenter;", "getStageViewPresenter", "()Lcom/amazon/mp3/view/stage/StageViewPresenter;", "prevButtonEnabled", "Z", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Landroid/os/Handler;", "handler", "Landroid/os/Handler;", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "playbackEventListener", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "currentStatus", "Lcom/amazon/music/media/playback/PlayStatus;", "pendingStatus", "Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;", "enabledStateManager", "Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;", "getEnabledStateManager", "()Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;", "setEnabledStateManager", "(Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;)V", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "getPlaybackController", "()Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "setPlaybackController", "(Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;)V", "Lcom/amazon/mp3/view/stage/StageDataModel;", "stageDataModel", "Lcom/amazon/mp3/view/stage/StageDataModel;", "getStageDataModel", "()Lcom/amazon/mp3/view/stage/StageDataModel;", "setStageDataModel", "(Lcom/amazon/mp3/view/stage/StageDataModel;)V", "Ljava/lang/Runnable;", "buttonUpdateRunnable", "Ljava/lang/Runnable;", "Lcom/amazon/mp3/voice/NowPlayingVoiceAccessProvider;", "voiceAccessProvider", "Lcom/amazon/mp3/voice/NowPlayingVoiceAccessProvider;", "getVoiceAccessProvider", "()Lcom/amazon/mp3/voice/NowPlayingVoiceAccessProvider;", "setVoiceAccessProvider", "(Lcom/amazon/mp3/voice/NowPlayingVoiceAccessProvider;)V", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/mp3/platform/providers/DispatcherProvider;", "dispatchers", "<init>", "(Lcom/amazon/mp3/view/stage/StageViewPresenter;Lcom/amazon/mp3/platform/providers/DispatcherProvider;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageViewModel implements LifecycleObserver {
    private static HashMap<String, String> artistImageMap = new HashMap<>();
    private final String TAG;
    private final Runnable buttonUpdateRunnable;
    private final CoroutineScope coroutineScope;
    private PlayStatus currentStatus;
    private PlayerViewEnabledStateManager enabledStateManager;
    private final Handler handler;
    private PlayStatus pendingStatus;
    private ActionValidatedPlaybackController playbackController;
    private final OnPlaybackEventListener playbackEventListener;
    private boolean prevButtonEnabled;
    private StageDataModel stageDataModel;
    private final StageViewPresenter stageViewPresenter;
    public NowPlayingVoiceAccessProvider voiceAccessProvider;

    public StageViewModel(StageViewPresenter stageViewPresenter, DispatcherProvider dispatchers) {
        Intrinsics.checkNotNullParameter(stageViewPresenter, "stageViewPresenter");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        this.stageViewPresenter = stageViewPresenter;
        this.TAG = StageViewModel.class.getSimpleName();
        this.handler = new Handler();
        this.playbackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.view.stage.StageViewModel$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public final void onPlaybackEvent(int i, ControlSource controlSource) {
                StageViewModel.m1754playbackEventListener$lambda0(this.f$0, i, controlSource);
            }
        };
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.stageDataModel = new StageDataModel(false, false, false, null, null, null, null, null, null, null, 1023, null);
        this.buttonUpdateRunnable = new Runnable() { // from class: com.amazon.mp3.view.stage.StageViewModel$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                StageViewModel.m1753buttonUpdateRunnable$lambda2(this.f$0);
            }
        };
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(dispatchers.io());
    }

    public /* synthetic */ StageViewModel(StageViewPresenter stageViewPresenter, DispatcherProvider dispatcherProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(stageViewPresenter, (i & 2) != 0 ? DefaultDispatcherProvider.INSTANCE : dispatcherProvider);
    }

    public final StageViewPresenter getStageViewPresenter() {
        return this.stageViewPresenter;
    }

    public final void onPlayStatusChanged() {
        updatePlaybackButton();
        updatePlayerControls();
    }

    public final void onMediaItemChanged() {
        StageDataModel stageDataModel = this.stageDataModel;
        if (stageDataModel != null) {
            stageDataModel.reset();
        }
        if (!NowPlayingUtil.isVideoPlaying()) {
            resetOverlayState();
        }
        PlayerViewEnabledStateManager playerViewEnabledStateManager = this.enabledStateManager;
        if (playerViewEnabledStateManager != null) {
            playerViewEnabledStateManager.updateConfigForMediaItem(this.playbackController.getCurrentMediaItem());
        }
        this.stageViewPresenter.refreshBottomButtons();
        this.stageViewPresenter.updateOverflowMenuButtonSecondaryIcon();
        updateLikeButton();
        refreshBottomButtonsVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playbackEventListener$lambda-0, reason: not valid java name */
    public static final void m1754playbackEventListener$lambda0(StageViewModel this$0, int i, ControlSource noName_1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        switch (i) {
            case 24:
                this$0.stageViewPresenter.getStageOverflowMenuSecondaryIconStateProvider().setStageOverflowMenuSecondaryIconState(StageOverflowMenuSecondaryIconState.SHUFFLE);
                this$0.stageViewPresenter.updateOverflowMenuButtonSecondaryIcon();
                break;
            case 25:
                this$0.updateStageOverflowMenuSecondaryIcon();
                break;
            case 26:
                this$0.updateStageOverflowMenuSecondaryIcon();
                break;
        }
    }

    public final PlayerViewEnabledStateManager getEnabledStateManager() {
        return this.enabledStateManager;
    }

    public final StageDataModel getStageDataModel() {
        return this.stageDataModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: buttonUpdateRunnable$lambda-2, reason: not valid java name */
    public static final void m1753buttonUpdateRunnable$lambda2(StageViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlayStatus playStatus = this$0.pendingStatus;
        if (playStatus == null) {
            return;
        }
        this$0.updatePlaybackButton(playStatus);
    }

    public final NowPlayingVoiceAccessProvider getVoiceAccessProvider() {
        NowPlayingVoiceAccessProvider nowPlayingVoiceAccessProvider = this.voiceAccessProvider;
        if (nowPlayingVoiceAccessProvider != null) {
            return nowPlayingVoiceAccessProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("voiceAccessProvider");
        return null;
    }

    public final void setVoiceAccessProvider(NowPlayingVoiceAccessProvider nowPlayingVoiceAccessProvider) {
        Intrinsics.checkNotNullParameter(nowPlayingVoiceAccessProvider, "<set-?>");
        this.voiceAccessProvider = nowPlayingVoiceAccessProvider;
    }

    public final void onCreate() {
        Log.debug(this.TAG, "onCreate()");
        PlayerViewEnabledStateManager playerViewEnabledStateManager = new PlayerViewEnabledStateManager(this.playbackController);
        this.enabledStateManager = playerViewEnabledStateManager;
        playerViewEnabledStateManager.setControlsAreEnabled(true);
        this.playbackController.addOnPlaybackEventListener(this.playbackEventListener);
        this.stageViewPresenter.updateOverflowMenuButtonSecondaryIcon();
    }

    public final void onDestroy() {
        Log.debug(this.TAG, "onDestroy()");
        this.playbackController.removeOnPlaybackEventListener(this.playbackEventListener);
        CoroutineScopeKt.cancel$default(this.coroutineScope, null, 1, null);
        getVoiceAccessProvider().onDestroy();
        this.stageDataModel = null;
    }

    public final void updateStageOverflowMenuSecondaryIcon() {
        if (this.playbackController.getRepeatMode() == RepeatMode.REPEAT_ONE) {
            this.stageViewPresenter.getStageOverflowMenuSecondaryIconStateProvider().setStageOverflowMenuSecondaryIconState(StageOverflowMenuSecondaryIconState.REPEAT_ONE);
        } else if (this.playbackController.getRepeatMode() == RepeatMode.REPEAT_ALL) {
            this.stageViewPresenter.getStageOverflowMenuSecondaryIconStateProvider().setStageOverflowMenuSecondaryIconState(StageOverflowMenuSecondaryIconState.REPEAT_ALL);
        } else if (this.playbackController.isShuffled()) {
            this.stageViewPresenter.getStageOverflowMenuSecondaryIconStateProvider().setStageOverflowMenuSecondaryIconState(StageOverflowMenuSecondaryIconState.SHUFFLE);
        } else {
            this.stageViewPresenter.getStageOverflowMenuSecondaryIconStateProvider().setStageOverflowMenuSecondaryIconState(StageOverflowMenuSecondaryIconState.NONE);
        }
        this.stageViewPresenter.updateOverflowMenuButtonSecondaryIcon();
    }

    public final void onPlayPauseButtonClick() {
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        this.playbackController.togglePlaying();
        sendUiClickEvent$default(this, this.playbackController.getPlayStatus().willPlay() ? ActionType.PLAY : ActionType.PAUSE, null, 2, null);
    }

    public static /* synthetic */ void onNextButtonClick$default(StageViewModel stageViewModel, InteractionType interactionType, int i, Object obj) {
        if ((i & 1) != 0) {
            interactionType = InteractionType.TAP;
        }
        stageViewModel.onNextButtonClick(interactionType);
    }

    public final void onNextButtonClick(InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        sendUiClickEvent(ActionType.SKIP_NEXT, interactionType);
        ActionResult actionResultSkipNextForResult = this.playbackController.skipNextForResult();
        this.stageViewPresenter.getOnNextClickedSubject().onNext(Boolean.TRUE);
        if (actionResultSkipNextForResult == ActionResult.OK || UserSubscriptionUtil.getUserSubscription().isHawkfireAllDevices()) {
            return;
        }
        displayBlockingUpsell(UpsellReason.TRACK_SKIP_LIMIT);
    }

    public static /* synthetic */ void onPrevButtonClick$default(StageViewModel stageViewModel, InteractionType interactionType, int i, Object obj) {
        if ((i & 1) != 0) {
            interactionType = InteractionType.TAP;
        }
        stageViewModel.onPrevButtonClick(interactionType);
    }

    public final void onPrevButtonClick(InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        sendUiClickEvent(ActionType.SKIP_PREVIOUS, interactionType);
        PlaybackActionCapability actionCapability = getActionCapability("PREVIOUS");
        if (this.prevButtonEnabled && (actionCapability == null || actionCapability.getActionStatus() == 0)) {
            this.playbackController.previousOrRestart();
        } else {
            if (UserSubscriptionUtil.getUserSubscription().isHawkfireAllDevices()) {
                return;
            }
            displayBlockingUpsell(UpsellReason.TRACK_PREVIOUS);
        }
    }

    private final void updatePlayerControls() {
        PlayerViewEnabledStateManager playerViewEnabledStateManager = this.enabledStateManager;
        if (playerViewEnabledStateManager != null) {
            playerViewEnabledStateManager.updateConfigForMediaItem(this.playbackController.getCurrentMediaItem());
        }
        this.stageViewPresenter.updatePlayerControls(this.enabledStateManager);
    }

    private final void updatePlaybackButton(PlayStatus playStatus) {
        this.currentStatus = playStatus;
        this.pendingStatus = null;
        if (playStatus.isDelayed()) {
            this.stageViewPresenter.setLoadingImage();
        } else {
            this.stageViewPresenter.setPlayButtonImage(playStatus.willPlay());
        }
    }

    public final void updatePlaybackButton() {
        PlayStatus status = this.playbackController.getPlayStatus();
        PlayStatus immediateStatus = status.isDelayed() ? PlayStatus.RENDERING : status;
        if (isEquivalentPlayStatus(this.currentStatus, status)) {
            this.pendingStatus = null;
            this.handler.removeCallbacks(this.buttonUpdateRunnable);
            return;
        }
        if (isEquivalentPlayStatus(this.pendingStatus, status)) {
            return;
        }
        PlayStatus playStatus = this.currentStatus;
        if (playStatus == null || status == immediateStatus) {
            this.handler.removeCallbacks(this.buttonUpdateRunnable);
            Intrinsics.checkNotNullExpressionValue(status, "status");
            updatePlaybackButton(status);
        } else {
            if (!isEquivalentPlayStatus(playStatus, immediateStatus)) {
                Intrinsics.checkNotNullExpressionValue(immediateStatus, "immediateStatus");
                updatePlaybackButton(immediateStatus);
            }
            this.pendingStatus = status;
            this.handler.removeCallbacks(this.buttonUpdateRunnable);
            this.handler.postDelayed(this.buttonUpdateRunnable, 250L);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isPrevButtonEnabled(com.amazon.music.platform.providers.EntityEligibilitiesProvider r5) {
        /*
            r4 = this;
            com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController r0 = r4.playbackController
            com.amazon.music.media.playback.MediaItem r0 = r0.getCurrentMediaItem()
            com.amazon.mp3.featuregate.Feature r1 = com.amazon.mp3.featuregate.Feature.prev_button_enabled
            boolean r1 = r1.isEnabled()
            r2 = 1
            if (r5 == 0) goto L19
            com.amazon.mp3.util.EligibilityUtil r1 = new com.amazon.mp3.util.EligibilityUtil
            r3 = 0
            r1.<init>(r3, r2, r3)
            boolean r1 = r1.getPrevButtonEnabled(r5)
        L19:
            com.amazon.music.subscription.UserSubscription r5 = com.amazon.mp3.util.UserSubscriptionUtil.getUserSubscription()
            boolean r5 = r5.isNightwingOnly()
            r3 = 0
            if (r5 != 0) goto L41
            com.amazon.music.subscription.UserSubscription r5 = com.amazon.mp3.util.UserSubscriptionUtil.getUserSubscription()
            boolean r5 = r5.isSonicRush()
            if (r5 != 0) goto L41
            if (r1 != 0) goto L40
            if (r0 != 0) goto L34
        L32:
            r5 = r3
            goto L3b
        L34:
            boolean r5 = r0.isInterlude()
            if (r5 != r2) goto L32
            r5 = r2
        L3b:
            if (r5 == 0) goto L3e
            goto L40
        L3e:
            r1 = r3
            goto L41
        L40:
            r1 = r2
        L41:
            if (r1 != 0) goto L52
            if (r0 != 0) goto L47
        L45:
            r5 = r3
            goto L4e
        L47:
            boolean r5 = r0.isBOP()
            if (r5 != r2) goto L45
            r5 = r2
        L4e:
            if (r5 == 0) goto L51
            goto L52
        L51:
            r2 = r3
        L52:
            r4.prevButtonEnabled = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.stage.StageViewModel.isPrevButtonEnabled(com.amazon.music.platform.providers.EntityEligibilitiesProvider):boolean");
    }

    public final boolean isNextButtonEnabled() {
        return this.playbackController.getSkipNextAvailability() != ActionAvailability.UNAVAILABLE_SKIP_LIMIT;
    }

    public final void resetAndUpdatePlaybackState() {
        this.currentStatus = null;
        this.pendingStatus = null;
        updatePlaybackButton();
    }

    private final boolean isEquivalentPlayStatus(PlayStatus status1, PlayStatus status2) {
        if (status1 == null || status2 == null || status1 == status2) {
            if (status1 == status2) {
                return true;
            }
        } else if (status1.willPlay() == status2.willPlay() && status1.isDelayed() == status2.isDelayed()) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ void sendUiClickEvent$default(StageViewModel stageViewModel, ActionType actionType, InteractionType interactionType, int i, Object obj) {
        if ((i & 2) != 0) {
            interactionType = InteractionType.TAP;
        }
        stageViewModel.sendUiClickEvent(actionType, interactionType);
    }

    public final void sendUiClickEvent(ActionType actionType, InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(interactionType).withPageType(PageType.NOW_PLAYING_STAGE).withEventTime(System.currentTimeMillis()).withEntityType(NowPlayingUtil.isVideoPlaying() ? EntityType.VIDEO : EntityType.TRACK).build());
    }

    private final PlaybackActionCapability getActionCapability(String action) {
        EntityItem currentMediaItem = this.playbackController.getController().getCurrentMediaItem();
        PlaybackActionCapabilityQueryable playbackActionCapabilityQueryable = currentMediaItem instanceof PlaybackActionCapabilityQueryable ? (PlaybackActionCapabilityQueryable) currentMediaItem : null;
        if (playbackActionCapabilityQueryable == null) {
            return null;
        }
        return playbackActionCapabilityQueryable.getPlaybackActionCapability(action);
    }

    private final void displayBlockingUpsell(UpsellReason upsellReason) {
        if (UserSubscriptionUtil.getUserSubscription().isHomeOnly()) {
            StageViewPresenter stageViewPresenter = this.stageViewPresenter;
            Context context = stageViewPresenter.getContext();
            if (context == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            }
            stageViewPresenter.showDialog((FragmentActivity) context, new HawkfireAllDevicesUpsellDialogFragment());
            return;
        }
        MediaItem currentMediaItem = this.playbackController.getController().getCurrentMediaItem();
        if (currentMediaItem == null) {
            return;
        }
        UpsellUtil.raiseBlockingUpsell(getStageViewPresenter().getContext(), UpsellUtil.getAsinFromMediaItem(currentMediaItem), upsellReason);
    }

    private final void refreshBottomButtonsVisibility() {
        this.stageViewPresenter.refreshBottomButtonsVisibility();
    }

    public final void updateLikeButton() {
        ActionValidatedPlaybackController actionValidatedPlaybackController = this.playbackController;
        if (actionValidatedPlaybackController == null) {
            this.stageViewPresenter.showLikeButton(false);
            return;
        }
        MediaItem currentMediaItem = actionValidatedPlaybackController.getCurrentMediaItem();
        if (!LikesServiceProxy.INSTANCE.shouldShowLikesFeature(currentMediaItem)) {
            this.stageViewPresenter.showLikeButton(false);
            return;
        }
        boolean z = currentMediaItem.getLikeState() == LikeState.LIKE;
        boolean zHasAnyInternetConnection = ConnectivityUtil.hasAnyInternetConnection();
        this.stageViewPresenter.setLikeButtonState(z);
        this.stageViewPresenter.showLikeButton(true);
        this.stageViewPresenter.setLikeButtonEnabledState(zHasAnyInternetConnection);
    }

    private final void resetOverlayState() {
        this.stageViewPresenter.resetOverlayState();
    }

    public final void initializeAlexa() {
        Context context = this.stageViewPresenter.getContext();
        if (context != null) {
            setVoiceAccessProvider(new NowPlayingVoiceAccessProvider((FragmentActivity) context));
            getVoiceAccessProvider().initAlexa();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
    }

    public final void onAlexaButtonClick() {
        if (VoiceManagerSingleton.isInitialized()) {
            NowPlayingVoiceAccessProvider voiceAccessProvider = getVoiceAccessProvider();
            MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
            boolean z = false;
            if (currentMediaItem != null && MediaItemExtensions.isVideo(currentMediaItem)) {
                z = true;
            }
            voiceAccessProvider.onAlexaClicked(z ? AlexaEntryPoint.NOW_PLAYING_VIDEO : AlexaEntryPoint.NOW_PLAYING_AUDIO);
        }
    }

    public final boolean isAdOrInterLude() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            return false;
        }
        return (currentMediaItem instanceof AdMediaItem) || (currentMediaItem instanceof InterludeMediaItem);
    }
}
