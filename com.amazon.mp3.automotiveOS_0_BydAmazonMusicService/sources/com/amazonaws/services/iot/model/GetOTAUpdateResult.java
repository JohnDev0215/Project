package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetOTAUpdateResult implements Serializable {
    private OTAUpdateInfo otaUpdateInfo;

    public OTAUpdateInfo getOtaUpdateInfo() {
        return this.otaUpdateInfo;
    }

    public void setOtaUpdateInfo(OTAUpdateInfo oTAUpdateInfo) {
        this.otaUpdateInfo = oTAUpdateInfo;
    }

    public GetOTAUpdateResult withOtaUpdateInfo(OTAUpdateInfo oTAUpdateInfo) {
        this.otaUpdateInfo = oTAUpdateInfo;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getOtaUpdateInfo() != null) {
            sb.append("otaUpdateInfo: " + getOtaUpdateInfo());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getOtaUpdateInfo() == null ? 0 : getOtaUpdateInfo().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetOTAUpdateResult)) {
            return false;
        }
        GetOTAUpdateResult getOTAUpdateResult = (GetOTAUpdateResult) obj;
        if ((getOTAUpdateResult.getOtaUpdateInfo() == null) ^ (getOtaUpdateInfo() == null)) {
            return false;
        }
        return getOTAUpdateResult.getOtaUpdateInfo() == null || getOTAUpdateResult.getOtaUpdateInfo().equals(getOtaUpdateInfo());
    }
}
