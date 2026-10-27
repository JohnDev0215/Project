package com.amazon.device.ads;

import com.amazon.device.ads.Metrics;

/* JADX INFO: compiled from: SISRequests.java */
/* JADX INFO: loaded from: classes2.dex */
class SISGenerateDIDRequest extends SISDeviceRequest {
    private static final Metrics.MetricType CALL_METRIC_TYPE = Metrics.MetricType.SIS_LATENCY_REGISTER;
    private static final String LOGTAG = "SISGenerateDIDRequest";

    public SISGenerateDIDRequest(AdvertisingIdentifier advertisingIdentifier) {
        this(advertisingIdentifier, MobileAdsInfoStore.getInstance(), Configuration.getInstance());
    }

    SISGenerateDIDRequest(AdvertisingIdentifier advertisingIdentifier, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        super(new MobileAdsLoggerFactory(), LOGTAG, CALL_METRIC_TYPE, "/generate_did", advertisingIdentifier, mobileAdsInfoStore, configuration);
    }
}
