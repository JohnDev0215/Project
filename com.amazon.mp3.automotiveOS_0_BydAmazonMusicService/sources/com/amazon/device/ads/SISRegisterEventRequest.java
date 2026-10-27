package com.amazon.device.ads;

import androidx.core.app.NotificationCompat;
import com.amazon.device.ads.AdvertisingIdentifier;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.WebRequest;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: SISRequests.java */
/* JADX INFO: loaded from: classes2.dex */
class SISRegisterEventRequest extends SISRequest {
    private static final Metrics.MetricType CALL_METRIC_TYPE = Metrics.MetricType.SIS_LATENCY_REGISTER_EVENT;
    private final AdvertisingIdentifier.Info advertisingIdentifierInfo;
    private final AppEventRegistrationHandler appEventRegistrationHandler;
    private final JSONArray appEvents;

    public SISRegisterEventRequest(AdvertisingIdentifier.Info info, JSONArray jSONArray) {
        this(info, jSONArray, AppEventRegistrationHandler.getInstance(), new MobileAdsLoggerFactory(), MobileAdsInfoStore.getInstance(), Configuration.getInstance());
    }

    SISRegisterEventRequest(AdvertisingIdentifier.Info info, JSONArray jSONArray, AppEventRegistrationHandler appEventRegistrationHandler, MobileAdsLoggerFactory mobileAdsLoggerFactory, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        super(mobileAdsLoggerFactory, "SISRegisterEventRequest", CALL_METRIC_TYPE, "/register_event", mobileAdsInfoStore, configuration);
        this.advertisingIdentifierInfo = info;
        this.appEvents = jSONArray;
        this.appEventRegistrationHandler = appEventRegistrationHandler;
    }

    @Override // com.amazon.device.ads.SISRequest
    public WebRequest.QueryStringParameters getQueryParameters() {
        WebRequest.QueryStringParameters queryParameters = super.getQueryParameters();
        if (getConfiguration().isAmazonAdvertisingPublisher()) {
            queryParameters.putUrlEncoded("adId", this.advertisingIdentifierInfo.getSISDeviceIdentifier());
        }
        return queryParameters;
    }

    @Override // com.amazon.device.ads.SISRequest
    public HashMap<String, String> getPostParameters() {
        HashMap<String, String> map = new HashMap<>();
        map.put("events", this.appEvents.toString());
        return map;
    }

    @Override // com.amazon.device.ads.SISRequest
    public void onResponseReceived(JSONObject jSONObject) {
        int integerFromJSON = JSONUtils.getIntegerFromJSON(jSONObject, "rcode", 0);
        String stringFromJSON = JSONUtils.getStringFromJSON(jSONObject, NotificationCompat.CATEGORY_MESSAGE, "");
        if (integerFromJSON == 1 || integerFromJSON == 103 || (integerFromJSON == 101 && stringFromJSON.equals("103"))) {
            this.logger.d("Application events registered successfully.");
            this.appEventRegistrationHandler.onAppEventsRegistered();
            if (integerFromJSON == 103 || integerFromJSON == 101) {
                this.mobileAdsInfoStore.getRegistrationInfo().removeAdId(this.advertisingIdentifierInfo);
                this.logger.d("gdpr consent not granted");
                return;
            }
            return;
        }
        this.logger.d("Application events not registered. rcode:" + integerFromJSON);
    }
}
