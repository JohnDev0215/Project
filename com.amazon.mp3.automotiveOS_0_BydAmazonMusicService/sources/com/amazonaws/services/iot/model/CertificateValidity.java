package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class CertificateValidity implements Serializable {
    private Date notAfter;
    private Date notBefore;

    public Date getNotBefore() {
        return this.notBefore;
    }

    public void setNotBefore(Date date) {
        this.notBefore = date;
    }

    public CertificateValidity withNotBefore(Date date) {
        this.notBefore = date;
        return this;
    }

    public Date getNotAfter() {
        return this.notAfter;
    }

    public void setNotAfter(Date date) {
        this.notAfter = date;
    }

    public CertificateValidity withNotAfter(Date date) {
        this.notAfter = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getNotBefore() != null) {
            sb.append("notBefore: " + getNotBefore() + ",");
        }
        if (getNotAfter() != null) {
            sb.append("notAfter: " + getNotAfter());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getNotBefore() == null ? 0 : getNotBefore().hashCode()) + 31) * 31) + (getNotAfter() != null ? getNotAfter().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CertificateValidity)) {
            return false;
        }
        CertificateValidity certificateValidity = (CertificateValidity) obj;
        if ((certificateValidity.getNotBefore() == null) ^ (getNotBefore() == null)) {
            return false;
        }
        if (certificateValidity.getNotBefore() != null && !certificateValidity.getNotBefore().equals(getNotBefore())) {
            return false;
        }
        if ((certificateValidity.getNotAfter() == null) ^ (getNotAfter() == null)) {
            return false;
        }
        return certificateValidity.getNotAfter() == null || certificateValidity.getNotAfter().equals(getNotAfter());
    }
}
