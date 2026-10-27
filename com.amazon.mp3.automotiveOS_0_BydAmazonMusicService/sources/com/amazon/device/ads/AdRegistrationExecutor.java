package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
class AdRegistrationExecutor {
    private final MobileAdsInfoStore infoStore;
    private volatile boolean isInitialized = false;
    private final MobileAdsLogger logger;
    private final MobileAdsLoggerFactory loggerFactory;
    private final PermissionChecker permissionChecker;
    private final Settings settings;

    AdRegistrationExecutor(String str, MobileAdsInfoStore mobileAdsInfoStore, Settings settings, MobileAdsLoggerFactory mobileAdsLoggerFactory, PermissionChecker permissionChecker) {
        this.infoStore = mobileAdsInfoStore;
        this.settings = settings;
        this.loggerFactory = mobileAdsLoggerFactory;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(str);
        this.permissionChecker = permissionChecker;
    }

    public void setAppKey(String str) throws IllegalArgumentException {
        this.infoStore.getRegistrationInfo().putAppKey(str);
    }

    MobileAdsInfoStore getMobileAdsInfoStore() {
        return this.infoStore;
    }

    Settings getSettings() {
        return this.settings;
    }

    PermissionChecker getPermissionChecker() {
        return this.permissionChecker;
    }

    MobileAdsLogger getLogger() {
        return this.logger;
    }

    MobileAdsLoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }
}
