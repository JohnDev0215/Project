package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class StartAuditMitigationActionsTaskRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, List<String>> auditCheckToActionsMapping;
    private String clientRequestToken;
    private AuditMitigationActionsTaskTarget target;
    private String taskId;

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public StartAuditMitigationActionsTaskRequest withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public AuditMitigationActionsTaskTarget getTarget() {
        return this.target;
    }

    public void setTarget(AuditMitigationActionsTaskTarget auditMitigationActionsTaskTarget) {
        this.target = auditMitigationActionsTaskTarget;
    }

    public StartAuditMitigationActionsTaskRequest withTarget(AuditMitigationActionsTaskTarget auditMitigationActionsTaskTarget) {
        this.target = auditMitigationActionsTaskTarget;
        return this;
    }

    public Map<String, List<String>> getAuditCheckToActionsMapping() {
        return this.auditCheckToActionsMapping;
    }

    public void setAuditCheckToActionsMapping(Map<String, List<String>> map) {
        this.auditCheckToActionsMapping = map;
    }

    public StartAuditMitigationActionsTaskRequest withAuditCheckToActionsMapping(Map<String, List<String>> map) {
        this.auditCheckToActionsMapping = map;
        return this;
    }

    public StartAuditMitigationActionsTaskRequest addauditCheckToActionsMappingEntry(String str, List<String> list) {
        if (this.auditCheckToActionsMapping == null) {
            this.auditCheckToActionsMapping = new HashMap();
        }
        if (this.auditCheckToActionsMapping.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.auditCheckToActionsMapping.put(str, list);
        return this;
    }

    public StartAuditMitigationActionsTaskRequest clearauditCheckToActionsMappingEntries() {
        this.auditCheckToActionsMapping = null;
        return this;
    }

    public String getClientRequestToken() {
        return this.clientRequestToken;
    }

    public void setClientRequestToken(String str) {
        this.clientRequestToken = str;
    }

    public StartAuditMitigationActionsTaskRequest withClientRequestToken(String str) {
        this.clientRequestToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskId() != null) {
            sb.append("taskId: " + getTaskId() + ",");
        }
        if (getTarget() != null) {
            sb.append("target: " + getTarget() + ",");
        }
        if (getAuditCheckToActionsMapping() != null) {
            sb.append("auditCheckToActionsMapping: " + getAuditCheckToActionsMapping() + ",");
        }
        if (getClientRequestToken() != null) {
            sb.append("clientRequestToken: " + getClientRequestToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getTarget() == null ? 0 : getTarget().hashCode())) * 31) + (getAuditCheckToActionsMapping() == null ? 0 : getAuditCheckToActionsMapping().hashCode())) * 31) + (getClientRequestToken() != null ? getClientRequestToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StartAuditMitigationActionsTaskRequest)) {
            return false;
        }
        StartAuditMitigationActionsTaskRequest startAuditMitigationActionsTaskRequest = (StartAuditMitigationActionsTaskRequest) obj;
        if ((startAuditMitigationActionsTaskRequest.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (startAuditMitigationActionsTaskRequest.getTaskId() != null && !startAuditMitigationActionsTaskRequest.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((startAuditMitigationActionsTaskRequest.getTarget() == null) ^ (getTarget() == null)) {
            return false;
        }
        if (startAuditMitigationActionsTaskRequest.getTarget() != null && !startAuditMitigationActionsTaskRequest.getTarget().equals(getTarget())) {
            return false;
        }
        if ((startAuditMitigationActionsTaskRequest.getAuditCheckToActionsMapping() == null) ^ (getAuditCheckToActionsMapping() == null)) {
            return false;
        }
        if (startAuditMitigationActionsTaskRequest.getAuditCheckToActionsMapping() != null && !startAuditMitigationActionsTaskRequest.getAuditCheckToActionsMapping().equals(getAuditCheckToActionsMapping())) {
            return false;
        }
        if ((startAuditMitigationActionsTaskRequest.getClientRequestToken() == null) ^ (getClientRequestToken() == null)) {
            return false;
        }
        return startAuditMitigationActionsTaskRequest.getClientRequestToken() == null || startAuditMitigationActionsTaskRequest.getClientRequestToken().equals(getClientRequestToken());
    }
}
