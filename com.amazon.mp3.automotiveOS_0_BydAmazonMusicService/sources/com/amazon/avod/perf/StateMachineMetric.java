package com.amazon.avod.perf;

import android.os.SystemClock;
import com.amazon.avod.perf.Conditional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class StateMachineMetric implements MarkerMetric, TimerMetric {
    private final Conditional mConditional;
    private final String mMetricName;
    private long mStartTimeMillis = -1;
    private long mDurationMillis = -1;

    public StateMachineMetric(@Nonnull String str, @Nonnull Conditional conditional) {
        Preconditions.checkNotNull(str, "name");
        Preconditions.checkState((str.contains(",") && str.contains(" ")) ? false : true, "metric names can not contain commas or spaces");
        this.mMetricName = str;
        this.mConditional = (Conditional) Preconditions.checkNotNull(conditional, "conditional");
    }

    /* JADX INFO: renamed from: com.amazon.avod.perf.StateMachineMetric$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$perf$Conditional$Result;

        static {
            int[] iArr = new int[Conditional.Result.values().length];
            $SwitchMap$com$amazon$avod$perf$Conditional$Result = iArr;
            try {
                iArr[Conditional.Result.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$perf$Conditional$Result[Conditional.Result.FINISHED.ordinal()] = 2;
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
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$perf$Conditional$Result[this.mConditional.evaluate().ordinal()];
        if (i == 1) {
            this.mStartTimeMillis = SystemClock.elapsedRealtime();
            return false;
        }
        if (i == 2) {
            this.mDurationMillis = SystemClock.elapsedRealtime() - this.mStartTimeMillis;
            return true;
        }
        if (i != 3) {
            return false;
        }
        this.mStartTimeMillis = -1L;
        return false;
    }

    @Override // com.amazon.avod.perf.MarkerMetric
    public void reset() {
        this.mStartTimeMillis = -1L;
        this.mDurationMillis = -1L;
        this.mConditional.reset();
    }

    @Override // com.amazon.avod.perf.Metric
    public final String getName() {
        return this.mMetricName;
    }

    @Override // com.amazon.avod.perf.Metric
    public ImmutableList<String> getTypeList() {
        return ImmutableList.of("Metric");
    }

    @Override // com.amazon.avod.perf.TimerMetric
    public final long getStartTimeMillis() {
        return this.mStartTimeMillis;
    }

    @Override // com.amazon.avod.perf.TimerMetric
    public final long getDurationMillis() {
        return this.mDurationMillis;
    }

    public String toString() {
        return String.format(Locale.US, "Metric[name=%s, type=%s, duration=%s]", this.mMetricName, "Metric", Long.valueOf(this.mDurationMillis));
    }
}
