package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class SnsAction implements Serializable {
    private String messageFormat;
    private String roleArn;
    private String targetArn;

    public String getTargetArn() {
        return this.targetArn;
    }

    public void setTargetArn(String str) {
        this.targetArn = str;
    }

    public SnsAction withTargetArn(String str) {
        this.targetArn = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public SnsAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getMessageFormat() {
        return this.messageFormat;
    }

    public void setMessageFormat(String str) {
        this.messageFormat = str;
    }

    public SnsAction withMessageFormat(String str) {
        this.messageFormat = str;
        return this;
    }

    public void setMessageFormat(MessageFormat messageFormat) {
        this.messageFormat = messageFormat.toString();
    }

    public SnsAction withMessageFormat(MessageFormat messageFormat) {
        this.messageFormat = messageFormat.toString();
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
        if (getMessageFormat() != null) {
            sb.append("messageFormat: " + getMessageFormat());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getTargetArn() == null ? 0 : getTargetArn().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getMessageFormat() != null ? getMessageFormat().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SnsAction)) {
            return false;
        }
        SnsAction snsAction = (SnsAction) obj;
        if ((snsAction.getTargetArn() == null) ^ (getTargetArn() == null)) {
            return false;
        }
        if (snsAction.getTargetArn() != null && !snsAction.getTargetArn().equals(getTargetArn())) {
            return false;
        }
        if ((snsAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (snsAction.getRoleArn() != null && !snsAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((snsAction.getMessageFormat() == null) ^ (getMessageFormat() == null)) {
            return false;
        }
        return snsAction.getMessageFormat() == null || snsAction.getMessageFormat().equals(getMessageFormat());
    }
}
