package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListCustomMetricsResult implements Serializable {
    private List<String> metricNames;
    private String nextToken;

    public List<String> getMetricNames() {
        return this.metricNames;
    }

    public void setMetricNames(Collection<String> collection) {
        if (collection == null) {
            this.metricNames = null;
        } else {
            this.metricNames = new ArrayList(collection);
        }
    }

    public ListCustomMetricsResult withMetricNames(String... strArr) {
        if (getMetricNames() == null) {
            this.metricNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.metricNames.add(str);
        }
        return this;
    }

    public ListCustomMetricsResult withMetricNames(Collection<String> collection) {
        setMetricNames(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListCustomMetricsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMetricNames() != null) {
            sb.append("metricNames: " + getMetricNames() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getMetricNames() == null ? 0 : getMetricNames().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListCustomMetricsResult)) {
            return false;
        }
        ListCustomMetricsResult listCustomMetricsResult = (ListCustomMetricsResult) obj;
        if ((listCustomMetricsResult.getMetricNames() == null) ^ (getMetricNames() == null)) {
            return false;
        }
        if (listCustomMetricsResult.getMetricNames() != null && !listCustomMetricsResult.getMetricNames().equals(getMetricNames())) {
            return false;
        }
        if ((listCustomMetricsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listCustomMetricsResult.getNextToken() == null || listCustomMetricsResult.getNextToken().equals(getNextToken());
    }
}
