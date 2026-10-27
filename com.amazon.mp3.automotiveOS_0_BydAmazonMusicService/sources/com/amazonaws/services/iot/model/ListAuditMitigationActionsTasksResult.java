package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditMitigationActionsTasksResult implements Serializable {
    private String nextToken;
    private List<AuditMitigationActionsTaskMetadata> tasks;

    public List<AuditMitigationActionsTaskMetadata> getTasks() {
        return this.tasks;
    }

    public void setTasks(Collection<AuditMitigationActionsTaskMetadata> collection) {
        if (collection == null) {
            this.tasks = null;
        } else {
            this.tasks = new ArrayList(collection);
        }
    }

    public ListAuditMitigationActionsTasksResult withTasks(AuditMitigationActionsTaskMetadata... auditMitigationActionsTaskMetadataArr) {
        if (getTasks() == null) {
            this.tasks = new ArrayList(auditMitigationActionsTaskMetadataArr.length);
        }
        for (AuditMitigationActionsTaskMetadata auditMitigationActionsTaskMetadata : auditMitigationActionsTaskMetadataArr) {
            this.tasks.add(auditMitigationActionsTaskMetadata);
        }
        return this;
    }

    public ListAuditMitigationActionsTasksResult withTasks(Collection<AuditMitigationActionsTaskMetadata> collection) {
        setTasks(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListAuditMitigationActionsTasksResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTasks() != null) {
            sb.append("tasks: " + getTasks() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTasks() == null ? 0 : getTasks().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditMitigationActionsTasksResult)) {
            return false;
        }
        ListAuditMitigationActionsTasksResult listAuditMitigationActionsTasksResult = (ListAuditMitigationActionsTasksResult) obj;
        if ((listAuditMitigationActionsTasksResult.getTasks() == null) ^ (getTasks() == null)) {
            return false;
        }
        if (listAuditMitigationActionsTasksResult.getTasks() != null && !listAuditMitigationActionsTasksResult.getTasks().equals(getTasks())) {
            return false;
        }
        if ((listAuditMitigationActionsTasksResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listAuditMitigationActionsTasksResult.getNextToken() == null || listAuditMitigationActionsTasksResult.getNextToken().equals(getNextToken());
    }
}
