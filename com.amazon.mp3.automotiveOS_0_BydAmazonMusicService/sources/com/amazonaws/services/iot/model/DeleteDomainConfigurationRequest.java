package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteDomainConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private String domainConfigurationName;

    public String getDomainConfigurationName() {
        return this.domainConfigurationName;
    }

    public void setDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
    }

    public DeleteDomainConfigurationRequest withDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDomainConfigurationName() != null) {
            sb.append("domainConfigurationName: " + getDomainConfigurationName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getDomainConfigurationName() == null ? 0 : getDomainConfigurationName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteDomainConfigurationRequest)) {
            return false;
        }
        DeleteDomainConfigurationRequest deleteDomainConfigurationRequest = (DeleteDomainConfigurationRequest) obj;
        if ((deleteDomainConfigurationRequest.getDomainConfigurationName() == null) ^ (getDomainConfigurationName() == null)) {
            return false;
        }
        return deleteDomainConfigurationRequest.getDomainConfigurationName() == null || deleteDomainConfigurationRequest.getDomainConfigurationName().equals(getDomainConfigurationName());
    }
}
