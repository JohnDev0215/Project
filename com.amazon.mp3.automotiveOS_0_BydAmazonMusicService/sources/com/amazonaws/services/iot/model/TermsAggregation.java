package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class TermsAggregation implements Serializable {
    private Integer maxBuckets;

    public Integer getMaxBuckets() {
        return this.maxBuckets;
    }

    public void setMaxBuckets(Integer num) {
        this.maxBuckets = num;
    }

    public TermsAggregation withMaxBuckets(Integer num) {
        this.maxBuckets = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMaxBuckets() != null) {
            sb.append("maxBuckets: " + getMaxBuckets());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getMaxBuckets() == null ? 0 : getMaxBuckets().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TermsAggregation)) {
            return false;
        }
        TermsAggregation termsAggregation = (TermsAggregation) obj;
        if ((termsAggregation.getMaxBuckets() == null) ^ (getMaxBuckets() == null)) {
            return false;
        }
        return termsAggregation.getMaxBuckets() == null || termsAggregation.getMaxBuckets().equals(getMaxBuckets());
    }
}
