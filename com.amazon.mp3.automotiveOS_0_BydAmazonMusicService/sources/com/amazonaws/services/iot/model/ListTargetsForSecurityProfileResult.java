package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListTargetsForSecurityProfileResult implements Serializable {
    private String nextToken;
    private List<SecurityProfileTarget> securityProfileTargets;

    public List<SecurityProfileTarget> getSecurityProfileTargets() {
        return this.securityProfileTargets;
    }

    public void setSecurityProfileTargets(Collection<SecurityProfileTarget> collection) {
        if (collection == null) {
            this.securityProfileTargets = null;
        } else {
            this.securityProfileTargets = new ArrayList(collection);
        }
    }

    public ListTargetsForSecurityProfileResult withSecurityProfileTargets(SecurityProfileTarget... securityProfileTargetArr) {
        if (getSecurityProfileTargets() == null) {
            this.securityProfileTargets = new ArrayList(securityProfileTargetArr.length);
        }
        for (SecurityProfileTarget securityProfileTarget : securityProfileTargetArr) {
            this.securityProfileTargets.add(securityProfileTarget);
        }
        return this;
    }

    public ListTargetsForSecurityProfileResult withSecurityProfileTargets(Collection<SecurityProfileTarget> collection) {
        setSecurityProfileTargets(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListTargetsForSecurityProfileResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileTargets() != null) {
            sb.append("securityProfileTargets: " + getSecurityProfileTargets() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getSecurityProfileTargets() == null ? 0 : getSecurityProfileTargets().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTargetsForSecurityProfileResult)) {
            return false;
        }
        ListTargetsForSecurityProfileResult listTargetsForSecurityProfileResult = (ListTargetsForSecurityProfileResult) obj;
        if ((listTargetsForSecurityProfileResult.getSecurityProfileTargets() == null) ^ (getSecurityProfileTargets() == null)) {
            return false;
        }
        if (listTargetsForSecurityProfileResult.getSecurityProfileTargets() != null && !listTargetsForSecurityProfileResult.getSecurityProfileTargets().equals(getSecurityProfileTargets())) {
            return false;
        }
        if ((listTargetsForSecurityProfileResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listTargetsForSecurityProfileResult.getNextToken() == null || listTargetsForSecurityProfileResult.getNextToken().equals(getNextToken());
    }
}
