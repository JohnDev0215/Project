package com.amazon.device.ads;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.autofill.HintConstants;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class DeviceInfo {
    private static final String LOGTAG = "DeviceInfo";
    private final AndroidBuildInfo buildInfo;
    private String carrier;
    private String country;
    private final MobileAdsInfoStore infoStore;
    private String language;
    private final MobileAdsLogger logger;
    private String make;
    private String model;
    private String networkCountry;
    private String osVersion;
    private float scalingFactor;
    private String scalingFactorAsString;
    private UserAgentManager userAgentManager;

    public String getDeviceType() {
        return "android";
    }

    public String getOS() {
        return "Android";
    }

    public DeviceInfo(Context context, UserAgentManager userAgentManager) {
        this(context, userAgentManager, MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), new AndroidBuildInfo());
    }

    DeviceInfo(Context context, UserAgentManager userAgentManager, MobileAdsInfoStore mobileAdsInfoStore, MobileAdsLoggerFactory mobileAdsLoggerFactory, AndroidBuildInfo androidBuildInfo) {
        this.make = Build.MANUFACTURER;
        this.model = Build.MODEL;
        this.osVersion = Build.VERSION.RELEASE;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.infoStore = mobileAdsInfoStore;
        this.buildInfo = androidBuildInfo;
        setCountry();
        setNetworkCountry(context);
        setCarrier(context);
        setLanguage();
        setScalingFactor(context);
        this.userAgentManager = userAgentManager;
    }

    private void setLanguage() {
        String language = Locale.getDefault().getLanguage();
        if (language == null || language.length() <= 0) {
            language = null;
        }
        this.language = language;
    }

    private void setCountry() {
        String country = Locale.getDefault().getCountry();
        if (country == null || country.length() <= 0) {
            country = null;
        }
        this.country = country;
    }

    private void setNetworkCountry(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        if (telephonyManager != null) {
            String networkCountryIso = telephonyManager.getNetworkCountryIso();
            this.networkCountry = (networkCountryIso == null || networkCountryIso.length() <= 0) ? null : networkCountryIso.toUpperCase();
        }
    }

    private void setCarrier(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        if (telephonyManager != null) {
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName == null || networkOperatorName.length() <= 0) {
                networkOperatorName = null;
            }
            this.carrier = networkOperatorName;
        }
    }

    private void setScalingFactor(Context context) {
        if (this.make.equals("motorola") && this.model.equals("MB502")) {
            this.scalingFactor = 1.0f;
        } else {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            this.scalingFactor = displayMetrics.scaledDensity;
        }
        this.scalingFactorAsString = Float.toString(this.scalingFactor);
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getOSVersion() {
        return this.osVersion;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public String getCountry() {
        return this.country;
    }

    public String getNetworkCountry() {
        return this.networkCountry;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getScalingFactorAsString() {
        return this.scalingFactorAsString;
    }

    public float getScalingFactorAsFloat() {
        return this.scalingFactor;
    }

    public String getUserAgentString() {
        return this.userAgentManager.getUserAgentString();
    }

    public JSONObject getDInfoProperty() {
        JSONObject jSONObject = new JSONObject();
        JSONUtils.put(jSONObject, "make", getMake());
        JSONUtils.put(jSONObject, "model", getModel());
        JSONUtils.put(jSONObject, "os", getOS());
        JSONUtils.put(jSONObject, "osVersion", getOSVersion());
        JSONUtils.put(jSONObject, "scalingFactor", getScalingFactorAsString());
        JSONUtils.put(jSONObject, "language", getLanguage());
        JSONUtils.put(jSONObject, "country", getCountry());
        JSONUtils.put(jSONObject, "networkCountry", getNetworkCountry());
        JSONUtils.put(jSONObject, "carrier", getCarrier());
        return jSONObject;
    }
}
