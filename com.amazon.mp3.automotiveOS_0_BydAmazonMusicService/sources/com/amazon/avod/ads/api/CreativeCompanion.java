package com.amazon.avod.ads.api;

import com.amazon.avod.ads.api.internal.AdCreativeHolderNode;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastCompanion;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class CreativeCompanion extends Creative {
    private final VastCompanion mInnerCompanion;
    private final Resource mResource;

    public CreativeCompanion(AdCreativeHolderNode adCreativeHolderNode, AdHttpClient adHttpClient, VastCompanion vastCompanion, String str) {
        super((AdCreativeHolderNode) Preconditions.checkNotNull(adCreativeHolderNode), (AdHttpClient) Preconditions.checkNotNull(adHttpClient), vastCompanion.getTrackingEvents(), str);
        this.mInnerCompanion = vastCompanion;
        if (vastCompanion.getResource() != null) {
            this.mResource = new Resource(vastCompanion.getResource());
        } else {
            this.mResource = null;
        }
    }

    @Override // com.amazon.avod.ads.api.Creative
    public CreativeType getCreativeType() {
        return CreativeType.companion;
    }

    @Override // com.amazon.avod.ads.api.Creative
    public boolean isDisplayable() {
        return this.mResource != null;
    }

    public int getWidth() {
        return this.mInnerCompanion.getWidth();
    }

    public int getHeight() {
        return this.mInnerCompanion.getHeight();
    }
}
