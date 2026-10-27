package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class LogTargetConfiguration implements Serializable {
    private String logLevel;
    private LogTarget logTarget;

    public LogTarget getLogTarget() {
        return this.logTarget;
    }

    public void setLogTarget(LogTarget logTarget) {
        this.logTarget = logTarget;
    }

    public LogTargetConfiguration withLogTarget(LogTarget logTarget) {
        this.logTarget = logTarget;
        return this;
    }

    public String getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(String str) {
        this.logLevel = str;
    }

    public LogTargetConfiguration withLogLevel(String str) {
        this.logLevel = str;
        return this;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
    }

    public LogTargetConfiguration withLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getLogTarget() != null) {
            sb.append("logTarget: " + getLogTarget() + ",");
        }
        if (getLogLevel() != null) {
            sb.append("logLevel: " + getLogLevel());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getLogTarget() == null ? 0 : getLogTarget().hashCode()) + 31) * 31) + (getLogLevel() != null ? getLogLevel().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LogTargetConfiguration)) {
            return false;
        }
        LogTargetConfiguration logTargetConfiguration = (LogTargetConfiguration) obj;
        if ((logTargetConfiguration.getLogTarget() == null) ^ (getLogTarget() == null)) {
            return false;
        }
        if (logTargetConfiguration.getLogTarget() != null && !logTargetConfiguration.getLogTarget().equals(getLogTarget())) {
            return false;
        }
        if ((logTargetConfiguration.getLogLevel() == null) ^ (getLogLevel() == null)) {
            return false;
        }
        return logTargetConfiguration.getLogLevel() == null || logTargetConfiguration.getLogLevel().equals(getLogLevel());
    }
}
