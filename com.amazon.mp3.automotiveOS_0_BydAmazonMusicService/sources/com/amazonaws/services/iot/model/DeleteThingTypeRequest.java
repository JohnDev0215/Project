package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteThingTypeRequest extends AmazonWebServiceRequest implements Serializable {
    private String thingTypeName;

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public DeleteThingTypeRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingTypeName() != null) {
            sb.append("thingTypeName: " + getThingTypeName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteThingTypeRequest)) {
            return false;
        }
        DeleteThingTypeRequest deleteThingTypeRequest = (DeleteThingTypeRequest) obj;
        if ((deleteThingTypeRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        return deleteThingTypeRequest.getThingTypeName() == null || deleteThingTypeRequest.getThingTypeName().equals(getThingTypeName());
    }
}
