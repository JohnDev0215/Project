package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ThingDocument implements Serializable {
    private Map<String, String> attributes;
    private ThingConnectivity connectivity;
    private String deviceDefender;
    private String shadow;
    private List<String> thingGroupNames;
    private String thingId;
    private String thingName;
    private String thingTypeName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public ThingDocument withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getThingId() {
        return this.thingId;
    }

    public void setThingId(String str) {
        this.thingId = str;
    }

    public ThingDocument withThingId(String str) {
        this.thingId = str;
        return this;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public ThingDocument withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public List<String> getThingGroupNames() {
        return this.thingGroupNames;
    }

    public void setThingGroupNames(Collection<String> collection) {
        if (collection == null) {
            this.thingGroupNames = null;
        } else {
            this.thingGroupNames = new ArrayList(collection);
        }
    }

    public ThingDocument withThingGroupNames(String... strArr) {
        if (getThingGroupNames() == null) {
            this.thingGroupNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.thingGroupNames.add(str);
        }
        return this;
    }

    public ThingDocument withThingGroupNames(Collection<String> collection) {
        setThingGroupNames(collection);
        return this;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public ThingDocument withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public ThingDocument addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (this.attributes.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.attributes.put(str, str2);
        return this;
    }

    public ThingDocument clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public String getShadow() {
        return this.shadow;
    }

    public void setShadow(String str) {
        this.shadow = str;
    }

    public ThingDocument withShadow(String str) {
        this.shadow = str;
        return this;
    }

    public String getDeviceDefender() {
        return this.deviceDefender;
    }

    public void setDeviceDefender(String str) {
        this.deviceDefender = str;
    }

    public ThingDocument withDeviceDefender(String str) {
        this.deviceDefender = str;
        return this;
    }

    public ThingConnectivity getConnectivity() {
        return this.connectivity;
    }

    public void setConnectivity(ThingConnectivity thingConnectivity) {
        this.connectivity = thingConnectivity;
    }

    public ThingDocument withConnectivity(ThingConnectivity thingConnectivity) {
        this.connectivity = thingConnectivity;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getThingId() != null) {
            sb.append("thingId: " + getThingId() + ",");
        }
        if (getThingTypeName() != null) {
            sb.append("thingTypeName: " + getThingTypeName() + ",");
        }
        if (getThingGroupNames() != null) {
            sb.append("thingGroupNames: " + getThingGroupNames() + ",");
        }
        if (getAttributes() != null) {
            sb.append("attributes: " + getAttributes() + ",");
        }
        if (getShadow() != null) {
            sb.append("shadow: " + getShadow() + ",");
        }
        if (getDeviceDefender() != null) {
            sb.append("deviceDefender: " + getDeviceDefender() + ",");
        }
        if (getConnectivity() != null) {
            sb.append("connectivity: " + getConnectivity());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getThingId() == null ? 0 : getThingId().hashCode())) * 31) + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode())) * 31) + (getThingGroupNames() == null ? 0 : getThingGroupNames().hashCode())) * 31) + (getAttributes() == null ? 0 : getAttributes().hashCode())) * 31) + (getShadow() == null ? 0 : getShadow().hashCode())) * 31) + (getDeviceDefender() == null ? 0 : getDeviceDefender().hashCode())) * 31) + (getConnectivity() != null ? getConnectivity().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingDocument)) {
            return false;
        }
        ThingDocument thingDocument = (ThingDocument) obj;
        if ((thingDocument.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (thingDocument.getThingName() != null && !thingDocument.getThingName().equals(getThingName())) {
            return false;
        }
        if ((thingDocument.getThingId() == null) ^ (getThingId() == null)) {
            return false;
        }
        if (thingDocument.getThingId() != null && !thingDocument.getThingId().equals(getThingId())) {
            return false;
        }
        if ((thingDocument.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (thingDocument.getThingTypeName() != null && !thingDocument.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((thingDocument.getThingGroupNames() == null) ^ (getThingGroupNames() == null)) {
            return false;
        }
        if (thingDocument.getThingGroupNames() != null && !thingDocument.getThingGroupNames().equals(getThingGroupNames())) {
            return false;
        }
        if ((thingDocument.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (thingDocument.getAttributes() != null && !thingDocument.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((thingDocument.getShadow() == null) ^ (getShadow() == null)) {
            return false;
        }
        if (thingDocument.getShadow() != null && !thingDocument.getShadow().equals(getShadow())) {
            return false;
        }
        if ((thingDocument.getDeviceDefender() == null) ^ (getDeviceDefender() == null)) {
            return false;
        }
        if (thingDocument.getDeviceDefender() != null && !thingDocument.getDeviceDefender().equals(getDeviceDefender())) {
            return false;
        }
        if ((thingDocument.getConnectivity() == null) ^ (getConnectivity() == null)) {
            return false;
        }
        return thingDocument.getConnectivity() == null || thingDocument.getConnectivity().equals(getConnectivity());
    }
}
