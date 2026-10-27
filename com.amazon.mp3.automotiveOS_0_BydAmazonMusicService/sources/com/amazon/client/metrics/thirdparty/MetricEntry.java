package com.amazon.client.metrics.thirdparty;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MetricEntry {
    private final List<DataPoint> mDatapoints;
    private final String mProgram;
    private final String mSource;
    private final long mTimestamp;

    public MetricEntry(long j, String str, String str2, List<DataPoint> list) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("program cannot be empty or null");
        }
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("source cannot be empty or null");
        }
        if (list == null) {
            throw new IllegalArgumentException("data points cannot be null");
        }
        this.mTimestamp = j;
        this.mProgram = str;
        this.mSource = str2;
        this.mDatapoints = list;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public String getProgram() {
        return this.mProgram;
    }

    public String getSource() {
        return this.mSource;
    }

    public List<DataPoint> getDatapoints() {
        return this.mDatapoints;
    }

    public int hashCode() {
        int iHashCode = (((((this.mDatapoints.hashCode() + 31) * 31) + this.mProgram.hashCode()) * 31) + this.mSource.hashCode()) * 31;
        long j = this.mTimestamp;
        return iHashCode + ((int) (j ^ (j >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MetricEntry metricEntry = (MetricEntry) obj;
        return this.mDatapoints.equals(metricEntry.mDatapoints) && this.mProgram.equals(metricEntry.mProgram) && this.mSource.equals(metricEntry.mSource) && this.mTimestamp == metricEntry.mTimestamp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mTimestamp);
        sb.append(" ");
        sb.append(this.mProgram);
        sb.append(" ");
        sb.append(this.mSource);
        sb.append(" ");
        for (int i = 0; i < this.mDatapoints.size(); i++) {
            sb.append(this.mDatapoints.get(i).toString());
        }
        return sb.toString();
    }
}
