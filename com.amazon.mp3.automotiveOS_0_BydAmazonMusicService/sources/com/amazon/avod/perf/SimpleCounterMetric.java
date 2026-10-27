package com.amazon.avod.perf;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Locale;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleCounterMetric implements CounterMetric {
    public static final ImmutableList<String> DEFAULT_TYPE_LIST = ImmutableList.of("Counter");
    private final String mCounterMetricName;
    private final long mIncrementValue;
    private final ImmutableList<String> mTypeList;

    public SimpleCounterMetric(@Nonnull String str) {
        this(str, DEFAULT_TYPE_LIST);
    }

    public SimpleCounterMetric(@Nonnull String str, @Nonnull ImmutableList<String> immutableList) {
        this(str, immutableList, 1L);
    }

    public SimpleCounterMetric(@Nonnull String str, long j) {
        this(str, DEFAULT_TYPE_LIST, j);
    }

    public SimpleCounterMetric(@Nonnull String str, @Nonnull ImmutableList<String> immutableList, long j) {
        this.mCounterMetricName = (String) Preconditions.checkNotNull(str, "counterName");
        this.mTypeList = (ImmutableList) Preconditions.checkNotNull(immutableList, "typeList");
        this.mIncrementValue = j;
    }

    @Override // com.amazon.avod.perf.Metric
    public String getName() {
        return this.mCounterMetricName;
    }

    @Override // com.amazon.avod.perf.Metric
    public ImmutableList<String> getTypeList() {
        return this.mTypeList;
    }

    @Override // com.amazon.avod.perf.CounterMetric
    @Nonnegative
    public long getIncrementValue() {
        return this.mIncrementValue;
    }

    public String toString() {
        return String.format(Locale.US, "Metric[name=%s, type=%s, val=%s]", this.mCounterMetricName, this.mTypeList, Long.valueOf(this.mIncrementValue));
    }
}
