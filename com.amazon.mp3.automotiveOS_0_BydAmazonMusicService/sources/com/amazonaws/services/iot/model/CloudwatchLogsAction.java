package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CloudwatchLogsAction implements Serializable {
    private String logGroupName;
    private String roleArn;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public CloudwatchLogsAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public CloudwatchLogsAction withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getLogGroupName() != null) {
            sb.append("logGroupName: " + getLogGroupName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getLogGroupName() != null ? getLogGroupName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CloudwatchLogsAction)) {
            return false;
        }
        CloudwatchLogsAction cloudwatchLogsAction = (CloudwatchLogsAction) obj;
        if ((cloudwatchLogsAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (cloudwatchLogsAction.getRoleArn() != null && !cloudwatchLogsAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((cloudwatchLogsAction.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        return cloudwatchLogsAction.getLogGroupName() == null || cloudwatchLogsAction.getLogGroupName().equals(getLogGroupName());
    }
}
