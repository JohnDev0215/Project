package com.amazon.mp3.view.player;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.PlaybackActionCapability;
import com.amazon.mp3.cloudqueue.sequencer.PlaybackActionCapabilityQueryable;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.platform.data.entity.EntityItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerViewEnabledStateManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0016\u001a\u00020\u0006H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;", "", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "(Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;)V", "controlsAreEnabled", "", "getControlsAreEnabled", "()Z", "setControlsAreEnabled", "(Z)V", "viewConfig", "Lcom/amazon/mp3/view/player/NowPlayingViewConfig;", "getActionCapability", "Lcom/amazon/mp3/cloudqueue/PlaybackActionCapability;", "action", "", "getEnabledViewState", "Lcom/amazon/mp3/view/player/EnabledViewState;", "playerAction", "Lcom/amazon/mp3/view/player/PlayerViewAction;", "isSupported", "isThumbActionStatusEnabled", "updateConfigForMediaItem", "", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayerViewEnabledStateManager {
    private static final String TAG = PlayerViewEnabledStateManager.class.getSimpleName();
    private boolean controlsAreEnabled;
    private final ActionValidatedPlaybackController playbackController;
    private NowPlayingViewConfig viewConfig;

    /* JADX INFO: compiled from: PlayerViewEnabledStateManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayerViewAction.values().length];
            iArr[PlayerViewAction.ALEXA.ordinal()] = 1;
            iArr[PlayerViewAction.NEXT.ordinal()] = 2;
            iArr[PlayerViewAction.PLAY.ordinal()] = 3;
            iArr[PlayerViewAction.PAUSE.ordinal()] = 4;
            iArr[PlayerViewAction.PREVIOUS.ordinal()] = 5;
            iArr[PlayerViewAction.RATE.ordinal()] = 6;
            iArr[PlayerViewAction.SHUFFLE.ordinal()] = 7;
            iArr[PlayerViewAction.PLAY_QUEUE.ordinal()] = 8;
            iArr[PlayerViewAction.LIKE.ordinal()] = 9;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PlayerViewEnabledStateManager(ActionValidatedPlaybackController playbackController) {
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        this.playbackController = playbackController;
        this.viewConfig = NowPlayingViewConfig.INSTANCE.fromMediaItem(playbackController.getController().getCurrentMediaItem());
    }

    public final boolean getControlsAreEnabled() {
        return this.controlsAreEnabled;
    }

    public final void setControlsAreEnabled(boolean z) {
        this.controlsAreEnabled = z;
    }

    public final boolean isSupported(PlayerViewAction playerAction) {
        Intrinsics.checkNotNullParameter(playerAction, "playerAction");
        return EnabledViewState.GONE != getEnabledViewState(playerAction);
    }

    public final void updateConfigForMediaItem(MediaItem mediaItem) {
        if (this.viewConfig.equalsPlayerContentType(mediaItem)) {
            return;
        }
        this.viewConfig = NowPlayingViewConfig.INSTANCE.fromMediaItem(mediaItem);
    }

    public final EnabledViewState getEnabledViewState(PlayerViewAction playerAction) {
        EnabledViewState enabledViewState;
        Intrinsics.checkNotNullParameter(playerAction, "playerAction");
        EnabledViewState enabledState = this.viewConfig.getEnabledState(playerAction);
        EnabledViewState enabledViewState2 = EnabledViewState.GONE;
        if (enabledViewState2 == enabledState || (enabledViewState = EnabledViewState.DISABLED) == enabledState) {
            return enabledState;
        }
        if (!this.controlsAreEnabled || this.playbackController.getCurrentMediaItem() == null) {
            return enabledViewState;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[playerAction.ordinal()]) {
            case 1:
                return AmazonApplication.getCapabilities().isAlexaEnabled() ? EnabledViewState.ENABLED : enabledViewState2;
            case 2:
                if (this.playbackController.canDisplayAction("NEXT")) {
                    return EnabledViewState.ENABLED;
                }
                break;
            case 3:
            case 4:
                if (this.playbackController.canPlay() || this.playbackController.getCurrentMediaItem() != null) {
                    return EnabledViewState.ENABLED;
                }
                break;
            case 5:
                if (this.playbackController.canDisplayAction("PREVIOUS")) {
                    return EnabledViewState.ENABLED;
                }
                break;
            case 6:
                return (this.playbackController.canDisplayAction("RATE_TRACK") && isThumbActionStatusEnabled()) ? EnabledViewState.ENABLED : enabledViewState2;
            case 7:
                if (this.playbackController.canDisplayAction("SHUFFLE")) {
                    return EnabledViewState.ENABLED;
                }
                break;
            case 8:
                return (Feature.display_queue_view.isEnabled() && CastingUtil.isPlayQueueSupported()) ? EnabledViewState.ENABLED : enabledViewState2;
            case 9:
                return EnabledViewState.ENABLED;
            default:
                return EnabledViewState.ENABLED;
        }
        return enabledViewState;
    }

    private final PlaybackActionCapability getActionCapability(String action) {
        EntityItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem instanceof PlaybackActionCapabilityQueryable) {
            return ((PlaybackActionCapabilityQueryable) currentMediaItem).getPlaybackActionCapability(action);
        }
        return null;
    }

    private final boolean isThumbActionStatusEnabled() {
        PlaybackActionCapability actionCapability = getActionCapability("RATE_TRACK");
        return actionCapability == null || actionCapability.getActionStatus() == 0;
    }
}
