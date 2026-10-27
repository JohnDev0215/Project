package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ThingGroupIndexingConfiguration implements Serializable {
    private List<Field> customFields;
    private List<Field> managedFields;
    private String thingGroupIndexingMode;

    public String getThingGroupIndexingMode() {
        return this.thingGroupIndexingMode;
    }

    public void setThingGroupIndexingMode(String str) {
        this.thingGroupIndexingMode = str;
    }

    public ThingGroupIndexingConfiguration withThingGroupIndexingMode(String str) {
        this.thingGroupIndexingMode = str;
        return this;
    }

    public void setThingGroupIndexingMode(ThingGroupIndexingMode thingGroupIndexingMode) {
        this.thingGroupIndexingMode = thingGroupIndexingMode.toString();
    }

    public ThingGroupIndexingConfiguration withThingGroupIndexingMode(ThingGroupIndexingMode thingGroupIndexingMode) {
        this.thingGroupIndexingMode = thingGroupIndexingMode.toString();
        return this;
    }

    public List<Field> getManagedFields() {
        return this.managedFields;
    }

    public void setManagedFields(Collection<Field> collection) {
        if (collection == null) {
            this.managedFields = null;
        } else {
            this.managedFields = new ArrayList(collection);
        }
    }

    public ThingGroupIndexingConfiguration withManagedFields(Field... fieldArr) {
        if (getManagedFields() == null) {
            this.managedFields = new ArrayList(fieldArr.length);
        }
        for (Field field : fieldArr) {
            this.managedFields.add(field);
        }
        return this;
    }

    public ThingGroupIndexingConfiguration withManagedFields(Collection<Field> collection) {
        setManagedFields(collection);
        return this;
    }

    public List<Field> getCustomFields() {
        return this.customFields;
    }

    public void setCustomFields(Collection<Field> collection) {
        if (collection == null) {
            this.customFields = null;
        } else {
            this.customFields = new ArrayList(collection);
        }
    }

    public ThingGroupIndexingConfiguration withCustomFields(Field... fieldArr) {
        if (getCustomFields() == null) {
            this.customFields = new ArrayList(fieldArr.length);
        }
        for (Field field : fieldArr) {
            this.customFields.add(field);
        }
        return this;
    }

    public ThingGroupIndexingConfiguration withCustomFields(Collection<Field> collection) {
        setCustomFields(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupIndexingMode() != null) {
            sb.append("thingGroupIndexingMode: " + getThingGroupIndexingMode() + ",");
        }
        if (getManagedFields() != null) {
            sb.append("managedFields: " + getManagedFields() + ",");
        }
        if (getCustomFields() != null) {
            sb.append("customFields: " + getCustomFields());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getThingGroupIndexingMode() == null ? 0 : getThingGroupIndexingMode().hashCode()) + 31) * 31) + (getManagedFields() == null ? 0 : getManagedFields().hashCode())) * 31) + (getCustomFields() != null ? getCustomFields().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingGroupIndexingConfiguration)) {
            return false;
        }
        ThingGroupIndexingConfiguration thingGroupIndexingConfiguration = (ThingGroupIndexingConfiguration) obj;
        if ((thingGroupIndexingConfiguration.getThingGroupIndexingMode() == null) ^ (getThingGroupIndexingMode() == null)) {
            return false;
        }
        if (thingGroupIndexingConfiguration.getThingGroupIndexingMode() != null && !thingGroupIndexingConfiguration.getThingGroupIndexingMode().equals(getThingGroupIndexingMode())) {
            return false;
        }
        if ((thingGroupIndexingConfiguration.getManagedFields() == null) ^ (getManagedFields() == null)) {
            return false;
        }
        if (thingGroupIndexingConfiguration.getManagedFields() != null && !thingGroupIndexingConfiguration.getManagedFields().equals(getManagedFields())) {
            return false;
        }
        if ((thingGroupIndexingConfiguration.getCustomFields() == null) ^ (getCustomFields() == null)) {
            return false;
        }
        return thingGroupIndexingConfiguration.getCustomFields() == null || thingGroupIndexingConfiguration.getCustomFields().equals(getCustomFields());
    }
}
