package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDetectMitigationActionsTaskResult implements Serializable {
    private DetectMitigationActionsTaskSummary taskSummary;

    public DetectMitigationActionsTaskSummary getTaskSummary() {
        return this.taskSummary;
    }

    public void setTaskSummary(DetectMitigationActionsTaskSummary detectMitigationActionsTaskSummary) {
        this.taskSummary = detectMitigationActionsTaskSummary;
    }

    public DescribeDetectMitigationActionsTaskResult withTaskSummary(DetectMitigationActionsTaskSummary detectMitigationActionsTaskSummary) {
        this.taskSummary = detectMitigationActionsTaskSummary;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskSummary() != null) {
            sb.append("taskSummary: " + getTaskSummary());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTaskSummary() == null ? 0 : getTaskSummary().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeDetectMitigationActionsTaskResult)) {
            return false;
        }
        DescribeDetectMitigationActionsTaskResult describeDetectMitigationActionsTaskResult = (DescribeDetectMitigationActionsTaskResult) obj;
        if ((describeDetectMitigationActionsTaskResult.getTaskSummary() == null) ^ (getTaskSummary() == null)) {
            return false;
        }
        return describeDetectMitigationActionsTaskResult.getTaskSummary() == null || describeDetectMitigationActionsTaskResult.getTaskSummary().equals(getTaskSummary());
    }
}
