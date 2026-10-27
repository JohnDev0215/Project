package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String securityProfileName;

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public DeleteSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public DeleteSecurityProfileRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getExpectedVersion() != null ? getExpectedVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteSecurityProfileRequest)) {
            return false;
        }
        DeleteSecurityProfileRequest deleteSecurityProfileRequest = (DeleteSecurityProfileRequest) obj;
        if ((deleteSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (deleteSecurityProfileRequest.getSecurityProfileName() != null && !deleteSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((deleteSecurityProfileRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return deleteSecurityProfileRequest.getExpectedVersion() == null || deleteSecurityProfileRequest.getExpectedVersion().equals(getExpectedVersion());
    }
}
