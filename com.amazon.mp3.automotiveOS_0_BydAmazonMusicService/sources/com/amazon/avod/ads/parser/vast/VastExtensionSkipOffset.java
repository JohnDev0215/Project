package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VastExtensionSkipOffset {
    private final VastTimeSpan mOffsetVal;
    private final boolean mShowTimer;

    public VastExtensionSkipOffset(@Nonnull VastTimeSpan vastTimeSpan, boolean z) {
        this.mOffsetVal = (VastTimeSpan) Preconditions.checkNotNull(vastTimeSpan, "skipOffset");
        this.mShowTimer = z;
    }

    @Nonnull
    public VastTimeSpan getOffsetVal() {
        return this.mOffsetVal;
    }

    public boolean getShowTimer() {
        return this.mShowTimer;
    }
}
