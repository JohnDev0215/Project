package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuthorizersRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String marker;
    private Integer pageSize;
    private String status;

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public ListAuthorizersRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public ListAuthorizersRequest withMarker(String str) {
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

    public ListAuthorizersRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public ListAuthorizersRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
    }

    public ListAuthorizersRequest withStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
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
            sb.append("ascendingOrder: " + getAscendingOrder() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getPageSize() == null ? 0 : getPageSize().hashCode()) + 31) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getAscendingOrder() == null ? 0 : getAscendingOrder().hashCode())) * 31) + (getStatus() != null ? getStatus().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuthorizersRequest)) {
            return false;
        }
        ListAuthorizersRequest listAuthorizersRequest = (ListAuthorizersRequest) obj;
        if ((listAuthorizersRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        if (listAuthorizersRequest.getPageSize() != null && !listAuthorizersRequest.getPageSize().equals(getPageSize())) {
            return false;
        }
        if ((listAuthorizersRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listAuthorizersRequest.getMarker() != null && !listAuthorizersRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listAuthorizersRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        if (listAuthorizersRequest.getAscendingOrder() != null && !listAuthorizersRequest.getAscendingOrder().equals(getAscendingOrder())) {
            return false;
        }
        if ((listAuthorizersRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return listAuthorizersRequest.getStatus() == null || listAuthorizersRequest.getStatus().equals(getStatus());
    }
}
