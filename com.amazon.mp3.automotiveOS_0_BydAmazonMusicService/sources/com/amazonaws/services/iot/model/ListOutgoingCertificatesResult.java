package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListOutgoingCertificatesResult implements Serializable {
    private String nextMarker;
    private List<OutgoingCertificate> outgoingCertificates;

    public List<OutgoingCertificate> getOutgoingCertificates() {
        return this.outgoingCertificates;
    }

    public void setOutgoingCertificates(Collection<OutgoingCertificate> collection) {
        if (collection == null) {
            this.outgoingCertificates = null;
        } else {
            this.outgoingCertificates = new ArrayList(collection);
        }
    }

    public ListOutgoingCertificatesResult withOutgoingCertificates(OutgoingCertificate... outgoingCertificateArr) {
        if (getOutgoingCertificates() == null) {
            this.outgoingCertificates = new ArrayList(outgoingCertificateArr.length);
        }
        for (OutgoingCertificate outgoingCertificate : outgoingCertificateArr) {
            this.outgoingCertificates.add(outgoingCertificate);
        }
        return this;
    }

    public ListOutgoingCertificatesResult withOutgoingCertificates(Collection<OutgoingCertificate> collection) {
        setOutgoingCertificates(collection);
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public ListOutgoingCertificatesResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getOutgoingCertificates() != null) {
            sb.append("outgoingCertificates: " + getOutgoingCertificates() + ",");
        }
        if (getNextMarker() != null) {
            sb.append("nextMarker: " + getNextMarker());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getOutgoingCertificates() == null ? 0 : getOutgoingCertificates().hashCode()) + 31) * 31) + (getNextMarker() != null ? getNextMarker().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListOutgoingCertificatesResult)) {
            return false;
        }
        ListOutgoingCertificatesResult listOutgoingCertificatesResult = (ListOutgoingCertificatesResult) obj;
        if ((listOutgoingCertificatesResult.getOutgoingCertificates() == null) ^ (getOutgoingCertificates() == null)) {
            return false;
        }
        if (listOutgoingCertificatesResult.getOutgoingCertificates() != null && !listOutgoingCertificatesResult.getOutgoingCertificates().equals(getOutgoingCertificates())) {
            return false;
        }
        if ((listOutgoingCertificatesResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listOutgoingCertificatesResult.getNextMarker() == null || listOutgoingCertificatesResult.getNextMarker().equals(getNextMarker());
    }
}
