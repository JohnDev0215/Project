package com.amazon.music.account;

import java.util.concurrent.Callable;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class CatalogAuthorizeDeviceCallable implements Callable<Boolean> {
    private final AccountService accountService;

    public CatalogAuthorizeDeviceCallable(AccountService accountService) {
        this.accountService = (AccountService) Validate.notNull(accountService);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Boolean call() throws Exception {
        return Boolean.valueOf(this.accountService.catalogAuthorizeDevice());
    }
}
