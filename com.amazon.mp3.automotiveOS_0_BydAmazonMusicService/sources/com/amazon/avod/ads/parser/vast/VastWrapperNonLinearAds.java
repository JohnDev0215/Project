package com.amazon.avod.ads.parser.vast;

import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastWrapperNonLinearAds implements VastNonLinearAds {
    private final List<VastWrapperNonLinear> mNonLinear;
    private final List<VastTracking> mTrackingEvents;

    public VastWrapperNonLinearAds(@Nullable List<VastTracking> list, @Nullable List<VastWrapperNonLinear> list2) {
        this.mTrackingEvents = list;
        this.mNonLinear = list2;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastNonLinearAds
    @Nullable
    public List<VastTracking> getTrackingEvents() {
        return this.mTrackingEvents;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastNonLinearAds
    @Nullable
    public List<VastWrapperNonLinear> getNonLinearAds() {
        return this.mNonLinear;
    }
}
