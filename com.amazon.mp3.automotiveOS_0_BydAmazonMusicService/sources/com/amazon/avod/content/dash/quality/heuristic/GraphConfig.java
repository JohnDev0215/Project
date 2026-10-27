package com.amazon.avod.content.dash.quality.heuristic;

/* JADX INFO: loaded from: classes2.dex */
public interface GraphConfig {
    double[] getBandwidthConfidenceScaleFactorGraphXValues();

    double[] getBandwidthConfidenceScaleFactorGraphYValues();

    double[] getBitrateDownshiftThresholdGraphXValues();

    double[] getBitrateDownshiftThresholdGraphYValues();

    double[] getBitrateUpshiftLimitGraphXValues();

    double[] getBitrateUpshiftLimitGraphYValues();

    double[] getFragmentDrainRatioGraphXValues();

    double[] getFragmentDrainRatioGraphYValues();

    double[] getLatencyConfidenceScaleFactorGraphXValues();

    double[] getLatencyConfidenceScaleFactorGraphYValues();

    double[] getLiveFragmentDrainRatioGraphXValues();

    double[] getLiveFragmentDrainRatioGraphYValues();

    double[] getRetryQualityPenaltyGraphXValues();

    double[] getRetryQualityPenaltyGraphYValues();

    double[] getTimeOutGraphXValues();

    double[] getTimeOutGraphYValues();
}
