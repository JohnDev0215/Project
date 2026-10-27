package com.amazon.music.platform.providers;

import com.amazon.music.platform.metrics.event.LatencyWrapper;
import com.amazon.music.platform.metrics.event.MetricsWrapper;
import kotlin.Metadata;

/* JADX INFO: compiled from: MetricsProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/providers/MetricsProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "sendMetricsEvent", "", "metricsWrapper", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "trackLatency", "latencyWrapper", "Lcom/amazon/music/platform/metrics/event/LatencyWrapper;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface MetricsProvider extends InterfaceProvider {
    void sendMetricsEvent(MetricsWrapper metricsWrapper);

    void trackLatency(LatencyWrapper latencyWrapper);
}
