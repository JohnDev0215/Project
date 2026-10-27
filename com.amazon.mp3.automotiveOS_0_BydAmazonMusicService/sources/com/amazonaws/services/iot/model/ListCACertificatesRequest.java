package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListCACertificatesRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String marker;
    private Integer pageSize;
    private String templateName;

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public ListCACertificatesRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public ListCACertificatesRequest withMarker(String str) {
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

    public ListCACertificatesRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public ListCACertificatesRequest withTemplateName(String str) {
        this.templateName = str;
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
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getPageSize() == null ? 0 : getPageSize().hashCode()) + 31) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getAscendingOrder() == null ? 0 : getAscendingOrder().hashCode())) * 31) + (getTemplateName() != null ? getTemplateName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListCACertificatesRequest)) {
            return false;
        }
        ListCACertificatesRequest listCACertificatesRequest = (ListCACertificatesRequest) obj;
        if ((listCACertificatesRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        if (listCACertificatesRequest.getPageSize() != null && !listCACertificatesRequest.getPageSize().equals(getPageSize())) {
            return false;
        }
        if ((listCACertificatesRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listCACertificatesRequest.getMarker() != null && !listCACertificatesRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listCACertificatesRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        if (listCACertificatesRequest.getAscendingOrder() != null && !listCACertificatesRequest.getAscendingOrder().equals(getAscendingOrder())) {
            return false;
        }
        if ((listCACertificatesRequest.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        return listCACertificatesRequest.getTemplateName() == null || listCACertificatesRequest.getTemplateName().equals(getTemplateName());
    }
}
