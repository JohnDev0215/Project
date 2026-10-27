package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AnalyticsStringProperty implements AnalyticsPropertyBehavior<String> {
    private final String value;

    private AnalyticsStringProperty(String str) {
        this.value = str;
    }

    @Override // com.amplifyframework.analytics.AnalyticsPropertyBehavior
    public String getValue() {
        return this.value;
    }

    @NonNull
    public static AnalyticsStringProperty from(@NonNull String str) {
        Objects.requireNonNull(str);
        return new AnalyticsStringProperty(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AnalyticsStringProperty.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getValue(), ((AnalyticsStringProperty) obj).getValue());
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    @NonNull
    public String toString() {
        return "AnalyticsStringProperty{value='" + this.value + "'}";
    }
}
