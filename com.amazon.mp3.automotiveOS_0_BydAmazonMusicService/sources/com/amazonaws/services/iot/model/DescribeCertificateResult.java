package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeCertificateResult implements Serializable {
    private CertificateDescription certificateDescription;

    public CertificateDescription getCertificateDescription() {
        return this.certificateDescription;
    }

    public void setCertificateDescription(CertificateDescription certificateDescription) {
        this.certificateDescription = certificateDescription;
    }

    public DescribeCertificateResult withCertificateDescription(CertificateDescription certificateDescription) {
        this.certificateDescription = certificateDescription;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateDescription() != null) {
            sb.append("certificateDescription: " + getCertificateDescription());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getCertificateDescription() == null ? 0 : getCertificateDescription().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeCertificateResult)) {
            return false;
        }
        DescribeCertificateResult describeCertificateResult = (DescribeCertificateResult) obj;
        if ((describeCertificateResult.getCertificateDescription() == null) ^ (getCertificateDescription() == null)) {
            return false;
        }
        return describeCertificateResult.getCertificateDescription() == null || describeCertificateResult.getCertificateDescription().equals(getCertificateDescription());
    }
}
