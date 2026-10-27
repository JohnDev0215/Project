package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

import com.amazon.avod.content.dash.quality.heuristic.ReviewDownloadProgressStrategy;
import com.amazon.avod.content.dash.quality.heuristic.TimeoutStrategy;
import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class G2S2NativeShortBufferHeuristicsConfig implements NativeShortBufferHeuristicsConfig {
    private final ShortBufferHeuristicsConfig mConfig;

    public static G2S2NativeShortBufferHeuristicsConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final G2S2NativeShortBufferHeuristicsConfig INSTANCE = new G2S2NativeShortBufferHeuristicsConfig(ShortBufferHeuristicsConfig.getInstance());

        private SingletonHolder() {
        }
    }

    private G2S2NativeShortBufferHeuristicsConfig(ShortBufferHeuristicsConfig shortBufferHeuristicsConfig) {
        this.mConfig = shortBufferHeuristicsConfig;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getAudioSubtitleConcurrentDownloadRequests() {
        return this.mConfig.getAudioSubtitleConcurrentDownloadRequests();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getVideoConcurrentDownloadRequests() {
        return this.mConfig.getVideoConcurrentDownloadRequests();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getNumberOfFutureFragmentsToCheck() {
        return this.mConfig.getNumberOfFutureFragmentsToCheck();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getAudioSubtitleDownloadTimeoutNanos() {
        return this.mConfig.getAudioSubtitleDownloadTimeout().getTotalNanoSeconds();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getMinVideoDownloadTimeoutNanos() {
        return this.mConfig.getMinVideoDownloadTimeout().getTotalNanoSeconds();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getMaxVideoDownloadTimeoutNanos() {
        return this.mConfig.getMaxVideoDownloadTimeout().getTotalNanoSeconds();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getVideoDownloadReservedTimeNanos() {
        return this.mConfig.getVideoDownloadReservedTime().getTotalNanoSeconds();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public float getMinConfidence() {
        return this.mConfig.getMinConfidence();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public float getMaxConfidence() {
        return this.mConfig.getMaxConfidence();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public float getStartConfidence() {
        return this.mConfig.getStartConfidence();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public float getConfidenceStep() {
        return this.mConfig.getConfidenceStep();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public float getFragmentSizeOverhead() {
        return this.mConfig.getFragmentSizeOverhead();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBufferDurationMillisToDrainedBandwidthRatioXVvalue() {
        return this.mConfig.getBufferDurationMillisToDrainedBandwidthRatioGraph().getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBufferDurationMillisToDrainedBandwidthRatioYVvalue() {
        return this.mConfig.getBufferDurationMillisToDrainedBandwidthRatioGraph().getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBandwidthKbpsToDrainedBandwidthRatioGraphXValue() {
        return this.mConfig.getBandwidthKbpsToDrainedBandwidthRatioGraph().getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBandwidthKbpsToDrainedBandwidthRatioGraphYValue() {
        return this.mConfig.getBandwidthKbpsToDrainedBandwidthRatioGraph().getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getStartVideoBitrateBitsPerSecond() {
        return this.mConfig.getStartVideoBitrateBitsPerSecond();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBandwidthKbpsToDrainedBandwidthRatioWhenNotPlayingXValue() {
        return this.mConfig.getBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying().getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public double[] getBandwidthKbpsToDrainedBandwidthRatioWhenNotPlayingYValue() {
        return this.mConfig.getBandwidthKbpsToDrainedBandwidthRatioGraphWhenNotPlaying().getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getUnReviewTimeNanos() {
        return this.mConfig.getUnReviewTime().getTotalNanoSeconds();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public long getVideoDownloadTimeoutWhenNotPlayingNanos() {
        return this.mConfig.getVideoDownloadTimeoutWhenNotPlaying().getTotalNanoSeconds();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    public int getNumberOfLatencySamples() {
        return this.mConfig.getNumberOfLatencySamples();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig
    @Nonnull
    public String getTimeoutCalculationStrategy() {
        return this.mConfig.getTimeoutCalculationStrategy().name();
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
        return this.mConfig.getReviewDownloadProgressStrategy().name();
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
        return this.mConfig.getBufferThresholdToRestrictConcurrentDownloads().getTotalMilliseconds();
    }
}
