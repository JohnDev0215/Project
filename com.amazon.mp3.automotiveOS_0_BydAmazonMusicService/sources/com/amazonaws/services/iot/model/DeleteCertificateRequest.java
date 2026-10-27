package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteCertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private Boolean forceDelete;

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public DeleteCertificateRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public Boolean isForceDelete() {
        return this.forceDelete;
    }

    public Boolean getForceDelete() {
        return this.forceDelete;
    }

    public void setForceDelete(Boolean bool) {
        this.forceDelete = bool;
    }

    public DeleteCertificateRequest withForceDelete(Boolean bool) {
        this.forceDelete = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId() + ",");
        }
        if (getForceDelete() != null) {
            sb.append("forceDelete: " + getForceDelete());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31) + (getForceDelete() != null ? getForceDelete().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteCertificateRequest)) {
            return false;
        }
        DeleteCertificateRequest deleteCertificateRequest = (DeleteCertificateRequest) obj;
        if ((deleteCertificateRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (deleteCertificateRequest.getCertificateId() != null && !deleteCertificateRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((deleteCertificateRequest.getForceDelete() == null) ^ (getForceDelete() == null)) {
            return false;
        }
        return deleteCertificateRequest.getForceDelete() == null || deleteCertificateRequest.getForceDelete().equals(getForceDelete());
    }
}
