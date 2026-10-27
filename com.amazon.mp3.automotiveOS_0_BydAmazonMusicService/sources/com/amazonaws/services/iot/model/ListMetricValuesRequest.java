package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class ListMetricValuesRequest extends AmazonWebServiceRequest implements Serializable {
    private String dimensionName;
    private String dimensionValueOperator;
    private Date endTime;
    private Integer maxResults;
    private String metricName;
    private String nextToken;
    private Date startTime;
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public ListMetricValuesRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public ListMetricValuesRequest withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public String getDimensionName() {
        return this.dimensionName;
    }

    public void setDimensionName(String str) {
        this.dimensionName = str;
    }

    public ListMetricValuesRequest withDimensionName(String str) {
        this.dimensionName = str;
        return this;
    }

    public String getDimensionValueOperator() {
        return this.dimensionValueOperator;
    }

    public void setDimensionValueOperator(String str) {
        this.dimensionValueOperator = str;
    }

    public ListMetricValuesRequest withDimensionValueOperator(String str) {
        this.dimensionValueOperator = str;
        return this;
    }

    public void setDimensionValueOperator(DimensionValueOperator dimensionValueOperator) {
        this.dimensionValueOperator = dimensionValueOperator.toString();
    }

    public ListMetricValuesRequest withDimensionValueOperator(DimensionValueOperator dimensionValueOperator) {
        this.dimensionValueOperator = dimensionValueOperator.toString();
        return this;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public ListMetricValuesRequest withStartTime(Date date) {
        this.startTime = date;
        return this;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public ListMetricValuesRequest withEndTime(Date date) {
        this.endTime = date;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListMetricValuesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListMetricValuesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getMetricName() != null) {
            sb.append("metricName: " + getMetricName() + ",");
        }
        if (getDimensionName() != null) {
            sb.append("dimensionName: " + getDimensionName() + ",");
        }
        if (getDimensionValueOperator() != null) {
            sb.append("dimensionValueOperator: " + getDimensionValueOperator() + ",");
        }
        if (getStartTime() != null) {
            sb.append("startTime: " + getStartTime() + ",");
        }
        if (getEndTime() != null) {
            sb.append("endTime: " + getEndTime() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getMetricName() == null ? 0 : getMetricName().hashCode())) * 31) + (getDimensionName() == null ? 0 : getDimensionName().hashCode())) * 31) + (getDimensionValueOperator() == null ? 0 : getDimensionValueOperator().hashCode())) * 31) + (getStartTime() == null ? 0 : getStartTime().hashCode())) * 31) + (getEndTime() == null ? 0 : getEndTime().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListMetricValuesRequest)) {
            return false;
        }
        ListMetricValuesRequest listMetricValuesRequest = (ListMetricValuesRequest) obj;
        if ((listMetricValuesRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (listMetricValuesRequest.getThingName() != null && !listMetricValuesRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((listMetricValuesRequest.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (listMetricValuesRequest.getMetricName() != null && !listMetricValuesRequest.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((listMetricValuesRequest.getDimensionName() == null) ^ (getDimensionName() == null)) {
            return false;
        }
        if (listMetricValuesRequest.getDimensionName() != null && !listMetricValuesRequest.getDimensionName().equals(getDimensionName())) {
            return false;
        }
        if ((listMetricValuesRequest.getDimensionValueOperator() == null) ^ (getDimensionValueOperator() == null)) {
            return false;
        }
        if (listMetricValuesRequest.getDimensionValueOperator() != null && !listMetricValuesRequest.getDimensionValueOperator().equals(getDimensionValueOperator())) {
            return false;
        }
        if ((listMetricValuesRequest.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (listMetricValuesRequest.getStartTime() != null && !listMetricValuesRequest.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((listMetricValuesRequest.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        if (listMetricValuesRequest.getEndTime() != null && !listMetricValuesRequest.getEndTime().equals(getEndTime())) {
            return false;
        }
        if ((listMetricValuesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listMetricValuesRequest.getMaxResults() != null && !listMetricValuesRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listMetricValuesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listMetricValuesRequest.getNextToken() == null || listMetricValuesRequest.getNextToken().equals(getNextToken());
    }
}
