package com.amazon.avod.qos.internal;

import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class WindowedSummaryStats {
    private final double[] mBuf;
    private final SummaryStats mStats = new SummaryStats();
    private int mBegin = 0;

    public WindowedSummaryStats(int i) {
        this.mBuf = new double[i];
    }

    public void append(double d) {
        int iNumSamples = this.mStats.numSamples();
        double[] dArr = this.mBuf;
        if (iNumSamples == dArr.length) {
            this.mStats.reject(dArr[this.mBegin]);
            this.mBegin = (this.mBegin + 1) % this.mBuf.length;
        }
        this.mBuf[(this.mBegin + this.mStats.numSamples()) % this.mBuf.length] = d;
        this.mStats.append(d);
    }

    public long numSamples() {
        return this.mStats.numSamples();
    }

    public double getArithmeticMean() {
        Preconditions.checkState(numSamples() > 0, "Arithmetic mean is undefined for empty sequence.");
        return this.mStats.getArithmeticMean();
    }

    public Double getArithmeticMeanOrNull() {
        return this.mStats.getArithmeticMeanOrNull();
    }

    public double getVariance() {
        Preconditions.checkState(numSamples() > 0, "Variance is undefined for empty sequence.");
        return this.mStats.getVariance();
    }

    public double getStandardDeviation() {
        Preconditions.checkState(numSamples() > 0, "Standard deviation is undefined for empty sequence.");
        return this.mStats.getStandardDeviation();
    }
}
