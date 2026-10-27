package com.amazon.avod.qos.internal;

import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class SummaryStats {
    private int mNumSamples = 0;
    private double mMean = 0.0d;
    private double mSquareError = 0.0d;

    public void append(double d) {
        int i = this.mNumSamples + 1;
        this.mNumSamples = i;
        if (i == 1) {
            this.mMean = d;
            this.mSquareError = 0.0d;
            return;
        }
        double d2 = this.mMean;
        double d3 = this.mSquareError;
        double d4 = d - d2;
        double d5 = d2 + (d4 / ((double) i));
        this.mMean = d5;
        this.mSquareError = d3 + (d4 * (d - d5));
    }

    public void reject(double d) {
        Preconditions.checkState(numSamples() > 0, "There must be elements in the sequence in order to reject them.");
        int i = this.mNumSamples - 1;
        this.mNumSamples = i;
        if (i != 0) {
            double d2 = this.mMean;
            double d3 = this.mSquareError;
            double d4 = d - d2;
            double d5 = d2 - (d4 / ((double) i));
            this.mMean = d5;
            this.mSquareError = d3 - (d4 * (d - d5));
        }
    }

    public int numSamples() {
        return this.mNumSamples;
    }

    public double getArithmeticMean() {
        Preconditions.checkState(numSamples() > 0, "Arithmetic mean is undefined for empty sequence.");
        return this.mMean;
    }

    public Double getArithmeticMeanOrNull() {
        if (this.mNumSamples > 0) {
            return Double.valueOf(getArithmeticMean());
        }
        return null;
    }

    public double getVariance() {
        Preconditions.checkState(numSamples() > 0, "Variance is undefined for empty sequence.");
        int i = this.mNumSamples;
        if (i > 1) {
            return this.mSquareError / ((double) (i - 1));
        }
        return 0.0d;
    }

    public double getStandardDeviation() {
        Preconditions.checkState(numSamples() > 0, "Standard deviation is undefined for empty sequence.");
        return Math.sqrt(getVariance());
    }

    public Double getStandardDeviationOrNull() {
        if (this.mNumSamples > 0) {
            return Double.valueOf(getStandardDeviation());
        }
        return null;
    }
}
