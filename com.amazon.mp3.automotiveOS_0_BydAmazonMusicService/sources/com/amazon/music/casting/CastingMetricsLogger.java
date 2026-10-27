package com.amazon.music.casting;

import com.amazon.music.metrics.mts.event.definition.LatencyInfoEvent;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes4.dex */
public class CastingMetricsLogger {
    public static void sendLatencyMetric(long j) {
        EventBus.getDefault().post(LatencyInfoEvent.builder(j, "casting", "DeviceControl", "OptimisticUpdate").build());
    }
}
