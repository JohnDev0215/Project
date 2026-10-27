package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class HttpUrlDestinationProperties implements Serializable {
    private String confirmationUrl;

    public String getConfirmationUrl() {
        return this.confirmationUrl;
    }

    public void setConfirmationUrl(String str) {
        this.confirmationUrl = str;
    }

    public HttpUrlDestinationProperties withConfirmationUrl(String str) {
        this.confirmationUrl = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getConfirmationUrl() != null) {
            sb.append("confirmationUrl: " + getConfirmationUrl());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getConfirmationUrl() == null ? 0 : getConfirmationUrl().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HttpUrlDestinationProperties)) {
            return false;
        }
        HttpUrlDestinationProperties httpUrlDestinationProperties = (HttpUrlDestinationProperties) obj;
        if ((httpUrlDestinationProperties.getConfirmationUrl() == null) ^ (getConfirmationUrl() == null)) {
            return false;
        }
        return httpUrlDestinationProperties.getConfirmationUrl() == null || httpUrlDestinationProperties.getConfirmationUrl().equals(getConfirmationUrl());
    }
}
