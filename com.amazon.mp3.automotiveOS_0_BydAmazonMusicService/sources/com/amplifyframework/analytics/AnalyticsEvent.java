package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.analytics.AnalyticsProperties;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AnalyticsEvent implements AnalyticsEventBehavior {
    private final String name;
    private final AnalyticsProperties properties;

    private AnalyticsEvent(String str, AnalyticsProperties analyticsProperties) {
        this.name = str;
        this.properties = analyticsProperties;
    }

    @Override // com.amplifyframework.analytics.AnalyticsEventBehavior
    @NonNull
    public String getName() {
        return this.name;
    }

    @Override // com.amplifyframework.analytics.AnalyticsEventBehavior
    @NonNull
    public AnalyticsProperties getProperties() {
        return this.properties;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AnalyticsEvent.class != obj.getClass()) {
            return false;
        }
        AnalyticsEvent analyticsEvent = (AnalyticsEvent) obj;
        if (ObjectsCompat.equals(getName(), analyticsEvent.getName())) {
            return ObjectsCompat.equals(getProperties(), analyticsEvent.getProperties());
        }
        return false;
    }

    public int hashCode() {
        return (getName().hashCode() * 31) + getProperties().hashCode();
    }

    @NonNull
    public String toString() {
        return "AnalyticsEvent{name='" + this.name + "', properties=" + this.properties + '}';
    }

    public static final class Builder {
        private String name;
        private AnalyticsProperties.Builder propertiesBuilder;

        private Builder() {
            this.propertiesBuilder = AnalyticsProperties.builder();
        }

        @NonNull
        public Builder name(@NonNull String str) {
            Objects.requireNonNull(str);
            this.name = str;
            return this;
        }

        @NonNull
        public Builder addProperty(@NonNull String str, @NonNull String str2) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(str2);
            this.propertiesBuilder.add(str, str2);
            return this;
        }

        @NonNull
        public Builder addProperty(@NonNull String str, @NonNull Double d) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(d);
            this.propertiesBuilder.add(str, d);
            return this;
        }

        @NonNull
        public Builder addProperty(@NonNull String str, @NonNull Boolean bool) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(bool);
            this.propertiesBuilder.add(str, bool);
            return this;
        }

        @NonNull
        public Builder addProperty(@NonNull String str, @NonNull Integer num) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(num);
            this.propertiesBuilder.add(str, num);
            return this;
        }

        @NonNull
        public AnalyticsEvent build() {
            return new AnalyticsEvent(this.name, this.propertiesBuilder.build());
        }
    }
}
