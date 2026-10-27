package com.amazon.music.media.playback.metrics;

import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsEvent implements Comparable<MetricsEvent> {
    private final long eventTimeMillis;
    private final MetricsEventType<?> eventType;

    public MetricsEvent(@NonNull MetricsEventType<?> metricsEventType, long j) {
        Class<T> metricsEventClass = metricsEventType.getMetricsEventClass();
        if (metricsEventClass == 0) {
            throw new NullPointerException("MetricsEventType " + metricsEventType + " returned null from getMetricsEventClass()");
        }
        if (!metricsEventClass.isInstance(this)) {
            throw new IllegalArgumentException("MetricsEventType " + metricsEventType + " expected event of type " + metricsEventType.getMetricsEventClass().getName() + ", not " + getClass().getName());
        }
        if (j <= 0 && j != Long.MIN_VALUE) {
            this.eventTimeMillis = Long.MIN_VALUE;
        } else {
            this.eventTimeMillis = j;
        }
        this.eventType = (MetricsEventType) Validate.notNull(metricsEventType);
    }

    @NonNull
    public MetricsEventType<?> getEventType() {
        return this.eventType;
    }

    public <T extends MetricsEvent> T castFor(@NonNull MetricsEventType<T> metricsEventType) {
        return metricsEventType.getMetricsEventClass().cast(this);
    }

    public long getEventTimeMillis() {
        return this.eventTimeMillis;
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull MetricsEvent metricsEvent) {
        long j = this.eventTimeMillis;
        long j2 = metricsEvent.eventTimeMillis;
        if (j < j2) {
            return -1;
        }
        return j > j2 ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MetricsEvent metricsEvent = (MetricsEvent) obj;
        return this.eventTimeMillis == metricsEvent.eventTimeMillis && this.eventType == metricsEvent.eventType;
    }

    public int hashCode() {
        int iHashCode = this.eventType.hashCode() * 31;
        long j = this.eventTimeMillis;
        return iHashCode + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "Event{eventType='" + this.eventType + "', eventTimeMillis=" + this.eventTimeMillis + '}';
    }
}
