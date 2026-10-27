package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AcceptCertificateTransferRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private Boolean setAsActive;

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public AcceptCertificateTransferRequest withCertificateId(String str) {
        this.certificateId = str;
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

    public AcceptCertificateTransferRequest withSetAsActive(Boolean bool) {
        this.setAsActive = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId() + ",");
        }
        if (getSetAsActive() != null) {
            sb.append("setAsActive: " + getSetAsActive());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31) + (getSetAsActive() != null ? getSetAsActive().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AcceptCertificateTransferRequest)) {
            return false;
        }
        AcceptCertificateTransferRequest acceptCertificateTransferRequest = (AcceptCertificateTransferRequest) obj;
        if ((acceptCertificateTransferRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (acceptCertificateTransferRequest.getCertificateId() != null && !acceptCertificateTransferRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((acceptCertificateTransferRequest.getSetAsActive() == null) ^ (getSetAsActive() == null)) {
            return false;
        }
        return acceptCertificateTransferRequest.getSetAsActive() == null || acceptCertificateTransferRequest.getSetAsActive().equals(getSetAsActive());
    }
}
