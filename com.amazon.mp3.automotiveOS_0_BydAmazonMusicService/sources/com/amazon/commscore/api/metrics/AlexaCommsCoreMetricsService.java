package com.amazon.commscore.api.metrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface AlexaCommsCoreMetricsService {
    @NonNull
    CounterMetric createCounter(@NonNull String str, @Nullable String str2, @Nullable Map<String, Object> map);

    @NonNull
    TimerMetric createTimer(@NonNull String str, @Nullable String str2, long j, boolean z, @Nullable Map<String, Object> map);

    void recordCounter(@NonNull CounterMetric counterMetric);

    void recordOccurrence(@NonNull String str, @Nullable String str2, boolean z, @Nullable Map<String, Object> map);

    void recordTimer(@NonNull TimerMetric timerMetric);
}
