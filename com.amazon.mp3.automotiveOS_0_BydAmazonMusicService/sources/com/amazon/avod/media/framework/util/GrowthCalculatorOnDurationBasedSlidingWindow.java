package com.amazon.avod.media.framework.util;

import java.util.LinkedList;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class GrowthCalculatorOnDurationBasedSlidingWindow {
    private final long mMaxWindowDuration;
    private long mNetGain;
    private long mTotalElapsedTime;
    private long mTotalGainedTime;
    private double mGrowthRate = -1.0d;
    private final LinkedList<Long> mElapsedDurations = new LinkedList<>();
    private final LinkedList<Long> mGainedDurations = new LinkedList<>();

    public GrowthCalculatorOnDurationBasedSlidingWindow(long j) {
        this.mMaxWindowDuration = j;
    }

    public void recordTimeLostAndGained(long j, long j2) {
        trimWindow();
        this.mElapsedDurations.addLast(Long.valueOf(j));
        this.mTotalElapsedTime += j;
        this.mGainedDurations.addLast(Long.valueOf(j2));
        this.mTotalGainedTime += j2;
        updateGrowthAndGain();
    }

    private void trimWindow() {
        if (this.mTotalElapsedTime < this.mMaxWindowDuration) {
            return;
        }
        while (this.mTotalElapsedTime >= this.mMaxWindowDuration && !this.mElapsedDurations.isEmpty()) {
            this.mTotalElapsedTime -= this.mElapsedDurations.removeFirst().longValue();
            this.mTotalGainedTime -= this.mGainedDurations.removeFirst().longValue();
        }
    }

    private void updateGrowthAndGain() {
        long j = this.mTotalElapsedTime;
        if (j >= this.mMaxWindowDuration) {
            long j2 = this.mTotalGainedTime;
            this.mGrowthRate = j2 / j;
            this.mNetGain = j2 - j;
        } else {
            this.mGrowthRate = 0.0d;
            this.mNetGain = 0L;
        }
    }

    public double getCurrentGrowthFactor() {
        return this.mGrowthRate;
    }

    public long getNetGain() {
        return this.mNetGain;
    }

    public long getTotalElapsedTime() {
        return this.mTotalElapsedTime;
    }

    public long getMaxWindowDuration() {
        return this.mMaxWindowDuration;
    }

    public long getTotalGainedTime() {
        return this.mTotalGainedTime;
    }
}
