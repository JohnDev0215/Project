package com.amazon.music.media.playback.metrics;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsInfo {
    private final List<MetricsEvent> events;
    private List<MetricsEvent> eventsListSnapshot;
    private final Object lock;
    private Map<MetricsProperty<?>, Object> properties;
    private boolean propertiesInUse;

    public MetricsInfo() {
        this(null);
    }

    public MetricsInfo(MetricsInfo metricsInfo) {
        this.lock = new Object();
        ArrayList arrayList = new ArrayList();
        this.events = arrayList;
        this.properties = new HashMap();
        if (metricsInfo != null) {
            synchronized (metricsInfo.lock) {
                arrayList.addAll(metricsInfo.events);
                this.properties.putAll(metricsInfo.properties);
            }
        }
    }

    public void add(@NonNull MetricsEvent metricsEvent) {
        if (StrictMode.validateNotNull(metricsEvent, "event cannot be null") == null) {
            return;
        }
        synchronized (this.lock) {
            try {
                onAddingTo(metricsEvent.getEventType(), metricsEvent);
            } catch (RuntimeException e) {
                StrictMode.crashIfStrict(e);
            }
            this.eventsListSnapshot = null;
            this.events.add(metricsEvent);
        }
    }

    private <T extends MetricsEvent> void onAddingTo(MetricsEventType<T> metricsEventType, MetricsEvent metricsEvent) {
        metricsEventType.onAddingTo(metricsEventType.getMetricsEventClass().cast(metricsEvent), this);
    }

    public List<MetricsEvent> getEventsSnapshot() {
        synchronized (this.lock) {
            if (this.events.isEmpty()) {
                return Collections.emptyList();
            }
            if (this.eventsListSnapshot == null) {
                this.eventsListSnapshot = Collections.unmodifiableList(new ArrayList(this.events));
            }
            return this.eventsListSnapshot;
        }
    }

    public <T> T set(@NonNull MetricsProperty<T> metricsProperty, T t) {
        T tCast;
        Validate.notNull(metricsProperty);
        try {
            metricsProperty.validate(t);
            synchronized (this.lock) {
                if (this.propertiesInUse) {
                    this.properties = new HashMap(this.properties);
                    this.propertiesInUse = false;
                }
                tCast = metricsProperty.getPropertyType().cast(this.properties.put(metricsProperty, t));
            }
            return tCast;
        } catch (RuntimeException e) {
            StrictMode.crashIfStrict(e);
            return (T) get(metricsProperty);
        }
    }

    public <T> T get(MetricsProperty<T> metricsProperty) {
        T tCast;
        if (metricsProperty == null) {
            return null;
        }
        synchronized (this.lock) {
            tCast = metricsProperty.getPropertyType().cast(this.properties.get(metricsProperty));
            if (tCast == null) {
                tCast = metricsProperty.getDefaultValue();
            }
        }
        return tCast;
    }

    public String toString() {
        String str;
        synchronized (this.lock) {
            str = "MetricsInfo{events=" + StringUtils.toString(this.events) + ", properties=" + StringUtils.toString(this.properties) + '}';
        }
        return str;
    }
}
