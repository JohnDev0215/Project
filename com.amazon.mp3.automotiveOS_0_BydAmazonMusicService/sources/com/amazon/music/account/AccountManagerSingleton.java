package com.amazon.music.account;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class AccountManagerSingleton {
    private static AccountManager accountManager;
    private static AtomicBoolean initialized = new AtomicBoolean(false);

    @Deprecated
    public static synchronized void initialize(AccountManager accountManager2) {
        if (initialized.getAndSet(true)) {
            throw new IllegalStateException("Cannot call initialize more than once");
        }
        accountManager = accountManager2;
    }

    @Deprecated
    public static synchronized AccountManager get() {
        if (initialized.get()) {
        } else {
            throw new IllegalStateException("Cannot call get without first calling initialize");
        }
        return accountManager;
    }
}
