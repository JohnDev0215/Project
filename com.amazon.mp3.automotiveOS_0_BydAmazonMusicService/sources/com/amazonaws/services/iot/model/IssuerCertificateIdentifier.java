package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class IssuerCertificateIdentifier implements Serializable {
    private String issuerCertificateSerialNumber;
    private String issuerCertificateSubject;
    private String issuerId;

    public String getIssuerCertificateSubject() {
        return this.issuerCertificateSubject;
    }

    public void setIssuerCertificateSubject(String str) {
        this.issuerCertificateSubject = str;
    }

    public IssuerCertificateIdentifier withIssuerCertificateSubject(String str) {
        this.issuerCertificateSubject = str;
        return this;
    }

    public String getIssuerId() {
        return this.issuerId;
    }

    public void setIssuerId(String str) {
        this.issuerId = str;
    }

    public IssuerCertificateIdentifier withIssuerId(String str) {
        this.issuerId = str;
        return this;
    }

    public String getIssuerCertificateSerialNumber() {
        return this.issuerCertificateSerialNumber;
    }

    public void setIssuerCertificateSerialNumber(String str) {
        this.issuerCertificateSerialNumber = str;
    }

    public IssuerCertificateIdentifier withIssuerCertificateSerialNumber(String str) {
        this.issuerCertificateSerialNumber = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIssuerCertificateSubject() != null) {
            sb.append("issuerCertificateSubject: " + getIssuerCertificateSubject() + ",");
        }
        if (getIssuerId() != null) {
            sb.append("issuerId: " + getIssuerId() + ",");
        }
        if (getIssuerCertificateSerialNumber() != null) {
            sb.append("issuerCertificateSerialNumber: " + getIssuerCertificateSerialNumber());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getIssuerCertificateSubject() == null ? 0 : getIssuerCertificateSubject().hashCode()) + 31) * 31) + (getIssuerId() == null ? 0 : getIssuerId().hashCode())) * 31) + (getIssuerCertificateSerialNumber() != null ? getIssuerCertificateSerialNumber().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof IssuerCertificateIdentifier)) {
            return false;
        }
        IssuerCertificateIdentifier issuerCertificateIdentifier = (IssuerCertificateIdentifier) obj;
        if ((issuerCertificateIdentifier.getIssuerCertificateSubject() == null) ^ (getIssuerCertificateSubject() == null)) {
            return false;
        }
        if (issuerCertificateIdentifier.getIssuerCertificateSubject() != null && !issuerCertificateIdentifier.getIssuerCertificateSubject().equals(getIssuerCertificateSubject())) {
            return false;
        }
        if ((issuerCertificateIdentifier.getIssuerId() == null) ^ (getIssuerId() == null)) {
            return false;
        }
        if (issuerCertificateIdentifier.getIssuerId() != null && !issuerCertificateIdentifier.getIssuerId().equals(getIssuerId())) {
            return false;
        }
        if ((issuerCertificateIdentifier.getIssuerCertificateSerialNumber() == null) ^ (getIssuerCertificateSerialNumber() == null)) {
            return false;
        }
        return issuerCertificateIdentifier.getIssuerCertificateSerialNumber() == null || issuerCertificateIdentifier.getIssuerCertificateSerialNumber().equals(getIssuerCertificateSerialNumber());
    }
}
