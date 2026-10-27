package com.amazonaws.services.iotdata.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListRetainedMessagesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListRetainedMessagesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListRetainedMessagesRequest withMaxResults(Integer num) {
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
        if (obj == null || !(obj instanceof ListRetainedMessagesRequest)) {
            return false;
        }
        ListRetainedMessagesRequest listRetainedMessagesRequest = (ListRetainedMessagesRequest) obj;
        if ((listRetainedMessagesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listRetainedMessagesRequest.getNextToken() != null && !listRetainedMessagesRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listRetainedMessagesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listRetainedMessagesRequest.getMaxResults() == null || listRetainedMessagesRequest.getMaxResults().equals(getMaxResults());
    }
}
