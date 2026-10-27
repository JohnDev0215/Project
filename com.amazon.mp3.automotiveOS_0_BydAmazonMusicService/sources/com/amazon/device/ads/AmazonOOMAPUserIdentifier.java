package com.amazon.device.ads;

import com.amazon.music.inappmessaging.internal.model.Splash;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class AmazonOOMAPUserIdentifier extends AdsIdentityUserIdentifier {
    private static final String LOGTAG = "AmazonOOMAPUserIdentifier";
    private final AuthenticationPlatformAdapter authenticationPlatform;
    private final String sessionId;

    @Deprecated
    public AmazonOOMAPUserIdentifier(AuthenticationPlatformAdapter authenticationPlatformAdapter, AdvertisingIdentifier advertisingIdentifier, Configuration configuration, Settings settings, TrustedPackageManager trustedPackageManager, SystemTime systemTime, DebugProperties debugProperties, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        super(advertisingIdentifier, configuration, settings, trustedPackageManager, systemTime, debugProperties, LOGTAG, "/RegisterOAuth", null, mobileAdsLoggerFactory);
        this.authenticationPlatform = authenticationPlatformAdapter;
        this.sessionId = null;
    }

    @Override // com.amazon.device.ads.AdsIdentityUserIdentifier, com.amazon.device.ads.UserIdentifier
    void processExtraParameters(JSONObject jSONObject) {
        super.processExtraParameters(jSONObject);
        JSONUtils.put(jSONObject, Splash.PARAMS_SESSION_ID, this.sessionId);
    }

    public String getAccount() {
        return this.authenticationPlatform.getAccountName();
    }

    @Override // com.amazon.device.ads.UserIdentifier
    WebRequest initializeWebRequest() {
        String account = getAccount();
        if (account == null) {
            return null;
        }
        return makeAuthenticatedWebRequest(account);
    }

    protected WebRequest makeAuthenticatedWebRequest(String str) {
        return this.authenticationPlatform.makeAuthenticatedWebRequest(str);
    }
}
