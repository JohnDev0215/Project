package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateCertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private String newStatus;

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public UpdateCertificateRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getNewStatus() {
        return this.newStatus;
    }

    public void setNewStatus(String str) {
        this.newStatus = str;
    }

    public UpdateCertificateRequest withNewStatus(String str) {
        this.newStatus = str;
        return this;
    }

    public void setNewStatus(CertificateStatus certificateStatus) {
        this.newStatus = certificateStatus.toString();
    }

    public UpdateCertificateRequest withNewStatus(CertificateStatus certificateStatus) {
        this.newStatus = certificateStatus.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId() + ",");
        }
        if (getNewStatus() != null) {
            sb.append("newStatus: " + getNewStatus());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31) + (getNewStatus() != null ? getNewStatus().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateCertificateRequest)) {
            return false;
        }
        UpdateCertificateRequest updateCertificateRequest = (UpdateCertificateRequest) obj;
        if ((updateCertificateRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (updateCertificateRequest.getCertificateId() != null && !updateCertificateRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((updateCertificateRequest.getNewStatus() == null) ^ (getNewStatus() == null)) {
            return false;
        }
        return updateCertificateRequest.getNewStatus() == null || updateCertificateRequest.getNewStatus().equals(getNewStatus());
    }
}
