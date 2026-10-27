package com.amazon.mp3.capability;

import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.featureflag.DefaultFeatureFlagProvider;
import com.amazon.music.marketplace.Marketplace;

/* JADX INFO: loaded from: classes3.dex */
public class NoOpFeatureFlagProvider extends DefaultFeatureFlagProvider {
    private static final String TAG = "NoOpFeatureFlagProvider";

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean areIvyRefinementPillsEnabled() {
        return true;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessRecommendationForUnlimited() {
        return true;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public int getAppRatingNoIncidentThreshold() {
        return 8;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public int getHybridQueueBatchSize() {
        return 50;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public int getHybridQueueLookaheadWindowSize() {
        return 10;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public int getPageLatencySamplingRate() {
        return 10;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isMarketPlaceCheckForLyricsSupported() {
        return true;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean shouldSuppressIvyCatalogNonTrackAlbumFiltering() {
        return true;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean supportsLanguagePreferences() {
        return false;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessUnlimited() {
        try {
            return AccountManagerSingleton.get().getUser().isInHawkfireMarketplace();
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            Log.error(TAG, "Data is not ready - cannot determine if User is in Unlimited Marketplace");
            return false;
        }
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessPrime() {
        try {
            return AccountManagerSingleton.get().getUser().isInPrimeMarketplace();
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            Log.error(TAG, "Data is not ready - cannot determine if User is in Prime Marketplace");
            return false;
        }
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessNightwing() {
        try {
            return AccountManagerSingleton.get().getUser().isInNightwingMarketplace();
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            Log.error(TAG, "Data is not ready - cannot determine if User is in Nightwing Marketplace");
            return false;
        }
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessKatana() {
        try {
            return AccountManagerSingleton.get().getUser().isInKatanaMarketplace();
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            Log.error(TAG, "Data is not ready - cannot determine if User is in Nightwing Marketplace");
            return false;
        }
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessStore() {
        try {
            Marketplace homeMarketPlaceNotDefaulted = AccountDetailUtil.get().getHomeMarketPlaceNotDefaulted();
            return homeMarketPlaceNotDefaulted == Marketplace.USA || homeMarketPlaceNotDefaulted == Marketplace.UK || homeMarketPlaceNotDefaulted == Marketplace.GERMANY || homeMarketPlaceNotDefaulted == Marketplace.FRANCE || homeMarketPlaceNotDefaulted == Marketplace.SPAIN || homeMarketPlaceNotDefaulted == Marketplace.ITALY || homeMarketPlaceNotDefaulted == Marketplace.JAPAN;
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return false;
        }
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessLyrics() {
        Marketplace homeMarketPlace = AccountDetailUtil.get().getHomeMarketPlace();
        return homeMarketPlace == Marketplace.USA || homeMarketPlace == Marketplace.UK || homeMarketPlace == Marketplace.GERMANY;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean primeBadgingRequired() {
        return !AccountDetailUtil.get().isRowMarketplace();
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isAppDisplayLangSettingEnabled() {
        return AccountDetailUtil.get().getHomeMarketPlace() == Marketplace.USA || MusicTerritory.INDIA.equalsIgnoreCase(AccountDetailUtil.getMusicTerritoryOfResidence());
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isINLanguageExpansionSupported() {
        return MusicTerritory.INDIA.equalsIgnoreCase(AccountDetailUtil.getMusicTerritoryOfResidence());
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessRecommendationForPrime() {
        Marketplace homeMarketPlace = AccountDetailUtil.get().getHomeMarketPlace();
        return (homeMarketPlace == Marketplace.FRANCE || homeMarketPlace == Marketplace.ITALY || homeMarketPlace == Marketplace.SPAIN || MusicTerritory.INDIA.equalsIgnoreCase(AccountDetailUtil.getMusicTerritoryOfResidence())) ? false : true;
    }

    @Override // com.amazon.music.featureflag.DefaultFeatureFlagProvider, com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isMerchEnabled() {
        return AccountDetailUtil.get().getHomeMarketPlace() == Marketplace.USA;
    }
}
