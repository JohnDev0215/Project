package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDomainConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private String domainConfigurationName;

    public String getDomainConfigurationName() {
        return this.domainConfigurationName;
    }

    public void setDomainConfigurationName(String str) {
        this.domainConfigurationName = str;
    }

    public DescribeDomainConfigurationRequest withDomainConfigurationName(String str) {
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
        if (obj == null || !(obj instanceof DescribeDomainConfigurationRequest)) {
            return false;
        }
        DescribeDomainConfigurationRequest describeDomainConfigurationRequest = (DescribeDomainConfigurationRequest) obj;
        if ((describeDomainConfigurationRequest.getDomainConfigurationName() == null) ^ (getDomainConfigurationName() == null)) {
            return false;
        }
        return describeDomainConfigurationRequest.getDomainConfigurationName() == null || describeDomainConfigurationRequest.getDomainConfigurationName().equals(getDomainConfigurationName());
    }
}
