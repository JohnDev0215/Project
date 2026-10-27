package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
class AdvertisingIdParameter {
    private final AdvertisingIdentifier advertisingIdentifier;
    private final DebugProperties debugProperties;
    private DeviceInfo deviceInfo;
    private final Settings settings;

    public AdvertisingIdParameter() {
        this(new AdvertisingIdentifier(), Settings.getInstance(), DebugProperties.getInstance(), MobileAdsInfoStore.getInstance().getDeviceInfo());
    }

    AdvertisingIdParameter(AdvertisingIdentifier advertisingIdentifier, Settings settings, DebugProperties debugProperties, DeviceInfo deviceInfo) {
        this.advertisingIdentifier = advertisingIdentifier;
        this.settings = settings;
        this.debugProperties = debugProperties;
        this.deviceInfo = deviceInfo;
    }
}
