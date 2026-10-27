package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class HttpAuthorization implements Serializable {
    private SigV4Authorization sigv4;

    public SigV4Authorization getSigv4() {
        return this.sigv4;
    }

    public void setSigv4(SigV4Authorization sigV4Authorization) {
        this.sigv4 = sigV4Authorization;
    }

    public HttpAuthorization withSigv4(SigV4Authorization sigV4Authorization) {
        this.sigv4 = sigV4Authorization;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSigv4() != null) {
            sb.append("sigv4: " + getSigv4());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getSigv4() == null ? 0 : getSigv4().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HttpAuthorization)) {
            return false;
        }
        HttpAuthorization httpAuthorization = (HttpAuthorization) obj;
        if ((httpAuthorization.getSigv4() == null) ^ (getSigv4() == null)) {
            return false;
        }
        return httpAuthorization.getSigv4() == null || httpAuthorization.getSigv4().equals(getSigv4());
    }
}
