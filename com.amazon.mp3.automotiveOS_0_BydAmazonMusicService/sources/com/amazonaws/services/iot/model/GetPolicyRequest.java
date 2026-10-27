package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public GetPolicyRequest withPolicyName(String str) {
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
        if (obj == null || !(obj instanceof GetPolicyRequest)) {
            return false;
        }
        GetPolicyRequest getPolicyRequest = (GetPolicyRequest) obj;
        if ((getPolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        return getPolicyRequest.getPolicyName() == null || getPolicyRequest.getPolicyName().equals(getPolicyName());
    }
}
