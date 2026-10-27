package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateScheduledAuditResult implements Serializable {
    private String scheduledAuditArn;

    public String getScheduledAuditArn() {
        return this.scheduledAuditArn;
    }

    public void setScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
    }

    public UpdateScheduledAuditResult withScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getScheduledAuditArn() != null) {
            sb.append("scheduledAuditArn: " + getScheduledAuditArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getScheduledAuditArn() == null ? 0 : getScheduledAuditArn().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateScheduledAuditResult)) {
            return false;
        }
        UpdateScheduledAuditResult updateScheduledAuditResult = (UpdateScheduledAuditResult) obj;
        if ((updateScheduledAuditResult.getScheduledAuditArn() == null) ^ (getScheduledAuditArn() == null)) {
            return false;
        }
        return updateScheduledAuditResult.getScheduledAuditArn() == null || updateScheduledAuditResult.getScheduledAuditArn().equals(getScheduledAuditArn());
    }
}
