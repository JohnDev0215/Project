package com.amazon.music.media.playback.player;

/* JADX INFO: loaded from: classes4.dex */
public enum PlayRequestStatus {
    NOT_STARTED,
    PAUSED,
    PREPARING,
    SEEKING,
    BUFFERING,
    RENDERING,
    TERMINATED;

    public boolean shouldBePlaying() {
        return this == PREPARING || this == SEEKING || this == BUFFERING || this == RENDERING;
    }

    public boolean hasStarted() {
        return (this == NOT_STARTED || this == PREPARING) ? false : true;
    }

    public PlayRequestStatus onSeek() {
        return !shouldBePlaying() ? this : hasStarted() ? SEEKING : PREPARING;
    }

    public PlayRequestStatus onPrepare() {
        return !shouldBePlaying() ? this : hasStarted() ? BUFFERING : PREPARING;
    }

    public PlayRequestStatus onShouldPlay() {
        return shouldBePlaying() ? this : hasStarted() ? BUFFERING : PREPARING;
    }

    public PlayRequestStatus onBuffering() {
        return !shouldBePlaying() ? this : hasStarted() ? BUFFERING : PREPARING;
    }

    public PlayRequestStatus onBufferingComplete() {
        return !shouldBePlaying() ? this : RENDERING;
    }

    public PlayRequestStatus onPause() {
        return !shouldBePlaying() ? this : hasStarted() ? PAUSED : NOT_STARTED;
    }

    public PlayRequestStatus onError() {
        return hasStarted() ? PAUSED : NOT_STARTED;
    }
}
