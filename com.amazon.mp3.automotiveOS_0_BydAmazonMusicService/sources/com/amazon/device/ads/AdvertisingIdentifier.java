package com.amazon.device.ads;

import com.amazon.device.ads.GooglePlayServices;

/* JADX INFO: loaded from: classes2.dex */
class AdvertisingIdentifier {
    private static final String LOGTAG = "AdvertisingIdentifier";
    private static String lastKnownIdfa = null;
    private static boolean lastKnownIdfaDefined = false;
    private final DebugProperties debugProperties;
    private GooglePlayServices.AdvertisingInfo gpsAdvertisingInfo;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final Settings settings;
    private boolean shouldSetCurrentAdvertisingIdentifier;

    public static String getLastKnownIdfa() {
        return lastKnownIdfa;
    }

    public AdvertisingIdentifier() {
        this(Settings.getInstance(), MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), DebugProperties.getInstance());
    }

    AdvertisingIdentifier(Settings settings, MobileAdsInfoStore mobileAdsInfoStore, MobileAdsLoggerFactory mobileAdsLoggerFactory, DebugProperties debugProperties) {
        this.shouldSetCurrentAdvertisingIdentifier = true;
        this.settings = settings;
        this.infoStore = mobileAdsInfoStore;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.debugProperties = debugProperties;
        if (lastKnownIdfaDefined) {
            return;
        }
        lastKnownIdfaDefined = true;
        lastKnownIdfa = getCurrentGPSAID();
    }

    String getAndClearTransition() {
        String string = this.settings.getString("adIdTransistion", null);
        this.settings.remove("adIdTransistion");
        return string;
    }

    Info getAdvertisingIdentifierInfo() {
        String advertisingIdentifier;
        if (ThreadUtils.isOnMainThread()) {
            this.logger.e("You must obtain the advertising indentifier information on a background thread.");
            return new Info(this.debugProperties).setCanDo(false);
        }
        fetchGooglePlayServicesAdvertisingIdentifierInfo();
        if (this.shouldSetCurrentAdvertisingIdentifier) {
            determineTransition();
        }
        Info info = new Info(this.debugProperties);
        if (getGPSAdvertisingInfo().hasAdvertisingIdentifier()) {
            info.setAdvertisingIdentifier(getGPSAdvertisingInfo().getAdvertisingIdentifier());
            info.setLimitAdTrackingEnabled(getGPSAdvertisingInfo().isLimitAdTrackingEnabled());
            if (this.shouldSetCurrentAdvertisingIdentifier && (advertisingIdentifier = getGPSAdvertisingInfo().getAdvertisingIdentifier()) != null && !advertisingIdentifier.isEmpty()) {
                setCurrentGPSAID(advertisingIdentifier);
            }
        }
        RegistrationInfo registrationInfo = this.infoStore.getRegistrationInfo();
        if (registrationInfo.isAdIdCurrent(info)) {
            info.setSISDeviceIdentifier(registrationInfo.getAdId());
        } else {
            registrationInfo.requestNewSISDeviceIdentifier();
        }
        return info;
    }

    private void determineTransition() {
        String str;
        if (isTransitionMigrated()) {
            str = "migrate";
        } else if (isTransitionReset()) {
            str = "reset";
        } else {
            str = isTransitionReverted() ? "revert" : null;
        }
        if (str != null) {
            setTransition(str);
        } else {
            this.logger.d("No transition detected.");
        }
    }

    private void setTransition(String str) {
        this.logger.d("Transition: %s", str);
        this.settings.putString("adIdTransistion", str);
    }

    protected void fetchGooglePlayServicesAdvertisingIdentifierInfo() {
        this.gpsAdvertisingInfo = new GooglePlayServices().getAdvertisingIdentifierInfo();
    }

    protected GooglePlayServices.AdvertisingInfo getGPSAdvertisingInfo() {
        if (this.gpsAdvertisingInfo == null) {
            fetchGooglePlayServicesAdvertisingIdentifierInfo();
        }
        return this.gpsAdvertisingInfo;
    }

    private boolean isTransitionMigrated() {
        return this.infoStore.getRegistrationInfo().hasAdId() && RegistrationInfo.isAdIdOriginatedFromNonAdvertisingIdentifier() && !hasCurrentGPSAID() && getGPSAdvertisingInfo().hasAdvertisingIdentifier();
    }

    private boolean isTransitionReset() {
        return hasCurrentGPSAID() && getGPSAdvertisingInfo().hasAdvertisingIdentifier() && !getCurrentGPSAID().equals(getGPSAdvertisingInfo().getAdvertisingIdentifier());
    }

    private boolean isTransitionReverted() {
        return hasCurrentGPSAID() && !getGPSAdvertisingInfo().hasAdvertisingIdentifier();
    }

    private void setCurrentGPSAID(String str) {
        lastKnownIdfa = str;
        this.settings.putString("gpsAdId", str);
    }

    private String getCurrentGPSAID() {
        return this.settings.getString("gpsAdId", "");
    }

    private boolean hasCurrentGPSAID() {
        return !StringUtils.isNullOrEmpty(getCurrentGPSAID());
    }

    static class Info {
        private String advertisingIdentifier;
        private boolean canDo;
        private final DebugProperties debugProperties;
        private boolean limitAdTrackingEnabled;
        private String sisDeviceIdentifier;

        private Info(DebugProperties debugProperties) {
            this.debugProperties = debugProperties;
            this.canDo = true;
        }

        boolean canDo() {
            return this.canDo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Info setCanDo(boolean z) {
            this.canDo = z;
            return this;
        }

        String getAdvertisingIdentifier() {
            return this.debugProperties.getDebugPropertyAsString("debug.idfa", this.advertisingIdentifier);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Info setAdvertisingIdentifier(String str) {
            this.advertisingIdentifier = str;
            return this;
        }

        boolean hasAdvertisingIdentifier() {
            return !StringUtils.isNullOrEmpty(getAdvertisingIdentifier());
        }

        boolean isLimitAdTrackingEnabled() {
            return this.debugProperties.getDebugPropertyAsBoolean("debug.optOut", Boolean.valueOf(this.limitAdTrackingEnabled)).booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Info setLimitAdTrackingEnabled(boolean z) {
            this.limitAdTrackingEnabled = z;
            return this;
        }

        String getSISDeviceIdentifier() {
            return this.debugProperties.getDebugPropertyAsString("debug.adid", this.sisDeviceIdentifier);
        }

        boolean hasSISDeviceIdentifier() {
            return getSISDeviceIdentifier() != null;
        }

        Info setSISDeviceIdentifier(String str) {
            this.sisDeviceIdentifier = str;
            return this;
        }
    }
}
