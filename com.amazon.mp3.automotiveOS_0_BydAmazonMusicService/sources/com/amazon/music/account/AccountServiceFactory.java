package com.amazon.music.account;

/* JADX INFO: loaded from: classes4.dex */
public class AccountServiceFactory {
    public AccountService buildAccountService(StratusServiceConfiguration stratusServiceConfiguration, AccountServiceConfiguration accountServiceConfiguration, AccountCache accountCache) {
        return new AccountService(new StratusService(stratusServiceConfiguration, buildStratusMarketplaceProvider(accountCache)), new AccountServiceResponseParser(), new AccountServiceRequestBuilder(accountServiceConfiguration));
    }

    public AccountService buildAccountService(StratusServiceConfiguration stratusServiceConfiguration, MusicIdentityServiceConfiguration musicIdentityServiceConfiguration, AccountServiceConfiguration accountServiceConfiguration, AccountCache accountCache) {
        return new AccountService(new StratusService(stratusServiceConfiguration, buildStratusMarketplaceProvider(accountCache)), new MusicIdentityService(musicIdentityServiceConfiguration), new AccountServiceResponseParser(), new AccountServiceRequestBuilder(accountServiceConfiguration));
    }

    StratusMarketplaceProvider buildStratusMarketplaceProvider(AccountCache accountCache) {
        return new StratusMarketplaceProvider(accountCache);
    }
}
