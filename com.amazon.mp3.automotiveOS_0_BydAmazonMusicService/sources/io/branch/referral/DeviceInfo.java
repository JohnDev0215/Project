package io.branch.referral;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import io.branch.referral.SystemObserver;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class DeviceInfo {
    private static DeviceInfo thisInstance_;
    private final Context context_;
    private final SystemObserver systemObserver_ = new SystemObserverInstance();

    static DeviceInfo initialize(Context context) {
        if (thisInstance_ == null) {
            thisInstance_ = new DeviceInfo(context);
        }
        return thisInstance_;
    }

    static DeviceInfo getInstance() {
        return thisInstance_;
    }

    private DeviceInfo(Context context) {
        this.context_ = context;
    }

    void updateRequestWithV1Params(JSONObject jSONObject) {
        try {
            SystemObserver.UniqueId hardwareID = getHardwareID();
            if (!isNullOrEmptyOrBlank(hardwareID.getId())) {
                jSONObject.put(Defines$Jsonkey.HardwareID.getKey(), hardwareID.getId());
                jSONObject.put(Defines$Jsonkey.IsHardwareIDReal.getKey(), hardwareID.isReal());
            }
            String phoneBrand = SystemObserver.getPhoneBrand();
            if (!isNullOrEmptyOrBlank(phoneBrand)) {
                jSONObject.put(Defines$Jsonkey.Brand.getKey(), phoneBrand);
            }
            String phoneModel = SystemObserver.getPhoneModel();
            if (!isNullOrEmptyOrBlank(phoneModel)) {
                jSONObject.put(Defines$Jsonkey.Model.getKey(), phoneModel);
            }
            DisplayMetrics screenDisplay = SystemObserver.getScreenDisplay(this.context_);
            jSONObject.put(Defines$Jsonkey.ScreenDpi.getKey(), screenDisplay.densityDpi);
            jSONObject.put(Defines$Jsonkey.ScreenHeight.getKey(), screenDisplay.heightPixels);
            jSONObject.put(Defines$Jsonkey.ScreenWidth.getKey(), screenDisplay.widthPixels);
            jSONObject.put(Defines$Jsonkey.WiFi.getKey(), SystemObserver.getWifiConnected(this.context_));
            jSONObject.put(Defines$Jsonkey.UIMode.getKey(), SystemObserver.getUIMode(this.context_));
            String os = SystemObserver.getOS(this.context_);
            if (!isNullOrEmptyOrBlank(os)) {
                jSONObject.put(Defines$Jsonkey.OS.getKey(), os);
            }
            jSONObject.put(Defines$Jsonkey.OSVersion.getKey(), SystemObserver.getOSVersion());
            String iSO2CountryCode = SystemObserver.getISO2CountryCode();
            if (!TextUtils.isEmpty(iSO2CountryCode)) {
                jSONObject.put(Defines$Jsonkey.Country.getKey(), iSO2CountryCode);
            }
            String iSO2LanguageCode = SystemObserver.getISO2LanguageCode();
            if (!TextUtils.isEmpty(iSO2LanguageCode)) {
                jSONObject.put(Defines$Jsonkey.Language.getKey(), iSO2LanguageCode);
            }
            String localIPAddress = SystemObserver.getLocalIPAddress();
            if (!TextUtils.isEmpty(localIPAddress)) {
                jSONObject.put(Defines$Jsonkey.LocalIP.getKey(), localIPAddress);
            }
            if (PrefHelper.getInstance(this.context_).shouldAddModules()) {
                String imei = SystemObserver.getImei(this.context_);
                if (isNullOrEmptyOrBlank(imei)) {
                    return;
                }
                jSONObject.put(Defines$ModuleNameKeys.imei.getKey(), imei);
            }
        } catch (JSONException unused) {
        }
    }

    void updateRequestWithV2Params(Context context, PrefHelper prefHelper, JSONObject jSONObject) {
        try {
            SystemObserver.UniqueId hardwareID = getHardwareID();
            if (!isNullOrEmptyOrBlank(hardwareID.getId()) && hardwareID.isReal()) {
                jSONObject.put(Defines$Jsonkey.AndroidID.getKey(), hardwareID.getId());
            } else {
                jSONObject.put(Defines$Jsonkey.UnidentifiedDevice.getKey(), true);
            }
            String phoneBrand = SystemObserver.getPhoneBrand();
            if (!isNullOrEmptyOrBlank(phoneBrand)) {
                jSONObject.put(Defines$Jsonkey.Brand.getKey(), phoneBrand);
            }
            String phoneModel = SystemObserver.getPhoneModel();
            if (!isNullOrEmptyOrBlank(phoneModel)) {
                jSONObject.put(Defines$Jsonkey.Model.getKey(), phoneModel);
            }
            DisplayMetrics screenDisplay = SystemObserver.getScreenDisplay(this.context_);
            jSONObject.put(Defines$Jsonkey.ScreenDpi.getKey(), screenDisplay.densityDpi);
            jSONObject.put(Defines$Jsonkey.ScreenHeight.getKey(), screenDisplay.heightPixels);
            jSONObject.put(Defines$Jsonkey.ScreenWidth.getKey(), screenDisplay.widthPixels);
            String os = SystemObserver.getOS(this.context_);
            if (!isNullOrEmptyOrBlank(os)) {
                jSONObject.put(Defines$Jsonkey.OS.getKey(), os);
            }
            jSONObject.put(Defines$Jsonkey.OSVersion.getKey(), SystemObserver.getOSVersion());
            String iSO2CountryCode = SystemObserver.getISO2CountryCode();
            if (!TextUtils.isEmpty(iSO2CountryCode)) {
                jSONObject.put(Defines$Jsonkey.Country.getKey(), iSO2CountryCode);
            }
            String iSO2LanguageCode = SystemObserver.getISO2LanguageCode();
            if (!TextUtils.isEmpty(iSO2LanguageCode)) {
                jSONObject.put(Defines$Jsonkey.Language.getKey(), iSO2LanguageCode);
            }
            String localIPAddress = SystemObserver.getLocalIPAddress();
            if (!TextUtils.isEmpty(localIPAddress)) {
                jSONObject.put(Defines$Jsonkey.LocalIP.getKey(), localIPAddress);
            }
            if (prefHelper != null) {
                if (!isNullOrEmptyOrBlank(prefHelper.getDeviceFingerPrintID())) {
                    jSONObject.put(Defines$Jsonkey.DeviceFingerprintID.getKey(), prefHelper.getDeviceFingerPrintID());
                }
                String identity = prefHelper.getIdentity();
                if (!isNullOrEmptyOrBlank(identity)) {
                    jSONObject.put(Defines$Jsonkey.DeveloperIdentity.getKey(), identity);
                }
            }
            if (prefHelper != null && prefHelper.shouldAddModules()) {
                String imei = SystemObserver.getImei(this.context_);
                if (!isNullOrEmptyOrBlank(imei)) {
                    jSONObject.put(Defines$ModuleNameKeys.imei.getKey(), imei);
                }
            }
            jSONObject.put(Defines$Jsonkey.AppVersion.getKey(), getAppVersion());
            jSONObject.put(Defines$Jsonkey.SDK.getKey(), "android");
            jSONObject.put(Defines$Jsonkey.SdkVersion.getKey(), "4.1.1");
            jSONObject.put(Defines$Jsonkey.UserAgent.getKey(), getDefaultBrowserAgent(context));
        } catch (JSONException unused) {
        }
    }

    void updateRequestWithAttributionWindow(PrefHelper prefHelper, JSONObject jSONObject) {
        if (prefHelper != null) {
            try {
                jSONObject.put(Defines$Jsonkey.LATDAttributionWindow.getKey(), prefHelper.getLATDAttributonWindow());
            } catch (JSONException unused) {
            }
        }
    }

    public String getAppVersion() {
        return SystemObserver.getAppVersion(this.context_);
    }

    public long getFirstInstallTime() {
        return SystemObserver.getFirstInstallTime(this.context_);
    }

    public long getLastUpdateTime() {
        return SystemObserver.getLastUpdateTime(this.context_);
    }

    public boolean isPackageInstalled() {
        return SystemObserver.isPackageInstalled(this.context_);
    }

    public static boolean isDebugHardwareIdNeeded() {
        return Branch.isDeviceIDFetchDisabled() || BranchUtil.isDebugEnabled();
    }

    public SystemObserver.UniqueId getHardwareID() {
        getSystemObserver();
        return SystemObserver.getUniqueID(this.context_, isDebugHardwareIdNeeded());
    }

    public String getOsName() {
        return SystemObserver.getOS(this.context_);
    }

    private String getDefaultBrowserAgent(Context context) {
        try {
            return WebSettings.getDefaultUserAgent(context);
        } catch (Exception unused) {
            return "";
        }
    }

    private class SystemObserverInstance extends SystemObserver {
        public SystemObserverInstance() {
        }
    }

    SystemObserver getSystemObserver() {
        return this.systemObserver_;
    }

    public static boolean isNullOrEmptyOrBlank(String str) {
        return TextUtils.isEmpty(str) || str.equals("bnc_no_value");
    }
}
