package com.amazon.avod.ads.parser.vast;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface VastNonLinearAds {
    List<? extends VastWrapperNonLinear> getNonLinearAds();

    List<VastTracking> getTrackingEvents();
}
