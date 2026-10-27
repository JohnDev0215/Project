package com.amazon.avod.media.playback.state;

/* JADX INFO: loaded from: classes2.dex */
public enum StandardPlayerStateType implements PlayerStateType {
    UNINITIALIZED,
    INITIALIZING,
    BUFFERING,
    ERROR,
    PAUSED,
    PLAYING,
    PREPARING,
    PREPARED,
    SEEKING,
    COMPLETED,
    SHUTDOWN
}
