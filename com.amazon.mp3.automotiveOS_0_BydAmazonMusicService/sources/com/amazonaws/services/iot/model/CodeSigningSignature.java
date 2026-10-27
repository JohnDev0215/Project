package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
public class CodeSigningSignature implements Serializable {
    private ByteBuffer inlineDocument;

    public ByteBuffer getInlineDocument() {
        return this.inlineDocument;
    }

    public void setInlineDocument(ByteBuffer byteBuffer) {
        this.inlineDocument = byteBuffer;
    }

    public CodeSigningSignature withInlineDocument(ByteBuffer byteBuffer) {
        this.inlineDocument = byteBuffer;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getInlineDocument() != null) {
            sb.append("inlineDocument: " + getInlineDocument());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getInlineDocument() == null ? 0 : getInlineDocument().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CodeSigningSignature)) {
            return false;
        }
        CodeSigningSignature codeSigningSignature = (CodeSigningSignature) obj;
        if ((codeSigningSignature.getInlineDocument() == null) ^ (getInlineDocument() == null)) {
            return false;
        }
        return codeSigningSignature.getInlineDocument() == null || codeSigningSignature.getInlineDocument().equals(getInlineDocument());
    }
}
