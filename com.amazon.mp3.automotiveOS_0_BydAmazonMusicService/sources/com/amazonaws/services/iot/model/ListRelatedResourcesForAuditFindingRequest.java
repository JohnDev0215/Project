package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListRelatedResourcesForAuditFindingRequest extends AmazonWebServiceRequest implements Serializable {
    private String findingId;
    private Integer maxResults;
    private String nextToken;

    public String getFindingId() {
        return this.findingId;
    }

    public void setFindingId(String str) {
        this.findingId = str;
    }

    public ListRelatedResourcesForAuditFindingRequest withFindingId(String str) {
        this.findingId = str;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListRelatedResourcesForAuditFindingRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListRelatedResourcesForAuditFindingRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFindingId() != null) {
            sb.append("findingId: " + getFindingId() + ",");
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
        return (((((getFindingId() == null ? 0 : getFindingId().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListRelatedResourcesForAuditFindingRequest)) {
            return false;
        }
        ListRelatedResourcesForAuditFindingRequest listRelatedResourcesForAuditFindingRequest = (ListRelatedResourcesForAuditFindingRequest) obj;
        if ((listRelatedResourcesForAuditFindingRequest.getFindingId() == null) ^ (getFindingId() == null)) {
            return false;
        }
        if (listRelatedResourcesForAuditFindingRequest.getFindingId() != null && !listRelatedResourcesForAuditFindingRequest.getFindingId().equals(getFindingId())) {
            return false;
        }
        if ((listRelatedResourcesForAuditFindingRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listRelatedResourcesForAuditFindingRequest.getNextToken() != null && !listRelatedResourcesForAuditFindingRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listRelatedResourcesForAuditFindingRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listRelatedResourcesForAuditFindingRequest.getMaxResults() == null || listRelatedResourcesForAuditFindingRequest.getMaxResults().equals(getMaxResults());
    }
}
