package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class TimestreamTimestamp implements Serializable {
    private String unit;
    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public TimestreamTimestamp withValue(String str) {
        this.value = str;
        return this;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public TimestreamTimestamp withUnit(String str) {
        this.unit = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getValue() != null) {
            sb.append("value: " + getValue() + ",");
        }
        if (getUnit() != null) {
            sb.append("unit: " + getUnit());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getValue() == null ? 0 : getValue().hashCode()) + 31) * 31) + (getUnit() != null ? getUnit().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TimestreamTimestamp)) {
            return false;
        }
        TimestreamTimestamp timestreamTimestamp = (TimestreamTimestamp) obj;
        if ((timestreamTimestamp.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        if (timestreamTimestamp.getValue() != null && !timestreamTimestamp.getValue().equals(getValue())) {
            return false;
        }
        if ((timestreamTimestamp.getUnit() == null) ^ (getUnit() == null)) {
            return false;
        }
        return timestreamTimestamp.getUnit() == null || timestreamTimestamp.getUnit().equals(getUnit());
    }
}
