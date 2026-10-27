package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListDetectMitigationActionsTasksResult implements Serializable {
    private String nextToken;
    private List<DetectMitigationActionsTaskSummary> tasks;

    public List<DetectMitigationActionsTaskSummary> getTasks() {
        return this.tasks;
    }

    public void setTasks(Collection<DetectMitigationActionsTaskSummary> collection) {
        if (collection == null) {
            this.tasks = null;
        } else {
            this.tasks = new ArrayList(collection);
        }
    }

    public ListDetectMitigationActionsTasksResult withTasks(DetectMitigationActionsTaskSummary... detectMitigationActionsTaskSummaryArr) {
        if (getTasks() == null) {
            this.tasks = new ArrayList(detectMitigationActionsTaskSummaryArr.length);
        }
        for (DetectMitigationActionsTaskSummary detectMitigationActionsTaskSummary : detectMitigationActionsTaskSummaryArr) {
            this.tasks.add(detectMitigationActionsTaskSummary);
        }
        return this;
    }

    public ListDetectMitigationActionsTasksResult withTasks(Collection<DetectMitigationActionsTaskSummary> collection) {
        setTasks(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListDetectMitigationActionsTasksResult withNextToken(String str) {
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
        if (obj == null || !(obj instanceof ListDetectMitigationActionsTasksResult)) {
            return false;
        }
        ListDetectMitigationActionsTasksResult listDetectMitigationActionsTasksResult = (ListDetectMitigationActionsTasksResult) obj;
        if ((listDetectMitigationActionsTasksResult.getTasks() == null) ^ (getTasks() == null)) {
            return false;
        }
        if (listDetectMitigationActionsTasksResult.getTasks() != null && !listDetectMitigationActionsTasksResult.getTasks().equals(getTasks())) {
            return false;
        }
        if ((listDetectMitigationActionsTasksResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listDetectMitigationActionsTasksResult.getNextToken() == null || listDetectMitigationActionsTasksResult.getNextToken().equals(getNextToken());
    }
}
