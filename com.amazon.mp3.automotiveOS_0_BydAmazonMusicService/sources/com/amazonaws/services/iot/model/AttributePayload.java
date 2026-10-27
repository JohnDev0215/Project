package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class AttributePayload implements Serializable {
    private Map<String, String> attributes;
    private Boolean merge;

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public AttributePayload withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public AttributePayload addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (this.attributes.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.attributes.put(str, str2);
        return this;
    }

    public AttributePayload clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public Boolean isMerge() {
        return this.merge;
    }

    public Boolean getMerge() {
        return this.merge;
    }

    public void setMerge(Boolean bool) {
        this.merge = bool;
    }

    public AttributePayload withMerge(Boolean bool) {
        this.merge = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAttributes() != null) {
            sb.append("attributes: " + getAttributes() + ",");
        }
        if (getMerge() != null) {
            sb.append("merge: " + getMerge());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getAttributes() == null ? 0 : getAttributes().hashCode()) + 31) * 31) + (getMerge() != null ? getMerge().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttributePayload)) {
            return false;
        }
        AttributePayload attributePayload = (AttributePayload) obj;
        if ((attributePayload.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (attributePayload.getAttributes() != null && !attributePayload.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((attributePayload.getMerge() == null) ^ (getMerge() == null)) {
            return false;
        }
        return attributePayload.getMerge() == null || attributePayload.getMerge().equals(getMerge());
    }
}
