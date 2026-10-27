package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String thingGroupName;

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public DescribeThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupName() != null) {
            sb.append("thingGroupName: " + getThingGroupName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getThingGroupName() == null ? 0 : getThingGroupName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingGroupRequest)) {
            return false;
        }
        DescribeThingGroupRequest describeThingGroupRequest = (DescribeThingGroupRequest) obj;
        if ((describeThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        return describeThingGroupRequest.getThingGroupName() == null || describeThingGroupRequest.getThingGroupName().equals(getThingGroupName());
    }
}
