package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ExponentialRolloutRate implements Serializable {
    private Integer baseRatePerMinute;
    private Double incrementFactor;
    private RateIncreaseCriteria rateIncreaseCriteria;

    public Integer getBaseRatePerMinute() {
        return this.baseRatePerMinute;
    }

    public void setBaseRatePerMinute(Integer num) {
        this.baseRatePerMinute = num;
    }

    public ExponentialRolloutRate withBaseRatePerMinute(Integer num) {
        this.baseRatePerMinute = num;
        return this;
    }

    public Double getIncrementFactor() {
        return this.incrementFactor;
    }

    public void setIncrementFactor(Double d) {
        this.incrementFactor = d;
    }

    public ExponentialRolloutRate withIncrementFactor(Double d) {
        this.incrementFactor = d;
        return this;
    }

    public RateIncreaseCriteria getRateIncreaseCriteria() {
        return this.rateIncreaseCriteria;
    }

    public void setRateIncreaseCriteria(RateIncreaseCriteria rateIncreaseCriteria) {
        this.rateIncreaseCriteria = rateIncreaseCriteria;
    }

    public ExponentialRolloutRate withRateIncreaseCriteria(RateIncreaseCriteria rateIncreaseCriteria) {
        this.rateIncreaseCriteria = rateIncreaseCriteria;
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
        if (obj == null || !(obj instanceof ExponentialRolloutRate)) {
            return false;
        }
        ExponentialRolloutRate exponentialRolloutRate = (ExponentialRolloutRate) obj;
        if ((exponentialRolloutRate.getBaseRatePerMinute() == null) ^ (getBaseRatePerMinute() == null)) {
            return false;
        }
        if (exponentialRolloutRate.getBaseRatePerMinute() != null && !exponentialRolloutRate.getBaseRatePerMinute().equals(getBaseRatePerMinute())) {
            return false;
        }
        if ((exponentialRolloutRate.getIncrementFactor() == null) ^ (getIncrementFactor() == null)) {
            return false;
        }
        if (exponentialRolloutRate.getIncrementFactor() != null && !exponentialRolloutRate.getIncrementFactor().equals(getIncrementFactor())) {
            return false;
        }
        if ((exponentialRolloutRate.getRateIncreaseCriteria() == null) ^ (getRateIncreaseCriteria() == null)) {
            return false;
        }
        return exponentialRolloutRate.getRateIncreaseCriteria() == null || exponentialRolloutRate.getRateIncreaseCriteria().equals(getRateIncreaseCriteria());
    }
}
