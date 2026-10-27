package com.amazon.avod.content.classification;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class ExponentialSmoothingClassifier implements Classifier {
    private double mAlpha;
    private List<Double> mBandwidth;
    private ImmutableMap<String, ImmutableList<Double>> mClusterModel;
    private String mCurrentClusterLabel;
    private String mCurrentClusterModelVersion;
    private List<Double> mLatency;
    private int mWindowSize;
    private double mEWMABandwidthMean = 0.0d;
    private double mEWMABandwidthStd = 0.0d;
    private double mEWMALatencyMean = 0.0d;
    private double mEWMALatencyStd = 0.0d;
    private double mEWMABandwidthMin = 0.0d;
    private double mEWMABandwidthMax = 0.0d;
    private double mEWMALatencyMin = 0.0d;
    private double mEWMALatencyMax = 0.0d;
    private boolean isInitialized = false;

    public ExponentialSmoothingClassifier(@Nonnull ImmutableMap<String, ImmutableList<Double>> immutableMap, int i, double d, @Nonnull String str, @Nonnull String str2) {
        if (i <= 0) {
            throw new IllegalArgumentException(String.format("Window Size has to be greater than 0. Received %d", Integer.valueOf(i)));
        }
        if (d < 0.0d || d > 1.0d) {
            throw new IllegalArgumentException(String.format("Alpha has to be between 0 and 1. Received %f", Double.valueOf(d)));
        }
        if (!"Legacy".equals(str) && !immutableMap.containsKey(str)) {
            throw new IllegalArgumentException(String.format("Current Cluster Label not in provided cluster model %s", str));
        }
        this.mClusterModel = (ImmutableMap) Preconditions.checkNotNull(immutableMap, "clusterModel");
        this.mWindowSize = i;
        this.mAlpha = d;
        this.mBandwidth = new ArrayList();
        this.mLatency = new ArrayList();
        this.mCurrentClusterLabel = (String) Preconditions.checkNotNull(str, "currentClusterLabel");
        this.mCurrentClusterModelVersion = (String) Preconditions.checkNotNull(str2, "currentClusterModelVersion");
    }

    @Override // com.amazon.avod.content.classification.Classifier
    @Nonnull
    public String reportDownload(double d, double d2) {
        this.mBandwidth.add(Double.valueOf(d));
        this.mLatency.add(Double.valueOf(d2));
        if (this.mBandwidth.size() == this.mWindowSize) {
            this.mCurrentClusterLabel = getPredictedClusterLabel();
            this.mBandwidth.clear();
            this.mLatency.clear();
        }
        return this.mCurrentClusterLabel;
    }

    private String getPredictedClusterLabel() {
        if ("v0".equals(this.mCurrentClusterModelVersion)) {
            return getPredictedClusterLabelMeanMinAndMaxModel();
        }
        return getPredictedClusterLabelMeanANdStandardDeviationModel();
    }

    private String getPredictedClusterLabelMeanMinAndMaxModel() {
        double mean = getMean(this.mBandwidth);
        double dDoubleValue = ((Double) Collections.min(this.mBandwidth)).doubleValue();
        double dDoubleValue2 = ((Double) Collections.max(this.mBandwidth)).doubleValue();
        double mean2 = getMean(this.mLatency);
        double dDoubleValue3 = ((Double) Collections.min(this.mLatency)).doubleValue();
        double dDoubleValue4 = ((Double) Collections.max(this.mLatency)).doubleValue();
        if (!this.isInitialized) {
            this.mEWMABandwidthMean = mean;
            this.mEWMABandwidthMin = dDoubleValue;
            this.mEWMABandwidthMax = dDoubleValue2;
            this.mEWMALatencyMean = mean2;
            this.mEWMALatencyMin = dDoubleValue3;
            this.mEWMALatencyMax = dDoubleValue4;
            this.isInitialized = true;
        } else {
            this.mEWMABandwidthMean = getEWMA(mean, this.mEWMABandwidthMean);
            this.mEWMABandwidthMin = getEWMA(dDoubleValue, this.mEWMABandwidthMin);
            this.mEWMABandwidthMax = getEWMA(dDoubleValue2, this.mEWMABandwidthMax);
            this.mEWMALatencyMean = getEWMA(mean2, this.mEWMALatencyMean);
            this.mEWMALatencyMin = getEWMA(dDoubleValue3, this.mEWMALatencyMin);
            this.mEWMALatencyMax = getEWMA(dDoubleValue4, this.mEWMALatencyMax);
        }
        String key = this.mCurrentClusterLabel;
        double d = Double.POSITIVE_INFINITY;
        UnmodifiableIterator<Map.Entry<String, ImmutableList<Double>>> it = this.mClusterModel.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ImmutableList<Double>> next = it.next();
            double dPow = Math.pow(this.mEWMABandwidthMean - next.getValue().get(0).doubleValue(), 2.0d) + Math.pow(this.mEWMABandwidthMin - next.getValue().get(1).doubleValue(), 2.0d) + Math.pow(this.mEWMABandwidthMax - next.getValue().get(2).doubleValue(), 2.0d) + Math.pow(this.mEWMALatencyMean - next.getValue().get(3).doubleValue(), 2.0d) + Math.pow(this.mEWMALatencyMin - next.getValue().get(4).doubleValue(), 2.0d) + Math.pow(this.mEWMALatencyMax - next.getValue().get(5).doubleValue(), 2.0d);
            if (dPow < d) {
                key = next.getKey();
                d = dPow;
            }
        }
        this.mCurrentClusterLabel = key;
        return key;
    }

    private String getPredictedClusterLabelMeanANdStandardDeviationModel() {
        double mean = getMean(this.mBandwidth);
        double standardDeviation = getStandardDeviation(this.mBandwidth, Double.valueOf(mean));
        double mean2 = getMean(this.mLatency);
        double standardDeviation2 = getStandardDeviation(this.mLatency, Double.valueOf(mean2));
        if (!this.isInitialized) {
            this.mEWMABandwidthMean = mean;
            this.mEWMABandwidthStd = standardDeviation;
            this.mEWMALatencyMean = mean2;
            this.mEWMALatencyStd = standardDeviation2;
            this.isInitialized = true;
        } else {
            this.mEWMABandwidthMean = getEWMA(mean, this.mEWMABandwidthMean);
            this.mEWMABandwidthStd = getEWMA(standardDeviation, this.mEWMABandwidthStd);
            this.mEWMALatencyMean = getEWMA(mean2, this.mEWMALatencyMean);
            this.mEWMALatencyStd = getEWMA(standardDeviation2, this.mEWMALatencyStd);
        }
        String key = this.mCurrentClusterLabel;
        double d = Double.POSITIVE_INFINITY;
        UnmodifiableIterator<Map.Entry<String, ImmutableList<Double>>> it = this.mClusterModel.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ImmutableList<Double>> next = it.next();
            double dPow = Math.pow(this.mEWMABandwidthMean - next.getValue().get(0).doubleValue(), 2.0d) + Math.pow(this.mEWMABandwidthStd - next.getValue().get(1).doubleValue(), 2.0d) + Math.pow(this.mEWMALatencyMean - next.getValue().get(2).doubleValue(), 2.0d) + Math.pow(this.mEWMALatencyStd - next.getValue().get(3).doubleValue(), 2.0d);
            if (dPow < d) {
                key = next.getKey();
                d = dPow;
            }
        }
        this.mCurrentClusterLabel = key;
        return key;
    }

    private double getEWMA(double d, double d2) {
        double d3 = this.mAlpha;
        return (d * d3) + ((1.0d - d3) * d2);
    }

    private double getMean(List<Double> list) {
        double dDoubleValue = 0.0d;
        int i = 0;
        while (true) {
            int i2 = this.mWindowSize;
            if (i >= i2) {
                return dDoubleValue / ((double) i2);
            }
            dDoubleValue += list.get(i).doubleValue();
            i++;
        }
    }

    private double getStandardDeviation(List<Double> list, Double d) {
        double dPow = 0.0d;
        int i = 0;
        while (true) {
            int i2 = this.mWindowSize;
            if (i < i2) {
                dPow += Math.pow(list.get(i).doubleValue() - d.doubleValue(), 2.0d);
                i++;
            } else {
                return Math.sqrt(dPow / ((double) i2));
            }
        }
    }
}
