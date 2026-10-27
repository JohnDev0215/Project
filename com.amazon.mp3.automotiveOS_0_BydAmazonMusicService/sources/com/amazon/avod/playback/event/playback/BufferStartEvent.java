package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.event.PlaybackEvent;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class BufferStartEvent extends PlaybackEvent {
    private final BufferingAnalysis mBufferingAnalysis;
    private final boolean mIsInCachedRegion;

    public BufferStartEvent(TimeSpan timeSpan, boolean z, @Nullable BufferingAnalysis bufferingAnalysis) {
        super(timeSpan);
        this.mIsInCachedRegion = z;
        this.mBufferingAnalysis = bufferingAnalysis;
    }

    public boolean isInCachedRegion() {
        return this.mIsInCachedRegion;
    }

    @Nullable
    public BufferingAnalysis getBufferingAnalysis() {
        return this.mBufferingAnalysis;
    }
}
