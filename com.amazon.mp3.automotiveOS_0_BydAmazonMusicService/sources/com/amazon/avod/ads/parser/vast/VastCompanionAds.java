package com.amazon.avod.ads.parser.vast;

import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastCompanionAds {
    private final List<VastCompanion> mCompanions;
    private final AdsRequired mRequired;

    public enum AdsRequired {
        all,
        any,
        none
    }

    public VastCompanionAds(@Nullable AdsRequired adsRequired, @Nullable List<VastCompanion> list) {
        this.mRequired = adsRequired;
        this.mCompanions = list;
    }

    @Nullable
    public AdsRequired getRequired() {
        return this.mRequired;
    }

    @Nullable
    public List<VastCompanion> getCompanions() {
        return this.mCompanions;
    }
}
