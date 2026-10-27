package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AnalyticsIntegerProperty implements AnalyticsPropertyBehavior<Integer> {
    private final Integer value;

    private AnalyticsIntegerProperty(Integer num) {
        this.value = num;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amplifyframework.analytics.AnalyticsPropertyBehavior
    @NonNull
    public Integer getValue() {
        return this.value;
    }

    @NonNull
    public static AnalyticsIntegerProperty from(@NonNull Integer num) {
        Objects.requireNonNull(num);
        return new AnalyticsIntegerProperty(num);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AnalyticsIntegerProperty.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getValue(), ((AnalyticsIntegerProperty) obj).getValue());
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    @NonNull
    public String toString() {
        return "AnalyticsIntegerProperty{value=" + this.value + '}';
    }
}
