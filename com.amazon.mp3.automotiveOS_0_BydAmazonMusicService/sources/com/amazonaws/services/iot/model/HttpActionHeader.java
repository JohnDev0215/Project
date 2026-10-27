package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class HttpActionHeader implements Serializable {
    private String key;
    private String value;

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public HttpActionHeader withKey(String str) {
        this.key = str;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public HttpActionHeader withValue(String str) {
        this.value = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKey() != null) {
            sb.append("key: " + getKey() + ",");
        }
        if (getValue() != null) {
            sb.append("value: " + getValue());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getKey() == null ? 0 : getKey().hashCode()) + 31) * 31) + (getValue() != null ? getValue().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HttpActionHeader)) {
            return false;
        }
        HttpActionHeader httpActionHeader = (HttpActionHeader) obj;
        if ((httpActionHeader.getKey() == null) ^ (getKey() == null)) {
            return false;
        }
        if (httpActionHeader.getKey() != null && !httpActionHeader.getKey().equals(getKey())) {
            return false;
        }
        if ((httpActionHeader.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        return httpActionHeader.getValue() == null || httpActionHeader.getValue().equals(getValue());
    }
}
