package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AwsJobExecutionsRolloutConfig implements Serializable {
    private AwsJobExponentialRolloutRate exponentialRate;
    private Integer maximumPerMinute;

    public Integer getMaximumPerMinute() {
        return this.maximumPerMinute;
    }

    public void setMaximumPerMinute(Integer num) {
        this.maximumPerMinute = num;
    }

    public AwsJobExecutionsRolloutConfig withMaximumPerMinute(Integer num) {
        this.maximumPerMinute = num;
        return this;
    }

    public AwsJobExponentialRolloutRate getExponentialRate() {
        return this.exponentialRate;
    }

    public void setExponentialRate(AwsJobExponentialRolloutRate awsJobExponentialRolloutRate) {
        this.exponentialRate = awsJobExponentialRolloutRate;
    }

    public AwsJobExecutionsRolloutConfig withExponentialRate(AwsJobExponentialRolloutRate awsJobExponentialRolloutRate) {
        this.exponentialRate = awsJobExponentialRolloutRate;
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
        if (obj == null || !(obj instanceof AwsJobExecutionsRolloutConfig)) {
            return false;
        }
        AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig = (AwsJobExecutionsRolloutConfig) obj;
        if ((awsJobExecutionsRolloutConfig.getMaximumPerMinute() == null) ^ (getMaximumPerMinute() == null)) {
            return false;
        }
        if (awsJobExecutionsRolloutConfig.getMaximumPerMinute() != null && !awsJobExecutionsRolloutConfig.getMaximumPerMinute().equals(getMaximumPerMinute())) {
            return false;
        }
        if ((awsJobExecutionsRolloutConfig.getExponentialRate() == null) ^ (getExponentialRate() == null)) {
            return false;
        }
        return awsJobExecutionsRolloutConfig.getExponentialRate() == null || awsJobExecutionsRolloutConfig.getExponentialRate().equals(getExponentialRate());
    }
}
