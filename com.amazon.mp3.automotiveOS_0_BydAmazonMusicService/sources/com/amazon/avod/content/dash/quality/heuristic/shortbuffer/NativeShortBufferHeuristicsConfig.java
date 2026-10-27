package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface NativeShortBufferHeuristicsConfig {
    int getAudioSubtitleConcurrentDownloadRequests();

    long getAudioSubtitleDownloadTimeoutNanos();

    double[] getBandwidthKbpsToDrainedBandwidthRatioGraphXValue();

    double[] getBandwidthKbpsToDrainedBandwidthRatioGraphYValue();

    double[] getBandwidthKbpsToDrainedBandwidthRatioWhenNotPlayingXValue();

    double[] getBandwidthKbpsToDrainedBandwidthRatioWhenNotPlayingYValue();

    double[] getBufferDurationMillisToDrainedBandwidthRatioXVvalue();

    double[] getBufferDurationMillisToDrainedBandwidthRatioYVvalue();

    long getBufferThresholdToRestrictConcurrentDownloadsMillis();

    float getConfidenceStep();

    float getFragmentSizeOverhead();

    float getMaxConfidence();

    long getMaxVideoDownloadTimeoutNanos();

    float getMinConfidence();

    long getMinVideoDownloadTimeoutNanos();

    int getNumberOfFutureFragmentsToCheck();

    int getNumberOfLatencySamples();

    @Nonnull
    String getReviewDownloadProgressStrategy();

    float getStartConfidence();

    int getStartVideoBitrateBitsPerSecond();

    @Nonnull
    String getTimeoutCalculationStrategy();

    long getUnReviewTimeNanos();

    int getValidatedReviewDownloadProgressStrategy();

    int getValidatedTimeoutStrategy();

    int getVideoConcurrentDownloadRequests();

    long getVideoDownloadReservedTimeNanos();

    long getVideoDownloadTimeoutWhenNotPlayingNanos();
}
