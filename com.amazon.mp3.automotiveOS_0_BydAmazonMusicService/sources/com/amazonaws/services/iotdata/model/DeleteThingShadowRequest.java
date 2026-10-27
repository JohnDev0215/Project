package com.amazonaws.services.iotdata.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteThingShadowRequest extends AmazonWebServiceRequest implements Serializable {
    private String shadowName;
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public DeleteThingShadowRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getShadowName() {
        return this.shadowName;
    }

    public void setShadowName(String str) {
        this.shadowName = str;
    }

    public DeleteThingShadowRequest withShadowName(String str) {
        this.shadowName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getShadowName() != null) {
            sb.append("shadowName: " + getShadowName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getShadowName() != null ? getShadowName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteThingShadowRequest)) {
            return false;
        }
        DeleteThingShadowRequest deleteThingShadowRequest = (DeleteThingShadowRequest) obj;
        if ((deleteThingShadowRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (deleteThingShadowRequest.getThingName() != null && !deleteThingShadowRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((deleteThingShadowRequest.getShadowName() == null) ^ (getShadowName() == null)) {
            return false;
        }
        return deleteThingShadowRequest.getShadowName() == null || deleteThingShadowRequest.getShadowName().equals(getShadowName());
    }
}
