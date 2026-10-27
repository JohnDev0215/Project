package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListCertificatesResult implements Serializable {
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

    public ListCertificatesResult withCertificates(Certificate... certificateArr) {
        if (getCertificates() == null) {
            this.certificates = new ArrayList(certificateArr.length);
        }
        for (Certificate certificate : certificateArr) {
            this.certificates.add(certificate);
        }
        return this;
    }

    public ListCertificatesResult withCertificates(Collection<Certificate> collection) {
        setCertificates(collection);
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public ListCertificatesResult withNextMarker(String str) {
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
        if (obj == null || !(obj instanceof ListCertificatesResult)) {
            return false;
        }
        ListCertificatesResult listCertificatesResult = (ListCertificatesResult) obj;
        if ((listCertificatesResult.getCertificates() == null) ^ (getCertificates() == null)) {
            return false;
        }
        if (listCertificatesResult.getCertificates() != null && !listCertificatesResult.getCertificates().equals(getCertificates())) {
            return false;
        }
        if ((listCertificatesResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listCertificatesResult.getNextMarker() == null || listCertificatesResult.getNextMarker().equals(getNextMarker());
    }
}
