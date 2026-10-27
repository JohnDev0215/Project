package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class Policy implements Serializable {
    private String policyArn;
    private String policyName;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public Policy withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getPolicyArn() {
        return this.policyArn;
    }

    public void setPolicyArn(String str) {
        this.policyArn = str;
    }

    public Policy withPolicyArn(String str) {
        this.policyArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicyName() != null) {
            sb.append("policyName: " + getPolicyName() + ",");
        }
        if (getPolicyArn() != null) {
            sb.append("policyArn: " + getPolicyArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getPolicyArn() != null ? getPolicyArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Policy)) {
            return false;
        }
        Policy policy = (Policy) obj;
        if ((policy.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (policy.getPolicyName() != null && !policy.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((policy.getPolicyArn() == null) ^ (getPolicyArn() == null)) {
            return false;
        }
        return policy.getPolicyArn() == null || policy.getPolicyArn().equals(getPolicyArn());
    }
}
