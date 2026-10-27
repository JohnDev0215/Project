package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateCACertificateParams implements Serializable {
    private String action;

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public UpdateCACertificateParams withAction(String str) {
        this.action = str;
        return this;
    }

    public void setAction(CACertificateUpdateAction cACertificateUpdateAction) {
        this.action = cACertificateUpdateAction.toString();
    }

    public UpdateCACertificateParams withAction(CACertificateUpdateAction cACertificateUpdateAction) {
        this.action = cACertificateUpdateAction.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAction() != null) {
            sb.append("action: " + getAction());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getAction() == null ? 0 : getAction().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateCACertificateParams)) {
            return false;
        }
        UpdateCACertificateParams updateCACertificateParams = (UpdateCACertificateParams) obj;
        if ((updateCACertificateParams.getAction() == null) ^ (getAction() == null)) {
            return false;
        }
        return updateCACertificateParams.getAction() == null || updateCACertificateParams.getAction().equals(getAction());
    }
}
