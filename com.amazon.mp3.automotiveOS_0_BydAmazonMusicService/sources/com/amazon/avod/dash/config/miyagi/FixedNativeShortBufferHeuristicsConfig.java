package com.amazon.avod.dash.config.miyagi;

import com.amazon.avod.content.dash.quality.heuristic.HeuristicsGraph;
import com.amazon.avod.content.dash.quality.heuristic.ReviewDownloadProgressStrategy;
import com.amazon.avod.content.dash.quality.heuristic.TimeoutStrategy;
import com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig;
import com.amazon.avod.util.DLog;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
class FixedNativeShortBufferHeuristicsConfig extends HeuristicsConfigBase implements NativeShortBufferHeuristicsConfig {
    private final int mAudioSubtitleConcurrentDownloadRequests;
    private final long mAudioSubtitleDownloadTimeoutNanos;
    private final HeuristicsGraph mBandwidthKbpsToDrainedBandwidthRatioGraph;
    private final HeuristicsGraph mBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying;
    private final HeuristicsGraph mBufferDurationMillisToDrainedBandwidthRatioGraph;
    private final long mBufferThresholdToRestrictConcurrentDownloadsMillis;
    private final float mConfidenceStep;
    private final float mFragmentSizeOverhead;
    private final float mMaxConfidence;
    private final long mMaxVideoDownloadTimeoutNanos;
    private final float mMinConfidence;
    private final long mMinVideoDownloadTimeoutNanos;
    private final int mNumberOfFutureFragmentsToCheck;
    private final int mNumberOfLatencySamples;
    private final String mReviewDownloadProgressStrategy;
    private final float mStartConfidence;
    private final int mStartVideoBitrateBitsPerSecond;
    private final String mTimeoutCalculationStrategy;
    private final long mUnReviewTimeNanos;
    private final int mVideoConcurrentDownloadRequests;
    private final long mVideoDownloadReservedTimeNanos;
    private final long mVideoDownloadTimeoutWhenNotPlayingNanos;

