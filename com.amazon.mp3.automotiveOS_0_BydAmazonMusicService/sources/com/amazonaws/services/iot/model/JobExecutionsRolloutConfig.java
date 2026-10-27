package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class JobExecutionsRolloutConfig implements Serializable {
    private ExponentialRolloutRate exponentialRate;
    private Integer maximumPerMinute;

    public Integer getMaximumPerMinute() {
        return this.maximumPerMinute;
    }

    public void setMaximumPerMinute(Integer num) {
        this.maximumPerMinute = num;
    }

    public JobExecutionsRolloutConfig withMaximumPerMinute(Integer num) {
        this.maximumPerMinute = num;
        return this;
    }

    public ExponentialRolloutRate getExponentialRate() {
        return this.exponentialRate;
    }

    public void setExponentialRate(ExponentialRolloutRate exponentialRolloutRate) {
        this.exponentialRate = exponentialRolloutRate;
    }

    public JobExecutionsRolloutConfig withExponentialRate(ExponentialRolloutRate exponentialRolloutRate) {
        this.exponentialRate = exponentialRolloutRate;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMaximumPerMinute() != null) {
            sb.append("maximumPerMinute: " + getMaximumPerMinute() + ",");
        }
        if (getExponentialRate() != null) {
            sb.append("exponentialRate: " + getExponentialRate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getMaximumPerMinute() == null ? 0 : getMaximumPerMinute().hashCode()) + 31) * 31) + (getExponentialRate() != null ? getExponentialRate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionsRolloutConfig)) {
            return false;
        }
        JobExecutionsRolloutConfig jobExecutionsRolloutConfig = (JobExecutionsRolloutConfig) obj;
        if ((jobExecutionsRolloutConfig.getMaximumPerMinute() == null) ^ (getMaximumPerMinute() == null)) {
            return false;
        }
        if (jobExecutionsRolloutConfig.getMaximumPerMinute() != null && !jobExecutionsRolloutConfig.getMaximumPerMinute().equals(getMaximumPerMinute())) {
            return false;
        }
        if ((jobExecutionsRolloutConfig.getExponentialRate() == null) ^ (getExponentialRate() == null)) {
            return false;
        }
        return jobExecutionsRolloutConfig.getExponentialRate() == null || jobExecutionsRolloutConfig.getExponentialRate().equals(getExponentialRate());
    }
}
