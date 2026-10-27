package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class BehaviorCriteria implements Serializable {
    private String comparisonOperator;
    private Integer consecutiveDatapointsToAlarm;
    private Integer consecutiveDatapointsToClear;
    private Integer durationSeconds;
    private MachineLearningDetectionConfig mlDetectionConfig;
    private StatisticalThreshold statisticalThreshold;
    private MetricValue value;

    public String getComparisonOperator() {
        return this.comparisonOperator;
    }

    public void setComparisonOperator(String str) {
        this.comparisonOperator = str;
    }

    public BehaviorCriteria withComparisonOperator(String str) {
        this.comparisonOperator = str;
        return this;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator.toString();
    }

    public BehaviorCriteria withComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator.toString();
        return this;
    }

    public MetricValue getValue() {
        return this.value;
    }

    public void setValue(MetricValue metricValue) {
        this.value = metricValue;
    }

    public BehaviorCriteria withValue(MetricValue metricValue) {
        this.value = metricValue;
        return this;
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public void setDurationSeconds(Integer num) {
        this.durationSeconds = num;
    }

    public BehaviorCriteria withDurationSeconds(Integer num) {
        this.durationSeconds = num;
        return this;
    }

    public Integer getConsecutiveDatapointsToAlarm() {
        return this.consecutiveDatapointsToAlarm;
    }

    public void setConsecutiveDatapointsToAlarm(Integer num) {
        this.consecutiveDatapointsToAlarm = num;
    }

    public BehaviorCriteria withConsecutiveDatapointsToAlarm(Integer num) {
        this.consecutiveDatapointsToAlarm = num;
        return this;
    }

    public Integer getConsecutiveDatapointsToClear() {
        return this.consecutiveDatapointsToClear;
    }

    public void setConsecutiveDatapointsToClear(Integer num) {
        this.consecutiveDatapointsToClear = num;
    }

    public BehaviorCriteria withConsecutiveDatapointsToClear(Integer num) {
        this.consecutiveDatapointsToClear = num;
        return this;
    }

    public StatisticalThreshold getStatisticalThreshold() {
        return this.statisticalThreshold;
    }

    public void setStatisticalThreshold(StatisticalThreshold statisticalThreshold) {
        this.statisticalThreshold = statisticalThreshold;
    }

    public BehaviorCriteria withStatisticalThreshold(StatisticalThreshold statisticalThreshold) {
        this.statisticalThreshold = statisticalThreshold;
        return this;
    }

    public MachineLearningDetectionConfig getMlDetectionConfig() {
        return this.mlDetectionConfig;
    }

    public void setMlDetectionConfig(MachineLearningDetectionConfig machineLearningDetectionConfig) {
        this.mlDetectionConfig = machineLearningDetectionConfig;
    }

    public BehaviorCriteria withMlDetectionConfig(MachineLearningDetectionConfig machineLearningDetectionConfig) {
        this.mlDetectionConfig = machineLearningDetectionConfig;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getComparisonOperator() != null) {
            sb.append("comparisonOperator: " + getComparisonOperator() + ",");
        }
        if (getValue() != null) {
            sb.append("value: " + getValue() + ",");
        }
        if (getDurationSeconds() != null) {
            sb.append("durationSeconds: " + getDurationSeconds() + ",");
        }
        if (getConsecutiveDatapointsToAlarm() != null) {
            sb.append("consecutiveDatapointsToAlarm: " + getConsecutiveDatapointsToAlarm() + ",");
        }
        if (getConsecutiveDatapointsToClear() != null) {
            sb.append("consecutiveDatapointsToClear: " + getConsecutiveDatapointsToClear() + ",");
        }
        if (getStatisticalThreshold() != null) {
            sb.append("statisticalThreshold: " + getStatisticalThreshold() + ",");
        }
        if (getMlDetectionConfig() != null) {
            sb.append("mlDetectionConfig: " + getMlDetectionConfig());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getComparisonOperator() == null ? 0 : getComparisonOperator().hashCode()) + 31) * 31) + (getValue() == null ? 0 : getValue().hashCode())) * 31) + (getDurationSeconds() == null ? 0 : getDurationSeconds().hashCode())) * 31) + (getConsecutiveDatapointsToAlarm() == null ? 0 : getConsecutiveDatapointsToAlarm().hashCode())) * 31) + (getConsecutiveDatapointsToClear() == null ? 0 : getConsecutiveDatapointsToClear().hashCode())) * 31) + (getStatisticalThreshold() == null ? 0 : getStatisticalThreshold().hashCode())) * 31) + (getMlDetectionConfig() != null ? getMlDetectionConfig().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BehaviorCriteria)) {
            return false;
        }
        BehaviorCriteria behaviorCriteria = (BehaviorCriteria) obj;
        if ((behaviorCriteria.getComparisonOperator() == null) ^ (getComparisonOperator() == null)) {
            return false;
        }
        if (behaviorCriteria.getComparisonOperator() != null && !behaviorCriteria.getComparisonOperator().equals(getComparisonOperator())) {
            return false;
        }
        if ((behaviorCriteria.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        if (behaviorCriteria.getValue() != null && !behaviorCriteria.getValue().equals(getValue())) {
            return false;
        }
        if ((behaviorCriteria.getDurationSeconds() == null) ^ (getDurationSeconds() == null)) {
            return false;
        }
        if (behaviorCriteria.getDurationSeconds() != null && !behaviorCriteria.getDurationSeconds().equals(getDurationSeconds())) {
            return false;
        }
        if ((behaviorCriteria.getConsecutiveDatapointsToAlarm() == null) ^ (getConsecutiveDatapointsToAlarm() == null)) {
            return false;
        }
        if (behaviorCriteria.getConsecutiveDatapointsToAlarm() != null && !behaviorCriteria.getConsecutiveDatapointsToAlarm().equals(getConsecutiveDatapointsToAlarm())) {
            return false;
        }
        if ((behaviorCriteria.getConsecutiveDatapointsToClear() == null) ^ (getConsecutiveDatapointsToClear() == null)) {
            return false;
        }
        if (behaviorCriteria.getConsecutiveDatapointsToClear() != null && !behaviorCriteria.getConsecutiveDatapointsToClear().equals(getConsecutiveDatapointsToClear())) {
            return false;
        }
        if ((behaviorCriteria.getStatisticalThreshold() == null) ^ (getStatisticalThreshold() == null)) {
            return false;
        }
        if (behaviorCriteria.getStatisticalThreshold() != null && !behaviorCriteria.getStatisticalThreshold().equals(getStatisticalThreshold())) {
            return false;
        }
        if ((behaviorCriteria.getMlDetectionConfig() == null) ^ (getMlDetectionConfig() == null)) {
            return false;
        }
        return behaviorCriteria.getMlDetectionConfig() == null || behaviorCriteria.getMlDetectionConfig().equals(getMlDetectionConfig());
    }
}
