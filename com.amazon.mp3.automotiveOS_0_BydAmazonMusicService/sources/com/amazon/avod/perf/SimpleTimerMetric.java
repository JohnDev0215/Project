package com.amazon.avod.perf;

import com.google.common.collect.ImmutableList;
import java.util.Locale;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleTimerMetric implements TimerMetric {
    public static final ImmutableList<String> DEFAULT_TYPE_LIST = ImmutableList.of("Metric");
    private final long mDurationMillis;
    private final String mMetricName;
    private final long mStartTimeMillis;
    private final ImmutableList<String> mTypeList;

    public SimpleTimerMetric(@Nonnull String str, long j, @Nonnegative long j2) {
        this(str, DEFAULT_TYPE_LIST, j, j2);
    }

    public SimpleTimerMetric(@Nonnull String str, @Nonnull ImmutableList<String> immutableList, long j, @Nonnegative long j2) {
        this.mMetricName = str;
        this.mTypeList = immutableList;
        this.mStartTimeMillis = j;
        this.mDurationMillis = j2;
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
    public final long getStartTimeMillis() {
        return this.mStartTimeMillis;
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
