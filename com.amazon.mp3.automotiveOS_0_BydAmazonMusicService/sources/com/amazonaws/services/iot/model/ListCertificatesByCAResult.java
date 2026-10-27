package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListCertificatesByCAResult implements Serializable {
    private List<Certificate> certificates;
    private String nextMarker;

    public List<Certificate> getCertificates() {
        return this.certificates;
    }

    public void setCertificates(Collection<Certificate> collection) {
        if (collection == null) {
            this.certificates = null;
        } else {
            this.certificates = new ArrayList(collection);
        }
    }

    public ListCertificatesByCAResult withCertificates(Certificate... certificateArr) {
        if (getCertificates() == null) {
            this.certificates = new ArrayList(certificateArr.length);
        }
        for (Certificate certificate : certificateArr) {
            this.certificates.add(certificate);
        }
        return this;
    }

    public ListCertificatesByCAResult withCertificates(Collection<Certificate> collection) {
        setCertificates(collection);
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public ListCertificatesByCAResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificates() != null) {
            sb.append("certificates: " + getCertificates() + ",");
        }
        if (getNextMarker() != null) {
            sb.append("nextMarker: " + getNextMarker());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificates() == null ? 0 : getCertificates().hashCode()) + 31) * 31) + (getNextMarker() != null ? getNextMarker().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListCertificatesByCAResult)) {
            return false;
        }
        ListCertificatesByCAResult listCertificatesByCAResult = (ListCertificatesByCAResult) obj;
        if ((listCertificatesByCAResult.getCertificates() == null) ^ (getCertificates() == null)) {
            return false;
        }
        if (listCertificatesByCAResult.getCertificates() != null && !listCertificatesByCAResult.getCertificates().equals(getCertificates())) {
            return false;
        }
        if ((listCertificatesByCAResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listCertificatesByCAResult.getNextMarker() == null || listCertificatesByCAResult.getNextMarker().equals(getNextMarker());
    }
}
