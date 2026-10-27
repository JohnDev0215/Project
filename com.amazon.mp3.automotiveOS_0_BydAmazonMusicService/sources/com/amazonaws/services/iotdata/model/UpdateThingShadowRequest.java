package com.amazonaws.services.iotdata.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateThingShadowRequest extends AmazonWebServiceRequest implements Serializable {
    private ByteBuffer payload;
    private String shadowName;
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public UpdateThingShadowRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getShadowName() {
        return this.shadowName;
    }

    public void setShadowName(String str) {
        this.shadowName = str;
    }

    public UpdateThingShadowRequest withShadowName(String str) {
        this.shadowName = str;
        return this;
    }

    public ByteBuffer getPayload() {
        return this.payload;
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
    }

    public UpdateThingShadowRequest withPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getShadowName() != null) {
            sb.append("shadowName: " + getShadowName() + ",");
        }
        if (getPayload() != null) {
            sb.append("payload: " + getPayload());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getShadowName() == null ? 0 : getShadowName().hashCode())) * 31) + (getPayload() != null ? getPayload().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateThingShadowRequest)) {
            return false;
        }
        UpdateThingShadowRequest updateThingShadowRequest = (UpdateThingShadowRequest) obj;
        if ((updateThingShadowRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (updateThingShadowRequest.getThingName() != null && !updateThingShadowRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((updateThingShadowRequest.getShadowName() == null) ^ (getShadowName() == null)) {
            return false;
        }
        if (updateThingShadowRequest.getShadowName() != null && !updateThingShadowRequest.getShadowName().equals(getShadowName())) {
            return false;
        }
        if ((updateThingShadowRequest.getPayload() == null) ^ (getPayload() == null)) {
            return false;
        }
        return updateThingShadowRequest.getPayload() == null || updateThingShadowRequest.getPayload().equals(getPayload());
    }
}
