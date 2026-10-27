package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeprecateThingTypeRequest extends AmazonWebServiceRequest implements Serializable {
    private String thingTypeName;
    private Boolean undoDeprecate;

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public DeprecateThingTypeRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public Boolean isUndoDeprecate() {
        return this.undoDeprecate;
    }

    public Boolean getUndoDeprecate() {
        return this.undoDeprecate;
    }

    public void setUndoDeprecate(Boolean bool) {
        this.undoDeprecate = bool;
    }

    public DeprecateThingTypeRequest withUndoDeprecate(Boolean bool) {
        this.undoDeprecate = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingTypeName() != null) {
            sb.append("thingTypeName: " + getThingTypeName() + ",");
        }
        if (getUndoDeprecate() != null) {
            sb.append("undoDeprecate: " + getUndoDeprecate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingTypeName() == null ? 0 : getThingTypeName().hashCode()) + 31) * 31) + (getUndoDeprecate() != null ? getUndoDeprecate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeprecateThingTypeRequest)) {
            return false;
        }
        DeprecateThingTypeRequest deprecateThingTypeRequest = (DeprecateThingTypeRequest) obj;
        if ((deprecateThingTypeRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (deprecateThingTypeRequest.getThingTypeName() != null && !deprecateThingTypeRequest.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((deprecateThingTypeRequest.getUndoDeprecate() == null) ^ (getUndoDeprecate() == null)) {
            return false;
        }
        return deprecateThingTypeRequest.getUndoDeprecate() == null || deprecateThingTypeRequest.getUndoDeprecate().equals(getUndoDeprecate());
    }
}
