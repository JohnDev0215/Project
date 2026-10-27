package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateCertificateFromCsrResult implements Serializable {
    private String certificateArn;
    private String certificateId;
    private String certificatePem;

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public CreateCertificateFromCsrResult withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public CreateCertificateFromCsrResult withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public CreateCertificateFromCsrResult withCertificatePem(String str) {
        this.certificatePem = str;
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
        if (getCertificatePem() != null) {
            sb.append("certificatePem: " + getCertificatePem());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getCertificatePem() != null ? getCertificatePem().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateCertificateFromCsrResult)) {
            return false;
        }
        CreateCertificateFromCsrResult createCertificateFromCsrResult = (CreateCertificateFromCsrResult) obj;
        if ((createCertificateFromCsrResult.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (createCertificateFromCsrResult.getCertificateArn() != null && !createCertificateFromCsrResult.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((createCertificateFromCsrResult.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (createCertificateFromCsrResult.getCertificateId() != null && !createCertificateFromCsrResult.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((createCertificateFromCsrResult.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        return createCertificateFromCsrResult.getCertificatePem() == null || createCertificateFromCsrResult.getCertificatePem().equals(getCertificatePem());
    }
}
