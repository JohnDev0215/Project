package com.amazon.avod.dash.config.g2s2;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.dash.quality.heuristic.GraphConfig;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.google.common.collect.Lists;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class G2S2GraphConfigLive extends MediaConfigBase implements GraphConfig {
    private static final String BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES_KEY = "graph_bandwidthConfidenceScaleFactorGraphXValues_live";
    private static final String BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES_KEY = "graph_bandwidthConfidenceScaleFactorGraphYValues_live";
    private static final String BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_X_VALUES_KEY = "graph_bitrateDownshiftThresholdGraphXValues_live";
    private static final String BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_Y_VALUES_KEY = "graph_bitrateDownshiftThresholdGraphYValues_live";
    private static final String BITRATE_UPSHIFT_LIMIT_GRAPH_X_VALUES_KEY = "graph_bitrateUpshiftLimitGraphXValues_live";
    private static final String BITRATE_UPSHIFT_LIMIT_GRAPH_Y_VALUES_KEY = "graph_bitrateUpshiftLimitGraphYValues_live";
    private static final String FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES_KEY = "graph_fragmentDrainRatioGraphXValues_live";
    private static final String FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES_KEY = "graph_fragmentDrainRatioGraphYValues_live";
    private static final String LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES_KEY = "graph_latencyConfidenceScaleFactorGraphXValues_live";
    private static final String LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES_KEY = "graph_latencyConfidenceScaleFactorGraphYValues_live";
    private static final String LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES_KEY = "graph_liveFragmentDrainRatioGraphXValues_live";
    private static final String LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES_KEY = "graph_liveFragmentDrainRatioGraphYValues_live";
    private static final String RETRY_QUALITY_PENALTY_GRAPH_X_VALUES_KEY = "graph_retryQualityPenaltyGraphXValues_live";
    private static final String RETRY_QUALITY_PENALTY_GRAPH_Y_VALUES_KEY = "graph_retryQualityPenaltyGraphYValues_live";
    private static final String TIME_OUT_GRAPH_X_VALUES_KEY = "graph_timeOutGraphXValues_live";
    private static final String TIME_OUT_GRAPH_Y_VALUES_KEY = "graph_timeOutGraphYValues_live";
    private final ConfigurationValue<List<String>> mGraph_bandwidthConfidenceScaleFactorGraphXValues_live;
    private final ConfigurationValue<List<String>> mGraph_bandwidthConfidenceScaleFactorGraphYValues_live;
    private final ConfigurationValue<List<String>> mGraph_bitrateDownshiftThresholdGraphXValues_live;
    private final ConfigurationValue<List<String>> mGraph_bitrateDownshiftThresholdGraphYValues_live;
    private final ConfigurationValue<List<String>> mGraph_bitrateUpshiftLimitGraphXValues_live;
    private final ConfigurationValue<List<String>> mGraph_bitrateUpshiftLimitGraphYValues_live;
    private final ConfigurationValue<List<String>> mGraph_fragmentDrainRatioGraphXValues_live;
    private final ConfigurationValue<List<String>> mGraph_fragmentDrainRatioGraphYValues_live;
    private final ConfigurationValue<List<String>> mGraph_latencyConfidenceScaleFactorGraphXValues_live;
    private final ConfigurationValue<List<String>> mGraph_latencyConfidenceScaleFactorGraphYValues_live;
    private final ConfigurationValue<List<String>> mGraph_liveFragmentDrainRatioGraphXValues_live;
    private final ConfigurationValue<List<String>> mGraph_liveFragmentDrainRatioGraphYValues_live;
    private final ConfigurationValue<List<String>> mGraph_retryQualityPenaltyGraphXValues_live;
    private final ConfigurationValue<List<String>> mGraph_retryQualityPenaltyGraphYValues_live;
    private final ConfigurationValue<List<String>> mGraph_timeOutGraphXValues_live;
    private final ConfigurationValue<List<String>> mGraph_timeOutGraphYValues_live;
    private static final List<String> DEFAULT_TIME_OUT_GRAPH_X_VALUES = Lists.newArrayList("10.0", "30.0", "40.0", "60.0", "90.0");
    private static final List<String> DEFAULT_TIME_OUT_GRAPH_Y_VALUES = Lists.newArrayList("1.5", "2.5", "2.8", "3.0", "3.8");
    private static final List<String> DEFAULT_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES = Lists.newArrayList("4.0", "8.0", "16.0", "24.0", "30.0", "40.0", "50.0", "60.0");
    private static final List<String> DEFAULT_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES = Lists.newArrayList("0.15", "0.2", "0.3", "0.55", "0.7", "0.9", "1.0", "1.2");
    private static final List<String> DEFAULT_BITRATE_UPSHIFT_LIMIT_GRAPH_X_VALUES = Lists.newArrayList("10.0", "10.1", "30.0", "30.1", "45.0", "45.1", "60.0", "60.1");
    private static final List<String> DEFAULT_BITRATE_UPSHIFT_LIMIT_GRAPH_Y_VALUES = Lists.newArrayList("0.0", "0.7", "0.7", "2.0", "2.0", "4.0", "4.0", "1000.0");
    private static final List<String> DEFAULT_BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_X_VALUES = Lists.newArrayList("6.0", "18.0", "32.0", "46.0", "62.0", "92.0", "122.0", "182.0");
    private static final List<String> DEFAULT_BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_Y_VALUES = Lists.newArrayList("0.0", "-0.1", "-0.2", "-0.3", "-0.32", "-0.33", "-0.4", "-1.0");
    private static final List<String> DEFAULT_RETRY_QUALITY_PENALTY_GRAPH_X_VALUES = Lists.newArrayList("10.0", "18.0", "32.0", "62.0", "92.0", "122.0");
    private static final List<String> DEFAULT_RETRY_QUALITY_PENALTY_GRAPH_Y_VALUES = Lists.newArrayList("0.78", "0.74", "0.59", "0.39", "0.14", "0.1");
    private static final List<String> DEFAULT_BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES = Lists.newArrayList("0.0", "30.0", "60.0");
    private static final List<String> DEFAULT_BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES = Lists.newArrayList("0.01", "0.05", "0.25");
    private static final List<String> DEFAULT_LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES = Lists.newArrayList("0.0", "30.0", "60.0");
    private static final List<String> DEFAULT_LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES = Lists.newArrayList("0.01", "0.05", "0.25");
    private static final List<String> DEFAULT_LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES = Lists.newArrayList("10.0", "20.0");
    private static final List<String> DEFAULT_LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES = Lists.newArrayList("0.5", "1.0");

    public static G2S2GraphConfigLive getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final G2S2GraphConfigLive INSTANCE = new G2S2GraphConfigLive();

        private SingletonHolder() {
        }
    }

    private G2S2GraphConfigLive() {
        this.mGraph_timeOutGraphXValues_live = newSemicolonDelimitedStringListConfigurationValue(TIME_OUT_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_TIME_OUT_GRAPH_X_VALUES));
        this.mGraph_timeOutGraphYValues_live = newSemicolonDelimitedStringListConfigurationValue(TIME_OUT_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_TIME_OUT_GRAPH_Y_VALUES));
        this.mGraph_fragmentDrainRatioGraphXValues_live = newSemicolonDelimitedStringListConfigurationValue(FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES));
        this.mGraph_fragmentDrainRatioGraphYValues_live = newSemicolonDelimitedStringListConfigurationValue(FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES));
        this.mGraph_bitrateUpshiftLimitGraphXValues_live = newSemicolonDelimitedStringListConfigurationValue(BITRATE_UPSHIFT_LIMIT_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BITRATE_UPSHIFT_LIMIT_GRAPH_X_VALUES));
        this.mGraph_bitrateUpshiftLimitGraphYValues_live = newSemicolonDelimitedStringListConfigurationValue(BITRATE_UPSHIFT_LIMIT_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BITRATE_UPSHIFT_LIMIT_GRAPH_Y_VALUES));
        this.mGraph_bitrateDownshiftThresholdGraphXValues_live = newSemicolonDelimitedStringListConfigurationValue(BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_X_VALUES));
        this.mGraph_bitrateDownshiftThresholdGraphYValues_live = newSemicolonDelimitedStringListConfigurationValue(BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_Y_VALUES));
        this.mGraph_retryQualityPenaltyGraphXValues_live = newSemicolonDelimitedStringListConfigurationValue(RETRY_QUALITY_PENALTY_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_RETRY_QUALITY_PENALTY_GRAPH_X_VALUES));
        this.mGraph_retryQualityPenaltyGraphYValues_live = newSemicolonDelimitedStringListConfigurationValue(RETRY_QUALITY_PENALTY_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_RETRY_QUALITY_PENALTY_GRAPH_Y_VALUES));
        this.mGraph_bandwidthConfidenceScaleFactorGraphXValues_live = newSemicolonDelimitedStringListConfigurationValue(BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES));
        this.mGraph_bandwidthConfidenceScaleFactorGraphYValues_live = newSemicolonDelimitedStringListConfigurationValue(BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES));
        this.mGraph_latencyConfidenceScaleFactorGraphXValues_live = newSemicolonDelimitedStringListConfigurationValue(LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES));
        this.mGraph_latencyConfidenceScaleFactorGraphYValues_live = newSemicolonDelimitedStringListConfigurationValue(LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES));
        this.mGraph_liveFragmentDrainRatioGraphXValues_live = newSemicolonDelimitedStringListConfigurationValue(LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES));
        this.mGraph_liveFragmentDrainRatioGraphYValues_live = newSemicolonDelimitedStringListConfigurationValue(LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES));
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getTimeOutGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_timeOutGraphXValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getTimeOutGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_timeOutGraphYValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getFragmentDrainRatioGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_fragmentDrainRatioGraphXValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getFragmentDrainRatioGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_fragmentDrainRatioGraphYValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateUpshiftLimitGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bitrateUpshiftLimitGraphXValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateUpshiftLimitGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bitrateUpshiftLimitGraphYValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateDownshiftThresholdGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bitrateDownshiftThresholdGraphXValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateDownshiftThresholdGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bitrateDownshiftThresholdGraphYValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getRetryQualityPenaltyGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_retryQualityPenaltyGraphXValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getRetryQualityPenaltyGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_retryQualityPenaltyGraphYValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBandwidthConfidenceScaleFactorGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bandwidthConfidenceScaleFactorGraphXValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBandwidthConfidenceScaleFactorGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bandwidthConfidenceScaleFactorGraphYValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getLatencyConfidenceScaleFactorGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_latencyConfidenceScaleFactorGraphXValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getLatencyConfidenceScaleFactorGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_latencyConfidenceScaleFactorGraphYValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getLiveFragmentDrainRatioGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_liveFragmentDrainRatioGraphXValues_live.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getLiveFragmentDrainRatioGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_liveFragmentDrainRatioGraphYValues_live.getValue());
    }
}
