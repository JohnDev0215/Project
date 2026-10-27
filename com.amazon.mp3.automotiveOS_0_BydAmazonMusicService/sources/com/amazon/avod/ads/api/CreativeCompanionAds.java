package com.amazon.avod.ads.api;

import com.amazon.avod.ads.api.internal.AdCreativeHolderNode;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastCompanion;
import com.amazon.avod.ads.parser.vast.VastCompanionAds;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CreativeCompanionAds {
    private final VastCompanionAds.AdsRequired mAdsRequired;
    private final List<CreativeCompanion> mCompanions = Lists.newLinkedList();
    private final AdHttpClient mHttpClient;
    private final boolean mInline;
    private final AdCreativeHolderNode mParent;

    public CreativeCompanionAds(AdCreativeHolderNode adCreativeHolderNode, AdHttpClient adHttpClient, VastCompanionAds vastCompanionAds, boolean z, @Nullable String str) {
        this.mParent = (AdCreativeHolderNode) Preconditions.checkNotNull(adCreativeHolderNode);
        this.mHttpClient = (AdHttpClient) Preconditions.checkNotNull(adHttpClient);
        Preconditions.checkNotNull(vastCompanionAds);
        this.mInline = z;
        this.mAdsRequired = (VastCompanionAds.AdsRequired) Optional.fromNullable(vastCompanionAds.getRequired()).or(VastCompanionAds.AdsRequired.none);
        if (vastCompanionAds.getCompanions() != null) {
            Iterator<VastCompanion> it = vastCompanionAds.getCompanions().iterator();
            while (it.hasNext()) {
                this.mCompanions.add(new CreativeCompanion(this.mParent, this.mHttpClient, it.next(), str));
            }
        }
    }

    public VastCompanionAds.AdsRequired getAdsRequired() {
        return this.mAdsRequired;
    }

    public boolean isDisplayable() {
        return this.mInline;
    }

    @Nonnull
    public List<CreativeCompanion> getCompanions() {
        return this.mCompanions;
    }
}
