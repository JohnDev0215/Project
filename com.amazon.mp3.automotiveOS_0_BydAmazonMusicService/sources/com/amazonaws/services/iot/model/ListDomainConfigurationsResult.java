package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListDomainConfigurationsResult implements Serializable {
    private List<DomainConfigurationSummary> domainConfigurations;
    private String nextMarker;

    public List<DomainConfigurationSummary> getDomainConfigurations() {
        return this.domainConfigurations;
    }

    public void setDomainConfigurations(Collection<DomainConfigurationSummary> collection) {
        if (collection == null) {
            this.domainConfigurations = null;
        } else {
            this.domainConfigurations = new ArrayList(collection);
        }
    }

    public ListDomainConfigurationsResult withDomainConfigurations(DomainConfigurationSummary... domainConfigurationSummaryArr) {
        if (getDomainConfigurations() == null) {
            this.domainConfigurations = new ArrayList(domainConfigurationSummaryArr.length);
        }
        for (DomainConfigurationSummary domainConfigurationSummary : domainConfigurationSummaryArr) {
            this.domainConfigurations.add(domainConfigurationSummary);
        }
        return this;
    }

    public ListDomainConfigurationsResult withDomainConfigurations(Collection<DomainConfigurationSummary> collection) {
        setDomainConfigurations(collection);
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public ListDomainConfigurationsResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDomainConfigurations() != null) {
            sb.append("domainConfigurations: " + getDomainConfigurations() + ",");
        }
        if (getNextMarker() != null) {
            sb.append("nextMarker: " + getNextMarker());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getDomainConfigurations() == null ? 0 : getDomainConfigurations().hashCode()) + 31) * 31) + (getNextMarker() != null ? getNextMarker().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListDomainConfigurationsResult)) {
            return false;
        }
        ListDomainConfigurationsResult listDomainConfigurationsResult = (ListDomainConfigurationsResult) obj;
        if ((listDomainConfigurationsResult.getDomainConfigurations() == null) ^ (getDomainConfigurations() == null)) {
            return false;
        }
        if (listDomainConfigurationsResult.getDomainConfigurations() != null && !listDomainConfigurationsResult.getDomainConfigurations().equals(getDomainConfigurations())) {
            return false;
        }
        if ((listDomainConfigurationsResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listDomainConfigurationsResult.getNextMarker() == null || listDomainConfigurationsResult.getNextMarker().equals(getNextMarker());
    }
}
