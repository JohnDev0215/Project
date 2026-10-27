package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateScheduledAuditRequest extends AmazonWebServiceRequest implements Serializable {
    private String dayOfMonth;
    private String dayOfWeek;
    private String frequency;
    private String scheduledAuditName;
    private List<Tag> tags;
    private List<String> targetCheckNames;

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String str) {
        this.frequency = str;
    }

    public CreateScheduledAuditRequest withFrequency(String str) {
        this.frequency = str;
        return this;
    }

    public void setFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
    }

    public CreateScheduledAuditRequest withFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
        return this;
    }

    public String getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(String str) {
        this.dayOfMonth = str;
    }

    public CreateScheduledAuditRequest withDayOfMonth(String str) {
        this.dayOfMonth = str;
        return this;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(String str) {
        this.dayOfWeek = str;
    }

    public CreateScheduledAuditRequest withDayOfWeek(String str) {
        this.dayOfWeek = str;
        return this;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
    }

    public CreateScheduledAuditRequest withDayOfWeek(DayOfWeek dayOfWeek) {
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

    public CreateScheduledAuditRequest withTargetCheckNames(String... strArr) {
        if (getTargetCheckNames() == null) {
            this.targetCheckNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targetCheckNames.add(str);
        }
        return this;
    }

    public CreateScheduledAuditRequest withTargetCheckNames(Collection<String> collection) {
        setTargetCheckNames(collection);
        return this;
    }

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public void setScheduledAuditName(String str) {
        this.scheduledAuditName = str;
    }

    public CreateScheduledAuditRequest withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public CreateScheduledAuditRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateScheduledAuditRequest withTags(Collection<Tag> collection) {
        setTags(collection);
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
            sb.append("scheduledAuditName: " + getScheduledAuditName() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getFrequency() == null ? 0 : getFrequency().hashCode()) + 31) * 31) + (getDayOfMonth() == null ? 0 : getDayOfMonth().hashCode())) * 31) + (getDayOfWeek() == null ? 0 : getDayOfWeek().hashCode())) * 31) + (getTargetCheckNames() == null ? 0 : getTargetCheckNames().hashCode())) * 31) + (getScheduledAuditName() == null ? 0 : getScheduledAuditName().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateScheduledAuditRequest)) {
            return false;
        }
        CreateScheduledAuditRequest createScheduledAuditRequest = (CreateScheduledAuditRequest) obj;
        if ((createScheduledAuditRequest.getFrequency() == null) ^ (getFrequency() == null)) {
            return false;
        }
        if (createScheduledAuditRequest.getFrequency() != null && !createScheduledAuditRequest.getFrequency().equals(getFrequency())) {
            return false;
        }
        if ((createScheduledAuditRequest.getDayOfMonth() == null) ^ (getDayOfMonth() == null)) {
            return false;
        }
        if (createScheduledAuditRequest.getDayOfMonth() != null && !createScheduledAuditRequest.getDayOfMonth().equals(getDayOfMonth())) {
            return false;
        }
        if ((createScheduledAuditRequest.getDayOfWeek() == null) ^ (getDayOfWeek() == null)) {
            return false;
        }
        if (createScheduledAuditRequest.getDayOfWeek() != null && !createScheduledAuditRequest.getDayOfWeek().equals(getDayOfWeek())) {
            return false;
        }
        if ((createScheduledAuditRequest.getTargetCheckNames() == null) ^ (getTargetCheckNames() == null)) {
            return false;
        }
        if (createScheduledAuditRequest.getTargetCheckNames() != null && !createScheduledAuditRequest.getTargetCheckNames().equals(getTargetCheckNames())) {
            return false;
        }
        if ((createScheduledAuditRequest.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        if (createScheduledAuditRequest.getScheduledAuditName() != null && !createScheduledAuditRequest.getScheduledAuditName().equals(getScheduledAuditName())) {
            return false;
        }
        if ((createScheduledAuditRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createScheduledAuditRequest.getTags() == null || createScheduledAuditRequest.getTags().equals(getTags());
    }
}
