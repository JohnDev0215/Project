package com.amazon.avod.ads.api;

import com.amazon.avod.util.Preconditions2;
import javax.annotation.Nonnegative;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class AdSkipInfo {
    private final long mMinAdLength;
    private final long mOffsetDuration;
    private final boolean mShowTimer;

    public AdSkipInfo(boolean z, @Nonnegative long j, @Nonnegative long j2) {
        this.mShowTimer = z;
        this.mOffsetDuration = Preconditions2.checkNonNegative(j, "offsetDuration");
        this.mMinAdLength = Preconditions2.checkNonNegative(j2, "minAdLength");
    }

    public boolean isShowTimer() {
        return this.mShowTimer;
    }

    @Nonnegative
    public long getOffsetDuration() {
        return this.mOffsetDuration;
    }

    @Nonnegative
    public long getMinAdLength() {
        return this.mMinAdLength;
    }
}
