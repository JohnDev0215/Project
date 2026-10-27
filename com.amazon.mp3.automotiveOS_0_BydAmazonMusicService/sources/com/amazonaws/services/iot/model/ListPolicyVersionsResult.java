package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListPolicyVersionsResult implements Serializable {
    private List<PolicyVersion> policyVersions;

    public List<PolicyVersion> getPolicyVersions() {
        return this.policyVersions;
    }

    public void setPolicyVersions(Collection<PolicyVersion> collection) {
        if (collection == null) {
            this.policyVersions = null;
        } else {
            this.policyVersions = new ArrayList(collection);
        }
    }

    public ListPolicyVersionsResult withPolicyVersions(PolicyVersion... policyVersionArr) {
        if (getPolicyVersions() == null) {
            this.policyVersions = new ArrayList(policyVersionArr.length);
        }
        for (PolicyVersion policyVersion : policyVersionArr) {
            this.policyVersions.add(policyVersion);
        }
        return this;
    }

    public ListPolicyVersionsResult withPolicyVersions(Collection<PolicyVersion> collection) {
        setPolicyVersions(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicyVersions() != null) {
            sb.append("policyVersions: " + getPolicyVersions());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getPolicyVersions() == null ? 0 : getPolicyVersions().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPolicyVersionsResult)) {
            return false;
        }
        ListPolicyVersionsResult listPolicyVersionsResult = (ListPolicyVersionsResult) obj;
        if ((listPolicyVersionsResult.getPolicyVersions() == null) ^ (getPolicyVersions() == null)) {
            return false;
        }
        return listPolicyVersionsResult.getPolicyVersions() == null || listPolicyVersionsResult.getPolicyVersions().equals(getPolicyVersions());
    }
}
