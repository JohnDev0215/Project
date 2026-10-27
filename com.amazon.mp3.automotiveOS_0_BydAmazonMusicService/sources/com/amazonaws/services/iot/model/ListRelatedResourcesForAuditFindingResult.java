package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListRelatedResourcesForAuditFindingResult implements Serializable {
    private String nextToken;
    private List<RelatedResource> relatedResources;

    public List<RelatedResource> getRelatedResources() {
        return this.relatedResources;
    }

    public void setRelatedResources(Collection<RelatedResource> collection) {
        if (collection == null) {
            this.relatedResources = null;
        } else {
            this.relatedResources = new ArrayList(collection);
        }
    }

    public ListRelatedResourcesForAuditFindingResult withRelatedResources(RelatedResource... relatedResourceArr) {
        if (getRelatedResources() == null) {
            this.relatedResources = new ArrayList(relatedResourceArr.length);
        }
        for (RelatedResource relatedResource : relatedResourceArr) {
            this.relatedResources.add(relatedResource);
        }
        return this;
    }

    public ListRelatedResourcesForAuditFindingResult withRelatedResources(Collection<RelatedResource> collection) {
        setRelatedResources(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListRelatedResourcesForAuditFindingResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRelatedResources() != null) {
            sb.append("relatedResources: " + getRelatedResources() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRelatedResources() == null ? 0 : getRelatedResources().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListRelatedResourcesForAuditFindingResult)) {
            return false;
        }
        ListRelatedResourcesForAuditFindingResult listRelatedResourcesForAuditFindingResult = (ListRelatedResourcesForAuditFindingResult) obj;
        if ((listRelatedResourcesForAuditFindingResult.getRelatedResources() == null) ^ (getRelatedResources() == null)) {
            return false;
        }
        if (listRelatedResourcesForAuditFindingResult.getRelatedResources() != null && !listRelatedResourcesForAuditFindingResult.getRelatedResources().equals(getRelatedResources())) {
            return false;
        }
        if ((listRelatedResourcesForAuditFindingResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listRelatedResourcesForAuditFindingResult.getNextToken() == null || listRelatedResourcesForAuditFindingResult.getNextToken().equals(getNextToken());
    }
}
