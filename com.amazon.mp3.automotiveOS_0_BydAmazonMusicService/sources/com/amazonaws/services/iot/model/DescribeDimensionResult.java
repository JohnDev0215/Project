package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDimensionResult implements Serializable {
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

    public DescribeDimensionResult withName(String str) {
        this.name = str;
        return this;
    }

    public String getArn() {
        return this.arn;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public DescribeDimensionResult withArn(String str) {
        this.arn = str;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public DescribeDimensionResult withType(String str) {
        this.type = str;
        return this;
    }

    public void setType(DimensionType dimensionType) {
        this.type = dimensionType.toString();
    }

    public DescribeDimensionResult withType(DimensionType dimensionType) {
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

    public DescribeDimensionResult withStringValues(String... strArr) {
        if (getStringValues() == null) {
            this.stringValues = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.stringValues.add(str);
        }
        return this;
    }

    public DescribeDimensionResult withStringValues(Collection<String> collection) {
        setStringValues(collection);
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public DescribeDimensionResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public DescribeDimensionResult withLastModifiedDate(Date date) {
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
        if (obj == null || !(obj instanceof DescribeDimensionResult)) {
            return false;
        }
        DescribeDimensionResult describeDimensionResult = (DescribeDimensionResult) obj;
        if ((describeDimensionResult.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (describeDimensionResult.getName() != null && !describeDimensionResult.getName().equals(getName())) {
            return false;
        }
        if ((describeDimensionResult.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        if (describeDimensionResult.getArn() != null && !describeDimensionResult.getArn().equals(getArn())) {
            return false;
        }
        if ((describeDimensionResult.getType() == null) ^ (getType() == null)) {
            return false;
        }
        if (describeDimensionResult.getType() != null && !describeDimensionResult.getType().equals(getType())) {
            return false;
        }
        if ((describeDimensionResult.getStringValues() == null) ^ (getStringValues() == null)) {
            return false;
        }
        if (describeDimensionResult.getStringValues() != null && !describeDimensionResult.getStringValues().equals(getStringValues())) {
            return false;
        }
        if ((describeDimensionResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeDimensionResult.getCreationDate() != null && !describeDimensionResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeDimensionResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return describeDimensionResult.getLastModifiedDate() == null || describeDimensionResult.getLastModifiedDate().equals(getLastModifiedDate());
    }
}
