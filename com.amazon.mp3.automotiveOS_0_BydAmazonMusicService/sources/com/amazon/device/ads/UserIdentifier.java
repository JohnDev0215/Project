package com.amazon.device.ads;

import androidx.core.app.NotificationCompat;
import com.amazon.device.ads.AdvertisingIdentifier;
import com.amazon.device.ads.Configuration;
import com.amazon.device.ads.WebRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
abstract class UserIdentifier extends StartUpWaiter {
    private static final ReentrantLock serviceCallLock = new ReentrantLock();
    private final AdvertisingIdentifier advertisingIdentifier;
    private AdvertisingIdentifier.Info advertisingIdentifierInfo;
    private boolean allowEmptyIdfa;
    private final Configuration configuration;
    private final DebugProperties debugProperties;
    private MobileAdsInfoStore infoStore;
    private boolean isTest;
    private final ArrayList<IdentifyUserListener> listeners;
    private final MobileAdsLogger logger;
    private final String logtag;
    private final String path;
    protected final Settings settings;
    protected final SystemTime systemTime;
    private final TrustedPackageManager trustedPackageManager;
    private final WebRequest.WebRequestFactory webRequestFactory;

    interface IdentifyUserListener {
        void userIdentified();
    }

    protected enum IdentifyUserStatus {
        SUCCESS,
        FAILURE,
        REQUESTFAILED,
        REQUESTTHROTTLED
    }

    abstract WebRequest initializeWebRequest();

    abstract void processExtraParameters(JSONObject jSONObject);

    UserIdentifier(AdvertisingIdentifier advertisingIdentifier, Configuration configuration, Settings settings, TrustedPackageManager trustedPackageManager, SystemTime systemTime, DebugProperties debugProperties, String str, String str2, WebRequest.WebRequestFactory webRequestFactory, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        super(settings, configuration);
        this.allowEmptyIdfa = true;
        this.isTest = false;
        this.advertisingIdentifier = advertisingIdentifier;
        this.configuration = configuration;
        this.settings = settings;
        this.trustedPackageManager = trustedPackageManager;
        this.systemTime = systemTime;
        this.debugProperties = debugProperties;
        this.logtag = str;
        this.path = str2;
        this.webRequestFactory = webRequestFactory;
        MobileAdsLogger mobileAdsLoggerCreateMobileAdsLogger = mobileAdsLoggerFactory.createMobileAdsLogger(str);
        this.logger = mobileAdsLoggerCreateMobileAdsLogger;
        this.listeners = new ArrayList<>();
        this.isTest = mobileAdsLoggerCreateMobileAdsLogger.getClass().getName().contains("Mockito");
    }

    public boolean identifyUser() {
        WebRequest webRequestCreateWebRequest;
        WebRequest.WebResponse webResponseMakeCall;
        JSONObject asJSON;
        JSONObject jSONObjectCreateParameters = createParameters();
        if (jSONObjectCreateParameters == null || (webRequestCreateWebRequest = createWebRequest(jSONObjectCreateParameters)) == null) {
            return false;
        }
        updateLastAttemptTimeStamp();
        try {
            webResponseMakeCall = webRequestCreateWebRequest.makeCall();
            this.logger.d("Service call result: %d", Integer.valueOf(webResponseMakeCall.getHttpStatusCode()));
        } catch (WebRequest.WebRequestException unused) {
        }
        if (!webResponseMakeCall.isHttpStatusCodeOK() || (asJSON = webResponseMakeCall.getResponseReader().readAsJSON()) == null) {
            return false;
        }
        String stringFromJSON = JSONUtils.getStringFromJSON(asJSON, "code", null);
        String stringFromJSON2 = JSONUtils.getStringFromJSON(asJSON, NotificationCompat.CATEGORY_STATUS, null);
        this.settings.putJSONObject("IUParameters", jSONObjectCreateParameters);
        this.logger.d("Service result - code: %s, status: %s", stringFromJSON, stringFromJSON2);
        return stringFromJSON != null && (stringFromJSON.equals("200") || (stringFromJSON.equals("400") && "Invalid Input".equals(stringFromJSON2)));
    }

    private WebRequest createWebRequest(JSONObject jSONObject) {
        WebRequest webRequestInitializeWebRequest = initializeWebRequest();
        if (webRequestInitializeWebRequest == null) {
            return null;
        }
        webRequestInitializeWebRequest.setExternalLogTag(this.logtag);
        webRequestInitializeWebRequest.convertToJSONPostRequest();
        webRequestInitializeWebRequest.enableLog(true);
        webRequestInitializeWebRequest.enableLogSessionID(this.configuration.getBooleanWithDefault(Configuration.ConfigOption.WHITELISTED_CUSTOMER, false));
        webRequestInitializeWebRequest.setPath(this.path);
        setParametersAndHost(webRequestInitializeWebRequest, jSONObject);
        return webRequestInitializeWebRequest;
    }

    public void setMobileAdsInfoStore(MobileAdsInfoStore mobileAdsInfoStore) {
        this.infoStore = mobileAdsInfoStore;
    }

    public void executeRequest() {
        start();
    }

