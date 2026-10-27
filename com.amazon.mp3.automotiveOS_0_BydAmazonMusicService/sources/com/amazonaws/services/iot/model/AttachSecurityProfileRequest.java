package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AttachSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private String securityProfileName;
    private String securityProfileTargetArn;

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public AttachSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String getSecurityProfileTargetArn() {
        return this.securityProfileTargetArn;
    }

    public void setSecurityProfileTargetArn(String str) {
        this.securityProfileTargetArn = str;
    }

    public AttachSecurityProfileRequest withSecurityProfileTargetArn(String str) {
        this.securityProfileTargetArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
        }
        if (getSecurityProfileTargetArn() != null) {
            sb.append("securityProfileTargetArn: " + getSecurityProfileTargetArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getSecurityProfileTargetArn() != null ? getSecurityProfileTargetArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttachSecurityProfileRequest)) {
            return false;
        }
        AttachSecurityProfileRequest attachSecurityProfileRequest = (AttachSecurityProfileRequest) obj;
        if ((attachSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (attachSecurityProfileRequest.getSecurityProfileName() != null && !attachSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((attachSecurityProfileRequest.getSecurityProfileTargetArn() == null) ^ (getSecurityProfileTargetArn() == null)) {
            return false;
        }
        return attachSecurityProfileRequest.getSecurityProfileTargetArn() == null || attachSecurityProfileRequest.getSecurityProfileTargetArn().equals(getSecurityProfileTargetArn());
    }
}
