package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateScheduledAuditRequest extends AmazonWebServiceRequest implements Serializable {
    private String dayOfMonth;
    private String dayOfWeek;
    private String frequency;
    private String scheduledAuditName;
    private List<String> targetCheckNames;

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String str) {
        this.frequency = str;
    }

    public UpdateScheduledAuditRequest withFrequency(String str) {
        this.frequency = str;
        return this;
    }

    public void setFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
    }

    public UpdateScheduledAuditRequest withFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
        return this;
    }

    public String getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(String str) {
        this.dayOfMonth = str;
    }

    public UpdateScheduledAuditRequest withDayOfMonth(String str) {
        this.dayOfMonth = str;
        return this;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(String str) {
        this.dayOfWeek = str;
    }

    public UpdateScheduledAuditRequest withDayOfWeek(String str) {
        this.dayOfWeek = str;
        return this;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
    }

    public UpdateScheduledAuditRequest withDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
        return this;
    }

    public List<String> getTargetCheckNames() {
        return this.targetCheckNames;
    }

    public void setTargetCheckNames(Collection<String> collection) {
        if (collection == null) {
            this.targetCheckNames = null;
        } else {
            this.targetCheckNames = new ArrayList(collection);
        }
    }

    public UpdateScheduledAuditRequest withTargetCheckNames(String... strArr) {
        if (getTargetCheckNames() == null) {
            this.targetCheckNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targetCheckNames.add(str);
        }
        return this;
    }

    public UpdateScheduledAuditRequest withTargetCheckNames(Collection<String> collection) {
        setTargetCheckNames(collection);
        return this;
    }

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public void setScheduledAuditName(String str) {
        this.scheduledAuditName = str;
    }

    public UpdateScheduledAuditRequest withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFrequency() != null) {
            sb.append("frequency: " + getFrequency() + ",");
        }
        if (getDayOfMonth() != null) {
            sb.append("dayOfMonth: " + getDayOfMonth() + ",");
        }
        if (getDayOfWeek() != null) {
            sb.append("dayOfWeek: " + getDayOfWeek() + ",");
        }
        if (getTargetCheckNames() != null) {
            sb.append("targetCheckNames: " + getTargetCheckNames() + ",");
        }
        if (getScheduledAuditName() != null) {
            sb.append("scheduledAuditName: " + getScheduledAuditName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getFrequency() == null ? 0 : getFrequency().hashCode()) + 31) * 31) + (getDayOfMonth() == null ? 0 : getDayOfMonth().hashCode())) * 31) + (getDayOfWeek() == null ? 0 : getDayOfWeek().hashCode())) * 31) + (getTargetCheckNames() == null ? 0 : getTargetCheckNames().hashCode())) * 31) + (getScheduledAuditName() != null ? getScheduledAuditName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateScheduledAuditRequest)) {
            return false;
        }
        UpdateScheduledAuditRequest updateScheduledAuditRequest = (UpdateScheduledAuditRequest) obj;
        if ((updateScheduledAuditRequest.getFrequency() == null) ^ (getFrequency() == null)) {
            return false;
        }
        if (updateScheduledAuditRequest.getFrequency() != null && !updateScheduledAuditRequest.getFrequency().equals(getFrequency())) {
            return false;
        }
        if ((updateScheduledAuditRequest.getDayOfMonth() == null) ^ (getDayOfMonth() == null)) {
            return false;
        }
        if (updateScheduledAuditRequest.getDayOfMonth() != null && !updateScheduledAuditRequest.getDayOfMonth().equals(getDayOfMonth())) {
            return false;
        }
        if ((updateScheduledAuditRequest.getDayOfWeek() == null) ^ (getDayOfWeek() == null)) {
            return false;
        }
        if (updateScheduledAuditRequest.getDayOfWeek() != null && !updateScheduledAuditRequest.getDayOfWeek().equals(getDayOfWeek())) {
            return false;
        }
        if ((updateScheduledAuditRequest.getTargetCheckNames() == null) ^ (getTargetCheckNames() == null)) {
            return false;
        }
        if (updateScheduledAuditRequest.getTargetCheckNames() != null && !updateScheduledAuditRequest.getTargetCheckNames().equals(getTargetCheckNames())) {
            return false;
        }
        if ((updateScheduledAuditRequest.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        return updateScheduledAuditRequest.getScheduledAuditName() == null || updateScheduledAuditRequest.getScheduledAuditName().equals(getScheduledAuditName());
    }
}
