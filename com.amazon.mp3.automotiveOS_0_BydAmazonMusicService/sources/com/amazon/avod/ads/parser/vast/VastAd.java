package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastAd {
    private final VastAdData mAdData;
    private final String mId;
    private final Integer mSequence;

    public VastAd(@Nullable String str, @Nullable Integer num, @Nonnull VastAdData vastAdData) {
        this.mId = str;
        this.mSequence = num;
        this.mAdData = (VastAdData) Preconditions.checkNotNull(vastAdData, "adData");
    }

    @Nullable
    public String getId() {
        return this.mId;
    }

    @Nullable
    public Integer getSequence() {
        return this.mSequence;
    }

    @Nonnull
    public VastAdData getAdData() {
        return this.mAdData;
    }
}
