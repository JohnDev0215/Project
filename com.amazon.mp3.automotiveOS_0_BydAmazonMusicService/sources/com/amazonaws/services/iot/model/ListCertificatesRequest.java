package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListCertificatesRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String marker;
    private Integer pageSize;

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public ListCertificatesRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public ListCertificatesRequest withMarker(String str) {
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

    public ListCertificatesRequest withAscendingOrder(Boolean bool) {
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
        if (obj == null || !(obj instanceof ListCertificatesRequest)) {
            return false;
        }
        ListCertificatesRequest listCertificatesRequest = (ListCertificatesRequest) obj;
        if ((listCertificatesRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        if (listCertificatesRequest.getPageSize() != null && !listCertificatesRequest.getPageSize().equals(getPageSize())) {
            return false;
        }
        if ((listCertificatesRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listCertificatesRequest.getMarker() != null && !listCertificatesRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listCertificatesRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        return listCertificatesRequest.getAscendingOrder() == null || listCertificatesRequest.getAscendingOrder().equals(getAscendingOrder());
    }
}
