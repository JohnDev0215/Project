package com.amazon.device.ads;

import android.content.Context;
import com.amazon.device.ads.AmazonOOAdRegistration;
import com.amazon.device.ads.ThreadUtils;
import com.amazon.device.ads.WebRequest;

/* JADX INFO: loaded from: classes2.dex */
class AmazonOOAdRegistrationExecutor extends AdRegistrationExecutor {
    private static final String LOGTAG = AmazonOOAdRegistration.class.getSimpleName();
    private final AdvertisingIdentifier advertisingIdentifier;
    private final AppEventRegistrationHandler appEventRegistrationHandler;
    private final Configuration configuration;
    private final DebugProperties debugProperties;
    private final SystemTime systemTime;
    private final ThreadUtils.ThreadRunner threadRunner;
    private final WebRequest.WebRequestFactory webRequestFactory;
    private final WebUtils2 webUtils;

    public AmazonOOAdRegistrationExecutor() {
        this(AppEventRegistrationHandler.getInstance(), Configuration.getInstance(), new AdvertisingIdentifier(), ThreadUtils.getThreadRunner(), MobileAdsInfoStore.getInstance(), Settings.getInstance(), new MobileAdsLoggerFactory(), new PermissionChecker(), new SystemTime(), DebugProperties.getInstance(), new WebUtils2(), new WebRequest.WebRequestFactory(), new MAPUtils(), new AmazonOOPreferredMarketplaceRetrieverFactory());
    }

    AmazonOOAdRegistrationExecutor(AppEventRegistrationHandler appEventRegistrationHandler, Configuration configuration, AdvertisingIdentifier advertisingIdentifier, ThreadUtils.ThreadRunner threadRunner, MobileAdsInfoStore mobileAdsInfoStore, Settings settings, MobileAdsLoggerFactory mobileAdsLoggerFactory, PermissionChecker permissionChecker, SystemTime systemTime, DebugProperties debugProperties, WebUtils2 webUtils2, WebRequest.WebRequestFactory webRequestFactory, MAPUtils mAPUtils, AmazonOOPreferredMarketplaceRetrieverFactory amazonOOPreferredMarketplaceRetrieverFactory) {
        super(LOGTAG, mobileAdsInfoStore, settings, mobileAdsLoggerFactory, permissionChecker);
        this.appEventRegistrationHandler = appEventRegistrationHandler;
        this.configuration = configuration;
        this.advertisingIdentifier = advertisingIdentifier;
        this.threadRunner = threadRunner;
        this.systemTime = systemTime;
        this.debugProperties = debugProperties;
        this.webUtils = webUtils2;
        this.webRequestFactory = webRequestFactory;
        configuration.setIsFirstParty(true);
        configuration.setPreferredMarketplaceRetriever(amazonOOPreferredMarketplaceRetrieverFactory.createPreferredMarketplaceRetriever());
        if (mAPUtils.isMAPAvailable()) {
            getSettings().putTransientObject("userIdParam", new DirectedIdParameter());
            getSettings().putTransientObject("directedIdRetriever", new MAPDirectedIdRetriever());
        } else {
            getLogger().d("MAP Library not available");
        }
    }

    public void registerApp(Context context) {
        registerApp(context, null);
    }

    public void listenForRegistrationInfo(AmazonOOAdRegistrationListener amazonOOAdRegistrationListener) {
        new AmazonOOStartUpWaiter(amazonOOAdRegistrationListener, this.advertisingIdentifier, getMobileAdsInfoStore(), getSettings(), this.configuration, this.systemTime, this).start();
    }

    public void registerApp(Context context, AmazonOOAdRegistrationListener amazonOOAdRegistrationListener) {
        if (!getPermissionChecker().hasInternetPermission(context)) {
            getLogger().e("Network task cannot commence because the INTERNET permission is missing from the app's manifest.");
            return;
        }
        prepareRegistrationInfo(context);
        getMobileAdsInfoStore().register();
        if (amazonOOAdRegistrationListener != null) {
            listenForRegistrationInfo(amazonOOAdRegistrationListener);
        }
    }

