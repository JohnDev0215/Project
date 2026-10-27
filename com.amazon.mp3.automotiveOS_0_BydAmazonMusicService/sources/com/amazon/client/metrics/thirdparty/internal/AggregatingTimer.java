package com.amazon.client.metrics.thirdparty.internal;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class AggregatingTimer {
    private static final TimeUnit TIMEUNIT = TimeUnit.MILLISECONDS;
    private final boolean mAllowRunningTimers;
    private int mRunningCount = 0;
    private int mTotalSamplesCount = 0;
    private long mLastUpdatedTime = 0;
    private double mTotalElapsedTime = 0.0d;

    public AggregatingTimer(boolean z) {
        this.mAllowRunningTimers = z;
    }

    public void startTimer() {
        long currentTimeMillis = getCurrentTimeMillis();
        int i = this.mRunningCount;
        if (i > 0) {
            this.mTotalElapsedTime += ((long) i) * (currentTimeMillis - this.mLastUpdatedTime);
        }
        this.mLastUpdatedTime = currentTimeMillis;
        this.mRunningCount = i + 1;
    }

    public void stopTimer() {
        long currentTimeMillis = getCurrentTimeMillis();
        int i = this.mRunningCount;
        if (i > 0) {
            this.mTotalElapsedTime += ((long) i) * (currentTimeMillis - this.mLastUpdatedTime);
            this.mRunningCount = i - 1;
            this.mLastUpdatedTime = currentTimeMillis;
            this.mTotalSamplesCount++;
        }
    }

    public void addTime(double d, int i) {
        DoubleValidator.validateDouble(d);
        this.mTotalElapsedTime += d;
        this.mTotalSamplesCount += i;
    }

    public double getElapsedTime() {
        if (this.mRunningCount > 0) {
            if (!this.mAllowRunningTimers) {
                throw new IllegalStateException("Cannot get elapsed time - " + this.mRunningCount + " start calls not matched with stop.");
            }
            return this.mTotalElapsedTime + (((long) r0) * (getCurrentTimeMillis() - this.mLastUpdatedTime));
        }
        return this.mTotalElapsedTime;
    }

    public int getSamples() {
        if (!this.mAllowRunningTimers) {
            return this.mTotalSamplesCount;
        }
        return this.mTotalSamplesCount + this.mRunningCount;
    }

    public int numRunningTimers() {
        return this.mRunningCount;
    }

    protected long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
