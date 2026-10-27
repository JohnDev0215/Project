package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListDetectMitigationActionsExecutionsResult implements Serializable {
    private List<DetectMitigationActionExecution> actionsExecutions;
    private String nextToken;

    public List<DetectMitigationActionExecution> getActionsExecutions() {
        return this.actionsExecutions;
    }

    public void setActionsExecutions(Collection<DetectMitigationActionExecution> collection) {
        if (collection == null) {
            this.actionsExecutions = null;
        } else {
            this.actionsExecutions = new ArrayList(collection);
        }
    }

    public ListDetectMitigationActionsExecutionsResult withActionsExecutions(DetectMitigationActionExecution... detectMitigationActionExecutionArr) {
        if (getActionsExecutions() == null) {
            this.actionsExecutions = new ArrayList(detectMitigationActionExecutionArr.length);
        }
        for (DetectMitigationActionExecution detectMitigationActionExecution : detectMitigationActionExecutionArr) {
            this.actionsExecutions.add(detectMitigationActionExecution);
        }
        return this;
    }

    public ListDetectMitigationActionsExecutionsResult withActionsExecutions(Collection<DetectMitigationActionExecution> collection) {
        setActionsExecutions(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListDetectMitigationActionsExecutionsResult withNextToken(String str) {
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
        if (obj == null || !(obj instanceof ListDetectMitigationActionsExecutionsResult)) {
            return false;
        }
        ListDetectMitigationActionsExecutionsResult listDetectMitigationActionsExecutionsResult = (ListDetectMitigationActionsExecutionsResult) obj;
        if ((listDetectMitigationActionsExecutionsResult.getActionsExecutions() == null) ^ (getActionsExecutions() == null)) {
            return false;
        }
        if (listDetectMitigationActionsExecutionsResult.getActionsExecutions() != null && !listDetectMitigationActionsExecutionsResult.getActionsExecutions().equals(getActionsExecutions())) {
            return false;
        }
        if ((listDetectMitigationActionsExecutionsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listDetectMitigationActionsExecutionsResult.getNextToken() == null || listDetectMitigationActionsExecutionsResult.getNextToken().equals(getNextToken());
    }
}
