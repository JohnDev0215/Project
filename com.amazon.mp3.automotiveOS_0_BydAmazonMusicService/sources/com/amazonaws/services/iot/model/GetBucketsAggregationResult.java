package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetBucketsAggregationResult implements Serializable {
    private List<Bucket> buckets;
    private Integer totalCount;

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer num) {
        this.totalCount = num;
    }

    public GetBucketsAggregationResult withTotalCount(Integer num) {
        this.totalCount = num;
        return this;
    }

    public List<Bucket> getBuckets() {
        return this.buckets;
    }

    public void setBuckets(Collection<Bucket> collection) {
        if (collection == null) {
            this.buckets = null;
        } else {
            this.buckets = new ArrayList(collection);
        }
    }

    public GetBucketsAggregationResult withBuckets(Bucket... bucketArr) {
        if (getBuckets() == null) {
            this.buckets = new ArrayList(bucketArr.length);
        }
        for (Bucket bucket : bucketArr) {
            this.buckets.add(bucket);
        }
        return this;
    }

    public GetBucketsAggregationResult withBuckets(Collection<Bucket> collection) {
        setBuckets(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTotalCount() != null) {
            sb.append("totalCount: " + getTotalCount() + ",");
        }
        if (getBuckets() != null) {
            sb.append("buckets: " + getBuckets());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTotalCount() == null ? 0 : getTotalCount().hashCode()) + 31) * 31) + (getBuckets() != null ? getBuckets().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetBucketsAggregationResult)) {
            return false;
        }
        GetBucketsAggregationResult getBucketsAggregationResult = (GetBucketsAggregationResult) obj;
        if ((getBucketsAggregationResult.getTotalCount() == null) ^ (getTotalCount() == null)) {
            return false;
        }
        if (getBucketsAggregationResult.getTotalCount() != null && !getBucketsAggregationResult.getTotalCount().equals(getTotalCount())) {
            return false;
        }
        if ((getBucketsAggregationResult.getBuckets() == null) ^ (getBuckets() == null)) {
            return false;
        }
        return getBucketsAggregationResult.getBuckets() == null || getBucketsAggregationResult.getBuckets().equals(getBuckets());
    }
}
