package com.amazon.device.ads;

import com.amazon.device.ads.AdvertisingIdentifier;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.WebRequest;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: compiled from: SISRequests.java */
/* JADX INFO: loaded from: classes2.dex */
abstract class SISDeviceRequest extends SISRequest {
    private AdvertisingIdentifier advertisingIdentifier;
    private AdvertisingIdentifier.Info advertisingIdentifierInfo;

    private static String convertOptOutBooleanToStringInt(boolean z) {
        return z ? "1" : "0";
    }

    @Override // com.amazon.device.ads.SISRequest
    public HashMap<String, String> getPostParameters() {
        return null;
    }

    SISDeviceRequest(MobileAdsLoggerFactory mobileAdsLoggerFactory, String str, Metrics.MetricType metricType, String str2, AdvertisingIdentifier advertisingIdentifier, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        super(mobileAdsLoggerFactory, str, metricType, str2, mobileAdsInfoStore, configuration);
        this.advertisingIdentifier = advertisingIdentifier;
        this.advertisingIdentifierInfo = advertisingIdentifier.getAdvertisingIdentifierInfo();
    }

    @Override // com.amazon.device.ads.SISRequest
    public WebRequest.QueryStringParameters getQueryParameters() {
        WebRequest.QueryStringParameters queryParameters = super.getQueryParameters();
        DeviceInfo deviceInfo = this.mobileAdsInfoStore.getDeviceInfo();
        queryParameters.putUnencoded("ua", deviceInfo.getUserAgentString());
        queryParameters.putUnencoded("dinfo", deviceInfo.getDInfoProperty().toString());
        if (this.advertisingIdentifierInfo.hasAdvertisingIdentifier()) {
            queryParameters.putUrlEncoded("oo", convertOptOutBooleanToStringInt(this.advertisingIdentifierInfo.isLimitAdTrackingEnabled()));
        }
        if (getConfiguration().isAmazonAdvertisingPublisher() && this.advertisingIdentifierInfo.hasAdvertisingIdentifier()) {
            queryParameters.putUrlEncoded("idfa", this.advertisingIdentifierInfo.getAdvertisingIdentifier());
        }
        String andClearTransition = this.advertisingIdentifier.getAndClearTransition();
        queryParameters.putUrlEncodedIfTrue("aidts", andClearTransition, andClearTransition != null);
        return queryParameters;
    }

    protected AdvertisingIdentifier.Info getAdvertisingIdentifierInfo() {
        return this.advertisingIdentifierInfo;
    }

    @Override // com.amazon.device.ads.SISRequest
    public void onResponseReceived(JSONObject jSONObject) {
        int integerFromJSON = JSONUtils.getIntegerFromJSON(jSONObject, "rcode", 0);
        this.mobileAdsInfoStore.getRegistrationInfo().setSISRegistrationStatus();
        if (integerFromJSON == 1) {
            String stringFromJSON = JSONUtils.getStringFromJSON(jSONObject, "adId", "");
            if (stringFromJSON.length() > 0) {
                this.mobileAdsInfoStore.getRegistrationInfo().putAdId(stringFromJSON, getAdvertisingIdentifierInfo());
                return;
            }
            return;
        }
        this.mobileAdsInfoStore.getRegistrationInfo().removeAdId(getAdvertisingIdentifierInfo());
        this.logger.d("No ad-id returned,gdpr consent not granted");
    }
}
