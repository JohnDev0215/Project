package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class PercentPair implements Serializable {
    private Double percent;
    private Double value;

    public Double getPercent() {
        return this.percent;
    }

    public void setPercent(Double d) {
        this.percent = d;
    }

    public PercentPair withPercent(Double d) {
        this.percent = d;
        return this;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double d) {
        this.value = d;
    }

    public PercentPair withValue(Double d) {
        this.value = d;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPercent() != null) {
            sb.append("percent: " + getPercent() + ",");
        }
        if (getValue() != null) {
            sb.append("value: " + getValue());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getPercent() == null ? 0 : getPercent().hashCode()) + 31) * 31) + (getValue() != null ? getValue().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PercentPair)) {
            return false;
        }
        PercentPair percentPair = (PercentPair) obj;
        if ((percentPair.getPercent() == null) ^ (getPercent() == null)) {
            return false;
        }
        if (percentPair.getPercent() != null && !percentPair.getPercent().equals(getPercent())) {
            return false;
        }
        if ((percentPair.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        return percentPair.getValue() == null || percentPair.getValue().equals(getValue());
    }
}
