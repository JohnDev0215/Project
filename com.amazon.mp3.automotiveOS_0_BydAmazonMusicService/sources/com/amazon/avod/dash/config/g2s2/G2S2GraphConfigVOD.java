package com.amazon.avod.dash.config.g2s2;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.dash.quality.heuristic.GraphConfig;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.google.common.collect.Lists;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class G2S2GraphConfigVOD extends MediaConfigBase implements GraphConfig {
    private static final String BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES_KEY = "graph_bandwidthConfidenceScaleFactorGraphXValues";
    private static final String BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES_KEY = "graph_bandwidthConfidenceScaleFactorGraphYValues";
    private static final String BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_X_VALUES_KEY = "graph_bitrateDownshiftThresholdGraphXValues";
    private static final String BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_Y_VALUES_KEY = "graph_bitrateDownshiftThresholdGraphYValues";
    private static final String BITRATE_UPSHIFT_LIMIT_GRAPH_X_VALUES_KEY = "graph_bitrateUpshiftLimitGraphXValues";
    private static final String BITRATE_UPSHIFT_LIMIT_GRAPH_Y_VALUES_KEY = "graph_bitrateUpshiftLimitGraphYValues";
    private static final String FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES_KEY = "graph_fragmentDrainRatioGraphXValues";
    private static final String FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES_KEY = "graph_fragmentDrainRatioGraphYValues";
    private static final String LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES_KEY = "graph_latencyConfidenceScaleFactorGraphXValues";
    private static final String LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES_KEY = "graph_latencyConfidenceScaleFactorGraphYValues";
    private static final String LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES_KEY = "graph_liveFragmentDrainRatioGraphXValues_live";
    private static final String LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES_KEY = "graph_liveFragmentDrainRatioGraphYValues_live";
    private static final String RETRY_QUALITY_PENALTY_GRAPH_X_VALUES_KEY = "graph_retryQualityPenaltyGraphXValues";
    private static final String RETRY_QUALITY_PENALTY_GRAPH_Y_VALUES_KEY = "graph_retryQualityPenaltyGraphYValues";
    private static final String TIME_OUT_GRAPH_X_VALUES_KEY = "graph_timeOutGraphXValues";
    private static final String TIME_OUT_GRAPH_Y_VALUES_KEY = "graph_timeOutGraphYValues";
    private final ConfigurationValue<List<String>> mGraph_bandwidthConfidenceScaleFactorGraphXValues;
    private final ConfigurationValue<List<String>> mGraph_bandwidthConfidenceScaleFactorGraphYValues;
    private final ConfigurationValue<List<String>> mGraph_bitrateDownshiftThresholdGraphXValues;
    private final ConfigurationValue<List<String>> mGraph_bitrateDownshiftThresholdGraphYValues;
    private final ConfigurationValue<List<String>> mGraph_bitrateUpshiftLimitGraphXValues;
    private final ConfigurationValue<List<String>> mGraph_bitrateUpshiftLimitGraphYValues;
    private final ConfigurationValue<List<String>> mGraph_fragmentDrainRatioGraphXValues;
    private final ConfigurationValue<List<String>> mGraph_fragmentDrainRatioGraphYValues;
    private final ConfigurationValue<List<String>> mGraph_latencyConfidenceScaleFactorGraphXValues;
    private final ConfigurationValue<List<String>> mGraph_latencyConfidenceScaleFactorGraphYValues;
    private final ConfigurationValue<List<String>> mGraph_liveFragmentDrainRatioGraphXValues_live;
    private final ConfigurationValue<List<String>> mGraph_liveFragmentDrainRatioGraphYValues_live;
    private final ConfigurationValue<List<String>> mGraph_retryQualityPenaltyGraphXValues;
    private final ConfigurationValue<List<String>> mGraph_retryQualityPenaltyGraphYValues;
    private final ConfigurationValue<List<String>> mGraph_timeOutGraphXValues;
    private final ConfigurationValue<List<String>> mGraph_timeOutGraphYValues;
    private static final List<String> DEFAULT_TIME_OUT_GRAPH_X_VALUES = Lists.newArrayList("10.0", "30.0", "40.0", "60.0", "90.0");
    private static final List<String> DEFAULT_TIME_OUT_GRAPH_Y_VALUES = Lists.newArrayList("1.5", "2.5", "2.8", "3.0", "3.8");
    private static final List<String> DEFAULT_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES = Lists.newArrayList("4.0", "10.0", "16.0", "24.0", "30.0", "44.0", "60.0", "90.0", "120.0");
    private static final List<String> DEFAULT_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES = Lists.newArrayList("0.2", "0.35", "0.4", "0.55", "0.65", "0.8", "1.0", "2.5", "3.5");
    private static final List<String> DEFAULT_BITRATE_UPSHIFT_LIMIT_GRAPH_X_VALUES = Lists.newArrayList("10.0", "10.1", "45.0", "45.1", "60.0", "60.1", "90.0", "120.0");
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

    public static G2S2GraphConfigVOD getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final G2S2GraphConfigVOD INSTANCE = new G2S2GraphConfigVOD();

        private SingletonHolder() {
        }
    }

    private G2S2GraphConfigVOD() {
        this.mGraph_timeOutGraphXValues = newSemicolonDelimitedStringListConfigurationValue(TIME_OUT_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_TIME_OUT_GRAPH_X_VALUES));
        this.mGraph_timeOutGraphYValues = newSemicolonDelimitedStringListConfigurationValue(TIME_OUT_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_TIME_OUT_GRAPH_Y_VALUES));
        this.mGraph_fragmentDrainRatioGraphXValues = newSemicolonDelimitedStringListConfigurationValue(FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES));
        this.mGraph_fragmentDrainRatioGraphYValues = newSemicolonDelimitedStringListConfigurationValue(FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES));
        this.mGraph_bitrateUpshiftLimitGraphXValues = newSemicolonDelimitedStringListConfigurationValue(BITRATE_UPSHIFT_LIMIT_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BITRATE_UPSHIFT_LIMIT_GRAPH_X_VALUES));
        this.mGraph_bitrateUpshiftLimitGraphYValues = newSemicolonDelimitedStringListConfigurationValue(BITRATE_UPSHIFT_LIMIT_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BITRATE_UPSHIFT_LIMIT_GRAPH_Y_VALUES));
        this.mGraph_bitrateDownshiftThresholdGraphXValues = newSemicolonDelimitedStringListConfigurationValue(BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_X_VALUES));
        this.mGraph_bitrateDownshiftThresholdGraphYValues = newSemicolonDelimitedStringListConfigurationValue(BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_Y_VALUES));
        this.mGraph_retryQualityPenaltyGraphXValues = newSemicolonDelimitedStringListConfigurationValue(RETRY_QUALITY_PENALTY_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_RETRY_QUALITY_PENALTY_GRAPH_X_VALUES));
        this.mGraph_retryQualityPenaltyGraphYValues = newSemicolonDelimitedStringListConfigurationValue(RETRY_QUALITY_PENALTY_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_RETRY_QUALITY_PENALTY_GRAPH_Y_VALUES));
        this.mGraph_bandwidthConfidenceScaleFactorGraphXValues = newSemicolonDelimitedStringListConfigurationValue(BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES));
        this.mGraph_bandwidthConfidenceScaleFactorGraphYValues = newSemicolonDelimitedStringListConfigurationValue(BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES));
        this.mGraph_latencyConfidenceScaleFactorGraphXValues = newSemicolonDelimitedStringListConfigurationValue(LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_X_VALUES));
        this.mGraph_latencyConfidenceScaleFactorGraphYValues = newSemicolonDelimitedStringListConfigurationValue(LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_Y_VALUES));
        this.mGraph_liveFragmentDrainRatioGraphXValues_live = newSemicolonDelimitedStringListConfigurationValue(LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_X_VALUES));
        this.mGraph_liveFragmentDrainRatioGraphYValues_live = newSemicolonDelimitedStringListConfigurationValue(LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES_KEY, MediaConfigBase.convertToStringArray(DEFAULT_LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_Y_VALUES));
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getTimeOutGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_timeOutGraphXValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getTimeOutGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_timeOutGraphYValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getFragmentDrainRatioGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_fragmentDrainRatioGraphXValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getFragmentDrainRatioGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_fragmentDrainRatioGraphYValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateUpshiftLimitGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bitrateUpshiftLimitGraphXValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateUpshiftLimitGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bitrateUpshiftLimitGraphYValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateDownshiftThresholdGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bitrateDownshiftThresholdGraphXValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateDownshiftThresholdGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bitrateDownshiftThresholdGraphYValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getRetryQualityPenaltyGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_retryQualityPenaltyGraphXValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getRetryQualityPenaltyGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_retryQualityPenaltyGraphYValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBandwidthConfidenceScaleFactorGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bandwidthConfidenceScaleFactorGraphXValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBandwidthConfidenceScaleFactorGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_bandwidthConfidenceScaleFactorGraphYValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getLatencyConfidenceScaleFactorGraphXValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_latencyConfidenceScaleFactorGraphXValues.getValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getLatencyConfidenceScaleFactorGraphYValues() {
        return MediaConfigBase.convertToDoubleArray(this.mGraph_latencyConfidenceScaleFactorGraphYValues.getValue());
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
