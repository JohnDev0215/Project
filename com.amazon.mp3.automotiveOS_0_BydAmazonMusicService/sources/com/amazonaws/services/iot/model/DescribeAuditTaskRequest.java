package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditTaskRequest extends AmazonWebServiceRequest implements Serializable {
    private String taskId;

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public DescribeAuditTaskRequest withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskId() != null) {
            sb.append("taskId: " + getTaskId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTaskId() == null ? 0 : getTaskId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuditTaskRequest)) {
            return false;
        }
        DescribeAuditTaskRequest describeAuditTaskRequest = (DescribeAuditTaskRequest) obj;
        if ((describeAuditTaskRequest.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        return describeAuditTaskRequest.getTaskId() == null || describeAuditTaskRequest.getTaskId().equals(getTaskId());
    }
}
