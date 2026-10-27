package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingsInBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String billingGroupName;
    private Integer maxResults;
    private String nextToken;

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public ListThingsInBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingsInBillingGroupRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListThingsInBillingGroupRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBillingGroupName() != null) {
            sb.append("billingGroupName: " + getBillingGroupName() + ",");
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
        return (((((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingsInBillingGroupRequest)) {
            return false;
        }
        ListThingsInBillingGroupRequest listThingsInBillingGroupRequest = (ListThingsInBillingGroupRequest) obj;
        if ((listThingsInBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (listThingsInBillingGroupRequest.getBillingGroupName() != null && !listThingsInBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((listThingsInBillingGroupRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingsInBillingGroupRequest.getNextToken() != null && !listThingsInBillingGroupRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingsInBillingGroupRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listThingsInBillingGroupRequest.getMaxResults() == null || listThingsInBillingGroupRequest.getMaxResults().equals(getMaxResults());
    }
}
