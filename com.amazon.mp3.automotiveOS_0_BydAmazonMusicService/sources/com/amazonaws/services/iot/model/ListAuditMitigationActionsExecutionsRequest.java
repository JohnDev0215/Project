package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditMitigationActionsExecutionsRequest extends AmazonWebServiceRequest implements Serializable {
    private String actionStatus;
    private String findingId;
    private Integer maxResults;
    private String nextToken;
    private String taskId;

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public ListAuditMitigationActionsExecutionsRequest withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public String getActionStatus() {
        return this.actionStatus;
    }

    public void setActionStatus(String str) {
        this.actionStatus = str;
    }

    public ListAuditMitigationActionsExecutionsRequest withActionStatus(String str) {
        this.actionStatus = str;
        return this;
    }

    public void setActionStatus(AuditMitigationActionsExecutionStatus auditMitigationActionsExecutionStatus) {
        this.actionStatus = auditMitigationActionsExecutionStatus.toString();
    }

    public ListAuditMitigationActionsExecutionsRequest withActionStatus(AuditMitigationActionsExecutionStatus auditMitigationActionsExecutionStatus) {
        this.actionStatus = auditMitigationActionsExecutionStatus.toString();
        return this;
    }

    public String getFindingId() {
        return this.findingId;
    }

    public void setFindingId(String str) {
        this.findingId = str;
    }

    public ListAuditMitigationActionsExecutionsRequest withFindingId(String str) {
        this.findingId = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListAuditMitigationActionsExecutionsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListAuditMitigationActionsExecutionsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskId() != null) {
            sb.append("taskId: " + getTaskId() + ",");
        }
        if (getActionStatus() != null) {
            sb.append("actionStatus: " + getActionStatus() + ",");
        }
        if (getFindingId() != null) {
            sb.append("findingId: " + getFindingId() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getActionStatus() == null ? 0 : getActionStatus().hashCode())) * 31) + (getFindingId() == null ? 0 : getFindingId().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditMitigationActionsExecutionsRequest)) {
            return false;
        }
        ListAuditMitigationActionsExecutionsRequest listAuditMitigationActionsExecutionsRequest = (ListAuditMitigationActionsExecutionsRequest) obj;
        if ((listAuditMitigationActionsExecutionsRequest.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (listAuditMitigationActionsExecutionsRequest.getTaskId() != null && !listAuditMitigationActionsExecutionsRequest.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((listAuditMitigationActionsExecutionsRequest.getActionStatus() == null) ^ (getActionStatus() == null)) {
            return false;
        }
        if (listAuditMitigationActionsExecutionsRequest.getActionStatus() != null && !listAuditMitigationActionsExecutionsRequest.getActionStatus().equals(getActionStatus())) {
            return false;
        }
        if ((listAuditMitigationActionsExecutionsRequest.getFindingId() == null) ^ (getFindingId() == null)) {
            return false;
        }
        if (listAuditMitigationActionsExecutionsRequest.getFindingId() != null && !listAuditMitigationActionsExecutionsRequest.getFindingId().equals(getFindingId())) {
            return false;
        }
        if ((listAuditMitigationActionsExecutionsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listAuditMitigationActionsExecutionsRequest.getMaxResults() != null && !listAuditMitigationActionsExecutionsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listAuditMitigationActionsExecutionsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listAuditMitigationActionsExecutionsRequest.getNextToken() == null || listAuditMitigationActionsExecutionsRequest.getNextToken().equals(getNextToken());
    }
}
