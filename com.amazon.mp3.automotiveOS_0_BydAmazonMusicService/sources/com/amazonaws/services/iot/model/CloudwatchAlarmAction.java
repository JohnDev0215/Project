package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CloudwatchAlarmAction implements Serializable {
    private String alarmName;
    private String roleArn;
    private String stateReason;
    private String stateValue;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public CloudwatchAlarmAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getAlarmName() {
        return this.alarmName;
    }

    public void setAlarmName(String str) {
        this.alarmName = str;
    }

    public CloudwatchAlarmAction withAlarmName(String str) {
        this.alarmName = str;
        return this;
    }

    public String getStateReason() {
        return this.stateReason;
    }

    public void setStateReason(String str) {
        this.stateReason = str;
    }

    public CloudwatchAlarmAction withStateReason(String str) {
        this.stateReason = str;
        return this;
    }

    public String getStateValue() {
        return this.stateValue;
    }

    public void setStateValue(String str) {
        this.stateValue = str;
    }

    public CloudwatchAlarmAction withStateValue(String str) {
        this.stateValue = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getAlarmName() != null) {
            sb.append("alarmName: " + getAlarmName() + ",");
        }
        if (getStateReason() != null) {
            sb.append("stateReason: " + getStateReason() + ",");
        }
        if (getStateValue() != null) {
            sb.append("stateValue: " + getStateValue());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getAlarmName() == null ? 0 : getAlarmName().hashCode())) * 31) + (getStateReason() == null ? 0 : getStateReason().hashCode())) * 31) + (getStateValue() != null ? getStateValue().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CloudwatchAlarmAction)) {
            return false;
        }
        CloudwatchAlarmAction cloudwatchAlarmAction = (CloudwatchAlarmAction) obj;
        if ((cloudwatchAlarmAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (cloudwatchAlarmAction.getRoleArn() != null && !cloudwatchAlarmAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((cloudwatchAlarmAction.getAlarmName() == null) ^ (getAlarmName() == null)) {
            return false;
        }
        if (cloudwatchAlarmAction.getAlarmName() != null && !cloudwatchAlarmAction.getAlarmName().equals(getAlarmName())) {
            return false;
        }
        if ((cloudwatchAlarmAction.getStateReason() == null) ^ (getStateReason() == null)) {
            return false;
        }
        if (cloudwatchAlarmAction.getStateReason() != null && !cloudwatchAlarmAction.getStateReason().equals(getStateReason())) {
            return false;
        }
        if ((cloudwatchAlarmAction.getStateValue() == null) ^ (getStateValue() == null)) {
            return false;
        }
        return cloudwatchAlarmAction.getStateValue() == null || cloudwatchAlarmAction.getStateValue().equals(getStateValue());
    }
}
