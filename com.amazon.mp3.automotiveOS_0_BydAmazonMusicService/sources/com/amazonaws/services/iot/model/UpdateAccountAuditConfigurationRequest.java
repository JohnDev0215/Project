package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateAccountAuditConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, AuditCheckConfiguration> auditCheckConfigurations;
    private Map<String, AuditNotificationTarget> auditNotificationTargetConfigurations;
    private String roleArn;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public UpdateAccountAuditConfigurationRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public Map<String, AuditNotificationTarget> getAuditNotificationTargetConfigurations() {
        return this.auditNotificationTargetConfigurations;
    }

    public void setAuditNotificationTargetConfigurations(Map<String, AuditNotificationTarget> map) {
        this.auditNotificationTargetConfigurations = map;
    }

    public UpdateAccountAuditConfigurationRequest withAuditNotificationTargetConfigurations(Map<String, AuditNotificationTarget> map) {
        this.auditNotificationTargetConfigurations = map;
        return this;
    }

    public UpdateAccountAuditConfigurationRequest addauditNotificationTargetConfigurationsEntry(String str, AuditNotificationTarget auditNotificationTarget) {
        if (this.auditNotificationTargetConfigurations == null) {
            this.auditNotificationTargetConfigurations = new HashMap();
        }
        if (this.auditNotificationTargetConfigurations.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.auditNotificationTargetConfigurations.put(str, auditNotificationTarget);
        return this;
    }

    public UpdateAccountAuditConfigurationRequest clearauditNotificationTargetConfigurationsEntries() {
        this.auditNotificationTargetConfigurations = null;
        return this;
    }

    public Map<String, AuditCheckConfiguration> getAuditCheckConfigurations() {
        return this.auditCheckConfigurations;
    }

    public void setAuditCheckConfigurations(Map<String, AuditCheckConfiguration> map) {
        this.auditCheckConfigurations = map;
    }

    public UpdateAccountAuditConfigurationRequest withAuditCheckConfigurations(Map<String, AuditCheckConfiguration> map) {
        this.auditCheckConfigurations = map;
        return this;
    }

    public UpdateAccountAuditConfigurationRequest addauditCheckConfigurationsEntry(String str, AuditCheckConfiguration auditCheckConfiguration) {
        if (this.auditCheckConfigurations == null) {
            this.auditCheckConfigurations = new HashMap();
        }
        if (this.auditCheckConfigurations.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.auditCheckConfigurations.put(str, auditCheckConfiguration);
        return this;
    }

    public UpdateAccountAuditConfigurationRequest clearauditCheckConfigurationsEntries() {
        this.auditCheckConfigurations = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getAuditNotificationTargetConfigurations() != null) {
            sb.append("auditNotificationTargetConfigurations: " + getAuditNotificationTargetConfigurations() + ",");
        }
        if (getAuditCheckConfigurations() != null) {
            sb.append("auditCheckConfigurations: " + getAuditCheckConfigurations());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getAuditNotificationTargetConfigurations() == null ? 0 : getAuditNotificationTargetConfigurations().hashCode())) * 31) + (getAuditCheckConfigurations() != null ? getAuditCheckConfigurations().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateAccountAuditConfigurationRequest)) {
            return false;
        }
        UpdateAccountAuditConfigurationRequest updateAccountAuditConfigurationRequest = (UpdateAccountAuditConfigurationRequest) obj;
        if ((updateAccountAuditConfigurationRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (updateAccountAuditConfigurationRequest.getRoleArn() != null && !updateAccountAuditConfigurationRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((updateAccountAuditConfigurationRequest.getAuditNotificationTargetConfigurations() == null) ^ (getAuditNotificationTargetConfigurations() == null)) {
            return false;
        }
        if (updateAccountAuditConfigurationRequest.getAuditNotificationTargetConfigurations() != null && !updateAccountAuditConfigurationRequest.getAuditNotificationTargetConfigurations().equals(getAuditNotificationTargetConfigurations())) {
            return false;
        }
        if ((updateAccountAuditConfigurationRequest.getAuditCheckConfigurations() == null) ^ (getAuditCheckConfigurations() == null)) {
            return false;
        }
        return updateAccountAuditConfigurationRequest.getAuditCheckConfigurations() == null || updateAccountAuditConfigurationRequest.getAuditCheckConfigurations().equals(getAuditCheckConfigurations());
    }
}
