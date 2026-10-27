package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateEventConfigurationsRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, Configuration> eventConfigurations;

    public Map<String, Configuration> getEventConfigurations() {
        return this.eventConfigurations;
    }

    public void setEventConfigurations(Map<String, Configuration> map) {
        this.eventConfigurations = map;
    }

    public UpdateEventConfigurationsRequest withEventConfigurations(Map<String, Configuration> map) {
        this.eventConfigurations = map;
        return this;
    }

    public UpdateEventConfigurationsRequest addeventConfigurationsEntry(String str, Configuration configuration) {
        if (this.eventConfigurations == null) {
            this.eventConfigurations = new HashMap();
        }
        if (this.eventConfigurations.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.eventConfigurations.put(str, configuration);
        return this;
    }

    public UpdateEventConfigurationsRequest cleareventConfigurationsEntries() {
        this.eventConfigurations = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getEventConfigurations() != null) {
            sb.append("eventConfigurations: " + getEventConfigurations());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getEventConfigurations() == null ? 0 : getEventConfigurations().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateEventConfigurationsRequest)) {
            return false;
        }
        UpdateEventConfigurationsRequest updateEventConfigurationsRequest = (UpdateEventConfigurationsRequest) obj;
        if ((updateEventConfigurationsRequest.getEventConfigurations() == null) ^ (getEventConfigurations() == null)) {
            return false;
        }
        return updateEventConfigurationsRequest.getEventConfigurations() == null || updateEventConfigurationsRequest.getEventConfigurations().equals(getEventConfigurations());
    }
}
