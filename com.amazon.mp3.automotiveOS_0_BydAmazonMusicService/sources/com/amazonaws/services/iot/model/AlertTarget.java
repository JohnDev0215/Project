package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AlertTarget implements Serializable {
    private String alertTargetArn;
    private String roleArn;

    public String getAlertTargetArn() {
        return this.alertTargetArn;
    }

    public void setAlertTargetArn(String str) {
        this.alertTargetArn = str;
    }

    public AlertTarget withAlertTargetArn(String str) {
        this.alertTargetArn = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public AlertTarget withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAlertTargetArn() != null) {
            sb.append("alertTargetArn: " + getAlertTargetArn() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getAlertTargetArn() == null ? 0 : getAlertTargetArn().hashCode()) + 31) * 31) + (getRoleArn() != null ? getRoleArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AlertTarget)) {
            return false;
        }
        AlertTarget alertTarget = (AlertTarget) obj;
        if ((alertTarget.getAlertTargetArn() == null) ^ (getAlertTargetArn() == null)) {
            return false;
        }
        if (alertTarget.getAlertTargetArn() != null && !alertTarget.getAlertTargetArn().equals(getAlertTargetArn())) {
            return false;
        }
        if ((alertTarget.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return alertTarget.getRoleArn() == null || alertTarget.getRoleArn().equals(getRoleArn());
    }
}
