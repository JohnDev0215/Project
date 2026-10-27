package com.amazon.music.account;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class UpdateService {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateService.class.getSimpleName());
    private ScheduledFuture activePollingRunnable;
    private final UpdateServiceRunnableFactory runnableFactory;
    private final ScheduledExecutorService service;

    public UpdateService(UpdateServiceRunnableFactory updateServiceRunnableFactory) {
        this(updateServiceRunnableFactory, Executors.newSingleThreadScheduledExecutor());
    }

    UpdateService(UpdateServiceRunnableFactory updateServiceRunnableFactory, ScheduledExecutorService scheduledExecutorService) {
        this.runnableFactory = (UpdateServiceRunnableFactory) Validate.notNull(updateServiceRunnableFactory);
        this.service = scheduledExecutorService;
    }

    public synchronized void startPolling(long j) {
        cancelActiveTaskIfNecessary();
        this.activePollingRunnable = this.service.scheduleWithFixedDelay(this.runnableFactory.buildRefreshCachedDataRunnable(), 0L, j, TimeUnit.MILLISECONDS);
    }

    public synchronized void stopPolling() {
        cancelActiveTaskIfNecessary();
    }

    public void update() {
        try {
            this.service.submit(this.runnableFactory.buildRefreshCachedDataRunnable()).get();
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Unable to execute update", e);
        }
    }

    public void updateAsync() {
        this.service.submit(this.runnableFactory.buildRefreshCachedDataRunnable());
    }

    public void updateUser() {
        try {
            this.service.submit(this.runnableFactory.buildRefreshCachedUserDataRunnable()).get();
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Unable to execute update", e);
        }
    }

    public void updateDevices() {
        try {
            this.service.submit(this.runnableFactory.buildRefreshCachedDevicesDataRunnable()).get();
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Unable to execute update", e);
        }
    }

    public boolean acceptTermsOfUse() {
        return makeCall(this.runnableFactory.buildAcceptTermsOfUseCallable(), "acceptTermsOfUse");
    }

    public boolean catalogAuthorizeDevice() {
        return makeCall(this.runnableFactory.buildCatalogAuthorizeDeviceCallable(), "catalogAuthorizeDevice");
    }

    public boolean updateExplicitLanguagePreference(boolean z) {
        return makeCall(this.runnableFactory.buildUpdateExplicitLanguagePreferenceCallable(z), "updateExplicitLanguagePreference");
    }

    public boolean updateTargetedAdsPreference(boolean z) {
        return makeCall(this.runnableFactory.buildTargetedAdsPreferenceCallable(z), "updateTargetedAdsPreference");
    }

    private boolean makeCall(Callable callable, String str) {
        try {
            Boolean bool = (Boolean) this.service.submit(callable).get();
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Unable to execute " + str, e);
            return false;
        }
    }

    private synchronized void cancelActiveTaskIfNecessary() {
        ScheduledFuture scheduledFuture = this.activePollingRunnable;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.activePollingRunnable = null;
        }
    }
}
