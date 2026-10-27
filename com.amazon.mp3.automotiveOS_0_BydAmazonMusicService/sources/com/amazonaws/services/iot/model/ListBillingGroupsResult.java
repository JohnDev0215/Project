package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListBillingGroupsResult implements Serializable {
    private List<GroupNameAndArn> billingGroups;
    private String nextToken;

    public List<GroupNameAndArn> getBillingGroups() {
        return this.billingGroups;
    }

    public void setBillingGroups(Collection<GroupNameAndArn> collection) {
        if (collection == null) {
            this.billingGroups = null;
        } else {
            this.billingGroups = new ArrayList(collection);
        }
    }

    public ListBillingGroupsResult withBillingGroups(GroupNameAndArn... groupNameAndArnArr) {
        if (getBillingGroups() == null) {
            this.billingGroups = new ArrayList(groupNameAndArnArr.length);
        }
        for (GroupNameAndArn groupNameAndArn : groupNameAndArnArr) {
            this.billingGroups.add(groupNameAndArn);
        }
        return this;
    }

    public ListBillingGroupsResult withBillingGroups(Collection<GroupNameAndArn> collection) {
        setBillingGroups(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListBillingGroupsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBillingGroups() != null) {
            sb.append("billingGroups: " + getBillingGroups() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getBillingGroups() == null ? 0 : getBillingGroups().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListBillingGroupsResult)) {
            return false;
        }
        ListBillingGroupsResult listBillingGroupsResult = (ListBillingGroupsResult) obj;
        if ((listBillingGroupsResult.getBillingGroups() == null) ^ (getBillingGroups() == null)) {
            return false;
        }
        if (listBillingGroupsResult.getBillingGroups() != null && !listBillingGroupsResult.getBillingGroups().equals(getBillingGroups())) {
            return false;
        }
        if ((listBillingGroupsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listBillingGroupsResult.getNextToken() == null || listBillingGroupsResult.getNextToken().equals(getNextToken());
    }
}
