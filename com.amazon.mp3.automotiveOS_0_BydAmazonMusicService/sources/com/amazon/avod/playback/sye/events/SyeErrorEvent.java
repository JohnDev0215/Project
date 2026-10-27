package com.amazon.avod.playback.sye.events;

import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyeErrorEvent {
    private final String mErrorMessage;
    private final boolean mReportFatal;
    private final SyePlayerError mSyePlayerError;

    public SyeErrorEvent(@Nonnull SyePlayerError syePlayerError, @Nonnull String str, boolean z) {
        this.mSyePlayerError = (SyePlayerError) Preconditions.checkNotNull(syePlayerError, "syePlayerError");
        this.mErrorMessage = (String) Preconditions.checkNotNull(str, "errorMessage");
        this.mReportFatal = z;
    }

    @Nonnull
    public SyePlayerError getSyePlayerError() {
        return this.mSyePlayerError;
    }

    @Nonnull
    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public boolean isFatal() {
        return this.mReportFatal;
    }
}
