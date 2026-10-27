package com.amazon.mp3.platform.providers;

import androidx.core.app.NotificationCompat;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvent;
import com.amazon.music.platform.playback.commands.PlaybackCommandHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicPlaybackCommandHandlers.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u0005¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers;", "", "()V", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "getPlaybackController", "()Lcom/amazon/music/media/playback/PlaybackController;", "MusicNextCommandHandler", "MusicPauseCommandHandler", "MusicPlayCommandHandler", "MusicPreviousCommandHandler", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MusicPlaybackCommandHandlers {
    private final PlaybackController playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.NONE);

    public final PlaybackController getPlaybackController() {
        return this.playbackController;
    }

    /* JADX INFO: compiled from: MusicPlaybackCommandHandlers.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers$MusicPlayCommandHandler;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandHandler;", "(Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers;)V", "handle", "", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MusicPlayCommandHandler implements PlaybackCommandHandler {
        final /* synthetic */ MusicPlaybackCommandHandlers this$0;

        public MusicPlayCommandHandler(MusicPlaybackCommandHandlers this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.music.platform.playback.commands.PlaybackCommandHandler
        public void handle(PlaybackCommandEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            this.this$0.getPlaybackController().play();
        }
    }

    /* JADX INFO: compiled from: MusicPlaybackCommandHandlers.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers$MusicPauseCommandHandler;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandHandler;", "(Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers;)V", "handle", "", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MusicPauseCommandHandler implements PlaybackCommandHandler {
        final /* synthetic */ MusicPlaybackCommandHandlers this$0;

        public MusicPauseCommandHandler(MusicPlaybackCommandHandlers this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.music.platform.playback.commands.PlaybackCommandHandler
        public void handle(PlaybackCommandEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            this.this$0.getPlaybackController().pause();
        }
    }

    /* JADX INFO: compiled from: MusicPlaybackCommandHandlers.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers$MusicNextCommandHandler;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandHandler;", "(Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers;)V", "handle", "", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MusicNextCommandHandler implements PlaybackCommandHandler {
        final /* synthetic */ MusicPlaybackCommandHandlers this$0;

        public MusicNextCommandHandler(MusicPlaybackCommandHandlers this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.music.platform.playback.commands.PlaybackCommandHandler
        public void handle(PlaybackCommandEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            this.this$0.getPlaybackController().skipNext();
        }
    }

    /* JADX INFO: compiled from: MusicPlaybackCommandHandlers.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers$MusicPreviousCommandHandler;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandHandler;", "(Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers;)V", "handle", "", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MusicPreviousCommandHandler implements PlaybackCommandHandler {
        final /* synthetic */ MusicPlaybackCommandHandlers this$0;

        public MusicPreviousCommandHandler(MusicPlaybackCommandHandlers this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.music.platform.playback.commands.PlaybackCommandHandler
        public void handle(PlaybackCommandEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            this.this$0.getPlaybackController().skipPrevious();
        }
    }
}
