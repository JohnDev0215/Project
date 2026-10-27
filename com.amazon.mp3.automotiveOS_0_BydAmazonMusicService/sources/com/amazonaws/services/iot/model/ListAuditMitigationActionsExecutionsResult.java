package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditMitigationActionsExecutionsResult implements Serializable {
    private List<AuditMitigationActionExecutionMetadata> actionsExecutions;
    private String nextToken;

    public List<AuditMitigationActionExecutionMetadata> getActionsExecutions() {
        return this.actionsExecutions;
    }

    public void setActionsExecutions(Collection<AuditMitigationActionExecutionMetadata> collection) {
        if (collection == null) {
            this.actionsExecutions = null;
        } else {
            this.actionsExecutions = new ArrayList(collection);
        }
    }

    public ListAuditMitigationActionsExecutionsResult withActionsExecutions(AuditMitigationActionExecutionMetadata... auditMitigationActionExecutionMetadataArr) {
        if (getActionsExecutions() == null) {
            this.actionsExecutions = new ArrayList(auditMitigationActionExecutionMetadataArr.length);
        }
        for (AuditMitigationActionExecutionMetadata auditMitigationActionExecutionMetadata : auditMitigationActionExecutionMetadataArr) {
            this.actionsExecutions.add(auditMitigationActionExecutionMetadata);
        }
        return this;
    }

    public ListAuditMitigationActionsExecutionsResult withActionsExecutions(Collection<AuditMitigationActionExecutionMetadata> collection) {
        setActionsExecutions(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListAuditMitigationActionsExecutionsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionsExecutions() != null) {
            sb.append("actionsExecutions: " + getActionsExecutions() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getActionsExecutions() == null ? 0 : getActionsExecutions().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditMitigationActionsExecutionsResult)) {
            return false;
        }
        ListAuditMitigationActionsExecutionsResult listAuditMitigationActionsExecutionsResult = (ListAuditMitigationActionsExecutionsResult) obj;
        if ((listAuditMitigationActionsExecutionsResult.getActionsExecutions() == null) ^ (getActionsExecutions() == null)) {
            return false;
        }
        if (listAuditMitigationActionsExecutionsResult.getActionsExecutions() != null && !listAuditMitigationActionsExecutionsResult.getActionsExecutions().equals(getActionsExecutions())) {
            return false;
        }
        if ((listAuditMitigationActionsExecutionsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listAuditMitigationActionsExecutionsResult.getNextToken() == null || listAuditMitigationActionsExecutionsResult.getNextToken().equals(getNextToken());
    }
}
