package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateDomainConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private AuthorizerConfig authorizerConfig;
    private String domainConfigurationName;
    private String domainName;
    private List<String> serverCertificateArns;
    private String serviceType;
    private List<Tag> tags;
    private String validationCertificateArn;

    public String getDomainConfigurationName() {
        return this.domainConfigurationName;
    }

    public void setDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
    }

    public CreateDomainConfigurationRequest withDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
        return this;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public void setDomainName(String str) {
        this.domainName = str;
    }

    public CreateDomainConfigurationRequest withDomainName(String str) {
        this.domainName = str;
        return this;
    }

    public List<String> getServerCertificateArns() {
        return this.serverCertificateArns;
    }

    public void setServerCertificateArns(Collection<String> collection) {
        if (collection == null) {
            this.serverCertificateArns = null;
        } else {
            this.serverCertificateArns = new ArrayList(collection);
        }
    }

    public CreateDomainConfigurationRequest withServerCertificateArns(String... strArr) {
        if (getServerCertificateArns() == null) {
            this.serverCertificateArns = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.serverCertificateArns.add(str);
        }
        return this;
    }

    public CreateDomainConfigurationRequest withServerCertificateArns(Collection<String> collection) {
        setServerCertificateArns(collection);
        return this;
    }

    public String getValidationCertificateArn() {
        return this.validationCertificateArn;
    }

    public void setValidationCertificateArn(String str) {
        this.validationCertificateArn = str;
    }

    public CreateDomainConfigurationRequest withValidationCertificateArn(String str) {
        this.validationCertificateArn = str;
        return this;
    }

    public AuthorizerConfig getAuthorizerConfig() {
        return this.authorizerConfig;
    }

    public void setAuthorizerConfig(AuthorizerConfig authorizerConfig) {
        this.authorizerConfig = authorizerConfig;
    }

    public CreateDomainConfigurationRequest withAuthorizerConfig(AuthorizerConfig authorizerConfig) {
        this.authorizerConfig = authorizerConfig;
        return this;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String str) {
        this.serviceType = str;
    }

    public CreateDomainConfigurationRequest withServiceType(String str) {
        this.serviceType = str;
        return this;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType.toString();
    }

    public CreateDomainConfigurationRequest withServiceType(ServiceType serviceType) {
        this.serviceType = serviceType.toString();
        return this;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public CreateDomainConfigurationRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateDomainConfigurationRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDomainConfigurationName() != null) {
            sb.append("domainConfigurationName: " + getDomainConfigurationName() + ",");
        }
        if (getDomainName() != null) {
            sb.append("domainName: " + getDomainName() + ",");
        }
        if (getServerCertificateArns() != null) {
            sb.append("serverCertificateArns: " + getServerCertificateArns() + ",");
        }
        if (getValidationCertificateArn() != null) {
            sb.append("validationCertificateArn: " + getValidationCertificateArn() + ",");
        }
        if (getAuthorizerConfig() != null) {
            sb.append("authorizerConfig: " + getAuthorizerConfig() + ",");
        }
        if (getServiceType() != null) {
            sb.append("serviceType: " + getServiceType() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getDomainConfigurationName() == null ? 0 : getDomainConfigurationName().hashCode()) + 31) * 31) + (getDomainName() == null ? 0 : getDomainName().hashCode())) * 31) + (getServerCertificateArns() == null ? 0 : getServerCertificateArns().hashCode())) * 31) + (getValidationCertificateArn() == null ? 0 : getValidationCertificateArn().hashCode())) * 31) + (getAuthorizerConfig() == null ? 0 : getAuthorizerConfig().hashCode())) * 31) + (getServiceType() == null ? 0 : getServiceType().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateDomainConfigurationRequest)) {
            return false;
        }
        CreateDomainConfigurationRequest createDomainConfigurationRequest = (CreateDomainConfigurationRequest) obj;
        if ((createDomainConfigurationRequest.getDomainConfigurationName() == null) ^ (getDomainConfigurationName() == null)) {
            return false;
        }
        if (createDomainConfigurationRequest.getDomainConfigurationName() != null && !createDomainConfigurationRequest.getDomainConfigurationName().equals(getDomainConfigurationName())) {
            return false;
        }
        if ((createDomainConfigurationRequest.getDomainName() == null) ^ (getDomainName() == null)) {
            return false;
        }
        if (createDomainConfigurationRequest.getDomainName() != null && !createDomainConfigurationRequest.getDomainName().equals(getDomainName())) {
            return false;
        }
        if ((createDomainConfigurationRequest.getServerCertificateArns() == null) ^ (getServerCertificateArns() == null)) {
            return false;
        }
        if (createDomainConfigurationRequest.getServerCertificateArns() != null && !createDomainConfigurationRequest.getServerCertificateArns().equals(getServerCertificateArns())) {
            return false;
        }
        if ((createDomainConfigurationRequest.getValidationCertificateArn() == null) ^ (getValidationCertificateArn() == null)) {
            return false;
        }
        if (createDomainConfigurationRequest.getValidationCertificateArn() != null && !createDomainConfigurationRequest.getValidationCertificateArn().equals(getValidationCertificateArn())) {
            return false;
        }
        if ((createDomainConfigurationRequest.getAuthorizerConfig() == null) ^ (getAuthorizerConfig() == null)) {
            return false;
        }
        if (createDomainConfigurationRequest.getAuthorizerConfig() != null && !createDomainConfigurationRequest.getAuthorizerConfig().equals(getAuthorizerConfig())) {
            return false;
        }
        if ((createDomainConfigurationRequest.getServiceType() == null) ^ (getServiceType() == null)) {
            return false;
        }
        if (createDomainConfigurationRequest.getServiceType() != null && !createDomainConfigurationRequest.getServiceType().equals(getServiceType())) {
            return false;
        }
        if ((createDomainConfigurationRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createDomainConfigurationRequest.getTags() == null || createDomainConfigurationRequest.getTags().equals(getTags());
    }
}
