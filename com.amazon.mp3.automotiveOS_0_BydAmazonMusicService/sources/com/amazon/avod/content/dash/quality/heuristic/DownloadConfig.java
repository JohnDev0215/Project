package com.amazon.avod.content.dash.quality.heuristic;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadConfig {
    int getBandwidthAverageObservationWindow();

    int getBandwidthAverageSamplingWindow();

    int getBandwidthBaselineSampleWindow();

    int getBandwidthStdDevObservationWindow();

    int getBandwidthStdDevSamplingWindow();

    int getBandwidthUpgradeSampleWindow();

    int getBaselineFragmentOffset();

    double getBaselineShare();

    int getConsumptionHeadPadding();

    int getFragmentDownloadAttemptInfoRetention();

    int getFragmentDownloadInfoRetention();

    int getFrontBufferFullnessForReviewDownloadProgress();

    double getHeadRoomShareStep();

    int getLatencyAverageObservationWindow();

    int getLatencyAverageSamplingWindow();

    int getLatencyStdDevObservationWindow();

    int getLatencyStdDevSamplingWindow();

    int getMaxAverageBandwidthToLoad();

    int getMaxConcurrentRequests();

    int getMaxFutureIndexDownloadLimit();

    double getMaxHeadRoomShare();

    int getMinAverageBandwidthToLoad();

    double getMinHeadRoomShare();

    int getMinPercentToContinueDownload();

    @Nonnull
    String getReviewDownloadProgressStrategy();

    int getScaleFactorForBonusTimeout();

    @Nonnull
    String getTimeoutCalculationStrategy();

    double getUpgradeTolerableRate();

    int getValidatedReviewDownloadProgressStrategy();

    int getValidatedTimeoutStrategy();
}
