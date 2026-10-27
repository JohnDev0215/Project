package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateThingResult implements Serializable {
    private String thingArn;
    private String thingId;
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public CreateThingResult withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public CreateThingResult withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public String getThingId() {
        return this.thingId;
    }

    public void setThingId(String str) {
        this.thingId = str;
    }

    public CreateThingResult withThingId(String str) {
        this.thingId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getThingArn() != null) {
            sb.append("thingArn: " + getThingArn() + ",");
        }
        if (getThingId() != null) {
            sb.append("thingId: " + getThingId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getThingArn() == null ? 0 : getThingArn().hashCode())) * 31) + (getThingId() != null ? getThingId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateThingResult)) {
            return false;
        }
        CreateThingResult createThingResult = (CreateThingResult) obj;
        if ((createThingResult.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (createThingResult.getThingName() != null && !createThingResult.getThingName().equals(getThingName())) {
            return false;
        }
        if ((createThingResult.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        if (createThingResult.getThingArn() != null && !createThingResult.getThingArn().equals(getThingArn())) {
            return false;
        }
        if ((createThingResult.getThingId() == null) ^ (getThingId() == null)) {
            return false;
        }
        return createThingResult.getThingId() == null || createThingResult.getThingId().equals(getThingId());
    }
}
