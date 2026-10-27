package com.amazon.podcast.media.playback;

/* JADX INFO: loaded from: classes5.dex */
public interface PodcastPlayerCallback {
    void onBufferedPositionChanged(final long position);

    void onHeartbeatPositionChanged(final long position);

    void onMediaBuffered(final long duration);

    void onPlaybackCleared();

    void onPlaybackPaused();

    void onPlaybackPositionChanged(final long position);

    void onPlaybackRemovedFromLockScreen();

    void onPlaybackStarted();
}
