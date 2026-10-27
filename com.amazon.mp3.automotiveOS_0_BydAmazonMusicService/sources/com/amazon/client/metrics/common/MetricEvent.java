package com.amazon.client.metrics.common;

/* JADX INFO: loaded from: classes2.dex */
public interface MetricEvent {
    void addCounter(String str, double d);

    void addString(String str, String str2);

    void incrementCounter(String str, double d);

    void startTimer(String str);

    void stopTimer(String str);
}
