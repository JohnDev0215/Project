package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetIndexingConfigurationResult implements Serializable {
    private ThingGroupIndexingConfiguration thingGroupIndexingConfiguration;
    private ThingIndexingConfiguration thingIndexingConfiguration;

    public ThingIndexingConfiguration getThingIndexingConfiguration() {
        return this.thingIndexingConfiguration;
    }

    public void setThingIndexingConfiguration(ThingIndexingConfiguration thingIndexingConfiguration) {
        this.thingIndexingConfiguration = thingIndexingConfiguration;
    }

    public GetIndexingConfigurationResult withThingIndexingConfiguration(ThingIndexingConfiguration thingIndexingConfiguration) {
        this.thingIndexingConfiguration = thingIndexingConfiguration;
        return this;
    }

    public ThingGroupIndexingConfiguration getThingGroupIndexingConfiguration() {
        return this.thingGroupIndexingConfiguration;
    }

    public void setThingGroupIndexingConfiguration(ThingGroupIndexingConfiguration thingGroupIndexingConfiguration) {
        this.thingGroupIndexingConfiguration = thingGroupIndexingConfiguration;
    }

    public GetIndexingConfigurationResult withThingGroupIndexingConfiguration(ThingGroupIndexingConfiguration thingGroupIndexingConfiguration) {
        this.thingGroupIndexingConfiguration = thingGroupIndexingConfiguration;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingIndexingConfiguration() != null) {
            sb.append("thingIndexingConfiguration: " + getThingIndexingConfiguration() + ",");
        }
        if (getThingGroupIndexingConfiguration() != null) {
            sb.append("thingGroupIndexingConfiguration: " + getThingGroupIndexingConfiguration());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingIndexingConfiguration() == null ? 0 : getThingIndexingConfiguration().hashCode()) + 31) * 31) + (getThingGroupIndexingConfiguration() != null ? getThingGroupIndexingConfiguration().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetIndexingConfigurationResult)) {
            return false;
        }
        GetIndexingConfigurationResult getIndexingConfigurationResult = (GetIndexingConfigurationResult) obj;
        if ((getIndexingConfigurationResult.getThingIndexingConfiguration() == null) ^ (getThingIndexingConfiguration() == null)) {
            return false;
        }
        if (getIndexingConfigurationResult.getThingIndexingConfiguration() != null && !getIndexingConfigurationResult.getThingIndexingConfiguration().equals(getThingIndexingConfiguration())) {
            return false;
        }
        if ((getIndexingConfigurationResult.getThingGroupIndexingConfiguration() == null) ^ (getThingGroupIndexingConfiguration() == null)) {
            return false;
        }
        return getIndexingConfigurationResult.getThingGroupIndexingConfiguration() == null || getIndexingConfigurationResult.getThingGroupIndexingConfiguration().equals(getThingGroupIndexingConfiguration());
    }
}
