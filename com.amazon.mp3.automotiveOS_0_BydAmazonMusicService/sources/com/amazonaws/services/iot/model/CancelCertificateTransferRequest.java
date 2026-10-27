package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CancelCertificateTransferRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public CancelCertificateTransferRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getCertificateId() == null ? 0 : getCertificateId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CancelCertificateTransferRequest)) {
            return false;
        }
        CancelCertificateTransferRequest cancelCertificateTransferRequest = (CancelCertificateTransferRequest) obj;
        if ((cancelCertificateTransferRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        return cancelCertificateTransferRequest.getCertificateId() == null || cancelCertificateTransferRequest.getCertificateId().equals(getCertificateId());
    }
}
