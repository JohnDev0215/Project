package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class RetryCriteria implements Serializable {
    private String failureType;
    private Integer numberOfRetries;

    public String getFailureType() {
        return this.failureType;
    }

    public void setFailureType(String str) {
        this.failureType = str;
    }

    public RetryCriteria withFailureType(String str) {
        this.failureType = str;
        return this;
    }

    public void setFailureType(RetryableFailureType retryableFailureType) {
        this.failureType = retryableFailureType.toString();
    }

    public RetryCriteria withFailureType(RetryableFailureType retryableFailureType) {
        this.failureType = retryableFailureType.toString();
        return this;
    }

    public Integer getNumberOfRetries() {
        return this.numberOfRetries;
    }

    public void setNumberOfRetries(Integer num) {
        this.numberOfRetries = num;
    }

    public RetryCriteria withNumberOfRetries(Integer num) {
        this.numberOfRetries = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFailureType() != null) {
            sb.append("failureType: " + getFailureType() + ",");
        }
        if (getNumberOfRetries() != null) {
            sb.append("numberOfRetries: " + getNumberOfRetries());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getFailureType() == null ? 0 : getFailureType().hashCode()) + 31) * 31) + (getNumberOfRetries() != null ? getNumberOfRetries().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RetryCriteria)) {
            return false;
        }
        RetryCriteria retryCriteria = (RetryCriteria) obj;
        if ((retryCriteria.getFailureType() == null) ^ (getFailureType() == null)) {
            return false;
        }
        if (retryCriteria.getFailureType() != null && !retryCriteria.getFailureType().equals(getFailureType())) {
            return false;
        }
        if ((retryCriteria.getNumberOfRetries() == null) ^ (getNumberOfRetries() == null)) {
            return false;
        }
        return retryCriteria.getNumberOfRetries() == null || retryCriteria.getNumberOfRetries().equals(getNumberOfRetries());
    }
}
