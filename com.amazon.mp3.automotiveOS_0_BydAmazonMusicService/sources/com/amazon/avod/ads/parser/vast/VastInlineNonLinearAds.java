package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastInlineNonLinearAds implements VastNonLinearAds {
    private final List<VastInlineNonLinear> mNonLinear;
    private final List<VastTracking> mTrackingEvents;

    public VastInlineNonLinearAds(@Nullable List<VastTracking> list, @Nonnull List<VastInlineNonLinear> list2) {
        this.mTrackingEvents = list;
        this.mNonLinear = (List) Preconditions.checkNotNull(list2, "nonLinear");
    }

    @Override // com.amazon.avod.ads.parser.vast.VastNonLinearAds
    @Nullable
    public List<VastTracking> getTrackingEvents() {
        return this.mTrackingEvents;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastNonLinearAds
    @Nonnull
    public List<VastInlineNonLinear> getNonLinearAds() {
        return this.mNonLinear;
    }
}
