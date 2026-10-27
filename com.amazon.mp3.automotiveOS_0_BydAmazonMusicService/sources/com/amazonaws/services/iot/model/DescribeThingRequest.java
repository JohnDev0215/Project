package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingRequest extends AmazonWebServiceRequest implements Serializable {
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public DescribeThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getThingName() == null ? 0 : getThingName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingRequest)) {
            return false;
        }
        DescribeThingRequest describeThingRequest = (DescribeThingRequest) obj;
        if ((describeThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        return describeThingRequest.getThingName() == null || describeThingRequest.getThingName().equals(getThingName());
    }
}
