package com.amazon.device.ads;

import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.WebRequest;
import org.json.JSONObject;

/* JADX INFO: compiled from: SISRequests.java */
/* JADX INFO: loaded from: classes2.dex */
class SISUpdateDeviceInfoRequest extends SISDeviceRequest {
    private static final Metrics.MetricType CALL_METRIC_TYPE = Metrics.MetricType.SIS_LATENCY_UPDATE_DEVICE_INFO;
    private final DebugProperties debugPropertes;
    private final Metrics metrics;

    public SISUpdateDeviceInfoRequest(AdvertisingIdentifier advertisingIdentifier) {
        this(advertisingIdentifier, MobileAdsInfoStore.getInstance(), Configuration.getInstance(), DebugProperties.getInstance(), Metrics.getInstance());
    }

    SISUpdateDeviceInfoRequest(AdvertisingIdentifier advertisingIdentifier, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration, DebugProperties debugProperties, Metrics metrics) {
        super(new MobileAdsLoggerFactory(), "SISUpdateDeviceInfoRequest", CALL_METRIC_TYPE, "/update_dev_info", advertisingIdentifier, mobileAdsInfoStore, configuration);
        this.debugPropertes = debugProperties;
        this.metrics = metrics;
    }

    @Override // com.amazon.device.ads.SISDeviceRequest, com.amazon.device.ads.SISRequest
    public WebRequest.QueryStringParameters getQueryParameters() {
        WebRequest.QueryStringParameters queryParameters = super.getQueryParameters();
        String debugPropertyAsString = getConfiguration().isAmazonAdvertisingPublisher() ? this.debugPropertes.getDebugPropertyAsString("debug.adid", getAdvertisingIdentifierInfo().getSISDeviceIdentifier()) : null;
        if (!StringUtils.isNullOrEmpty(debugPropertyAsString)) {
            queryParameters.putUrlEncoded("adId", debugPropertyAsString);
        }
        return queryParameters;
    }

    @Override // com.amazon.device.ads.SISDeviceRequest, com.amazon.device.ads.SISRequest
    public void onResponseReceived(JSONObject jSONObject) {
        super.onResponseReceived(jSONObject);
        if (JSONUtils.getBooleanFromJSON(jSONObject, "idChanged", false)) {
            this.metrics.getMetricsCollector().incrementMetric(Metrics.MetricType.SIS_COUNTER_IDENTIFIED_DEVICE_CHANGED);
        }
    }
}
