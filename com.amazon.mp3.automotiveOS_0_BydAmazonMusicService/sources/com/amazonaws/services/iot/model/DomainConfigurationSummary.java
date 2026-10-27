package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DomainConfigurationSummary implements Serializable {
    private String domainConfigurationArn;
    private String domainConfigurationName;
    private String serviceType;

    public String getDomainConfigurationName() {
        return this.domainConfigurationName;
    }

    public void setDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
    }

    public DomainConfigurationSummary withDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
        return this;
    }

    public String getDomainConfigurationArn() {
        return this.domainConfigurationArn;
    }

    public void setDomainConfigurationArn(String str) {
        this.domainConfigurationArn = str;
    }

    public DomainConfigurationSummary withDomainConfigurationArn(String str) {
        this.domainConfigurationArn = str;
        return this;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String str) {
        this.serviceType = str;
    }

    public DomainConfigurationSummary withServiceType(String str) {
        this.serviceType = str;
        return this;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType.toString();
    }

    public DomainConfigurationSummary withServiceType(ServiceType serviceType) {
        this.serviceType = serviceType.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDomainConfigurationName() != null) {
            sb.append("domainConfigurationName: " + getDomainConfigurationName() + ",");
        }
        if (getDomainConfigurationArn() != null) {
            sb.append("domainConfigurationArn: " + getDomainConfigurationArn() + ",");
        }
        if (getServiceType() != null) {
            sb.append("serviceType: " + getServiceType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getDomainConfigurationName() == null ? 0 : getDomainConfigurationName().hashCode()) + 31) * 31) + (getDomainConfigurationArn() == null ? 0 : getDomainConfigurationArn().hashCode())) * 31) + (getServiceType() != null ? getServiceType().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DomainConfigurationSummary)) {
            return false;
        }
        DomainConfigurationSummary domainConfigurationSummary = (DomainConfigurationSummary) obj;
        if ((domainConfigurationSummary.getDomainConfigurationName() == null) ^ (getDomainConfigurationName() == null)) {
            return false;
        }
        if (domainConfigurationSummary.getDomainConfigurationName() != null && !domainConfigurationSummary.getDomainConfigurationName().equals(getDomainConfigurationName())) {
            return false;
        }
        if ((domainConfigurationSummary.getDomainConfigurationArn() == null) ^ (getDomainConfigurationArn() == null)) {
            return false;
        }
        if (domainConfigurationSummary.getDomainConfigurationArn() != null && !domainConfigurationSummary.getDomainConfigurationArn().equals(getDomainConfigurationArn())) {
            return false;
        }
        if ((domainConfigurationSummary.getServiceType() == null) ^ (getServiceType() == null)) {
            return false;
        }
        return domainConfigurationSummary.getServiceType() == null || domainConfigurationSummary.getServiceType().equals(getServiceType());
    }
}
