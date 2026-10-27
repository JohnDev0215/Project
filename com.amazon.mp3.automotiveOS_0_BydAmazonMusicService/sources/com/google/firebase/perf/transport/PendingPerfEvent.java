package com.google.firebase.perf.transport;

import androidx.annotation.NonNull;
import com.google.firebase.perf.v1.ApplicationProcessState;
import com.google.firebase.perf.v1.PerfMetric;

/* JADX INFO: loaded from: classes6.dex */
final class PendingPerfEvent {
    protected final ApplicationProcessState appState;
    protected final PerfMetric.Builder perfMetricBuilder;

    public PendingPerfEvent(@NonNull PerfMetric.Builder builder, @NonNull ApplicationProcessState applicationProcessState) {
        this.perfMetricBuilder = builder;
        this.appState = applicationProcessState;
    }
}
