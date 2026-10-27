package com.amazon.music.account;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class UpdateExplicitLanguagePreferenceCallable implements Callable<Boolean> {
    private static final AccountStateField[] EXPLICIT_FILTER_ARRAY;
    private static final Set<AccountStateField> EXPLICIT_FILTER_SET;
    private final AccountCache accountCache;
    private final AccountService accountService;
    private final AccountStateChangeListener accountStateChangeListener;
    private final boolean isExplicitFilterEnabledOnDevice;

    static {
        AccountStateField[] accountStateFieldArr = {AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE};
        EXPLICIT_FILTER_ARRAY = accountStateFieldArr;
        EXPLICIT_FILTER_SET = new HashSet(Arrays.asList(accountStateFieldArr));
    }

    public UpdateExplicitLanguagePreferenceCallable(AccountService accountService, boolean z, AccountCache accountCache, AccountStateChangeListener accountStateChangeListener) {
        this.accountService = (AccountService) Validate.notNull(accountService);
        this.isExplicitFilterEnabledOnDevice = z;
        this.accountCache = (AccountCache) Validate.notNull(accountCache);
        this.accountStateChangeListener = (AccountStateChangeListener) Validate.notNull(accountStateChangeListener);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Boolean call() throws Exception {
        boolean zUpdateExplicitLanguagePreference = this.accountService.updateExplicitLanguagePreference(this.isExplicitFilterEnabledOnDevice);
        if (zUpdateExplicitLanguagePreference) {
            updateUserExplicitLanguage();
        }
        return Boolean.valueOf(zUpdateExplicitLanguagePreference);
    }

    private void updateUserExplicitLanguage() {
        User cachedUser = this.accountCache.getCachedUser();
        this.accountCache.setCachedUser(new User(cachedUser.getCustomerId(), cachedUser.getMusicAccountStatus(), cachedUser.getCountryOfResidence(), cachedUser.getLibraryHomeMarketplace(), cachedUser.getTermsOfUseMarketplace(), cachedUser.getMusicTerritoryOfResidence(), cachedUser.hasAcceptedTermsOfUse(), cachedUser.hasCloudPlayerAccount(), cachedUser.isAccountVerified(), cachedUser.isInKatanaMarketplace(), cachedUser.isInNightwingMarketplace(), cachedUser.isInSonicRushMusicTerritory(), cachedUser.isInPrimeMarketplace(), cachedUser.isInHawkfireMarketplace(), cachedUser.isInBundesligaMarketplace(), cachedUser.getCloudAccountStatus(), cachedUser.getBenefits(), this.isExplicitFilterEnabledOnDevice, cachedUser.isTargetedAdsEnabled(), cachedUser.isCCBAOptOut(), cachedUser.getCreationDate()));
        this.accountStateChangeListener.onAccountStateChange(EXPLICIT_FILTER_SET);
    }
}
