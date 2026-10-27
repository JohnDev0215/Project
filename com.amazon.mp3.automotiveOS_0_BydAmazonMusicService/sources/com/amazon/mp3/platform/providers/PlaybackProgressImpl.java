package com.amazon.mp3.platform.providers;

import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.platform.playback.state.PlaybackProgress;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackProgressImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\f\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/platform/providers/PlaybackProgressImpl;", "Lcom/amazon/music/platform/playback/state/PlaybackProgress;", "Lcom/amazon/music/media/playback/PlaybackController;", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "getPlaybackController", "()Lcom/amazon/music/media/playback/PlaybackController;", "", "getProgressInMS", "()F", "progressInMS", "getBufferedPercentage", "bufferedPercentage", "<init>", "(Lcom/amazon/music/media/playback/PlaybackController;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaybackProgressImpl implements PlaybackProgress {
    private final PlaybackController playbackController;

    public PlaybackProgressImpl(PlaybackController playbackController) {
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        this.playbackController = playbackController;
    }

    @Override // com.amazon.music.platform.playback.state.PlaybackProgress
    public float getProgressInMS() {
        return this.playbackController.getPositionMillis();
    }

    @Override // com.amazon.music.platform.playback.state.PlaybackProgress
    public float getBufferedPercentage() {
        return this.playbackController.getAmountBuffered();
    }
}
