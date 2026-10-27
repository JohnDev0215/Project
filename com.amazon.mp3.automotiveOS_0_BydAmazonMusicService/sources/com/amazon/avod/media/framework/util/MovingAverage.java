package com.amazon.avod.media.framework.util;

import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class MovingAverage {
    private double currentAverage;
    private double currentMax;
    private double currentMin;
    private long numberSamples;

    public double addSample(double d) {
        if (this.numberSamples > 0) {
            this.currentMax = Math.max(this.currentMax, d);
            this.currentMin = Math.min(this.currentMin, d);
        } else {
            this.currentMin = d;
            this.currentMax = d;
        }
        double d2 = this.currentAverage;
        long j = this.numberSamples;
        double d3 = (d2 * j) + d;
        long j2 = j + 1;
        this.numberSamples = j2;
        double d4 = d3 / j2;
        this.currentAverage = d4;
        return d4;
    }

    public double getCurrentAverage() {
        return this.currentAverage;
    }

    public double getCurrentMin() {
        return this.currentMin;
    }

    public double getCurrentMax() {
        return this.currentMax;
    }
}
