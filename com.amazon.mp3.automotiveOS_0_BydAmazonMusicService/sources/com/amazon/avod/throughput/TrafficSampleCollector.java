package com.amazon.avod.throughput;

import android.net.TrafficStats;
import com.amazon.avod.media.framework.util.IntRollingStatisticsAggregator;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class TrafficSampleCollector implements Runnable {
    private final int mAppUid;
    private final IntRollingStatisticsAggregator mBandwidthBpsAggregator;
    private long mLastTotalRxBytes;
    private final IntRollingStatisticsAggregator mPredictionErrorBpsAggregator;
    private final int mSampleBottomThresholdBps;
    private final int mTimeStepMillis;

    public TrafficSampleCollector(int i, @Nonnull IntRollingStatisticsAggregator intRollingStatisticsAggregator, @Nonnull IntRollingStatisticsAggregator intRollingStatisticsAggregator2, int i2, int i3) {
        Preconditions.checkNotNull(intRollingStatisticsAggregator, "bandwidthBpsAggregator");
        Preconditions.checkNotNull(intRollingStatisticsAggregator, "predictionErrorBpsAggregator");
        this.mAppUid = i;
        this.mBandwidthBpsAggregator = intRollingStatisticsAggregator;
        this.mPredictionErrorBpsAggregator = intRollingStatisticsAggregator2;
        this.mTimeStepMillis = i2;
        this.mSampleBottomThresholdBps = i3;
    }

    @Override // java.lang.Runnable
    public void run() {
        long totalRxBytes;
        int i;
        int i2 = this.mAppUid;
        if (i2 != -1) {
            totalRxBytes = TrafficStats.getUidRxBytes(i2);
        } else {
            totalRxBytes = TrafficStats.getTotalRxBytes();
        }
        long j = this.mLastTotalRxBytes;
        if (j > 0 && (i = (int) (((totalRxBytes - j) * 1000) / ((long) this.mTimeStepMillis))) > this.mSampleBottomThresholdBps) {
            this.mPredictionErrorBpsAggregator.addSample(this.mBandwidthBpsAggregator.getAverage() - i);
            this.mBandwidthBpsAggregator.addSample(i);
        }
        this.mLastTotalRxBytes = totalRxBytes;
    }
}
