package com.amazon.avod.media.playback.util;

import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Deque;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class SlidingWindowEventTracker {
    private final Deque<TimeSpan> mEventTimeStamps = Lists.newLinkedList();
    private final TimeSpan mWindowLength;
    private final int mWindowThreshold;

    public SlidingWindowEventTracker(@Nonnull TimeSpan timeSpan, int i) {
        this.mWindowLength = (TimeSpan) Preconditions.checkNotNull(timeSpan, "windowLength");
        Preconditions.checkArgument(i > 0, "Threshold for a sliding window should be greater than zero");
        this.mWindowThreshold = i;
    }

    public SlidingWindowEventTracker(@Nonnull SlidingWindowConfig slidingWindowConfig) {
        Preconditions.checkNotNull(slidingWindowConfig, "slidingWindowConfig");
        this.mWindowLength = (TimeSpan) Preconditions.checkNotNull(slidingWindowConfig.getSlidingWindowLength(), "windowLength");
        int slidingWindowThreshold = slidingWindowConfig.getSlidingWindowThreshold();
        Preconditions.checkArgument(slidingWindowThreshold > 0, "Threshold for a sliding window should be greater than zero");
        this.mWindowThreshold = slidingWindowThreshold;
    }

    public void recordEvent(@Nonnull TimeSpan timeSpan) {
        Preconditions.checkNotNull(timeSpan, "eventTimestamp");
        this.mEventTimeStamps.add(timeSpan);
    }

    public boolean isEventCountGreaterThanOrEqualToThreshold() {
        purgeRecordsOutsideWindow();
        return this.mEventTimeStamps.size() >= this.mWindowThreshold;
    }

    public boolean isEventCountGreaterThanThreshold() {
        purgeRecordsOutsideWindow();
        return this.mEventTimeStamps.size() > this.mWindowThreshold;
    }

    public int getEventCount() {
        purgeRecordsOutsideWindow();
        return this.mEventTimeStamps.size();
    }

    private void purgeRecordsOutsideWindow() {
        Iterator<TimeSpan> it = this.mEventTimeStamps.iterator();
        while (it.hasNext()) {
            if (TimeSpan.difference(TimeSpan.now(), it.next()).greaterThan(this.mWindowLength)) {
                it.remove();
            }
        }
    }

    public void purgeAllRecords() {
        this.mEventTimeStamps.clear();
    }
}
