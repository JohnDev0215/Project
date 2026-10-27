package com.amazon.avod.perf;

import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface Metric {
    @Nonnull
    String getName();

    @Nonnull
    ImmutableList<String> getTypeList();
}
