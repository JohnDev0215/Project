package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class SetV2LoggingLevelRequest extends AmazonWebServiceRequest implements Serializable {
    private String logLevel;
    private LogTarget logTarget;

    public LogTarget getLogTarget() {
        return this.logTarget;
    }

    public void setLogTarget(LogTarget logTarget) {
        this.logTarget = logTarget;
    }

    public SetV2LoggingLevelRequest withLogTarget(LogTarget logTarget) {
        this.logTarget = logTarget;
        return this;
    }

    public String getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(String str) {
        this.logLevel = str;
    }

    public SetV2LoggingLevelRequest withLogLevel(String str) {
        this.logLevel = str;
        return this;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
    }

    public SetV2LoggingLevelRequest withLogLevel(LogLevel logLevel) {
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
        if (obj == null || !(obj instanceof SetV2LoggingLevelRequest)) {
            return false;
        }
        SetV2LoggingLevelRequest setV2LoggingLevelRequest = (SetV2LoggingLevelRequest) obj;
        if ((setV2LoggingLevelRequest.getLogTarget() == null) ^ (getLogTarget() == null)) {
            return false;
        }
        if (setV2LoggingLevelRequest.getLogTarget() != null && !setV2LoggingLevelRequest.getLogTarget().equals(getLogTarget())) {
            return false;
        }
        if ((setV2LoggingLevelRequest.getLogLevel() == null) ^ (getLogLevel() == null)) {
            return false;
        }
        return setV2LoggingLevelRequest.getLogLevel() == null || setV2LoggingLevelRequest.getLogLevel().equals(getLogLevel());
    }
}
