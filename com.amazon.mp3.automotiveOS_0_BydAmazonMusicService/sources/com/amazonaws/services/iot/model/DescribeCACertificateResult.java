package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeCACertificateResult implements Serializable {
    private CACertificateDescription certificateDescription;
    private RegistrationConfig registrationConfig;

    public CACertificateDescription getCertificateDescription() {
        return this.certificateDescription;
    }

    public void setCertificateDescription(CACertificateDescription cACertificateDescription) {
        this.certificateDescription = cACertificateDescription;
    }

    public DescribeCACertificateResult withCertificateDescription(CACertificateDescription cACertificateDescription) {
        this.certificateDescription = cACertificateDescription;
        return this;
    }

    public RegistrationConfig getRegistrationConfig() {
        return this.registrationConfig;
    }

    public void setRegistrationConfig(RegistrationConfig registrationConfig) {
        this.registrationConfig = registrationConfig;
    }

    public DescribeCACertificateResult withRegistrationConfig(RegistrationConfig registrationConfig) {
        this.registrationConfig = registrationConfig;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateDescription() != null) {
            sb.append("certificateDescription: " + getCertificateDescription() + ",");
        }
        if (getRegistrationConfig() != null) {
            sb.append("registrationConfig: " + getRegistrationConfig());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificateDescription() == null ? 0 : getCertificateDescription().hashCode()) + 31) * 31) + (getRegistrationConfig() != null ? getRegistrationConfig().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeCACertificateResult)) {
            return false;
        }
        DescribeCACertificateResult describeCACertificateResult = (DescribeCACertificateResult) obj;
        if ((describeCACertificateResult.getCertificateDescription() == null) ^ (getCertificateDescription() == null)) {
            return false;
        }
        if (describeCACertificateResult.getCertificateDescription() != null && !describeCACertificateResult.getCertificateDescription().equals(getCertificateDescription())) {
            return false;
        }
        if ((describeCACertificateResult.getRegistrationConfig() == null) ^ (getRegistrationConfig() == null)) {
            return false;
        }
        return describeCACertificateResult.getRegistrationConfig() == null || describeCACertificateResult.getRegistrationConfig().equals(getRegistrationConfig());
    }
}
