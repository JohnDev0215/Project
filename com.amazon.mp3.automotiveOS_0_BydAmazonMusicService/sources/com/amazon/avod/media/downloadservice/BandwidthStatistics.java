package com.amazon.avod.media.downloadservice;

import com.google.common.base.MoreObjects;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class BandwidthStatistics {
    private final int mAverage;
    private final int mMinimum;
    private final int mSampleCount;

    public BandwidthStatistics(int i, int i2, int i3) {
        this.mAverage = i;
        this.mMinimum = i2;
        this.mSampleCount = i3;
    }

    public int getAverage() {
        return this.mAverage;
    }

    public int getMinimum() {
        return this.mMinimum;
    }

    public int getSampleCount() {
        return this.mSampleCount;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("Average bps", this.mAverage).add("Minimum bps", this.mMinimum).add("Sample count", this.mSampleCount).toString();
    }
}
