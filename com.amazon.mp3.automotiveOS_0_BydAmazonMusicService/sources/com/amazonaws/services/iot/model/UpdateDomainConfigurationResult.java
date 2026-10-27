package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDomainConfigurationResult implements Serializable {
    private String domainConfigurationArn;
    private String domainConfigurationName;

    public String getDomainConfigurationName() {
        return this.domainConfigurationName;
    }

    public void setDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
    }

    public UpdateDomainConfigurationResult withDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
        return this;
    }

    public String getDomainConfigurationArn() {
        return this.domainConfigurationArn;
    }

    public void setDomainConfigurationArn(String str) {
        this.domainConfigurationArn = str;
    }

    public UpdateDomainConfigurationResult withDomainConfigurationArn(String str) {
        this.domainConfigurationArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDomainConfigurationName() != null) {
            sb.append("domainConfigurationName: " + getDomainConfigurationName() + ",");
        }
        if (getDomainConfigurationArn() != null) {
            sb.append("domainConfigurationArn: " + getDomainConfigurationArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getDomainConfigurationName() == null ? 0 : getDomainConfigurationName().hashCode()) + 31) * 31) + (getDomainConfigurationArn() != null ? getDomainConfigurationArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateDomainConfigurationResult)) {
            return false;
        }
        UpdateDomainConfigurationResult updateDomainConfigurationResult = (UpdateDomainConfigurationResult) obj;
        if ((updateDomainConfigurationResult.getDomainConfigurationName() == null) ^ (getDomainConfigurationName() == null)) {
            return false;
        }
        if (updateDomainConfigurationResult.getDomainConfigurationName() != null && !updateDomainConfigurationResult.getDomainConfigurationName().equals(getDomainConfigurationName())) {
            return false;
        }
        if ((updateDomainConfigurationResult.getDomainConfigurationArn() == null) ^ (getDomainConfigurationArn() == null)) {
            return false;
        }
        return updateDomainConfigurationResult.getDomainConfigurationArn() == null || updateDomainConfigurationResult.getDomainConfigurationArn().equals(getDomainConfigurationArn());
    }
}
