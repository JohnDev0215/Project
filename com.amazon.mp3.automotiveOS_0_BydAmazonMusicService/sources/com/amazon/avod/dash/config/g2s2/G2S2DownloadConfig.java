package com.amazon.avod.dash.config.g2s2;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.dash.quality.heuristic.DownloadConfig;
import com.amazon.avod.content.dash.quality.heuristic.ReviewDownloadProgressStrategy;
import com.amazon.avod.content.dash.quality.heuristic.TimeoutStrategy;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class G2S2DownloadConfig extends MediaConfigBase implements DownloadConfig {
    private static final String BANDWIDTH_AVGERAGE_OBSERVATION_WINDOW_KEY = "download_bandwidthAverageObservationWindow";
    private static final String BANDWIDTH_AVGERAGE_SAMPLING_WINDOW_KEY = "download_bandwidthAverageSamplingWindow";
    private static final String BANDWIDTH_BASELINE_SAMPLE_WINDOW_KEY = "download_bandwidthBaselineSampleWindow";
    private static final Integer BANDWIDTH_BASELINE_SAMPLE_WINDOW_VALUE;
    private static final String BANDWIDTH_STDDEV_OBSERVATION_WINDOW_KEY = "download_bandwidthStdDevObservationWindow";
    private static final String BANDWIDTH_STDDEV_SAMPLING_WINDOW_KEY = "download_bandwidthStdDevSamplingWindow";
    private static final String BANDWIDTH_UPGRADE_SAMPLE_WINDOW_KEY = "download_bandwidthUpgradeSampleWindow";
    private static final Integer BANDWIDTH_UPGRADE_SAMPLE_WINDOW_VALUE;
    private static final String BASELINE_FRAGMENT_OFFSET_KEY = "download_baselineFragmentOffset";
    private static final Integer BASELINE_FRAGMENT_OFFSET_VALUE;
    private static final String BASELINE_SHARE_KEY = "download_baselineShare";
    private static final Float BASELINE_SHARE_VALUE;
    private static final String CONSUMPTION_HEAD_PADDING_KEY = "download_consumptionHeadPadding";
    private static final Integer CONSUMPTION_HEAD_PADDING_VALUE;
    private static final Integer DEFAULT_MAX_AVERAGE_BANDWIDTH_TO_LOAD;
    private static final String DEFAULT_REVIEW_DOWNLOAD_PROGRES_STRATEGY;
    private static final String DEFAULT_TIMEOUT_CALCULATION_STRATEGY;
    private static final String FRAGMENT_DOWNLOAD_ATTEMPT_INFO_RETENTION_KEY = "download_fragmentDownloadAttemptInfoRetention";
    private static final String FRAGMENT_DOWNLOAD_INFO_RETENTION_KEY = "download_fragmentDownloadInfoRetention";
    private static final String FRONT_BUFFER_FULLNESS_FOR_RDP_KEY = "download_frontBufferFullnessForReviewDownloadProgress";
    private static final Integer FRONT_BUFFER_FULLNESS_FOR_RDP_VALUE;
    private static final String HEAD_ROOM_SHARE_STEP_KEY = "download_headRoomShareStep";
    private static final Float HEAD_ROOM_SHARE_STEP_VALUE;
    private static final String LATENCY_AVGERAGE_OBSERVATION_WINDOW_KEY = "download_latencyAverageObservationWindow";
    private static final String LATENCY_AVGERAGE_SAMPLING_WINDOW_KEY = "download_latencyAverageSamplingWindow";
    private static final String LATENCY_STDDEVE_SAMPLING_WINDOW_KEY = "download_latencyStdDevSamplingWindow";
    private static final String LATENCY_STDDEV_OBSERVATION_WINDOW_KEY = "download_latencyStdDevObservationWindow";
    private static final String MAX_AVERAGE_BANDWIDTH_TO_LOAD_KEY = "download_maxAverageBandwidthToLoad";
    private static final String MAX_CONCURRENT_REQUESTS_KEY = "download_maxConcurrentRequests";
    private static final String MAX_FUTURE_INDEX_DOWNLOAD_LIMIT_KEY = "download_maxFutureIndexDownloadLimit";
    private static final String MAX_HEAD_ROOM_SHARE_KEY = "download_maxHeadRoomShare_2";
    private static final String MIN_AVERAGE_BANDWIDTH_TO_LOAD_KEY = "download_minAverageBandwidthToLoad";
    private static final String MIN_HEAD_ROOM_SHARE_KEY = "download_minHeadRoomShare_2";
    private static final Float MIN_HEAD_ROOM_SHARE_VALUE;
    private static final String MIN_PERCENT_TO_CONTINUE_DOWNLOAD_KEY = "download_minPercentToContinueDownload";
    private static final Integer MIN_PERCENT_TO_CONTINUE_DOWNLOAD_VALUE;
    private static final String REVIEW_DOWNLOAD_PROGRESS_STRATEGY = "download_reviewDownloadProgressStrategy";
    private static final String SCALE_FACTOR_FOR_BONUS_TIMEOUT_KEY = "download_scaleFactorForBonusTimeout";
    private static final Integer SCALE_FACTOR_FOR_BONUS_TIMEOUT_VALUE;
    private static final String TIMEOUT_CALCULATION_STRATEGY = "download_timeoutCalculationStrategy";
    private static final String UPGRADE_TOLERABLE_RATE_KEY = "download_upgradeTolerableRate";
    private static final Float UPGRADE_TOLERABLE_RATE_VALUE;
    private static final Integer DEFAULT_MAX_CONCURRENT_REQUESTS = 2;
    private static final Integer DEFAULT_MAX_FUTURE_INDEX_DOWNLOAD_LIMIT = 3;
    private static final Integer DEFAULT_FRAGMENT_DOWNLOAD_INFO_RETENTION = 10;
    private static final Integer DEFAULT_FRAGMENT_DOWNLOAD_ATTEMPT_INFO_RETENTION = 4;
    private static final Integer DEFAULT_BANDWIDTH_AVGERAGE_OBSERVATION_WINDOW = 6;
    private static final Integer DEFAULT_BANDWIDTH_AVGERAGE_SAMPLING_WINDOW = 3;
    private static final Integer DEFAULT_BANDWIDTH_STDDEV_OBSERVATION_WINDOW = 10;
    private static final Integer DEFAULT_BANDWIDTH_STDDEV_SAMPLING_WINDOW = 5;
    private static final Integer DEFAULT_LATENCY_AVGERAGE_OBSERVATION_WINDOW = 6;
    private static final Integer DEFAULT_LATENCY_AVGERAGE_SAMPLING_WINDOW = 3;
    private static final Integer DEFAULT_LATENCY_STDDEV_OBSERVATION_WINDOW = 10;
    private static final Integer DEFAULT_LATENCY_STDDEV_SAMPLING_WINDOW = 5;
    private static final Integer DEFAULT_MIN_AVERAGE_BANDWIDTH_TO_LOAD = 262144;
    private static final Float MAX_HEAD_ROOM_SHARE_VALUE = Float.valueOf(0.5f);
    private final ConfigurationValue<Integer> mDownload_maxConcurrentRequests = newIntConfigValue(MAX_CONCURRENT_REQUESTS_KEY, DEFAULT_MAX_CONCURRENT_REQUESTS.intValue());
    private final ConfigurationValue<Integer> mDownload_maxFutureIndexDownloadLimit = newIntConfigValue(MAX_FUTURE_INDEX_DOWNLOAD_LIMIT_KEY, DEFAULT_MAX_FUTURE_INDEX_DOWNLOAD_LIMIT.intValue());
    private final ConfigurationValue<Integer> mDownload_fragmentDownloadInfoRetention = newIntConfigValue(FRAGMENT_DOWNLOAD_INFO_RETENTION_KEY, DEFAULT_FRAGMENT_DOWNLOAD_INFO_RETENTION.intValue());
    private final ConfigurationValue<Integer> mDownload_fragmentDownloadAttemptInfoRetention = newIntConfigValue(FRAGMENT_DOWNLOAD_ATTEMPT_INFO_RETENTION_KEY, DEFAULT_FRAGMENT_DOWNLOAD_ATTEMPT_INFO_RETENTION.intValue());
    private final ConfigurationValue<Integer> mDownload_bandwidthAverageObservationWindow = newIntConfigValue(BANDWIDTH_AVGERAGE_OBSERVATION_WINDOW_KEY, DEFAULT_BANDWIDTH_AVGERAGE_OBSERVATION_WINDOW.intValue());
    private final ConfigurationValue<Integer> mDownload_bandwidthAverageSamplingWindow = newIntConfigValue(BANDWIDTH_AVGERAGE_SAMPLING_WINDOW_KEY, DEFAULT_BANDWIDTH_AVGERAGE_SAMPLING_WINDOW.intValue());
    private final ConfigurationValue<Integer> mDownload_bandwidthStdDevObservationWindow = newIntConfigValue(BANDWIDTH_STDDEV_OBSERVATION_WINDOW_KEY, DEFAULT_BANDWIDTH_STDDEV_OBSERVATION_WINDOW.intValue());
    private final ConfigurationValue<Integer> mDownload_bandwidthStdDevSamplingWindow = newIntConfigValue(BANDWIDTH_STDDEV_SAMPLING_WINDOW_KEY, DEFAULT_BANDWIDTH_STDDEV_SAMPLING_WINDOW.intValue());
    private final ConfigurationValue<Integer> mDownload_latencyAverageObservationWindow = newIntConfigValue(LATENCY_AVGERAGE_OBSERVATION_WINDOW_KEY, DEFAULT_LATENCY_AVGERAGE_OBSERVATION_WINDOW.intValue());
    private final ConfigurationValue<Integer> mDownload_latencyAverageSamplingWindow = newIntConfigValue(LATENCY_AVGERAGE_SAMPLING_WINDOW_KEY, DEFAULT_LATENCY_AVGERAGE_SAMPLING_WINDOW.intValue());
    private final ConfigurationValue<Integer> mDownload_latencyStdDevObservationWindow = newIntConfigValue(LATENCY_STDDEV_OBSERVATION_WINDOW_KEY, DEFAULT_LATENCY_STDDEV_OBSERVATION_WINDOW.intValue());
    private final ConfigurationValue<Integer> mDownload_latencyStdDevSamplingWindow = newIntConfigValue(LATENCY_STDDEVE_SAMPLING_WINDOW_KEY, DEFAULT_LATENCY_STDDEV_SAMPLING_WINDOW.intValue());
    private final ConfigurationValue<Integer> mDownload_minAverageBandwidthToLoad = newIntConfigValue(MIN_AVERAGE_BANDWIDTH_TO_LOAD_KEY, DEFAULT_MIN_AVERAGE_BANDWIDTH_TO_LOAD.intValue());
    private final ConfigurationValue<Float> mDownload_maxHeadRoomShare = newFloatConfigValue(MAX_HEAD_ROOM_SHARE_KEY, MAX_HEAD_ROOM_SHARE_VALUE.floatValue());
    private final ConfigurationValue<Float> mDownload_minHeadRoomShare = newFloatConfigValue(MIN_HEAD_ROOM_SHARE_KEY, MIN_HEAD_ROOM_SHARE_VALUE.floatValue());
    private final ConfigurationValue<Float> mDownload_headRoomShareStep = newFloatConfigValue(HEAD_ROOM_SHARE_STEP_KEY, HEAD_ROOM_SHARE_STEP_VALUE.floatValue());
    private final ConfigurationValue<Float> mDownload_baselineShare = newFloatConfigValue(BASELINE_SHARE_KEY, BASELINE_SHARE_VALUE.floatValue());
    private final ConfigurationValue<Integer> mDownload_baselineFragmentOffset = newIntConfigValue(BASELINE_FRAGMENT_OFFSET_KEY, BASELINE_FRAGMENT_OFFSET_VALUE.intValue());
    private final ConfigurationValue<Float> mDownload_upgradeTolerableRate = newFloatConfigValue(UPGRADE_TOLERABLE_RATE_KEY, UPGRADE_TOLERABLE_RATE_VALUE.floatValue());
    private final ConfigurationValue<Integer> mDownload_consumptionHeadPadding = newIntConfigValue(CONSUMPTION_HEAD_PADDING_KEY, CONSUMPTION_HEAD_PADDING_VALUE.intValue());
    private final ConfigurationValue<Integer> mDownload_bandwidthBaselineSampleWindow = newIntConfigValue(BANDWIDTH_BASELINE_SAMPLE_WINDOW_KEY, BANDWIDTH_BASELINE_SAMPLE_WINDOW_VALUE.intValue());
    private final ConfigurationValue<Integer> mDownload_bandwidthUpgradeSampleWindow = newIntConfigValue(BANDWIDTH_UPGRADE_SAMPLE_WINDOW_KEY, BANDWIDTH_UPGRADE_SAMPLE_WINDOW_VALUE.intValue());
    private final ConfigurationValue<Integer> mDownload_maxAverageBandwidthToLoad = newIntConfigValue(MAX_AVERAGE_BANDWIDTH_TO_LOAD_KEY, DEFAULT_MAX_AVERAGE_BANDWIDTH_TO_LOAD.intValue());
    private final ConfigurationValue<String> mDownload_timeoutCalculationStrategy = newStringConfigValue(TIMEOUT_CALCULATION_STRATEGY, DEFAULT_TIMEOUT_CALCULATION_STRATEGY);
    private final ConfigurationValue<String> mDownload_reviewDownloadProgressStrategy = newStringConfigValue(REVIEW_DOWNLOAD_PROGRESS_STRATEGY, DEFAULT_REVIEW_DOWNLOAD_PROGRES_STRATEGY);
    private final ConfigurationValue<Integer> mDownload_frontBufferFullnessForReviewDownloadProgress = newIntConfigValue(FRONT_BUFFER_FULLNESS_FOR_RDP_KEY, FRONT_BUFFER_FULLNESS_FOR_RDP_VALUE.intValue());
    private final ConfigurationValue<Integer> mDownload_minPercentToContinueDownload = newIntConfigValue(MIN_PERCENT_TO_CONTINUE_DOWNLOAD_KEY, MIN_PERCENT_TO_CONTINUE_DOWNLOAD_VALUE.intValue());
    private final ConfigurationValue<Integer> mDownload_scaleFactorForBonusTimeout = newIntConfigValue(SCALE_FACTOR_FOR_BONUS_TIMEOUT_KEY, SCALE_FACTOR_FOR_BONUS_TIMEOUT_VALUE.intValue());

    public static G2S2DownloadConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final G2S2DownloadConfig INSTANCE = new G2S2DownloadConfig();

        private SingletonHolder() {
        }
    }

    static {
        Float fValueOf = Float.valueOf(0.1f);
        MIN_HEAD_ROOM_SHARE_VALUE = fValueOf;
        HEAD_ROOM_SHARE_STEP_VALUE = Float.valueOf(0.01f);
        BASELINE_SHARE_VALUE = fValueOf;
        BASELINE_FRAGMENT_OFFSET_VALUE = 2;
        UPGRADE_TOLERABLE_RATE_VALUE = Float.valueOf(1.8f);
        CONSUMPTION_HEAD_PADDING_VALUE = 3;
        BANDWIDTH_BASELINE_SAMPLE_WINDOW_VALUE = 3;
        BANDWIDTH_UPGRADE_SAMPLE_WINDOW_VALUE = 3;
        DEFAULT_MAX_AVERAGE_BANDWIDTH_TO_LOAD = 2621440;
        DEFAULT_TIMEOUT_CALCULATION_STRATEGY = TimeoutStrategy.FRAGMENT_DURATION.name();
        DEFAULT_REVIEW_DOWNLOAD_PROGRES_STRATEGY = ReviewDownloadProgressStrategy.NO_REVIEW_DOWNLOAD_PROGRESS.name();
        FRONT_BUFFER_FULLNESS_FOR_RDP_VALUE = 20;
        MIN_PERCENT_TO_CONTINUE_DOWNLOAD_VALUE = 50;
        SCALE_FACTOR_FOR_BONUS_TIMEOUT_VALUE = 4;
    }

    protected G2S2DownloadConfig() {
    }

    public int getMaxConcurrentRequests() {
        return this.mDownload_maxConcurrentRequests.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getMaxFutureIndexDownloadLimit() {
        return this.mDownload_maxFutureIndexDownloadLimit.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getFragmentDownloadInfoRetention() {
        return this.mDownload_fragmentDownloadInfoRetention.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getFragmentDownloadAttemptInfoRetention() {
        return this.mDownload_fragmentDownloadAttemptInfoRetention.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthAverageObservationWindow() {
        return this.mDownload_bandwidthAverageObservationWindow.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthAverageSamplingWindow() {
        return this.mDownload_bandwidthAverageSamplingWindow.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthStdDevObservationWindow() {
        return this.mDownload_bandwidthStdDevObservationWindow.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthStdDevSamplingWindow() {
        return this.mDownload_bandwidthStdDevSamplingWindow.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getLatencyAverageObservationWindow() {
        return this.mDownload_latencyAverageObservationWindow.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getLatencyAverageSamplingWindow() {
        return this.mDownload_latencyAverageSamplingWindow.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getLatencyStdDevObservationWindow() {
        return this.mDownload_latencyStdDevObservationWindow.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getLatencyStdDevSamplingWindow() {
        return this.mDownload_latencyStdDevSamplingWindow.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getMinAverageBandwidthToLoad() {
        return this.mDownload_minAverageBandwidthToLoad.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public double getMaxHeadRoomShare() {
        return this.mDownload_maxHeadRoomShare.getValue().floatValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public double getMinHeadRoomShare() {
        return this.mDownload_minHeadRoomShare.getValue().floatValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public double getHeadRoomShareStep() {
        return this.mDownload_headRoomShareStep.getValue().floatValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public double getBaselineShare() {
        return this.mDownload_baselineShare.getValue().floatValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBaselineFragmentOffset() {
        return this.mDownload_baselineFragmentOffset.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public double getUpgradeTolerableRate() {
        return this.mDownload_upgradeTolerableRate.getValue().floatValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getConsumptionHeadPadding() {
        return this.mDownload_consumptionHeadPadding.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthBaselineSampleWindow() {
        return this.mDownload_bandwidthBaselineSampleWindow.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getBandwidthUpgradeSampleWindow() {
        return this.mDownload_bandwidthUpgradeSampleWindow.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getMaxAverageBandwidthToLoad() {
        return this.mDownload_maxAverageBandwidthToLoad.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    @Nonnull
    public String getTimeoutCalculationStrategy() {
        return this.mDownload_timeoutCalculationStrategy.getValue();
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
        return this.mDownload_reviewDownloadProgressStrategy.getValue();
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
        return this.mDownload_frontBufferFullnessForReviewDownloadProgress.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getMinPercentToContinueDownload() {
        return this.mDownload_minPercentToContinueDownload.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getScaleFactorForBonusTimeout() {
        return this.mDownload_scaleFactorForBonusTimeout.getValue().intValue();
    }
}
