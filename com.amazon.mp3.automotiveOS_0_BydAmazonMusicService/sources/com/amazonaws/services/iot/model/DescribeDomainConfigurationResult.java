package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDomainConfigurationResult implements Serializable {
    private AuthorizerConfig authorizerConfig;
    private String domainConfigurationArn;
    private String domainConfigurationName;
    private String domainConfigurationStatus;
    private String domainName;
    private String domainType;
    private Date lastStatusChangeDate;
    private List<ServerCertificateSummary> serverCertificates;
    private String serviceType;

    public String getDomainConfigurationName() {
        return this.domainConfigurationName;
    }

    public void setDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
    }

    public DescribeDomainConfigurationResult withDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
        return this;
    }

    public String getDomainConfigurationArn() {
        return this.domainConfigurationArn;
    }

    public void setDomainConfigurationArn(String str) {
        this.domainConfigurationArn = str;
    }

    public DescribeDomainConfigurationResult withDomainConfigurationArn(String str) {
        this.domainConfigurationArn = str;
        return this;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public void setDomainName(String str) {
        this.domainName = str;
    }

    public DescribeDomainConfigurationResult withDomainName(String str) {
        this.domainName = str;
        return this;
    }

    public List<ServerCertificateSummary> getServerCertificates() {
        return this.serverCertificates;
    }

    public void setServerCertificates(Collection<ServerCertificateSummary> collection) {
        if (collection == null) {
            this.serverCertificates = null;
        } else {
            this.serverCertificates = new ArrayList(collection);
        }
    }

    public DescribeDomainConfigurationResult withServerCertificates(ServerCertificateSummary... serverCertificateSummaryArr) {
        if (getServerCertificates() == null) {
            this.serverCertificates = new ArrayList(serverCertificateSummaryArr.length);
        }
        for (ServerCertificateSummary serverCertificateSummary : serverCertificateSummaryArr) {
            this.serverCertificates.add(serverCertificateSummary);
        }
        return this;
    }

    public DescribeDomainConfigurationResult withServerCertificates(Collection<ServerCertificateSummary> collection) {
        setServerCertificates(collection);
        return this;
    }

    public AuthorizerConfig getAuthorizerConfig() {
        return this.authorizerConfig;
    }

    public void setAuthorizerConfig(AuthorizerConfig authorizerConfig) {
        this.authorizerConfig = authorizerConfig;
    }

    public DescribeDomainConfigurationResult withAuthorizerConfig(AuthorizerConfig authorizerConfig) {
        this.authorizerConfig = authorizerConfig;
        return this;
    }

    public String getDomainConfigurationStatus() {
        return this.domainConfigurationStatus;
    }

    public void setDomainConfigurationStatus(String str) {
        this.domainConfigurationStatus = str;
    }

    public DescribeDomainConfigurationResult withDomainConfigurationStatus(String str) {
        this.domainConfigurationStatus = str;
        return this;
    }

    public void setDomainConfigurationStatus(DomainConfigurationStatus domainConfigurationStatus) {
        this.domainConfigurationStatus = domainConfigurationStatus.toString();
    }

    public DescribeDomainConfigurationResult withDomainConfigurationStatus(DomainConfigurationStatus domainConfigurationStatus) {
        this.domainConfigurationStatus = domainConfigurationStatus.toString();
        return this;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String str) {
        this.serviceType = str;
    }

    public DescribeDomainConfigurationResult withServiceType(String str) {
        this.serviceType = str;
        return this;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType.toString();
    }

    public DescribeDomainConfigurationResult withServiceType(ServiceType serviceType) {
        this.serviceType = serviceType.toString();
        return this;
    }

    public String getDomainType() {
        return this.domainType;
    }

    public void setDomainType(String str) {
        this.domainType = str;
    }

    public DescribeDomainConfigurationResult withDomainType(String str) {
        this.domainType = str;
        return this;
    }

    public void setDomainType(DomainType domainType) {
        this.domainType = domainType.toString();
    }

    public DescribeDomainConfigurationResult withDomainType(DomainType domainType) {
        this.domainType = domainType.toString();
        return this;
    }

    public Date getLastStatusChangeDate() {
        return this.lastStatusChangeDate;
    }

    public void setLastStatusChangeDate(Date date) {
        this.lastStatusChangeDate = date;
    }

    public DescribeDomainConfigurationResult withLastStatusChangeDate(Date date) {
        this.lastStatusChangeDate = date;
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
        if (getDomainName() != null) {
            sb.append("domainName: " + getDomainName() + ",");
        }
        if (getServerCertificates() != null) {
            sb.append("serverCertificates: " + getServerCertificates() + ",");
        }
        if (getAuthorizerConfig() != null) {
            sb.append("authorizerConfig: " + getAuthorizerConfig() + ",");
        }
        if (getDomainConfigurationStatus() != null) {
            sb.append("domainConfigurationStatus: " + getDomainConfigurationStatus() + ",");
        }
        if (getServiceType() != null) {
            sb.append("serviceType: " + getServiceType() + ",");
        }
        if (getDomainType() != null) {
            sb.append("domainType: " + getDomainType() + ",");
        }
        if (getLastStatusChangeDate() != null) {
            sb.append("lastStatusChangeDate: " + getLastStatusChangeDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((getDomainConfigurationName() == null ? 0 : getDomainConfigurationName().hashCode()) + 31) * 31) + (getDomainConfigurationArn() == null ? 0 : getDomainConfigurationArn().hashCode())) * 31) + (getDomainName() == null ? 0 : getDomainName().hashCode())) * 31) + (getServerCertificates() == null ? 0 : getServerCertificates().hashCode())) * 31) + (getAuthorizerConfig() == null ? 0 : getAuthorizerConfig().hashCode())) * 31) + (getDomainConfigurationStatus() == null ? 0 : getDomainConfigurationStatus().hashCode())) * 31) + (getServiceType() == null ? 0 : getServiceType().hashCode())) * 31) + (getDomainType() == null ? 0 : getDomainType().hashCode())) * 31) + (getLastStatusChangeDate() != null ? getLastStatusChangeDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeDomainConfigurationResult)) {
            return false;
        }
        DescribeDomainConfigurationResult describeDomainConfigurationResult = (DescribeDomainConfigurationResult) obj;
        if ((describeDomainConfigurationResult.getDomainConfigurationName() == null) ^ (getDomainConfigurationName() == null)) {
            return false;
        }
        if (describeDomainConfigurationResult.getDomainConfigurationName() != null && !describeDomainConfigurationResult.getDomainConfigurationName().equals(getDomainConfigurationName())) {
            return false;
        }
        if ((describeDomainConfigurationResult.getDomainConfigurationArn() == null) ^ (getDomainConfigurationArn() == null)) {
            return false;
        }
        if (describeDomainConfigurationResult.getDomainConfigurationArn() != null && !describeDomainConfigurationResult.getDomainConfigurationArn().equals(getDomainConfigurationArn())) {
            return false;
        }
        if ((describeDomainConfigurationResult.getDomainName() == null) ^ (getDomainName() == null)) {
            return false;
        }
        if (describeDomainConfigurationResult.getDomainName() != null && !describeDomainConfigurationResult.getDomainName().equals(getDomainName())) {
            return false;
        }
        if ((describeDomainConfigurationResult.getServerCertificates() == null) ^ (getServerCertificates() == null)) {
            return false;
        }
        if (describeDomainConfigurationResult.getServerCertificates() != null && !describeDomainConfigurationResult.getServerCertificates().equals(getServerCertificates())) {
            return false;
        }
        if ((describeDomainConfigurationResult.getAuthorizerConfig() == null) ^ (getAuthorizerConfig() == null)) {
            return false;
        }
        if (describeDomainConfigurationResult.getAuthorizerConfig() != null && !describeDomainConfigurationResult.getAuthorizerConfig().equals(getAuthorizerConfig())) {
            return false;
        }
        if ((describeDomainConfigurationResult.getDomainConfigurationStatus() == null) ^ (getDomainConfigurationStatus() == null)) {
            return false;
        }
        if (describeDomainConfigurationResult.getDomainConfigurationStatus() != null && !describeDomainConfigurationResult.getDomainConfigurationStatus().equals(getDomainConfigurationStatus())) {
            return false;
        }
        if ((describeDomainConfigurationResult.getServiceType() == null) ^ (getServiceType() == null)) {
            return false;
        }
        if (describeDomainConfigurationResult.getServiceType() != null && !describeDomainConfigurationResult.getServiceType().equals(getServiceType())) {
            return false;
        }
        if ((describeDomainConfigurationResult.getDomainType() == null) ^ (getDomainType() == null)) {
            return false;
        }
        if (describeDomainConfigurationResult.getDomainType() != null && !describeDomainConfigurationResult.getDomainType().equals(getDomainType())) {
            return false;
        }
        if ((describeDomainConfigurationResult.getLastStatusChangeDate() == null) ^ (getLastStatusChangeDate() == null)) {
            return false;
        }
        return describeDomainConfigurationResult.getLastStatusChangeDate() == null || describeDomainConfigurationResult.getLastStatusChangeDate().equals(getLastStatusChangeDate());
    }
}
