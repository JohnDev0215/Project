package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateCertificateFromCsrRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateSigningRequest;
    private Boolean setAsActive;

    public String getCertificateSigningRequest() {
        return this.certificateSigningRequest;
    }

    public void setCertificateSigningRequest(String str) {
        this.certificateSigningRequest = str;
    }

    public CreateCertificateFromCsrRequest withCertificateSigningRequest(String str) {
        this.certificateSigningRequest = str;
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

    public CreateCertificateFromCsrRequest withSetAsActive(Boolean bool) {
        this.setAsActive = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateSigningRequest() != null) {
            sb.append("certificateSigningRequest: " + getCertificateSigningRequest() + ",");
        }
        if (getSetAsActive() != null) {
            sb.append("setAsActive: " + getSetAsActive());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificateSigningRequest() == null ? 0 : getCertificateSigningRequest().hashCode()) + 31) * 31) + (getSetAsActive() != null ? getSetAsActive().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateCertificateFromCsrRequest)) {
            return false;
        }
        CreateCertificateFromCsrRequest createCertificateFromCsrRequest = (CreateCertificateFromCsrRequest) obj;
        if ((createCertificateFromCsrRequest.getCertificateSigningRequest() == null) ^ (getCertificateSigningRequest() == null)) {
            return false;
        }
        if (createCertificateFromCsrRequest.getCertificateSigningRequest() != null && !createCertificateFromCsrRequest.getCertificateSigningRequest().equals(getCertificateSigningRequest())) {
            return false;
        }
        if ((createCertificateFromCsrRequest.getSetAsActive() == null) ^ (getSetAsActive() == null)) {
            return false;
        }
        return createCertificateFromCsrRequest.getSetAsActive() == null || createCertificateFromCsrRequest.getSetAsActive().equals(getSetAsActive());
    }
}
