package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DetachPrincipalPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;
    private String principal;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public DetachPrincipalPolicyRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(String str) {
        this.principal = str;
    }

    public DetachPrincipalPolicyRequest withPrincipal(String str) {
        this.principal = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicyName() != null) {
            sb.append("policyName: " + getPolicyName() + ",");
        }
        if (getPrincipal() != null) {
            sb.append("principal: " + getPrincipal());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getPrincipal() != null ? getPrincipal().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DetachPrincipalPolicyRequest)) {
            return false;
        }
        DetachPrincipalPolicyRequest detachPrincipalPolicyRequest = (DetachPrincipalPolicyRequest) obj;
        if ((detachPrincipalPolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (detachPrincipalPolicyRequest.getPolicyName() != null && !detachPrincipalPolicyRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((detachPrincipalPolicyRequest.getPrincipal() == null) ^ (getPrincipal() == null)) {
            return false;
        }
        return detachPrincipalPolicyRequest.getPrincipal() == null || detachPrincipalPolicyRequest.getPrincipal().equals(getPrincipal());
    }
}
