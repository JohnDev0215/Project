package com.amazon.avod.media.error;

import com.amazon.avod.metrics.pmet.MetricParameter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaErrorCode extends MetricParameter {
    @Nonnull
    String getName();

    int getNumber();

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    String toReportableString();
}
