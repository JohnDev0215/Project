package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class FeatureFlag implements Map.Entry<String, String> {
    private final String name;
    private final String variant;

    public FeatureFlag(@NonNull String str, @Nullable String str2) {
        if (str == null) {
            throw new NullPointerException("FeatureFlags cannot have null name");
        }
        this.name = str;
        this.variant = str2;
    }

    @Override // java.util.Map.Entry
    @NonNull
    public String getKey() {
        return this.name;
    }

    @Override // java.util.Map.Entry
    @Nullable
    public String getValue() {
        return this.variant;
    }

    @Override // java.util.Map.Entry
    @Nullable
    public String setValue(@Nullable String str) {
        throw new UnsupportedOperationException("FeatureFlag is immutable");
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return getKey().hashCode() ^ (getValue() == null ? 0 : getValue().hashCode());
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (getKey().equals(entry.getKey())) {
            if (getValue() == null) {
                if (entry.getValue() == null) {
                    return true;
                }
            } else if (getValue().equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "FeatureFlag{name='" + this.name + "', variant='" + this.variant + "'}";
    }
}
