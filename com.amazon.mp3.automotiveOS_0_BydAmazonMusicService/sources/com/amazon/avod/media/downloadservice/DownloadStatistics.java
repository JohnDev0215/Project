package com.amazon.avod.media.downloadservice;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class DownloadStatistics {
    private final long mBandwidthBps;
    private final long mBytesProcessed;
    private final long mLatencyInNanoseconds;
    private final long mProcessedTimeInNanoseconds;
    private final long mThroughputBps;
    private final long mTotalTimeInNanoseconds;

    @Nullable
    private final String mURL;

    @Nullable
    private final Map<String, List<String>> mUnformattedHeaders;

    public static int getBitRateSeconds(long j, long j2) {
        return (int) (((j * 8) * 1000) / Math.max(TimeUnit.NANOSECONDS.toMillis(j2), 1L));
    }

    private DownloadStatistics(DownloadStatisticsBuilder downloadStatisticsBuilder) {
        this.mLatencyInNanoseconds = downloadStatisticsBuilder.mLatencyInNanoseconds;
        this.mProcessedTimeInNanoseconds = downloadStatisticsBuilder.mProcessedTimeInNanoseconds;
        this.mTotalTimeInNanoseconds = downloadStatisticsBuilder.mLatencyInNanoseconds + downloadStatisticsBuilder.mProcessedTimeInNanoseconds;
        this.mBytesProcessed = downloadStatisticsBuilder.mBytesProcessed;
        this.mBandwidthBps = getBitRateSeconds(r4, r0);
        this.mThroughputBps = getBitRateSeconds(r4, r2);
        this.mUnformattedHeaders = downloadStatisticsBuilder.mUnformattedHeaders;
        this.mURL = downloadStatisticsBuilder.mURL;
    }

    public long getLatencyInNanos() {
        return this.mLatencyInNanoseconds;
    }

    public long getProcessedTimeInNanos() {
        return this.mProcessedTimeInNanoseconds;
    }

    public long getTotalTimeInNanos() {
        return this.mTotalTimeInNanoseconds;
    }

    public long getBytesProcessed() {
        return this.mBytesProcessed;
    }

    public long getBandwidth() {
        return this.mBandwidthBps;
    }

    public long getThroughput() {
        return this.mThroughputBps;
    }

    @Nullable
    public Map<String, List<String>> getUnformattedHeaders() {
        return this.mUnformattedHeaders;
    }

    @Nullable
    public String getURL() {
        return this.mURL;
    }

    public static DownloadStatisticsBuilder newBuilder() {
        return new DownloadStatisticsBuilder();
    }

    public static class DownloadStatisticsBuilder {
        private long mBytesProcessed;
        private long mLatencyInNanoseconds;
        private long mProcessedTimeInNanoseconds;

        @Nullable
        private String mURL;

        @Nullable
        private Map<String, List<String>> mUnformattedHeaders;

        public DownloadStatisticsBuilder onHeadersReceived(long j, @Nullable Map<String, List<String>> map, @Nullable String str) {
            this.mLatencyInNanoseconds = j;
            this.mUnformattedHeaders = map;
            this.mURL = str;
            return this;
        }

        public DownloadStatisticsBuilder onBytesProcessed(long j) {
            this.mBytesProcessed += j;
            return this;
        }

        public DownloadStatisticsBuilder onConnectionClosed(long j) {
            this.mProcessedTimeInNanoseconds = j;
            return this;
        }

        public DownloadStatistics build() {
            return new DownloadStatistics(this);
        }
    }
}
