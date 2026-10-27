package com.amazon.music.metrics.mts.event.types;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackInitiationInfo {
    private DirectedPlayStatus mDirectedPlayStatus;
    private PlaybackPageType mPlaybackPageType;
    private boolean mShufflePlayStatus;

    public PlaybackInitiationInfo(DirectedPlayStatus directedPlayStatus, boolean z, PlaybackPageType playbackPageType) {
        this.mDirectedPlayStatus = directedPlayStatus;
        this.mShufflePlayStatus = z;
        this.mPlaybackPageType = playbackPageType;
    }

    public DirectedPlayStatus getDirectedPlayStatus() {
        return this.mDirectedPlayStatus;
    }

    public boolean getShufflePlayStatus() {
        return this.mShufflePlayStatus;
    }

    public PlaybackPageType getPlaybackPageType() {
        return this.mPlaybackPageType;
    }
}
