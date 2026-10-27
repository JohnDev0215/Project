package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetCardinalityRequest extends AmazonWebServiceRequest implements Serializable {
    private String aggregationField;
    private String indexName;
    private String queryString;
    private String queryVersion;

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public GetCardinalityRequest withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public GetCardinalityRequest withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public String getAggregationField() {
        return this.aggregationField;
    }

    public void setAggregationField(String str) {
        this.aggregationField = str;
    }

    public GetCardinalityRequest withAggregationField(String str) {
        this.aggregationField = str;
        return this;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public GetCardinalityRequest withQueryVersion(String str) {
        this.queryVersion = str;
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
            sb.append("queryVersion: " + getQueryVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getIndexName() == null ? 0 : getIndexName().hashCode()) + 31) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getAggregationField() == null ? 0 : getAggregationField().hashCode())) * 31) + (getQueryVersion() != null ? getQueryVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetCardinalityRequest)) {
            return false;
        }
        GetCardinalityRequest getCardinalityRequest = (GetCardinalityRequest) obj;
        if ((getCardinalityRequest.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (getCardinalityRequest.getIndexName() != null && !getCardinalityRequest.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((getCardinalityRequest.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (getCardinalityRequest.getQueryString() != null && !getCardinalityRequest.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((getCardinalityRequest.getAggregationField() == null) ^ (getAggregationField() == null)) {
            return false;
        }
        if (getCardinalityRequest.getAggregationField() != null && !getCardinalityRequest.getAggregationField().equals(getAggregationField())) {
            return false;
        }
        if ((getCardinalityRequest.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        return getCardinalityRequest.getQueryVersion() == null || getCardinalityRequest.getQueryVersion().equals(getQueryVersion());
    }
}
