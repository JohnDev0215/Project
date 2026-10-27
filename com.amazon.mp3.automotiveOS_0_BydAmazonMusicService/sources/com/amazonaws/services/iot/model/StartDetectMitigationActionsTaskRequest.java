package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class StartDetectMitigationActionsTaskRequest extends AmazonWebServiceRequest implements Serializable {
    private List<String> actions;
    private String clientRequestToken;
    private Boolean includeOnlyActiveViolations;
    private Boolean includeSuppressedAlerts;
    private DetectMitigationActionsTaskTarget target;
    private String taskId;
    private ViolationEventOccurrenceRange violationEventOccurrenceRange;

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public StartDetectMitigationActionsTaskRequest withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public DetectMitigationActionsTaskTarget getTarget() {
        return this.target;
    }

    public void setTarget(DetectMitigationActionsTaskTarget detectMitigationActionsTaskTarget) {
        this.target = detectMitigationActionsTaskTarget;
    }

    public StartDetectMitigationActionsTaskRequest withTarget(DetectMitigationActionsTaskTarget detectMitigationActionsTaskTarget) {
        this.target = detectMitigationActionsTaskTarget;
        return this;
    }

    public List<String> getActions() {
        return this.actions;
    }

    public void setActions(Collection<String> collection) {
        if (collection == null) {
            this.actions = null;
        } else {
            this.actions = new ArrayList(collection);
        }
    }

    public StartDetectMitigationActionsTaskRequest withActions(String... strArr) {
        if (getActions() == null) {
            this.actions = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.actions.add(str);
        }
        return this;
    }

    public StartDetectMitigationActionsTaskRequest withActions(Collection<String> collection) {
        setActions(collection);
        return this;
    }

    public ViolationEventOccurrenceRange getViolationEventOccurrenceRange() {
        return this.violationEventOccurrenceRange;
    }

    public void setViolationEventOccurrenceRange(ViolationEventOccurrenceRange violationEventOccurrenceRange) {
        this.violationEventOccurrenceRange = violationEventOccurrenceRange;
    }

    public StartDetectMitigationActionsTaskRequest withViolationEventOccurrenceRange(ViolationEventOccurrenceRange violationEventOccurrenceRange) {
        this.violationEventOccurrenceRange = violationEventOccurrenceRange;
        return this;
    }

    public Boolean isIncludeOnlyActiveViolations() {
        return this.includeOnlyActiveViolations;
    }

    public Boolean getIncludeOnlyActiveViolations() {
        return this.includeOnlyActiveViolations;
    }

    public void setIncludeOnlyActiveViolations(Boolean bool) {
        this.includeOnlyActiveViolations = bool;
    }

    public StartDetectMitigationActionsTaskRequest withIncludeOnlyActiveViolations(Boolean bool) {
        this.includeOnlyActiveViolations = bool;
        return this;
    }

    public Boolean isIncludeSuppressedAlerts() {
        return this.includeSuppressedAlerts;
    }

    public Boolean getIncludeSuppressedAlerts() {
        return this.includeSuppressedAlerts;
    }

    public void setIncludeSuppressedAlerts(Boolean bool) {
        this.includeSuppressedAlerts = bool;
    }

    public StartDetectMitigationActionsTaskRequest withIncludeSuppressedAlerts(Boolean bool) {
        this.includeSuppressedAlerts = bool;
        return this;
    }

    public String getClientRequestToken() {
        return this.clientRequestToken;
    }

    public void setClientRequestToken(String str) {
        this.clientRequestToken = str;
    }

    public StartDetectMitigationActionsTaskRequest withClientRequestToken(String str) {
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
        if (getActions() != null) {
            sb.append("actions: " + getActions() + ",");
        }
        if (getViolationEventOccurrenceRange() != null) {
            sb.append("violationEventOccurrenceRange: " + getViolationEventOccurrenceRange() + ",");
        }
        if (getIncludeOnlyActiveViolations() != null) {
            sb.append("includeOnlyActiveViolations: " + getIncludeOnlyActiveViolations() + ",");
        }
        if (getIncludeSuppressedAlerts() != null) {
            sb.append("includeSuppressedAlerts: " + getIncludeSuppressedAlerts() + ",");
        }
        if (getClientRequestToken() != null) {
            sb.append("clientRequestToken: " + getClientRequestToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getTarget() == null ? 0 : getTarget().hashCode())) * 31) + (getActions() == null ? 0 : getActions().hashCode())) * 31) + (getViolationEventOccurrenceRange() == null ? 0 : getViolationEventOccurrenceRange().hashCode())) * 31) + (getIncludeOnlyActiveViolations() == null ? 0 : getIncludeOnlyActiveViolations().hashCode())) * 31) + (getIncludeSuppressedAlerts() == null ? 0 : getIncludeSuppressedAlerts().hashCode())) * 31) + (getClientRequestToken() != null ? getClientRequestToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StartDetectMitigationActionsTaskRequest)) {
            return false;
        }
        StartDetectMitigationActionsTaskRequest startDetectMitigationActionsTaskRequest = (StartDetectMitigationActionsTaskRequest) obj;
        if ((startDetectMitigationActionsTaskRequest.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (startDetectMitigationActionsTaskRequest.getTaskId() != null && !startDetectMitigationActionsTaskRequest.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((startDetectMitigationActionsTaskRequest.getTarget() == null) ^ (getTarget() == null)) {
            return false;
        }
        if (startDetectMitigationActionsTaskRequest.getTarget() != null && !startDetectMitigationActionsTaskRequest.getTarget().equals(getTarget())) {
            return false;
        }
        if ((startDetectMitigationActionsTaskRequest.getActions() == null) ^ (getActions() == null)) {
            return false;
        }
        if (startDetectMitigationActionsTaskRequest.getActions() != null && !startDetectMitigationActionsTaskRequest.getActions().equals(getActions())) {
            return false;
        }
        if ((startDetectMitigationActionsTaskRequest.getViolationEventOccurrenceRange() == null) ^ (getViolationEventOccurrenceRange() == null)) {
            return false;
        }
        if (startDetectMitigationActionsTaskRequest.getViolationEventOccurrenceRange() != null && !startDetectMitigationActionsTaskRequest.getViolationEventOccurrenceRange().equals(getViolationEventOccurrenceRange())) {
            return false;
        }
        if ((startDetectMitigationActionsTaskRequest.getIncludeOnlyActiveViolations() == null) ^ (getIncludeOnlyActiveViolations() == null)) {
            return false;
        }
        if (startDetectMitigationActionsTaskRequest.getIncludeOnlyActiveViolations() != null && !startDetectMitigationActionsTaskRequest.getIncludeOnlyActiveViolations().equals(getIncludeOnlyActiveViolations())) {
            return false;
        }
        if ((startDetectMitigationActionsTaskRequest.getIncludeSuppressedAlerts() == null) ^ (getIncludeSuppressedAlerts() == null)) {
            return false;
        }
        if (startDetectMitigationActionsTaskRequest.getIncludeSuppressedAlerts() != null && !startDetectMitigationActionsTaskRequest.getIncludeSuppressedAlerts().equals(getIncludeSuppressedAlerts())) {
            return false;
        }
        if ((startDetectMitigationActionsTaskRequest.getClientRequestToken() == null) ^ (getClientRequestToken() == null)) {
            return false;
        }
        return startDetectMitigationActionsTaskRequest.getClientRequestToken() == null || startDetectMitigationActionsTaskRequest.getClientRequestToken().equals(getClientRequestToken());
    }
}
