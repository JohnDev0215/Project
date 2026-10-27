package com.amazon.mp3.platform.providers;

import com.amazon.mp3.platform.providers.MusicPlaybackCommandHandlers;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.platform.playback.commands.PlaybackCommand;
import com.amazon.music.platform.playback.commands.PlaybackControls;
import com.amazon.music.platform.providers.impl.PlaybackCommandImpl;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackControlsImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000f\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u0011\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0013\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/platform/providers/PlaybackControlsImpl;", "Lcom/amazon/music/platform/playback/commands/PlaybackControls;", "Lcom/amazon/music/media/playback/PlaybackController;", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "getPlaybackController", "()Lcom/amazon/music/media/playback/PlaybackController;", "Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers;", "musicPlaybackCommandHandlers", "Lcom/amazon/mp3/platform/providers/MusicPlaybackCommandHandlers;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommand;", "getPlay", "()Lcom/amazon/music/platform/playback/commands/PlaybackCommand;", PageUriUtils.DO_PLAY_VALUE, "getPause", "pause", "getPreviousItem", "previousItem", "getNextItem", "nextItem", "<init>", "(Lcom/amazon/music/media/playback/PlaybackController;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaybackControlsImpl implements PlaybackControls {
    private final MusicPlaybackCommandHandlers musicPlaybackCommandHandlers;
    private final PlaybackController playbackController;

    public PlaybackControlsImpl() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public PlaybackControlsImpl(PlaybackController playbackController) {
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        this.playbackController = playbackController;
        this.musicPlaybackCommandHandlers = new MusicPlaybackCommandHandlers();
    }

    public /* synthetic */ PlaybackControlsImpl(PlaybackController playbackController, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? PlaybackControllerProvider.INSTANCE.getController(ControlSource.NONE) : playbackController);
    }

    public final PlaybackController getPlaybackController() {
        return this.playbackController;
    }

    @Override // com.amazon.music.platform.playback.commands.PlaybackControls
    public PlaybackCommand getPlay() {
        final PlayStatus playStatus = this.playbackController.getPlayStatus();
        return new PlaybackCommandImpl(false, CollectionsKt__CollectionsKt.mutableListOf(new StatefulCommandHandler(CollectionsKt__CollectionsJVMKt.listOf(new Pair(new Function0<Boolean>() { // from class: com.amazon.mp3.platform.providers.PlaybackControlsImpl$play$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                PlayStatus playStatus2 = playStatus;
                return Boolean.valueOf(playStatus2 == PlayStatus.USER_PAUSED || playStatus2 == PlayStatus.SYSTEM_PAUSED);
            }
        }, new MusicPlaybackCommandHandlers.MusicPlayCommandHandler(this.musicPlaybackCommandHandlers))))));
    }

    @Override // com.amazon.music.platform.playback.commands.PlaybackControls
    public PlaybackCommand getPause() {
        final PlayStatus playStatus = this.playbackController.getPlayStatus();
        return new PlaybackCommandImpl(false, CollectionsKt__CollectionsKt.mutableListOf(new StatefulCommandHandler(CollectionsKt__CollectionsJVMKt.listOf(new Pair(new Function0<Boolean>() { // from class: com.amazon.mp3.platform.providers.PlaybackControlsImpl$pause$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                PlayStatus playStatus2 = playStatus;
                return Boolean.valueOf(playStatus2 == PlayStatus.RENDERING || playStatus2 == PlayStatus.BUFFERING || playStatus2 == PlayStatus.PREPARING);
            }
        }, new MusicPlaybackCommandHandlers.MusicPauseCommandHandler(this.musicPlaybackCommandHandlers))))));
    }

    @Override // com.amazon.music.platform.playback.commands.PlaybackControls
    public PlaybackCommand getPreviousItem() {
        return new PlaybackCommandImpl(false, CollectionsKt__CollectionsKt.mutableListOf(new StatefulCommandHandler(CollectionsKt__CollectionsJVMKt.listOf(new Pair(new Function0<Boolean>() { // from class: com.amazon.mp3.platform.providers.PlaybackControlsImpl$previousItem$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(this.this$0.getPlaybackController().canSkipPrevious());
            }
        }, new MusicPlaybackCommandHandlers.MusicPreviousCommandHandler(this.musicPlaybackCommandHandlers))))));
    }

    @Override // com.amazon.music.platform.playback.commands.PlaybackControls
    public PlaybackCommand getNextItem() {
        return new PlaybackCommandImpl(false, CollectionsKt__CollectionsKt.mutableListOf(new StatefulCommandHandler(CollectionsKt__CollectionsJVMKt.listOf(new Pair(new Function0<Boolean>() { // from class: com.amazon.mp3.platform.providers.PlaybackControlsImpl$nextItem$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(this.this$0.getPlaybackController().canSkipNext());
            }
        }, new MusicPlaybackCommandHandlers.MusicNextCommandHandler(this.musicPlaybackCommandHandlers))))));
    }
}
