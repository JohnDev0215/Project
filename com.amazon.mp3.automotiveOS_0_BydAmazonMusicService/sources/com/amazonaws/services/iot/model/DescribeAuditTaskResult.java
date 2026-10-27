package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditTaskResult implements Serializable {
    private Map<String, AuditCheckDetails> auditDetails;
    private String scheduledAuditName;
    private Date taskStartTime;
    private TaskStatistics taskStatistics;
    private String taskStatus;
    private String taskType;

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public DescribeAuditTaskResult withTaskStatus(String str) {
        this.taskStatus = str;
        return this;
    }

    public void setTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
    }

    public DescribeAuditTaskResult withTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
        return this;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String str) {
        this.taskType = str;
    }

    public DescribeAuditTaskResult withTaskType(String str) {
        this.taskType = str;
        return this;
    }

    public void setTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
    }

    public DescribeAuditTaskResult withTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
        return this;
    }

    public Date getTaskStartTime() {
        return this.taskStartTime;
    }

    public void setTaskStartTime(Date date) {
        this.taskStartTime = date;
    }

    public DescribeAuditTaskResult withTaskStartTime(Date date) {
        this.taskStartTime = date;
        return this;
    }

    public TaskStatistics getTaskStatistics() {
        return this.taskStatistics;
    }

    public void setTaskStatistics(TaskStatistics taskStatistics) {
        this.taskStatistics = taskStatistics;
    }

    public DescribeAuditTaskResult withTaskStatistics(TaskStatistics taskStatistics) {
        this.taskStatistics = taskStatistics;
        return this;
    }

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public void setScheduledAuditName(String str) {
        this.scheduledAuditName = str;
    }

    public DescribeAuditTaskResult withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }

    public Map<String, AuditCheckDetails> getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(Map<String, AuditCheckDetails> map) {
        this.auditDetails = map;
    }

    public DescribeAuditTaskResult withAuditDetails(Map<String, AuditCheckDetails> map) {
        this.auditDetails = map;
        return this;
    }

    public DescribeAuditTaskResult addauditDetailsEntry(String str, AuditCheckDetails auditCheckDetails) {
        if (this.auditDetails == null) {
            this.auditDetails = new HashMap();
        }
        if (this.auditDetails.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.auditDetails.put(str, auditCheckDetails);
        return this;
    }

    public DescribeAuditTaskResult clearauditDetailsEntries() {
        this.auditDetails = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskStatus() != null) {
            sb.append("taskStatus: " + getTaskStatus() + ",");
        }
        if (getTaskType() != null) {
            sb.append("taskType: " + getTaskType() + ",");
        }
        if (getTaskStartTime() != null) {
            sb.append("taskStartTime: " + getTaskStartTime() + ",");
        }
        if (getTaskStatistics() != null) {
            sb.append("taskStatistics: " + getTaskStatistics() + ",");
        }
        if (getScheduledAuditName() != null) {
            sb.append("scheduledAuditName: " + getScheduledAuditName() + ",");
        }
        if (getAuditDetails() != null) {
            sb.append("auditDetails: " + getAuditDetails());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getTaskStatus() == null ? 0 : getTaskStatus().hashCode()) + 31) * 31) + (getTaskType() == null ? 0 : getTaskType().hashCode())) * 31) + (getTaskStartTime() == null ? 0 : getTaskStartTime().hashCode())) * 31) + (getTaskStatistics() == null ? 0 : getTaskStatistics().hashCode())) * 31) + (getScheduledAuditName() == null ? 0 : getScheduledAuditName().hashCode())) * 31) + (getAuditDetails() != null ? getAuditDetails().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuditTaskResult)) {
            return false;
        }
        DescribeAuditTaskResult describeAuditTaskResult = (DescribeAuditTaskResult) obj;
        if ((describeAuditTaskResult.getTaskStatus() == null) ^ (getTaskStatus() == null)) {
            return false;
        }
        if (describeAuditTaskResult.getTaskStatus() != null && !describeAuditTaskResult.getTaskStatus().equals(getTaskStatus())) {
            return false;
        }
        if ((describeAuditTaskResult.getTaskType() == null) ^ (getTaskType() == null)) {
            return false;
        }
        if (describeAuditTaskResult.getTaskType() != null && !describeAuditTaskResult.getTaskType().equals(getTaskType())) {
            return false;
        }
        if ((describeAuditTaskResult.getTaskStartTime() == null) ^ (getTaskStartTime() == null)) {
            return false;
        }
        if (describeAuditTaskResult.getTaskStartTime() != null && !describeAuditTaskResult.getTaskStartTime().equals(getTaskStartTime())) {
            return false;
        }
        if ((describeAuditTaskResult.getTaskStatistics() == null) ^ (getTaskStatistics() == null)) {
            return false;
        }
        if (describeAuditTaskResult.getTaskStatistics() != null && !describeAuditTaskResult.getTaskStatistics().equals(getTaskStatistics())) {
            return false;
        }
        if ((describeAuditTaskResult.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        if (describeAuditTaskResult.getScheduledAuditName() != null && !describeAuditTaskResult.getScheduledAuditName().equals(getScheduledAuditName())) {
            return false;
        }
        if ((describeAuditTaskResult.getAuditDetails() == null) ^ (getAuditDetails() == null)) {
            return false;
        }
        return describeAuditTaskResult.getAuditDetails() == null || describeAuditTaskResult.getAuditDetails().equals(getAuditDetails());
    }
}
