package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDynamicThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String indexName;
    private String queryString;
    private String queryVersion;
    private String thingGroupName;
    private ThingGroupProperties thingGroupProperties;

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public UpdateDynamicThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public ThingGroupProperties getThingGroupProperties() {
        return this.thingGroupProperties;
    }

    public void setThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
    }

    public UpdateDynamicThingGroupRequest withThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public UpdateDynamicThingGroupRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public UpdateDynamicThingGroupRequest withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public UpdateDynamicThingGroupRequest withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public UpdateDynamicThingGroupRequest withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupName() != null) {
            sb.append("thingGroupName: " + getThingGroupName() + ",");
        }
        if (getThingGroupProperties() != null) {
            sb.append("thingGroupProperties: " + getThingGroupProperties() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion() + ",");
        }
        if (getIndexName() != null) {
            sb.append("indexName: " + getIndexName() + ",");
        }
        if (getQueryString() != null) {
            sb.append("queryString: " + getQueryString() + ",");
        }
        if (getQueryVersion() != null) {
            sb.append("queryVersion: " + getQueryVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupProperties() == null ? 0 : getThingGroupProperties().hashCode())) * 31) + (getExpectedVersion() == null ? 0 : getExpectedVersion().hashCode())) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getQueryVersion() != null ? getQueryVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateDynamicThingGroupRequest)) {
            return false;
        }
        UpdateDynamicThingGroupRequest updateDynamicThingGroupRequest = (UpdateDynamicThingGroupRequest) obj;
        if ((updateDynamicThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (updateDynamicThingGroupRequest.getThingGroupName() != null && !updateDynamicThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((updateDynamicThingGroupRequest.getThingGroupProperties() == null) ^ (getThingGroupProperties() == null)) {
            return false;
        }
        if (updateDynamicThingGroupRequest.getThingGroupProperties() != null && !updateDynamicThingGroupRequest.getThingGroupProperties().equals(getThingGroupProperties())) {
            return false;
        }
        if ((updateDynamicThingGroupRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        if (updateDynamicThingGroupRequest.getExpectedVersion() != null && !updateDynamicThingGroupRequest.getExpectedVersion().equals(getExpectedVersion())) {
            return false;
        }
        if ((updateDynamicThingGroupRequest.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (updateDynamicThingGroupRequest.getIndexName() != null && !updateDynamicThingGroupRequest.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((updateDynamicThingGroupRequest.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (updateDynamicThingGroupRequest.getQueryString() != null && !updateDynamicThingGroupRequest.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((updateDynamicThingGroupRequest.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        return updateDynamicThingGroupRequest.getQueryVersion() == null || updateDynamicThingGroupRequest.getQueryVersion().equals(getQueryVersion());
    }
}
