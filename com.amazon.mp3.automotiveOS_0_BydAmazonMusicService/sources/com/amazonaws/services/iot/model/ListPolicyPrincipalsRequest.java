package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListPolicyPrincipalsRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String marker;
    private Integer pageSize;
    private String policyName;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public ListPolicyPrincipalsRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public ListPolicyPrincipalsRequest withMarker(String str) {
        this.marker = str;
        return this;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public ListPolicyPrincipalsRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public Boolean isAscendingOrder() {
        return this.ascendingOrder;
    }

    public Boolean getAscendingOrder() {
        return this.ascendingOrder;
    }

    public void setAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
    }

    public ListPolicyPrincipalsRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
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
            sb.append("pageSize: " + getPageSize() + ",");
        }
        if (getAscendingOrder() != null) {
            sb.append("ascendingOrder: " + getAscendingOrder());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getPageSize() == null ? 0 : getPageSize().hashCode())) * 31) + (getAscendingOrder() != null ? getAscendingOrder().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPolicyPrincipalsRequest)) {
            return false;
        }
        ListPolicyPrincipalsRequest listPolicyPrincipalsRequest = (ListPolicyPrincipalsRequest) obj;
        if ((listPolicyPrincipalsRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (listPolicyPrincipalsRequest.getPolicyName() != null && !listPolicyPrincipalsRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((listPolicyPrincipalsRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listPolicyPrincipalsRequest.getMarker() != null && !listPolicyPrincipalsRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listPolicyPrincipalsRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        if (listPolicyPrincipalsRequest.getPageSize() != null && !listPolicyPrincipalsRequest.getPageSize().equals(getPageSize())) {
            return false;
        }
        if ((listPolicyPrincipalsRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        return listPolicyPrincipalsRequest.getAscendingOrder() == null || listPolicyPrincipalsRequest.getAscendingOrder().equals(getAscendingOrder());
    }
}
