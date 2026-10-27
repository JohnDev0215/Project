package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class StatisticalThreshold implements Serializable {
    private String statistic;

    public String getStatistic() {
        return this.statistic;
    }

    public void setStatistic(String str) {
        this.statistic = str;
    }

    public StatisticalThreshold withStatistic(String str) {
        this.statistic = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStatistic() != null) {
            sb.append("statistic: " + getStatistic());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getStatistic() == null ? 0 : getStatistic().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StatisticalThreshold)) {
            return false;
        }
        StatisticalThreshold statisticalThreshold = (StatisticalThreshold) obj;
        if ((statisticalThreshold.getStatistic() == null) ^ (getStatistic() == null)) {
            return false;
        }
        return statisticalThreshold.getStatistic() == null || statisticalThreshold.getStatistic().equals(getStatistic());
    }
}
