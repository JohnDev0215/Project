package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TimestreamAction implements Serializable {
    private String databaseName;
    private List<TimestreamDimension> dimensions;
    private String roleArn;
    private String tableName;
    private TimestreamTimestamp timestamp;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public TimestreamAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public void setDatabaseName(String str) {
        this.databaseName = str;
    }

    public TimestreamAction withDatabaseName(String str) {
        this.databaseName = str;
        return this;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String str) {
        this.tableName = str;
    }

    public TimestreamAction withTableName(String str) {
        this.tableName = str;
        return this;
    }

    public List<TimestreamDimension> getDimensions() {
        return this.dimensions;
    }

    public void setDimensions(Collection<TimestreamDimension> collection) {
        if (collection == null) {
            this.dimensions = null;
        } else {
            this.dimensions = new ArrayList(collection);
        }
    }

    public TimestreamAction withDimensions(TimestreamDimension... timestreamDimensionArr) {
        if (getDimensions() == null) {
            this.dimensions = new ArrayList(timestreamDimensionArr.length);
        }
        for (TimestreamDimension timestreamDimension : timestreamDimensionArr) {
            this.dimensions.add(timestreamDimension);
        }
        return this;
    }

    public TimestreamAction withDimensions(Collection<TimestreamDimension> collection) {
        setDimensions(collection);
        return this;
    }

    public TimestreamTimestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(TimestreamTimestamp timestreamTimestamp) {
        this.timestamp = timestreamTimestamp;
    }

    public TimestreamAction withTimestamp(TimestreamTimestamp timestreamTimestamp) {
        this.timestamp = timestreamTimestamp;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getDatabaseName() != null) {
            sb.append("databaseName: " + getDatabaseName() + ",");
        }
        if (getTableName() != null) {
            sb.append("tableName: " + getTableName() + ",");
        }
        if (getDimensions() != null) {
            sb.append("dimensions: " + getDimensions() + ",");
        }
        if (getTimestamp() != null) {
            sb.append("timestamp: " + getTimestamp());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getDatabaseName() == null ? 0 : getDatabaseName().hashCode())) * 31) + (getTableName() == null ? 0 : getTableName().hashCode())) * 31) + (getDimensions() == null ? 0 : getDimensions().hashCode())) * 31) + (getTimestamp() != null ? getTimestamp().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TimestreamAction)) {
            return false;
        }
        TimestreamAction timestreamAction = (TimestreamAction) obj;
        if ((timestreamAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (timestreamAction.getRoleArn() != null && !timestreamAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((timestreamAction.getDatabaseName() == null) ^ (getDatabaseName() == null)) {
            return false;
        }
        if (timestreamAction.getDatabaseName() != null && !timestreamAction.getDatabaseName().equals(getDatabaseName())) {
            return false;
        }
        if ((timestreamAction.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (timestreamAction.getTableName() != null && !timestreamAction.getTableName().equals(getTableName())) {
            return false;
        }
        if ((timestreamAction.getDimensions() == null) ^ (getDimensions() == null)) {
            return false;
        }
        if (timestreamAction.getDimensions() != null && !timestreamAction.getDimensions().equals(getDimensions())) {
            return false;
        }
        if ((timestreamAction.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        return timestreamAction.getTimestamp() == null || timestreamAction.getTimestamp().equals(getTimestamp());
    }
}
