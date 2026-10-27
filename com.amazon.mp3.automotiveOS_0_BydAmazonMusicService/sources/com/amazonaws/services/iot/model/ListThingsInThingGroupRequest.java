package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingsInThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private Boolean recursive;
    private String thingGroupName;

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public ListThingsInThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public Boolean isRecursive() {
        return this.recursive;
    }

    public Boolean getRecursive() {
        return this.recursive;
    }

    public void setRecursive(Boolean bool) {
        this.recursive = bool;
    }

    public ListThingsInThingGroupRequest withRecursive(Boolean bool) {
        this.recursive = bool;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingsInThingGroupRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListThingsInThingGroupRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupName() != null) {
            sb.append("thingGroupName: " + getThingGroupName() + ",");
        }
        if (getRecursive() != null) {
            sb.append("recursive: " + getRecursive() + ",");
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
        return (((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getRecursive() == null ? 0 : getRecursive().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingsInThingGroupRequest)) {
            return false;
        }
        ListThingsInThingGroupRequest listThingsInThingGroupRequest = (ListThingsInThingGroupRequest) obj;
        if ((listThingsInThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (listThingsInThingGroupRequest.getThingGroupName() != null && !listThingsInThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((listThingsInThingGroupRequest.getRecursive() == null) ^ (getRecursive() == null)) {
            return false;
        }
        if (listThingsInThingGroupRequest.getRecursive() != null && !listThingsInThingGroupRequest.getRecursive().equals(getRecursive())) {
            return false;
        }
        if ((listThingsInThingGroupRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingsInThingGroupRequest.getNextToken() != null && !listThingsInThingGroupRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingsInThingGroupRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listThingsInThingGroupRequest.getMaxResults() == null || listThingsInThingGroupRequest.getMaxResults().equals(getMaxResults());
    }
}
