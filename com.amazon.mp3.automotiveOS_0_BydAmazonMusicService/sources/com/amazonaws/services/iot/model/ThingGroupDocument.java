package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ThingGroupDocument implements Serializable {
    private Map<String, String> attributes;
    private List<String> parentGroupNames;
    private String thingGroupDescription;
    private String thingGroupId;
    private String thingGroupName;

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public ThingGroupDocument withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public String getThingGroupId() {
        return this.thingGroupId;
    }

    public void setThingGroupId(String str) {
        this.thingGroupId = str;
    }

    public ThingGroupDocument withThingGroupId(String str) {
        this.thingGroupId = str;
        return this;
    }

    public String getThingGroupDescription() {
        return this.thingGroupDescription;
    }

    public void setThingGroupDescription(String str) {
        this.thingGroupDescription = str;
    }

    public ThingGroupDocument withThingGroupDescription(String str) {
        this.thingGroupDescription = str;
        return this;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public ThingGroupDocument withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public ThingGroupDocument addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (this.attributes.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.attributes.put(str, str2);
        return this;
    }

    public ThingGroupDocument clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public List<String> getParentGroupNames() {
        return this.parentGroupNames;
    }

    public void setParentGroupNames(Collection<String> collection) {
        if (collection == null) {
            this.parentGroupNames = null;
        } else {
            this.parentGroupNames = new ArrayList(collection);
        }
    }

    public ThingGroupDocument withParentGroupNames(String... strArr) {
        if (getParentGroupNames() == null) {
            this.parentGroupNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.parentGroupNames.add(str);
        }
        return this;
    }

    public ThingGroupDocument withParentGroupNames(Collection<String> collection) {
        setParentGroupNames(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupName() != null) {
            sb.append("thingGroupName: " + getThingGroupName() + ",");
        }
        if (getThingGroupId() != null) {
            sb.append("thingGroupId: " + getThingGroupId() + ",");
        }
        if (getThingGroupDescription() != null) {
            sb.append("thingGroupDescription: " + getThingGroupDescription() + ",");
        }
        if (getAttributes() != null) {
            sb.append("attributes: " + getAttributes() + ",");
        }
        if (getParentGroupNames() != null) {
            sb.append("parentGroupNames: " + getParentGroupNames());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupId() == null ? 0 : getThingGroupId().hashCode())) * 31) + (getThingGroupDescription() == null ? 0 : getThingGroupDescription().hashCode())) * 31) + (getAttributes() == null ? 0 : getAttributes().hashCode())) * 31) + (getParentGroupNames() != null ? getParentGroupNames().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingGroupDocument)) {
            return false;
        }
        ThingGroupDocument thingGroupDocument = (ThingGroupDocument) obj;
        if ((thingGroupDocument.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (thingGroupDocument.getThingGroupName() != null && !thingGroupDocument.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((thingGroupDocument.getThingGroupId() == null) ^ (getThingGroupId() == null)) {
            return false;
        }
        if (thingGroupDocument.getThingGroupId() != null && !thingGroupDocument.getThingGroupId().equals(getThingGroupId())) {
            return false;
        }
        if ((thingGroupDocument.getThingGroupDescription() == null) ^ (getThingGroupDescription() == null)) {
            return false;
        }
        if (thingGroupDocument.getThingGroupDescription() != null && !thingGroupDocument.getThingGroupDescription().equals(getThingGroupDescription())) {
            return false;
        }
        if ((thingGroupDocument.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (thingGroupDocument.getAttributes() != null && !thingGroupDocument.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((thingGroupDocument.getParentGroupNames() == null) ^ (getParentGroupNames() == null)) {
            return false;
        }
        return thingGroupDocument.getParentGroupNames() == null || thingGroupDocument.getParentGroupNames().equals(getParentGroupNames());
    }
}
