package com.amazon.music.account;

import java.util.EnumSet;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class RefreshCachedDataRunnable implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(RefreshCachedDataRunnable.class.getSimpleName());
    private final AccountCacheSaver accountCacheSaver;
    private final AccountService accountService;
    private final AccountStateChangeListener accountStateChangeListener;
    private final EnumSet<Filter> filters;

    public enum Filter {
        USER,
        DEVICE,
        DEVICE_AUTHORIZATION,
        DEVICE_CAPABILITIES
    }

    public RefreshCachedDataRunnable(AccountCacheSaver accountCacheSaver, AccountService accountService, AccountStateChangeListener accountStateChangeListener, EnumSet<Filter> enumSet) {
        this.accountService = (AccountService) Validate.notNull(accountService);
        this.accountCacheSaver = (AccountCacheSaver) Validate.notNull(accountCacheSaver);
        this.accountStateChangeListener = (AccountStateChangeListener) Validate.notNull(accountStateChangeListener);
        this.filters = (EnumSet) Validate.notNull(enumSet);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            EnumSet enumSetNoneOf = EnumSet.noneOf(AccountStateField.class);
            if (this.filters.contains(Filter.USER)) {
                UserStateWrapper userStateFromService = this.accountService.getUserStateFromService();
                if (userStateFromService != null) {
                    enumSetNoneOf.addAll(this.accountCacheSaver.saveUser(userStateFromService.getUser()));
                    this.accountCacheSaver.saveInvokeAccountWebview(userStateFromService.shouldInvokeAccountWorkflow());
                }
                if (Thread.interrupted()) {
                    return;
                }
            }
            if (this.filters.contains(Filter.DEVICE)) {
                enumSetNoneOf.addAll(this.accountCacheSaver.saveDevice(this.accountService.getDeviceFromService()));
                if (Thread.interrupted()) {
                    return;
                }
            }
            if (this.filters.contains(Filter.DEVICE_AUTHORIZATION)) {
                enumSetNoneOf.addAll(this.accountCacheSaver.saveDeviceAuthorizations(this.accountService.getDeviceAuthorizationsFromService()));
                if (Thread.interrupted()) {
                    return;
                }
            }
            if (this.filters.contains(Filter.DEVICE_CAPABILITIES)) {
                enumSetNoneOf.addAll(this.accountCacheSaver.saveDeviceCapabilities(this.accountService.getDeviceCapabilitiesFromService()));
                if (Thread.interrupted()) {
                    return;
                }
            }
            if (enumSetNoneOf.isEmpty()) {
                return;
            }
            this.accountStateChangeListener.onAccountStateChange(enumSetNoneOf);
        } catch (Exception e) {
            LOG.error("Caught unexpected exception", (Throwable) e);
        }
    }
}
