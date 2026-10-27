package com.amazon.avod.dash.config.miyagi;

import com.amazon.avod.content.dash.quality.heuristic.DownloadConfig;
import com.amazon.avod.content.dash.quality.heuristic.ReviewDownloadProgressStrategy;
import com.amazon.avod.content.dash.quality.heuristic.TimeoutStrategy;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
class FixedDownloadConfig extends HeuristicsConfigBase implements DownloadConfig {
    private final int mBandwidthAverageObservationWindow;
    private final int mBandwidthAverageSamplingWindow;
    private final int mBandwidthBaselineSampleWindow;
    private final int mBandwidthStdDevObservationWindow;
    private final int mBandwidthStdDevSamplingWindow;
    private final int mBandwidthUpgradeSampleWindow;
    private final int mBaselineFragmentOffset;
    private final double mBaselineShare;
    private final int mConsumptionHeadPadding;
    private final int mFragmentDownloadAttemptInfoRetention;
    private final int mFragmentDownloadInfoRetention;
    private final int mFrontBufferFullnessForReviewDownloadProgress;
    private final double mHeadRoomShareStep;
    private final int mLatencyAverageObservationWindow;
    private final int mLatencyAverageSamplingWindow;
    private final int mLatencyStdDevObservationWindow;
    private final int mLatencyStdDevSamplingWindow;
    private final int mMaxAverageBandwidthToLoad;
    private final int mMaxConcurrentRequests;
    private final int mMaxFutureIndexDownloadLimit;
    private final double mMaxHeadRoomShare;
    private final int mMinAverageBandwidthToLoad;
    private final double mMinHeadRoomShare;
    private final int mMinPercentToContinueDownload;
    private final String mReviewDownloadProgressStrategy;
    private final int mScaleFactorForBonusTimeout;
    private final String mTimeoutCalculationStrategy;
    private final double mUpgradeTolerableRate;

