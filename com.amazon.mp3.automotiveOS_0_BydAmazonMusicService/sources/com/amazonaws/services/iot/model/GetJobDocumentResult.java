package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetJobDocumentResult implements Serializable {
    private String document;

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String str) {
        this.document = str;
    }

    public GetJobDocumentResult withDocument(String str) {
        this.document = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDocument() != null) {
            sb.append("document: " + getDocument());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getDocument() == null ? 0 : getDocument().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetJobDocumentResult)) {
            return false;
        }
        GetJobDocumentResult getJobDocumentResult = (GetJobDocumentResult) obj;
        if ((getJobDocumentResult.getDocument() == null) ^ (getDocument() == null)) {
            return false;
        }
        return getJobDocumentResult.getDocument() == null || getJobDocumentResult.getDocument().equals(getDocument());
    }
}
