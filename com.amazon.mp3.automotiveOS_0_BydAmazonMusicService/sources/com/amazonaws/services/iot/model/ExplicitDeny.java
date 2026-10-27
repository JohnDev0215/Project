package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ExplicitDeny implements Serializable {
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

    public ExplicitDeny withPolicies(Policy... policyArr) {
        if (getPolicies() == null) {
            this.policies = new ArrayList(policyArr.length);
        }
        for (Policy policy : policyArr) {
            this.policies.add(policy);
        }
        return this;
    }

    public ExplicitDeny withPolicies(Collection<Policy> collection) {
        setPolicies(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicies() != null) {
            sb.append("policies: " + getPolicies());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getPolicies() == null ? 0 : getPolicies().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ExplicitDeny)) {
            return false;
        }
        ExplicitDeny explicitDeny = (ExplicitDeny) obj;
        if ((explicitDeny.getPolicies() == null) ^ (getPolicies() == null)) {
            return false;
        }
        return explicitDeny.getPolicies() == null || explicitDeny.getPolicies().equals(getPolicies());
    }
}
