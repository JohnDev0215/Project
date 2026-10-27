package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateThingTypeResult implements Serializable {
    private String thingTypeArn;
    private String thingTypeId;
    private String thingTypeName;

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public CreateThingTypeResult withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public String getThingTypeArn() {
        return this.thingTypeArn;
    }

    public void setThingTypeArn(String str) {
        this.thingTypeArn = str;
    }

    public CreateThingTypeResult withThingTypeArn(String str) {
        this.thingTypeArn = str;
        return this;
    }

    public String getThingTypeId() {
        return this.thingTypeId;
    }

    public void setThingTypeId(String str) {
        this.thingTypeId = str;
    }

    public CreateThingTypeResult withThingTypeId(String str) {
        this.thingTypeId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingTypeName() != null) {
            sb.append("thingTypeName: " + getThingTypeName() + ",");
        }
        if (getThingTypeArn() != null) {
            sb.append("thingTypeArn: " + getThingTypeArn() + ",");
        }
        if (getThingTypeId() != null) {
            sb.append("thingTypeId: " + getThingTypeId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getThingTypeName() == null ? 0 : getThingTypeName().hashCode()) + 31) * 31) + (getThingTypeArn() == null ? 0 : getThingTypeArn().hashCode())) * 31) + (getThingTypeId() != null ? getThingTypeId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateThingTypeResult)) {
            return false;
        }
        CreateThingTypeResult createThingTypeResult = (CreateThingTypeResult) obj;
        if ((createThingTypeResult.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (createThingTypeResult.getThingTypeName() != null && !createThingTypeResult.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((createThingTypeResult.getThingTypeArn() == null) ^ (getThingTypeArn() == null)) {
            return false;
        }
        if (createThingTypeResult.getThingTypeArn() != null && !createThingTypeResult.getThingTypeArn().equals(getThingTypeArn())) {
            return false;
        }
        if ((createThingTypeResult.getThingTypeId() == null) ^ (getThingTypeId() == null)) {
            return false;
        }
        return createThingTypeResult.getThingTypeId() == null || createThingTypeResult.getThingTypeId().equals(getThingTypeId());
    }
}
