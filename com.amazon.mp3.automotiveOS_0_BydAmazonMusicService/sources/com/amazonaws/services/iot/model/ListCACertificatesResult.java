package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListCACertificatesResult implements Serializable {
    private List<CACertificate> certificates;
    private String nextMarker;

    public List<CACertificate> getCertificates() {
        return this.certificates;
    }

    public void setCertificates(Collection<CACertificate> collection) {
        if (collection == null) {
            this.certificates = null;
        } else {
            this.certificates = new ArrayList(collection);
        }
    }

    public ListCACertificatesResult withCertificates(CACertificate... cACertificateArr) {
        if (getCertificates() == null) {
            this.certificates = new ArrayList(cACertificateArr.length);
        }
        for (CACertificate cACertificate : cACertificateArr) {
            this.certificates.add(cACertificate);
        }
        return this;
    }

    public ListCACertificatesResult withCertificates(Collection<CACertificate> collection) {
        setCertificates(collection);
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public ListCACertificatesResult withNextMarker(String str) {
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
        if (obj == null || !(obj instanceof ListCACertificatesResult)) {
            return false;
        }
        ListCACertificatesResult listCACertificatesResult = (ListCACertificatesResult) obj;
        if ((listCACertificatesResult.getCertificates() == null) ^ (getCertificates() == null)) {
            return false;
        }
        if (listCACertificatesResult.getCertificates() != null && !listCACertificatesResult.getCertificates().equals(getCertificates())) {
            return false;
        }
        if ((listCACertificatesResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listCACertificatesResult.getNextMarker() == null || listCACertificatesResult.getNextMarker().equals(getNextMarker());
    }
}
