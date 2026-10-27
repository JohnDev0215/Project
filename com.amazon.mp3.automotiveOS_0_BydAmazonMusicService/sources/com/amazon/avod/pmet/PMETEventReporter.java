package com.amazon.avod.pmet;

import com.amazon.avod.perf.CounterMetric;
import com.amazon.avod.perf.Profiler;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PMETEventReporter {
    public void reportCounterMetric(@Nonnull CounterMetric counterMetric) {
        Profiler.reportCounterMetric((CounterMetric) Preconditions.checkNotNull(counterMetric, "metric"));
    }
}
