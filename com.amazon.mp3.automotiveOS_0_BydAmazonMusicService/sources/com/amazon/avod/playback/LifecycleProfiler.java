package com.amazon.avod.playback;

import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface LifecycleProfiler {
    void end(@Nonnull LifecycleProfilerMetrics lifecycleProfilerMetrics);

    void reportMetric(@Nonnull List<LifecycleProfilerReporter> list);

    void start(@Nonnull LifecycleProfilerMetrics lifecycleProfilerMetrics);
}
