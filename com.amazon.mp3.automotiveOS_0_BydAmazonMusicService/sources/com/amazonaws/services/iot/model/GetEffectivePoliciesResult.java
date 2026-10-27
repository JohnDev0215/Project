package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetEffectivePoliciesResult implements Serializable {
    private List<EffectivePolicy> effectivePolicies;

    public List<EffectivePolicy> getEffectivePolicies() {
        return this.effectivePolicies;
    }

    public void setEffectivePolicies(Collection<EffectivePolicy> collection) {
        if (collection == null) {
            this.effectivePolicies = null;
        } else {
            this.effectivePolicies = new ArrayList(collection);
        }
    }

    public GetEffectivePoliciesResult withEffectivePolicies(EffectivePolicy... effectivePolicyArr) {
        if (getEffectivePolicies() == null) {
            this.effectivePolicies = new ArrayList(effectivePolicyArr.length);
        }
        for (EffectivePolicy effectivePolicy : effectivePolicyArr) {
            this.effectivePolicies.add(effectivePolicy);
        }
        return this;
    }

    public GetEffectivePoliciesResult withEffectivePolicies(Collection<EffectivePolicy> collection) {
        setEffectivePolicies(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getEffectivePolicies() != null) {
            sb.append("effectivePolicies: " + getEffectivePolicies());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getEffectivePolicies() == null ? 0 : getEffectivePolicies().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetEffectivePoliciesResult)) {
            return false;
        }
        GetEffectivePoliciesResult getEffectivePoliciesResult = (GetEffectivePoliciesResult) obj;
        if ((getEffectivePoliciesResult.getEffectivePolicies() == null) ^ (getEffectivePolicies() == null)) {
            return false;
        }
        return getEffectivePoliciesResult.getEffectivePolicies() == null || getEffectivePoliciesResult.getEffectivePolicies().equals(getEffectivePolicies());
    }
}
