package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobTemplatesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListJobTemplatesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListJobTemplatesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
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
        return (((getMaxResults() == null ? 0 : getMaxResults().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListJobTemplatesRequest)) {
            return false;
        }
        ListJobTemplatesRequest listJobTemplatesRequest = (ListJobTemplatesRequest) obj;
        if ((listJobTemplatesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listJobTemplatesRequest.getMaxResults() != null && !listJobTemplatesRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listJobTemplatesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listJobTemplatesRequest.getNextToken() == null || listJobTemplatesRequest.getNextToken().equals(getNextToken());
    }
}
