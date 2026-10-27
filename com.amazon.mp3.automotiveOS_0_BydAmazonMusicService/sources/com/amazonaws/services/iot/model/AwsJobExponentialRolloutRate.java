package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AwsJobExponentialRolloutRate implements Serializable {
    private Integer baseRatePerMinute;
    private Double incrementFactor;
    private AwsJobRateIncreaseCriteria rateIncreaseCriteria;

    public Integer getBaseRatePerMinute() {
        return this.baseRatePerMinute;
    }

    public void setBaseRatePerMinute(Integer num) {
        this.baseRatePerMinute = num;
    }

    public AwsJobExponentialRolloutRate withBaseRatePerMinute(Integer num) {
        this.baseRatePerMinute = num;
        return this;
    }

    public Double getIncrementFactor() {
        return this.incrementFactor;
    }

    public void setIncrementFactor(Double d) {
        this.incrementFactor = d;
    }

    public AwsJobExponentialRolloutRate withIncrementFactor(Double d) {
        this.incrementFactor = d;
        return this;
    }

    public AwsJobRateIncreaseCriteria getRateIncreaseCriteria() {
        return this.rateIncreaseCriteria;
    }

    public void setRateIncreaseCriteria(AwsJobRateIncreaseCriteria awsJobRateIncreaseCriteria) {
        this.rateIncreaseCriteria = awsJobRateIncreaseCriteria;
    }

    public AwsJobExponentialRolloutRate withRateIncreaseCriteria(AwsJobRateIncreaseCriteria awsJobRateIncreaseCriteria) {
        this.rateIncreaseCriteria = awsJobRateIncreaseCriteria;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBaseRatePerMinute() != null) {
            sb.append("baseRatePerMinute: " + getBaseRatePerMinute() + ",");
        }
        if (getIncrementFactor() != null) {
            sb.append("incrementFactor: " + getIncrementFactor() + ",");
        }
        if (getRateIncreaseCriteria() != null) {
            sb.append("rateIncreaseCriteria: " + getRateIncreaseCriteria());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getBaseRatePerMinute() == null ? 0 : getBaseRatePerMinute().hashCode()) + 31) * 31) + (getIncrementFactor() == null ? 0 : getIncrementFactor().hashCode())) * 31) + (getRateIncreaseCriteria() != null ? getRateIncreaseCriteria().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AwsJobExponentialRolloutRate)) {
            return false;
        }
        AwsJobExponentialRolloutRate awsJobExponentialRolloutRate = (AwsJobExponentialRolloutRate) obj;
        if ((awsJobExponentialRolloutRate.getBaseRatePerMinute() == null) ^ (getBaseRatePerMinute() == null)) {
            return false;
        }
        if (awsJobExponentialRolloutRate.getBaseRatePerMinute() != null && !awsJobExponentialRolloutRate.getBaseRatePerMinute().equals(getBaseRatePerMinute())) {
            return false;
        }
        if ((awsJobExponentialRolloutRate.getIncrementFactor() == null) ^ (getIncrementFactor() == null)) {
            return false;
        }
        if (awsJobExponentialRolloutRate.getIncrementFactor() != null && !awsJobExponentialRolloutRate.getIncrementFactor().equals(getIncrementFactor())) {
            return false;
        }
        if ((awsJobExponentialRolloutRate.getRateIncreaseCriteria() == null) ^ (getRateIncreaseCriteria() == null)) {
            return false;
        }
        return awsJobExponentialRolloutRate.getRateIncreaseCriteria() == null || awsJobExponentialRolloutRate.getRateIncreaseCriteria().equals(getRateIncreaseCriteria());
    }
}
