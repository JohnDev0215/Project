package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetLoggingOptionsResult implements Serializable {
    private String logLevel;
    private String roleArn;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public GetLoggingOptionsResult withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(String str) {
        this.logLevel = str;
    }

    public GetLoggingOptionsResult withLogLevel(String str) {
        this.logLevel = str;
        return this;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
    }

    public GetLoggingOptionsResult withLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getLogLevel() != null) {
            sb.append("logLevel: " + getLogLevel());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getLogLevel() != null ? getLogLevel().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetLoggingOptionsResult)) {
            return false;
        }
        GetLoggingOptionsResult getLoggingOptionsResult = (GetLoggingOptionsResult) obj;
        if ((getLoggingOptionsResult.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (getLoggingOptionsResult.getRoleArn() != null && !getLoggingOptionsResult.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((getLoggingOptionsResult.getLogLevel() == null) ^ (getLogLevel() == null)) {
            return false;
        }
        return getLoggingOptionsResult.getLogLevel() == null || getLoggingOptionsResult.getLogLevel().equals(getLogLevel());
    }
}
