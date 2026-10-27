package com.amazon.music.media.playback.metrics;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.metrics.MetricsEvent;

/* JADX INFO: loaded from: classes4.dex */
public interface MetricsEventType<T extends MetricsEvent> {
    Class<T> getMetricsEventClass();

    void onAddingTo(@NonNull T t, @NonNull MetricsInfo metricsInfo);
}
