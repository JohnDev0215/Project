package com.amazon.mp3.playlist;

/* JADX INFO: loaded from: classes3.dex */
public enum PrimePlaylistFollowState {
    UNFOLLOWED(0),
    FOLLOWING(1);

    private int mState;

    PrimePlaylistFollowState(int value) {
        this.mState = value;
    }

    public int getState() {
        return this.mState;
    }
}
