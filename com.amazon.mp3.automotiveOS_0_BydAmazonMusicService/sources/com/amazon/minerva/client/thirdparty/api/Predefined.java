package com.amazon.minerva.client.thirdparty.api;

/* JADX INFO: loaded from: classes3.dex */
public enum Predefined {
    SOFTWARE_VERSION("_softwareVersion"),
    SOFTWARE_VERSION_FINGERPRINT("_softwareVersionFingerprint"),
    OS_FILE_TAG("_osFileTag"),
    BUILD_TYPE("_buildType"),
    PLATFORM("_platform"),
    MODEL("_model"),
    HARDWARE("_hardware"),
    DEVICE_TYPE("_deviceType"),
    DEVICE_ID("_deviceId"),
    CUSTOMER_ID("_customerId"),
    MARKETPLACE_ID("_marketPlaceId"),
    COUNTRY_OF_RESIDENCE("_countryOfResidence"),
    DEVICE_LANGUAGE("_deviceLanguage"),
    OTA_GROUP_NAME("_otaGroupName"),
    TIME_ZONE("_timeZone");

    private final String key;

    Predefined(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }
}
