package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListFleetMetricsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListFleetMetricsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListFleetMetricsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListFleetMetricsRequest)) {
            return false;
        }
        ListFleetMetricsRequest listFleetMetricsRequest = (ListFleetMetricsRequest) obj;
        if ((listFleetMetricsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listFleetMetricsRequest.getNextToken() != null && !listFleetMetricsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listFleetMetricsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listFleetMetricsRequest.getMaxResults() == null || listFleetMetricsRequest.getMaxResults().equals(getMaxResults());
    }
}
