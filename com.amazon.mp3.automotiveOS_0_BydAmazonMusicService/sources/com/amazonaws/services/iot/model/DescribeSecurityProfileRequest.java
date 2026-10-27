package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private String securityProfileName;

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public DescribeSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeSecurityProfileRequest)) {
            return false;
        }
        DescribeSecurityProfileRequest describeSecurityProfileRequest = (DescribeSecurityProfileRequest) obj;
        if ((describeSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        return describeSecurityProfileRequest.getSecurityProfileName() == null || describeSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName());
    }
}
