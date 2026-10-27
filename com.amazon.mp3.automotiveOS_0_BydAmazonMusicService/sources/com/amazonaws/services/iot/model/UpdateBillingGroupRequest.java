package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String billingGroupName;
    private BillingGroupProperties billingGroupProperties;
    private Long expectedVersion;

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public UpdateBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public BillingGroupProperties getBillingGroupProperties() {
        return this.billingGroupProperties;
    }

    public void setBillingGroupProperties(BillingGroupProperties billingGroupProperties) {
        this.billingGroupProperties = billingGroupProperties;
    }

    public UpdateBillingGroupRequest withBillingGroupProperties(BillingGroupProperties billingGroupProperties) {
        this.billingGroupProperties = billingGroupProperties;
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public UpdateBillingGroupRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBillingGroupName() != null) {
            sb.append("billingGroupName: " + getBillingGroupName() + ",");
        }
        if (getBillingGroupProperties() != null) {
            sb.append("billingGroupProperties: " + getBillingGroupProperties() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31) + (getBillingGroupProperties() == null ? 0 : getBillingGroupProperties().hashCode())) * 31) + (getExpectedVersion() != null ? getExpectedVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateBillingGroupRequest)) {
            return false;
        }
        UpdateBillingGroupRequest updateBillingGroupRequest = (UpdateBillingGroupRequest) obj;
        if ((updateBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (updateBillingGroupRequest.getBillingGroupName() != null && !updateBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((updateBillingGroupRequest.getBillingGroupProperties() == null) ^ (getBillingGroupProperties() == null)) {
            return false;
        }
        if (updateBillingGroupRequest.getBillingGroupProperties() != null && !updateBillingGroupRequest.getBillingGroupProperties().equals(getBillingGroupProperties())) {
            return false;
        }
        if ((updateBillingGroupRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return updateBillingGroupRequest.getExpectedVersion() == null || updateBillingGroupRequest.getExpectedVersion().equals(getExpectedVersion());
    }
}
