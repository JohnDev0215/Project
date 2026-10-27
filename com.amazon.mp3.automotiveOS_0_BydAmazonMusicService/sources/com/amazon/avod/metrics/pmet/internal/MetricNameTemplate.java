package com.amazon.avod.metrics.pmet.internal;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MetricNameTemplate {
    private final String mBaseName;
    private final List<Class<? extends MetricParameter>> mRequiredParameterClasses;

    public MetricNameTemplate(@Nonnull String str) {
        this(str, ImmutableList.of());
    }

    public MetricNameTemplate(@Nonnull String str, @Nonnull List<Class<? extends MetricParameter>> list) {
        this.mBaseName = (String) Preconditions.checkNotNull(str, "baseName");
        this.mRequiredParameterClasses = (List) Preconditions.checkNotNull(list, "requiredParameterClasses");
    }

    public String format(@Nonnull ImmutableList<MetricParameter> immutableList) {
        Preconditions.checkNotNull(immutableList, "parameters");
        return PmetMetricUtils.appendParametersIfValid(this.mRequiredParameterClasses, immutableList, this.mBaseName);
    }
}
