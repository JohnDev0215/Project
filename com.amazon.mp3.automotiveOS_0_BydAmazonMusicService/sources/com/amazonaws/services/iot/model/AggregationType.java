package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AggregationType implements Serializable {
    private String name;
    private List<String> values;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public AggregationType withName(String str) {
        this.name = str;
        return this;
    }

    public void setName(AggregationTypeName aggregationTypeName) {
        this.name = aggregationTypeName.toString();
    }

    public AggregationType withName(AggregationTypeName aggregationTypeName) {
        this.name = aggregationTypeName.toString();
        return this;
    }

    public List<String> getValues() {
        return this.values;
    }

    public void setValues(Collection<String> collection) {
        if (collection == null) {
            this.values = null;
        } else {
            this.values = new ArrayList(collection);
        }
    }

    public AggregationType withValues(String... strArr) {
        if (getValues() == null) {
            this.values = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.values.add(str);
        }
        return this;
    }

    public AggregationType withValues(Collection<String> collection) {
        setValues(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getName() != null) {
            sb.append("name: " + getName() + ",");
        }
        if (getValues() != null) {
            sb.append("values: " + getValues());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getValues() != null ? getValues().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AggregationType)) {
            return false;
        }
        AggregationType aggregationType = (AggregationType) obj;
        if ((aggregationType.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (aggregationType.getName() != null && !aggregationType.getName().equals(getName())) {
            return false;
        }
        if ((aggregationType.getValues() == null) ^ (getValues() == null)) {
            return false;
        }
        return aggregationType.getValues() == null || aggregationType.getValues().equals(getValues());
    }
}
