package com.amazon.podcast.media.playback;

/* JADX INFO: loaded from: classes5.dex */
public enum PlaybackEngineType {
    EXOPLAYER("ExoPlayer"),
    HARLEY("Harley_Android");

    private String playbackEngineType;

    PlaybackEngineType(final String playbackEngineType) {
        this.playbackEngineType = playbackEngineType;
    }

    public final String getPlaybackEngineType() {
        return this.playbackEngineType;
    }
}