    FixedDownloadConfig(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, DownloadConfig downloadConfig) {
        super(map, map2, map3);
        this.mMaxConcurrentRequests = getIntegerConfigValue("scheduler_maxConcurrentRequests", downloadConfig.getMaxConcurrentRequests());
        this.mMaxFutureIndexDownloadLimit = getIntegerConfigValue("scheduler_maxFutureDownloadFragments", downloadConfig.getMaxFutureIndexDownloadLimit());
        this.mFragmentDownloadInfoRetention = getIntegerConfigValue("retention_fragmentsToRetain", downloadConfig.getFragmentDownloadInfoRetention());
        this.mFragmentDownloadAttemptInfoRetention = getIntegerConfigValue("retention_requestsPerFragmentToRetain", downloadConfig.getFragmentDownloadAttemptInfoRetention());
        this.mBandwidthAverageObservationWindow = getIntegerConfigValue("bandwidthPredictor_averageRequestWindow", downloadConfig.getBandwidthAverageObservationWindow());
        this.mBandwidthAverageSamplingWindow = getIntegerConfigValue("bandwidthPredictor_averageFragmentWindow", downloadConfig.getBandwidthAverageSamplingWindow());
        this.mBandwidthStdDevObservationWindow = getIntegerConfigValue("bandwidthPredictor_stdDevRequestWindow", downloadConfig.getBandwidthStdDevObservationWindow());
        this.mBandwidthStdDevSamplingWindow = getIntegerConfigValue("bandwidthPredictor_stdDevFragmentWindow", downloadConfig.getBandwidthStdDevSamplingWindow());
        this.mLatencyAverageObservationWindow = getIntegerConfigValue("latencyPredictor_averageRequestWindow", downloadConfig.getLatencyAverageObservationWindow());
        this.mLatencyAverageSamplingWindow = getIntegerConfigValue("latencyPredictor_averageFragmentWindow", downloadConfig.getLatencyAverageSamplingWindow());
        this.mLatencyStdDevObservationWindow = getIntegerConfigValue("latencyPredictor_stdDevRequestWindow", downloadConfig.getLatencyStdDevObservationWindow());
        this.mLatencyStdDevSamplingWindow = getIntegerConfigValue("latencyPredictor_stdDevFragmentWindow", downloadConfig.getLatencyStdDevSamplingWindow());
        DataUnit dataUnit = DataUnit.KILOBITS;
        DataUnit dataUnit2 = DataUnit.BYTES;
        this.mMinAverageBandwidthToLoad = (int) dataUnit.toBytes(getIntegerConfigValue("bandwidthPredictor_minStartingBandwidthKbps", (int) dataUnit2.toKiloBits(downloadConfig.getMinAverageBandwidthToLoad())));
        this.mMaxAverageBandwidthToLoad = (int) dataUnit.toBytes(getIntegerConfigValue("bandwidthPredictor_maxStartingBandwidthKbps", (int) dataUnit2.toKiloBits(downloadConfig.getMaxAverageBandwidthToLoad())));
        this.mMinHeadRoomShare = getDoubleConfigValue("bandwidthPredictor_minBandwidthHeadroomFactor", downloadConfig.getMinHeadRoomShare());
        this.mMaxHeadRoomShare = getDoubleConfigValue("bandwidthPredictor_maxBandwidthHeadroomFactor", downloadConfig.getMaxHeadRoomShare());
        this.mHeadRoomShareStep = getDoubleConfigValue("bandwidthPredictor_bandwidthHeadroomStepFactor", downloadConfig.getHeadRoomShareStep());
        this.mBaselineShare = getDoubleConfigValue("bandwidthPredictor_bandwidthBaselineFactor", downloadConfig.getBaselineShare());
        this.mBaselineFragmentOffset = getIntegerConfigValue("scheduler_upgradeOffsetFragments", downloadConfig.getBaselineFragmentOffset());
        this.mUpgradeTolerableRate = getDoubleConfigValue("download_upgradeTolerableRate", downloadConfig.getUpgradeTolerableRate());
        this.mConsumptionHeadPadding = getIntegerConfigValue("scheduler_upgradeEmptyBufferSkipCountFragments", downloadConfig.getConsumptionHeadPadding());
        this.mBandwidthBaselineSampleWindow = getIntegerConfigValue("download_bandwidthBaselineSampleWindow", downloadConfig.getBandwidthBaselineSampleWindow());
        this.mBandwidthUpgradeSampleWindow = getIntegerConfigValue("download_bandwidthUpgradeSampleWindow", downloadConfig.getBandwidthUpgradeSampleWindow());
        this.mTimeoutCalculationStrategy = getStringConfigValue("download_timeoutCalculationStrategy", downloadConfig.getTimeoutCalculationStrategy());
        this.mReviewDownloadProgressStrategy = getStringConfigValue("download_reviewDownloadProgressStrategy", downloadConfig.getReviewDownloadProgressStrategy());
        this.mFrontBufferFullnessForReviewDownloadProgress = getIntegerConfigValue("download_frontBufferFullnessForReviewDownloadProgress", downloadConfig.getFrontBufferFullnessForReviewDownloadProgress());
        this.mMinPercentToContinueDownload = getIntegerConfigValue("download_minPercentToContinueDownload", downloadConfig.getMinPercentToContinueDownload());
        this.mScaleFactorForBonusTimeout = getIntegerConfigValue("download_scaleFactorForBonusTimeout", downloadConfig.getScaleFactorForBonusTimeout());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getMaxConcurrentRequests() {
        return this.mMaxConcurrentRequests;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getMaxFutureIndexDownloadLimit() {
        return this.mMaxFutureIndexDownloadLimit;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getFragmentDownloadInfoRetention() {
        return this.mFragmentDownloadInfoRetention;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getFragmentDownloadAttemptInfoRetention() {
        return this.mFragmentDownloadAttemptInfoRetention;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthAverageObservationWindow() {
        return this.mBandwidthAverageObservationWindow;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthAverageSamplingWindow() {
        return this.mBandwidthAverageSamplingWindow;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthStdDevObservationWindow() {
        return this.mBandwidthStdDevObservationWindow;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthStdDevSamplingWindow() {
        return this.mBandwidthStdDevSamplingWindow;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getLatencyAverageObservationWindow() {
        return this.mLatencyAverageObservationWindow;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getLatencyAverageSamplingWindow() {
        return this.mLatencyAverageSamplingWindow;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getLatencyStdDevObservationWindow() {
        return this.mLatencyStdDevObservationWindow;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getLatencyStdDevSamplingWindow() {
        return this.mLatencyStdDevSamplingWindow;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getMinAverageBandwidthToLoad() {
        return this.mMinAverageBandwidthToLoad;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getMaxAverageBandwidthToLoad() {
        return this.mMaxAverageBandwidthToLoad;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public double getMinHeadRoomShare() {
        return this.mMinHeadRoomShare;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public double getMaxHeadRoomShare() {
        return this.mMaxHeadRoomShare;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public double getHeadRoomShareStep() {
        return this.mHeadRoomShareStep;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public double getBaselineShare() {
        return this.mBaselineShare;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBaselineFragmentOffset() {
        return this.mBaselineFragmentOffset;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public double getUpgradeTolerableRate() {
        return this.mUpgradeTolerableRate;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getConsumptionHeadPadding() {
        return this.mConsumptionHeadPadding;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthBaselineSampleWindow() {
        return this.mBandwidthBaselineSampleWindow;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthUpgradeSampleWindow() {
        return this.mBandwidthUpgradeSampleWindow;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    @Nonnull
    public String getTimeoutCalculationStrategy() {
        return this.mTimeoutCalculationStrategy;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getValidatedTimeoutStrategy() {
        try {
            return TimeoutStrategy.valueOf(getTimeoutCalculationStrategy()).ordinal();
        } catch (IllegalArgumentException | NullPointerException e) {
            DLog.exceptionf(e, "Unable to get timeout strategy from config, returning default", new Object[0]);
            return TimeoutStrategy.FRAGMENT_DURATION.ordinal();
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    @Nonnull
    public String getReviewDownloadProgressStrategy() {
        return this.mReviewDownloadProgressStrategy;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getValidatedReviewDownloadProgressStrategy() {
        try {
            return ReviewDownloadProgressStrategy.valueOf(getReviewDownloadProgressStrategy()).ordinal();
        } catch (IllegalArgumentException | NullPointerException e) {
            DLog.exceptionf(e, "Unable to get review download progress strategy from config, returning default", new Object[0]);
            return ReviewDownloadProgressStrategy.NO_REVIEW_DOWNLOAD_PROGRESS.ordinal();
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getFrontBufferFullnessForReviewDownloadProgress() {
        return this.mFrontBufferFullnessForReviewDownloadProgress;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getMinPercentToContinueDownload() {
        return this.mMinPercentToContinueDownload;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getScaleFactorForBonusTimeout() {
        return this.mScaleFactorForBonusTimeout;
    }
}
