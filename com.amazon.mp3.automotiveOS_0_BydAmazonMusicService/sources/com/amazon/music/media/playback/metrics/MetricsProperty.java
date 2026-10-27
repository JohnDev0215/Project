package com.amazon.music.media.playback.metrics;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.util.StrictMode;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsProperty<T> {
    private final T defaultValue;
    private final String name;
    private final Class<T> propertyType;
    private final MetricsProperty<T>.SetPropertyEventType setterEventType;

    public MetricsProperty(@NonNull String str, @NonNull Class<T> cls) {
        this(str, cls, null);
    }

    public MetricsProperty(@NonNull String str, @NonNull Class<T> cls, T t) {
        this.name = (String) Validate.notEmpty(str);
        this.propertyType = (Class) Validate.notNull(cls);
        this.defaultValue = t;
        this.setterEventType = new SetPropertyEventType();
    }

    public String getName() {
        return this.name;
    }

    public Class<T> getPropertyType() {
        return this.propertyType;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public void validate(T t) {
        if (t != null && !this.propertyType.isInstance(t)) {
            throw new IllegalArgumentException("event value should be instance of " + this.propertyType.getName() + " but is " + t);
        }
        if (t != null || this.defaultValue == null) {
            return;
        }
        StrictMode.crashIfStrict("Set " + this + " to " + this.defaultValue + " instead of null.");
    }

    public String toString() {
        return "MetricsProperty<" + this.name + ", " + this.propertyType.getSimpleName() + ">";
    }

    private class SetPropertyEventType implements MetricsEventType<SetPropertyEvent<T>> {
        private SetPropertyEventType() {
        }

        @Override // com.amazon.music.media.playback.metrics.MetricsEventType
        public Class<SetPropertyEvent<T>> getMetricsEventClass() {
            return SetPropertyEvent.class;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.music.media.playback.metrics.MetricsEventType
        public void onAddingTo(@NonNull SetPropertyEvent<T> setPropertyEvent, @NonNull MetricsInfo metricsInfo) {
            metricsInfo.set(MetricsProperty.this, ((SetPropertyEvent) setPropertyEvent).value);
        }

        public String toString() {
            return "set_" + MetricsProperty.this.name;
        }
    }

    public static class SetPropertyEvent<T> extends MetricsEvent {
        private T value;
    }
}
