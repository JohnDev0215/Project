package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ThingTypeDefinition implements Serializable {
    private String thingTypeArn;
    private ThingTypeMetadata thingTypeMetadata;
    private String thingTypeName;
    private ThingTypeProperties thingTypeProperties;

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public ThingTypeDefinition withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public String getThingTypeArn() {
        return this.thingTypeArn;
    }

    public void setThingTypeArn(String str) {
        this.thingTypeArn = str;
    }

    public ThingTypeDefinition withThingTypeArn(String str) {
        this.thingTypeArn = str;
        return this;
    }

    public ThingTypeProperties getThingTypeProperties() {
        return this.thingTypeProperties;
    }

    public void setThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
    }

    public ThingTypeDefinition withThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
        return this;
    }

    public ThingTypeMetadata getThingTypeMetadata() {
        return this.thingTypeMetadata;
    }

    public void setThingTypeMetadata(ThingTypeMetadata thingTypeMetadata) {
        this.thingTypeMetadata = thingTypeMetadata;
    }

    public ThingTypeDefinition withThingTypeMetadata(ThingTypeMetadata thingTypeMetadata) {
        this.thingTypeMetadata = thingTypeMetadata;
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
        if (getThingTypeProperties() != null) {
            sb.append("thingTypeProperties: " + getThingTypeProperties() + ",");
        }
        if (getThingTypeMetadata() != null) {
            sb.append("thingTypeMetadata: " + getThingTypeMetadata());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getThingTypeName() == null ? 0 : getThingTypeName().hashCode()) + 31) * 31) + (getThingTypeArn() == null ? 0 : getThingTypeArn().hashCode())) * 31) + (getThingTypeProperties() == null ? 0 : getThingTypeProperties().hashCode())) * 31) + (getThingTypeMetadata() != null ? getThingTypeMetadata().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingTypeDefinition)) {
            return false;
        }
        ThingTypeDefinition thingTypeDefinition = (ThingTypeDefinition) obj;
        if ((thingTypeDefinition.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (thingTypeDefinition.getThingTypeName() != null && !thingTypeDefinition.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((thingTypeDefinition.getThingTypeArn() == null) ^ (getThingTypeArn() == null)) {
            return false;
        }
        if (thingTypeDefinition.getThingTypeArn() != null && !thingTypeDefinition.getThingTypeArn().equals(getThingTypeArn())) {
            return false;
        }
        if ((thingTypeDefinition.getThingTypeProperties() == null) ^ (getThingTypeProperties() == null)) {
            return false;
        }
        if (thingTypeDefinition.getThingTypeProperties() != null && !thingTypeDefinition.getThingTypeProperties().equals(getThingTypeProperties())) {
            return false;
        }
        if ((thingTypeDefinition.getThingTypeMetadata() == null) ^ (getThingTypeMetadata() == null)) {
            return false;
        }
        return thingTypeDefinition.getThingTypeMetadata() == null || thingTypeDefinition.getThingTypeMetadata().equals(getThingTypeMetadata());
    }
}
