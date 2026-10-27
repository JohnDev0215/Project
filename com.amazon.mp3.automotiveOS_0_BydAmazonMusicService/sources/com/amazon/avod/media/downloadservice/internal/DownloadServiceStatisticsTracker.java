package com.amazon.avod.media.downloadservice.internal;

import com.amazon.avod.media.downloadservice.BandwidthStatistics;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.util.IntRollingStatisticsAggregator;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadServiceStatisticsTracker {
    private final IntRollingStatisticsAggregator mBandwidthSampleAggregator;
    private final IntRollingStatisticsAggregator mLatencySampleAggregator;
    private long mMaxPrimaryContentBufferLengthInNanoseconds = -1;
    private long mCurrentPrimaryContentBufferLengthInNanoseconds = 0;
    private BandwidthStatistics mCurrentBandwidthStatistics = new BandwidthStatistics(0, 0, 0);

    public DownloadServiceStatisticsTracker(int i) {
        this.mBandwidthSampleAggregator = new IntRollingStatisticsAggregator(i);
        this.mLatencySampleAggregator = new IntRollingStatisticsAggregator(i);
    }

    public void updateBandwidthStatistics(DownloadStatistics downloadStatistics) {
        int millis = (int) TimeUnit.NANOSECONDS.toMillis(downloadStatistics.getLatencyInNanos());
        if (millis > 0) {
            this.mLatencySampleAggregator.addSample(millis);
        } else {
            DLog.devf("Discarding non-positive latency sample %s (ms)", Integer.valueOf(millis));
        }
        int bandwidth = (int) downloadStatistics.getBandwidth();
        if (bandwidth > 0) {
            this.mBandwidthSampleAggregator.addSample(bandwidth);
        } else {
            DLog.devf("Discarding non-positive bandwidth sample %s (bps)", Integer.valueOf(bandwidth));
        }
        this.mCurrentBandwidthStatistics = new BandwidthStatistics(this.mBandwidthSampleAggregator.getAverage(), this.mBandwidthSampleAggregator.getMinimum(), this.mBandwidthSampleAggregator.getSampleCount());
    }

    public long estimateDownloadTimeInNanos(int i) {
        double average = this.mBandwidthSampleAggregator.getAverage();
        if (average <= 0.0d) {
            return Long.MAX_VALUE;
        }
        return TimeUnit.MILLISECONDS.toNanos(((long) ((DataUnit.BYTES.toBits(i) / average) * 1000.0d)) + ((long) this.mLatencySampleAggregator.getAverage()));
    }

    public int getBandwidthAggregatorSampleCount() {
        return this.mBandwidthSampleAggregator.getSampleCount();
    }

    public void updateBufferLengthInNanos(long j) {
        this.mCurrentPrimaryContentBufferLengthInNanoseconds = j;
    }

    public void setMaxBufferLengthInNanos(long j) {
        this.mMaxPrimaryContentBufferLengthInNanoseconds = j;
    }

    public BandwidthStatistics getBandwidthStatistics() {
        return this.mCurrentBandwidthStatistics;
    }

    public long getPrimaryContentBufferLengthInNanos() {
        return this.mCurrentPrimaryContentBufferLengthInNanoseconds;
    }

    public long getMaxPrimaryContentBufferLengthInNanoseconds() {
        return this.mMaxPrimaryContentBufferLengthInNanoseconds;
    }
}
