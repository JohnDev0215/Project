package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListPrincipalPoliciesResult implements Serializable {
    private String nextMarker;
    private List<Policy> policies;

    public List<Policy> getPolicies() {
        return this.policies;
    }

    public void setPolicies(Collection<Policy> collection) {
        if (collection == null) {
            this.policies = null;
        } else {
            this.policies = new ArrayList(collection);
        }
    }

    public ListPrincipalPoliciesResult withPolicies(Policy... policyArr) {
        if (getPolicies() == null) {
            this.policies = new ArrayList(policyArr.length);
        }
        for (Policy policy : policyArr) {
            this.policies.add(policy);
        }
        return this;
    }

    public ListPrincipalPoliciesResult withPolicies(Collection<Policy> collection) {
        setPolicies(collection);
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public ListPrincipalPoliciesResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicies() != null) {
            sb.append("policies: " + getPolicies() + ",");
        }
        if (getNextMarker() != null) {
            sb.append("nextMarker: " + getNextMarker());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getPolicies() == null ? 0 : getPolicies().hashCode()) + 31) * 31) + (getNextMarker() != null ? getNextMarker().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPrincipalPoliciesResult)) {
            return false;
        }
        ListPrincipalPoliciesResult listPrincipalPoliciesResult = (ListPrincipalPoliciesResult) obj;
        if ((listPrincipalPoliciesResult.getPolicies() == null) ^ (getPolicies() == null)) {
            return false;
        }
        if (listPrincipalPoliciesResult.getPolicies() != null && !listPrincipalPoliciesResult.getPolicies().equals(getPolicies())) {
            return false;
        }
        if ((listPrincipalPoliciesResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listPrincipalPoliciesResult.getNextMarker() == null || listPrincipalPoliciesResult.getNextMarker().equals(getNextMarker());
    }
}
