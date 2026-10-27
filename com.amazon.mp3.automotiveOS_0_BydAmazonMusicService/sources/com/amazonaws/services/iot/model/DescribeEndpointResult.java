package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeEndpointResult implements Serializable {
    private String endpointAddress;

    public String getEndpointAddress() {
        return this.endpointAddress;
    }

    public void setEndpointAddress(String str) {
        this.endpointAddress = str;
    }

    public DescribeEndpointResult withEndpointAddress(String str) {
        this.endpointAddress = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getEndpointAddress() != null) {
            sb.append("endpointAddress: " + getEndpointAddress());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getEndpointAddress() == null ? 0 : getEndpointAddress().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeEndpointResult)) {
            return false;
        }
        DescribeEndpointResult describeEndpointResult = (DescribeEndpointResult) obj;
        if ((describeEndpointResult.getEndpointAddress() == null) ^ (getEndpointAddress() == null)) {
            return false;
        }
        return describeEndpointResult.getEndpointAddress() == null || describeEndpointResult.getEndpointAddress().equals(getEndpointAddress());
    }
}
