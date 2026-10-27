package com.amazon.music.media.playback;

/* JADX INFO: loaded from: classes4.dex */
public enum PlayStatus {
    RENDERING,
    USER_PAUSED,
    SYSTEM_PAUSED,
    SWITCHING_PLAYERS,
    LOADING,
    PREPARING,
    SEEKING,
    BUFFERING;

    public boolean willPlay() {
        return this != USER_PAUSED;
    }

    public boolean shouldBePlaying() {
        return (this == USER_PAUSED || this == SYSTEM_PAUSED) ? false : true;
    }

    public boolean userExpectsPlayback() {
        return (this == USER_PAUSED || this == SYSTEM_PAUSED) ? false : true;
    }

    public boolean isDelayed() {
        return userExpectsPlayback() && this != RENDERING;
    }

    public boolean isPositionChanging() {
        return this == RENDERING;
    }
}
