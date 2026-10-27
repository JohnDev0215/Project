package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeFleetMetricResult implements Serializable {
    private String aggregationField;
    private AggregationType aggregationType;
    private Date creationDate;
    private String description;
    private String indexName;
    private Date lastModifiedDate;
    private String metricArn;
    private String metricName;
    private Integer period;
    private String queryString;
    private String queryVersion;
    private String unit;
    private Long version;

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public DescribeFleetMetricResult withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public DescribeFleetMetricResult withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public AggregationType getAggregationType() {
        return this.aggregationType;
    }

    public void setAggregationType(AggregationType aggregationType) {
        this.aggregationType = aggregationType;
    }

    public DescribeFleetMetricResult withAggregationType(AggregationType aggregationType) {
        this.aggregationType = aggregationType;
        return this;
    }

    public Integer getPeriod() {
        return this.period;
    }

    public void setPeriod(Integer num) {
        this.period = num;
    }

    public DescribeFleetMetricResult withPeriod(Integer num) {
        this.period = num;
        return this;
    }

    public String getAggregationField() {
        return this.aggregationField;
    }

    public void setAggregationField(String str) {
        this.aggregationField = str;
    }

    public DescribeFleetMetricResult withAggregationField(String str) {
        this.aggregationField = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public DescribeFleetMetricResult withDescription(String str) {
        this.description = str;
        return this;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public DescribeFleetMetricResult withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public DescribeFleetMetricResult withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public DescribeFleetMetricResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public DescribeFleetMetricResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public DescribeFleetMetricResult withUnit(String str) {
        this.unit = str;
        return this;
    }

    public void setUnit(FleetMetricUnit fleetMetricUnit) {
        this.unit = fleetMetricUnit.toString();
    }

    public DescribeFleetMetricResult withUnit(FleetMetricUnit fleetMetricUnit) {
        this.unit = fleetMetricUnit.toString();
        return this;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public DescribeFleetMetricResult withVersion(Long l) {
        this.version = l;
        return this;
    }

    public String getMetricArn() {
        return this.metricArn;
    }

    public void setMetricArn(String str) {
        this.metricArn = str;
    }

    public DescribeFleetMetricResult withMetricArn(String str) {
        this.metricArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMetricName() != null) {
            sb.append("metricName: " + getMetricName() + ",");
        }
        if (getQueryString() != null) {
            sb.append("queryString: " + getQueryString() + ",");
        }
        if (getAggregationType() != null) {
            sb.append("aggregationType: " + getAggregationType() + ",");
        }
        if (getPeriod() != null) {
            sb.append("period: " + getPeriod() + ",");
        }
        if (getAggregationField() != null) {
            sb.append("aggregationField: " + getAggregationField() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getQueryVersion() != null) {
            sb.append("queryVersion: " + getQueryVersion() + ",");
        }
        if (getIndexName() != null) {
            sb.append("indexName: " + getIndexName() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate() + ",");
        }
        if (getUnit() != null) {
            sb.append("unit: " + getUnit() + ",");
        }
        if (getVersion() != null) {
            sb.append("version: " + getVersion() + ",");
        }
        if (getMetricArn() != null) {
            sb.append("metricArn: " + getMetricArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((((((getMetricName() == null ? 0 : getMetricName().hashCode()) + 31) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getAggregationType() == null ? 0 : getAggregationType().hashCode())) * 31) + (getPeriod() == null ? 0 : getPeriod().hashCode())) * 31) + (getAggregationField() == null ? 0 : getAggregationField().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getQueryVersion() == null ? 0 : getQueryVersion().hashCode())) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getUnit() == null ? 0 : getUnit().hashCode())) * 31) + (getVersion() == null ? 0 : getVersion().hashCode())) * 31) + (getMetricArn() != null ? getMetricArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeFleetMetricResult)) {
            return false;
        }
        DescribeFleetMetricResult describeFleetMetricResult = (DescribeFleetMetricResult) obj;
        if ((describeFleetMetricResult.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getMetricName() != null && !describeFleetMetricResult.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((describeFleetMetricResult.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getQueryString() != null && !describeFleetMetricResult.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((describeFleetMetricResult.getAggregationType() == null) ^ (getAggregationType() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getAggregationType() != null && !describeFleetMetricResult.getAggregationType().equals(getAggregationType())) {
            return false;
        }
        if ((describeFleetMetricResult.getPeriod() == null) ^ (getPeriod() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getPeriod() != null && !describeFleetMetricResult.getPeriod().equals(getPeriod())) {
            return false;
        }
        if ((describeFleetMetricResult.getAggregationField() == null) ^ (getAggregationField() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getAggregationField() != null && !describeFleetMetricResult.getAggregationField().equals(getAggregationField())) {
            return false;
        }
        if ((describeFleetMetricResult.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getDescription() != null && !describeFleetMetricResult.getDescription().equals(getDescription())) {
            return false;
        }
        if ((describeFleetMetricResult.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getQueryVersion() != null && !describeFleetMetricResult.getQueryVersion().equals(getQueryVersion())) {
            return false;
        }
        if ((describeFleetMetricResult.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getIndexName() != null && !describeFleetMetricResult.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((describeFleetMetricResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getCreationDate() != null && !describeFleetMetricResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeFleetMetricResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getLastModifiedDate() != null && !describeFleetMetricResult.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((describeFleetMetricResult.getUnit() == null) ^ (getUnit() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getUnit() != null && !describeFleetMetricResult.getUnit().equals(getUnit())) {
            return false;
        }
        if ((describeFleetMetricResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        if (describeFleetMetricResult.getVersion() != null && !describeFleetMetricResult.getVersion().equals(getVersion())) {
            return false;
        }
        if ((describeFleetMetricResult.getMetricArn() == null) ^ (getMetricArn() == null)) {
            return false;
        }
        return describeFleetMetricResult.getMetricArn() == null || describeFleetMetricResult.getMetricArn().equals(getMetricArn());
    }
}
