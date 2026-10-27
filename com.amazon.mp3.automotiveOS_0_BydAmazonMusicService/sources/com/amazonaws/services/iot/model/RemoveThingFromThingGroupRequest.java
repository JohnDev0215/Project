package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class RemoveThingFromThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String thingArn;
    private String thingGroupArn;
    private String thingGroupName;
    private String thingName;

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public RemoveThingFromThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public String getThingGroupArn() {
        return this.thingGroupArn;
    }

    public void setThingGroupArn(String str) {
        this.thingGroupArn = str;
    }

    public RemoveThingFromThingGroupRequest withThingGroupArn(String str) {
        this.thingGroupArn = str;
        return this;
    }

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public RemoveThingFromThingGroupRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public RemoveThingFromThingGroupRequest withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupName() != null) {
            sb.append("thingGroupName: " + getThingGroupName() + ",");
        }
        if (getThingGroupArn() != null) {
            sb.append("thingGroupArn: " + getThingGroupArn() + ",");
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
        return (((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupArn() == null ? 0 : getThingGroupArn().hashCode())) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getThingArn() != null ? getThingArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RemoveThingFromThingGroupRequest)) {
            return false;
        }
        RemoveThingFromThingGroupRequest removeThingFromThingGroupRequest = (RemoveThingFromThingGroupRequest) obj;
        if ((removeThingFromThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (removeThingFromThingGroupRequest.getThingGroupName() != null && !removeThingFromThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((removeThingFromThingGroupRequest.getThingGroupArn() == null) ^ (getThingGroupArn() == null)) {
            return false;
        }
        if (removeThingFromThingGroupRequest.getThingGroupArn() != null && !removeThingFromThingGroupRequest.getThingGroupArn().equals(getThingGroupArn())) {
            return false;
        }
        if ((removeThingFromThingGroupRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (removeThingFromThingGroupRequest.getThingName() != null && !removeThingFromThingGroupRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((removeThingFromThingGroupRequest.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        return removeThingFromThingGroupRequest.getThingArn() == null || removeThingFromThingGroupRequest.getThingArn().equals(getThingArn());
    }
}
