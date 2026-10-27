package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AuditTaskMetadata implements Serializable {
    private String taskId;
    private String taskStatus;
    private String taskType;

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public AuditTaskMetadata withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public AuditTaskMetadata withTaskStatus(String str) {
        this.taskStatus = str;
        return this;
    }

    public void setTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
    }

    public AuditTaskMetadata withTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
        return this;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String str) {
        this.taskType = str;
    }

    public AuditTaskMetadata withTaskType(String str) {
        this.taskType = str;
        return this;
    }

    public void setTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
    }

    public AuditTaskMetadata withTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskId() != null) {
            sb.append("taskId: " + getTaskId() + ",");
        }
        if (getTaskStatus() != null) {
            sb.append("taskStatus: " + getTaskStatus() + ",");
        }
        if (getTaskType() != null) {
            sb.append("taskType: " + getTaskType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getTaskStatus() == null ? 0 : getTaskStatus().hashCode())) * 31) + (getTaskType() != null ? getTaskType().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditTaskMetadata)) {
            return false;
        }
        AuditTaskMetadata auditTaskMetadata = (AuditTaskMetadata) obj;
        if ((auditTaskMetadata.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (auditTaskMetadata.getTaskId() != null && !auditTaskMetadata.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((auditTaskMetadata.getTaskStatus() == null) ^ (getTaskStatus() == null)) {
            return false;
        }
        if (auditTaskMetadata.getTaskStatus() != null && !auditTaskMetadata.getTaskStatus().equals(getTaskStatus())) {
            return false;
        }
        if ((auditTaskMetadata.getTaskType() == null) ^ (getTaskType() == null)) {
            return false;
        }
        return auditTaskMetadata.getTaskType() == null || auditTaskMetadata.getTaskType().equals(getTaskType());
    }
}
