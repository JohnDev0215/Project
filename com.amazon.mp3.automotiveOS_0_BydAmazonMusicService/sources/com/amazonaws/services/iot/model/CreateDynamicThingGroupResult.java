package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateDynamicThingGroupResult implements Serializable {
    private String indexName;
    private String queryString;
    private String queryVersion;
    private String thingGroupArn;
    private String thingGroupId;
    private String thingGroupName;

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public CreateDynamicThingGroupResult withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public String getThingGroupArn() {
        return this.thingGroupArn;
    }

    public void setThingGroupArn(String str) {
        this.thingGroupArn = str;
    }

    public CreateDynamicThingGroupResult withThingGroupArn(String str) {
        this.thingGroupArn = str;
        return this;
    }

    public String getThingGroupId() {
        return this.thingGroupId;
    }

    public void setThingGroupId(String str) {
        this.thingGroupId = str;
    }

    public CreateDynamicThingGroupResult withThingGroupId(String str) {
        this.thingGroupId = str;
        return this;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public CreateDynamicThingGroupResult withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public CreateDynamicThingGroupResult withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public CreateDynamicThingGroupResult withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupName() != null) {
            sb.append("thingGroupName: " + getThingGroupName() + ",");
        }
        if (getThingGroupArn() != null) {
            sb.append("thingGroupArn: " + getThingGroupArn() + ",");
        }
        if (getThingGroupId() != null) {
            sb.append("thingGroupId: " + getThingGroupId() + ",");
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
        return (((((((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupArn() == null ? 0 : getThingGroupArn().hashCode())) * 31) + (getThingGroupId() == null ? 0 : getThingGroupId().hashCode())) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getQueryVersion() != null ? getQueryVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateDynamicThingGroupResult)) {
            return false;
        }
        CreateDynamicThingGroupResult createDynamicThingGroupResult = (CreateDynamicThingGroupResult) obj;
        if ((createDynamicThingGroupResult.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (createDynamicThingGroupResult.getThingGroupName() != null && !createDynamicThingGroupResult.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((createDynamicThingGroupResult.getThingGroupArn() == null) ^ (getThingGroupArn() == null)) {
            return false;
        }
        if (createDynamicThingGroupResult.getThingGroupArn() != null && !createDynamicThingGroupResult.getThingGroupArn().equals(getThingGroupArn())) {
            return false;
        }
        if ((createDynamicThingGroupResult.getThingGroupId() == null) ^ (getThingGroupId() == null)) {
            return false;
        }
        if (createDynamicThingGroupResult.getThingGroupId() != null && !createDynamicThingGroupResult.getThingGroupId().equals(getThingGroupId())) {
            return false;
        }
        if ((createDynamicThingGroupResult.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (createDynamicThingGroupResult.getIndexName() != null && !createDynamicThingGroupResult.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((createDynamicThingGroupResult.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (createDynamicThingGroupResult.getQueryString() != null && !createDynamicThingGroupResult.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((createDynamicThingGroupResult.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        return createDynamicThingGroupResult.getQueryVersion() == null || createDynamicThingGroupResult.getQueryVersion().equals(getQueryVersion());
    }
}
