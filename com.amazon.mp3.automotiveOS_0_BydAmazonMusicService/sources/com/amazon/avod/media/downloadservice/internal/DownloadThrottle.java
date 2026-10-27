package com.amazon.avod.media.downloadservice.internal;

import com.amazon.avod.media.TimeSpan;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadThrottle {
    private final DownloadServiceStatisticsTracker mDownloadServiceStatsTracker;
    private volatile boolean mIsThrottling = false;
    private final TimeSpan mMaxDelay;
    private final double mThrottleFactor;

    public DownloadThrottle(TimeSpan timeSpan, double d, DownloadServiceStatisticsTracker downloadServiceStatisticsTracker) {
        this.mMaxDelay = timeSpan;
        this.mThrottleFactor = d;
        this.mDownloadServiceStatsTracker = downloadServiceStatisticsTracker;
    }

    public int getDelayMillis(int i) {
        if (!this.mIsThrottling || this.mDownloadServiceStatsTracker.getBandwidthAggregatorSampleCount() <= 0) {
            return 0;
        }
        return (int) Math.min(this.mMaxDelay.getTotalMilliseconds(), (int) Math.max(0.0d, TimeUnit.NANOSECONDS.toMillis(this.mDownloadServiceStatsTracker.estimateDownloadTimeInNanos(i)) * (this.mThrottleFactor - 1.0d)));
    }
}
