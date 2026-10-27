package com.amazon.mp3.video.stories.metrics;

import com.amazon.mp3.util.Log;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.metrics.event.MetricsWrapper;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: UIMetricsEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/video/stories/metrics/UIMetricsEvent;", "", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "metricsWrapper", "", "sendMetricsEvent", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class UIMetricsEvent {
    public static final UIMetricsEvent INSTANCE = new UIMetricsEvent();

    private UIMetricsEvent() {
    }

    public final void sendMetricsEvent(MetricsWrapper metricsWrapper) {
        Intrinsics.checkNotNullParameter(metricsWrapper, "metricsWrapper");
        Log.debug("VideoStoryMetrics", metricsWrapper.toString());
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof MetricsProvider)) {
            interfaceProvider = null;
        }
        MetricsProvider metricsProvider = (MetricsProvider) interfaceProvider;
        if (metricsProvider == null) {
            return;
        }
        metricsProvider.sendMetricsEvent(metricsWrapper);
    }
}
