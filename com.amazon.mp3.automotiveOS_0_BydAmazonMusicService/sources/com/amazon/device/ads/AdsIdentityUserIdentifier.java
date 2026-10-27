package com.amazon.device.ads;

import com.amazon.device.ads.WebRequest;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
abstract class AdsIdentityUserIdentifier extends UserIdentifier {
    private boolean calledFromIdModule;

    AdsIdentityUserIdentifier(AdvertisingIdentifier advertisingIdentifier, Configuration configuration, Settings settings, TrustedPackageManager trustedPackageManager, SystemTime systemTime, DebugProperties debugProperties, String str, String str2, WebRequest.WebRequestFactory webRequestFactory, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        super(advertisingIdentifier, configuration, settings, trustedPackageManager, systemTime, debugProperties, str, str2, webRequestFactory, mobileAdsLoggerFactory);
        this.calledFromIdModule = false;
    }

    @Override // com.amazon.device.ads.UserIdentifier
    void processExtraParameters(JSONObject jSONObject) {
        if (this.calledFromIdModule) {
            JSONUtils.put(jSONObject, "idModule", true);
        }
    }
}
