package com.amazon.avod.media.framework.error;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.metrics.pmet.MetricParameter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaInternalErrorCode extends MetricParameter {
    @Nonnull
    MediaErrorCode getExternalCode();

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    /* synthetic */ String toReportableString();
}
