package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingTypeResult implements Serializable {
    private String thingTypeArn;
    private String thingTypeId;
    private ThingTypeMetadata thingTypeMetadata;
    private String thingTypeName;
    private ThingTypeProperties thingTypeProperties;

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public DescribeThingTypeResult withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public String getThingTypeId() {
        return this.thingTypeId;
    }

    public void setThingTypeId(String str) {
        this.thingTypeId = str;
    }

    public DescribeThingTypeResult withThingTypeId(String str) {
        this.thingTypeId = str;
        return this;
    }

    public String getThingTypeArn() {
        return this.thingTypeArn;
    }

    public void setThingTypeArn(String str) {
        this.thingTypeArn = str;
    }

    public DescribeThingTypeResult withThingTypeArn(String str) {
        this.thingTypeArn = str;
        return this;
    }

    public ThingTypeProperties getThingTypeProperties() {
        return this.thingTypeProperties;
    }

    public void setThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
    }

    public DescribeThingTypeResult withThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
        return this;
    }

    public ThingTypeMetadata getThingTypeMetadata() {
        return this.thingTypeMetadata;
    }

    public void setThingTypeMetadata(ThingTypeMetadata thingTypeMetadata) {
        this.thingTypeMetadata = thingTypeMetadata;
    }

    public DescribeThingTypeResult withThingTypeMetadata(ThingTypeMetadata thingTypeMetadata) {
        this.thingTypeMetadata = thingTypeMetadata;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingTypeName() != null) {
            sb.append("thingTypeName: " + getThingTypeName() + ",");
        }
        if (getThingTypeId() != null) {
            sb.append("thingTypeId: " + getThingTypeId() + ",");
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
        return (((((((((getThingTypeName() == null ? 0 : getThingTypeName().hashCode()) + 31) * 31) + (getThingTypeId() == null ? 0 : getThingTypeId().hashCode())) * 31) + (getThingTypeArn() == null ? 0 : getThingTypeArn().hashCode())) * 31) + (getThingTypeProperties() == null ? 0 : getThingTypeProperties().hashCode())) * 31) + (getThingTypeMetadata() != null ? getThingTypeMetadata().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingTypeResult)) {
            return false;
        }
        DescribeThingTypeResult describeThingTypeResult = (DescribeThingTypeResult) obj;
        if ((describeThingTypeResult.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (describeThingTypeResult.getThingTypeName() != null && !describeThingTypeResult.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((describeThingTypeResult.getThingTypeId() == null) ^ (getThingTypeId() == null)) {
            return false;
        }
        if (describeThingTypeResult.getThingTypeId() != null && !describeThingTypeResult.getThingTypeId().equals(getThingTypeId())) {
            return false;
        }
        if ((describeThingTypeResult.getThingTypeArn() == null) ^ (getThingTypeArn() == null)) {
            return false;
        }
        if (describeThingTypeResult.getThingTypeArn() != null && !describeThingTypeResult.getThingTypeArn().equals(getThingTypeArn())) {
            return false;
        }
        if ((describeThingTypeResult.getThingTypeProperties() == null) ^ (getThingTypeProperties() == null)) {
            return false;
        }
        if (describeThingTypeResult.getThingTypeProperties() != null && !describeThingTypeResult.getThingTypeProperties().equals(getThingTypeProperties())) {
            return false;
        }
        if ((describeThingTypeResult.getThingTypeMetadata() == null) ^ (getThingTypeMetadata() == null)) {
            return false;
        }
        return describeThingTypeResult.getThingTypeMetadata() == null || describeThingTypeResult.getThingTypeMetadata().equals(getThingTypeMetadata());
    }
}
