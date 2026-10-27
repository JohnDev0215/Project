package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;

/* JADX INFO: loaded from: classes2.dex */
public class SeekStartEvent extends PlaybackEvent {
    private final boolean mIsInCachedRegion;
    private final TimeSpan mTargetTimeStamp;

    public SeekStartEvent(TimeSpan timeSpan, TimeSpan timeSpan2, boolean z) {
        super(timeSpan);
        this.mTargetTimeStamp = timeSpan2;
        this.mIsInCachedRegion = z;
    }

    public boolean isInCachedRegion() {
        return this.mIsInCachedRegion;
    }

    public TimeSpan getTargetTime() {
        return this.mTargetTimeStamp;
    }
}
