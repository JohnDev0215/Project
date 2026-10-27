package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditTasksRequest extends AmazonWebServiceRequest implements Serializable {
    private Date endTime;
    private Integer maxResults;
    private String nextToken;
    private Date startTime;
    private String taskStatus;
    private String taskType;

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public ListAuditTasksRequest withStartTime(Date date) {
        this.startTime = date;
        return this;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public ListAuditTasksRequest withEndTime(Date date) {
        this.endTime = date;
        return this;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String str) {
        this.taskType = str;
    }

    public ListAuditTasksRequest withTaskType(String str) {
        this.taskType = str;
        return this;
    }

    public void setTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
    }

    public ListAuditTasksRequest withTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
        return this;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public ListAuditTasksRequest withTaskStatus(String str) {
        this.taskStatus = str;
        return this;
    }

    public void setTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
    }

    public ListAuditTasksRequest withTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListAuditTasksRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListAuditTasksRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStartTime() != null) {
            sb.append("startTime: " + getStartTime() + ",");
        }
        if (getEndTime() != null) {
            sb.append("endTime: " + getEndTime() + ",");
        }
        if (getTaskType() != null) {
            sb.append("taskType: " + getTaskType() + ",");
        }
        if (getTaskStatus() != null) {
            sb.append("taskStatus: " + getTaskStatus() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getStartTime() == null ? 0 : getStartTime().hashCode()) + 31) * 31) + (getEndTime() == null ? 0 : getEndTime().hashCode())) * 31) + (getTaskType() == null ? 0 : getTaskType().hashCode())) * 31) + (getTaskStatus() == null ? 0 : getTaskStatus().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditTasksRequest)) {
            return false;
        }
        ListAuditTasksRequest listAuditTasksRequest = (ListAuditTasksRequest) obj;
        if ((listAuditTasksRequest.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (listAuditTasksRequest.getStartTime() != null && !listAuditTasksRequest.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((listAuditTasksRequest.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        if (listAuditTasksRequest.getEndTime() != null && !listAuditTasksRequest.getEndTime().equals(getEndTime())) {
            return false;
        }
        if ((listAuditTasksRequest.getTaskType() == null) ^ (getTaskType() == null)) {
            return false;
        }
        if (listAuditTasksRequest.getTaskType() != null && !listAuditTasksRequest.getTaskType().equals(getTaskType())) {
            return false;
        }
        if ((listAuditTasksRequest.getTaskStatus() == null) ^ (getTaskStatus() == null)) {
            return false;
        }
        if (listAuditTasksRequest.getTaskStatus() != null && !listAuditTasksRequest.getTaskStatus().equals(getTaskStatus())) {
            return false;
        }
        if ((listAuditTasksRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listAuditTasksRequest.getNextToken() != null && !listAuditTasksRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listAuditTasksRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listAuditTasksRequest.getMaxResults() == null || listAuditTasksRequest.getMaxResults().equals(getMaxResults());
    }
}
