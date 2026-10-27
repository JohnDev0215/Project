package com.amazon.avod.perf;

import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Locale;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DurationMetric implements TimerMetric {
    public static final ImmutableList<String> DEFAULT_TYPE_LIST = ImmutableList.of("Metric");
    private final long mDurationMillis;
    private final String mMetricName;
    private final ImmutableList<String> mTypeList;

    @Override // com.amazon.avod.perf.TimerMetric
    public final long getStartTimeMillis() {
        return -1L;
    }

    public DurationMetric(@Nonnull String str, @Nonnegative long j) {
        this(str, DEFAULT_TYPE_LIST, j);
    }

    public DurationMetric(@Nonnull String str, @Nonnull ImmutableList<String> immutableList, @Nonnegative long j) {
        this.mMetricName = (String) Preconditions.checkNotNull(str, "name");
        this.mTypeList = (ImmutableList) Preconditions.checkNotNull(immutableList, "typeList");
        this.mDurationMillis = Preconditions2.checkNonNegativeWeakly(j, j, "durationMillis");
    }

    @Override // com.amazon.avod.perf.Metric
    public final String getName() {
        return this.mMetricName;
    }

    @Override // com.amazon.avod.perf.Metric
    public ImmutableList<String> getTypeList() {
        return this.mTypeList;
    }

    @Override // com.amazon.avod.perf.TimerMetric
    @Nonnegative
    public final long getDurationMillis() {
        return this.mDurationMillis;
    }

    public String toString() {
        return String.format(Locale.US, "Metric[name=%s, type=%s, duration=%s]", this.mMetricName, this.mTypeList, Long.valueOf(this.mDurationMillis));
    }
}
