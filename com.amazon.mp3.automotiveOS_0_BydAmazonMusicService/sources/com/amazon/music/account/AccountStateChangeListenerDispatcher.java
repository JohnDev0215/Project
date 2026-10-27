package com.amazon.music.account;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class AccountStateChangeListenerDispatcher implements AccountStateChangeListener {
    private final ExecutorService executorService;
    private final Set<AccountStateChangeListener> listeners;

    public AccountStateChangeListenerDispatcher() {
        this(Executors.newSingleThreadExecutor());
    }

    AccountStateChangeListenerDispatcher(ExecutorService executorService) {
        this.listeners = new CopyOnWriteArraySet();
        this.executorService = executorService;
    }

    public void registerListener(AccountStateChangeListener accountStateChangeListener) {
        this.listeners.add((AccountStateChangeListener) Validate.notNull(accountStateChangeListener));
    }

    public void deregisterListener(AccountStateChangeListener accountStateChangeListener) {
        this.listeners.remove(Validate.notNull(accountStateChangeListener));
    }

    @Override // com.amazon.music.account.AccountStateChangeListener
    public void onAccountStateChange(Set<AccountStateField> set) {
        Iterator<AccountStateChangeListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            this.executorService.submit(new AccountStateChangeListenerRunnable(it.next(), set));
        }
    }
}
