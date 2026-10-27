package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CustomCodeSigning implements Serializable {
    private CodeSigningCertificateChain certificateChain;
    private String hashAlgorithm;
    private CodeSigningSignature signature;
    private String signatureAlgorithm;

    public CodeSigningSignature getSignature() {
        return this.signature;
    }

    public void setSignature(CodeSigningSignature codeSigningSignature) {
        this.signature = codeSigningSignature;
    }

    public CustomCodeSigning withSignature(CodeSigningSignature codeSigningSignature) {
        this.signature = codeSigningSignature;
        return this;
    }

    public CodeSigningCertificateChain getCertificateChain() {
        return this.certificateChain;
    }

    public void setCertificateChain(CodeSigningCertificateChain codeSigningCertificateChain) {
        this.certificateChain = codeSigningCertificateChain;
    }

    public CustomCodeSigning withCertificateChain(CodeSigningCertificateChain codeSigningCertificateChain) {
        this.certificateChain = codeSigningCertificateChain;
        return this;
    }

    public String getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public void setHashAlgorithm(String str) {
        this.hashAlgorithm = str;
    }

    public CustomCodeSigning withHashAlgorithm(String str) {
        this.hashAlgorithm = str;
        return this;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public CustomCodeSigning withSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSignature() != null) {
            sb.append("signature: " + getSignature() + ",");
        }
        if (getCertificateChain() != null) {
            sb.append("certificateChain: " + getCertificateChain() + ",");
        }
        if (getHashAlgorithm() != null) {
            sb.append("hashAlgorithm: " + getHashAlgorithm() + ",");
        }
        if (getSignatureAlgorithm() != null) {
            sb.append("signatureAlgorithm: " + getSignatureAlgorithm());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getSignature() == null ? 0 : getSignature().hashCode()) + 31) * 31) + (getCertificateChain() == null ? 0 : getCertificateChain().hashCode())) * 31) + (getHashAlgorithm() == null ? 0 : getHashAlgorithm().hashCode())) * 31) + (getSignatureAlgorithm() != null ? getSignatureAlgorithm().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CustomCodeSigning)) {
            return false;
        }
        CustomCodeSigning customCodeSigning = (CustomCodeSigning) obj;
        if ((customCodeSigning.getSignature() == null) ^ (getSignature() == null)) {
            return false;
        }
        if (customCodeSigning.getSignature() != null && !customCodeSigning.getSignature().equals(getSignature())) {
            return false;
        }
        if ((customCodeSigning.getCertificateChain() == null) ^ (getCertificateChain() == null)) {
            return false;
        }
        if (customCodeSigning.getCertificateChain() != null && !customCodeSigning.getCertificateChain().equals(getCertificateChain())) {
            return false;
        }
        if ((customCodeSigning.getHashAlgorithm() == null) ^ (getHashAlgorithm() == null)) {
            return false;
        }
        if (customCodeSigning.getHashAlgorithm() != null && !customCodeSigning.getHashAlgorithm().equals(getHashAlgorithm())) {
            return false;
        }
        if ((customCodeSigning.getSignatureAlgorithm() == null) ^ (getSignatureAlgorithm() == null)) {
            return false;
        }
        return customCodeSigning.getSignatureAlgorithm() == null || customCodeSigning.getSignatureAlgorithm().equals(getSignatureAlgorithm());
    }
}
