package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class SetV2LoggingOptionsRequest extends AmazonWebServiceRequest implements Serializable {
    private String defaultLogLevel;
    private Boolean disableAllLogs;
    private String roleArn;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public SetV2LoggingOptionsRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getDefaultLogLevel() {
        return this.defaultLogLevel;
    }

    public void setDefaultLogLevel(String str) {
        this.defaultLogLevel = str;
    }

    public SetV2LoggingOptionsRequest withDefaultLogLevel(String str) {
        this.defaultLogLevel = str;
        return this;
    }

    public void setDefaultLogLevel(LogLevel logLevel) {
        this.defaultLogLevel = logLevel.toString();
    }

    public SetV2LoggingOptionsRequest withDefaultLogLevel(LogLevel logLevel) {
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

    public SetV2LoggingOptionsRequest withDisableAllLogs(Boolean bool) {
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
        if (obj == null || !(obj instanceof SetV2LoggingOptionsRequest)) {
            return false;
        }
        SetV2LoggingOptionsRequest setV2LoggingOptionsRequest = (SetV2LoggingOptionsRequest) obj;
        if ((setV2LoggingOptionsRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (setV2LoggingOptionsRequest.getRoleArn() != null && !setV2LoggingOptionsRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((setV2LoggingOptionsRequest.getDefaultLogLevel() == null) ^ (getDefaultLogLevel() == null)) {
            return false;
        }
        if (setV2LoggingOptionsRequest.getDefaultLogLevel() != null && !setV2LoggingOptionsRequest.getDefaultLogLevel().equals(getDefaultLogLevel())) {
            return false;
        }
        if ((setV2LoggingOptionsRequest.getDisableAllLogs() == null) ^ (getDisableAllLogs() == null)) {
            return false;
        }
        return setV2LoggingOptionsRequest.getDisableAllLogs() == null || setV2LoggingOptionsRequest.getDisableAllLogs().equals(getDisableAllLogs());
    }
}