    @Override // com.amazon.device.ads.StartUpWaiter
    protected void startUpReady() {
        ReentrantLock reentrantLock = serviceCallLock;
        reentrantLock.lock();
        try {
            try {
                if (this.isTest) {
                    if (!this.configuration.hasValue(Configuration.ConfigOption.MADS_HOSTNAME) || !getAdvertisingIdentifierInfo().canDo()) {
                        this.logger.d("Advertising Info prevents executing identity call");
                        notifyListeners(IdentifyUserStatus.REQUESTFAILED);
                        reentrantLock.unlock();
                        return;
                    }
                } else if (!getAdvertisingIdentifierInfo().canDo()) {
                    this.logger.d("Advertising Info prevents executing identity call");
                    notifyListeners(IdentifyUserStatus.REQUESTFAILED);
                    reentrantLock.unlock();
                    return;
                }
                if (this.debugProperties.getDebugPropertyAsBoolean("debug.shouldIdentifyUser", Boolean.valueOf(shouldIdentifyUser())).booleanValue()) {
                    if (identifyUser()) {
                        updateLastCheckInTime();
                        this.logger.d("Identify user call succeeded");
                        notifyListeners(IdentifyUserStatus.SUCCESS);
                    } else {
                        this.logger.e("Identify user call failed");
                        notifyListeners(IdentifyUserStatus.FAILURE);
                    }
                } else {
                    notifyListeners(IdentifyUserStatus.REQUESTTHROTTLED);
                }
            } catch (Exception unused) {
                notifyListeners(IdentifyUserStatus.REQUESTFAILED);
                reentrantLock = serviceCallLock;
            }
            reentrantLock.unlock();
        } catch (Throwable th) {
            serviceCallLock.unlock();
            throw th;
        }
    }

    @Override // com.amazon.device.ads.StartUpWaiter
    protected void startUpFailed() {
        notifyListeners(IdentifyUserStatus.REQUESTFAILED);
    }

    protected AdvertisingIdentifier.Info getAdvertisingIdentifierInfo() {
        if (this.advertisingIdentifierInfo == null) {
            this.advertisingIdentifierInfo = this.advertisingIdentifier.getAdvertisingIdentifierInfo();
        }
        return this.advertisingIdentifierInfo;
    }

    protected final JSONObject createParameters() {
        JSONObject jSONObject = new JSONObject();
        RegistrationInfo registrationInfo = this.infoStore.getRegistrationInfo();
        DeviceInfo deviceInfo = this.infoStore.getDeviceInfo();
        AppInfo appInfo = this.infoStore.getAppInfo();
        JSONUtils.put(jSONObject, "dt", "android");
        JSONUtils.put(jSONObject, "app", registrationInfo.getAppName());
        JSONUtils.put(jSONObject, "aud", this.configuration.getString(Configuration.ConfigOption.SIS_DOMAIN));
        JSONUtils.put(jSONObject, "appId", registrationInfo.getAppKey());
        JSONUtils.put(jSONObject, "sdkVer", Version.getSDKVersion());
        JSONUtils.put(jSONObject, "fp_consent", this.configuration.getCookieConsent());
        if (getAdvertisingIdentifierInfo().hasAdvertisingIdentifier()) {
            JSONUtils.put(jSONObject, "oo", Boolean.toString(getAdvertisingIdentifierInfo().isLimitAdTrackingEnabled()));
        }
        if (this.configuration.isAmazonAdvertisingPublisher()) {
            if (getAdvertisingIdentifierInfo().hasAdvertisingIdentifier()) {
                JSONUtils.put(jSONObject, "idfa", getAdvertisingIdentifierInfo().getAdvertisingIdentifier());
            } else {
                String lastKnownIdfa = AdvertisingIdentifier.getLastKnownIdfa();
                if (lastKnownIdfa != null && !lastKnownIdfa.isEmpty()) {
                    JSONUtils.put(jSONObject, "idfa", lastKnownIdfa);
                } else if (!this.allowEmptyIdfa && new GooglePlayServicesAdapter().getAdvertisingIdentifierInfo().isGPSAvailable()) {
                    return null;
                }
            }
        }
        JSONUtils.put(jSONObject, "ua", deviceInfo.getUserAgentString());
        JSONUtils.put(jSONObject, "dinfo", deviceInfo.getDInfoProperty().toString());
        JSONUtils.put(jSONObject, "pkg", appInfo.getPackageInfoJSONString());
        processExtraParameters(jSONObject);
        return jSONObject;
    }

    protected final void setParametersAndHost(WebRequest webRequest, JSONObject jSONObject) {
        String string = this.configuration.getString(Configuration.ConfigOption.MADS_HOSTNAME);
        if (string == null) {
            string = "mads.amazon.com";
        }
        webRequest.setHost(string);
        webRequest.setUseSecure(this.debugProperties.getDebugPropertyAsBoolean("debug.madsUseSecure", Boolean.TRUE).booleanValue());
        webRequest.setRequestBodyString(jSONObject.toString());
    }

    protected void notifyListeners(IdentifyUserStatus identifyUserStatus) {
        if (identifyUserStatus == IdentifyUserStatus.SUCCESS) {
            Iterator<IdentifyUserListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().userIdentified();
            }
        }
    }

    protected boolean shouldIdentifyUser() throws JSONException {
        return checkTimeThrottling("amzn-ad-iu-last-checkin", Configuration.ConfigOption.IDENTIFY_USER_INTERVAL, 86400000L) && checkTimeThrottling("amzn-ad-iu-last-attempt", Configuration.ConfigOption.IDENTIFY_USER_RETRY_INTERVAL, 3600000L);
    }

    protected boolean checkTimeThrottling(String str, Configuration.ConfigOption configOption, long j) {
        long jCurrentTimeMillis = this.systemTime.currentTimeMillis();
        long writtenLong = this.settings.getWrittenLong(str, 0L);
        if (this.configuration.getLong(configOption) > 0) {
            j = this.configuration.getLong(configOption);
        }
        return jCurrentTimeMillis > writtenLong + j;
    }

    protected void updateLastCheckInTime() {
        this.settings.putLong("amzn-ad-iu-last-checkin", this.systemTime.currentTimeMillis());
    }

    protected void updateLastAttemptTimeStamp() {
        this.settings.putLong("amzn-ad-iu-last-attempt", this.systemTime.currentTimeMillis());
    }
}
