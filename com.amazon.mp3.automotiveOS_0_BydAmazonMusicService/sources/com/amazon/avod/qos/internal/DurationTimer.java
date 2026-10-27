package com.amazon.avod.qos.internal;

import com.amazon.avod.media.TimeSpan;

/* JADX INFO: loaded from: classes2.dex */
public class DurationTimer {
    private TimeSpan mStartTime;

    public void setStartTime(TimeSpan timeSpan) {
        this.mStartTime = timeSpan;
    }

    public void reset() {
        this.mStartTime = null;
    }

    public long elapsedMillisUntil(TimeSpan timeSpan) {
        return elapsedUntil(timeSpan).getTotalMilliseconds();
    }

    public TimeSpan elapsedUntil(TimeSpan timeSpan) {
        return getDuration(this.mStartTime, timeSpan);
    }

    private TimeSpan getDuration(TimeSpan timeSpan, TimeSpan timeSpan2) {
        if (timeSpan == null || timeSpan2 == null) {
            return TimeSpan.ZERO;
        }
        return TimeSpan.difference(timeSpan, timeSpan2);
    }

    public boolean isStarted() {
        return this.mStartTime != null;
    }
}
