package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class PolicyVersionIdentifier implements Serializable {
    private String policyName;
    private String policyVersionId;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public PolicyVersionIdentifier withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getPolicyVersionId() {
        return this.policyVersionId;
    }

    public void setPolicyVersionId(String str) {
        this.policyVersionId = str;
    }

    public PolicyVersionIdentifier withPolicyVersionId(String str) {
        this.policyVersionId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicyName() != null) {
            sb.append("policyName: " + getPolicyName() + ",");
        }
        if (getPolicyVersionId() != null) {
            sb.append("policyVersionId: " + getPolicyVersionId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getPolicyVersionId() != null ? getPolicyVersionId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PolicyVersionIdentifier)) {
            return false;
        }
        PolicyVersionIdentifier policyVersionIdentifier = (PolicyVersionIdentifier) obj;
        if ((policyVersionIdentifier.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (policyVersionIdentifier.getPolicyName() != null && !policyVersionIdentifier.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((policyVersionIdentifier.getPolicyVersionId() == null) ^ (getPolicyVersionId() == null)) {
            return false;
        }
        return policyVersionIdentifier.getPolicyVersionId() == null || policyVersionIdentifier.getPolicyVersionId().equals(getPolicyVersionId());
    }
}
