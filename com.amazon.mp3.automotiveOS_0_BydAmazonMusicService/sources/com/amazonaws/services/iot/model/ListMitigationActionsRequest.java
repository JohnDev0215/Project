package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListMitigationActionsRequest extends AmazonWebServiceRequest implements Serializable {
    private String actionType;
    private Integer maxResults;
    private String nextToken;

    public String getActionType() {
        return this.actionType;
    }

    public void setActionType(String str) {
        this.actionType = str;
    }

    public ListMitigationActionsRequest withActionType(String str) {
        this.actionType = str;
        return this;
    }

    public void setActionType(MitigationActionType mitigationActionType) {
        this.actionType = mitigationActionType.toString();
    }

    public ListMitigationActionsRequest withActionType(MitigationActionType mitigationActionType) {
        this.actionType = mitigationActionType.toString();
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListMitigationActionsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListMitigationActionsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionType() != null) {
            sb.append("actionType: " + getActionType() + ",");
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
        return (((((getActionType() == null ? 0 : getActionType().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListMitigationActionsRequest)) {
            return false;
        }
        ListMitigationActionsRequest listMitigationActionsRequest = (ListMitigationActionsRequest) obj;
        if ((listMitigationActionsRequest.getActionType() == null) ^ (getActionType() == null)) {
            return false;
        }
        if (listMitigationActionsRequest.getActionType() != null && !listMitigationActionsRequest.getActionType().equals(getActionType())) {
            return false;
        }
        if ((listMitigationActionsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listMitigationActionsRequest.getMaxResults() != null && !listMitigationActionsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listMitigationActionsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listMitigationActionsRequest.getNextToken() == null || listMitigationActionsRequest.getNextToken().equals(getNextToken());
    }
}
