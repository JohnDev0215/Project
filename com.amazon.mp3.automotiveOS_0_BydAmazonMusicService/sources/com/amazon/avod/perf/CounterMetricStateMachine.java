package com.amazon.avod.perf;

import com.amazon.avod.perf.Conditional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class CounterMetricStateMachine implements MarkerMetric, CounterMetric {
    private final Conditional mConditional;
    private long mCount;
    private final long mIncrementValue;
    private final String mMetricName;

    public CounterMetricStateMachine(@Nonnull String str, @Nonnull Conditional conditional) {
        this(str, conditional, 1L);
    }

    public CounterMetricStateMachine(@Nonnull String str, @Nonnull Conditional conditional, long j) {
        this.mCount = 0L;
        Preconditions.checkNotNull(str, "name");
        Preconditions.checkState((str.contains(",") && str.contains(" ")) ? false : true, "metric names can not contain commas or spaces");
        this.mMetricName = str;
        this.mConditional = (Conditional) Preconditions.checkNotNull(conditional, "conditional");
        this.mIncrementValue = j;
    }

    @Override // com.amazon.avod.perf.CounterMetric
    public long getIncrementValue() {
        return this.mIncrementValue;
    }

    /* JADX INFO: renamed from: com.amazon.avod.perf.CounterMetricStateMachine$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$perf$Conditional$Result;

        static {
            int[] iArr = new int[Conditional.Result.values().length];
            $SwitchMap$com$amazon$avod$perf$Conditional$Result = iArr;
            try {
                iArr[Conditional.Result.FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$perf$Conditional$Result[Conditional.Result.START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$perf$Conditional$Result[Conditional.Result.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$perf$Conditional$Result[Conditional.Result.PENDING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.amazon.avod.perf.MarkerMetric
    public boolean onMarker() {
        if (AnonymousClass1.$SwitchMap$com$amazon$avod$perf$Conditional$Result[this.mConditional.evaluate().ordinal()] != 1) {
            return false;
        }
        this.mCount += this.mIncrementValue;
        return true;
    }

    @Override // com.amazon.avod.perf.MarkerMetric
    public void reset() {
        this.mCount = 0L;
        this.mConditional.reset();
    }

    @Override // com.amazon.avod.perf.Metric
    @Nonnull
    public String getName() {
        return this.mMetricName;
    }

    @Override // com.amazon.avod.perf.Metric
    @Nonnull
    public ImmutableList<String> getTypeList() {
        return ImmutableList.of("Counter");
    }

    public String toString() {
        return String.format(Locale.US, "Metric[name=%s, type=%s, count=%s]", this.mMetricName, "Counter", Long.valueOf(this.mCount));
    }
}
