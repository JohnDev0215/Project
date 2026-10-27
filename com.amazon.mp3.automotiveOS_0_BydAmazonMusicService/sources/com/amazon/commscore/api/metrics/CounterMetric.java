package com.amazon.commscore.api.metrics;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CounterMetric extends OperationalMetric {
    public abstract long getCount();

    public abstract void incrementCounterBy(long j);

    public CounterMetric(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4, OperationalMetricType.COUNTER);
    }
}
