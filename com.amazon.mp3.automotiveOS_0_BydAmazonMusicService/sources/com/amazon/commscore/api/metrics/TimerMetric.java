package com.amazon.commscore.api.metrics;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TimerMetric extends OperationalMetric {
    public abstract void finishTimer();

    public abstract long getElapsedTime();

    public TimerMetric(String str, String str2, String str3) {
        super(str, str2, str3, OperationalMetricType.TIMER);
    }
}
