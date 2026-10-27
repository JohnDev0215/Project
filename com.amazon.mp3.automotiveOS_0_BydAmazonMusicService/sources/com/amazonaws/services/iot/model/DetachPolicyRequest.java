package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DetachPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;
    private String target;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public DetachPolicyRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public DetachPolicyRequest withTarget(String str) {
        this.target = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicyName() != null) {
            sb.append("policyName: " + getPolicyName() + ",");
        }
        if (getTarget() != null) {
            sb.append("target: " + getTarget());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getTarget() != null ? getTarget().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DetachPolicyRequest)) {
            return false;
        }
        DetachPolicyRequest detachPolicyRequest = (DetachPolicyRequest) obj;
        if ((detachPolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (detachPolicyRequest.getPolicyName() != null && !detachPolicyRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((detachPolicyRequest.getTarget() == null) ^ (getTarget() == null)) {
            return false;
        }
        return detachPolicyRequest.getTarget() == null || detachPolicyRequest.getTarget().equals(getTarget());
    }
}
