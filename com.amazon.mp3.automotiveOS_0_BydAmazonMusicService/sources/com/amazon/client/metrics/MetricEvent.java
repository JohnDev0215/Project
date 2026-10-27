package com.amazon.client.metrics;

/* JADX INFO: loaded from: classes2.dex */
public interface MetricEvent {
    void addCounter(String str, double d);

    void addString(String str, String str2);

    void addTimer(String str, double d);

    void clear();

    void incrementCounter(String str, double d);

    void startTimer(String str);

    void stopTimer(String str);
}
