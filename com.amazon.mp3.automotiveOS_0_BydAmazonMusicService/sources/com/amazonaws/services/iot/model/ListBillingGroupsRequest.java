package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListBillingGroupsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String namePrefixFilter;
    private String nextToken;

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListBillingGroupsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListBillingGroupsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNamePrefixFilter() {
        return this.namePrefixFilter;
    }

    public void setNamePrefixFilter(String str) {
        this.namePrefixFilter = str;
    }

    public ListBillingGroupsRequest withNamePrefixFilter(String str) {
        this.namePrefixFilter = str;
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
        if (getNamePrefixFilter() != null) {
            sb.append("namePrefixFilter: " + getNamePrefixFilter());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNamePrefixFilter() != null ? getNamePrefixFilter().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListBillingGroupsRequest)) {
            return false;
        }
        ListBillingGroupsRequest listBillingGroupsRequest = (ListBillingGroupsRequest) obj;
        if ((listBillingGroupsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listBillingGroupsRequest.getNextToken() != null && !listBillingGroupsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listBillingGroupsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listBillingGroupsRequest.getMaxResults() != null && !listBillingGroupsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listBillingGroupsRequest.getNamePrefixFilter() == null) ^ (getNamePrefixFilter() == null)) {
            return false;
        }
        return listBillingGroupsRequest.getNamePrefixFilter() == null || listBillingGroupsRequest.getNamePrefixFilter().equals(getNamePrefixFilter());
    }
}
