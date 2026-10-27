package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class NonCompliantResource implements Serializable {
    private Map<String, String> additionalInfo;
    private ResourceIdentifier resourceIdentifier;
    private String resourceType;

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String str) {
        this.resourceType = str;
    }

    public NonCompliantResource withResourceType(String str) {
        this.resourceType = str;
        return this;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType.toString();
    }

    public NonCompliantResource withResourceType(ResourceType resourceType) {
        this.resourceType = resourceType.toString();
        return this;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public NonCompliantResource withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        return this;
    }

    public Map<String, String> getAdditionalInfo() {
        return this.additionalInfo;
    }

    public void setAdditionalInfo(Map<String, String> map) {
        this.additionalInfo = map;
    }

    public NonCompliantResource withAdditionalInfo(Map<String, String> map) {
        this.additionalInfo = map;
        return this;
    }

    public NonCompliantResource addadditionalInfoEntry(String str, String str2) {
        if (this.additionalInfo == null) {
            this.additionalInfo = new HashMap();
        }
        if (this.additionalInfo.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.additionalInfo.put(str, str2);
        return this;
    }

    public NonCompliantResource clearadditionalInfoEntries() {
        this.additionalInfo = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getResourceType() != null) {
            sb.append("resourceType: " + getResourceType() + ",");
        }
        if (getResourceIdentifier() != null) {
            sb.append("resourceIdentifier: " + getResourceIdentifier() + ",");
        }
        if (getAdditionalInfo() != null) {
            sb.append("additionalInfo: " + getAdditionalInfo());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getResourceType() == null ? 0 : getResourceType().hashCode()) + 31) * 31) + (getResourceIdentifier() == null ? 0 : getResourceIdentifier().hashCode())) * 31) + (getAdditionalInfo() != null ? getAdditionalInfo().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NonCompliantResource)) {
            return false;
        }
        NonCompliantResource nonCompliantResource = (NonCompliantResource) obj;
        if ((nonCompliantResource.getResourceType() == null) ^ (getResourceType() == null)) {
            return false;
        }
        if (nonCompliantResource.getResourceType() != null && !nonCompliantResource.getResourceType().equals(getResourceType())) {
            return false;
        }
        if ((nonCompliantResource.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        if (nonCompliantResource.getResourceIdentifier() != null && !nonCompliantResource.getResourceIdentifier().equals(getResourceIdentifier())) {
            return false;
        }
        if ((nonCompliantResource.getAdditionalInfo() == null) ^ (getAdditionalInfo() == null)) {
            return false;
        }
        return nonCompliantResource.getAdditionalInfo() == null || nonCompliantResource.getAdditionalInfo().equals(getAdditionalInfo());
    }
}
