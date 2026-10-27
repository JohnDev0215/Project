package com.amazon.music.explore.metrics;

import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.providers.MetricsProvider;

/* JADX INFO: loaded from: classes4.dex */
public class SpotlightImageLatencyFlexEventEmitter {
    private static String FLEX_NAME = "spotlightImageLoadingLatency";
    private final MetricsProvider metricsProvider;
    private long startTimeNs = -1;
    private boolean didEmitEvent = false;

    public SpotlightImageLatencyFlexEventEmitter(MetricsProvider metricsProvider) {
        this.metricsProvider = metricsProvider;
    }

    public void startTiming() {
        if (this.startTimeNs == -1) {
            this.startTimeNs = System.nanoTime();
        }
    }

    public void emitFlexEvent() {
        if (this.startTimeNs == -1 || this.didEmitEvent) {
            return;
        }
        this.metricsProvider.sendMetricsEvent(new FlexWrapper.Builder().withName(FLEX_NAME).withNum1(Float.valueOf((System.nanoTime() - this.startTimeNs) / 1000000)).build());
        this.didEmitEvent = true;
    }
}
