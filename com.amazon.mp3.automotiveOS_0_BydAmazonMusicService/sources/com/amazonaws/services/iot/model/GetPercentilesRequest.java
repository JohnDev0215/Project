package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetPercentilesRequest extends AmazonWebServiceRequest implements Serializable {
    private String aggregationField;
    private String indexName;
    private List<Double> percents;
    private String queryString;
    private String queryVersion;

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public GetPercentilesRequest withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public GetPercentilesRequest withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public String getAggregationField() {
        return this.aggregationField;
    }

    public void setAggregationField(String str) {
        this.aggregationField = str;
    }

    public GetPercentilesRequest withAggregationField(String str) {
        this.aggregationField = str;
        return this;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public GetPercentilesRequest withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public List<Double> getPercents() {
        return this.percents;
    }

    public void setPercents(Collection<Double> collection) {
        if (collection == null) {
            this.percents = null;
        } else {
            this.percents = new ArrayList(collection);
        }
    }

    public GetPercentilesRequest withPercents(Double... dArr) {
        if (getPercents() == null) {
            this.percents = new ArrayList(dArr.length);
        }
        for (Double d : dArr) {
            this.percents.add(d);
        }
        return this;
    }

    public GetPercentilesRequest withPercents(Collection<Double> collection) {
        setPercents(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIndexName() != null) {
            sb.append("indexName: " + getIndexName() + ",");
        }
        if (getQueryString() != null) {
            sb.append("queryString: " + getQueryString() + ",");
        }
        if (getAggregationField() != null) {
            sb.append("aggregationField: " + getAggregationField() + ",");
        }
        if (getQueryVersion() != null) {
            sb.append("queryVersion: " + getQueryVersion() + ",");
        }
        if (getPercents() != null) {
            sb.append("percents: " + getPercents());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getIndexName() == null ? 0 : getIndexName().hashCode()) + 31) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getAggregationField() == null ? 0 : getAggregationField().hashCode())) * 31) + (getQueryVersion() == null ? 0 : getQueryVersion().hashCode())) * 31) + (getPercents() != null ? getPercents().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetPercentilesRequest)) {
            return false;
        }
        GetPercentilesRequest getPercentilesRequest = (GetPercentilesRequest) obj;
        if ((getPercentilesRequest.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (getPercentilesRequest.getIndexName() != null && !getPercentilesRequest.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((getPercentilesRequest.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (getPercentilesRequest.getQueryString() != null && !getPercentilesRequest.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((getPercentilesRequest.getAggregationField() == null) ^ (getAggregationField() == null)) {
            return false;
        }
        if (getPercentilesRequest.getAggregationField() != null && !getPercentilesRequest.getAggregationField().equals(getAggregationField())) {
            return false;
        }
        if ((getPercentilesRequest.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        if (getPercentilesRequest.getQueryVersion() != null && !getPercentilesRequest.getQueryVersion().equals(getQueryVersion())) {
            return false;
        }
        if ((getPercentilesRequest.getPercents() == null) ^ (getPercents() == null)) {
            return false;
        }
        return getPercentilesRequest.getPercents() == null || getPercentilesRequest.getPercents().equals(getPercents());
    }
}
