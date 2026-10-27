package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListMitigationActionsResult implements Serializable {
    private List<MitigationActionIdentifier> actionIdentifiers;
    private String nextToken;

    public List<MitigationActionIdentifier> getActionIdentifiers() {
        return this.actionIdentifiers;
    }

    public void setActionIdentifiers(Collection<MitigationActionIdentifier> collection) {
        if (collection == null) {
            this.actionIdentifiers = null;
        } else {
            this.actionIdentifiers = new ArrayList(collection);
        }
    }

    public ListMitigationActionsResult withActionIdentifiers(MitigationActionIdentifier... mitigationActionIdentifierArr) {
        if (getActionIdentifiers() == null) {
            this.actionIdentifiers = new ArrayList(mitigationActionIdentifierArr.length);
        }
        for (MitigationActionIdentifier mitigationActionIdentifier : mitigationActionIdentifierArr) {
            this.actionIdentifiers.add(mitigationActionIdentifier);
        }
        return this;
    }

    public ListMitigationActionsResult withActionIdentifiers(Collection<MitigationActionIdentifier> collection) {
        setActionIdentifiers(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListMitigationActionsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionIdentifiers() != null) {
            sb.append("actionIdentifiers: " + getActionIdentifiers() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getActionIdentifiers() == null ? 0 : getActionIdentifiers().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListMitigationActionsResult)) {
            return false;
        }
        ListMitigationActionsResult listMitigationActionsResult = (ListMitigationActionsResult) obj;
        if ((listMitigationActionsResult.getActionIdentifiers() == null) ^ (getActionIdentifiers() == null)) {
            return false;
        }
        if (listMitigationActionsResult.getActionIdentifiers() != null && !listMitigationActionsResult.getActionIdentifiers().equals(getActionIdentifiers())) {
            return false;
        }
        if ((listMitigationActionsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listMitigationActionsResult.getNextToken() == null || listMitigationActionsResult.getNextToken().equals(getNextToken());
    }
}
