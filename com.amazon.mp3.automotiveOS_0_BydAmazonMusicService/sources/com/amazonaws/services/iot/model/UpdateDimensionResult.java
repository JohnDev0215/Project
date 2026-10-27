package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDimensionResult implements Serializable {
    private String arn;
    private Date creationDate;
    private Date lastModifiedDate;
    private String name;
    private List<String> stringValues;
    private String type;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public UpdateDimensionResult withName(String str) {
        this.name = str;
        return this;
    }

    public String getArn() {
        return this.arn;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public UpdateDimensionResult withArn(String str) {
        this.arn = str;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public UpdateDimensionResult withType(String str) {
        this.type = str;
        return this;
    }

    public void setType(DimensionType dimensionType) {
        this.type = dimensionType.toString();
    }

    public UpdateDimensionResult withType(DimensionType dimensionType) {
        this.type = dimensionType.toString();
        return this;
    }

    public List<String> getStringValues() {
        return this.stringValues;
    }

    public void setStringValues(Collection<String> collection) {
        if (collection == null) {
            this.stringValues = null;
        } else {
            this.stringValues = new ArrayList(collection);
        }
    }

    public UpdateDimensionResult withStringValues(String... strArr) {
        if (getStringValues() == null) {
            this.stringValues = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.stringValues.add(str);
        }
        return this;
    }

    public UpdateDimensionResult withStringValues(Collection<String> collection) {
        setStringValues(collection);
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public UpdateDimensionResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public UpdateDimensionResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getName() != null) {
            sb.append("name: " + getName() + ",");
        }
        if (getArn() != null) {
            sb.append("arn: " + getArn() + ",");
        }
        if (getType() != null) {
            sb.append("type: " + getType() + ",");
        }
        if (getStringValues() != null) {
            sb.append("stringValues: " + getStringValues() + ",");
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
        return (((((((((((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getArn() == null ? 0 : getArn().hashCode())) * 31) + (getType() == null ? 0 : getType().hashCode())) * 31) + (getStringValues() == null ? 0 : getStringValues().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() != null ? getLastModifiedDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateDimensionResult)) {
            return false;
        }
        UpdateDimensionResult updateDimensionResult = (UpdateDimensionResult) obj;
        if ((updateDimensionResult.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (updateDimensionResult.getName() != null && !updateDimensionResult.getName().equals(getName())) {
            return false;
        }
        if ((updateDimensionResult.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        if (updateDimensionResult.getArn() != null && !updateDimensionResult.getArn().equals(getArn())) {
            return false;
        }
        if ((updateDimensionResult.getType() == null) ^ (getType() == null)) {
            return false;
        }
        if (updateDimensionResult.getType() != null && !updateDimensionResult.getType().equals(getType())) {
            return false;
        }
        if ((updateDimensionResult.getStringValues() == null) ^ (getStringValues() == null)) {
            return false;
        }
        if (updateDimensionResult.getStringValues() != null && !updateDimensionResult.getStringValues().equals(getStringValues())) {
            return false;
        }
        if ((updateDimensionResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (updateDimensionResult.getCreationDate() != null && !updateDimensionResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((updateDimensionResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return updateDimensionResult.getLastModifiedDate() == null || updateDimensionResult.getLastModifiedDate().equals(getLastModifiedDate());
    }
}
