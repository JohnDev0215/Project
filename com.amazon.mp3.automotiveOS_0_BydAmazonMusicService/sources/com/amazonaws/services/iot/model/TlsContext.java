package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class TlsContext implements Serializable {
    private String serverName;

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String str) {
        this.serverName = str;
    }

    public TlsContext withServerName(String str) {
        this.serverName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getServerName() != null) {
            sb.append("serverName: " + getServerName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getServerName() == null ? 0 : getServerName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TlsContext)) {
            return false;
        }
        TlsContext tlsContext = (TlsContext) obj;
        if ((tlsContext.getServerName() == null) ^ (getServerName() == null)) {
            return false;
        }
        return tlsContext.getServerName() == null || tlsContext.getServerName().equals(getServerName());
    }
}
