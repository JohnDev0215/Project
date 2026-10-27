package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AnalyticsProperties implements Iterable<Map.Entry<String, AnalyticsPropertyBehavior<?>>> {
    private final Map<String, AnalyticsPropertyBehavior<?>> properties;

    private AnalyticsProperties(Map<String, AnalyticsPropertyBehavior<?>> map) {
        this.properties = map;
    }

    @Override // java.lang.Iterable
    @NonNull
    public Iterator<Map.Entry<String, AnalyticsPropertyBehavior<?>>> iterator() {
        return this.properties.entrySet().iterator();
    }

    public int size() {
        return this.properties.size();
    }

    @NonNull
    public AnalyticsPropertyBehavior<?> get(@NonNull String str) throws NoSuchElementException {
        if (this.properties.get(str) == null) {
            throw new NoSuchElementException("AnalyticsPropertyBehavior not found " + str);
        }
        return this.properties.get(str);
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AnalyticsProperties.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.properties, ((AnalyticsProperties) obj).properties);
    }

    public int hashCode() {
        return this.properties.hashCode();
    }

    @NonNull
    public String toString() {
        return "AnalyticsProperties{properties=" + this.properties + '}';
    }

    public static final class Builder {
        private final Map<String, AnalyticsPropertyBehavior<?>> properties = new HashMap();

        @NonNull
        public Builder add(@NonNull String str, @NonNull String str2) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(str2);
            this.properties.put(str, AnalyticsStringProperty.from(str2));
            return this;
        }

        @NonNull
        public Builder add(@NonNull String str, @NonNull Double d) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(d);
            this.properties.put(str, AnalyticsDoubleProperty.from(d));
            return this;
        }

        @NonNull
        public Builder add(@NonNull String str, @NonNull Boolean bool) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(bool);
            this.properties.put(str, AnalyticsBooleanProperty.from(bool));
            return this;
        }

        @NonNull
        public Builder add(@NonNull String str, @NonNull Integer num) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(num);
            this.properties.put(str, AnalyticsIntegerProperty.from(num));
            return this;
        }

        @NonNull
        public <T, P extends AnalyticsPropertyBehavior<T>> Builder add(@NonNull String str, @NonNull P p) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(p);
            this.properties.put(str, p);
            return this;
        }

        @NonNull
        public AnalyticsProperties build() {
            return new AnalyticsProperties(this.properties);
        }
    }
}
