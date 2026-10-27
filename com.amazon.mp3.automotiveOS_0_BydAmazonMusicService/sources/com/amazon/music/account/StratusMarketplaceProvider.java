package com.amazon.music.account;

import com.amazon.music.marketplace.Marketplace;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class StratusMarketplaceProvider {
    private final AccountCache accountCache;

    public StratusMarketplaceProvider(AccountCache accountCache) {
        this.accountCache = (AccountCache) Validate.notNull(accountCache);
    }

    public Marketplace getMarketplace() throws MarketplaceProvisionFailedException {
        Marketplace libraryHomeMarketplace;
        User cachedUser = this.accountCache.getCachedUser();
        if (cachedUser == null || (libraryHomeMarketplace = cachedUser.getLibraryHomeMarketplace()) == null || libraryHomeMarketplace.equals(Marketplace.NONE)) {
            throw new MarketplaceProvisionFailedException();
        }
        return libraryHomeMarketplace;
    }

    public String getTerritory() throws MarketplaceProvisionFailedException {
        String musicTerritoryOfResidence;
        User cachedUser = this.accountCache.getCachedUser();
        if (cachedUser == null || (musicTerritoryOfResidence = cachedUser.getMusicTerritoryOfResidence()) == null) {
            throw new MarketplaceProvisionFailedException();
        }
        return musicTerritoryOfResidence;
    }
}
