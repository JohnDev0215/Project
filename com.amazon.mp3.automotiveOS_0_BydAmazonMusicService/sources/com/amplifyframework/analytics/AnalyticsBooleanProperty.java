package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AnalyticsBooleanProperty implements AnalyticsPropertyBehavior<Boolean> {
    private final Boolean value;

    private AnalyticsBooleanProperty(Boolean bool) {
        this.value = bool;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amplifyframework.analytics.AnalyticsPropertyBehavior
    public Boolean getValue() {
        return this.value;
    }

    @NonNull
    public static AnalyticsBooleanProperty from(@NonNull Boolean bool) {
        Objects.requireNonNull(bool);
        return new AnalyticsBooleanProperty(bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AnalyticsBooleanProperty.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getValue(), ((AnalyticsBooleanProperty) obj).getValue());
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    @NonNull
    public String toString() {
        return "AnalyticsBooleanProperty{value=" + this.value + '}';
    }
}
