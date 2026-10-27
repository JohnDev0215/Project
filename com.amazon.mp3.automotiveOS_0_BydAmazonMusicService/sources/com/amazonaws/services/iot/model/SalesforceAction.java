package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class SalesforceAction implements Serializable {
    private String token;
    private String url;

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public SalesforceAction withToken(String str) {
        this.token = str;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public SalesforceAction withUrl(String str) {
        this.url = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getToken() != null) {
            sb.append("token: " + getToken() + ",");
        }
        if (getUrl() != null) {
            sb.append("url: " + getUrl());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getToken() == null ? 0 : getToken().hashCode()) + 31) * 31) + (getUrl() != null ? getUrl().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SalesforceAction)) {
            return false;
        }
        SalesforceAction salesforceAction = (SalesforceAction) obj;
        if ((salesforceAction.getToken() == null) ^ (getToken() == null)) {
            return false;
        }
        if (salesforceAction.getToken() != null && !salesforceAction.getToken().equals(getToken())) {
            return false;
        }
        if ((salesforceAction.getUrl() == null) ^ (getUrl() == null)) {
            return false;
        }
        return salesforceAction.getUrl() == null || salesforceAction.getUrl().equals(getUrl());
    }
}
