package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class TransferCertificateResult implements Serializable {
    private String transferredCertificateArn;

    public String getTransferredCertificateArn() {
        return this.transferredCertificateArn;
    }

    public void setTransferredCertificateArn(String str) {
        this.transferredCertificateArn = str;
    }

    public TransferCertificateResult withTransferredCertificateArn(String str) {
        this.transferredCertificateArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTransferredCertificateArn() != null) {
            sb.append("transferredCertificateArn: " + getTransferredCertificateArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTransferredCertificateArn() == null ? 0 : getTransferredCertificateArn().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TransferCertificateResult)) {
            return false;
        }
        TransferCertificateResult transferCertificateResult = (TransferCertificateResult) obj;
        if ((transferCertificateResult.getTransferredCertificateArn() == null) ^ (getTransferredCertificateArn() == null)) {
            return false;
        }
        return transferCertificateResult.getTransferredCertificateArn() == null || transferCertificateResult.getTransferredCertificateArn().equals(getTransferredCertificateArn());
    }
}
