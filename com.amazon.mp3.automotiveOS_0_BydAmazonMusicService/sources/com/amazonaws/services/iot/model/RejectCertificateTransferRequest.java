package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class RejectCertificateTransferRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private String rejectReason;

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public RejectCertificateTransferRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getRejectReason() {
        return this.rejectReason;
    }

    public void setRejectReason(String str) {
        this.rejectReason = str;
    }

    public RejectCertificateTransferRequest withRejectReason(String str) {
        this.rejectReason = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId() + ",");
        }
        if (getRejectReason() != null) {
            sb.append("rejectReason: " + getRejectReason());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31) + (getRejectReason() != null ? getRejectReason().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RejectCertificateTransferRequest)) {
            return false;
        }
        RejectCertificateTransferRequest rejectCertificateTransferRequest = (RejectCertificateTransferRequest) obj;
        if ((rejectCertificateTransferRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (rejectCertificateTransferRequest.getCertificateId() != null && !rejectCertificateTransferRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((rejectCertificateTransferRequest.getRejectReason() == null) ^ (getRejectReason() == null)) {
            return false;
        }
        return rejectCertificateTransferRequest.getRejectReason() == null || rejectCertificateTransferRequest.getRejectReason().equals(getRejectReason());
    }
}
