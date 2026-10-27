package com.amazon.mp3.playback;

import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0010\b\u0002\u0010\u0006\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005H&¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/playback/PlaybackListener;", "", "Lcom/amazon/mp3/playback/PlaybackError;", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "", "onPlaybackFailure", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface PlaybackListener {
    void onPlaybackFailure(PlaybackError error, Exception exception);

    /* JADX INFO: compiled from: PlaybackListener.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void onPlaybackFailure$default(PlaybackListener playbackListener, PlaybackError playbackError, Exception exc, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onPlaybackFailure");
            }
            if ((i & 2) != 0) {
                exc = null;
            }
            playbackListener.onPlaybackFailure(playbackError, exc);
        }
    }
}
