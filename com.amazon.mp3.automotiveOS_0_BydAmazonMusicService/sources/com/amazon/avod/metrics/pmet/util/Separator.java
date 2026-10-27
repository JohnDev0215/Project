package com.amazon.avod.metrics.pmet.util;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum Separator implements MetricParameter {
    DASH("-"),
    COLON(":"),
    NO_SEPARATOR("");

    private final String mValue;

    Separator(@Nonnull String str) {
        this.mValue = (String) Preconditions.checkNotNull(str, "value");
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return this.mValue;
    }
}
