package com.amazon.music.account;

/* JADX INFO: loaded from: classes4.dex */
class UpdateServiceFactory {
    UpdateServiceFactory() {
    }

    public UpdateService buildUpdateService(AccountServiceFactory accountServiceFactory, AccountCache accountCache, AccountServiceConfiguration accountServiceConfiguration, StratusServiceConfiguration stratusServiceConfiguration, AccountStateChangeListener accountStateChangeListener) {
        return new UpdateService(new UpdateServiceRunnableFactory(accountServiceFactory, accountCache, accountServiceConfiguration, stratusServiceConfiguration, accountStateChangeListener));
    }
}
