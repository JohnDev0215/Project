package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteThingRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public DeleteThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public DeleteThingRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getExpectedVersion() != null ? getExpectedVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteThingRequest)) {
            return false;
        }
        DeleteThingRequest deleteThingRequest = (DeleteThingRequest) obj;
        if ((deleteThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (deleteThingRequest.getThingName() != null && !deleteThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((deleteThingRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return deleteThingRequest.getExpectedVersion() == null || deleteThingRequest.getExpectedVersion().equals(getExpectedVersion());
    }
}
