package com.amazon.avod.qos.model.internal;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AudioAdaptationSetSwitchContext {
    private final String mAdaptationSetName;
    private final int mBitrate;
    private final String mCause;
    private final String mStreamSelectionParams;

    public AudioAdaptationSetSwitchContext(@Nonnull String str, int i, @Nonnull String str2, @Nonnull String str3) {
        this.mAdaptationSetName = (String) Preconditions.checkNotNull(str, "adaptationSetName");
        this.mBitrate = i;
        this.mCause = (String) Preconditions.checkNotNull(str2, "cause");
        this.mStreamSelectionParams = (String) Preconditions.checkNotNull(str3, "streamSelectionParams");
    }

    @Nonnull
    public String getAdaptationSetName() {
        return this.mAdaptationSetName;
    }

    public int getBitrate() {
        return this.mBitrate;
    }

    @Nonnull
    public String getCause() {
        return this.mCause;
    }

    @Nonnull
    public String getStreamSelectionParams() {
        return this.mStreamSelectionParams;
    }
}
