package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingRegistrationTasksRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String status;

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingRegistrationTasksRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListThingRegistrationTasksRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public ListThingRegistrationTasksRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status.toString();
    }

    public ListThingRegistrationTasksRequest withStatus(Status status) {
        this.status = status.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getStatus() != null ? getStatus().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingRegistrationTasksRequest)) {
            return false;
        }
        ListThingRegistrationTasksRequest listThingRegistrationTasksRequest = (ListThingRegistrationTasksRequest) obj;
        if ((listThingRegistrationTasksRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingRegistrationTasksRequest.getNextToken() != null && !listThingRegistrationTasksRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingRegistrationTasksRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listThingRegistrationTasksRequest.getMaxResults() != null && !listThingRegistrationTasksRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listThingRegistrationTasksRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return listThingRegistrationTasksRequest.getStatus() == null || listThingRegistrationTasksRequest.getStatus().equals(getStatus());
    }
}
