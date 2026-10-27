package com.amazon.device.ads;

import com.amazon.device.ads.PreferredMarketplaceRetriever;

/* JADX INFO: loaded from: classes2.dex */
class AmazonOOPreferredMarketplaceRetrieverFactory {
    private final MAPUtils mapUtils;

    AmazonOOPreferredMarketplaceRetrieverFactory() {
        this(new MAPUtils());
    }

    AmazonOOPreferredMarketplaceRetrieverFactory(MAPUtils mAPUtils) {
        this.mapUtils = mAPUtils;
    }

    public PreferredMarketplaceRetriever createPreferredMarketplaceRetriever() {
        if (this.mapUtils.isMAPAvailable()) {
            return new AuthenticationPlatformPreferredMarketplaceRetriever(new AmazonOOMAPAdapter());
        }
        return new PreferredMarketplaceRetriever.NullPreferredMarketplaceRetriever();
    }
}
