package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateFleetMetricRequest extends AmazonWebServiceRequest implements Serializable {
    private String aggregationField;
    private AggregationType aggregationType;
    private String description;
    private Long expectedVersion;
    private String indexName;
    private String metricName;
    private Integer period;
    private String queryString;
    private String queryVersion;
    private String unit;

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public UpdateFleetMetricRequest withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public UpdateFleetMetricRequest withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public AggregationType getAggregationType() {
        return this.aggregationType;
    }

    public void setAggregationType(AggregationType aggregationType) {
        this.aggregationType = aggregationType;
    }

    public UpdateFleetMetricRequest withAggregationType(AggregationType aggregationType) {
        this.aggregationType = aggregationType;
        return this;
    }

    public Integer getPeriod() {
        return this.period;
    }

    public void setPeriod(Integer num) {
        this.period = num;
    }

    public UpdateFleetMetricRequest withPeriod(Integer num) {
        this.period = num;
        return this;
    }

    public String getAggregationField() {
        return this.aggregationField;
    }

    public void setAggregationField(String str) {
        this.aggregationField = str;
    }

    public UpdateFleetMetricRequest withAggregationField(String str) {
        this.aggregationField = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public UpdateFleetMetricRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public UpdateFleetMetricRequest withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public UpdateFleetMetricRequest withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public UpdateFleetMetricRequest withUnit(String str) {
        this.unit = str;
        return this;
    }

    public void setUnit(FleetMetricUnit fleetMetricUnit) {
        this.unit = fleetMetricUnit.toString();
    }

    public UpdateFleetMetricRequest withUnit(FleetMetricUnit fleetMetricUnit) {
        this.unit = fleetMetricUnit.toString();
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public UpdateFleetMetricRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
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
        if (getUnit() != null) {
            sb.append("unit: " + getUnit() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((getMetricName() == null ? 0 : getMetricName().hashCode()) + 31) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getAggregationType() == null ? 0 : getAggregationType().hashCode())) * 31) + (getPeriod() == null ? 0 : getPeriod().hashCode())) * 31) + (getAggregationField() == null ? 0 : getAggregationField().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getQueryVersion() == null ? 0 : getQueryVersion().hashCode())) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getUnit() == null ? 0 : getUnit().hashCode())) * 31) + (getExpectedVersion() != null ? getExpectedVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateFleetMetricRequest)) {
            return false;
        }
        UpdateFleetMetricRequest updateFleetMetricRequest = (UpdateFleetMetricRequest) obj;
        if ((updateFleetMetricRequest.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (updateFleetMetricRequest.getMetricName() != null && !updateFleetMetricRequest.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((updateFleetMetricRequest.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (updateFleetMetricRequest.getQueryString() != null && !updateFleetMetricRequest.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((updateFleetMetricRequest.getAggregationType() == null) ^ (getAggregationType() == null)) {
            return false;
        }
        if (updateFleetMetricRequest.getAggregationType() != null && !updateFleetMetricRequest.getAggregationType().equals(getAggregationType())) {
            return false;
        }
        if ((updateFleetMetricRequest.getPeriod() == null) ^ (getPeriod() == null)) {
            return false;
        }
        if (updateFleetMetricRequest.getPeriod() != null && !updateFleetMetricRequest.getPeriod().equals(getPeriod())) {
            return false;
        }
        if ((updateFleetMetricRequest.getAggregationField() == null) ^ (getAggregationField() == null)) {
            return false;
        }
        if (updateFleetMetricRequest.getAggregationField() != null && !updateFleetMetricRequest.getAggregationField().equals(getAggregationField())) {
            return false;
        }
        if ((updateFleetMetricRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (updateFleetMetricRequest.getDescription() != null && !updateFleetMetricRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((updateFleetMetricRequest.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        if (updateFleetMetricRequest.getQueryVersion() != null && !updateFleetMetricRequest.getQueryVersion().equals(getQueryVersion())) {
            return false;
        }
        if ((updateFleetMetricRequest.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (updateFleetMetricRequest.getIndexName() != null && !updateFleetMetricRequest.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((updateFleetMetricRequest.getUnit() == null) ^ (getUnit() == null)) {
            return false;
        }
        if (updateFleetMetricRequest.getUnit() != null && !updateFleetMetricRequest.getUnit().equals(getUnit())) {
            return false;
        }
        if ((updateFleetMetricRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return updateFleetMetricRequest.getExpectedVersion() == null || updateFleetMetricRequest.getExpectedVersion().equals(getExpectedVersion());
    }
}
