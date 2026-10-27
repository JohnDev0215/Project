package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterCertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String caCertificatePem;
    private String certificatePem;
    private Boolean setAsActive;
    private String status;

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public RegisterCertificateRequest withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public String getCaCertificatePem() {
        return this.caCertificatePem;
    }

    public void setCaCertificatePem(String str) {
        this.caCertificatePem = str;
    }

    public RegisterCertificateRequest withCaCertificatePem(String str) {
        this.caCertificatePem = str;
        return this;
    }

    public Boolean isSetAsActive() {
        return this.setAsActive;
    }

    public Boolean getSetAsActive() {
        return this.setAsActive;
    }

    public void setSetAsActive(Boolean bool) {
        this.setAsActive = bool;
    }

    public RegisterCertificateRequest withSetAsActive(Boolean bool) {
        this.setAsActive = bool;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public RegisterCertificateRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
    }

    public RegisterCertificateRequest withStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificatePem() != null) {
            sb.append("certificatePem: " + getCertificatePem() + ",");
        }
        if (getCaCertificatePem() != null) {
            sb.append("caCertificatePem: " + getCaCertificatePem() + ",");
        }
        if (getSetAsActive() != null) {
            sb.append("setAsActive: " + getSetAsActive() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getCertificatePem() == null ? 0 : getCertificatePem().hashCode()) + 31) * 31) + (getCaCertificatePem() == null ? 0 : getCaCertificatePem().hashCode())) * 31) + (getSetAsActive() == null ? 0 : getSetAsActive().hashCode())) * 31) + (getStatus() != null ? getStatus().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegisterCertificateRequest)) {
            return false;
        }
        RegisterCertificateRequest registerCertificateRequest = (RegisterCertificateRequest) obj;
        if ((registerCertificateRequest.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (registerCertificateRequest.getCertificatePem() != null && !registerCertificateRequest.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((registerCertificateRequest.getCaCertificatePem() == null) ^ (getCaCertificatePem() == null)) {
            return false;
        }
        if (registerCertificateRequest.getCaCertificatePem() != null && !registerCertificateRequest.getCaCertificatePem().equals(getCaCertificatePem())) {
            return false;
        }
        if ((registerCertificateRequest.getSetAsActive() == null) ^ (getSetAsActive() == null)) {
            return false;
        }
        if (registerCertificateRequest.getSetAsActive() != null && !registerCertificateRequest.getSetAsActive().equals(getSetAsActive())) {
            return false;
        }
        if ((registerCertificateRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return registerCertificateRequest.getStatus() == null || registerCertificateRequest.getStatus().equals(getStatus());
    }
}
