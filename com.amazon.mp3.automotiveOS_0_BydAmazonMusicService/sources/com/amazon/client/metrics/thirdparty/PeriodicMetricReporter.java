package com.amazon.client.metrics.thirdparty;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public interface PeriodicMetricReporter {
    MetricEvent getMetricEvent();

    void shutdown();

    void startRecordingPeriodically(long j, TimeUnit timeUnit);
}
