package com.amazon.avod.metrics.pmet.util;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ReportableString implements MetricParameter {
    private final String mValue;

    public ReportableString(@Nonnull String str, @Nonnull Set<String> set, @Nonnull String str2) {
        this.mValue = ((Set) Preconditions.checkNotNull(set, "whitelist")).contains(str) ? str : str2;
        Preconditions.checkState(!Strings.isNullOrEmpty(r2), "ReportableString cannot report a null/empty value");
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return this.mValue;
    }
}
