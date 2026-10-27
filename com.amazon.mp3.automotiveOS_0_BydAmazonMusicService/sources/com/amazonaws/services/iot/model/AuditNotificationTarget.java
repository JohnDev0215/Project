package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AuditNotificationTarget implements Serializable {
    private Boolean enabled;
    private String roleArn;
    private String targetArn;

    public String getTargetArn() {
        return this.targetArn;
    }

    public void setTargetArn(String str) {
        this.targetArn = str;
    }

    public AuditNotificationTarget withTargetArn(String str) {
        this.targetArn = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public AuditNotificationTarget withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean bool) {
        this.enabled = bool;
    }

    public AuditNotificationTarget withEnabled(Boolean bool) {
        this.enabled = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTargetArn() != null) {
            sb.append("targetArn: " + getTargetArn() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getEnabled() != null) {
            sb.append("enabled: " + getEnabled());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getTargetArn() == null ? 0 : getTargetArn().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getEnabled() != null ? getEnabled().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditNotificationTarget)) {
            return false;
        }
        AuditNotificationTarget auditNotificationTarget = (AuditNotificationTarget) obj;
        if ((auditNotificationTarget.getTargetArn() == null) ^ (getTargetArn() == null)) {
            return false;
        }
        if (auditNotificationTarget.getTargetArn() != null && !auditNotificationTarget.getTargetArn().equals(getTargetArn())) {
            return false;
        }
        if ((auditNotificationTarget.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (auditNotificationTarget.getRoleArn() != null && !auditNotificationTarget.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((auditNotificationTarget.getEnabled() == null) ^ (getEnabled() == null)) {
            return false;
        }
        return auditNotificationTarget.getEnabled() == null || auditNotificationTarget.getEnabled().equals(getEnabled());
    }
}
