package com.amazon.avod.pmet;

import com.amazon.avod.playback.LifecycleProfilerReporter;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PmetLifecycleProfilerReporter implements LifecycleProfilerReporter {
    private final PlaybackPmetMetricReporter mReporter;

    public PmetLifecycleProfilerReporter() {
        this(PlaybackPmetMetricReporter.getInstance());
    }

    PmetLifecycleProfilerReporter(@Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        this.mReporter = playbackPmetMetricReporter;
    }

    @Override // com.amazon.avod.playback.LifecycleProfilerReporter
    public void reportMetric(@Nonnull Map<LifecycleProfilerMetrics, Long> map) {
        for (Map.Entry<LifecycleProfilerMetrics, Long> entry : map.entrySet()) {
            Long value = entry.getValue();
            if (value != null) {
                this.mReporter.reportTtffTracesTimerMetric(entry.getKey(), value.longValue());
            }
        }
    }
}
