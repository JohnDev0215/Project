package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateFleetMetricRequest extends AmazonWebServiceRequest implements Serializable {
    private String aggregationField;
    private AggregationType aggregationType;
    private String description;
    private String indexName;
    private String metricName;
    private Integer period;
    private String queryString;
    private String queryVersion;
    private List<Tag> tags;
    private String unit;

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public CreateFleetMetricRequest withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public CreateFleetMetricRequest withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public AggregationType getAggregationType() {
        return this.aggregationType;
    }

    public void setAggregationType(AggregationType aggregationType) {
        this.aggregationType = aggregationType;
    }

    public CreateFleetMetricRequest withAggregationType(AggregationType aggregationType) {
        this.aggregationType = aggregationType;
        return this;
    }

    public Integer getPeriod() {
        return this.period;
    }

    public void setPeriod(Integer num) {
        this.period = num;
    }

    public CreateFleetMetricRequest withPeriod(Integer num) {
        this.period = num;
        return this;
    }

    public String getAggregationField() {
        return this.aggregationField;
    }

    public void setAggregationField(String str) {
        this.aggregationField = str;
    }

    public CreateFleetMetricRequest withAggregationField(String str) {
        this.aggregationField = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public CreateFleetMetricRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public CreateFleetMetricRequest withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public CreateFleetMetricRequest withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public CreateFleetMetricRequest withUnit(String str) {
        this.unit = str;
        return this;
    }

    public void setUnit(FleetMetricUnit fleetMetricUnit) {
        this.unit = fleetMetricUnit.toString();
    }

    public CreateFleetMetricRequest withUnit(FleetMetricUnit fleetMetricUnit) {
        this.unit = fleetMetricUnit.toString();
        return this;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public CreateFleetMetricRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateFleetMetricRequest withTags(Collection<Tag> collection) {
        setTags(collection);
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
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((getMetricName() == null ? 0 : getMetricName().hashCode()) + 31) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getAggregationType() == null ? 0 : getAggregationType().hashCode())) * 31) + (getPeriod() == null ? 0 : getPeriod().hashCode())) * 31) + (getAggregationField() == null ? 0 : getAggregationField().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getQueryVersion() == null ? 0 : getQueryVersion().hashCode())) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getUnit() == null ? 0 : getUnit().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateFleetMetricRequest)) {
            return false;
        }
        CreateFleetMetricRequest createFleetMetricRequest = (CreateFleetMetricRequest) obj;
        if ((createFleetMetricRequest.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (createFleetMetricRequest.getMetricName() != null && !createFleetMetricRequest.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((createFleetMetricRequest.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (createFleetMetricRequest.getQueryString() != null && !createFleetMetricRequest.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((createFleetMetricRequest.getAggregationType() == null) ^ (getAggregationType() == null)) {
            return false;
        }
        if (createFleetMetricRequest.getAggregationType() != null && !createFleetMetricRequest.getAggregationType().equals(getAggregationType())) {
            return false;
        }
        if ((createFleetMetricRequest.getPeriod() == null) ^ (getPeriod() == null)) {
            return false;
        }
        if (createFleetMetricRequest.getPeriod() != null && !createFleetMetricRequest.getPeriod().equals(getPeriod())) {
            return false;
        }
        if ((createFleetMetricRequest.getAggregationField() == null) ^ (getAggregationField() == null)) {
            return false;
        }
        if (createFleetMetricRequest.getAggregationField() != null && !createFleetMetricRequest.getAggregationField().equals(getAggregationField())) {
            return false;
        }
        if ((createFleetMetricRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createFleetMetricRequest.getDescription() != null && !createFleetMetricRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createFleetMetricRequest.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        if (createFleetMetricRequest.getQueryVersion() != null && !createFleetMetricRequest.getQueryVersion().equals(getQueryVersion())) {
            return false;
        }
        if ((createFleetMetricRequest.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (createFleetMetricRequest.getIndexName() != null && !createFleetMetricRequest.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((createFleetMetricRequest.getUnit() == null) ^ (getUnit() == null)) {
            return false;
        }
        if (createFleetMetricRequest.getUnit() != null && !createFleetMetricRequest.getUnit().equals(getUnit())) {
            return false;
        }
        if ((createFleetMetricRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createFleetMetricRequest.getTags() == null || createFleetMetricRequest.getTags().equals(getTags());
    }
}
