package com.amazon.music.account;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class UpdateTargetedAdsPreferenceCallable implements Callable<Boolean> {
    private static final AccountStateField[] TARGETED_ADS_ENABLED_FILTER_ARRAY;
    private static final Set<AccountStateField> TARGETED_ADS_FILTER_SET;
    private final AccountCache accountCache;
    private final AccountService accountService;
    private final AccountStateChangeListener accountStateChangeListener;
    private final boolean isTargetedAdsEnabled;

    static {
        AccountStateField[] accountStateFieldArr = {AccountStateField.TARGETED_ADS_ENABLED};
        TARGETED_ADS_ENABLED_FILTER_ARRAY = accountStateFieldArr;
        TARGETED_ADS_FILTER_SET = new HashSet(Arrays.asList(accountStateFieldArr));
    }

    public UpdateTargetedAdsPreferenceCallable(AccountService accountService, boolean z, AccountCache accountCache, AccountStateChangeListener accountStateChangeListener) {
        this.accountService = (AccountService) Validate.notNull(accountService);
        this.isTargetedAdsEnabled = z;
        this.accountCache = (AccountCache) Validate.notNull(accountCache);
        this.accountStateChangeListener = (AccountStateChangeListener) Validate.notNull(accountStateChangeListener);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Boolean call() throws Exception {
        boolean zUpdateTargetedAdsPreference = this.accountService.updateTargetedAdsPreference(this.isTargetedAdsEnabled);
        updateUserTargetedAdsPeference();
        return Boolean.valueOf(zUpdateTargetedAdsPreference);
    }

    private void updateUserTargetedAdsPeference() {
        User cachedUser = this.accountCache.getCachedUser();
        this.accountCache.setCachedUser(new User(cachedUser.getCustomerId(), cachedUser.getMusicAccountStatus(), cachedUser.getCountryOfResidence(), cachedUser.getLibraryHomeMarketplace(), cachedUser.getTermsOfUseMarketplace(), cachedUser.getMusicTerritoryOfResidence(), cachedUser.hasAcceptedTermsOfUse(), cachedUser.hasCloudPlayerAccount(), cachedUser.isAccountVerified(), cachedUser.isInKatanaMarketplace(), cachedUser.isInNightwingMarketplace(), cachedUser.isInSonicRushMusicTerritory(), cachedUser.isInPrimeMarketplace(), cachedUser.isInHawkfireMarketplace(), cachedUser.isInBundesligaMarketplace(), cachedUser.getCloudAccountStatus(), cachedUser.getBenefits(), cachedUser.isExplicitFilterEnabledOnDevice(), this.isTargetedAdsEnabled, cachedUser.isCCBAOptOut(), cachedUser.getCreationDate()));
        this.accountStateChangeListener.onAccountStateChange(TARGETED_ADS_FILTER_SET);
    }
}
