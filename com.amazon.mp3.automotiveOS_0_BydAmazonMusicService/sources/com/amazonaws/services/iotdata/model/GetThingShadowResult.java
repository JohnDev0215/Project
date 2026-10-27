package com.amazonaws.services.iotdata.model;

import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
public class GetThingShadowResult implements Serializable {
    private ByteBuffer payload;

    public ByteBuffer getPayload() {
        return this.payload;
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
    }

    public GetThingShadowResult withPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPayload() != null) {
            sb.append("payload: " + getPayload());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getPayload() == null ? 0 : getPayload().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetThingShadowResult)) {
            return false;
        }
        GetThingShadowResult getThingShadowResult = (GetThingShadowResult) obj;
        if ((getThingShadowResult.getPayload() == null) ^ (getPayload() == null)) {
            return false;
        }
        return getThingShadowResult.getPayload() == null || getThingShadowResult.getPayload().equals(getPayload());
    }
}
