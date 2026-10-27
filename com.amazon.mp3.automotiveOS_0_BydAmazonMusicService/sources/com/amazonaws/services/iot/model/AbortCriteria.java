package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AbortCriteria implements Serializable {
    private String action;
    private String failureType;
    private Integer minNumberOfExecutedThings;
    private Double thresholdPercentage;

    public String getFailureType() {
        return this.failureType;
    }

    public void setFailureType(String str) {
        this.failureType = str;
    }

    public AbortCriteria withFailureType(String str) {
        this.failureType = str;
        return this;
    }

    public void setFailureType(JobExecutionFailureType jobExecutionFailureType) {
        this.failureType = jobExecutionFailureType.toString();
    }

    public AbortCriteria withFailureType(JobExecutionFailureType jobExecutionFailureType) {
        this.failureType = jobExecutionFailureType.toString();
        return this;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public AbortCriteria withAction(String str) {
        this.action = str;
        return this;
    }

    public void setAction(AbortAction abortAction) {
        this.action = abortAction.toString();
    }

    public AbortCriteria withAction(AbortAction abortAction) {
        this.action = abortAction.toString();
        return this;
    }

    public Double getThresholdPercentage() {
        return this.thresholdPercentage;
    }

    public void setThresholdPercentage(Double d) {
        this.thresholdPercentage = d;
    }

    public AbortCriteria withThresholdPercentage(Double d) {
        this.thresholdPercentage = d;
        return this;
    }

    public Integer getMinNumberOfExecutedThings() {
        return this.minNumberOfExecutedThings;
    }

    public void setMinNumberOfExecutedThings(Integer num) {
        this.minNumberOfExecutedThings = num;
    }

    public AbortCriteria withMinNumberOfExecutedThings(Integer num) {
        this.minNumberOfExecutedThings = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFailureType() != null) {
            sb.append("failureType: " + getFailureType() + ",");
        }
        if (getAction() != null) {
            sb.append("action: " + getAction() + ",");
        }
        if (getThresholdPercentage() != null) {
            sb.append("thresholdPercentage: " + getThresholdPercentage() + ",");
        }
        if (getMinNumberOfExecutedThings() != null) {
            sb.append("minNumberOfExecutedThings: " + getMinNumberOfExecutedThings());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getFailureType() == null ? 0 : getFailureType().hashCode()) + 31) * 31) + (getAction() == null ? 0 : getAction().hashCode())) * 31) + (getThresholdPercentage() == null ? 0 : getThresholdPercentage().hashCode())) * 31) + (getMinNumberOfExecutedThings() != null ? getMinNumberOfExecutedThings().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AbortCriteria)) {
            return false;
        }
        AbortCriteria abortCriteria = (AbortCriteria) obj;
        if ((abortCriteria.getFailureType() == null) ^ (getFailureType() == null)) {
            return false;
        }
        if (abortCriteria.getFailureType() != null && !abortCriteria.getFailureType().equals(getFailureType())) {
            return false;
        }
        if ((abortCriteria.getAction() == null) ^ (getAction() == null)) {
            return false;
        }
        if (abortCriteria.getAction() != null && !abortCriteria.getAction().equals(getAction())) {
            return false;
        }
        if ((abortCriteria.getThresholdPercentage() == null) ^ (getThresholdPercentage() == null)) {
            return false;
        }
        if (abortCriteria.getThresholdPercentage() != null && !abortCriteria.getThresholdPercentage().equals(getThresholdPercentage())) {
            return false;
        }
        if ((abortCriteria.getMinNumberOfExecutedThings() == null) ^ (getMinNumberOfExecutedThings() == null)) {
            return false;
        }
        return abortCriteria.getMinNumberOfExecutedThings() == null || abortCriteria.getMinNumberOfExecutedThings().equals(getMinNumberOfExecutedThings());
    }
}
