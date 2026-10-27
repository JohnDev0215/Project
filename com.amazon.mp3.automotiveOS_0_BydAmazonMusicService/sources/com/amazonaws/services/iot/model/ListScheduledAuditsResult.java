package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListScheduledAuditsResult implements Serializable {
    private String nextToken;
    private List<ScheduledAuditMetadata> scheduledAudits;

    public List<ScheduledAuditMetadata> getScheduledAudits() {
        return this.scheduledAudits;
    }

    public void setScheduledAudits(Collection<ScheduledAuditMetadata> collection) {
        if (collection == null) {
            this.scheduledAudits = null;
        } else {
            this.scheduledAudits = new ArrayList(collection);
        }
    }

    public ListScheduledAuditsResult withScheduledAudits(ScheduledAuditMetadata... scheduledAuditMetadataArr) {
        if (getScheduledAudits() == null) {
            this.scheduledAudits = new ArrayList(scheduledAuditMetadataArr.length);
        }
        for (ScheduledAuditMetadata scheduledAuditMetadata : scheduledAuditMetadataArr) {
            this.scheduledAudits.add(scheduledAuditMetadata);
        }
        return this;
    }

    public ListScheduledAuditsResult withScheduledAudits(Collection<ScheduledAuditMetadata> collection) {
        setScheduledAudits(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListScheduledAuditsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getScheduledAudits() != null) {
            sb.append("scheduledAudits: " + getScheduledAudits() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getScheduledAudits() == null ? 0 : getScheduledAudits().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListScheduledAuditsResult)) {
            return false;
        }
        ListScheduledAuditsResult listScheduledAuditsResult = (ListScheduledAuditsResult) obj;
        if ((listScheduledAuditsResult.getScheduledAudits() == null) ^ (getScheduledAudits() == null)) {
            return false;
        }
        if (listScheduledAuditsResult.getScheduledAudits() != null && !listScheduledAuditsResult.getScheduledAudits().equals(getScheduledAudits())) {
            return false;
        }
        if ((listScheduledAuditsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listScheduledAuditsResult.getNextToken() == null || listScheduledAuditsResult.getNextToken().equals(getNextToken());
    }
}
