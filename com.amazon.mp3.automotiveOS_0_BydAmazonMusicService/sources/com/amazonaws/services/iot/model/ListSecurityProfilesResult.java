package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListSecurityProfilesResult implements Serializable {
    private String nextToken;
    private List<SecurityProfileIdentifier> securityProfileIdentifiers;

    public List<SecurityProfileIdentifier> getSecurityProfileIdentifiers() {
        return this.securityProfileIdentifiers;
    }

    public void setSecurityProfileIdentifiers(Collection<SecurityProfileIdentifier> collection) {
        if (collection == null) {
            this.securityProfileIdentifiers = null;
        } else {
            this.securityProfileIdentifiers = new ArrayList(collection);
        }
    }

    public ListSecurityProfilesResult withSecurityProfileIdentifiers(SecurityProfileIdentifier... securityProfileIdentifierArr) {
        if (getSecurityProfileIdentifiers() == null) {
            this.securityProfileIdentifiers = new ArrayList(securityProfileIdentifierArr.length);
        }
        for (SecurityProfileIdentifier securityProfileIdentifier : securityProfileIdentifierArr) {
            this.securityProfileIdentifiers.add(securityProfileIdentifier);
        }
        return this;
    }

    public ListSecurityProfilesResult withSecurityProfileIdentifiers(Collection<SecurityProfileIdentifier> collection) {
        setSecurityProfileIdentifiers(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListSecurityProfilesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileIdentifiers() != null) {
            sb.append("securityProfileIdentifiers: " + getSecurityProfileIdentifiers() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getSecurityProfileIdentifiers() == null ? 0 : getSecurityProfileIdentifiers().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListSecurityProfilesResult)) {
            return false;
        }
        ListSecurityProfilesResult listSecurityProfilesResult = (ListSecurityProfilesResult) obj;
        if ((listSecurityProfilesResult.getSecurityProfileIdentifiers() == null) ^ (getSecurityProfileIdentifiers() == null)) {
            return false;
        }
        if (listSecurityProfilesResult.getSecurityProfileIdentifiers() != null && !listSecurityProfilesResult.getSecurityProfileIdentifiers().equals(getSecurityProfileIdentifiers())) {
            return false;
        }
        if ((listSecurityProfilesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listSecurityProfilesResult.getNextToken() == null || listSecurityProfilesResult.getNextToken().equals(getNextToken());
    }
}
