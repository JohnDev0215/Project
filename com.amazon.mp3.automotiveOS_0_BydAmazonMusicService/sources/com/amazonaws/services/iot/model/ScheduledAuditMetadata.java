package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ScheduledAuditMetadata implements Serializable {
    private String dayOfMonth;
    private String dayOfWeek;
    private String frequency;
    private String scheduledAuditArn;
    private String scheduledAuditName;

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public void setScheduledAuditName(String str) {
        this.scheduledAuditName = str;
    }

    public ScheduledAuditMetadata withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }

    public String getScheduledAuditArn() {
        return this.scheduledAuditArn;
    }

    public void setScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
    }

    public ScheduledAuditMetadata withScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
        return this;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String str) {
        this.frequency = str;
    }

    public ScheduledAuditMetadata withFrequency(String str) {
        this.frequency = str;
        return this;
    }

    public void setFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
    }

    public ScheduledAuditMetadata withFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
        return this;
    }

    public String getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(String str) {
        this.dayOfMonth = str;
    }

    public ScheduledAuditMetadata withDayOfMonth(String str) {
        this.dayOfMonth = str;
        return this;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(String str) {
        this.dayOfWeek = str;
    }

    public ScheduledAuditMetadata withDayOfWeek(String str) {
        this.dayOfWeek = str;
        return this;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
    }

    public ScheduledAuditMetadata withDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getScheduledAuditName() != null) {
            sb.append("scheduledAuditName: " + getScheduledAuditName() + ",");
        }
        if (getScheduledAuditArn() != null) {
            sb.append("scheduledAuditArn: " + getScheduledAuditArn() + ",");
        }
        if (getFrequency() != null) {
            sb.append("frequency: " + getFrequency() + ",");
        }
        if (getDayOfMonth() != null) {
            sb.append("dayOfMonth: " + getDayOfMonth() + ",");
        }
        if (getDayOfWeek() != null) {
            sb.append("dayOfWeek: " + getDayOfWeek());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getScheduledAuditName() == null ? 0 : getScheduledAuditName().hashCode()) + 31) * 31) + (getScheduledAuditArn() == null ? 0 : getScheduledAuditArn().hashCode())) * 31) + (getFrequency() == null ? 0 : getFrequency().hashCode())) * 31) + (getDayOfMonth() == null ? 0 : getDayOfMonth().hashCode())) * 31) + (getDayOfWeek() != null ? getDayOfWeek().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ScheduledAuditMetadata)) {
            return false;
        }
        ScheduledAuditMetadata scheduledAuditMetadata = (ScheduledAuditMetadata) obj;
        if ((scheduledAuditMetadata.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        if (scheduledAuditMetadata.getScheduledAuditName() != null && !scheduledAuditMetadata.getScheduledAuditName().equals(getScheduledAuditName())) {
            return false;
        }
        if ((scheduledAuditMetadata.getScheduledAuditArn() == null) ^ (getScheduledAuditArn() == null)) {
            return false;
        }
        if (scheduledAuditMetadata.getScheduledAuditArn() != null && !scheduledAuditMetadata.getScheduledAuditArn().equals(getScheduledAuditArn())) {
            return false;
        }
        if ((scheduledAuditMetadata.getFrequency() == null) ^ (getFrequency() == null)) {
            return false;
        }
        if (scheduledAuditMetadata.getFrequency() != null && !scheduledAuditMetadata.getFrequency().equals(getFrequency())) {
            return false;
        }
        if ((scheduledAuditMetadata.getDayOfMonth() == null) ^ (getDayOfMonth() == null)) {
            return false;
        }
        if (scheduledAuditMetadata.getDayOfMonth() != null && !scheduledAuditMetadata.getDayOfMonth().equals(getDayOfMonth())) {
            return false;
        }
        if ((scheduledAuditMetadata.getDayOfWeek() == null) ^ (getDayOfWeek() == null)) {
            return false;
        }
        return scheduledAuditMetadata.getDayOfWeek() == null || scheduledAuditMetadata.getDayOfWeek().equals(getDayOfWeek());
    }
}
