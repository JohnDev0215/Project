package com.amazon.avod.playback.event.playback;

/* JADX INFO: loaded from: classes2.dex */
public class TimeDataChangeEvent {
    private final long mPlaybackCurrentPositionInMs;

    public TimeDataChangeEvent(long j) {
        this.mPlaybackCurrentPositionInMs = j;
    }

    public long getPlaybackCurrentPosition() {
        return this.mPlaybackCurrentPositionInMs;
    }
}
