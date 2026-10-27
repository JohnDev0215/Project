package com.amazon.music.voice.playbackstate;

import kotlin.Metadata;

/* JADX INFO: compiled from: MediaOperation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/voice/playbackstate/MediaOperation;", "", "operationName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getOperationName", "()Ljava/lang/String;", "PLAY", "PAUSE", "STOP", "ADJUST_SEEK_POSITION", "SET_SEEK_POSITION", "START_OVER", "DISABLE_REPEAT", "NEXT", "PREVIOUS", "DISABLE_SHUFFLE", "ENABLE_REPEAT", "ENABLE_REPEAT_ONE", "ENABLE_SHUFFLE", "FAST_FORWARD", "REWIND", "FAVORITE", "UN_FAVORITE", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum MediaOperation {
    PLAY("Play"),
    PAUSE("Pause"),
    STOP("Stop"),
    ADJUST_SEEK_POSITION("AdjustSeekPosition"),
    SET_SEEK_POSITION("SetSeekPosition"),
    START_OVER("StartOver"),
    DISABLE_REPEAT("DisableRepeat"),
    NEXT("Next"),
    PREVIOUS("Previous"),
    DISABLE_SHUFFLE("DisableShuffle"),
    ENABLE_REPEAT("EnableRepeat"),
    ENABLE_REPEAT_ONE("EnableRepeatOne"),
    ENABLE_SHUFFLE("EnableShuffle"),
    FAST_FORWARD("FastForward"),
    REWIND("Rewind"),
    FAVORITE("Favorite"),
    UN_FAVORITE("Unfavorite");

    private final String operationName;

    MediaOperation(String str) {
        this.operationName = str;
    }

    public final String getOperationName() {
        return this.operationName;
    }
}
