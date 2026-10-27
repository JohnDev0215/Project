package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateBillingGroupResult implements Serializable {
    private String billingGroupArn;
    private String billingGroupId;
    private String billingGroupName;

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public CreateBillingGroupResult withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public String getBillingGroupArn() {
        return this.billingGroupArn;
    }

    public void setBillingGroupArn(String str) {
        this.billingGroupArn = str;
    }

    public CreateBillingGroupResult withBillingGroupArn(String str) {
        this.billingGroupArn = str;
        return this;
    }

    public String getBillingGroupId() {
        return this.billingGroupId;
    }

    public void setBillingGroupId(String str) {
        this.billingGroupId = str;
    }

    public CreateBillingGroupResult withBillingGroupId(String str) {
        this.billingGroupId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBillingGroupName() != null) {
            sb.append("billingGroupName: " + getBillingGroupName() + ",");
        }
        if (getBillingGroupArn() != null) {
            sb.append("billingGroupArn: " + getBillingGroupArn() + ",");
        }
        if (getBillingGroupId() != null) {
            sb.append("billingGroupId: " + getBillingGroupId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31) + (getBillingGroupArn() == null ? 0 : getBillingGroupArn().hashCode())) * 31) + (getBillingGroupId() != null ? getBillingGroupId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateBillingGroupResult)) {
            return false;
        }
        CreateBillingGroupResult createBillingGroupResult = (CreateBillingGroupResult) obj;
        if ((createBillingGroupResult.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (createBillingGroupResult.getBillingGroupName() != null && !createBillingGroupResult.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((createBillingGroupResult.getBillingGroupArn() == null) ^ (getBillingGroupArn() == null)) {
            return false;
        }
        if (createBillingGroupResult.getBillingGroupArn() != null && !createBillingGroupResult.getBillingGroupArn().equals(getBillingGroupArn())) {
            return false;
        }
        if ((createBillingGroupResult.getBillingGroupId() == null) ^ (getBillingGroupId() == null)) {
            return false;
        }
        return createBillingGroupResult.getBillingGroupId() == null || createBillingGroupResult.getBillingGroupId().equals(getBillingGroupId());
    }
}
