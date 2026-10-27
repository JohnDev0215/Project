package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListActiveViolationsResult implements Serializable {
    private List<ActiveViolation> activeViolations;
    private String nextToken;

    public List<ActiveViolation> getActiveViolations() {
        return this.activeViolations;
    }

    public void setActiveViolations(Collection<ActiveViolation> collection) {
        if (collection == null) {
            this.activeViolations = null;
        } else {
            this.activeViolations = new ArrayList(collection);
        }
    }

    public ListActiveViolationsResult withActiveViolations(ActiveViolation... activeViolationArr) {
        if (getActiveViolations() == null) {
            this.activeViolations = new ArrayList(activeViolationArr.length);
        }
        for (ActiveViolation activeViolation : activeViolationArr) {
            this.activeViolations.add(activeViolation);
        }
        return this;
    }

    public ListActiveViolationsResult withActiveViolations(Collection<ActiveViolation> collection) {
        setActiveViolations(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListActiveViolationsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActiveViolations() != null) {
            sb.append("activeViolations: " + getActiveViolations() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getActiveViolations() == null ? 0 : getActiveViolations().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListActiveViolationsResult)) {
            return false;
        }
        ListActiveViolationsResult listActiveViolationsResult = (ListActiveViolationsResult) obj;
        if ((listActiveViolationsResult.getActiveViolations() == null) ^ (getActiveViolations() == null)) {
            return false;
        }
        if (listActiveViolationsResult.getActiveViolations() != null && !listActiveViolationsResult.getActiveViolations().equals(getActiveViolations())) {
            return false;
        }
        if ((listActiveViolationsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listActiveViolationsResult.getNextToken() == null || listActiveViolationsResult.getNextToken().equals(getNextToken());
    }
}
