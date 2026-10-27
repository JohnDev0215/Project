package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ThingIndexingConfiguration implements Serializable {
    private List<Field> customFields;
    private String deviceDefenderIndexingMode;
    private IndexingFilter filter;
    private List<Field> managedFields;
    private String namedShadowIndexingMode;
    private String thingConnectivityIndexingMode;
    private String thingIndexingMode;

    public String getThingIndexingMode() {
        return this.thingIndexingMode;
    }

    public void setThingIndexingMode(String str) {
        this.thingIndexingMode = str;
    }

    public ThingIndexingConfiguration withThingIndexingMode(String str) {
        this.thingIndexingMode = str;
        return this;
    }

    public void setThingIndexingMode(ThingIndexingMode thingIndexingMode) {
        this.thingIndexingMode = thingIndexingMode.toString();
    }

    public ThingIndexingConfiguration withThingIndexingMode(ThingIndexingMode thingIndexingMode) {
        this.thingIndexingMode = thingIndexingMode.toString();
        return this;
    }

    public String getThingConnectivityIndexingMode() {
        return this.thingConnectivityIndexingMode;
    }

    public void setThingConnectivityIndexingMode(String str) {
        this.thingConnectivityIndexingMode = str;
    }

    public ThingIndexingConfiguration withThingConnectivityIndexingMode(String str) {
        this.thingConnectivityIndexingMode = str;
        return this;
    }

    public void setThingConnectivityIndexingMode(ThingConnectivityIndexingMode thingConnectivityIndexingMode) {
        this.thingConnectivityIndexingMode = thingConnectivityIndexingMode.toString();
    }

    public ThingIndexingConfiguration withThingConnectivityIndexingMode(ThingConnectivityIndexingMode thingConnectivityIndexingMode) {
        this.thingConnectivityIndexingMode = thingConnectivityIndexingMode.toString();
        return this;
    }

    public String getDeviceDefenderIndexingMode() {
        return this.deviceDefenderIndexingMode;
    }

    public void setDeviceDefenderIndexingMode(String str) {
        this.deviceDefenderIndexingMode = str;
    }

    public ThingIndexingConfiguration withDeviceDefenderIndexingMode(String str) {
        this.deviceDefenderIndexingMode = str;
        return this;
    }

    public void setDeviceDefenderIndexingMode(DeviceDefenderIndexingMode deviceDefenderIndexingMode) {
        this.deviceDefenderIndexingMode = deviceDefenderIndexingMode.toString();
    }

    public ThingIndexingConfiguration withDeviceDefenderIndexingMode(DeviceDefenderIndexingMode deviceDefenderIndexingMode) {
        this.deviceDefenderIndexingMode = deviceDefenderIndexingMode.toString();
        return this;
    }

    public String getNamedShadowIndexingMode() {
        return this.namedShadowIndexingMode;
    }

    public void setNamedShadowIndexingMode(String str) {
        this.namedShadowIndexingMode = str;
    }

    public ThingIndexingConfiguration withNamedShadowIndexingMode(String str) {
        this.namedShadowIndexingMode = str;
        return this;
    }

    public void setNamedShadowIndexingMode(NamedShadowIndexingMode namedShadowIndexingMode) {
        this.namedShadowIndexingMode = namedShadowIndexingMode.toString();
    }

    public ThingIndexingConfiguration withNamedShadowIndexingMode(NamedShadowIndexingMode namedShadowIndexingMode) {
        this.namedShadowIndexingMode = namedShadowIndexingMode.toString();
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

    public ThingIndexingConfiguration withManagedFields(Field... fieldArr) {
        if (getManagedFields() == null) {
            this.managedFields = new ArrayList(fieldArr.length);
        }
        for (Field field : fieldArr) {
            this.managedFields.add(field);
        }
        return this;
    }

    public ThingIndexingConfiguration withManagedFields(Collection<Field> collection) {
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

    public ThingIndexingConfiguration withCustomFields(Field... fieldArr) {
        if (getCustomFields() == null) {
            this.customFields = new ArrayList(fieldArr.length);
        }
        for (Field field : fieldArr) {
            this.customFields.add(field);
        }
        return this;
    }

    public ThingIndexingConfiguration withCustomFields(Collection<Field> collection) {
        setCustomFields(collection);
        return this;
    }

    public IndexingFilter getFilter() {
        return this.filter;
    }

    public void setFilter(IndexingFilter indexingFilter) {
        this.filter = indexingFilter;
    }

    public ThingIndexingConfiguration withFilter(IndexingFilter indexingFilter) {
        this.filter = indexingFilter;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingIndexingMode() != null) {
            sb.append("thingIndexingMode: " + getThingIndexingMode() + ",");
        }
        if (getThingConnectivityIndexingMode() != null) {
            sb.append("thingConnectivityIndexingMode: " + getThingConnectivityIndexingMode() + ",");
        }
        if (getDeviceDefenderIndexingMode() != null) {
            sb.append("deviceDefenderIndexingMode: " + getDeviceDefenderIndexingMode() + ",");
        }
        if (getNamedShadowIndexingMode() != null) {
            sb.append("namedShadowIndexingMode: " + getNamedShadowIndexingMode() + ",");
        }
        if (getManagedFields() != null) {
            sb.append("managedFields: " + getManagedFields() + ",");
        }
        if (getCustomFields() != null) {
            sb.append("customFields: " + getCustomFields() + ",");
        }
        if (getFilter() != null) {
            sb.append("filter: " + getFilter());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getThingIndexingMode() == null ? 0 : getThingIndexingMode().hashCode()) + 31) * 31) + (getThingConnectivityIndexingMode() == null ? 0 : getThingConnectivityIndexingMode().hashCode())) * 31) + (getDeviceDefenderIndexingMode() == null ? 0 : getDeviceDefenderIndexingMode().hashCode())) * 31) + (getNamedShadowIndexingMode() == null ? 0 : getNamedShadowIndexingMode().hashCode())) * 31) + (getManagedFields() == null ? 0 : getManagedFields().hashCode())) * 31) + (getCustomFields() == null ? 0 : getCustomFields().hashCode())) * 31) + (getFilter() != null ? getFilter().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingIndexingConfiguration)) {
            return false;
        }
        ThingIndexingConfiguration thingIndexingConfiguration = (ThingIndexingConfiguration) obj;
        if ((thingIndexingConfiguration.getThingIndexingMode() == null) ^ (getThingIndexingMode() == null)) {
            return false;
        }
        if (thingIndexingConfiguration.getThingIndexingMode() != null && !thingIndexingConfiguration.getThingIndexingMode().equals(getThingIndexingMode())) {
            return false;
        }
        if ((thingIndexingConfiguration.getThingConnectivityIndexingMode() == null) ^ (getThingConnectivityIndexingMode() == null)) {
            return false;
        }
        if (thingIndexingConfiguration.getThingConnectivityIndexingMode() != null && !thingIndexingConfiguration.getThingConnectivityIndexingMode().equals(getThingConnectivityIndexingMode())) {
            return false;
        }
        if ((thingIndexingConfiguration.getDeviceDefenderIndexingMode() == null) ^ (getDeviceDefenderIndexingMode() == null)) {
            return false;
        }
        if (thingIndexingConfiguration.getDeviceDefenderIndexingMode() != null && !thingIndexingConfiguration.getDeviceDefenderIndexingMode().equals(getDeviceDefenderIndexingMode())) {
            return false;
        }
        if ((thingIndexingConfiguration.getNamedShadowIndexingMode() == null) ^ (getNamedShadowIndexingMode() == null)) {
            return false;
        }
        if (thingIndexingConfiguration.getNamedShadowIndexingMode() != null && !thingIndexingConfiguration.getNamedShadowIndexingMode().equals(getNamedShadowIndexingMode())) {
            return false;
        }
        if ((thingIndexingConfiguration.getManagedFields() == null) ^ (getManagedFields() == null)) {
            return false;
        }
        if (thingIndexingConfiguration.getManagedFields() != null && !thingIndexingConfiguration.getManagedFields().equals(getManagedFields())) {
            return false;
        }
        if ((thingIndexingConfiguration.getCustomFields() == null) ^ (getCustomFields() == null)) {
            return false;
        }
        if (thingIndexingConfiguration.getCustomFields() != null && !thingIndexingConfiguration.getCustomFields().equals(getCustomFields())) {
            return false;
        }
        if ((thingIndexingConfiguration.getFilter() == null) ^ (getFilter() == null)) {
            return false;
        }
        return thingIndexingConfiguration.getFilter() == null || thingIndexingConfiguration.getFilter().equals(getFilter());
    }
}
