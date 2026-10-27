package com.amazon.avod.metrics.pmet;

import com.amazon.avod.metrics.pmet.internal.ValidatedCounterMetric;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface EnumeratedCounterMetricTemplate {
    public static final ImmutableList<String> COUNTER_ONLY = ImmutableList.of("Counter");

    ValidatedCounterMetric format(ImmutableList<MetricParameter> immutableList, ImmutableList<ImmutableList<MetricParameter>> immutableList2);

    @Nonnull
    default ValidatedCounterMetric format(@Nonnull ImmutableList<MetricParameter> immutableList, @Nonnull ImmutableList<ImmutableList<MetricParameter>> immutableList2, long j) {
        return format(immutableList, immutableList2);
    }
}
