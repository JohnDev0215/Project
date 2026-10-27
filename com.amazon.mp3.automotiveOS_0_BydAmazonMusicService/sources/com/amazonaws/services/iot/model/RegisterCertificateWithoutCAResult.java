package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterCertificateWithoutCAResult implements Serializable {
    private String certificateArn;
    private String certificateId;

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public RegisterCertificateWithoutCAResult withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public RegisterCertificateWithoutCAResult withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateArn() != null) {
            sb.append("certificateArn: " + getCertificateArn() + ",");
        }
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() != null ? getCertificateId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegisterCertificateWithoutCAResult)) {
            return false;
        }
        RegisterCertificateWithoutCAResult registerCertificateWithoutCAResult = (RegisterCertificateWithoutCAResult) obj;
        if ((registerCertificateWithoutCAResult.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (registerCertificateWithoutCAResult.getCertificateArn() != null && !registerCertificateWithoutCAResult.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((registerCertificateWithoutCAResult.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        return registerCertificateWithoutCAResult.getCertificateId() == null || registerCertificateWithoutCAResult.getCertificateId().equals(getCertificateId());
    }
}
