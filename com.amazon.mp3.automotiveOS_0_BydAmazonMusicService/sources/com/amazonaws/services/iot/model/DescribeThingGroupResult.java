package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingGroupResult implements Serializable {
    private String indexName;
    private String queryString;
    private String queryVersion;
    private String status;
    private String thingGroupArn;
    private String thingGroupId;
    private ThingGroupMetadata thingGroupMetadata;
    private String thingGroupName;
    private ThingGroupProperties thingGroupProperties;
    private Long version;

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public DescribeThingGroupResult withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public String getThingGroupId() {
        return this.thingGroupId;
    }

    public void setThingGroupId(String str) {
        this.thingGroupId = str;
    }

    public DescribeThingGroupResult withThingGroupId(String str) {
        this.thingGroupId = str;
        return this;
    }

    public String getThingGroupArn() {
        return this.thingGroupArn;
    }

    public void setThingGroupArn(String str) {
        this.thingGroupArn = str;
    }

    public DescribeThingGroupResult withThingGroupArn(String str) {
        this.thingGroupArn = str;
        return this;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public DescribeThingGroupResult withVersion(Long l) {
        this.version = l;
        return this;
    }

    public ThingGroupProperties getThingGroupProperties() {
        return this.thingGroupProperties;
    }

    public void setThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
    }

    public DescribeThingGroupResult withThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
        return this;
    }

    public ThingGroupMetadata getThingGroupMetadata() {
        return this.thingGroupMetadata;
    }

    public void setThingGroupMetadata(ThingGroupMetadata thingGroupMetadata) {
        this.thingGroupMetadata = thingGroupMetadata;
    }

    public DescribeThingGroupResult withThingGroupMetadata(ThingGroupMetadata thingGroupMetadata) {
        this.thingGroupMetadata = thingGroupMetadata;
        return this;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public DescribeThingGroupResult withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public DescribeThingGroupResult withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public DescribeThingGroupResult withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public DescribeThingGroupResult withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(DynamicGroupStatus dynamicGroupStatus) {
        this.status = dynamicGroupStatus.toString();
    }

    public DescribeThingGroupResult withStatus(DynamicGroupStatus dynamicGroupStatus) {
        this.status = dynamicGroupStatus.toString();
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
        if (getThingGroupArn() != null) {
            sb.append("thingGroupArn: " + getThingGroupArn() + ",");
        }
        if (getVersion() != null) {
            sb.append("version: " + getVersion() + ",");
        }
        if (getThingGroupProperties() != null) {
            sb.append("thingGroupProperties: " + getThingGroupProperties() + ",");
        }
        if (getThingGroupMetadata() != null) {
            sb.append("thingGroupMetadata: " + getThingGroupMetadata() + ",");
        }
        if (getIndexName() != null) {
            sb.append("indexName: " + getIndexName() + ",");
        }
        if (getQueryString() != null) {
            sb.append("queryString: " + getQueryString() + ",");
        }
        if (getQueryVersion() != null) {
            sb.append("queryVersion: " + getQueryVersion() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupId() == null ? 0 : getThingGroupId().hashCode())) * 31) + (getThingGroupArn() == null ? 0 : getThingGroupArn().hashCode())) * 31) + (getVersion() == null ? 0 : getVersion().hashCode())) * 31) + (getThingGroupProperties() == null ? 0 : getThingGroupProperties().hashCode())) * 31) + (getThingGroupMetadata() == null ? 0 : getThingGroupMetadata().hashCode())) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getQueryVersion() == null ? 0 : getQueryVersion().hashCode())) * 31) + (getStatus() != null ? getStatus().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingGroupResult)) {
            return false;
        }
        DescribeThingGroupResult describeThingGroupResult = (DescribeThingGroupResult) obj;
        if ((describeThingGroupResult.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (describeThingGroupResult.getThingGroupName() != null && !describeThingGroupResult.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((describeThingGroupResult.getThingGroupId() == null) ^ (getThingGroupId() == null)) {
            return false;
        }
        if (describeThingGroupResult.getThingGroupId() != null && !describeThingGroupResult.getThingGroupId().equals(getThingGroupId())) {
            return false;
        }
        if ((describeThingGroupResult.getThingGroupArn() == null) ^ (getThingGroupArn() == null)) {
            return false;
        }
        if (describeThingGroupResult.getThingGroupArn() != null && !describeThingGroupResult.getThingGroupArn().equals(getThingGroupArn())) {
            return false;
        }
        if ((describeThingGroupResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        if (describeThingGroupResult.getVersion() != null && !describeThingGroupResult.getVersion().equals(getVersion())) {
            return false;
        }
        if ((describeThingGroupResult.getThingGroupProperties() == null) ^ (getThingGroupProperties() == null)) {
            return false;
        }
        if (describeThingGroupResult.getThingGroupProperties() != null && !describeThingGroupResult.getThingGroupProperties().equals(getThingGroupProperties())) {
            return false;
        }
        if ((describeThingGroupResult.getThingGroupMetadata() == null) ^ (getThingGroupMetadata() == null)) {
            return false;
        }
        if (describeThingGroupResult.getThingGroupMetadata() != null && !describeThingGroupResult.getThingGroupMetadata().equals(getThingGroupMetadata())) {
            return false;
        }
        if ((describeThingGroupResult.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (describeThingGroupResult.getIndexName() != null && !describeThingGroupResult.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((describeThingGroupResult.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (describeThingGroupResult.getQueryString() != null && !describeThingGroupResult.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((describeThingGroupResult.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        if (describeThingGroupResult.getQueryVersion() != null && !describeThingGroupResult.getQueryVersion().equals(getQueryVersion())) {
            return false;
        }
        if ((describeThingGroupResult.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return describeThingGroupResult.getStatus() == null || describeThingGroupResult.getStatus().equals(getStatus());
    }
}
