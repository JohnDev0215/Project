package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class EnableIoTLoggingParams implements Serializable {
    private String logLevel;
    private String roleArnForLogging;

    public String getRoleArnForLogging() {
        return this.roleArnForLogging;
    }

    public void setRoleArnForLogging(String str) {
        this.roleArnForLogging = str;
    }

    public EnableIoTLoggingParams withRoleArnForLogging(String str) {
        this.roleArnForLogging = str;
        return this;
    }

    public String getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(String str) {
        this.logLevel = str;
    }

    public EnableIoTLoggingParams withLogLevel(String str) {
        this.logLevel = str;
        return this;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
    }

    public EnableIoTLoggingParams withLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArnForLogging() != null) {
            sb.append("roleArnForLogging: " + getRoleArnForLogging() + ",");
        }
        if (getLogLevel() != null) {
            sb.append("logLevel: " + getLogLevel());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRoleArnForLogging() == null ? 0 : getRoleArnForLogging().hashCode()) + 31) * 31) + (getLogLevel() != null ? getLogLevel().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof EnableIoTLoggingParams)) {
            return false;
        }
        EnableIoTLoggingParams enableIoTLoggingParams = (EnableIoTLoggingParams) obj;
        if ((enableIoTLoggingParams.getRoleArnForLogging() == null) ^ (getRoleArnForLogging() == null)) {
            return false;
        }
        if (enableIoTLoggingParams.getRoleArnForLogging() != null && !enableIoTLoggingParams.getRoleArnForLogging().equals(getRoleArnForLogging())) {
            return false;
        }
        if ((enableIoTLoggingParams.getLogLevel() == null) ^ (getLogLevel() == null)) {
            return false;
        }
        return enableIoTLoggingParams.getLogLevel() == null || enableIoTLoggingParams.getLogLevel().equals(getLogLevel());
    }
}
