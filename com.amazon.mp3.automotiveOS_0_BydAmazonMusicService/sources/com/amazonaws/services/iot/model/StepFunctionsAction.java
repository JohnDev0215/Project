package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class StepFunctionsAction implements Serializable {
    private String executionNamePrefix;
    private String roleArn;
    private String stateMachineName;

    public String getExecutionNamePrefix() {
        return this.executionNamePrefix;
    }

    public void setExecutionNamePrefix(String str) {
        this.executionNamePrefix = str;
    }

    public StepFunctionsAction withExecutionNamePrefix(String str) {
        this.executionNamePrefix = str;
        return this;
    }

    public String getStateMachineName() {
        return this.stateMachineName;
    }

    public void setStateMachineName(String str) {
        this.stateMachineName = str;
    }

    public StepFunctionsAction withStateMachineName(String str) {
        this.stateMachineName = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public StepFunctionsAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getExecutionNamePrefix() != null) {
            sb.append("executionNamePrefix: " + getExecutionNamePrefix() + ",");
        }
        if (getStateMachineName() != null) {
            sb.append("stateMachineName: " + getStateMachineName() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getExecutionNamePrefix() == null ? 0 : getExecutionNamePrefix().hashCode()) + 31) * 31) + (getStateMachineName() == null ? 0 : getStateMachineName().hashCode())) * 31) + (getRoleArn() != null ? getRoleArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StepFunctionsAction)) {
            return false;
        }
        StepFunctionsAction stepFunctionsAction = (StepFunctionsAction) obj;
        if ((stepFunctionsAction.getExecutionNamePrefix() == null) ^ (getExecutionNamePrefix() == null)) {
            return false;
        }
        if (stepFunctionsAction.getExecutionNamePrefix() != null && !stepFunctionsAction.getExecutionNamePrefix().equals(getExecutionNamePrefix())) {
            return false;
        }
        if ((stepFunctionsAction.getStateMachineName() == null) ^ (getStateMachineName() == null)) {
            return false;
        }
        if (stepFunctionsAction.getStateMachineName() != null && !stepFunctionsAction.getStateMachineName().equals(getStateMachineName())) {
            return false;
        }
        if ((stepFunctionsAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return stepFunctionsAction.getRoleArn() == null || stepFunctionsAction.getRoleArn().equals(getRoleArn());
    }
}
