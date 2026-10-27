package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class TransferData implements Serializable {
    private Date acceptDate;
    private Date rejectDate;
    private String rejectReason;
    private Date transferDate;
    private String transferMessage;

    public String getTransferMessage() {
        return this.transferMessage;
    }

    public void setTransferMessage(String str) {
        this.transferMessage = str;
    }

    public TransferData withTransferMessage(String str) {
        this.transferMessage = str;
        return this;
    }

    public String getRejectReason() {
        return this.rejectReason;
    }

    public void setRejectReason(String str) {
        this.rejectReason = str;
    }

    public TransferData withRejectReason(String str) {
        this.rejectReason = str;
        return this;
    }

    public Date getTransferDate() {
        return this.transferDate;
    }

    public void setTransferDate(Date date) {
        this.transferDate = date;
    }

    public TransferData withTransferDate(Date date) {
        this.transferDate = date;
        return this;
    }

    public Date getAcceptDate() {
        return this.acceptDate;
    }

    public void setAcceptDate(Date date) {
        this.acceptDate = date;
    }

    public TransferData withAcceptDate(Date date) {
        this.acceptDate = date;
        return this;
    }

    public Date getRejectDate() {
        return this.rejectDate;
    }

    public void setRejectDate(Date date) {
        this.rejectDate = date;
    }

    public TransferData withRejectDate(Date date) {
        this.rejectDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTransferMessage() != null) {
            sb.append("transferMessage: " + getTransferMessage() + ",");
        }
        if (getRejectReason() != null) {
            sb.append("rejectReason: " + getRejectReason() + ",");
        }
        if (getTransferDate() != null) {
            sb.append("transferDate: " + getTransferDate() + ",");
        }
        if (getAcceptDate() != null) {
            sb.append("acceptDate: " + getAcceptDate() + ",");
        }
        if (getRejectDate() != null) {
            sb.append("rejectDate: " + getRejectDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getTransferMessage() == null ? 0 : getTransferMessage().hashCode()) + 31) * 31) + (getRejectReason() == null ? 0 : getRejectReason().hashCode())) * 31) + (getTransferDate() == null ? 0 : getTransferDate().hashCode())) * 31) + (getAcceptDate() == null ? 0 : getAcceptDate().hashCode())) * 31) + (getRejectDate() != null ? getRejectDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TransferData)) {
            return false;
        }
        TransferData transferData = (TransferData) obj;
        if ((transferData.getTransferMessage() == null) ^ (getTransferMessage() == null)) {
            return false;
        }
        if (transferData.getTransferMessage() != null && !transferData.getTransferMessage().equals(getTransferMessage())) {
            return false;
        }
        if ((transferData.getRejectReason() == null) ^ (getRejectReason() == null)) {
            return false;
        }
        if (transferData.getRejectReason() != null && !transferData.getRejectReason().equals(getRejectReason())) {
            return false;
        }
        if ((transferData.getTransferDate() == null) ^ (getTransferDate() == null)) {
            return false;
        }
        if (transferData.getTransferDate() != null && !transferData.getTransferDate().equals(getTransferDate())) {
            return false;
        }
        if ((transferData.getAcceptDate() == null) ^ (getAcceptDate() == null)) {
            return false;
        }
        if (transferData.getAcceptDate() != null && !transferData.getAcceptDate().equals(getAcceptDate())) {
            return false;
        }
        if ((transferData.getRejectDate() == null) ^ (getRejectDate() == null)) {
            return false;
        }
        return transferData.getRejectDate() == null || transferData.getRejectDate().equals(getRejectDate());
    }
}
