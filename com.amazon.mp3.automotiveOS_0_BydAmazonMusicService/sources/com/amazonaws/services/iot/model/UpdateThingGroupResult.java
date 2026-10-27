package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateThingGroupResult implements Serializable {
    private Long version;

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public UpdateThingGroupResult withVersion(Long l) {
        this.version = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getVersion() != null) {
            sb.append("version: " + getVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getVersion() == null ? 0 : getVersion().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateThingGroupResult)) {
            return false;
        }
        UpdateThingGroupResult updateThingGroupResult = (UpdateThingGroupResult) obj;
        if ((updateThingGroupResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        return updateThingGroupResult.getVersion() == null || updateThingGroupResult.getVersion().equals(getVersion());
    }
}
