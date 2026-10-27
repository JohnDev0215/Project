package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class MetricsContextTuple {
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
        if (obj == null || MetricsContextTuple.class != obj.getClass()) {
            return false;
        }
        MetricsContextTuple metricsContextTuple = (MetricsContextTuple) obj;
        return ObjectsCompat.equals(getKey(), metricsContextTuple.getKey()) && ObjectsCompat.equals(getValue(), metricsContextTuple.getValue());
    }

    public int hashCode() {
        return (getKey() + getValue()).hashCode();
    }
}
