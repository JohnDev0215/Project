package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String billingGroupName;

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public DescribeBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBillingGroupName() != null) {
            sb.append("billingGroupName: " + getBillingGroupName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeBillingGroupRequest)) {
            return false;
        }
        DescribeBillingGroupRequest describeBillingGroupRequest = (DescribeBillingGroupRequest) obj;
        if ((describeBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        return describeBillingGroupRequest.getBillingGroupName() == null || describeBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName());
    }
}
