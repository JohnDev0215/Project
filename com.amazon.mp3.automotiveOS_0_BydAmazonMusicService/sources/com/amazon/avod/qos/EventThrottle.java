package com.amazon.avod.qos;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.qos.internal.DurationTimer;
import com.amazon.avod.util.sequence.AbstractIntSequence;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class EventThrottle {
    private final TimeUnit mSequenceTimeUnit;
    private final AbstractIntSequence mThrottleSequence;
    private final DurationTimer mTimeSinceLastReport;
    private TimeSpan mTimeUntilNextReport;

    public EventThrottle(AbstractIntSequence abstractIntSequence) {
        this(abstractIntSequence, TimeUnit.SECONDS);
    }

    public EventThrottle(AbstractIntSequence abstractIntSequence, TimeUnit timeUnit) {
        this.mTimeSinceLastReport = new DurationTimer();
        this.mThrottleSequence = abstractIntSequence;
        this.mSequenceTimeUnit = timeUnit;
    }

    public boolean shouldSendEvent() {
        return shouldSendEvent(TimeSpan.now());
    }

    public void onEvent() {
        onEvent(TimeSpan.now());
    }

    public void reset() {
        this.mThrottleSequence.reset();
        this.mTimeSinceLastReport.reset();
    }

    boolean shouldSendEvent(TimeSpan timeSpan) {
        return !this.mTimeSinceLastReport.isStarted() || this.mTimeUntilNextReport.compareTo(this.mTimeSinceLastReport.elapsedUntil(timeSpan)) <= 0;
    }

    void onEvent(TimeSpan timeSpan) {
        this.mTimeUntilNextReport = getTimeUntilNextReport();
        this.mTimeSinceLastReport.setStartTime(timeSpan);
    }

    private TimeSpan getTimeUntilNextReport() {
        return new TimeSpan(this.mSequenceTimeUnit.toNanos(this.mThrottleSequence.getNextValue()));
    }
}
