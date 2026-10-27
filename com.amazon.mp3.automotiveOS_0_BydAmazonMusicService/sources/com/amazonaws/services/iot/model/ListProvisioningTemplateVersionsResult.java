package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListProvisioningTemplateVersionsResult implements Serializable {
    private String nextToken;
    private List<ProvisioningTemplateVersionSummary> versions;

    public List<ProvisioningTemplateVersionSummary> getVersions() {
        return this.versions;
    }

    public void setVersions(Collection<ProvisioningTemplateVersionSummary> collection) {
        if (collection == null) {
            this.versions = null;
        } else {
            this.versions = new ArrayList(collection);
        }
    }

    public ListProvisioningTemplateVersionsResult withVersions(ProvisioningTemplateVersionSummary... provisioningTemplateVersionSummaryArr) {
        if (getVersions() == null) {
            this.versions = new ArrayList(provisioningTemplateVersionSummaryArr.length);
        }
        for (ProvisioningTemplateVersionSummary provisioningTemplateVersionSummary : provisioningTemplateVersionSummaryArr) {
            this.versions.add(provisioningTemplateVersionSummary);
        }
        return this;
    }

    public ListProvisioningTemplateVersionsResult withVersions(Collection<ProvisioningTemplateVersionSummary> collection) {
        setVersions(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListProvisioningTemplateVersionsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getVersions() != null) {
            sb.append("versions: " + getVersions() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getVersions() == null ? 0 : getVersions().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListProvisioningTemplateVersionsResult)) {
            return false;
        }
        ListProvisioningTemplateVersionsResult listProvisioningTemplateVersionsResult = (ListProvisioningTemplateVersionsResult) obj;
        if ((listProvisioningTemplateVersionsResult.getVersions() == null) ^ (getVersions() == null)) {
            return false;
        }
        if (listProvisioningTemplateVersionsResult.getVersions() != null && !listProvisioningTemplateVersionsResult.getVersions().equals(getVersions())) {
            return false;
        }
        if ((listProvisioningTemplateVersionsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listProvisioningTemplateVersionsResult.getNextToken() == null || listProvisioningTemplateVersionsResult.getNextToken().equals(getNextToken());
    }
}
