package com.amazon.music.account;

import com.amazon.music.account.RefreshCachedDataRunnable;
import java.util.EnumSet;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class UpdateServiceRunnableFactory {
    private final AccountCache accountCache;
    private final AccountServiceConfiguration accountServiceConfiguration;
    private final AccountServiceFactory accountServiceFactory;
    private final AccountStateChangeListener accountStateChangeListener;
    private final StratusServiceConfiguration stratusServiceConfiguration;

    public UpdateServiceRunnableFactory(AccountServiceFactory accountServiceFactory, AccountCache accountCache, AccountServiceConfiguration accountServiceConfiguration, StratusServiceConfiguration stratusServiceConfiguration, AccountStateChangeListener accountStateChangeListener) {
        this.accountServiceFactory = (AccountServiceFactory) Validate.notNull(accountServiceFactory);
        this.accountCache = (AccountCache) Validate.notNull(accountCache);
        this.accountServiceConfiguration = (AccountServiceConfiguration) Validate.notNull(accountServiceConfiguration);
        this.stratusServiceConfiguration = (StratusServiceConfiguration) Validate.notNull(stratusServiceConfiguration);
        this.accountStateChangeListener = (AccountStateChangeListener) Validate.notNull(accountStateChangeListener);
    }

    public RefreshCachedDataRunnable buildRefreshCachedDataRunnable() {
        return getRefreshCachedDataRunnable(EnumSet.allOf(RefreshCachedDataRunnable.Filter.class));
    }

    public RefreshCachedDataRunnable buildRefreshCachedUserDataRunnable() {
        return getRefreshCachedDataRunnable(EnumSet.of(RefreshCachedDataRunnable.Filter.USER));
    }

    public RefreshCachedDataRunnable buildRefreshCachedDevicesDataRunnable() {
        return getRefreshCachedDataRunnable(EnumSet.of(RefreshCachedDataRunnable.Filter.DEVICE, RefreshCachedDataRunnable.Filter.DEVICE_CAPABILITIES, RefreshCachedDataRunnable.Filter.DEVICE_AUTHORIZATION));
    }

    private RefreshCachedDataRunnable getRefreshCachedDataRunnable(EnumSet<RefreshCachedDataRunnable.Filter> enumSet) {
        return new RefreshCachedDataRunnable(new AccountCacheSaver(this.accountCache, new AccountStateComparator()), this.accountServiceFactory.buildAccountService(this.stratusServiceConfiguration, this.accountServiceConfiguration, this.accountCache), this.accountStateChangeListener, enumSet);
    }

    public AcceptTermsOfUseCallable buildAcceptTermsOfUseCallable() {
        return new AcceptTermsOfUseCallable(this.accountServiceFactory.buildAccountService(this.stratusServiceConfiguration, this.accountServiceConfiguration, this.accountCache), this.accountCache);
    }

    public CatalogAuthorizeDeviceCallable buildCatalogAuthorizeDeviceCallable() {
        return new CatalogAuthorizeDeviceCallable(this.accountServiceFactory.buildAccountService(this.stratusServiceConfiguration, this.accountServiceConfiguration, this.accountCache));
    }

    public UpdateExplicitLanguagePreferenceCallable buildUpdateExplicitLanguagePreferenceCallable(boolean z) {
        return new UpdateExplicitLanguagePreferenceCallable(this.accountServiceFactory.buildAccountService(this.stratusServiceConfiguration, this.accountServiceConfiguration, this.accountCache), z, this.accountCache, this.accountStateChangeListener);
    }

    public UpdateTargetedAdsPreferenceCallable buildTargetedAdsPreferenceCallable(boolean z) {
        return new UpdateTargetedAdsPreferenceCallable(this.accountServiceFactory.buildAccountService(this.stratusServiceConfiguration, this.accountServiceConfiguration, this.accountCache), z, this.accountCache, this.accountStateChangeListener);
    }
}
