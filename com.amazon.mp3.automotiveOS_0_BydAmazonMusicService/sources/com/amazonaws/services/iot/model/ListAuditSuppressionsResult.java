package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditSuppressionsResult implements Serializable {
    private String nextToken;
    private List<AuditSuppression> suppressions;

    public List<AuditSuppression> getSuppressions() {
        return this.suppressions;
    }

    public void setSuppressions(Collection<AuditSuppression> collection) {
        if (collection == null) {
            this.suppressions = null;
        } else {
            this.suppressions = new ArrayList(collection);
        }
    }

    public ListAuditSuppressionsResult withSuppressions(AuditSuppression... auditSuppressionArr) {
        if (getSuppressions() == null) {
            this.suppressions = new ArrayList(auditSuppressionArr.length);
        }
        for (AuditSuppression auditSuppression : auditSuppressionArr) {
            this.suppressions.add(auditSuppression);
        }
        return this;
    }

    public ListAuditSuppressionsResult withSuppressions(Collection<AuditSuppression> collection) {
        setSuppressions(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListAuditSuppressionsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSuppressions() != null) {
            sb.append("suppressions: " + getSuppressions() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getSuppressions() == null ? 0 : getSuppressions().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditSuppressionsResult)) {
            return false;
        }
        ListAuditSuppressionsResult listAuditSuppressionsResult = (ListAuditSuppressionsResult) obj;
        if ((listAuditSuppressionsResult.getSuppressions() == null) ^ (getSuppressions() == null)) {
            return false;
        }
        if (listAuditSuppressionsResult.getSuppressions() != null && !listAuditSuppressionsResult.getSuppressions().equals(getSuppressions())) {
            return false;
        }
        if ((listAuditSuppressionsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listAuditSuppressionsResult.getNextToken() == null || listAuditSuppressionsResult.getNextToken().equals(getNextToken());
    }
}
