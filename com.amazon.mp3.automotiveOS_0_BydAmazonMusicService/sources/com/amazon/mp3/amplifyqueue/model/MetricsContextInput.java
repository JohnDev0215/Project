package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class MetricsContextInput {
    private final List<MetricTupleInput> metricTuple;

    public interface BuildStep {
    }

    public static class Builder implements MetricTupleStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface MetricTupleStep {
    }

    public List<MetricTupleInput> getMetricTuple() {
        return this.metricTuple;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MetricsContextInput.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getMetricTuple(), ((MetricsContextInput) obj).getMetricTuple());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMetricTuple());
        return sb.toString().hashCode();
    }
}
