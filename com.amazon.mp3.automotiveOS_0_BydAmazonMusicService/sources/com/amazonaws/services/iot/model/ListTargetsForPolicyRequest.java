package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListTargetsForPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String marker;
    private Integer pageSize;
    private String policyName;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public ListTargetsForPolicyRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public ListTargetsForPolicyRequest withMarker(String str) {
        this.marker = str;
        return this;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public ListTargetsForPolicyRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicyName() != null) {
            sb.append("policyName: " + getPolicyName() + ",");
        }
        if (getMarker() != null) {
            sb.append("marker: " + getMarker() + ",");
        }
        if (getPageSize() != null) {
            sb.append("pageSize: " + getPageSize());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getPageSize() != null ? getPageSize().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTargetsForPolicyRequest)) {
            return false;
        }
        ListTargetsForPolicyRequest listTargetsForPolicyRequest = (ListTargetsForPolicyRequest) obj;
        if ((listTargetsForPolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (listTargetsForPolicyRequest.getPolicyName() != null && !listTargetsForPolicyRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((listTargetsForPolicyRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listTargetsForPolicyRequest.getMarker() != null && !listTargetsForPolicyRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listTargetsForPolicyRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        return listTargetsForPolicyRequest.getPageSize() == null || listTargetsForPolicyRequest.getPageSize().equals(getPageSize());
    }
}
