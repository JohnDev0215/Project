package com.amazon.avod.qos.reporter.internal.data;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.qos.internal.DurationTimer;

/* JADX INFO: loaded from: classes2.dex */
public class TimedEventData {
    protected TimeSpan mCumulativeDuration;
    protected final DurationTimer mEventTimer;

    public TimedEventData() {
        this(new DurationTimer());
    }

    TimedEventData(DurationTimer durationTimer) {
        this.mCumulativeDuration = TimeSpan.ZERO;
        this.mEventTimer = durationTimer;
    }

    public boolean eventStart(TimeSpan timeSpan) {
        if (this.mEventTimer.isStarted()) {
            return false;
        }
        this.mEventTimer.setStartTime(timeSpan);
        return true;
    }

    public TimeSpan eventEnd(TimeSpan timeSpan) {
        if (!this.mEventTimer.isStarted()) {
            return null;
        }
        TimeSpan timeSpanElapsedUntil = this.mEventTimer.elapsedUntil(timeSpan);
        this.mCumulativeDuration = TimeSpan.add(this.mCumulativeDuration, timeSpanElapsedUntil);
        this.mEventTimer.reset();
        return timeSpanElapsedUntil;
    }

    public long getCumulativeTime(TimeSpan timeSpan) {
        long totalMilliseconds = this.mCumulativeDuration.getTotalMilliseconds();
        return this.mEventTimer.isStarted() ? totalMilliseconds + this.mEventTimer.elapsedMillisUntil(timeSpan) : totalMilliseconds;
    }

    public boolean isStarted() {
        return this.mEventTimer.isStarted();
    }

    public void reset() {
        this.mEventTimer.reset();
        this.mCumulativeDuration = TimeSpan.ZERO;
    }
}
