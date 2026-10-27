package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListProvisioningTemplatesResult implements Serializable {
    private String nextToken;
    private List<ProvisioningTemplateSummary> templates;

    public List<ProvisioningTemplateSummary> getTemplates() {
        return this.templates;
    }

    public void setTemplates(Collection<ProvisioningTemplateSummary> collection) {
        if (collection == null) {
            this.templates = null;
        } else {
            this.templates = new ArrayList(collection);
        }
    }

    public ListProvisioningTemplatesResult withTemplates(ProvisioningTemplateSummary... provisioningTemplateSummaryArr) {
        if (getTemplates() == null) {
            this.templates = new ArrayList(provisioningTemplateSummaryArr.length);
        }
        for (ProvisioningTemplateSummary provisioningTemplateSummary : provisioningTemplateSummaryArr) {
            this.templates.add(provisioningTemplateSummary);
        }
        return this;
    }

    public ListProvisioningTemplatesResult withTemplates(Collection<ProvisioningTemplateSummary> collection) {
        setTemplates(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListProvisioningTemplatesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplates() != null) {
            sb.append("templates: " + getTemplates() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTemplates() == null ? 0 : getTemplates().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListProvisioningTemplatesResult)) {
            return false;
        }
        ListProvisioningTemplatesResult listProvisioningTemplatesResult = (ListProvisioningTemplatesResult) obj;
        if ((listProvisioningTemplatesResult.getTemplates() == null) ^ (getTemplates() == null)) {
            return false;
        }
        if (listProvisioningTemplatesResult.getTemplates() != null && !listProvisioningTemplatesResult.getTemplates().equals(getTemplates())) {
            return false;
        }
        if ((listProvisioningTemplatesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listProvisioningTemplatesResult.getNextToken() == null || listProvisioningTemplatesResult.getNextToken().equals(getNextToken());
    }
}
