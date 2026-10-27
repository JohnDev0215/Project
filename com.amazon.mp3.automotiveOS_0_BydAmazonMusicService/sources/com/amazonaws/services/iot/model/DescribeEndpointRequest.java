package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeEndpointRequest extends AmazonWebServiceRequest implements Serializable {
    private String endpointType;

    public String getEndpointType() {
        return this.endpointType;
    }

    public void setEndpointType(String str) {
        this.endpointType = str;
    }

    public DescribeEndpointRequest withEndpointType(String str) {
        this.endpointType = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getEndpointType() != null) {
            sb.append("endpointType: " + getEndpointType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getEndpointType() == null ? 0 : getEndpointType().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeEndpointRequest)) {
            return false;
        }
        DescribeEndpointRequest describeEndpointRequest = (DescribeEndpointRequest) obj;
        if ((describeEndpointRequest.getEndpointType() == null) ^ (getEndpointType() == null)) {
            return false;
        }
        return describeEndpointRequest.getEndpointType() == null || describeEndpointRequest.getEndpointType().equals(getEndpointType());
    }
}
