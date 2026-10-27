package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class OutgoingCertificate implements Serializable {
    private String certificateArn;
    private String certificateId;
    private Date creationDate;
    private Date transferDate;
    private String transferMessage;
    private String transferredTo;

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public OutgoingCertificate withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public OutgoingCertificate withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getTransferredTo() {
        return this.transferredTo;
    }

    public void setTransferredTo(String str) {
        this.transferredTo = str;
    }

    public OutgoingCertificate withTransferredTo(String str) {
        this.transferredTo = str;
        return this;
    }

    public Date getTransferDate() {
        return this.transferDate;
    }

    public void setTransferDate(Date date) {
        this.transferDate = date;
    }

    public OutgoingCertificate withTransferDate(Date date) {
        this.transferDate = date;
        return this;
    }

    public String getTransferMessage() {
        return this.transferMessage;
    }

    public void setTransferMessage(String str) {
        this.transferMessage = str;
    }

    public OutgoingCertificate withTransferMessage(String str) {
        this.transferMessage = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public OutgoingCertificate withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateArn() != null) {
            sb.append("certificateArn: " + getCertificateArn() + ",");
        }
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId() + ",");
        }
        if (getTransferredTo() != null) {
            sb.append("transferredTo: " + getTransferredTo() + ",");
        }
        if (getTransferDate() != null) {
            sb.append("transferDate: " + getTransferDate() + ",");
        }
        if (getTransferMessage() != null) {
            sb.append("transferMessage: " + getTransferMessage() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getTransferredTo() == null ? 0 : getTransferredTo().hashCode())) * 31) + (getTransferDate() == null ? 0 : getTransferDate().hashCode())) * 31) + (getTransferMessage() == null ? 0 : getTransferMessage().hashCode())) * 31) + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OutgoingCertificate)) {
            return false;
        }
        OutgoingCertificate outgoingCertificate = (OutgoingCertificate) obj;
        if ((outgoingCertificate.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (outgoingCertificate.getCertificateArn() != null && !outgoingCertificate.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((outgoingCertificate.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (outgoingCertificate.getCertificateId() != null && !outgoingCertificate.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((outgoingCertificate.getTransferredTo() == null) ^ (getTransferredTo() == null)) {
            return false;
        }
        if (outgoingCertificate.getTransferredTo() != null && !outgoingCertificate.getTransferredTo().equals(getTransferredTo())) {
            return false;
        }
        if ((outgoingCertificate.getTransferDate() == null) ^ (getTransferDate() == null)) {
            return false;
        }
        if (outgoingCertificate.getTransferDate() != null && !outgoingCertificate.getTransferDate().equals(getTransferDate())) {
            return false;
        }
        if ((outgoingCertificate.getTransferMessage() == null) ^ (getTransferMessage() == null)) {
            return false;
        }
        if (outgoingCertificate.getTransferMessage() != null && !outgoingCertificate.getTransferMessage().equals(getTransferMessage())) {
            return false;
        }
        if ((outgoingCertificate.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return outgoingCertificate.getCreationDate() == null || outgoingCertificate.getCreationDate().equals(getCreationDate());
    }
}
