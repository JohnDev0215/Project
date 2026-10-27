package com.amazon.mp3.playback.playbackcontrol;

import com.amazon.mp3.playback.playbackcontrol.impl.ActionValidatedPlaybackControllerImpl;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackControllerProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/playback/playbackcontrol/PlaybackControllerProvider;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaybackControllerProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final ActionValidatedPlaybackController getController(ControlSource controlSource) {
        return INSTANCE.getController(controlSource);
    }

    /* JADX INFO: compiled from: PlaybackControllerProvider.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/playback/playbackcontrol/PlaybackControllerProvider$Companion;", "", "()V", "getController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "controlSource", "Lcom/amazon/music/media/playback/ControlSource;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ActionValidatedPlaybackController getController(ControlSource controlSource) {
            PlaybackController playbackController = Playback.getInstance().getPlaybackController(controlSource);
            Intrinsics.checkNotNullExpressionValue(playbackController, "getInstance().getPlaybackController(controlSource)");
            return new ActionValidatedPlaybackControllerImpl(playbackController);
        }
    }
}
