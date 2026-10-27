package com.amazon.mp3.platform.util;

import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.mp3.service.metrics.minerva.MinervaMetricsFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.metrics.event.MetricsWrapper;
import com.amazon.music.platform.metrics.event.MinervaWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MinervaMetricsConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/platform/util/MinervaMetricsConverter;", "", "()V", "convertToMinervaEvent", "Lcom/amazon/minerva/client/thirdparty/api/MetricEvent;", "metricsWrapper", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MinervaMetricsConverter {
    public static final MinervaMetricsConverter INSTANCE = new MinervaMetricsConverter();

    private MinervaMetricsConverter() {
    }

    public final MetricEvent convertToMinervaEvent(MetricsWrapper metricsWrapper) {
        Intrinsics.checkNotNullParameter(metricsWrapper, "metricsWrapper");
        if (metricsWrapper instanceof MinervaWrapper) {
            MinervaWrapper minervaWrapper = (MinervaWrapper) metricsWrapper;
            return MinervaMetricsFactory.addKeys(new MetricEvent(minervaWrapper.getMetricsGroupId(), minervaWrapper.getMetricSchema()), minervaWrapper.getParamList());
        }
        Log.warning(MinervaMetricsConverter.class.getSimpleName(), "Cannot convert the metricsWrapper type " + ((Object) metricsWrapper.getClass().getSimpleName()) + " to a Minerva MetricEvent");
        return null;
    }
}
