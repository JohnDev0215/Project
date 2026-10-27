package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class HttpAction implements Serializable {
    private HttpAuthorization auth;
    private String confirmationUrl;
    private List<HttpActionHeader> headers;
    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public HttpAction withUrl(String str) {
        this.url = str;
        return this;
    }

    public String getConfirmationUrl() {
        return this.confirmationUrl;
    }

    public void setConfirmationUrl(String str) {
        this.confirmationUrl = str;
    }

    public HttpAction withConfirmationUrl(String str) {
        this.confirmationUrl = str;
        return this;
    }

    public List<HttpActionHeader> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Collection<HttpActionHeader> collection) {
        if (collection == null) {
            this.headers = null;
        } else {
            this.headers = new ArrayList(collection);
        }
    }

    public HttpAction withHeaders(HttpActionHeader... httpActionHeaderArr) {
        if (getHeaders() == null) {
            this.headers = new ArrayList(httpActionHeaderArr.length);
        }
        for (HttpActionHeader httpActionHeader : httpActionHeaderArr) {
            this.headers.add(httpActionHeader);
        }
        return this;
    }

    public HttpAction withHeaders(Collection<HttpActionHeader> collection) {
        setHeaders(collection);
        return this;
    }

    public HttpAuthorization getAuth() {
        return this.auth;
    }

    public void setAuth(HttpAuthorization httpAuthorization) {
        this.auth = httpAuthorization;
    }

    public HttpAction withAuth(HttpAuthorization httpAuthorization) {
        this.auth = httpAuthorization;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getUrl() != null) {
            sb.append("url: " + getUrl() + ",");
        }
        if (getConfirmationUrl() != null) {
            sb.append("confirmationUrl: " + getConfirmationUrl() + ",");
        }
        if (getHeaders() != null) {
            sb.append("headers: " + getHeaders() + ",");
        }
        if (getAuth() != null) {
            sb.append("auth: " + getAuth());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getUrl() == null ? 0 : getUrl().hashCode()) + 31) * 31) + (getConfirmationUrl() == null ? 0 : getConfirmationUrl().hashCode())) * 31) + (getHeaders() == null ? 0 : getHeaders().hashCode())) * 31) + (getAuth() != null ? getAuth().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HttpAction)) {
            return false;
        }
        HttpAction httpAction = (HttpAction) obj;
        if ((httpAction.getUrl() == null) ^ (getUrl() == null)) {
            return false;
        }
        if (httpAction.getUrl() != null && !httpAction.getUrl().equals(getUrl())) {
            return false;
        }
        if ((httpAction.getConfirmationUrl() == null) ^ (getConfirmationUrl() == null)) {
            return false;
        }
        if (httpAction.getConfirmationUrl() != null && !httpAction.getConfirmationUrl().equals(getConfirmationUrl())) {
            return false;
        }
        if ((httpAction.getHeaders() == null) ^ (getHeaders() == null)) {
            return false;
        }
        if (httpAction.getHeaders() != null && !httpAction.getHeaders().equals(getHeaders())) {
            return false;
        }
        if ((httpAction.getAuth() == null) ^ (getAuth() == null)) {
            return false;
        }
        return httpAction.getAuth() == null || httpAction.getAuth().equals(getAuth());
    }
}
