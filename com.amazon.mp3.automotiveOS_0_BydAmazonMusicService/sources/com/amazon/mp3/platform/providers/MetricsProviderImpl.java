package com.amazon.mp3.platform.providers;

import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.mp3.platform.util.MetricsConverter;
import com.amazon.mp3.platform.util.MinervaMetricsConverter;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.service.metrics.minerva.MinervaMetricsFactory;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.metrics.event.LatencyWrapper;
import com.amazon.music.platform.metrics.event.MetricsWrapper;
import com.amazon.music.platform.metrics.event.MinervaWrapper;
import com.amazon.music.platform.metrics.event.MusicMetricsWrapper;
import com.amazon.music.platform.metrics.event.UIClickWrapper;
import com.amazon.music.platform.metrics.event.UIContentViewWrapper;
import com.amazon.music.platform.metrics.event.UIPageViewWrapper;
import com.amazon.music.platform.providers.MetricsProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MetricsProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0016¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/platform/providers/MetricsProviderImpl;", "Lcom/amazon/music/platform/providers/MetricsProvider;", "Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyTracker;", "latencyTracker", "Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyEventDescriptor;", "latencyEventDescriptor", "", "internalTrackLatency", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "metricsWrapper", "sendMetricsEvent", "Lcom/amazon/music/platform/metrics/event/LatencyWrapper;", "latencyWrapper", "trackLatency", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class MetricsProviderImpl implements MetricsProvider {
    @Override // com.amazon.music.platform.providers.MetricsProvider
    public void sendMetricsEvent(MetricsWrapper metricsWrapper) {
        MetricEvent metricEventConvertToMinervaEvent;
        Intrinsics.checkNotNullParameter(metricsWrapper, "metricsWrapper");
        if (metricsWrapper instanceof MusicMetricsWrapper ? true : metricsWrapper instanceof FlexWrapper) {
            MTSEvent mTSEventConvertToEvent = MetricsConverter.INSTANCE.convertToEvent(metricsWrapper);
            if (mTSEventConvertToEvent == null) {
                return;
            }
            MetricsLogger.sendEvent(mTSEventConvertToEvent);
            return;
        }
        if (metricsWrapper instanceof UIClickWrapper ? true : metricsWrapper instanceof UIContentViewWrapper ? true : metricsWrapper instanceof UIPageViewWrapper) {
            MTSEvent mTSEventConvertToEvent2 = MetricsConverter.INSTANCE.convertToEvent(metricsWrapper);
            if (mTSEventConvertToEvent2 == null) {
                return;
            }
            MetricsHolder.getManager().handleEvent(mTSEventConvertToEvent2);
            return;
        }
        if (!(metricsWrapper instanceof MinervaWrapper) || (metricEventConvertToMinervaEvent = MinervaMetricsConverter.INSTANCE.convertToMinervaEvent(metricsWrapper)) == null) {
            return;
        }
        MinervaMetricsFactory.record(metricEventConvertToMinervaEvent);
    }

    @Override // com.amazon.music.platform.providers.MetricsProvider
    public void trackLatency(LatencyWrapper latencyWrapper) {
        Intrinsics.checkNotNullParameter(latencyWrapper, "latencyWrapper");
        internalTrackLatency(LatencyTracker.INSTANCE, new LatencyEventDescriptor(LatencyTrackingLeg.valueOf(latencyWrapper.getLatencyTrackingLeg()), PageLoadLatencyCode.valueOf(latencyWrapper.getPageLoadLatencyCode())));
    }

    private final void internalTrackLatency(LatencyTracker latencyTracker, LatencyEventDescriptor latencyEventDescriptor) {
        latencyTracker.track(latencyEventDescriptor);
    }
}
