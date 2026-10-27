package com.amazon.avod.metrics.pmet;

import com.amazon.avod.metrics.pmet.internal.ValidatedCounterMetric;
import com.amazon.avod.perf.Profiler;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ValidatedCounterMetricBuilder {
    private long mIncrementValue;
    private final ImmutableList.Builder<MetricParameter> mNameParameters;
    private final EnumeratedCounterMetricTemplate mTemplate;
    private final ImmutableList.Builder<ImmutableList<MetricParameter>> mValueParameters;

    public ValidatedCounterMetricBuilder(@Nonnull EnumeratedCounterMetricTemplate enumeratedCounterMetricTemplate) {
        this(enumeratedCounterMetricTemplate, true);
    }

    public ValidatedCounterMetricBuilder(@Nonnull EnumeratedCounterMetricTemplate enumeratedCounterMetricTemplate, boolean z) {
        this.mIncrementValue = 1L;
        this.mTemplate = (EnumeratedCounterMetricTemplate) Preconditions.checkNotNull(enumeratedCounterMetricTemplate, "template");
        this.mNameParameters = ImmutableList.builder();
        this.mValueParameters = ImmutableList.builder();
        if (z) {
            addListValueParameter(ImmutableList.of());
        }
    }

    public ValidatedCounterMetricBuilder addNameParameter(@Nonnull MetricParameter metricParameter) {
        this.mNameParameters.add((MetricParameter) Preconditions.checkNotNull(metricParameter, "nameParameter"));
        return this;
    }

    public ValidatedCounterMetricBuilder addValueParameter(@Nonnull MetricParameter metricParameter) {
        return addListValueParameter(ImmutableList.of(metricParameter));
    }

    public ValidatedCounterMetricBuilder addValueParameters(@Nonnull Collection<MetricParameter> collection) {
        Iterator it = ((Collection) Preconditions.checkNotNull(collection)).iterator();
        while (it.hasNext()) {
            addValueParameter((MetricParameter) it.next());
        }
        return this;
    }

    public ValidatedCounterMetricBuilder addListValueParameter(@Nonnull ImmutableList<MetricParameter> immutableList) {
        this.mValueParameters.add((ImmutableList<MetricParameter>) Preconditions.checkNotNull(immutableList));
        return this;
    }

    public void report() {
        Profiler.reportCounterFromValidatedCounterMetric(toCounter());
    }

    ValidatedCounterMetric toCounter() {
        return new ValidatedCounterMetric(this.mTemplate.format(this.mNameParameters.build(), this.mValueParameters.build()), this.mIncrementValue);
    }
}
