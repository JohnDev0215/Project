package com.amazon.avod.playback;

import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface LifecycleProfilerReporter {
    void reportMetric(@Nonnull Map<LifecycleProfilerMetrics, Long> map);
}
