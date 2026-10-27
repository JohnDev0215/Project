package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditMitigationActionsTasksRequest extends AmazonWebServiceRequest implements Serializable {
    private String auditTaskId;
    private Date endTime;
    private String findingId;
    private Integer maxResults;
    private String nextToken;
    private Date startTime;
    private String taskStatus;

    public String getAuditTaskId() {
        return this.auditTaskId;
    }

    public void setAuditTaskId(String str) {
        this.auditTaskId = str;
    }

    public ListAuditMitigationActionsTasksRequest withAuditTaskId(String str) {
        this.auditTaskId = str;
        return this;
    }

    public String getFindingId() {
        return this.findingId;
    }

    public void setFindingId(String str) {
        this.findingId = str;
    }

    public ListAuditMitigationActionsTasksRequest withFindingId(String str) {
        this.findingId = str;
        return this;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public ListAuditMitigationActionsTasksRequest withTaskStatus(String str) {
        this.taskStatus = str;
        return this;
    }

    public void setTaskStatus(AuditMitigationActionsTaskStatus auditMitigationActionsTaskStatus) {
        this.taskStatus = auditMitigationActionsTaskStatus.toString();
    }

    public ListAuditMitigationActionsTasksRequest withTaskStatus(AuditMitigationActionsTaskStatus auditMitigationActionsTaskStatus) {
        this.taskStatus = auditMitigationActionsTaskStatus.toString();
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListAuditMitigationActionsTasksRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListAuditMitigationActionsTasksRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public ListAuditMitigationActionsTasksRequest withStartTime(Date date) {
        this.startTime = date;
        return this;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public ListAuditMitigationActionsTasksRequest withEndTime(Date date) {
        this.endTime = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAuditTaskId() != null) {
            sb.append("auditTaskId: " + getAuditTaskId() + ",");
        }
        if (getFindingId() != null) {
            sb.append("findingId: " + getFindingId() + ",");
        }
        if (getTaskStatus() != null) {
            sb.append("taskStatus: " + getTaskStatus() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getStartTime() != null) {
            sb.append("startTime: " + getStartTime() + ",");
        }
        if (getEndTime() != null) {
            sb.append("endTime: " + getEndTime());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getAuditTaskId() == null ? 0 : getAuditTaskId().hashCode()) + 31) * 31) + (getFindingId() == null ? 0 : getFindingId().hashCode())) * 31) + (getTaskStatus() == null ? 0 : getTaskStatus().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getStartTime() == null ? 0 : getStartTime().hashCode())) * 31) + (getEndTime() != null ? getEndTime().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditMitigationActionsTasksRequest)) {
            return false;
        }
        ListAuditMitigationActionsTasksRequest listAuditMitigationActionsTasksRequest = (ListAuditMitigationActionsTasksRequest) obj;
        if ((listAuditMitigationActionsTasksRequest.getAuditTaskId() == null) ^ (getAuditTaskId() == null)) {
            return false;
        }
        if (listAuditMitigationActionsTasksRequest.getAuditTaskId() != null && !listAuditMitigationActionsTasksRequest.getAuditTaskId().equals(getAuditTaskId())) {
            return false;
        }
        if ((listAuditMitigationActionsTasksRequest.getFindingId() == null) ^ (getFindingId() == null)) {
            return false;
        }
        if (listAuditMitigationActionsTasksRequest.getFindingId() != null && !listAuditMitigationActionsTasksRequest.getFindingId().equals(getFindingId())) {
            return false;
        }
        if ((listAuditMitigationActionsTasksRequest.getTaskStatus() == null) ^ (getTaskStatus() == null)) {
            return false;
        }
        if (listAuditMitigationActionsTasksRequest.getTaskStatus() != null && !listAuditMitigationActionsTasksRequest.getTaskStatus().equals(getTaskStatus())) {
            return false;
        }
        if ((listAuditMitigationActionsTasksRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listAuditMitigationActionsTasksRequest.getMaxResults() != null && !listAuditMitigationActionsTasksRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listAuditMitigationActionsTasksRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listAuditMitigationActionsTasksRequest.getNextToken() != null && !listAuditMitigationActionsTasksRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listAuditMitigationActionsTasksRequest.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (listAuditMitigationActionsTasksRequest.getStartTime() != null && !listAuditMitigationActionsTasksRequest.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((listAuditMitigationActionsTasksRequest.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        return listAuditMitigationActionsTasksRequest.getEndTime() == null || listAuditMitigationActionsTasksRequest.getEndTime().equals(getEndTime());
    }
}
