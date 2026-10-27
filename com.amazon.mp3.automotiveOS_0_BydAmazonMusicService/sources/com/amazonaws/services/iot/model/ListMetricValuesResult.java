package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListMetricValuesResult implements Serializable {
    private List<MetricDatum> metricDatumList;
    private String nextToken;

    public List<MetricDatum> getMetricDatumList() {
        return this.metricDatumList;
    }

    public void setMetricDatumList(Collection<MetricDatum> collection) {
        if (collection == null) {
            this.metricDatumList = null;
        } else {
            this.metricDatumList = new ArrayList(collection);
        }
    }

    public ListMetricValuesResult withMetricDatumList(MetricDatum... metricDatumArr) {
        if (getMetricDatumList() == null) {
            this.metricDatumList = new ArrayList(metricDatumArr.length);
        }
        for (MetricDatum metricDatum : metricDatumArr) {
            this.metricDatumList.add(metricDatum);
        }
        return this;
    }

    public ListMetricValuesResult withMetricDatumList(Collection<MetricDatum> collection) {
        setMetricDatumList(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListMetricValuesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMetricDatumList() != null) {
            sb.append("metricDatumList: " + getMetricDatumList() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getMetricDatumList() == null ? 0 : getMetricDatumList().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListMetricValuesResult)) {
            return false;
        }
        ListMetricValuesResult listMetricValuesResult = (ListMetricValuesResult) obj;
        if ((listMetricValuesResult.getMetricDatumList() == null) ^ (getMetricDatumList() == null)) {
            return false;
        }
        if (listMetricValuesResult.getMetricDatumList() != null && !listMetricValuesResult.getMetricDatumList().equals(getMetricDatumList())) {
            return false;
        }
        if ((listMetricValuesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listMetricValuesResult.getNextToken() == null || listMetricValuesResult.getNextToken().equals(getNextToken());
    }
}
