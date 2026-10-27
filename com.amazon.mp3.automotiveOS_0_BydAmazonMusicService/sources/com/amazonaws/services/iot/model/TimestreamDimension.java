package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class TimestreamDimension implements Serializable {
    private String name;
    private String value;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public TimestreamDimension withName(String str) {
        this.name = str;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public TimestreamDimension withValue(String str) {
        this.value = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getName() != null) {
            sb.append("name: " + getName() + ",");
        }
        if (getValue() != null) {
            sb.append("value: " + getValue());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getValue() != null ? getValue().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TimestreamDimension)) {
            return false;
        }
        TimestreamDimension timestreamDimension = (TimestreamDimension) obj;
        if ((timestreamDimension.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (timestreamDimension.getName() != null && !timestreamDimension.getName().equals(getName())) {
            return false;
        }
        if ((timestreamDimension.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        return timestreamDimension.getValue() == null || timestreamDimension.getValue().equals(getValue());
    }
}
