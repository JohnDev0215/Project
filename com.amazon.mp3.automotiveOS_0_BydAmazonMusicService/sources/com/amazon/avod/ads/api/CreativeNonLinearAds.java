package com.amazon.avod.ads.api;

import com.amazon.avod.ads.api.internal.AdCreativeHolderNode;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastInlineNonLinearAds;
import com.amazon.avod.ads.parser.vast.VastNonLinearAds;
import com.amazon.avod.ads.parser.vast.VastWrapperNonLinear;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CreativeNonLinearAds extends Creative {
    private final boolean mDisplayable;
    private final List<CreativeNonLinear> mNonLinearAds;

    public CreativeNonLinearAds(@Nonnull AdCreativeHolderNode adCreativeHolderNode, @Nonnull AdHttpClient adHttpClient, @Nonnull VastNonLinearAds vastNonLinearAds, @Nullable String str) {
        super((AdCreativeHolderNode) Preconditions.checkNotNull(adCreativeHolderNode), (AdHttpClient) Preconditions.checkNotNull(adHttpClient), vastNonLinearAds.getTrackingEvents(), str);
        this.mNonLinearAds = Lists.newLinkedList();
        this.mDisplayable = vastNonLinearAds instanceof VastInlineNonLinearAds;
        Iterator<? extends VastWrapperNonLinear> it = vastNonLinearAds.getNonLinearAds().iterator();
        while (it.hasNext()) {
            this.mNonLinearAds.add(new CreativeNonLinear(getHttpClient(), this, it.next()));
        }
    }

    @Override // com.amazon.avod.ads.api.Creative
    public CreativeType getCreativeType() {
        return CreativeType.nonlinear;
    }

    @Override // com.amazon.avod.ads.api.Creative
    public boolean isDisplayable() {
        return this.mDisplayable;
    }
}
