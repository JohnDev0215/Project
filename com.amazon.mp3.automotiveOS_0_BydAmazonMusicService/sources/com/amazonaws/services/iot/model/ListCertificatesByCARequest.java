package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListCertificatesByCARequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String caCertificateId;
    private String marker;
    private Integer pageSize;

    public String getCaCertificateId() {
        return this.caCertificateId;
    }

    public void setCaCertificateId(String str) {
        this.caCertificateId = str;
    }

    public ListCertificatesByCARequest withCaCertificateId(String str) {
        this.caCertificateId = str;
        return this;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public ListCertificatesByCARequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public ListCertificatesByCARequest withMarker(String str) {
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

    public ListCertificatesByCARequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCaCertificateId() != null) {
            sb.append("caCertificateId: " + getCaCertificateId() + ",");
        }
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
        return (((((((getCaCertificateId() == null ? 0 : getCaCertificateId().hashCode()) + 31) * 31) + (getPageSize() == null ? 0 : getPageSize().hashCode())) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getAscendingOrder() != null ? getAscendingOrder().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListCertificatesByCARequest)) {
            return false;
        }
        ListCertificatesByCARequest listCertificatesByCARequest = (ListCertificatesByCARequest) obj;
        if ((listCertificatesByCARequest.getCaCertificateId() == null) ^ (getCaCertificateId() == null)) {
            return false;
        }
        if (listCertificatesByCARequest.getCaCertificateId() != null && !listCertificatesByCARequest.getCaCertificateId().equals(getCaCertificateId())) {
            return false;
        }
        if ((listCertificatesByCARequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        if (listCertificatesByCARequest.getPageSize() != null && !listCertificatesByCARequest.getPageSize().equals(getPageSize())) {
            return false;
        }
        if ((listCertificatesByCARequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listCertificatesByCARequest.getMarker() != null && !listCertificatesByCARequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listCertificatesByCARequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        return listCertificatesByCARequest.getAscendingOrder() == null || listCertificatesByCARequest.getAscendingOrder().equals(getAscendingOrder());
    }
}
