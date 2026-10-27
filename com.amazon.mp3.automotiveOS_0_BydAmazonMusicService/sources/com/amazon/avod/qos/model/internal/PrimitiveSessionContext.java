package com.amazon.avod.qos.model.internal;

import com.amazon.avod.qos.internal.SummaryStats;
import com.amazon.avod.qos.internal.WindowedSummaryStats;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import java.util.UUID;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class PrimitiveSessionContext {
    public static final Factory FACTORY = new Factory();
    private final SummaryStats mBandwidth;
    private final WindowedSummaryStats mBandwidthWindow;
    private final SummaryStats mBitrate;
    private int mBufferCount;
    private final SummaryStats mBufferFullness;
    private long mBytesStreamed;
    private final SummaryStats mDownloadTime;
    private int mDownshiftCount;
    private int mDroppedFrames;
    private int mFatalErrorCount;
    private Long mFinalPlaybackIndexSeconds;
    private final SummaryStats mFrameRate;
    private final WindowedSummaryStats mFrameRateWindow;
    private Long mInitialPlaybackIndexSeconds;
    private final SummaryStats mMaxBuffer;
    private int mPauseCount;
    private final String mPrimitiveSessionId;
    private int mSeekBackwardsCount;
    private int mSeekForwardCount;
    private int mTotalDroppedFrames;
    private int mUpshiftCount;

    public static class Factory {
        protected Factory() {
        }

        public PrimitiveSessionContext createContext() {
            return new PrimitiveSessionContext();
        }
    }

    private PrimitiveSessionContext() {
        this(new SummaryStats(), new SummaryStats(), new SummaryStats(), new SummaryStats(), new SummaryStats(), new SummaryStats(), new WindowedSummaryStats(32), new WindowedSummaryStats(256), UUID.randomUUID().toString());
    }

    PrimitiveSessionContext(SummaryStats summaryStats, SummaryStats summaryStats2, SummaryStats summaryStats3, SummaryStats summaryStats4, SummaryStats summaryStats5, SummaryStats summaryStats6, WindowedSummaryStats windowedSummaryStats, WindowedSummaryStats windowedSummaryStats2, String str) {
        this.mFinalPlaybackIndexSeconds = null;
        this.mInitialPlaybackIndexSeconds = null;
        this.mFatalErrorCount = 0;
        this.mBytesStreamed = 0L;
        this.mUpshiftCount = 0;
        this.mDownshiftCount = 0;
        this.mTotalDroppedFrames = 0;
        this.mDroppedFrames = 0;
        this.mSeekForwardCount = 0;
        this.mSeekBackwardsCount = 0;
        this.mPauseCount = 0;
        this.mBufferCount = 0;
        this.mBandwidth = summaryStats;
        this.mBitrate = summaryStats2;
        this.mBufferFullness = summaryStats3;
        this.mDownloadTime = summaryStats4;
        this.mMaxBuffer = summaryStats5;
        this.mPrimitiveSessionId = str;
        this.mFrameRate = summaryStats6;
        this.mBandwidthWindow = windowedSummaryStats;
        this.mFrameRateWindow = windowedSummaryStats2;
    }

    public void updateStreamingStatistics(long j, long j2, long j3, long j4, long j5, long j6) {
        double d = j;
        this.mBandwidth.append(d);
        this.mBandwidthWindow.append(d);
        this.mBitrate.append(j2);
        this.mBufferFullness.append(j3);
        this.mDownloadTime.append(j4);
        this.mMaxBuffer.append(j5);
        this.mBytesStreamed += j6;
    }

    public void incrementSeekForwardCount() {
        this.mSeekForwardCount++;
    }

    public void incrementSeekBackwardsCount() {
        this.mSeekBackwardsCount++;
    }

    public void incrementErrorCount() {
        this.mFatalErrorCount++;
    }

    public void incrementUpshiftCount() {
        this.mUpshiftCount++;
    }

    public void incrementDownshiftCount() {
        this.mDownshiftCount++;
    }

    public void incrementBufferCount() {
        this.mBufferCount++;
    }

    public void incrementPauseCount() {
        this.mPauseCount++;
    }

    public void setInitialPlaybackIndexSeconds(Long l) {
        this.mInitialPlaybackIndexSeconds = l;
    }

    public void setFinalPlaybackIndexSeconds(Long l) {
        this.mFinalPlaybackIndexSeconds = l;
    }

    public Long getFinalPlaybackIndexSeconds() {
        return this.mFinalPlaybackIndexSeconds;
    }

    public int getTotalDroppedFrames() {
        return this.mTotalDroppedFrames;
    }

    public void updatePerformanceData(int i, int i2, double d) {
        this.mTotalDroppedFrames = i2;
        this.mDroppedFrames = i;
        this.mFrameRateWindow.append(d);
        this.mFrameRate.append(d);
    }

    public String getPrimitiveSessionId() {
        return this.mPrimitiveSessionId;
    }

    public void buildCommonMetrics(MetricsBuilder metricsBuilder) {
        metricsBuilder.avgStreamingFrameRate(this.mFrameRateWindow.getArithmeticMeanOrNull()).bytesStreamed(Long.valueOf(this.mBytesStreamed)).downloadBandwidth(this.mBandwidthWindow.getArithmeticMeanOrNull()).droppedFrames(Integer.valueOf(this.mDroppedFrames)).errorCount(Integer.valueOf(this.mFatalErrorCount)).primitiveSessionId(this.mPrimitiveSessionId);
        metricsBuilder.streamingSessionId(this.mPrimitiveSessionId);
    }

    public void buildSessionMetrics(MetricsBuilder metricsBuilder) {
        metricsBuilder.avgBandwidth(this.mBandwidth.getArithmeticMeanOrNull()).avgBufferFullness(this.mBufferFullness.getArithmeticMeanOrNull()).avgDownloadTime(this.mDownloadTime.getArithmeticMeanOrNull()).avgFragmentBitrate(this.mBitrate.getArithmeticMeanOrNull()).avgFrameRate(this.mFrameRate.getArithmeticMeanOrNull()).avgMaxBufferSize(this.mMaxBuffer.getArithmeticMeanOrNull()).bufferCount(Integer.valueOf(this.mBufferCount)).downshiftCount(Integer.valueOf(this.mDownshiftCount)).droppedFrameCount(Integer.valueOf(this.mTotalDroppedFrames)).ffCount(this.mSeekForwardCount).fromTimecode(this.mInitialPlaybackIndexSeconds).pauseCount(this.mPauseCount).rewCount(this.mSeekBackwardsCount).stddevBandwidth(this.mBandwidth.getStandardDeviationOrNull()).stddevBufferFullness(this.mBufferFullness.getStandardDeviationOrNull()).stddevDownloadTime(this.mDownloadTime.getStandardDeviationOrNull()).stddevFragmentBitrate(this.mBitrate.getStandardDeviationOrNull()).stddevFrameRate(this.mFrameRate.getStandardDeviationOrNull()).stddevMaxBufferSize(this.mMaxBuffer.getStandardDeviationOrNull()).toTimecode(this.mFinalPlaybackIndexSeconds).upshiftCount(Integer.valueOf(this.mUpshiftCount));
    }
}
