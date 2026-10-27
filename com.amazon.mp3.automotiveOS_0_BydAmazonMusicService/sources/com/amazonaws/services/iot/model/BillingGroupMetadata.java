package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class BillingGroupMetadata implements Serializable {
    private Date creationDate;

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public BillingGroupMetadata withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getCreationDate() == null ? 0 : getCreationDate().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BillingGroupMetadata)) {
            return false;
        }
        BillingGroupMetadata billingGroupMetadata = (BillingGroupMetadata) obj;
        if ((billingGroupMetadata.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return billingGroupMetadata.getCreationDate() == null || billingGroupMetadata.getCreationDate().equals(getCreationDate());
    }
}
