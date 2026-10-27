package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class AuditMitigationActionsTaskMetadata implements Serializable {
    private Date startTime;
    private String taskId;
    private String taskStatus;

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public AuditMitigationActionsTaskMetadata withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public AuditMitigationActionsTaskMetadata withStartTime(Date date) {
        this.startTime = date;
        return this;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public AuditMitigationActionsTaskMetadata withTaskStatus(String str) {
        this.taskStatus = str;
        return this;
    }

    public void setTaskStatus(AuditMitigationActionsTaskStatus auditMitigationActionsTaskStatus) {
        this.taskStatus = auditMitigationActionsTaskStatus.toString();
    }

    public AuditMitigationActionsTaskMetadata withTaskStatus(AuditMitigationActionsTaskStatus auditMitigationActionsTaskStatus) {
        this.taskStatus = auditMitigationActionsTaskStatus.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskId() != null) {
            sb.append("taskId: " + getTaskId() + ",");
        }
        if (getStartTime() != null) {
            sb.append("startTime: " + getStartTime() + ",");
        }
        if (getTaskStatus() != null) {
            sb.append("taskStatus: " + getTaskStatus());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getStartTime() == null ? 0 : getStartTime().hashCode())) * 31) + (getTaskStatus() != null ? getTaskStatus().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditMitigationActionsTaskMetadata)) {
            return false;
        }
        AuditMitigationActionsTaskMetadata auditMitigationActionsTaskMetadata = (AuditMitigationActionsTaskMetadata) obj;
        if ((auditMitigationActionsTaskMetadata.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (auditMitigationActionsTaskMetadata.getTaskId() != null && !auditMitigationActionsTaskMetadata.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((auditMitigationActionsTaskMetadata.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (auditMitigationActionsTaskMetadata.getStartTime() != null && !auditMitigationActionsTaskMetadata.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((auditMitigationActionsTaskMetadata.getTaskStatus() == null) ^ (getTaskStatus() == null)) {
            return false;
        }
        return auditMitigationActionsTaskMetadata.getTaskStatus() == null || auditMitigationActionsTaskMetadata.getTaskStatus().equals(getTaskStatus());
    }
}
