package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListStreamsRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private Integer maxResults;
    private String nextToken;

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListStreamsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListStreamsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Boolean isAscendingOrder() {
        return this.ascendingOrder;
    }

    public Boolean getAscendingOrder() {
        return this.ascendingOrder;
    }

    public void setAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
    }

    public ListStreamsRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getAscendingOrder() != null) {
            sb.append("ascendingOrder: " + getAscendingOrder());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getMaxResults() == null ? 0 : getMaxResults().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getAscendingOrder() != null ? getAscendingOrder().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListStreamsRequest)) {
            return false;
        }
        ListStreamsRequest listStreamsRequest = (ListStreamsRequest) obj;
        if ((listStreamsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listStreamsRequest.getMaxResults() != null && !listStreamsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listStreamsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listStreamsRequest.getNextToken() != null && !listStreamsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listStreamsRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        return listStreamsRequest.getAscendingOrder() == null || listStreamsRequest.getAscendingOrder().equals(getAscendingOrder());
    }
}