    FixedNativeShortBufferHeuristicsConfig(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, NativeShortBufferHeuristicsConfig nativeShortBufferHeuristicsConfig) {
        super(map, map2, map3);
        this.mAudioSubtitleConcurrentDownloadRequests = getIntegerConfigValue("sbh_audioSubtitleConcurrentDownloadRequests", nativeShortBufferHeuristicsConfig.getAudioSubtitleConcurrentDownloadRequests());
        this.mVideoConcurrentDownloadRequests = getIntegerConfigValue("sbh_videoConcurrentDownloadRequests", nativeShortBufferHeuristicsConfig.getVideoConcurrentDownloadRequests());
        this.mNumberOfFutureFragmentsToCheck = getIntegerConfigValue("sbh_numberOfFutureFragmentsToCheck", nativeShortBufferHeuristicsConfig.getNumberOfFutureFragmentsToCheck());
        this.mAudioSubtitleDownloadTimeoutNanos = getLongConfigValue("sbh_audioSubtitleDownloadTimeoutNanos", nativeShortBufferHeuristicsConfig.getAudioSubtitleDownloadTimeoutNanos());
        this.mMinVideoDownloadTimeoutNanos = getLongConfigValue("sbh_minVideoDownloadTimeoutNanos", nativeShortBufferHeuristicsConfig.getMinVideoDownloadTimeoutNanos());
        this.mMaxVideoDownloadTimeoutNanos = getLongConfigValue("sbh_maxVideoDownloadTimeoutNanos", nativeShortBufferHeuristicsConfig.getMaxVideoDownloadTimeoutNanos());
        this.mVideoDownloadReservedTimeNanos = getLongConfigValue("sbh_videoDownloadReservedTimeNanos", nativeShortBufferHeuristicsConfig.getVideoDownloadReservedTimeNanos());
        this.mMinConfidence = getFloatConfigValue("sbh_minConfidence", nativeShortBufferHeuristicsConfig.getMinConfidence());
        this.mMaxConfidence = getFloatConfigValue("sbh_maxConfidence", nativeShortBufferHeuristicsConfig.getMaxConfidence());
        this.mStartConfidence = getFloatConfigValue("sbh_startConfidence", nativeShortBufferHeuristicsConfig.getStartConfidence());
        this.mConfidenceStep = getFloatConfigValue("sbh_confidenceStep", nativeShortBufferHeuristicsConfig.getConfidenceStep());
        this.mFragmentSizeOverhead = getFloatConfigValue("sbh_fragmentSizeOverhead", nativeShortBufferHeuristicsConfig.getFragmentSizeOverhead());
        this.mBufferDurationMillisToDrainedBandwidthRatioGraph = getHeuristicsGraphConfigValue("sbh_bufferDurationMillisToDrainedBandwidthRatioGraph", nativeShortBufferHeuristicsConfig.getBufferDurationMillisToDrainedBandwidthRatioXVvalue(), nativeShortBufferHeuristicsConfig.getBufferDurationMillisToDrainedBandwidthRatioYVvalue());
        this.mBandwidthKbpsToDrainedBandwidthRatioGraph = getHeuristicsGraphConfigValue("sbh_bandwidthKbpsToDrainedBandwidthRatioGraph", nativeShortBufferHeuristicsConfig.getBandwidthKbpsToDrainedBandwidthRatioGraphXValue(), nativeShortBufferHeuristicsConfig.getBandwidthKbpsToDrainedBandwidthRatioGraphYValue());
        this.mStartVideoBitrateBitsPerSecond = getIntegerConfigValue("sbh_startVideoBitrateBitsPerSecond", nativeShortBufferHeuristicsConfig.getStartVideoBitrateBitsPerSecond());
        this.mBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying = getHeuristicsGraphConfigValue("sbh_bandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying", nativeShortBufferHeuristicsConfig.getBandwidthKbpsToDrainedBandwidthRatioWhenNotPlayingXValue(), nativeShortBufferHeuristicsConfig.getBandwidthKbpsToDrainedBandwidthRatioWhenNotPlayingYValue());
        this.mUnReviewTimeNanos = getLongConfigValue("sbh_unReviewTimeNanos", nativeShortBufferHeuristicsConfig.getUnReviewTimeNanos());
        this.mVideoDownloadTimeoutWhenNotPlayingNanos = getLongConfigValue("sbh_videoDownloadTimeoutWhenNotPlayingNanos", nativeShortBufferHeuristicsConfig.getVideoDownloadTimeoutWhenNotPlayingNanos());
        this.mNumberOfLatencySamples = getIntegerConfigValue("sbh_numberOfLatencySamples", nativeShortBufferHeuristicsConfig.getNumberOfLatencySamples());
        this.mTimeoutCalculationStrategy = getStringConfigValue("sbh_timeoutCalculationStrategy", nativeShortBufferHeuristicsConfig.getTimeoutCalculationStrategy());
        this.mReviewDownloadProgressStrategy = getStringConfigValue("sbh_reviewDownloadProgressStrategy", nativeShortBufferHeuristicsConfig.getReviewDownloadProgressStrategy());
        this.mBufferThresholdToRestrictConcurrentDownloadsMillis = getLongConfigValue("sbh_bufferThresholdToRestrictConcurrentDownloadsMillis", nativeShortBufferHeuristicsConfig.getBufferThresholdToRestrictConcurrentDownloadsMillis());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getAudioSubtitleConcurrentDownloadRequests() {
        return this.mAudioSubtitleConcurrentDownloadRequests;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getVideoConcurrentDownloadRequests() {
        return this.mVideoConcurrentDownloadRequests;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getNumberOfFutureFragmentsToCheck() {
        return this.mNumberOfFutureFragmentsToCheck;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getAudioSubtitleDownloadTimeoutNanos() {
        return this.mAudioSubtitleDownloadTimeoutNanos;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getMinVideoDownloadTimeoutNanos() {
        return this.mMinVideoDownloadTimeoutNanos;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getMaxVideoDownloadTimeoutNanos() {
        return this.mMaxVideoDownloadTimeoutNanos;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getVideoDownloadReservedTimeNanos() {
        return this.mVideoDownloadReservedTimeNanos;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public float getMinConfidence() {
        return this.mMinConfidence;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public float getMaxConfidence() {
        return this.mMaxConfidence;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public float getStartConfidence() {
        return this.mStartConfidence;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public float getConfidenceStep() {
        return this.mConfidenceStep;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public float getFragmentSizeOverhead() {
        return this.mFragmentSizeOverhead;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBufferDurationMillisToDrainedBandwidthRatioXVvalue() {
        return this.mBufferDurationMillisToDrainedBandwidthRatioGraph.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBufferDurationMillisToDrainedBandwidthRatioYVvalue() {
        return this.mBufferDurationMillisToDrainedBandwidthRatioGraph.getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBandwidthKbpsToDrainedBandwidthRatioGraphXValue() {
        return this.mBandwidthKbpsToDrainedBandwidthRatioGraph.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBandwidthKbpsToDrainedBandwidthRatioGraphYValue() {
        return this.mBandwidthKbpsToDrainedBandwidthRatioGraph.getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getStartVideoBitrateBitsPerSecond() {
        return this.mStartVideoBitrateBitsPerSecond;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBandwidthKbpsToDrainedBandwidthRatioWhenNotPlayingXValue() {
        return this.mBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBandwidthKbpsToDrainedBandwidthRatioWhenNotPlayingYValue() {
        return this.mBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying.getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getUnReviewTimeNanos() {
        return this.mUnReviewTimeNanos;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getVideoDownloadTimeoutWhenNotPlayingNanos() {
        return this.mVideoDownloadTimeoutWhenNotPlayingNanos;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getNumberOfLatencySamples() {
        return this.mNumberOfLatencySamples;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    @Nonnull
    public String getTimeoutCalculationStrategy() {
        return this.mTimeoutCalculationStrategy;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getValidatedTimeoutStrategy() {
        try {
            return TimeoutStrategy.valueOf(getTimeoutCalculationStrategy()).ordinal();
        } catch (IllegalArgumentException | NullPointerException e) {
            DLog.exceptionf(e, "Unable to get timeout strategy from config, returning default", new Object[0]);
            return TimeoutStrategy.BUFFERED_DURATION.ordinal();
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    @Nonnull
    public String getReviewDownloadProgressStrategy() {
        return this.mReviewDownloadProgressStrategy;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getValidatedReviewDownloadProgressStrategy() {
        try {
            return ReviewDownloadProgressStrategy.valueOf(getReviewDownloadProgressStrategy()).ordinal();
        } catch (IllegalArgumentException | NullPointerException e) {
            DLog.exceptionf(e, "Unable to get review download progress strategy from config, returning default", new Object[0]);
            return ReviewDownloadProgressStrategy.EARLY_REVIEW_DOWNLOAD_PROGRESS.ordinal();
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getBufferThresholdToRestrictConcurrentDownloadsMillis() {
        return this.mBufferThresholdToRestrictConcurrentDownloadsMillis;
    }
}
