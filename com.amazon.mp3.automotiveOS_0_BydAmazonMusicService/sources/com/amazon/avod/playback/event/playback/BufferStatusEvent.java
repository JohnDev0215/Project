package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;

/* JADX INFO: loaded from: classes2.dex */
public class BufferStatusEvent extends PlaybackEvent {
    private final boolean mNewInCachedRegion;
    private final boolean mOldInCachedRegion;
    private final float mProgress;

    public BufferStatusEvent(TimeSpan timeSpan, float f, boolean z, boolean z2) {
        super(timeSpan);
        this.mProgress = f;
        this.mOldInCachedRegion = z;
        this.mNewInCachedRegion = z2;
    }

    public float getProgressPercent() {
        return this.mProgress;
    }

    public boolean wasInCachedRegion() {
        return this.mOldInCachedRegion;
    }

    public boolean isNowInCachedRegion() {
        return this.mNewInCachedRegion;
    }
}
