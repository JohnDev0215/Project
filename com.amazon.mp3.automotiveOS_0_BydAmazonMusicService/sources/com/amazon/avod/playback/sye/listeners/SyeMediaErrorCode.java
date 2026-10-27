package com.amazon.avod.playback.sye.listeners;

import com.amazon.avod.media.error.MediaErrorCode;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyeMediaErrorCode implements MediaErrorCode {
    private final SyePlayerError mError;
    private final String mErrorMessage;

    @Override // com.amazon.avod.media.error.MediaErrorCode
    public int getNumber() {
        return 0;
    }

    public SyeMediaErrorCode(@Nonnull SyePlayerError syePlayerError, @Nonnull String str) {
        this.mError = (SyePlayerError) Preconditions.checkNotNull(syePlayerError, "error");
        this.mErrorMessage = (String) Preconditions.checkNotNull(str, "errorMessage");
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode
    @Nonnull
    public String getName() {
        return this.mError.name();
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return this.mError.name();
    }
}
