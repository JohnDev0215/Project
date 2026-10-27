package com.amazon.music.metrics.technical;

import com.amazon.client.metrics.common.MetricEvent;

/* JADX INFO: loaded from: classes4.dex */
public class TechnicalMetricsCollection {
    private MetricEvent metricEvent;

    TechnicalMetricsCollection(MetricEvent metricEvent) {
        if (metricEvent == null) {
            throw new NullPointerException("MetricEvent cannot be null");
        }
        this.metricEvent = metricEvent;
    }

    public void startTimer(String str) {
        this.metricEvent.startTimer(str);
    }

    public void stopTimer(String str) {
        this.metricEvent.stopTimer(str);
    }

    public void incrementCounter(String str, double d) {
        this.metricEvent.incrementCounter(str, d);
    }
}
