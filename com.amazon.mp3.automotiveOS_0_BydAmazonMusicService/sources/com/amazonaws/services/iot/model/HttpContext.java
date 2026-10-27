package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class HttpContext implements Serializable {
    private Map<String, String> headers;
    private String queryString;

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public HttpContext withHeaders(Map<String, String> map) {
        this.headers = map;
        return this;
    }

    public HttpContext addheadersEntry(String str, String str2) {
        if (this.headers == null) {
            this.headers = new HashMap();
        }
        if (this.headers.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.headers.put(str, str2);
        return this;
    }

    public HttpContext clearheadersEntries() {
        this.headers = null;
        return this;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public HttpContext withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getHeaders() != null) {
            sb.append("headers: " + getHeaders() + ",");
        }
        if (getQueryString() != null) {
            sb.append("queryString: " + getQueryString());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getHeaders() == null ? 0 : getHeaders().hashCode()) + 31) * 31) + (getQueryString() != null ? getQueryString().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HttpContext)) {
            return false;
        }
        HttpContext httpContext = (HttpContext) obj;
        if ((httpContext.getHeaders() == null) ^ (getHeaders() == null)) {
            return false;
        }
        if (httpContext.getHeaders() != null && !httpContext.getHeaders().equals(getHeaders())) {
            return false;
        }
        if ((httpContext.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        return httpContext.getQueryString() == null || httpContext.getQueryString().equals(getQueryString());
    }
}
