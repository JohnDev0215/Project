package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class CreateProvisioningClaimResult implements Serializable {
    private String certificateId;
    private String certificatePem;
    private Date expiration;
    private KeyPair keyPair;

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public CreateProvisioningClaimResult withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public CreateProvisioningClaimResult withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public KeyPair getKeyPair() {
        return this.keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public CreateProvisioningClaimResult withKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
        return this;
    }

    public Date getExpiration() {
        return this.expiration;
    }

    public void setExpiration(Date date) {
        this.expiration = date;
    }

    public CreateProvisioningClaimResult withExpiration(Date date) {
        this.expiration = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId() + ",");
        }
        if (getCertificatePem() != null) {
            sb.append("certificatePem: " + getCertificatePem() + ",");
        }
        if (getKeyPair() != null) {
            sb.append("keyPair: " + getKeyPair() + ",");
        }
        if (getExpiration() != null) {
            sb.append("expiration: " + getExpiration());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31) + (getCertificatePem() == null ? 0 : getCertificatePem().hashCode())) * 31) + (getKeyPair() == null ? 0 : getKeyPair().hashCode())) * 31) + (getExpiration() != null ? getExpiration().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateProvisioningClaimResult)) {
            return false;
        }
        CreateProvisioningClaimResult createProvisioningClaimResult = (CreateProvisioningClaimResult) obj;
        if ((createProvisioningClaimResult.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (createProvisioningClaimResult.getCertificateId() != null && !createProvisioningClaimResult.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((createProvisioningClaimResult.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (createProvisioningClaimResult.getCertificatePem() != null && !createProvisioningClaimResult.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((createProvisioningClaimResult.getKeyPair() == null) ^ (getKeyPair() == null)) {
            return false;
        }
        if (createProvisioningClaimResult.getKeyPair() != null && !createProvisioningClaimResult.getKeyPair().equals(getKeyPair())) {
            return false;
        }
        if ((createProvisioningClaimResult.getExpiration() == null) ^ (getExpiration() == null)) {
            return false;
        }
        return createProvisioningClaimResult.getExpiration() == null || createProvisioningClaimResult.getExpiration().equals(getExpiration());
    }
}
