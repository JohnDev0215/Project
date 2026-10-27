package com.amazon.music.account;

import java.util.concurrent.Callable;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class AcceptTermsOfUseCallable implements Callable<Boolean> {
    private final AccountCache accountCache;
    private final AccountService accountService;

    public AcceptTermsOfUseCallable(AccountService accountService, AccountCache accountCache) {
        this.accountService = (AccountService) Validate.notNull(accountService);
        this.accountCache = (AccountCache) Validate.notNull(accountCache);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Boolean call() {
        return Boolean.valueOf(this.accountService.acceptTermsOfUse(this.accountCache.getCachedUser().getMusicTerritoryOfResidence()));
    }
}
