package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;

/* JADX INFO: loaded from: classes2.dex */
public class BufferStopEvent extends PlaybackEvent {
    private final boolean mIsInCachedRegion;

    public BufferStopEvent(TimeSpan timeSpan, boolean z) {
        super(timeSpan);
        this.mIsInCachedRegion = z;
    }

    public boolean isInCachedRegion() {
        return this.mIsInCachedRegion;
    }
}
