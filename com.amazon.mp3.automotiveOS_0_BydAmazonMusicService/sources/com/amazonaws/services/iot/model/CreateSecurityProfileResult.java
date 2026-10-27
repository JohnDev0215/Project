package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateSecurityProfileResult implements Serializable {
    private String securityProfileArn;
    private String securityProfileName;

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public CreateSecurityProfileResult withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String getSecurityProfileArn() {
        return this.securityProfileArn;
    }

    public void setSecurityProfileArn(String str) {
        this.securityProfileArn = str;
    }

    public CreateSecurityProfileResult withSecurityProfileArn(String str) {
        this.securityProfileArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
        }
        if (getSecurityProfileArn() != null) {
            sb.append("securityProfileArn: " + getSecurityProfileArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getSecurityProfileArn() != null ? getSecurityProfileArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateSecurityProfileResult)) {
            return false;
        }
        CreateSecurityProfileResult createSecurityProfileResult = (CreateSecurityProfileResult) obj;
        if ((createSecurityProfileResult.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (createSecurityProfileResult.getSecurityProfileName() != null && !createSecurityProfileResult.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((createSecurityProfileResult.getSecurityProfileArn() == null) ^ (getSecurityProfileArn() == null)) {
            return false;
        }
        return createSecurityProfileResult.getSecurityProfileArn() == null || createSecurityProfileResult.getSecurityProfileArn().equals(getSecurityProfileArn());
    }
}
