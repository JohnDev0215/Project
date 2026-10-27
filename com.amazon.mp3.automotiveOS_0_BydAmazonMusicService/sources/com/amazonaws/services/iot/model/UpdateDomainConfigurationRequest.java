package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDomainConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private AuthorizerConfig authorizerConfig;
    private String domainConfigurationName;
    private String domainConfigurationStatus;
    private Boolean removeAuthorizerConfig;

    public String getDomainConfigurationName() {
        return this.domainConfigurationName;
    }

    public void setDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
    }

    public UpdateDomainConfigurationRequest withDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
        return this;
    }

    public AuthorizerConfig getAuthorizerConfig() {
        return this.authorizerConfig;
    }

    public void setAuthorizerConfig(AuthorizerConfig authorizerConfig) {
        this.authorizerConfig = authorizerConfig;
    }

    public UpdateDomainConfigurationRequest withAuthorizerConfig(AuthorizerConfig authorizerConfig) {
        this.authorizerConfig = authorizerConfig;
        return this;
    }

    public String getDomainConfigurationStatus() {
        return this.domainConfigurationStatus;
    }

    public void setDomainConfigurationStatus(String str) {
        this.domainConfigurationStatus = str;
    }

    public UpdateDomainConfigurationRequest withDomainConfigurationStatus(String str) {
        this.domainConfigurationStatus = str;
        return this;
    }

    public void setDomainConfigurationStatus(DomainConfigurationStatus domainConfigurationStatus) {
        this.domainConfigurationStatus = domainConfigurationStatus.toString();
    }

    public UpdateDomainConfigurationRequest withDomainConfigurationStatus(DomainConfigurationStatus domainConfigurationStatus) {
        this.domainConfigurationStatus = domainConfigurationStatus.toString();
        return this;
    }

    public Boolean isRemoveAuthorizerConfig() {
        return this.removeAuthorizerConfig;
    }

    public Boolean getRemoveAuthorizerConfig() {
        return this.removeAuthorizerConfig;
    }

    public void setRemoveAuthorizerConfig(Boolean bool) {
        this.removeAuthorizerConfig = bool;
    }

    public UpdateDomainConfigurationRequest withRemoveAuthorizerConfig(Boolean bool) {
        this.removeAuthorizerConfig = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDomainConfigurationName() != null) {
            sb.append("domainConfigurationName: " + getDomainConfigurationName() + ",");
        }
        if (getAuthorizerConfig() != null) {
            sb.append("authorizerConfig: " + getAuthorizerConfig() + ",");
        }
        if (getDomainConfigurationStatus() != null) {
            sb.append("domainConfigurationStatus: " + getDomainConfigurationStatus() + ",");
        }
        if (getRemoveAuthorizerConfig() != null) {
            sb.append("removeAuthorizerConfig: " + getRemoveAuthorizerConfig());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getDomainConfigurationName() == null ? 0 : getDomainConfigurationName().hashCode()) + 31) * 31) + (getAuthorizerConfig() == null ? 0 : getAuthorizerConfig().hashCode())) * 31) + (getDomainConfigurationStatus() == null ? 0 : getDomainConfigurationStatus().hashCode())) * 31) + (getRemoveAuthorizerConfig() != null ? getRemoveAuthorizerConfig().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateDomainConfigurationRequest)) {
            return false;
        }
        UpdateDomainConfigurationRequest updateDomainConfigurationRequest = (UpdateDomainConfigurationRequest) obj;
        if ((updateDomainConfigurationRequest.getDomainConfigurationName() == null) ^ (getDomainConfigurationName() == null)) {
            return false;
        }
        if (updateDomainConfigurationRequest.getDomainConfigurationName() != null && !updateDomainConfigurationRequest.getDomainConfigurationName().equals(getDomainConfigurationName())) {
            return false;
        }
        if ((updateDomainConfigurationRequest.getAuthorizerConfig() == null) ^ (getAuthorizerConfig() == null)) {
            return false;
        }
        if (updateDomainConfigurationRequest.getAuthorizerConfig() != null && !updateDomainConfigurationRequest.getAuthorizerConfig().equals(getAuthorizerConfig())) {
            return false;
        }
        if ((updateDomainConfigurationRequest.getDomainConfigurationStatus() == null) ^ (getDomainConfigurationStatus() == null)) {
            return false;
        }
        if (updateDomainConfigurationRequest.getDomainConfigurationStatus() != null && !updateDomainConfigurationRequest.getDomainConfigurationStatus().equals(getDomainConfigurationStatus())) {
            return false;
        }
        if ((updateDomainConfigurationRequest.getRemoveAuthorizerConfig() == null) ^ (getRemoveAuthorizerConfig() == null)) {
            return false;
        }
        return updateDomainConfigurationRequest.getRemoveAuthorizerConfig() == null || updateDomainConfigurationRequest.getRemoveAuthorizerConfig().equals(getRemoveAuthorizerConfig());
    }
}
