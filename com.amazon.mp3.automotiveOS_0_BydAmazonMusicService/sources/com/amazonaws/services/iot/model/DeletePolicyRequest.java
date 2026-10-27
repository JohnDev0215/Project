package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeletePolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public DeletePolicyRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicyName() != null) {
            sb.append("policyName: " + getPolicyName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getPolicyName() == null ? 0 : getPolicyName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeletePolicyRequest)) {
            return false;
        }
        DeletePolicyRequest deletePolicyRequest = (DeletePolicyRequest) obj;
        if ((deletePolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        return deletePolicyRequest.getPolicyName() == null || deletePolicyRequest.getPolicyName().equals(getPolicyName());
    }
}
