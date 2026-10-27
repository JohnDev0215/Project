package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ServerCertificateSummary implements Serializable {
    private String serverCertificateArn;
    private String serverCertificateStatus;
    private String serverCertificateStatusDetail;

    public String getServerCertificateArn() {
        return this.serverCertificateArn;
    }

    public void setServerCertificateArn(String str) {
        this.serverCertificateArn = str;
    }

    public ServerCertificateSummary withServerCertificateArn(String str) {
        this.serverCertificateArn = str;
        return this;
    }

    public String getServerCertificateStatus() {
        return this.serverCertificateStatus;
    }

    public void setServerCertificateStatus(String str) {
        this.serverCertificateStatus = str;
    }

    public ServerCertificateSummary withServerCertificateStatus(String str) {
        this.serverCertificateStatus = str;
        return this;
    }

    public void setServerCertificateStatus(ServerCertificateStatus serverCertificateStatus) {
        this.serverCertificateStatus = serverCertificateStatus.toString();
    }

    public ServerCertificateSummary withServerCertificateStatus(ServerCertificateStatus serverCertificateStatus) {
        this.serverCertificateStatus = serverCertificateStatus.toString();
        return this;
    }

    public String getServerCertificateStatusDetail() {
        return this.serverCertificateStatusDetail;
    }

    public void setServerCertificateStatusDetail(String str) {
        this.serverCertificateStatusDetail = str;
    }

    public ServerCertificateSummary withServerCertificateStatusDetail(String str) {
        this.serverCertificateStatusDetail = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getServerCertificateArn() != null) {
            sb.append("serverCertificateArn: " + getServerCertificateArn() + ",");
        }
        if (getServerCertificateStatus() != null) {
            sb.append("serverCertificateStatus: " + getServerCertificateStatus() + ",");
        }
        if (getServerCertificateStatusDetail() != null) {
            sb.append("serverCertificateStatusDetail: " + getServerCertificateStatusDetail());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getServerCertificateArn() == null ? 0 : getServerCertificateArn().hashCode()) + 31) * 31) + (getServerCertificateStatus() == null ? 0 : getServerCertificateStatus().hashCode())) * 31) + (getServerCertificateStatusDetail() != null ? getServerCertificateStatusDetail().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ServerCertificateSummary)) {
            return false;
        }
        ServerCertificateSummary serverCertificateSummary = (ServerCertificateSummary) obj;
        if ((serverCertificateSummary.getServerCertificateArn() == null) ^ (getServerCertificateArn() == null)) {
            return false;
        }
        if (serverCertificateSummary.getServerCertificateArn() != null && !serverCertificateSummary.getServerCertificateArn().equals(getServerCertificateArn())) {
            return false;
        }
        if ((serverCertificateSummary.getServerCertificateStatus() == null) ^ (getServerCertificateStatus() == null)) {
            return false;
        }
        if (serverCertificateSummary.getServerCertificateStatus() != null && !serverCertificateSummary.getServerCertificateStatus().equals(getServerCertificateStatus())) {
            return false;
        }
        if ((serverCertificateSummary.getServerCertificateStatusDetail() == null) ^ (getServerCertificateStatusDetail() == null)) {
            return false;
        }
        return serverCertificateSummary.getServerCertificateStatusDetail() == null || serverCertificateSummary.getServerCertificateStatusDetail().equals(getServerCertificateStatusDetail());
    }
}
