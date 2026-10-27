package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class BillingGroupProperties implements Serializable {
    private String billingGroupDescription;

    public String getBillingGroupDescription() {
        return this.billingGroupDescription;
    }

    public void setBillingGroupDescription(String str) {
        this.billingGroupDescription = str;
    }

    public BillingGroupProperties withBillingGroupDescription(String str) {
        this.billingGroupDescription = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBillingGroupDescription() != null) {
            sb.append("billingGroupDescription: " + getBillingGroupDescription());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getBillingGroupDescription() == null ? 0 : getBillingGroupDescription().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BillingGroupProperties)) {
            return false;
        }
        BillingGroupProperties billingGroupProperties = (BillingGroupProperties) obj;
        if ((billingGroupProperties.getBillingGroupDescription() == null) ^ (getBillingGroupDescription() == null)) {
            return false;
        }
        return billingGroupProperties.getBillingGroupDescription() == null || billingGroupProperties.getBillingGroupDescription().equals(getBillingGroupDescription());
    }
}
