package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public enum AnalyticsChannelEventName {
    INVALID_PROPERTY_TYPE("invalid_property_type");

    private final String hubEventName;

    AnalyticsChannelEventName(@NonNull String str) {
        Objects.requireNonNull(str);
        this.hubEventName = str;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.hubEventName;
    }

    @NonNull
    public static AnalyticsChannelEventName fromString(@Nullable String str) {
        for (AnalyticsChannelEventName analyticsChannelEventName : values()) {
            if (analyticsChannelEventName.toString().equals(str)) {
                return analyticsChannelEventName;
            }
        }
        throw new IllegalArgumentException("Analytics category does not publish any Hub event with name = " + str);
    }
}
