package com.amazon.avod.perf;

import javax.annotation.Nonnegative;

/* JADX INFO: loaded from: classes2.dex */
public interface CounterMetric extends Metric {
    @Nonnegative
    long getIncrementValue();
}
