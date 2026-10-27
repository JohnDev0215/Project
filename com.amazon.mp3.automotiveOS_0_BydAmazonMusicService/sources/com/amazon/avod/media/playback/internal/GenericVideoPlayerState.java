package com.amazon.avod.media.playback.internal;

/* JADX INFO: loaded from: classes2.dex */
public enum GenericVideoPlayerState {
    UNINITIALIZED(true, false),
    PREPARING(true, false),
    PREPARED(false, false),
    PLAYING(false, false),
    PAUSED(false, false),
    BUFFERING(false, false),
    SEEKING(false, false),
    ERRORED(false, true),
    COMPLETED(false, true),
    PAUSED_DURING_BUFFERING(false, false),
    TERMINATED(false, true);

    private final boolean mIsPostPlayback;
    private final boolean mIsPrePlayback;

    GenericVideoPlayerState(boolean z, boolean z2) {
        this.mIsPrePlayback = z;
        this.mIsPostPlayback = z2;
    }

    public boolean isPostPlayback() {
        return this.mIsPostPlayback;
    }

    public boolean isPrePlayback() {
        return this.mIsPrePlayback;
    }

    public boolean isPlayback() {
        return (this.mIsPrePlayback || this.mIsPostPlayback) ? false : true;
    }

    public boolean canTransitionTo(GenericVideoPlayerState genericVideoPlayerState) {
        return this != TERMINATED;
    }
}
