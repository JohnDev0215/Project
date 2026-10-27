package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class OTAUpdateSummary implements Serializable {
    private Date creationDate;
    private String otaUpdateArn;
    private String otaUpdateId;

    public String getOtaUpdateId() {
        return this.otaUpdateId;
    }

    public void setOtaUpdateId(String str) {
        this.otaUpdateId = str;
    }

    public OTAUpdateSummary withOtaUpdateId(String str) {
        this.otaUpdateId = str;
        return this;
    }

    public String getOtaUpdateArn() {
        return this.otaUpdateArn;
    }

    public void setOtaUpdateArn(String str) {
        this.otaUpdateArn = str;
    }

    public OTAUpdateSummary withOtaUpdateArn(String str) {
        this.otaUpdateArn = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public OTAUpdateSummary withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getOtaUpdateId() != null) {
            sb.append("otaUpdateId: " + getOtaUpdateId() + ",");
        }
        if (getOtaUpdateArn() != null) {
            sb.append("otaUpdateArn: " + getOtaUpdateArn() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getOtaUpdateId() == null ? 0 : getOtaUpdateId().hashCode()) + 31) * 31) + (getOtaUpdateArn() == null ? 0 : getOtaUpdateArn().hashCode())) * 31) + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OTAUpdateSummary)) {
            return false;
        }
        OTAUpdateSummary oTAUpdateSummary = (OTAUpdateSummary) obj;
        if ((oTAUpdateSummary.getOtaUpdateId() == null) ^ (getOtaUpdateId() == null)) {
            return false;
        }
        if (oTAUpdateSummary.getOtaUpdateId() != null && !oTAUpdateSummary.getOtaUpdateId().equals(getOtaUpdateId())) {
            return false;
        }
        if ((oTAUpdateSummary.getOtaUpdateArn() == null) ^ (getOtaUpdateArn() == null)) {
            return false;
        }
        if (oTAUpdateSummary.getOtaUpdateArn() != null && !oTAUpdateSummary.getOtaUpdateArn().equals(getOtaUpdateArn())) {
            return false;
        }
        if ((oTAUpdateSummary.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return oTAUpdateSummary.getCreationDate() == null || oTAUpdateSummary.getCreationDate().equals(getCreationDate());
    }
}
