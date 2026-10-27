package com.amazon.music.account;

import java.util.Set;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class AccountStateChangeListenerRunnable implements Runnable {
    private final Set<AccountStateField> changedFields;
    private final AccountStateChangeListener listener;

    public AccountStateChangeListenerRunnable(AccountStateChangeListener accountStateChangeListener, Set<AccountStateField> set) {
        this.listener = (AccountStateChangeListener) Validate.notNull(accountStateChangeListener);
        this.changedFields = (Set) Validate.notNull(set);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.listener.onAccountStateChange(this.changedFields);
    }
}
