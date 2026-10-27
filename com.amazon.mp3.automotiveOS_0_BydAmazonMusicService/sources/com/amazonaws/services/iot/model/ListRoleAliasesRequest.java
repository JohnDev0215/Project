package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListRoleAliasesRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String marker;
    private Integer pageSize;

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public ListRoleAliasesRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public ListRoleAliasesRequest withMarker(String str) {
        this.marker = str;
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

    public ListRoleAliasesRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPageSize() != null) {
            sb.append("pageSize: " + getPageSize() + ",");
        }
        if (getMarker() != null) {
            sb.append("marker: " + getMarker() + ",");
        }
        if (getAscendingOrder() != null) {
            sb.append("ascendingOrder: " + getAscendingOrder());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getPageSize() == null ? 0 : getPageSize().hashCode()) + 31) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getAscendingOrder() != null ? getAscendingOrder().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListRoleAliasesRequest)) {
            return false;
        }
        ListRoleAliasesRequest listRoleAliasesRequest = (ListRoleAliasesRequest) obj;
        if ((listRoleAliasesRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        if (listRoleAliasesRequest.getPageSize() != null && !listRoleAliasesRequest.getPageSize().equals(getPageSize())) {
            return false;
        }
        if ((listRoleAliasesRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listRoleAliasesRequest.getMarker() != null && !listRoleAliasesRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listRoleAliasesRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        return listRoleAliasesRequest.getAscendingOrder() == null || listRoleAliasesRequest.getAscendingOrder().equals(getAscendingOrder());
    }
}
