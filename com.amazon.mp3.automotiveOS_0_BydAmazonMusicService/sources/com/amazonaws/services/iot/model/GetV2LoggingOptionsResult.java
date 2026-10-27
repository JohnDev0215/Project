package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetV2LoggingOptionsResult implements Serializable {
    private String defaultLogLevel;
    private Boolean disableAllLogs;
    private String roleArn;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public GetV2LoggingOptionsResult withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getDefaultLogLevel() {
        return this.defaultLogLevel;
    }

    public void setDefaultLogLevel(String str) {
        this.defaultLogLevel = str;
    }

    public GetV2LoggingOptionsResult withDefaultLogLevel(String str) {
        this.defaultLogLevel = str;
        return this;
    }

    public void setDefaultLogLevel(LogLevel logLevel) {
        this.defaultLogLevel = logLevel.toString();
    }

    public GetV2LoggingOptionsResult withDefaultLogLevel(LogLevel logLevel) {
        this.defaultLogLevel = logLevel.toString();
        return this;
    }

    public Boolean isDisableAllLogs() {
        return this.disableAllLogs;
    }

    public Boolean getDisableAllLogs() {
        return this.disableAllLogs;
    }

    public void setDisableAllLogs(Boolean bool) {
        this.disableAllLogs = bool;
    }

    public GetV2LoggingOptionsResult withDisableAllLogs(Boolean bool) {
        this.disableAllLogs = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getDefaultLogLevel() != null) {
            sb.append("defaultLogLevel: " + getDefaultLogLevel() + ",");
        }
        if (getDisableAllLogs() != null) {
            sb.append("disableAllLogs: " + getDisableAllLogs());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getDefaultLogLevel() == null ? 0 : getDefaultLogLevel().hashCode())) * 31) + (getDisableAllLogs() != null ? getDisableAllLogs().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetV2LoggingOptionsResult)) {
            return false;
        }
        GetV2LoggingOptionsResult getV2LoggingOptionsResult = (GetV2LoggingOptionsResult) obj;
        if ((getV2LoggingOptionsResult.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (getV2LoggingOptionsResult.getRoleArn() != null && !getV2LoggingOptionsResult.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((getV2LoggingOptionsResult.getDefaultLogLevel() == null) ^ (getDefaultLogLevel() == null)) {
            return false;
        }
        if (getV2LoggingOptionsResult.getDefaultLogLevel() != null && !getV2LoggingOptionsResult.getDefaultLogLevel().equals(getDefaultLogLevel())) {
            return false;
        }
        if ((getV2LoggingOptionsResult.getDisableAllLogs() == null) ^ (getDisableAllLogs() == null)) {
            return false;
        }
        return getV2LoggingOptionsResult.getDisableAllLogs() == null || getV2LoggingOptionsResult.getDisableAllLogs().equals(getDisableAllLogs());
    }
}
