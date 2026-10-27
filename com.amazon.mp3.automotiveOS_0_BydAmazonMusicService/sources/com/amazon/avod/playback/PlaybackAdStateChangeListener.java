package com.amazon.avod.playback;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackAdStateChangeListener {
    void onAdPlaybackCompleted();

    void onAdPlaybackLoaded();

    void onAdPlaybackPaused();

    void onAdPlaybackPlaying();
}
