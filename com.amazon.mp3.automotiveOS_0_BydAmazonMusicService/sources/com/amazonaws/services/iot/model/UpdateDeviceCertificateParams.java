package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDeviceCertificateParams implements Serializable {
    private String action;

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public UpdateDeviceCertificateParams withAction(String str) {
        this.action = str;
        return this;
    }

    public void setAction(DeviceCertificateUpdateAction deviceCertificateUpdateAction) {
        this.action = deviceCertificateUpdateAction.toString();
    }

    public UpdateDeviceCertificateParams withAction(DeviceCertificateUpdateAction deviceCertificateUpdateAction) {
        this.action = deviceCertificateUpdateAction.toString();
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
        if (obj == null || !(obj instanceof UpdateDeviceCertificateParams)) {
            return false;
        }
        UpdateDeviceCertificateParams updateDeviceCertificateParams = (UpdateDeviceCertificateParams) obj;
        if ((updateDeviceCertificateParams.getAction() == null) ^ (getAction() == null)) {
            return false;
        }
        return updateDeviceCertificateParams.getAction() == null || updateDeviceCertificateParams.getAction().equals(getAction());
    }
}
