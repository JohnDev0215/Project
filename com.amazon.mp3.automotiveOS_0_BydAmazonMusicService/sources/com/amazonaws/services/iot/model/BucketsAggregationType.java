package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class BucketsAggregationType implements Serializable {
    private TermsAggregation termsAggregation;

    public TermsAggregation getTermsAggregation() {
        return this.termsAggregation;
    }

    public void setTermsAggregation(TermsAggregation termsAggregation) {
        this.termsAggregation = termsAggregation;
    }

    public BucketsAggregationType withTermsAggregation(TermsAggregation termsAggregation) {
        this.termsAggregation = termsAggregation;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTermsAggregation() != null) {
            sb.append("termsAggregation: " + getTermsAggregation());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTermsAggregation() == null ? 0 : getTermsAggregation().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BucketsAggregationType)) {
            return false;
        }
        BucketsAggregationType bucketsAggregationType = (BucketsAggregationType) obj;
        if ((bucketsAggregationType.getTermsAggregation() == null) ^ (getTermsAggregation() == null)) {
            return false;
        }
        return bucketsAggregationType.getTermsAggregation() == null || bucketsAggregationType.getTermsAggregation().equals(getTermsAggregation());
    }
}
