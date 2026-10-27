package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class HttpUrlDestinationConfiguration implements Serializable {
    private String confirmationUrl;

    public String getConfirmationUrl() {
        return this.confirmationUrl;
    }

    public void setConfirmationUrl(String str) {
        this.confirmationUrl = str;
    }

    public HttpUrlDestinationConfiguration withConfirmationUrl(String str) {
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
        if (obj == null || !(obj instanceof HttpUrlDestinationConfiguration)) {
            return false;
        }
        HttpUrlDestinationConfiguration httpUrlDestinationConfiguration = (HttpUrlDestinationConfiguration) obj;
        if ((httpUrlDestinationConfiguration.getConfirmationUrl() == null) ^ (getConfirmationUrl() == null)) {
            return false;
        }
        return httpUrlDestinationConfiguration.getConfirmationUrl() == null || httpUrlDestinationConfiguration.getConfirmationUrl().equals(getConfirmationUrl());
    }
}
