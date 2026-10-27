package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateKeysAndCertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean setAsActive;

    public Boolean isSetAsActive() {
        return this.setAsActive;
    }

    public Boolean getSetAsActive() {
        return this.setAsActive;
    }

    public void setSetAsActive(Boolean bool) {
        this.setAsActive = bool;
    }

    public CreateKeysAndCertificateRequest withSetAsActive(Boolean bool) {
        this.setAsActive = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSetAsActive() != null) {
            sb.append("setAsActive: " + getSetAsActive());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getSetAsActive() == null ? 0 : getSetAsActive().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateKeysAndCertificateRequest)) {
            return false;
        }
        CreateKeysAndCertificateRequest createKeysAndCertificateRequest = (CreateKeysAndCertificateRequest) obj;
        if ((createKeysAndCertificateRequest.getSetAsActive() == null) ^ (getSetAsActive() == null)) {
            return false;
        }
        return createKeysAndCertificateRequest.getSetAsActive() == null || createKeysAndCertificateRequest.getSetAsActive().equals(getSetAsActive());
    }
}
