package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateIndexingConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private ThingGroupIndexingConfiguration thingGroupIndexingConfiguration;
    private ThingIndexingConfiguration thingIndexingConfiguration;

    public ThingIndexingConfiguration getThingIndexingConfiguration() {
        return this.thingIndexingConfiguration;
    }

    public void setThingIndexingConfiguration(ThingIndexingConfiguration thingIndexingConfiguration) {
        this.thingIndexingConfiguration = thingIndexingConfiguration;
    }

    public UpdateIndexingConfigurationRequest withThingIndexingConfiguration(ThingIndexingConfiguration thingIndexingConfiguration) {
        this.thingIndexingConfiguration = thingIndexingConfiguration;
        return this;
    }

    public ThingGroupIndexingConfiguration getThingGroupIndexingConfiguration() {
        return this.thingGroupIndexingConfiguration;
    }

    public void setThingGroupIndexingConfiguration(ThingGroupIndexingConfiguration thingGroupIndexingConfiguration) {
        this.thingGroupIndexingConfiguration = thingGroupIndexingConfiguration;
    }

    public UpdateIndexingConfigurationRequest withThingGroupIndexingConfiguration(ThingGroupIndexingConfiguration thingGroupIndexingConfiguration) {
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
        if (obj == null || !(obj instanceof UpdateIndexingConfigurationRequest)) {
            return false;
        }
        UpdateIndexingConfigurationRequest updateIndexingConfigurationRequest = (UpdateIndexingConfigurationRequest) obj;
        if ((updateIndexingConfigurationRequest.getThingIndexingConfiguration() == null) ^ (getThingIndexingConfiguration() == null)) {
            return false;
        }
        if (updateIndexingConfigurationRequest.getThingIndexingConfiguration() != null && !updateIndexingConfigurationRequest.getThingIndexingConfiguration().equals(getThingIndexingConfiguration())) {
            return false;
        }
        if ((updateIndexingConfigurationRequest.getThingGroupIndexingConfiguration() == null) ^ (getThingGroupIndexingConfiguration() == null)) {
            return false;
        }
        return updateIndexingConfigurationRequest.getThingGroupIndexingConfiguration() == null || updateIndexingConfigurationRequest.getThingGroupIndexingConfiguration().equals(getThingGroupIndexingConfiguration());
    }
}
