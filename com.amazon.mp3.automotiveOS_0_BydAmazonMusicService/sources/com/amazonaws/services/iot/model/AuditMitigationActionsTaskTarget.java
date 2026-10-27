package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class AuditMitigationActionsTaskTarget implements Serializable {
    private Map<String, List<String>> auditCheckToReasonCodeFilter;
    private String auditTaskId;
    private List<String> findingIds;

    public String getAuditTaskId() {
        return this.auditTaskId;
    }

    public void setAuditTaskId(String str) {
        this.auditTaskId = str;
    }

    public AuditMitigationActionsTaskTarget withAuditTaskId(String str) {
        this.auditTaskId = str;
        return this;
    }

    public List<String> getFindingIds() {
        return this.findingIds;
    }

    public void setFindingIds(Collection<String> collection) {
        if (collection == null) {
            this.findingIds = null;
        } else {
            this.findingIds = new ArrayList(collection);
        }
    }

    public AuditMitigationActionsTaskTarget withFindingIds(String... strArr) {
        if (getFindingIds() == null) {
            this.findingIds = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.findingIds.add(str);
        }
        return this;
    }

    public AuditMitigationActionsTaskTarget withFindingIds(Collection<String> collection) {
        setFindingIds(collection);
        return this;
    }

    public Map<String, List<String>> getAuditCheckToReasonCodeFilter() {
        return this.auditCheckToReasonCodeFilter;
    }

    public void setAuditCheckToReasonCodeFilter(Map<String, List<String>> map) {
        this.auditCheckToReasonCodeFilter = map;
    }

    public AuditMitigationActionsTaskTarget withAuditCheckToReasonCodeFilter(Map<String, List<String>> map) {
        this.auditCheckToReasonCodeFilter = map;
        return this;
    }

    public AuditMitigationActionsTaskTarget addauditCheckToReasonCodeFilterEntry(String str, List<String> list) {
        if (this.auditCheckToReasonCodeFilter == null) {
            this.auditCheckToReasonCodeFilter = new HashMap();
        }
        if (this.auditCheckToReasonCodeFilter.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.auditCheckToReasonCodeFilter.put(str, list);
        return this;
    }

    public AuditMitigationActionsTaskTarget clearauditCheckToReasonCodeFilterEntries() {
        this.auditCheckToReasonCodeFilter = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAuditTaskId() != null) {
            sb.append("auditTaskId: " + getAuditTaskId() + ",");
        }
        if (getFindingIds() != null) {
            sb.append("findingIds: " + getFindingIds() + ",");
        }
        if (getAuditCheckToReasonCodeFilter() != null) {
            sb.append("auditCheckToReasonCodeFilter: " + getAuditCheckToReasonCodeFilter());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getAuditTaskId() == null ? 0 : getAuditTaskId().hashCode()) + 31) * 31) + (getFindingIds() == null ? 0 : getFindingIds().hashCode())) * 31) + (getAuditCheckToReasonCodeFilter() != null ? getAuditCheckToReasonCodeFilter().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditMitigationActionsTaskTarget)) {
            return false;
        }
        AuditMitigationActionsTaskTarget auditMitigationActionsTaskTarget = (AuditMitigationActionsTaskTarget) obj;
        if ((auditMitigationActionsTaskTarget.getAuditTaskId() == null) ^ (getAuditTaskId() == null)) {
            return false;
        }
        if (auditMitigationActionsTaskTarget.getAuditTaskId() != null && !auditMitigationActionsTaskTarget.getAuditTaskId().equals(getAuditTaskId())) {
            return false;
        }
        if ((auditMitigationActionsTaskTarget.getFindingIds() == null) ^ (getFindingIds() == null)) {
            return false;
        }
        if (auditMitigationActionsTaskTarget.getFindingIds() != null && !auditMitigationActionsTaskTarget.getFindingIds().equals(getFindingIds())) {
            return false;
        }
        if ((auditMitigationActionsTaskTarget.getAuditCheckToReasonCodeFilter() == null) ^ (getAuditCheckToReasonCodeFilter() == null)) {
            return false;
        }
        return auditMitigationActionsTaskTarget.getAuditCheckToReasonCodeFilter() == null || auditMitigationActionsTaskTarget.getAuditCheckToReasonCodeFilter().equals(getAuditCheckToReasonCodeFilter());
    }
}
