package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeIndexResult implements Serializable {
    private String indexName;
    private String indexStatus;
    private String schema;

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public DescribeIndexResult withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public String getIndexStatus() {
        return this.indexStatus;
    }

    public void setIndexStatus(String str) {
        this.indexStatus = str;
    }

    public DescribeIndexResult withIndexStatus(String str) {
        this.indexStatus = str;
        return this;
    }

    public void setIndexStatus(IndexStatus indexStatus) {
        this.indexStatus = indexStatus.toString();
    }

    public DescribeIndexResult withIndexStatus(IndexStatus indexStatus) {
        this.indexStatus = indexStatus.toString();
        return this;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String str) {
        this.schema = str;
    }

    public DescribeIndexResult withSchema(String str) {
        this.schema = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIndexName() != null) {
            sb.append("indexName: " + getIndexName() + ",");
        }
        if (getIndexStatus() != null) {
            sb.append("indexStatus: " + getIndexStatus() + ",");
        }
        if (getSchema() != null) {
            sb.append("schema: " + getSchema());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getIndexName() == null ? 0 : getIndexName().hashCode()) + 31) * 31) + (getIndexStatus() == null ? 0 : getIndexStatus().hashCode())) * 31) + (getSchema() != null ? getSchema().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeIndexResult)) {
            return false;
        }
        DescribeIndexResult describeIndexResult = (DescribeIndexResult) obj;
        if ((describeIndexResult.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (describeIndexResult.getIndexName() != null && !describeIndexResult.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((describeIndexResult.getIndexStatus() == null) ^ (getIndexStatus() == null)) {
            return false;
        }
        if (describeIndexResult.getIndexStatus() != null && !describeIndexResult.getIndexStatus().equals(getIndexStatus())) {
            return false;
        }
        if ((describeIndexResult.getSchema() == null) ^ (getSchema() == null)) {
            return false;
        }
        return describeIndexResult.getSchema() == null || describeIndexResult.getSchema().equals(getSchema());
    }
}
