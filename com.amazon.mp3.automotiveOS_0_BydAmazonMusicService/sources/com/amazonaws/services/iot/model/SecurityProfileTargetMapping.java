package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class SecurityProfileTargetMapping implements Serializable {
    private SecurityProfileIdentifier securityProfileIdentifier;
    private SecurityProfileTarget target;

    public SecurityProfileIdentifier getSecurityProfileIdentifier() {
        return this.securityProfileIdentifier;
    }

    public void setSecurityProfileIdentifier(SecurityProfileIdentifier securityProfileIdentifier) {
        this.securityProfileIdentifier = securityProfileIdentifier;
    }

    public SecurityProfileTargetMapping withSecurityProfileIdentifier(SecurityProfileIdentifier securityProfileIdentifier) {
        this.securityProfileIdentifier = securityProfileIdentifier;
        return this;
    }

    public SecurityProfileTarget getTarget() {
        return this.target;
    }

    public void setTarget(SecurityProfileTarget securityProfileTarget) {
        this.target = securityProfileTarget;
    }

    public SecurityProfileTargetMapping withTarget(SecurityProfileTarget securityProfileTarget) {
        this.target = securityProfileTarget;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileIdentifier() != null) {
            sb.append("securityProfileIdentifier: " + getSecurityProfileIdentifier() + ",");
        }
        if (getTarget() != null) {
            sb.append("target: " + getTarget());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getSecurityProfileIdentifier() == null ? 0 : getSecurityProfileIdentifier().hashCode()) + 31) * 31) + (getTarget() != null ? getTarget().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SecurityProfileTargetMapping)) {
            return false;
        }
        SecurityProfileTargetMapping securityProfileTargetMapping = (SecurityProfileTargetMapping) obj;
        if ((securityProfileTargetMapping.getSecurityProfileIdentifier() == null) ^ (getSecurityProfileIdentifier() == null)) {
            return false;
        }
        if (securityProfileTargetMapping.getSecurityProfileIdentifier() != null && !securityProfileTargetMapping.getSecurityProfileIdentifier().equals(getSecurityProfileIdentifier())) {
            return false;
        }
        if ((securityProfileTargetMapping.getTarget() == null) ^ (getTarget() == null)) {
            return false;
        }
        return securityProfileTargetMapping.getTarget() == null || securityProfileTargetMapping.getTarget().equals(getTarget());
    }
}
