package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateThingGroupResult implements Serializable {
    private String thingGroupArn;
    private String thingGroupId;
    private String thingGroupName;

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public CreateThingGroupResult withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public String getThingGroupArn() {
        return this.thingGroupArn;
    }

    public void setThingGroupArn(String str) {
        this.thingGroupArn = str;
    }

    public CreateThingGroupResult withThingGroupArn(String str) {
        this.thingGroupArn = str;
        return this;
    }

    public String getThingGroupId() {
        return this.thingGroupId;
    }

    public void setThingGroupId(String str) {
        this.thingGroupId = str;
    }

    public CreateThingGroupResult withThingGroupId(String str) {
        this.thingGroupId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupName() != null) {
            sb.append("thingGroupName: " + getThingGroupName() + ",");
        }
        if (getThingGroupArn() != null) {
            sb.append("thingGroupArn: " + getThingGroupArn() + ",");
        }
        if (getThingGroupId() != null) {
            sb.append("thingGroupId: " + getThingGroupId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupArn() == null ? 0 : getThingGroupArn().hashCode())) * 31) + (getThingGroupId() != null ? getThingGroupId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateThingGroupResult)) {
            return false;
        }
        CreateThingGroupResult createThingGroupResult = (CreateThingGroupResult) obj;
        if ((createThingGroupResult.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (createThingGroupResult.getThingGroupName() != null && !createThingGroupResult.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((createThingGroupResult.getThingGroupArn() == null) ^ (getThingGroupArn() == null)) {
            return false;
        }
        if (createThingGroupResult.getThingGroupArn() != null && !createThingGroupResult.getThingGroupArn().equals(getThingGroupArn())) {
            return false;
        }
        if ((createThingGroupResult.getThingGroupId() == null) ^ (getThingGroupId() == null)) {
            return false;
        }
        return createThingGroupResult.getThingGroupId() == null || createThingGroupResult.getThingGroupId().equals(getThingGroupId());
    }
}
