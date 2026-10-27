package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetPolicyVersionRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;
    private String policyVersionId;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public GetPolicyVersionRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getPolicyVersionId() {
        return this.policyVersionId;
    }

    public void setPolicyVersionId(String str) {
        this.policyVersionId = str;
    }

    public GetPolicyVersionRequest withPolicyVersionId(String str) {
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
        if (obj == null || !(obj instanceof GetPolicyVersionRequest)) {
            return false;
        }
        GetPolicyVersionRequest getPolicyVersionRequest = (GetPolicyVersionRequest) obj;
        if ((getPolicyVersionRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (getPolicyVersionRequest.getPolicyName() != null && !getPolicyVersionRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((getPolicyVersionRequest.getPolicyVersionId() == null) ^ (getPolicyVersionId() == null)) {
            return false;
        }
        return getPolicyVersionRequest.getPolicyVersionId() == null || getPolicyVersionRequest.getPolicyVersionId().equals(getPolicyVersionId());
    }
}
