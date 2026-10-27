package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AnalyticsDoubleProperty implements AnalyticsPropertyBehavior<Double> {
    private final Double value;

    private AnalyticsDoubleProperty(Double d) {
        this.value = d;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amplifyframework.analytics.AnalyticsPropertyBehavior
    public Double getValue() {
        return this.value;
    }

    @NonNull
    public static AnalyticsDoubleProperty from(@NonNull Double d) {
        Objects.requireNonNull(d);
        return new AnalyticsDoubleProperty(d);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AnalyticsDoubleProperty.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getValue(), ((AnalyticsDoubleProperty) obj).getValue());
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    @NonNull
    public String toString() {
        return "AnalyticsDoubleProperty{value=" + this.value + '}';
    }
}
