package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateThingRequest extends AmazonWebServiceRequest implements Serializable {
    private AttributePayload attributePayload;
    private Long expectedVersion;
    private Boolean removeThingType;
    private String thingName;
    private String thingTypeName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public UpdateThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public UpdateThingRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public AttributePayload getAttributePayload() {
        return this.attributePayload;
    }

    public void setAttributePayload(AttributePayload attributePayload) {
        this.attributePayload = attributePayload;
    }

    public UpdateThingRequest withAttributePayload(AttributePayload attributePayload) {
        this.attributePayload = attributePayload;
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public UpdateThingRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public Boolean isRemoveThingType() {
        return this.removeThingType;
    }

    public Boolean getRemoveThingType() {
        return this.removeThingType;
    }

    public void setRemoveThingType(Boolean bool) {
        this.removeThingType = bool;
    }

    public UpdateThingRequest withRemoveThingType(Boolean bool) {
        this.removeThingType = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getThingTypeName() != null) {
            sb.append("thingTypeName: " + getThingTypeName() + ",");
        }
        if (getAttributePayload() != null) {
            sb.append("attributePayload: " + getAttributePayload() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion() + ",");
        }
        if (getRemoveThingType() != null) {
            sb.append("removeThingType: " + getRemoveThingType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode())) * 31) + (getAttributePayload() == null ? 0 : getAttributePayload().hashCode())) * 31) + (getExpectedVersion() == null ? 0 : getExpectedVersion().hashCode())) * 31) + (getRemoveThingType() != null ? getRemoveThingType().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateThingRequest)) {
            return false;
        }
        UpdateThingRequest updateThingRequest = (UpdateThingRequest) obj;
        if ((updateThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (updateThingRequest.getThingName() != null && !updateThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((updateThingRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (updateThingRequest.getThingTypeName() != null && !updateThingRequest.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((updateThingRequest.getAttributePayload() == null) ^ (getAttributePayload() == null)) {
            return false;
        }
        if (updateThingRequest.getAttributePayload() != null && !updateThingRequest.getAttributePayload().equals(getAttributePayload())) {
            return false;
        }
        if ((updateThingRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        if (updateThingRequest.getExpectedVersion() != null && !updateThingRequest.getExpectedVersion().equals(getExpectedVersion())) {
            return false;
        }
        if ((updateThingRequest.getRemoveThingType() == null) ^ (getRemoveThingType() == null)) {
            return false;
        }
        return updateThingRequest.getRemoveThingType() == null || updateThingRequest.getRemoveThingType().equals(getRemoveThingType());
    }
}
