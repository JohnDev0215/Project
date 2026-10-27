package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListDimensionsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListDimensionsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListDimensionsRequest withMaxResults(Integer num) {
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
        if (obj == null || !(obj instanceof ListDimensionsRequest)) {
            return false;
        }
        ListDimensionsRequest listDimensionsRequest = (ListDimensionsRequest) obj;
        if ((listDimensionsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listDimensionsRequest.getNextToken() != null && !listDimensionsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listDimensionsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listDimensionsRequest.getMaxResults() == null || listDimensionsRequest.getMaxResults().equals(getMaxResults());
    }
}
