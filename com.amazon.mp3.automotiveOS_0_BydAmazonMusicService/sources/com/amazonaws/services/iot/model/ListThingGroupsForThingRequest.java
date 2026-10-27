package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingGroupsForThingRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public ListThingGroupsForThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingGroupsForThingRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListThingGroupsForThingRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
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
        return (((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingGroupsForThingRequest)) {
            return false;
        }
        ListThingGroupsForThingRequest listThingGroupsForThingRequest = (ListThingGroupsForThingRequest) obj;
        if ((listThingGroupsForThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (listThingGroupsForThingRequest.getThingName() != null && !listThingGroupsForThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((listThingGroupsForThingRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingGroupsForThingRequest.getNextToken() != null && !listThingGroupsForThingRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingGroupsForThingRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listThingGroupsForThingRequest.getMaxResults() == null || listThingGroupsForThingRequest.getMaxResults().equals(getMaxResults());
    }
}
