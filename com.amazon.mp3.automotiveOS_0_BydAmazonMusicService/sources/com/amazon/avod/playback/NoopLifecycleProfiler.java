package com.amazon.avod.playback;

import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NoopLifecycleProfiler implements LifecycleProfiler {
    @Override // com.amazon.avod.playback.LifecycleProfiler
    public void end(@Nonnull LifecycleProfilerMetrics lifecycleProfilerMetrics) {
    }

    @Override // com.amazon.avod.playback.LifecycleProfiler
    public void reportMetric(@Nonnull List<LifecycleProfilerReporter> list) {
    }

    @Override // com.amazon.avod.playback.LifecycleProfiler
    public void start(@Nonnull LifecycleProfilerMetrics lifecycleProfilerMetrics) {
    }

    protected NoopLifecycleProfiler() {
    }
}
