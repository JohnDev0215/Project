package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class Certificate implements Serializable {
    private String certificateArn;
    private String certificateId;
    private String certificateMode;
    private Date creationDate;
    private String status;

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public Certificate withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public Certificate withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public Certificate withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
    }

    public Certificate withStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
        return this;
    }

    public String getCertificateMode() {
        return this.certificateMode;
    }

    public void setCertificateMode(String str) {
        this.certificateMode = str;
    }

    public Certificate withCertificateMode(String str) {
        this.certificateMode = str;
        return this;
    }

    public void setCertificateMode(CertificateMode certificateMode) {
        this.certificateMode = certificateMode.toString();
    }

    public Certificate withCertificateMode(CertificateMode certificateMode) {
        this.certificateMode = certificateMode.toString();
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public Certificate withCreationDate(Date date) {
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
        if (getCertificateMode() != null) {
            sb.append("certificateMode: " + getCertificateMode() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getCertificateMode() == null ? 0 : getCertificateMode().hashCode())) * 31) + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Certificate)) {
            return false;
        }
        Certificate certificate = (Certificate) obj;
        if ((certificate.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (certificate.getCertificateArn() != null && !certificate.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((certificate.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (certificate.getCertificateId() != null && !certificate.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((certificate.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (certificate.getStatus() != null && !certificate.getStatus().equals(getStatus())) {
            return false;
        }
        if ((certificate.getCertificateMode() == null) ^ (getCertificateMode() == null)) {
            return false;
        }
        if (certificate.getCertificateMode() != null && !certificate.getCertificateMode().equals(getCertificateMode())) {
            return false;
        }
        if ((certificate.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return certificate.getCreationDate() == null || certificate.getCreationDate().equals(getCreationDate());
    }
}
