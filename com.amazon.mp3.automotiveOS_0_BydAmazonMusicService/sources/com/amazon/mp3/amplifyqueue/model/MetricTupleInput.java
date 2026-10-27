package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class MetricTupleInput {
    private final String key;
    private final String value;

    public interface BuildStep {
    }

    public static class Builder implements KeyStep, ValueStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface KeyStep {
    }

    public interface ValueStep {
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MetricTupleInput.class != obj.getClass()) {
            return false;
        }
        MetricTupleInput metricTupleInput = (MetricTupleInput) obj;
        return ObjectsCompat.equals(getKey(), metricTupleInput.getKey()) && ObjectsCompat.equals(getValue(), metricTupleInput.getValue());
    }

    public int hashCode() {
        return (getKey() + getValue()).hashCode();
    }
}
