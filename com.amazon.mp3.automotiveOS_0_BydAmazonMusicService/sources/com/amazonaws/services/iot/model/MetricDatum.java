package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class MetricDatum implements Serializable {
    private Date timestamp;
    private MetricValue value;

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public MetricDatum withTimestamp(Date date) {
        this.timestamp = date;
        return this;
    }

    public MetricValue getValue() {
        return this.value;
    }

    public void setValue(MetricValue metricValue) {
        this.value = metricValue;
    }

    public MetricDatum withValue(MetricValue metricValue) {
        this.value = metricValue;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTimestamp() != null) {
            sb.append("timestamp: " + getTimestamp() + ",");
        }
        if (getValue() != null) {
            sb.append("value: " + getValue());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTimestamp() == null ? 0 : getTimestamp().hashCode()) + 31) * 31) + (getValue() != null ? getValue().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MetricDatum)) {
            return false;
        }
        MetricDatum metricDatum = (MetricDatum) obj;
        if ((metricDatum.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        if (metricDatum.getTimestamp() != null && !metricDatum.getTimestamp().equals(getTimestamp())) {
            return false;
        }
        if ((metricDatum.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        return metricDatum.getValue() == null || metricDatum.getValue().equals(getValue());
    }
}
