package com.amazon.avod.ads.api;

import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastInlineNonLinear;
import com.amazon.avod.ads.parser.vast.VastWrapperNonLinear;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class CreativeNonLinear {
    private final AdHttpClient mHttpClient;
    private final VastInlineNonLinear mInlineNonLinear;
    private final CreativeNonLinearAds mParent;
    private final Resource mResource;
    private final VastWrapperNonLinear mWrapperNonLinear;

    public CreativeNonLinear(AdHttpClient adHttpClient, CreativeNonLinearAds creativeNonLinearAds, VastWrapperNonLinear vastWrapperNonLinear) {
        this.mParent = (CreativeNonLinearAds) Preconditions.checkNotNull(creativeNonLinearAds);
        this.mHttpClient = (AdHttpClient) Preconditions.checkNotNull(adHttpClient);
        this.mWrapperNonLinear = (VastWrapperNonLinear) Preconditions.checkNotNull(vastWrapperNonLinear);
        if (vastWrapperNonLinear instanceof VastInlineNonLinear) {
            VastInlineNonLinear vastInlineNonLinear = (VastInlineNonLinear) vastWrapperNonLinear;
            this.mInlineNonLinear = vastInlineNonLinear;
            this.mResource = new Resource(vastInlineNonLinear.getResource());
        } else {
            this.mInlineNonLinear = null;
            this.mResource = null;
        }
    }
}
