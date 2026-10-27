package com.amazon.coral.retry;

/* JADX INFO: loaded from: classes2.dex */
public interface MetricsRecorder extends AutoCloseable {
    void addMetric(String str, double d);
}
