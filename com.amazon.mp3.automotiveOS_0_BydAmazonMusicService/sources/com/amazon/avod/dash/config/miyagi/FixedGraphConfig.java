package com.amazon.avod.dash.config.miyagi;

import com.amazon.avod.content.dash.quality.heuristic.GraphConfig;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsGraph;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
class FixedGraphConfig extends HeuristicsConfigBase implements GraphConfig {
    public static final String BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_KEY = "bandwidthPredictor_bufferFullnessSecondsToConfidenceFactorGraph";
    public static final String BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_KEY = "filters_bufferFullnessSecondsToBitrateDownshiftLimitFactorGraph";
    public static final String BITRATE_UPSHIFT_LIMIT_GRAPH_KEY = "filters_bufferFullnessSecondsToBitrateUpshiftLimitFactorGraph";
    public static final String FRAGMENT_DRAIN_RATIO_GRAPH_KEY = "timeToDownloadPredictor_bufferFullnessSecondsToBandwidthThrottleFactorGraph";
    public static final String LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_KEY = "latencyPredictor_bufferFullnessSecondsToConfidenceFactorGraph";
    public static final String LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_KEY = "bufferFullnessSecondsToLiveBandwidthThrottleFactorGraph";
    public static final String RETRY_QUALITY_PENALTY_GRAPH__KEY = "timeToDownloadPredictor_bufferFullnessSecondsToRetryBandwidthThrottleFactorGraph";
    public static final String TIME_OUT_GRAPH_KEY = "scheduler_bufferFullnessSecondsToTimeoutFactorGraph";
    private final HeuristicsGraph mBandwidthConfidenceScaleFactorGraph;
    private final HeuristicsGraph mBitrateDownshiftThresholdGraph;
    private final HeuristicsGraph mBitrateUpshiftLimitGraph;
    private final HeuristicsGraph mFragmentDrainRatioGraph;
    private final HeuristicsGraph mHeuristicsTimeoutGraph;
    private final HeuristicsGraph mLatencyConfidenceScaleFactorGraph;
    private final HeuristicsGraph mLiveFragmentDrainRatioGraph;
    private final HeuristicsGraph mRetryQualityPenaltyGraph;

    FixedGraphConfig(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, GraphConfig graphConfig) {
        super(map, map2, map3);
        this.mHeuristicsTimeoutGraph = getHeuristicsGraphConfigValue(TIME_OUT_GRAPH_KEY, graphConfig.getTimeOutGraphXValues(), graphConfig.getTimeOutGraphYValues());
        this.mFragmentDrainRatioGraph = getHeuristicsGraphConfigValue(FRAGMENT_DRAIN_RATIO_GRAPH_KEY, graphConfig.getFragmentDrainRatioGraphXValues(), graphConfig.getFragmentDrainRatioGraphYValues());
        this.mBitrateUpshiftLimitGraph = getHeuristicsGraphConfigValue(BITRATE_UPSHIFT_LIMIT_GRAPH_KEY, graphConfig.getBitrateUpshiftLimitGraphXValues(), graphConfig.getBitrateUpshiftLimitGraphYValues());
        this.mBitrateDownshiftThresholdGraph = getHeuristicsGraphConfigValue(BITRATE_DOWNSHIFT_THRESHOLD_GRAPH_KEY, graphConfig.getBitrateDownshiftThresholdGraphXValues(), graphConfig.getBitrateDownshiftThresholdGraphYValues());
        this.mRetryQualityPenaltyGraph = getHeuristicsGraphConfigValue(RETRY_QUALITY_PENALTY_GRAPH__KEY, graphConfig.getRetryQualityPenaltyGraphXValues(), graphConfig.getRetryQualityPenaltyGraphYValues());
        this.mBandwidthConfidenceScaleFactorGraph = getHeuristicsGraphConfigValue(BANDWIDTH_CONFIDENCE_SCALE_FACTOR_GRAPH_KEY, graphConfig.getBandwidthConfidenceScaleFactorGraphXValues(), graphConfig.getBandwidthConfidenceScaleFactorGraphYValues());
        this.mLatencyConfidenceScaleFactorGraph = getHeuristicsGraphConfigValue(LATENCY_CONFIDENCE_SCALE_FACTOR_GRAPH_KEY, graphConfig.getLatencyConfidenceScaleFactorGraphXValues(), graphConfig.getLatencyConfidenceScaleFactorGraphYValues());
        this.mLiveFragmentDrainRatioGraph = getHeuristicsGraphConfigValue(LIVE_FRAGMENT_DRAIN_RATIO_GRAPH_KEY, graphConfig.getLiveFragmentDrainRatioGraphXValues(), graphConfig.getLiveFragmentDrainRatioGraphYValues());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getTimeOutGraphXValues() {
        return this.mHeuristicsTimeoutGraph.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getTimeOutGraphYValues() {
        return this.mHeuristicsTimeoutGraph.getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getFragmentDrainRatioGraphXValues() {
        return this.mFragmentDrainRatioGraph.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getFragmentDrainRatioGraphYValues() {
        return this.mFragmentDrainRatioGraph.getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateUpshiftLimitGraphXValues() {
        return this.mBitrateUpshiftLimitGraph.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateUpshiftLimitGraphYValues() {
        return this.mBitrateUpshiftLimitGraph.getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateDownshiftThresholdGraphXValues() {
        return this.mBitrateDownshiftThresholdGraph.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBitrateDownshiftThresholdGraphYValues() {
        return this.mBitrateDownshiftThresholdGraph.getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getRetryQualityPenaltyGraphXValues() {
        return this.mRetryQualityPenaltyGraph.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getRetryQualityPenaltyGraphYValues() {
        return this.mRetryQualityPenaltyGraph.getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBandwidthConfidenceScaleFactorGraphXValues() {
        return this.mBandwidthConfidenceScaleFactorGraph.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getBandwidthConfidenceScaleFactorGraphYValues() {
        return this.mBandwidthConfidenceScaleFactorGraph.getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getLatencyConfidenceScaleFactorGraphXValues() {
        return this.mLatencyConfidenceScaleFactorGraph.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getLatencyConfidenceScaleFactorGraphYValues() {
        return this.mLatencyConfidenceScaleFactorGraph.getYValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getLiveFragmentDrainRatioGraphXValues() {
        return this.mLiveFragmentDrainRatioGraph.getXValues();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.GraphConfig
    public double[] getLiveFragmentDrainRatioGraphYValues() {
        return this.mLiveFragmentDrainRatioGraph.getYValues();
    }
}
