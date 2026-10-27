package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingResult implements Serializable {
    private Map<String, String> attributes;
    private String billingGroupName;
    private String defaultClientId;
    private String thingArn;
    private String thingId;
    private String thingName;
    private String thingTypeName;
    private Long version;

    public String getDefaultClientId() {
        return this.defaultClientId;
    }

    public void setDefaultClientId(String str) {
        this.defaultClientId = str;
    }

    public DescribeThingResult withDefaultClientId(String str) {
        this.defaultClientId = str;
        return this;
    }

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public DescribeThingResult withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getThingId() {
        return this.thingId;
    }

    public void setThingId(String str) {
        this.thingId = str;
    }

    public DescribeThingResult withThingId(String str) {
        this.thingId = str;
        return this;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public DescribeThingResult withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public DescribeThingResult withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public DescribeThingResult withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public DescribeThingResult addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (this.attributes.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.attributes.put(str, str2);
        return this;
    }

    public DescribeThingResult clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public DescribeThingResult withVersion(Long l) {
        this.version = l;
        return this;
    }

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public DescribeThingResult withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDefaultClientId() != null) {
            sb.append("defaultClientId: " + getDefaultClientId() + ",");
        }
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getThingId() != null) {
            sb.append("thingId: " + getThingId() + ",");
        }
        if (getThingArn() != null) {
            sb.append("thingArn: " + getThingArn() + ",");
        }
        if (getThingTypeName() != null) {
            sb.append("thingTypeName: " + getThingTypeName() + ",");
        }
        if (getAttributes() != null) {
            sb.append("attributes: " + getAttributes() + ",");
        }
        if (getVersion() != null) {
            sb.append("version: " + getVersion() + ",");
        }
        if (getBillingGroupName() != null) {
            sb.append("billingGroupName: " + getBillingGroupName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((getDefaultClientId() == null ? 0 : getDefaultClientId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getThingId() == null ? 0 : getThingId().hashCode())) * 31) + (getThingArn() == null ? 0 : getThingArn().hashCode())) * 31) + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode())) * 31) + (getAttributes() == null ? 0 : getAttributes().hashCode())) * 31) + (getVersion() == null ? 0 : getVersion().hashCode())) * 31) + (getBillingGroupName() != null ? getBillingGroupName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingResult)) {
            return false;
        }
        DescribeThingResult describeThingResult = (DescribeThingResult) obj;
        if ((describeThingResult.getDefaultClientId() == null) ^ (getDefaultClientId() == null)) {
            return false;
        }
        if (describeThingResult.getDefaultClientId() != null && !describeThingResult.getDefaultClientId().equals(getDefaultClientId())) {
            return false;
        }
        if ((describeThingResult.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (describeThingResult.getThingName() != null && !describeThingResult.getThingName().equals(getThingName())) {
            return false;
        }
        if ((describeThingResult.getThingId() == null) ^ (getThingId() == null)) {
            return false;
        }
        if (describeThingResult.getThingId() != null && !describeThingResult.getThingId().equals(getThingId())) {
            return false;
        }
        if ((describeThingResult.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        if (describeThingResult.getThingArn() != null && !describeThingResult.getThingArn().equals(getThingArn())) {
            return false;
        }
        if ((describeThingResult.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (describeThingResult.getThingTypeName() != null && !describeThingResult.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((describeThingResult.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (describeThingResult.getAttributes() != null && !describeThingResult.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((describeThingResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        if (describeThingResult.getVersion() != null && !describeThingResult.getVersion().equals(getVersion())) {
            return false;
        }
        if ((describeThingResult.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        return describeThingResult.getBillingGroupName() == null || describeThingResult.getBillingGroupName().equals(getBillingGroupName());
    }
}
