package com.amazon.device.ads;

import android.content.SharedPreferences;
import com.amazon.device.ads.AdvertisingIdentifier;
import com.amazon.device.ads.Configuration;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.WebRequest;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: SISRequests.java */
/* JADX INFO: loaded from: classes2.dex */
abstract class SISRequest {
    private final Metrics.MetricType callMetricType;
    private final Configuration configuration;
    private final String logTag;
    protected final MobileAdsLogger logger;
    protected MobileAdsInfoStore mobileAdsInfoStore;
    private final String path;

    /* JADX INFO: compiled from: SISRequests.java */
    enum SISDeviceRequestType {
        GENERATE_DID,
        UPDATE_DEVICE_INFO
    }

    abstract HashMap<String, String> getPostParameters();

    abstract void onResponseReceived(JSONObject jSONObject);

    SISRequest(MobileAdsLoggerFactory mobileAdsLoggerFactory, String str, Metrics.MetricType metricType, String str2, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        this.logTag = str;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(str);
        this.callMetricType = metricType;
        this.path = str2;
        this.mobileAdsInfoStore = mobileAdsInfoStore;
        this.configuration = configuration;
    }

    MobileAdsLogger getLogger() {
        return this.logger;
    }

    String getLogTag() {
        return this.logTag;
    }

    Metrics.MetricType getCallMetricType() {
        return this.callMetricType;
    }

    String getPath() {
        return this.path;
    }

    Configuration getConfiguration() {
        return this.configuration;
    }

    WebRequest.QueryStringParameters getQueryParameters() {
        WebRequest.QueryStringParameters queryStringParameters = new WebRequest.QueryStringParameters();
        queryStringParameters.putUrlEncoded("dt", this.mobileAdsInfoStore.getDeviceInfo().getDeviceType());
        queryStringParameters.putUrlEncoded("app", this.mobileAdsInfoStore.getRegistrationInfo().getAppName());
        queryStringParameters.putUrlEncoded("appId", this.mobileAdsInfoStore.getRegistrationInfo().getAppKey());
        queryStringParameters.putUrlEncoded("sdkVer", Version.getSDKVersion());
        queryStringParameters.putUrlEncoded("aud", this.configuration.getString(Configuration.ConfigOption.SIS_DOMAIN));
        queryStringParameters.putUnencoded("pkg", this.mobileAdsInfoStore.getAppInfo().getPackageInfoJSONString());
        queryStringParameters.putUrlEncoded("fp_consent", this.configuration.getCookieConsent());
        if (this.mobileAdsInfoStore.isContextReceived()) {
            ApplicationDefaultPreferences.initialize(this.mobileAdsInfoStore.getApplicationContext());
        }
        SharedPreferences defaultPreferences = ApplicationDefaultPreferences.getDefaultPreferences();
        if (defaultPreferences != null) {
            GDPRInfo gDPRInfo = new GDPRInfo(defaultPreferences);
            queryStringParameters.putUrlEncoded("gdpr", gDPRInfo.getGdprSubjectTcf2());
            queryStringParameters.putUrlEncoded("gdpr_consent", gDPRInfo.getGdprConsent_Tcf2());
        }
        return queryStringParameters;
    }

    /* JADX INFO: compiled from: SISRequests.java */
    static class SISRequestFactory {
        SISRequestFactory() {
        }

        public SISDeviceRequest createDeviceRequest(SISDeviceRequestType sISDeviceRequestType, AdvertisingIdentifier advertisingIdentifier) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$ads$SISRequest$SISDeviceRequestType[sISDeviceRequestType.ordinal()];
            if (i == 1) {
                return new SISGenerateDIDRequest(advertisingIdentifier);
            }
            if (i == 2) {
                return new SISUpdateDeviceInfoRequest(advertisingIdentifier);
            }
            throw new IllegalArgumentException("SISRequestType " + sISDeviceRequestType + " is not a SISDeviceRequest");
        }

        public SISRegisterEventRequest createRegisterEventRequest(AdvertisingIdentifier.Info info, JSONArray jSONArray) {
            return new SISRegisterEventRequest(info, jSONArray);
        }
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.SISRequest$1, reason: invalid class name */
    /* JADX INFO: compiled from: SISRequests.java */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$SISRequest$SISDeviceRequestType;

        static {
            int[] iArr = new int[SISDeviceRequestType.values().length];
            $SwitchMap$com$amazon$device$ads$SISRequest$SISDeviceRequestType = iArr;
            try {
                iArr[SISDeviceRequestType.GENERATE_DID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SISRequest$SISDeviceRequestType[SISDeviceRequestType.UPDATE_DEVICE_INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
