package com.amazon.avod.throughput;

import android.net.TrafficStats;
import com.amazon.avod.media.framework.util.IntRollingStatisticsAggregator;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.google.common.base.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class BandwidthStats {
    private final IntRollingStatisticsAggregator mBandwidthBpsAggregator;
    private final Object mMutex = new Object();
    private final IntRollingStatisticsAggregator mPredictionErrorAggregator;
    private ScheduledExecutorService mScheduledExecutor;
    private final int mTimeStepToCollectSampleMillis;
    private final TrafficSampleCollector mTrafficSampleCollector;

    public static boolean isSupported() {
        return TrafficStats.getTotalRxBytes() != -1;
    }

    public boolean checkTrafficStatsSupport() {
        return TrafficStats.getTotalRxBytes() != -1;
    }

    public static BandwidthStats newInstance(int i) {
        BandwidthStatsConfig bandwidthStatsConfig = BandwidthStatsConfig.getInstance();
        int numberOfSamples = bandwidthStatsConfig.getNumberOfSamples();
        IntRollingStatisticsAggregator intRollingStatisticsAggregator = new IntRollingStatisticsAggregator(numberOfSamples);
        IntRollingStatisticsAggregator intRollingStatisticsAggregator2 = new IntRollingStatisticsAggregator(numberOfSamples);
        int timeStepToCollectSampleMills = bandwidthStatsConfig.getTimeStepToCollectSampleMills();
        int sampleBottomThresholdBps = bandwidthStatsConfig.getSampleBottomThresholdBps();
        return new BandwidthStats(bandwidthStatsConfig.monitorAllApplicationsThroughput() ? new TrafficSampleCollector(-1, intRollingStatisticsAggregator, intRollingStatisticsAggregator2, timeStepToCollectSampleMills, sampleBottomThresholdBps) : new TrafficSampleCollector(i, intRollingStatisticsAggregator, intRollingStatisticsAggregator2, timeStepToCollectSampleMills, sampleBottomThresholdBps), intRollingStatisticsAggregator, timeStepToCollectSampleMills, intRollingStatisticsAggregator2);
    }

    BandwidthStats(@Nonnull TrafficSampleCollector trafficSampleCollector, @Nonnull IntRollingStatisticsAggregator intRollingStatisticsAggregator, int i, @Nonnull IntRollingStatisticsAggregator intRollingStatisticsAggregator2) {
        this.mTrafficSampleCollector = (TrafficSampleCollector) Preconditions.checkNotNull(trafficSampleCollector, "trafficSampleCollector");
        this.mBandwidthBpsAggregator = (IntRollingStatisticsAggregator) Preconditions.checkNotNull(intRollingStatisticsAggregator, "bandwidthBpsAggregator");
        this.mPredictionErrorAggregator = (IntRollingStatisticsAggregator) Preconditions.checkNotNull(intRollingStatisticsAggregator2, "predictionErrorAggregator");
        this.mTimeStepToCollectSampleMillis = i;
    }

    public void start() {
        synchronized (this.mMutex) {
            Preconditions.checkState(this.mScheduledExecutor == null, "Cannot start twice");
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutorBuild = ScheduledExecutorBuilder.newBuilderFor(this, "BWStatsScheduler").withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build();
            this.mScheduledExecutor = scheduledThreadPoolExecutorBuild;
            scheduledThreadPoolExecutorBuild.scheduleAtFixedRate(this.mTrafficSampleCollector, 0L, this.mTimeStepToCollectSampleMillis, TimeUnit.MILLISECONDS);
        }
    }

    public void stop() {
        synchronized (this.mMutex) {
            ScheduledExecutorService scheduledExecutorService = this.mScheduledExecutor;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
                this.mScheduledExecutor = null;
            }
        }
    }

    public int getAvgBandwidthBps() {
        return this.mBandwidthBpsAggregator.getAverage();
    }

    public int getStdBandwidthBps() {
        return this.mBandwidthBpsAggregator.getStandardDeviation();
    }

    public int getAvgBandwidthPredictionErrorBps() {
        return this.mPredictionErrorAggregator.getAverage();
    }
}
