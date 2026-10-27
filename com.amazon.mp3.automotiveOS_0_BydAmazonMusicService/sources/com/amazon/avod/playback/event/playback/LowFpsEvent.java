package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;

/* JADX INFO: loaded from: classes2.dex */
public class LowFpsEvent extends PlaybackEvent {
    private final int mConsecutiveCount;
    private final TimeSpan mRealtimeDuration;
    private final TimeSpan mRenderedDuration;

    public LowFpsEvent(TimeSpan timeSpan, TimeSpan timeSpan2, TimeSpan timeSpan3, int i) {
        super(timeSpan);
        this.mRenderedDuration = timeSpan2;
        this.mRealtimeDuration = timeSpan3;
        this.mConsecutiveCount = i;
    }

    public int getConsecutiveCount() {
        return this.mConsecutiveCount;
    }

    public TimeSpan getRenderedDuration() {
        return this.mRenderedDuration;
    }

    public TimeSpan getRealtimeDuration() {
        return this.mRealtimeDuration;
    }
}
