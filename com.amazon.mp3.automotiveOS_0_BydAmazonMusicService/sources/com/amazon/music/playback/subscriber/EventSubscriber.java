package com.amazon.music.playback.subscriber;

import com.amazon.music.metrics.MetricsManager;

/* JADX INFO: loaded from: classes4.dex */
public abstract class EventSubscriber {
    protected final MetricsManager metricsManager;

    public EventSubscriber(MetricsManager metricsManager) {
        this.metricsManager = metricsManager;
    }
}
