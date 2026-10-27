package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ThingGroupProperties implements Serializable {
    private AttributePayload attributePayload;
    private String thingGroupDescription;

    public String getThingGroupDescription() {
        return this.thingGroupDescription;
    }

    public void setThingGroupDescription(String str) {
        this.thingGroupDescription = str;
    }

    public ThingGroupProperties withThingGroupDescription(String str) {
        this.thingGroupDescription = str;
        return this;
    }

    public AttributePayload getAttributePayload() {
        return this.attributePayload;
    }

    public void setAttributePayload(AttributePayload attributePayload) {
        this.attributePayload = attributePayload;
    }

    public ThingGroupProperties withAttributePayload(AttributePayload attributePayload) {
        this.attributePayload = attributePayload;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupDescription() != null) {
            sb.append("thingGroupDescription: " + getThingGroupDescription() + ",");
        }
        if (getAttributePayload() != null) {
            sb.append("attributePayload: " + getAttributePayload());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingGroupDescription() == null ? 0 : getThingGroupDescription().hashCode()) + 31) * 31) + (getAttributePayload() != null ? getAttributePayload().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingGroupProperties)) {
            return false;
        }
        ThingGroupProperties thingGroupProperties = (ThingGroupProperties) obj;
        if ((thingGroupProperties.getThingGroupDescription() == null) ^ (getThingGroupDescription() == null)) {
            return false;
        }
        if (thingGroupProperties.getThingGroupDescription() != null && !thingGroupProperties.getThingGroupDescription().equals(getThingGroupDescription())) {
            return false;
        }
        if ((thingGroupProperties.getAttributePayload() == null) ^ (getAttributePayload() == null)) {
            return false;
        }
        return thingGroupProperties.getAttributePayload() == null || thingGroupProperties.getAttributePayload().equals(getAttributePayload());
    }
}
