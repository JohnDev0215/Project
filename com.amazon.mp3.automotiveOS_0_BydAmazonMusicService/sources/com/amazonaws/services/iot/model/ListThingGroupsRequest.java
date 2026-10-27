package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingGroupsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String namePrefixFilter;
    private String nextToken;
    private String parentGroup;
    private Boolean recursive;

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingGroupsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListThingGroupsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getParentGroup() {
        return this.parentGroup;
    }

    public void setParentGroup(String str) {
        this.parentGroup = str;
    }

    public ListThingGroupsRequest withParentGroup(String str) {
        this.parentGroup = str;
        return this;
    }

    public String getNamePrefixFilter() {
        return this.namePrefixFilter;
    }

    public void setNamePrefixFilter(String str) {
        this.namePrefixFilter = str;
    }

    public ListThingGroupsRequest withNamePrefixFilter(String str) {
        this.namePrefixFilter = str;
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

    public ListThingGroupsRequest withRecursive(Boolean bool) {
        this.recursive = bool;
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
        if (getParentGroup() != null) {
            sb.append("parentGroup: " + getParentGroup() + ",");
        }
        if (getNamePrefixFilter() != null) {
            sb.append("namePrefixFilter: " + getNamePrefixFilter() + ",");
        }
        if (getRecursive() != null) {
            sb.append("recursive: " + getRecursive());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getParentGroup() == null ? 0 : getParentGroup().hashCode())) * 31) + (getNamePrefixFilter() == null ? 0 : getNamePrefixFilter().hashCode())) * 31) + (getRecursive() != null ? getRecursive().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingGroupsRequest)) {
            return false;
        }
        ListThingGroupsRequest listThingGroupsRequest = (ListThingGroupsRequest) obj;
        if ((listThingGroupsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingGroupsRequest.getNextToken() != null && !listThingGroupsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingGroupsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listThingGroupsRequest.getMaxResults() != null && !listThingGroupsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listThingGroupsRequest.getParentGroup() == null) ^ (getParentGroup() == null)) {
            return false;
        }
        if (listThingGroupsRequest.getParentGroup() != null && !listThingGroupsRequest.getParentGroup().equals(getParentGroup())) {
            return false;
        }
        if ((listThingGroupsRequest.getNamePrefixFilter() == null) ^ (getNamePrefixFilter() == null)) {
            return false;
        }
        if (listThingGroupsRequest.getNamePrefixFilter() != null && !listThingGroupsRequest.getNamePrefixFilter().equals(getNamePrefixFilter())) {
            return false;
        }
        if ((listThingGroupsRequest.getRecursive() == null) ^ (getRecursive() == null)) {
            return false;
        }
        return listThingGroupsRequest.getRecursive() == null || listThingGroupsRequest.getRecursive().equals(getRecursive());
    }
}
