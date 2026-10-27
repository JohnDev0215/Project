package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AttachPrincipalPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;
    private String principal;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public AttachPrincipalPolicyRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(String str) {
        this.principal = str;
    }

    public AttachPrincipalPolicyRequest withPrincipal(String str) {
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
        if (obj == null || !(obj instanceof AttachPrincipalPolicyRequest)) {
            return false;
        }
        AttachPrincipalPolicyRequest attachPrincipalPolicyRequest = (AttachPrincipalPolicyRequest) obj;
        if ((attachPrincipalPolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (attachPrincipalPolicyRequest.getPolicyName() != null && !attachPrincipalPolicyRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((attachPrincipalPolicyRequest.getPrincipal() == null) ^ (getPrincipal() == null)) {
            return false;
        }
        return attachPrincipalPolicyRequest.getPrincipal() == null || attachPrincipalPolicyRequest.getPrincipal().equals(getPrincipal());
    }
}
