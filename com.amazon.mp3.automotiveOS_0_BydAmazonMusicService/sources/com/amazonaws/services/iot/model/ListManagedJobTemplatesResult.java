package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListManagedJobTemplatesResult implements Serializable {
    private List<ManagedJobTemplateSummary> managedJobTemplates;
    private String nextToken;

    public List<ManagedJobTemplateSummary> getManagedJobTemplates() {
        return this.managedJobTemplates;
    }

    public void setManagedJobTemplates(Collection<ManagedJobTemplateSummary> collection) {
        if (collection == null) {
            this.managedJobTemplates = null;
        } else {
            this.managedJobTemplates = new ArrayList(collection);
        }
    }

    public ListManagedJobTemplatesResult withManagedJobTemplates(ManagedJobTemplateSummary... managedJobTemplateSummaryArr) {
        if (getManagedJobTemplates() == null) {
            this.managedJobTemplates = new ArrayList(managedJobTemplateSummaryArr.length);
        }
        for (ManagedJobTemplateSummary managedJobTemplateSummary : managedJobTemplateSummaryArr) {
            this.managedJobTemplates.add(managedJobTemplateSummary);
        }
        return this;
    }

    public ListManagedJobTemplatesResult withManagedJobTemplates(Collection<ManagedJobTemplateSummary> collection) {
        setManagedJobTemplates(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListManagedJobTemplatesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getManagedJobTemplates() != null) {
            sb.append("managedJobTemplates: " + getManagedJobTemplates() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getManagedJobTemplates() == null ? 0 : getManagedJobTemplates().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListManagedJobTemplatesResult)) {
            return false;
        }
        ListManagedJobTemplatesResult listManagedJobTemplatesResult = (ListManagedJobTemplatesResult) obj;
        if ((listManagedJobTemplatesResult.getManagedJobTemplates() == null) ^ (getManagedJobTemplates() == null)) {
            return false;
        }
        if (listManagedJobTemplatesResult.getManagedJobTemplates() != null && !listManagedJobTemplatesResult.getManagedJobTemplates().equals(getManagedJobTemplates())) {
            return false;
        }
        if ((listManagedJobTemplatesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listManagedJobTemplatesResult.getNextToken() == null || listManagedJobTemplatesResult.getNextToken().equals(getNextToken());
    }
}
