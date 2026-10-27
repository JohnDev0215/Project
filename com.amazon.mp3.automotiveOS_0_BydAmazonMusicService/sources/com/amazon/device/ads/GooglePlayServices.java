package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
class GooglePlayServices {
    private static final String LOGTAG = "GooglePlayServices";
    private final MobileAdsLogger logger;
    private final ReflectionUtils reflectionUtils;

    public GooglePlayServices() {
        this(new MobileAdsLoggerFactory(), new ReflectionUtils());
    }

    GooglePlayServices(MobileAdsLoggerFactory mobileAdsLoggerFactory, ReflectionUtils reflectionUtils) {
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.reflectionUtils = reflectionUtils;
    }

    public AdvertisingInfo getAdvertisingIdentifierInfo() {
        AdvertisingInfo advertisingIdentifierInfo;
        GooglePlayServicesAdapter googlePlayServicesAdapterCreateGooglePlayServicesAdapter;
        if (!isGPSAvailable()) {
            this.logger.v("The Google Play Services Advertising Identifier feature is not available.");
            return AdvertisingInfo.createNotAvailable();
        }
        if (!isGPSAvailableSet() && !isGoogleAdvertisingClassAvailable()) {
            this.logger.v("The Google Play Services Advertising Identifier feature is not available.");
            setGooglePlayServicesAvailable(false);
            return AdvertisingInfo.createNotAvailable();
        }
        AdvertisingInfo advertisingIdentifierInfo2 = null;
        if (isGoogleAdvertisingClassAvailable() && (googlePlayServicesAdapterCreateGooglePlayServicesAdapter = createGooglePlayServicesAdapter()) != null && (advertisingIdentifierInfo2 = googlePlayServicesAdapterCreateGooglePlayServicesAdapter.getAdvertisingIdentifierInfo()) != null && advertisingIdentifierInfo2.getAdvertisingIdentifier() != null && !advertisingIdentifierInfo2.getAdvertisingIdentifier().isEmpty()) {
            setGooglePlayServicesAvailable(advertisingIdentifierInfo2.isGPSAvailable());
            return advertisingIdentifierInfo2;
        }
        AmazonFireServicesAdapter amazonFireServicesAdapterCreateAmazonFireServiceAdapter = createAmazonFireServiceAdapter();
        if (amazonFireServicesAdapterCreateAmazonFireServiceAdapter != null && (advertisingIdentifierInfo = amazonFireServicesAdapterCreateAmazonFireServiceAdapter.getAdvertisingIdentifierInfo()) != null && advertisingIdentifierInfo.getAdvertisingIdentifier() != null && !advertisingIdentifierInfo.getAdvertisingIdentifier().isEmpty()) {
            setGooglePlayServicesAvailable(advertisingIdentifierInfo.isGPSAvailable());
            return advertisingIdentifierInfo;
        }
        this.logger.v("Advertising Identifier feature is not available.");
        if (advertisingIdentifierInfo2 == null || !advertisingIdentifierInfo2.gpsAvailable) {
            setGooglePlayServicesAvailable(false);
        }
        return advertisingIdentifierInfo2;
    }

    protected GooglePlayServicesAdapter createGooglePlayServicesAdapter() {
        return new GooglePlayServicesAdapter();
    }

    protected AmazonFireServicesAdapter createAmazonFireServiceAdapter() {
        return AmazonFireServicesAdapter.newAdapter();
    }

    private boolean isGoogleAdvertisingClassAvailable() {
        return this.reflectionUtils.isClassAvailable("com.google.android.gms.ads.identifier.AdvertisingIdClient");
    }

    private boolean isGPSAvailable() {
        return Settings.getInstance().getBoolean("gps-available", true);
    }

    private boolean isGPSAvailableSet() {
        return Settings.getInstance().containsKey("gps-available");
    }

    private void setGooglePlayServicesAvailable(boolean z) {
        Settings.getInstance().putTransientBoolean("gps-available", z);
    }

    static class AdvertisingInfo {
        private String advertisingIdentifier;
        private boolean gpsAvailable = true;
        private boolean limitAdTrackingEnabled;

        protected AdvertisingInfo() {
        }

        static AdvertisingInfo createNotAvailable() {
            return new AdvertisingInfo().setGPSAvailable(false);
        }

        boolean isGPSAvailable() {
            return this.gpsAvailable;
        }

        private AdvertisingInfo setGPSAvailable(boolean z) {
            this.gpsAvailable = z;
            return this;
        }

        String getAdvertisingIdentifier() {
            return this.advertisingIdentifier;
        }

        AdvertisingInfo setAdvertisingIdentifier(String str) {
            this.advertisingIdentifier = str;
            return this;
        }

        boolean hasAdvertisingIdentifier() {
            return getAdvertisingIdentifier() != null;
        }

        boolean isLimitAdTrackingEnabled() {
            return this.limitAdTrackingEnabled;
        }

        AdvertisingInfo setLimitAdTrackingEnabled(boolean z) {
            this.limitAdTrackingEnabled = z;
            return this;
        }
    }
}
