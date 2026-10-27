package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String thingGroupName;

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public DeleteThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public DeleteThingGroupRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupName() != null) {
            sb.append("thingGroupName: " + getThingGroupName() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getExpectedVersion() != null ? getExpectedVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteThingGroupRequest)) {
            return false;
        }
        DeleteThingGroupRequest deleteThingGroupRequest = (DeleteThingGroupRequest) obj;
        if ((deleteThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (deleteThingGroupRequest.getThingGroupName() != null && !deleteThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((deleteThingGroupRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return deleteThingGroupRequest.getExpectedVersion() == null || deleteThingGroupRequest.getExpectedVersion().equals(getExpectedVersion());
    }
}
