package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String thingGroupName;
    private ThingGroupProperties thingGroupProperties;

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public UpdateThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public ThingGroupProperties getThingGroupProperties() {
        return this.thingGroupProperties;
    }

    public void setThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
    }

    public UpdateThingGroupRequest withThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public UpdateThingGroupRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupName() != null) {
            sb.append("thingGroupName: " + getThingGroupName() + ",");
        }
        if (getThingGroupProperties() != null) {
            sb.append("thingGroupProperties: " + getThingGroupProperties() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupProperties() == null ? 0 : getThingGroupProperties().hashCode())) * 31) + (getExpectedVersion() != null ? getExpectedVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateThingGroupRequest)) {
            return false;
        }
        UpdateThingGroupRequest updateThingGroupRequest = (UpdateThingGroupRequest) obj;
        if ((updateThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (updateThingGroupRequest.getThingGroupName() != null && !updateThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((updateThingGroupRequest.getThingGroupProperties() == null) ^ (getThingGroupProperties() == null)) {
            return false;
        }
        if (updateThingGroupRequest.getThingGroupProperties() != null && !updateThingGroupRequest.getThingGroupProperties().equals(getThingGroupProperties())) {
            return false;
        }
        if ((updateThingGroupRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return updateThingGroupRequest.getExpectedVersion() == null || updateThingGroupRequest.getExpectedVersion().equals(getExpectedVersion());
    }
}
