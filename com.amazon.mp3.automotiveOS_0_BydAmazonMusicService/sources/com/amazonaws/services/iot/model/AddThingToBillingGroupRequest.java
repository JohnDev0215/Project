package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AddThingToBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String billingGroupArn;
    private String billingGroupName;
    private String thingArn;
    private String thingName;

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public AddThingToBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public String getBillingGroupArn() {
        return this.billingGroupArn;
    }

    public void setBillingGroupArn(String str) {
        this.billingGroupArn = str;
    }

    public AddThingToBillingGroupRequest withBillingGroupArn(String str) {
        this.billingGroupArn = str;
        return this;
    }

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public AddThingToBillingGroupRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public AddThingToBillingGroupRequest withThingArn(String str) {
        this.thingArn = str;
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
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getThingArn() != null) {
            sb.append("thingArn: " + getThingArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31) + (getBillingGroupArn() == null ? 0 : getBillingGroupArn().hashCode())) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getThingArn() != null ? getThingArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AddThingToBillingGroupRequest)) {
            return false;
        }
        AddThingToBillingGroupRequest addThingToBillingGroupRequest = (AddThingToBillingGroupRequest) obj;
        if ((addThingToBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (addThingToBillingGroupRequest.getBillingGroupName() != null && !addThingToBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((addThingToBillingGroupRequest.getBillingGroupArn() == null) ^ (getBillingGroupArn() == null)) {
            return false;
        }
        if (addThingToBillingGroupRequest.getBillingGroupArn() != null && !addThingToBillingGroupRequest.getBillingGroupArn().equals(getBillingGroupArn())) {
            return false;
        }
        if ((addThingToBillingGroupRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (addThingToBillingGroupRequest.getThingName() != null && !addThingToBillingGroupRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((addThingToBillingGroupRequest.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        return addThingToBillingGroupRequest.getThingArn() == null || addThingToBillingGroupRequest.getThingArn().equals(getThingArn());
    }
}
