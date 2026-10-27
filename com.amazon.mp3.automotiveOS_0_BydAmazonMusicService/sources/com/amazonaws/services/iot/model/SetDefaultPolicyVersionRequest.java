package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class SetDefaultPolicyVersionRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;
    private String policyVersionId;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public SetDefaultPolicyVersionRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getPolicyVersionId() {
        return this.policyVersionId;
    }

    public void setPolicyVersionId(String str) {
        this.policyVersionId = str;
    }

    public SetDefaultPolicyVersionRequest withPolicyVersionId(String str) {
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
        if (obj == null || !(obj instanceof SetDefaultPolicyVersionRequest)) {
            return false;
        }
        SetDefaultPolicyVersionRequest setDefaultPolicyVersionRequest = (SetDefaultPolicyVersionRequest) obj;
        if ((setDefaultPolicyVersionRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (setDefaultPolicyVersionRequest.getPolicyName() != null && !setDefaultPolicyVersionRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((setDefaultPolicyVersionRequest.getPolicyVersionId() == null) ^ (getPolicyVersionId() == null)) {
            return false;
        }
        return setDefaultPolicyVersionRequest.getPolicyVersionId() == null || setDefaultPolicyVersionRequest.getPolicyVersionId().equals(getPolicyVersionId());
    }
}
