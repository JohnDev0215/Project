package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateScheduledAuditResult implements Serializable {
    private String scheduledAuditArn;

    public String getScheduledAuditArn() {
        return this.scheduledAuditArn;
    }

    public void setScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
    }

    public CreateScheduledAuditResult withScheduledAuditArn(String str) {
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
        if (obj == null || !(obj instanceof CreateScheduledAuditResult)) {
            return false;
        }
        CreateScheduledAuditResult createScheduledAuditResult = (CreateScheduledAuditResult) obj;
        if ((createScheduledAuditResult.getScheduledAuditArn() == null) ^ (getScheduledAuditArn() == null)) {
            return false;
        }
        return createScheduledAuditResult.getScheduledAuditArn() == null || createScheduledAuditResult.getScheduledAuditArn().equals(getScheduledAuditArn());
    }
}
