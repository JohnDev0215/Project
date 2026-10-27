package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListSecurityProfilesForTargetResult implements Serializable {
    private String nextToken;
    private List<SecurityProfileTargetMapping> securityProfileTargetMappings;

    public List<SecurityProfileTargetMapping> getSecurityProfileTargetMappings() {
        return this.securityProfileTargetMappings;
    }

    public void setSecurityProfileTargetMappings(Collection<SecurityProfileTargetMapping> collection) {
        if (collection == null) {
            this.securityProfileTargetMappings = null;
        } else {
            this.securityProfileTargetMappings = new ArrayList(collection);
        }
    }

    public ListSecurityProfilesForTargetResult withSecurityProfileTargetMappings(SecurityProfileTargetMapping... securityProfileTargetMappingArr) {
        if (getSecurityProfileTargetMappings() == null) {
            this.securityProfileTargetMappings = new ArrayList(securityProfileTargetMappingArr.length);
        }
        for (SecurityProfileTargetMapping securityProfileTargetMapping : securityProfileTargetMappingArr) {
            this.securityProfileTargetMappings.add(securityProfileTargetMapping);
        }
        return this;
    }

    public ListSecurityProfilesForTargetResult withSecurityProfileTargetMappings(Collection<SecurityProfileTargetMapping> collection) {
        setSecurityProfileTargetMappings(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListSecurityProfilesForTargetResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileTargetMappings() != null) {
            sb.append("securityProfileTargetMappings: " + getSecurityProfileTargetMappings() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getSecurityProfileTargetMappings() == null ? 0 : getSecurityProfileTargetMappings().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListSecurityProfilesForTargetResult)) {
            return false;
        }
        ListSecurityProfilesForTargetResult listSecurityProfilesForTargetResult = (ListSecurityProfilesForTargetResult) obj;
        if ((listSecurityProfilesForTargetResult.getSecurityProfileTargetMappings() == null) ^ (getSecurityProfileTargetMappings() == null)) {
            return false;
        }
        if (listSecurityProfilesForTargetResult.getSecurityProfileTargetMappings() != null && !listSecurityProfilesForTargetResult.getSecurityProfileTargetMappings().equals(getSecurityProfileTargetMappings())) {
            return false;
        }
        if ((listSecurityProfilesForTargetResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listSecurityProfilesForTargetResult.getNextToken() == null || listSecurityProfilesForTargetResult.getNextToken().equals(getNextToken());
    }
}
