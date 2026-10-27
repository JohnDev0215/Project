package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetOTAUpdateRequest extends AmazonWebServiceRequest implements Serializable {
    private String otaUpdateId;

    public String getOtaUpdateId() {
        return this.otaUpdateId;
    }

    public void setOtaUpdateId(String str) {
        this.otaUpdateId = str;
    }

    public GetOTAUpdateRequest withOtaUpdateId(String str) {
        this.otaUpdateId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getOtaUpdateId() != null) {
            sb.append("otaUpdateId: " + getOtaUpdateId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getOtaUpdateId() == null ? 0 : getOtaUpdateId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetOTAUpdateRequest)) {
            return false;
        }
        GetOTAUpdateRequest getOTAUpdateRequest = (GetOTAUpdateRequest) obj;
        if ((getOTAUpdateRequest.getOtaUpdateId() == null) ^ (getOtaUpdateId() == null)) {
            return false;
        }
        return getOTAUpdateRequest.getOtaUpdateId() == null || getOTAUpdateRequest.getOtaUpdateId().equals(getOtaUpdateId());
    }
}
