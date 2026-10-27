package com.amazon.avod.metrics.pmet.internal;

import com.amazon.avod.metrics.pmet.MetricSource;
import com.amazon.avod.perf.MetricPriority;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ValidatedCounterMetric extends SimpleCounterMetric {
    private final MetricPriority mPriority;
    private final MetricSource mSource;

    public ValidatedCounterMetric(@Nonnull String str, @Nonnull ImmutableList<String> immutableList, @Nonnull MetricSource metricSource, @Nonnull MetricPriority metricPriority) {
        this(str, immutableList, 1L, metricSource, metricPriority);
    }

    public ValidatedCounterMetric(@Nonnull String str, @Nonnull ImmutableList<String> immutableList, @Nonnull MetricSource metricSource) {
        this(str, immutableList, 1L, metricSource, MetricPriority.NORMAL);
    }

    public ValidatedCounterMetric(@Nonnull ValidatedCounterMetric validatedCounterMetric, long j) {
        this(validatedCounterMetric.getName(), validatedCounterMetric.getTypeList(), j, validatedCounterMetric.getSource(), validatedCounterMetric.getPriority());
    }

    public ValidatedCounterMetric(@Nonnull String str, @Nonnull ImmutableList<String> immutableList, long j, @Nonnull MetricSource metricSource, @Nonnull MetricPriority metricPriority) {
        super((String) Preconditions.checkNotNull(str, "name"), (ImmutableList) Preconditions.checkNotNull(immutableList, "values"), j);
        this.mSource = (MetricSource) Preconditions.checkNotNull(metricSource, PageUriUtils.SOURCE_QUERY_PARAM);
        this.mPriority = (MetricPriority) Preconditions.checkNotNull(metricPriority, "priority");
    }

    @Nonnull
    public MetricSource getSource() {
        return this.mSource;
    }

    @Nonnull
    public MetricPriority getPriority() {
        return this.mPriority;
    }
}
