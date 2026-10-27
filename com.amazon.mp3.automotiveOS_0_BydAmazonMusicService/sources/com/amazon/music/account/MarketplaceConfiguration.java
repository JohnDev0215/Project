package com.amazon.music.account;

import com.amazon.music.marketplace.Marketplace;

/* JADX INFO: loaded from: classes4.dex */
public class MarketplaceConfiguration {
    private final Marketplace defaultMarketplace;
    private final String defaultTerritory;

    public MarketplaceConfiguration(Marketplace marketplace, String str) {
        this.defaultMarketplace = marketplace == null ? Marketplace.NONE : marketplace;
        this.defaultTerritory = str;
    }

    public Marketplace getDefaultMarketplace() {
        return this.defaultMarketplace;
    }

    public String getDefaultTerritory() {
        return this.defaultTerritory;
    }
}
