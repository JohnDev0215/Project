package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CodeSigningCertificateChain implements Serializable {
    private String certificateName;
    private String inlineDocument;

    public String getCertificateName() {
        return this.certificateName;
    }

    public void setCertificateName(String str) {
        this.certificateName = str;
    }

    public CodeSigningCertificateChain withCertificateName(String str) {
        this.certificateName = str;
        return this;
    }

    public String getInlineDocument() {
        return this.inlineDocument;
    }

    public void setInlineDocument(String str) {
        this.inlineDocument = str;
    }

    public CodeSigningCertificateChain withInlineDocument(String str) {
        this.inlineDocument = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateName() != null) {
            sb.append("certificateName: " + getCertificateName() + ",");
        }
        if (getInlineDocument() != null) {
            sb.append("inlineDocument: " + getInlineDocument());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificateName() == null ? 0 : getCertificateName().hashCode()) + 31) * 31) + (getInlineDocument() != null ? getInlineDocument().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CodeSigningCertificateChain)) {
            return false;
        }
        CodeSigningCertificateChain codeSigningCertificateChain = (CodeSigningCertificateChain) obj;
        if ((codeSigningCertificateChain.getCertificateName() == null) ^ (getCertificateName() == null)) {
            return false;
        }
        if (codeSigningCertificateChain.getCertificateName() != null && !codeSigningCertificateChain.getCertificateName().equals(getCertificateName())) {
            return false;
        }
        if ((codeSigningCertificateChain.getInlineDocument() == null) ^ (getInlineDocument() == null)) {
            return false;
        }
        return codeSigningCertificateChain.getInlineDocument() == null || codeSigningCertificateChain.getInlineDocument().equals(getInlineDocument());
    }
}
