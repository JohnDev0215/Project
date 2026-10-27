package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateKeysAndCertificateResult implements Serializable {
    private String certificateArn;
    private String certificateId;
    private String certificatePem;
    private KeyPair keyPair;

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public CreateKeysAndCertificateResult withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public CreateKeysAndCertificateResult withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public CreateKeysAndCertificateResult withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public KeyPair getKeyPair() {
        return this.keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public CreateKeysAndCertificateResult withKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateArn() != null) {
            sb.append("certificateArn: " + getCertificateArn() + ",");
        }
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId() + ",");
        }
        if (getCertificatePem() != null) {
            sb.append("certificatePem: " + getCertificatePem() + ",");
        }
        if (getKeyPair() != null) {
            sb.append("keyPair: " + getKeyPair());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getCertificatePem() == null ? 0 : getCertificatePem().hashCode())) * 31) + (getKeyPair() != null ? getKeyPair().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateKeysAndCertificateResult)) {
            return false;
        }
        CreateKeysAndCertificateResult createKeysAndCertificateResult = (CreateKeysAndCertificateResult) obj;
        if ((createKeysAndCertificateResult.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (createKeysAndCertificateResult.getCertificateArn() != null && !createKeysAndCertificateResult.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((createKeysAndCertificateResult.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (createKeysAndCertificateResult.getCertificateId() != null && !createKeysAndCertificateResult.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((createKeysAndCertificateResult.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (createKeysAndCertificateResult.getCertificatePem() != null && !createKeysAndCertificateResult.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((createKeysAndCertificateResult.getKeyPair() == null) ^ (getKeyPair() == null)) {
            return false;
        }
        return createKeysAndCertificateResult.getKeyPair() == null || createKeysAndCertificateResult.getKeyPair().equals(getKeyPair());
    }
}
