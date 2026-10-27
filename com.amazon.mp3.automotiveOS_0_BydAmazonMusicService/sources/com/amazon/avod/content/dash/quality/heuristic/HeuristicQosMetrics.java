package com.amazon.avod.content.dash.quality.heuristic;

import com.amazon.avod.util.DLog;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class HeuristicQosMetrics {
    private long mBandwidthAvgBps;
    private long mBandwidthStdDevBps;
    private long mLatencyAvgMillis;
    private long mLatencyStdDevMillis;
    private long mPredictionErrorAvgBps;

    public void initialize(@Nullable Heuristics heuristics) {
        if (heuristics != null) {
            this.mBandwidthAvgBps = heuristics.getBandwidthAverageBytesPerSecond();
            this.mBandwidthStdDevBps = heuristics.getBandwidthStandardDeviationBytesPerSecond();
            this.mLatencyAvgMillis = heuristics.getLatencyAverageMillis();
            this.mLatencyStdDevMillis = heuristics.getLatencyStandardDeviationMillis();
            this.mPredictionErrorAvgBps = heuristics.getBandwidthPredictionErrorAvgBytesPerSecond();
        }
    }

    public String toString() {
        return "HeuristicQosMetrics{bandwidthAvgBps=" + this.mBandwidthAvgBps + ", bandwidthStdDevBps=" + this.mBandwidthStdDevBps + ", ltencyAvgMillis=" + this.mLatencyAvgMillis + ", latencyStdDevMillis=" + this.mLatencyStdDevMillis + ", predictionErrorAvgBps=" + this.mPredictionErrorAvgBps + '}';
    }

    @Nullable
    public String toJsonString() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            DLog.warnf(e.getMessage());
            return null;
        }
    }
}
