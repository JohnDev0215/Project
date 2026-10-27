package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeScheduledAuditResult implements Serializable {
    private String dayOfMonth;
    private String dayOfWeek;
    private String frequency;
    private String scheduledAuditArn;
    private String scheduledAuditName;
    private List<String> targetCheckNames;

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String str) {
        this.frequency = str;
    }

    public DescribeScheduledAuditResult withFrequency(String str) {
        this.frequency = str;
        return this;
    }

    public void setFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
    }

    public DescribeScheduledAuditResult withFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
        return this;
    }

    public String getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(String str) {
        this.dayOfMonth = str;
    }

    public DescribeScheduledAuditResult withDayOfMonth(String str) {
        this.dayOfMonth = str;
        return this;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(String str) {
        this.dayOfWeek = str;
    }

    public DescribeScheduledAuditResult withDayOfWeek(String str) {
        this.dayOfWeek = str;
        return this;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
    }

    public DescribeScheduledAuditResult withDayOfWeek(DayOfWeek dayOfWeek) {
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

    public DescribeScheduledAuditResult withTargetCheckNames(String... strArr) {
        if (getTargetCheckNames() == null) {
            this.targetCheckNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targetCheckNames.add(str);
        }
        return this;
    }

    public DescribeScheduledAuditResult withTargetCheckNames(Collection<String> collection) {
        setTargetCheckNames(collection);
        return this;
    }

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public void setScheduledAuditName(String str) {
        this.scheduledAuditName = str;
    }

    public DescribeScheduledAuditResult withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }

    public String getScheduledAuditArn() {
        return this.scheduledAuditArn;
    }

    public void setScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
    }

    public DescribeScheduledAuditResult withScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
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
        if (getScheduledAuditArn() != null) {
            sb.append("scheduledAuditArn: " + getScheduledAuditArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getFrequency() == null ? 0 : getFrequency().hashCode()) + 31) * 31) + (getDayOfMonth() == null ? 0 : getDayOfMonth().hashCode())) * 31) + (getDayOfWeek() == null ? 0 : getDayOfWeek().hashCode())) * 31) + (getTargetCheckNames() == null ? 0 : getTargetCheckNames().hashCode())) * 31) + (getScheduledAuditName() == null ? 0 : getScheduledAuditName().hashCode())) * 31) + (getScheduledAuditArn() != null ? getScheduledAuditArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeScheduledAuditResult)) {
            return false;
        }
        DescribeScheduledAuditResult describeScheduledAuditResult = (DescribeScheduledAuditResult) obj;
        if ((describeScheduledAuditResult.getFrequency() == null) ^ (getFrequency() == null)) {
            return false;
        }
        if (describeScheduledAuditResult.getFrequency() != null && !describeScheduledAuditResult.getFrequency().equals(getFrequency())) {
            return false;
        }
        if ((describeScheduledAuditResult.getDayOfMonth() == null) ^ (getDayOfMonth() == null)) {
            return false;
        }
        if (describeScheduledAuditResult.getDayOfMonth() != null && !describeScheduledAuditResult.getDayOfMonth().equals(getDayOfMonth())) {
            return false;
        }
        if ((describeScheduledAuditResult.getDayOfWeek() == null) ^ (getDayOfWeek() == null)) {
            return false;
        }
        if (describeScheduledAuditResult.getDayOfWeek() != null && !describeScheduledAuditResult.getDayOfWeek().equals(getDayOfWeek())) {
            return false;
        }
        if ((describeScheduledAuditResult.getTargetCheckNames() == null) ^ (getTargetCheckNames() == null)) {
            return false;
        }
        if (describeScheduledAuditResult.getTargetCheckNames() != null && !describeScheduledAuditResult.getTargetCheckNames().equals(getTargetCheckNames())) {
            return false;
        }
        if ((describeScheduledAuditResult.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        if (describeScheduledAuditResult.getScheduledAuditName() != null && !describeScheduledAuditResult.getScheduledAuditName().equals(getScheduledAuditName())) {
            return false;
        }
        if ((describeScheduledAuditResult.getScheduledAuditArn() == null) ^ (getScheduledAuditArn() == null)) {
            return false;
        }
        return describeScheduledAuditResult.getScheduledAuditArn() == null || describeScheduledAuditResult.getScheduledAuditArn().equals(getScheduledAuditArn());
    }
}
