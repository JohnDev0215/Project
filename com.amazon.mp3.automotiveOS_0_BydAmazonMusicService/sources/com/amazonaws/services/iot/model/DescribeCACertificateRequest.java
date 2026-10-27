package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeCACertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public DescribeCACertificateRequest withCertificateId(String str) {
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
        if (obj == null || !(obj instanceof DescribeCACertificateRequest)) {
            return false;
        }
        DescribeCACertificateRequest describeCACertificateRequest = (DescribeCACertificateRequest) obj;
        if ((describeCACertificateRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        return describeCACertificateRequest.getCertificateId() == null || describeCACertificateRequest.getCertificateId().equals(getCertificateId());
    }
}
