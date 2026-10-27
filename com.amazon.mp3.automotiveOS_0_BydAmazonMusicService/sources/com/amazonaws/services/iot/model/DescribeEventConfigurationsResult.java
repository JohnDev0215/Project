package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeEventConfigurationsResult implements Serializable {
    private Date creationDate;
    private Map<String, Configuration> eventConfigurations;
    private Date lastModifiedDate;

    public Map<String, Configuration> getEventConfigurations() {
        return this.eventConfigurations;
    }

    public void setEventConfigurations(Map<String, Configuration> map) {
        this.eventConfigurations = map;
    }

    public DescribeEventConfigurationsResult withEventConfigurations(Map<String, Configuration> map) {
        this.eventConfigurations = map;
        return this;
    }

    public DescribeEventConfigurationsResult addeventConfigurationsEntry(String str, Configuration configuration) {
        if (this.eventConfigurations == null) {
            this.eventConfigurations = new HashMap();
        }
        if (this.eventConfigurations.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.eventConfigurations.put(str, configuration);
        return this;
    }

    public DescribeEventConfigurationsResult cleareventConfigurationsEntries() {
        this.eventConfigurations = null;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public DescribeEventConfigurationsResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public DescribeEventConfigurationsResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getEventConfigurations() != null) {
            sb.append("eventConfigurations: " + getEventConfigurations() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getEventConfigurations() == null ? 0 : getEventConfigurations().hashCode()) + 31) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() != null ? getLastModifiedDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeEventConfigurationsResult)) {
            return false;
        }
        DescribeEventConfigurationsResult describeEventConfigurationsResult = (DescribeEventConfigurationsResult) obj;
        if ((describeEventConfigurationsResult.getEventConfigurations() == null) ^ (getEventConfigurations() == null)) {
            return false;
        }
        if (describeEventConfigurationsResult.getEventConfigurations() != null && !describeEventConfigurationsResult.getEventConfigurations().equals(getEventConfigurations())) {
            return false;
        }
        if ((describeEventConfigurationsResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeEventConfigurationsResult.getCreationDate() != null && !describeEventConfigurationsResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeEventConfigurationsResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return describeEventConfigurationsResult.getLastModifiedDate() == null || describeEventConfigurationsResult.getLastModifiedDate().equals(getLastModifiedDate());
    }
}
