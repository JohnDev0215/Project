package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeJobExecutionResult implements Serializable {
    private JobExecution execution;

    public JobExecution getExecution() {
        return this.execution;
    }

    public void setExecution(JobExecution jobExecution) {
        this.execution = jobExecution;
    }

    public DescribeJobExecutionResult withExecution(JobExecution jobExecution) {
        this.execution = jobExecution;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getExecution() != null) {
            sb.append("execution: " + getExecution());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getExecution() == null ? 0 : getExecution().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeJobExecutionResult)) {
            return false;
        }
        DescribeJobExecutionResult describeJobExecutionResult = (DescribeJobExecutionResult) obj;
        if ((describeJobExecutionResult.getExecution() == null) ^ (getExecution() == null)) {
            return false;
        }
        return describeJobExecutionResult.getExecution() == null || describeJobExecutionResult.getExecution().equals(getExecution());
    }
}
