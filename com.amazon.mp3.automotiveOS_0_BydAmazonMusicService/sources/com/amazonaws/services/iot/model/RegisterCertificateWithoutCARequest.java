package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterCertificateWithoutCARequest extends AmazonWebServiceRequest implements Serializable {
    private String certificatePem;
    private String status;

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public RegisterCertificateWithoutCARequest withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public RegisterCertificateWithoutCARequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
    }

    public RegisterCertificateWithoutCARequest withStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificatePem() != null) {
            sb.append("certificatePem: " + getCertificatePem() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificatePem() == null ? 0 : getCertificatePem().hashCode()) + 31) * 31) + (getStatus() != null ? getStatus().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegisterCertificateWithoutCARequest)) {
            return false;
        }
        RegisterCertificateWithoutCARequest registerCertificateWithoutCARequest = (RegisterCertificateWithoutCARequest) obj;
        if ((registerCertificateWithoutCARequest.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (registerCertificateWithoutCARequest.getCertificatePem() != null && !registerCertificateWithoutCARequest.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((registerCertificateWithoutCARequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return registerCertificateWithoutCARequest.getStatus() == null || registerCertificateWithoutCARequest.getStatus().equals(getStatus());
    }
}
