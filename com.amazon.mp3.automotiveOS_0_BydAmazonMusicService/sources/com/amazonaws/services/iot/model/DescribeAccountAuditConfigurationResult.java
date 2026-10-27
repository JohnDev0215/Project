package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAccountAuditConfigurationResult implements Serializable {
    private Map<String, AuditCheckConfiguration> auditCheckConfigurations;
    private Map<String, AuditNotificationTarget> auditNotificationTargetConfigurations;
    private String roleArn;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public DescribeAccountAuditConfigurationResult withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public Map<String, AuditNotificationTarget> getAuditNotificationTargetConfigurations() {
        return this.auditNotificationTargetConfigurations;
    }

    public void setAuditNotificationTargetConfigurations(Map<String, AuditNotificationTarget> map) {
        this.auditNotificationTargetConfigurations = map;
    }

    public DescribeAccountAuditConfigurationResult withAuditNotificationTargetConfigurations(Map<String, AuditNotificationTarget> map) {
        this.auditNotificationTargetConfigurations = map;
        return this;
    }

    public DescribeAccountAuditConfigurationResult addauditNotificationTargetConfigurationsEntry(String str, AuditNotificationTarget auditNotificationTarget) {
        if (this.auditNotificationTargetConfigurations == null) {
            this.auditNotificationTargetConfigurations = new HashMap();
        }
        if (this.auditNotificationTargetConfigurations.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.auditNotificationTargetConfigurations.put(str, auditNotificationTarget);
        return this;
    }

    public DescribeAccountAuditConfigurationResult clearauditNotificationTargetConfigurationsEntries() {
        this.auditNotificationTargetConfigurations = null;
        return this;
    }

    public Map<String, AuditCheckConfiguration> getAuditCheckConfigurations() {
        return this.auditCheckConfigurations;
    }

    public void setAuditCheckConfigurations(Map<String, AuditCheckConfiguration> map) {
        this.auditCheckConfigurations = map;
    }

    public DescribeAccountAuditConfigurationResult withAuditCheckConfigurations(Map<String, AuditCheckConfiguration> map) {
        this.auditCheckConfigurations = map;
        return this;
    }

    public DescribeAccountAuditConfigurationResult addauditCheckConfigurationsEntry(String str, AuditCheckConfiguration auditCheckConfiguration) {
        if (this.auditCheckConfigurations == null) {
            this.auditCheckConfigurations = new HashMap();
        }
        if (this.auditCheckConfigurations.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.auditCheckConfigurations.put(str, auditCheckConfiguration);
        return this;
    }

    public DescribeAccountAuditConfigurationResult clearauditCheckConfigurationsEntries() {
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
        if (obj == null || !(obj instanceof DescribeAccountAuditConfigurationResult)) {
            return false;
        }
        DescribeAccountAuditConfigurationResult describeAccountAuditConfigurationResult = (DescribeAccountAuditConfigurationResult) obj;
        if ((describeAccountAuditConfigurationResult.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (describeAccountAuditConfigurationResult.getRoleArn() != null && !describeAccountAuditConfigurationResult.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((describeAccountAuditConfigurationResult.getAuditNotificationTargetConfigurations() == null) ^ (getAuditNotificationTargetConfigurations() == null)) {
            return false;
        }
        if (describeAccountAuditConfigurationResult.getAuditNotificationTargetConfigurations() != null && !describeAccountAuditConfigurationResult.getAuditNotificationTargetConfigurations().equals(getAuditNotificationTargetConfigurations())) {
            return false;
        }
        if ((describeAccountAuditConfigurationResult.getAuditCheckConfigurations() == null) ^ (getAuditCheckConfigurations() == null)) {
            return false;
        }
        return describeAccountAuditConfigurationResult.getAuditCheckConfigurations() == null || describeAccountAuditConfigurationResult.getAuditCheckConfigurations().equals(getAuditCheckConfigurations());
    }
}