    public void prepareRegistrationInfo(Context context) {
        getMobileAdsInfoStore().contextReceived(context);
    }

    public void setAppName(String str) {
        getMobileAdsInfoStore().getRegistrationInfo().putAppName(str);
    }

    public void setAppDefinedMarketplace(String str) {
        this.configuration.setAppDefinedMarketplace(str);
    }

    @Deprecated
    public void identifyAmazonUserUsingMAP() {
        if (!getMobileAdsInfoStore().isRegistered()) {
            throw new IllegalStateException("AmazonOOAdRegistration.registerApp must be called before identify user.");
        }
        AmazonOOMAPUserIdentifier amazonOOMAPUserIdentifier = new AmazonOOMAPUserIdentifier(new AmazonOOMAPAdapter().setContext(getMobileAdsInfoStore().getApplicationContext()), this.advertisingIdentifier, this.configuration, getSettings(), new TrustedPackageManager(getMobileAdsInfoStore().getAppInfo().getPackageName(), getMobileAdsInfoStore().getAppInfo().getPackageManager(), TrustedPackageManager.getTrustedCerts()), this.systemTime, this.debugProperties, getLoggerFactory());
        amazonOOMAPUserIdentifier.setMobileAdsInfoStore(getMobileAdsInfoStore());
        amazonOOMAPUserIdentifier.executeRequest();
    }

    public void setReferrer(Context context, String str) {
        if (StringUtils.isNullOrEmpty(str)) {
            getLogger().e("Referrer was null or emtpy. Could not set the referrer.");
            return;
        }
        prepareRegistrationInfo(context);
        AmazonOOAppEvent amazonOOAppEvent = new AmazonOOAppEvent("INSTALL_REFERRER", this.systemTime.currentTimeMillis());
        amazonOOAppEvent.setProperty("referrer", str);
        this.appEventRegistrationHandler.addEventToAppEventsCacheFile(amazonOOAppEvent);
        getLogger().logSetterNotification("Referrer", str);
    }

    private static class AmazonOOStartUpWaiter extends StartUpWaiter {
        private final AdvertisingIdentifier advertisingIdentifier;
        private final AmazonOOAdRegistrationExecutor executor;
        private final MobileAdsInfoStore infoStore;
        private final AmazonOOAdRegistrationListener listener;
        private final SystemTime systemTime;

        public AmazonOOStartUpWaiter(AmazonOOAdRegistrationListener amazonOOAdRegistrationListener, AdvertisingIdentifier advertisingIdentifier, MobileAdsInfoStore mobileAdsInfoStore, Settings settings, Configuration configuration, SystemTime systemTime, AmazonOOAdRegistrationExecutor amazonOOAdRegistrationExecutor) {
            super(settings, configuration);
            this.listener = amazonOOAdRegistrationListener;
            this.advertisingIdentifier = advertisingIdentifier;
            this.infoStore = mobileAdsInfoStore;
            this.systemTime = systemTime;
            this.executor = amazonOOAdRegistrationExecutor;
        }

        @Override // com.amazon.device.ads.StartUpWaiter
        protected void startUpReady() {
            this.listener.onRegistrationInfoReady(getAmazonOOAdRegistrationInfo());
        }

        @Override // com.amazon.device.ads.StartUpWaiter
        protected void startUpFailed() {
            this.listener.onRegistrationInfoReady(getAmazonOOAdRegistrationInfo());
        }

        private AmazonOOAdRegistration.AmazonOOAdRegistrationInfo getAmazonOOAdRegistrationInfo() {
            return new AmazonOOAdRegistration.AmazonOOAdRegistrationInfo(this.advertisingIdentifier, this.infoStore, getSettings(), getConfiguration(), this.systemTime, this.executor);
        }
    }
}
