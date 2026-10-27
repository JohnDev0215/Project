package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AwsJobRateIncreaseCriteria implements Serializable {
    private Integer numberOfNotifiedThings;
    private Integer numberOfSucceededThings;

    public Integer getNumberOfNotifiedThings() {
        return this.numberOfNotifiedThings;
    }

    public void setNumberOfNotifiedThings(Integer num) {
        this.numberOfNotifiedThings = num;
    }

    public AwsJobRateIncreaseCriteria withNumberOfNotifiedThings(Integer num) {
        this.numberOfNotifiedThings = num;
        return this;
    }

    public Integer getNumberOfSucceededThings() {
        return this.numberOfSucceededThings;
    }

    public void setNumberOfSucceededThings(Integer num) {
        this.numberOfSucceededThings = num;
    }

    public AwsJobRateIncreaseCriteria withNumberOfSucceededThings(Integer num) {
        this.numberOfSucceededThings = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getNumberOfNotifiedThings() != null) {
            sb.append("numberOfNotifiedThings: " + getNumberOfNotifiedThings() + ",");
        }
        if (getNumberOfSucceededThings() != null) {
            sb.append("numberOfSucceededThings: " + getNumberOfSucceededThings());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getNumberOfNotifiedThings() == null ? 0 : getNumberOfNotifiedThings().hashCode()) + 31) * 31) + (getNumberOfSucceededThings() != null ? getNumberOfSucceededThings().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AwsJobRateIncreaseCriteria)) {
            return false;
        }
        AwsJobRateIncreaseCriteria awsJobRateIncreaseCriteria = (AwsJobRateIncreaseCriteria) obj;
        if ((awsJobRateIncreaseCriteria.getNumberOfNotifiedThings() == null) ^ (getNumberOfNotifiedThings() == null)) {
            return false;
        }
        if (awsJobRateIncreaseCriteria.getNumberOfNotifiedThings() != null && !awsJobRateIncreaseCriteria.getNumberOfNotifiedThings().equals(getNumberOfNotifiedThings())) {
            return false;
        }
        if ((awsJobRateIncreaseCriteria.getNumberOfSucceededThings() == null) ^ (getNumberOfSucceededThings() == null)) {
            return false;
        }
        return awsJobRateIncreaseCriteria.getNumberOfSucceededThings() == null || awsJobRateIncreaseCriteria.getNumberOfSucceededThings().equals(getNumberOfSucceededThings());
    }
}
