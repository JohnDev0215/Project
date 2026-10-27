package com.amazon.device.ads;

import com.amazon.device.ads.AdvertisingIdentifier;

/* JADX INFO: loaded from: classes2.dex */
class RegistrationInfo {
    private String appKey;
    private String appName = "app";

    public String getAppName() {
        return this.appName;
    }

    public void putAppName(String str) {
        this.appName = WebUtils.getURLEncodedString(str);
    }

    public String getAdId() {
        return DebugProperties.getInstance().getDebugPropertyAsString("debug.adid", Settings.getInstance().getString("amzn-ad-id", null));
    }

    public boolean isAdIdCurrent(AdvertisingIdentifier.Info info) {
        boolean zIsAdIdOriginatedFromNonAdvertisingIdentifier = isAdIdOriginatedFromNonAdvertisingIdentifier();
        if (!info.hasAdvertisingIdentifier()) {
            return zIsAdIdOriginatedFromNonAdvertisingIdentifier;
        }
        if (zIsAdIdOriginatedFromNonAdvertisingIdentifier) {
            return false;
        }
        return info.getAdvertisingIdentifier().equals(Settings.getInstance().getString("amzn-ad-id-origin", null));
    }

    public boolean hasAdId() {
        return !StringUtils.isNullOrEmpty(getAdId());
    }

    public void putAdId(String str, AdvertisingIdentifier.Info info) {
        Settings settings = Settings.getInstance();
        settings.putStringWithNoFlush("amzn-ad-id", str);
        setOrigin(info);
        settings.putBooleanWithNoFlush("newSISDIDRequested", false);
        settings.flush();
    }

    protected void setSISRegistrationStatus() {
        Settings.getInstance().putBoolean("sis_registration_status", true);
    }

    protected void removeAdId(AdvertisingIdentifier.Info info) {
        Settings settings = Settings.getInstance();
        settings.remove("amzn-ad-id");
        setOrigin(info);
        settings.putBooleanWithNoFlush("newSISDIDRequested", false);
        settings.flush();
    }

    protected static void setOrigin(AdvertisingIdentifier.Info info) {
        if (info.hasAdvertisingIdentifier()) {
            Settings.getInstance().putStringWithNoFlush("amzn-ad-id-origin", info.getAdvertisingIdentifier());
        } else {
            Settings.getInstance().putStringWithNoFlush("amzn-ad-id-origin", "non-advertising-identifier");
        }
    }

    public static boolean isAdIdOriginatedFromNonAdvertisingIdentifier() {
        String string = Settings.getInstance().getString("amzn-ad-id-origin", null);
        return string == null || "non-advertising-identifier".equals(string);
    }

    public String getAppKey() {
        return DebugProperties.getInstance().getDebugPropertyAsString("debug.appid", this.appKey);
    }

    public void putAppKey(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Application Key must not be null or empty.");
        }
        this.appKey = WebUtils.getURLEncodedString(str);
    }

    public void requestNewSISDeviceIdentifier() {
        Settings.getInstance().putBoolean("newSISDIDRequested", true);
    }

    public boolean shouldGetNewSISDeviceIdentifer() {
        return Settings.getInstance().getBoolean("newSISDIDRequested", false);
    }

    public boolean isRegisteredWithSIS() {
        return hasAdId();
    }

    public boolean shouldGetNewSISRegistration() {
        return !Settings.getInstance().getBoolean("sis_registration_status", false);
    }
}
