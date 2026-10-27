package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class MetricDimension implements Serializable {
    private String dimensionName;
    private String operator;

    public String getDimensionName() {
        return this.dimensionName;
    }

    public void setDimensionName(String str) {
        this.dimensionName = str;
    }

    public MetricDimension withDimensionName(String str) {
        this.dimensionName = str;
        return this;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String str) {
        this.operator = str;
    }

    public MetricDimension withOperator(String str) {
        this.operator = str;
        return this;
    }

    public void setOperator(DimensionValueOperator dimensionValueOperator) {
        this.operator = dimensionValueOperator.toString();
    }

    public MetricDimension withOperator(DimensionValueOperator dimensionValueOperator) {
        this.operator = dimensionValueOperator.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDimensionName() != null) {
            sb.append("dimensionName: " + getDimensionName() + ",");
        }
        if (getOperator() != null) {
            sb.append("operator: " + getOperator());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getDimensionName() == null ? 0 : getDimensionName().hashCode()) + 31) * 31) + (getOperator() != null ? getOperator().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MetricDimension)) {
            return false;
        }
        MetricDimension metricDimension = (MetricDimension) obj;
        if ((metricDimension.getDimensionName() == null) ^ (getDimensionName() == null)) {
            return false;
        }
        if (metricDimension.getDimensionName() != null && !metricDimension.getDimensionName().equals(getDimensionName())) {
            return false;
        }
        if ((metricDimension.getOperator() == null) ^ (getOperator() == null)) {
            return false;
        }
        return metricDimension.getOperator() == null || metricDimension.getOperator().equals(getOperator());
    }
}
