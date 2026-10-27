package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastDocument {
    private final List<VastAd> mAds;
    private final String mVersion;

    public VastDocument(@Nonnull String str, @Nullable List<VastAd> list) {
        this.mVersion = (String) Preconditions.checkNotNull(str, "version");
        this.mAds = list;
    }

    @Nullable
    public List<VastAd> getAds() {
        return this.mAds;
    }
}
