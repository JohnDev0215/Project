package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
class WebRequestUserId {
    private final AdvertisingIdParameter adIdParam;
    private final Settings settings;

    public WebRequestUserId() {
        this(Settings.getInstance(), new AdvertisingIdParameter());
    }

    WebRequestUserId(Settings settings, AdvertisingIdParameter advertisingIdParameter) {
        this.settings = settings;
        this.adIdParam = advertisingIdParameter;
    }
}
