package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetBucketsAggregationRequest extends AmazonWebServiceRequest implements Serializable {
    private String aggregationField;
    private BucketsAggregationType bucketsAggregationType;
    private String indexName;
    private String queryString;
    private String queryVersion;

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public GetBucketsAggregationRequest withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public GetBucketsAggregationRequest withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public String getAggregationField() {
        return this.aggregationField;
    }

    public void setAggregationField(String str) {
        this.aggregationField = str;
    }

    public GetBucketsAggregationRequest withAggregationField(String str) {
        this.aggregationField = str;
        return this;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public GetBucketsAggregationRequest withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public BucketsAggregationType getBucketsAggregationType() {
        return this.bucketsAggregationType;
    }

    public void setBucketsAggregationType(BucketsAggregationType bucketsAggregationType) {
        this.bucketsAggregationType = bucketsAggregationType;
    }

    public GetBucketsAggregationRequest withBucketsAggregationType(BucketsAggregationType bucketsAggregationType) {
        this.bucketsAggregationType = bucketsAggregationType;
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
        if (getBucketsAggregationType() != null) {
            sb.append("bucketsAggregationType: " + getBucketsAggregationType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getIndexName() == null ? 0 : getIndexName().hashCode()) + 31) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getAggregationField() == null ? 0 : getAggregationField().hashCode())) * 31) + (getQueryVersion() == null ? 0 : getQueryVersion().hashCode())) * 31) + (getBucketsAggregationType() != null ? getBucketsAggregationType().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetBucketsAggregationRequest)) {
            return false;
        }
        GetBucketsAggregationRequest getBucketsAggregationRequest = (GetBucketsAggregationRequest) obj;
        if ((getBucketsAggregationRequest.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (getBucketsAggregationRequest.getIndexName() != null && !getBucketsAggregationRequest.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((getBucketsAggregationRequest.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (getBucketsAggregationRequest.getQueryString() != null && !getBucketsAggregationRequest.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((getBucketsAggregationRequest.getAggregationField() == null) ^ (getAggregationField() == null)) {
            return false;
        }
        if (getBucketsAggregationRequest.getAggregationField() != null && !getBucketsAggregationRequest.getAggregationField().equals(getAggregationField())) {
            return false;
        }
        if ((getBucketsAggregationRequest.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        if (getBucketsAggregationRequest.getQueryVersion() != null && !getBucketsAggregationRequest.getQueryVersion().equals(getQueryVersion())) {
            return false;
        }
        if ((getBucketsAggregationRequest.getBucketsAggregationType() == null) ^ (getBucketsAggregationType() == null)) {
            return false;
        }
        return getBucketsAggregationRequest.getBucketsAggregationType() == null || getBucketsAggregationRequest.getBucketsAggregationType().equals(getBucketsAggregationType());
    }
}
