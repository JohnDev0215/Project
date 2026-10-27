package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditFindingsRequest extends AmazonWebServiceRequest implements Serializable {
    private String checkName;
    private Date endTime;
    private Boolean listSuppressedFindings;
    private Integer maxResults;
    private String nextToken;
    private ResourceIdentifier resourceIdentifier;
    private Date startTime;
    private String taskId;

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public ListAuditFindingsRequest withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public String getCheckName() {
        return this.checkName;
    }

    public void setCheckName(String str) {
        this.checkName = str;
    }

    public ListAuditFindingsRequest withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public ListAuditFindingsRequest withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListAuditFindingsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListAuditFindingsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public ListAuditFindingsRequest withStartTime(Date date) {
        this.startTime = date;
        return this;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public ListAuditFindingsRequest withEndTime(Date date) {
        this.endTime = date;
        return this;
    }

    public Boolean isListSuppressedFindings() {
        return this.listSuppressedFindings;
    }

    public Boolean getListSuppressedFindings() {
        return this.listSuppressedFindings;
    }

    public void setListSuppressedFindings(Boolean bool) {
        this.listSuppressedFindings = bool;
    }

    public ListAuditFindingsRequest withListSuppressedFindings(Boolean bool) {
        this.listSuppressedFindings = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskId() != null) {
            sb.append("taskId: " + getTaskId() + ",");
        }
        if (getCheckName() != null) {
            sb.append("checkName: " + getCheckName() + ",");
        }
        if (getResourceIdentifier() != null) {
            sb.append("resourceIdentifier: " + getResourceIdentifier() + ",");
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
            sb.append("endTime: " + getEndTime() + ",");
        }
        if (getListSuppressedFindings() != null) {
            sb.append("listSuppressedFindings: " + getListSuppressedFindings());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getCheckName() == null ? 0 : getCheckName().hashCode())) * 31) + (getResourceIdentifier() == null ? 0 : getResourceIdentifier().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getStartTime() == null ? 0 : getStartTime().hashCode())) * 31) + (getEndTime() == null ? 0 : getEndTime().hashCode())) * 31) + (getListSuppressedFindings() != null ? getListSuppressedFindings().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditFindingsRequest)) {
            return false;
        }
        ListAuditFindingsRequest listAuditFindingsRequest = (ListAuditFindingsRequest) obj;
        if ((listAuditFindingsRequest.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getTaskId() != null && !listAuditFindingsRequest.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((listAuditFindingsRequest.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getCheckName() != null && !listAuditFindingsRequest.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((listAuditFindingsRequest.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getResourceIdentifier() != null && !listAuditFindingsRequest.getResourceIdentifier().equals(getResourceIdentifier())) {
            return false;
        }
        if ((listAuditFindingsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getMaxResults() != null && !listAuditFindingsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listAuditFindingsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getNextToken() != null && !listAuditFindingsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listAuditFindingsRequest.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getStartTime() != null && !listAuditFindingsRequest.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((listAuditFindingsRequest.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getEndTime() != null && !listAuditFindingsRequest.getEndTime().equals(getEndTime())) {
            return false;
        }
        if ((listAuditFindingsRequest.getListSuppressedFindings() == null) ^ (getListSuppressedFindings() == null)) {
            return false;
        }
        return listAuditFindingsRequest.getListSuppressedFindings() == null || listAuditFindingsRequest.getListSuppressedFindings().equals(getListSuppressedFindings());
    }
}
