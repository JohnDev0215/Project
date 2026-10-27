package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class FleetMetricNameAndArn implements Serializable {
    private String metricArn;
    private String metricName;

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public FleetMetricNameAndArn withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public String getMetricArn() {
        return this.metricArn;
    }

    public void setMetricArn(String str) {
        this.metricArn = str;
    }

    public FleetMetricNameAndArn withMetricArn(String str) {
        this.metricArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMetricName() != null) {
            sb.append("metricName: " + getMetricName() + ",");
        }
        if (getMetricArn() != null) {
            sb.append("metricArn: " + getMetricArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getMetricName() == null ? 0 : getMetricName().hashCode()) + 31) * 31) + (getMetricArn() != null ? getMetricArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FleetMetricNameAndArn)) {
            return false;
        }
        FleetMetricNameAndArn fleetMetricNameAndArn = (FleetMetricNameAndArn) obj;
        if ((fleetMetricNameAndArn.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (fleetMetricNameAndArn.getMetricName() != null && !fleetMetricNameAndArn.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((fleetMetricNameAndArn.getMetricArn() == null) ^ (getMetricArn() == null)) {
            return false;
        }
        return fleetMetricNameAndArn.getMetricArn() == null || fleetMetricNameAndArn.getMetricArn().equals(getMetricArn());
    }
}
