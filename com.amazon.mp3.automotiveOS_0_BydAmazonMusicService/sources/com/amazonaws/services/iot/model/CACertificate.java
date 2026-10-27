package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class CACertificate implements Serializable {
    private String certificateArn;
    private String certificateId;
    private Date creationDate;
    private String status;

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public CACertificate withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public CACertificate withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public CACertificate withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(CACertificateStatus cACertificateStatus) {
        this.status = cACertificateStatus.toString();
    }

    public CACertificate withStatus(CACertificateStatus cACertificateStatus) {
        this.status = cACertificateStatus.toString();
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public CACertificate withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateArn() != null) {
            sb.append("certificateArn: " + getCertificateArn() + ",");
        }
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CACertificate)) {
            return false;
        }
        CACertificate cACertificate = (CACertificate) obj;
        if ((cACertificate.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (cACertificate.getCertificateArn() != null && !cACertificate.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((cACertificate.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (cACertificate.getCertificateId() != null && !cACertificate.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((cACertificate.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (cACertificate.getStatus() != null && !cACertificate.getStatus().equals(getStatus())) {
            return false;
        }
        if ((cACertificate.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return cACertificate.getCreationDate() == null || cACertificate.getCreationDate().equals(getCreationDate());
    }
}
