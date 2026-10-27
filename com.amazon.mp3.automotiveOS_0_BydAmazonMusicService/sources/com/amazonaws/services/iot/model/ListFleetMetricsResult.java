package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListFleetMetricsResult implements Serializable {
    private List<FleetMetricNameAndArn> fleetMetrics;
    private String nextToken;

    public List<FleetMetricNameAndArn> getFleetMetrics() {
        return this.fleetMetrics;
    }

    public void setFleetMetrics(Collection<FleetMetricNameAndArn> collection) {
        if (collection == null) {
            this.fleetMetrics = null;
        } else {
            this.fleetMetrics = new ArrayList(collection);
        }
    }

    public ListFleetMetricsResult withFleetMetrics(FleetMetricNameAndArn... fleetMetricNameAndArnArr) {
        if (getFleetMetrics() == null) {
            this.fleetMetrics = new ArrayList(fleetMetricNameAndArnArr.length);
        }
        for (FleetMetricNameAndArn fleetMetricNameAndArn : fleetMetricNameAndArnArr) {
            this.fleetMetrics.add(fleetMetricNameAndArn);
        }
        return this;
    }

    public ListFleetMetricsResult withFleetMetrics(Collection<FleetMetricNameAndArn> collection) {
        setFleetMetrics(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListFleetMetricsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFleetMetrics() != null) {
            sb.append("fleetMetrics: " + getFleetMetrics() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getFleetMetrics() == null ? 0 : getFleetMetrics().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListFleetMetricsResult)) {
            return false;
        }
        ListFleetMetricsResult listFleetMetricsResult = (ListFleetMetricsResult) obj;
        if ((listFleetMetricsResult.getFleetMetrics() == null) ^ (getFleetMetrics() == null)) {
            return false;
        }
        if (listFleetMetricsResult.getFleetMetrics() != null && !listFleetMetricsResult.getFleetMetrics().equals(getFleetMetrics())) {
            return false;
        }
        if ((listFleetMetricsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listFleetMetricsResult.getNextToken() == null || listFleetMetricsResult.getNextToken().equals(getNextToken());
    }
}
