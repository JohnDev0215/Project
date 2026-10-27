package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class RemoveThingFromBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
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

    public RemoveThingFromBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public String getBillingGroupArn() {
        return this.billingGroupArn;
    }

    public void setBillingGroupArn(String str) {
        this.billingGroupArn = str;
    }

    public RemoveThingFromBillingGroupRequest withBillingGroupArn(String str) {
        this.billingGroupArn = str;
        return this;
    }

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public RemoveThingFromBillingGroupRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public RemoveThingFromBillingGroupRequest withThingArn(String str) {
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
        if (obj == null || !(obj instanceof RemoveThingFromBillingGroupRequest)) {
            return false;
        }
        RemoveThingFromBillingGroupRequest removeThingFromBillingGroupRequest = (RemoveThingFromBillingGroupRequest) obj;
        if ((removeThingFromBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (removeThingFromBillingGroupRequest.getBillingGroupName() != null && !removeThingFromBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((removeThingFromBillingGroupRequest.getBillingGroupArn() == null) ^ (getBillingGroupArn() == null)) {
            return false;
        }
        if (removeThingFromBillingGroupRequest.getBillingGroupArn() != null && !removeThingFromBillingGroupRequest.getBillingGroupArn().equals(getBillingGroupArn())) {
            return false;
        }
        if ((removeThingFromBillingGroupRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (removeThingFromBillingGroupRequest.getThingName() != null && !removeThingFromBillingGroupRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((removeThingFromBillingGroupRequest.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        return removeThingFromBillingGroupRequest.getThingArn() == null || removeThingFromBillingGroupRequest.getThingArn().equals(getThingArn());
    }
}
