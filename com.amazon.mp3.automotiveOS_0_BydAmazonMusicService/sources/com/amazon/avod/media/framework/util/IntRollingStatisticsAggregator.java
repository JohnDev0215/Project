package com.amazon.avod.media.framework.util;

/* JADX INFO: loaded from: classes2.dex */
public class IntRollingStatisticsAggregator {
    final int[] mSamples;
    int mNumSamples = 0;
    int mRollingCounter = 0;
    long mTotal = 0;
    int mAverage = 0;

    public IntRollingStatisticsAggregator(int i) {
        this.mSamples = new int[i];
    }

    public void addSample(int i) {
        synchronized (this.mSamples) {
            int[] iArr = this.mSamples;
            int i2 = this.mRollingCounter;
            int i3 = iArr[i2];
            iArr[i2] = i;
            int i4 = i2 + 1;
            this.mRollingCounter = i4;
            if (i4 >= iArr.length) {
                this.mRollingCounter = 0;
            }
            int i5 = this.mNumSamples;
            if (i5 < iArr.length) {
                this.mNumSamples = i5 + 1;
            }
            this.mAverage = calculateAverage(i3, i);
        }
    }

    public int getAverage() {
        return this.mAverage;
    }

    public int getMinimum() {
        int i;
        synchronized (this.mSamples) {
            i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < Math.min(this.mSamples.length, this.mNumSamples); i2++) {
                int i3 = this.mSamples[i2];
                if (i3 < i) {
                    i = i3;
                }
            }
        }
        return i;
    }

    public int getSampleCount() {
        return this.mNumSamples;
    }

    public int getStandardDeviation() {
        synchronized (this.mSamples) {
            int i = this.mNumSamples;
            int i2 = 0;
            if (i <= 0) {
                return 0;
            }
            double d = this.mTotal / ((double) i);
            double dPow = 0.0d;
            while (true) {
                int i3 = this.mNumSamples;
                if (i2 < i3) {
                    dPow += Math.pow(((double) this.mSamples[i2]) - d, 2.0d);
                    i2++;
                } else {
                    return (int) Math.sqrt(dPow / ((double) i3));
                }
            }
        }
    }

    private int calculateAverage(int i, int i2) {
        long j = this.mTotal + ((long) (i2 - i));
        this.mTotal = j;
        return (int) (j / ((long) this.mNumSamples));
    }
}
