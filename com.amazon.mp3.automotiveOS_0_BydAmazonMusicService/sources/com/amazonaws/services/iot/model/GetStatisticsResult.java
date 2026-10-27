package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetStatisticsResult implements Serializable {
    private Statistics statistics;

    public Statistics getStatistics() {
        return this.statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public GetStatisticsResult withStatistics(Statistics statistics) {
        this.statistics = statistics;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStatistics() != null) {
            sb.append("statistics: " + getStatistics());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getStatistics() == null ? 0 : getStatistics().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetStatisticsResult)) {
            return false;
        }
        GetStatisticsResult getStatisticsResult = (GetStatisticsResult) obj;
        if ((getStatisticsResult.getStatistics() == null) ^ (getStatistics() == null)) {
            return false;
        }
        return getStatisticsResult.getStatistics() == null || getStatisticsResult.getStatistics().equals(getStatistics());
    }
}
