package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class MetricToRetain implements Serializable {
    private String metric;
    private MetricDimension metricDimension;

    public String getMetric() {
        return this.metric;
    }

    public void setMetric(String str) {
        this.metric = str;
    }

    public MetricToRetain withMetric(String str) {
        this.metric = str;
        return this;
    }

    public MetricDimension getMetricDimension() {
        return this.metricDimension;
    }

    public void setMetricDimension(MetricDimension metricDimension) {
        this.metricDimension = metricDimension;
    }

    public MetricToRetain withMetricDimension(MetricDimension metricDimension) {
        this.metricDimension = metricDimension;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMetric() != null) {
            sb.append("metric: " + getMetric() + ",");
        }
        if (getMetricDimension() != null) {
            sb.append("metricDimension: " + getMetricDimension());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getMetric() == null ? 0 : getMetric().hashCode()) + 31) * 31) + (getMetricDimension() != null ? getMetricDimension().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MetricToRetain)) {
            return false;
        }
        MetricToRetain metricToRetain = (MetricToRetain) obj;
        if ((metricToRetain.getMetric() == null) ^ (getMetric() == null)) {
            return false;
        }
        if (metricToRetain.getMetric() != null && !metricToRetain.getMetric().equals(getMetric())) {
            return false;
        }
        if ((metricToRetain.getMetricDimension() == null) ^ (getMetricDimension() == null)) {
            return false;
        }
        return metricToRetain.getMetricDimension() == null || metricToRetain.getMetricDimension().equals(getMetricDimension());
    }
}
