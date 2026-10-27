package com.amazon.mp3.service.metrics;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import androidx.autofill.HintConstants;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.service.DigitalMusicLicensingService;
import com.amazon.mp3.util.AdvertisingUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.util.DateUtils;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import org.json.JSONObject;

/* JADX INFO: compiled from: AppsflyerAppstartMetricLogger.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b5\u00106J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0002H\u0003J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u001a\u001a\u00020\u0006H\u0002J\u0010\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u001c\u001a\u00020\u0006H\u0002J\b\u0010\u001d\u001a\u00020\u0006H\u0002J\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010 \u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010!\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\"\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010#\u001a\u00020\u0006H\u0002J\u0010\u0010$\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010&\u001a\u00020%2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010'\u001a\u00020%2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002R\u001c\u0010)\u001a\n (*\u0004\u0018\u00010\u00060\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010,\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0018\u0010.\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010-R\u0018\u00100\u001a\u0004\u0018\u00010/8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101R\u0018\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104¨\u00067"}, d2 = {"Lcom/amazon/mp3/service/metrics/AppsflyerAppstartMetricLogger;", "", "Landroid/content/Context;", "context", "", "logAppStartEvent", "", "getCompassEndpointURL", "getEndpointURL", "Lorg/json/JSONObject;", "buildAppStartEvent", "getLocale", "getInstallDate", "Ljava/util/Date;", "dateTime", "convertCurrentUtcIso8601TimeWithoutZ", "convertCurrentUtcIso8601Time", "", "getAppStartCount", "Landroid/content/SharedPreferences;", "getSharedPref", "getAndroidInstallReferrer", "getDeviceId", "getDeviceType", "getUserAgent", "getObfuscatedMarketplaceId", "getApplicationName", "getApplicationVersion", "getOperatingSystemName", "getOperatingSystemVersion", "getNetworkCarrier", "getNetworkOperator", "getLicenseInfo", "getDeviceData", "getInstallAppStore", "getPreinstallInfo", "getInstallReferrer", "", "getClickTS", "getInstallBeginTS", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Ljava/text/DateFormat;", "DATE_FORMAT_WITHOUT_Z", "Ljava/text/DateFormat;", "DATE_FORMAT_WITH_Z", "Ljava/util/TimeZone;", "TIME_ZONE", "Ljava/util/TimeZone;", "Lcom/amazon/music/featuregating/configuration/Configuration;", "configuration", "Lcom/amazon/music/featuregating/configuration/Configuration;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AppsflyerAppstartMetricLogger {
    private static DateFormat DATE_FORMAT_WITHOUT_Z;
    private static DateFormat DATE_FORMAT_WITH_Z;
    public static final AppsflyerAppstartMetricLogger INSTANCE = new AppsflyerAppstartMetricLogger();
    private static final String TAG = AppsflyerAppstartMetricLogger.class.getName();
    private static TimeZone TIME_ZONE = TimeZone.getTimeZone("UTC");
    private static Configuration configuration;

    private final String getApplicationName() {
        return "DigitalMusicAndroid3P";
    }

    private final String getOperatingSystemName() {
        return "Android";
    }

    private AppsflyerAppstartMetricLogger() {
    }

    static {
        Locale locale = Locale.US;
        DATE_FORMAT_WITHOUT_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", locale);
        DATE_FORMAT_WITH_Z = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN, locale);
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof ConfigurationProvider)) {
            interfaceProvider = null;
        }
        ConfigurationProvider configurationProvider = (ConfigurationProvider) interfaceProvider;
        configuration = configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null;
    }

    @JvmStatic
    public static final boolean logAppStartEvent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AppsflyerAppstartMetricLogger appsflyerAppstartMetricLogger = INSTANCE;
        JSONObject jSONObjectBuildAppStartEvent = appsflyerAppstartMetricLogger.buildAppStartEvent(context);
        String str = TAG;
        Log.debug(str, jSONObjectBuildAppStartEvent.toString());
        RequestQueue requestQueueNewRequestQueue = Volley.newRequestQueue(context);
        requestQueueNewRequestQueue.add(new JsonObjectRequest(1, appsflyerAppstartMetricLogger.getEndpointURL(context), jSONObjectBuildAppStartEvent, new Response.Listener() { // from class: com.amazon.mp3.service.metrics.AppsflyerAppstartMetricLogger$$ExternalSyntheticLambda1
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                AppsflyerAppstartMetricLogger.m1622logAppStartEvent$lambda0((JSONObject) obj);
            }
        }, new Response.ErrorListener() { // from class: com.amazon.mp3.service.metrics.AppsflyerAppstartMetricLogger$$ExternalSyntheticLambda2
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                AppsflyerAppstartMetricLogger.m1623logAppStartEvent$lambda1(volleyError);
            }
        }));
        requestQueueNewRequestQueue.add(new JsonObjectRequest(1, appsflyerAppstartMetricLogger.getCompassEndpointURL(context), jSONObjectBuildAppStartEvent, new Response.Listener() { // from class: com.amazon.mp3.service.metrics.AppsflyerAppstartMetricLogger$$ExternalSyntheticLambda3
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                AppsflyerAppstartMetricLogger.m1624logAppStartEvent$lambda2((JSONObject) obj);
            }
        }, new Response.ErrorListener() { // from class: com.amazon.mp3.service.metrics.AppsflyerAppstartMetricLogger$$ExternalSyntheticLambda4
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                AppsflyerAppstartMetricLogger.m1625logAppStartEvent$lambda3(volleyError);
            }
        }));
        Log.debug(str, "Finished logging Appsflyer AppStart event");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: logAppStartEvent$lambda-0, reason: not valid java name */
    public static final void m1622logAppStartEvent$lambda0(JSONObject jSONObject) {
        Log.debug(TAG, "Success: post app start event");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: logAppStartEvent$lambda-1, reason: not valid java name */
    public static final void m1623logAppStartEvent$lambda1(VolleyError volleyError) {
        Log.error(TAG, "Error: post app start event");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: logAppStartEvent$lambda-2, reason: not valid java name */
    public static final void m1624logAppStartEvent$lambda2(JSONObject jSONObject) {
        Log.debug(TAG, "Success: post app start event to Compass");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: logAppStartEvent$lambda-3, reason: not valid java name */
    public static final void m1625logAppStartEvent$lambda3(VolleyError volleyError) {
        Log.error(TAG, "Error: post app start event to Compass");
    }

    private final String getCompassEndpointURL(Context context) {
        String strString;
        String strString2;
        Configuration configuration2 = configuration;
        String str = "https://t2k4oiwi0i.execute-api.us-east-1.amazonaws.com/prod";
        if (configuration2 != null && (strString2 = configuration2.string("appsflyer_compass_prod_url")) != null) {
            str = strString2;
        }
        if ((context.getApplicationInfo().flags & 2) == 0) {
            return str;
        }
        Configuration configuration3 = configuration;
        return (configuration3 == null || (strString = configuration3.string("appsflyer_compass_gamma_url")) == null) ? "https://c3ep9tiny8.execute-api.us-east-1.amazonaws.com/prod" : strString;
    }

    private final String getEndpointURL(Context context) {
        String strString;
        String strString2;
        Configuration configuration2 = configuration;
        String str = "https://aj1dlvksl5.execute-api.us-east-1.amazonaws.com/prod";
        if (configuration2 != null && (strString2 = configuration2.string("appsflyer_dmmap_prod_url")) != null) {
            str = strString2;
        }
        if ((context.getApplicationInfo().flags & 2) == 0) {
            return str;
        }
        Configuration configuration3 = configuration;
        return (configuration3 == null || (strString = configuration3.string("appsflyer_dmmap_gamma_url")) == null) ? "https://f6mt140a2c.execute-api.us-east-1.amazonaws.com/prod" : strString;
    }

    private final JSONObject buildAppStartEvent(Context context) {
        String adId;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Splash.PARAMS_LOCALE, getLocale(context));
        jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, getDeviceType(context));
        AdvertisingUtil.AndroidAdvertisingInfo adInfo = AdvertisingUtil.INSTANCE.getAdInfo(context);
        jSONObject.put("is_advertising_id_enabled", !(adInfo == null ? true : adInfo.getIsLimitAdTrackingEnabled()));
        if (adInfo == null || (adId = adInfo.getAdId()) == null) {
            adId = "";
        }
        jSONObject.put("android_advertising_id", adId);
        jSONObject.put("install_date_time", getInstallDate(context));
        jSONObject.put("bundle_id", context.getPackageName());
        jSONObject.put("android_install_referrer", getAndroidInstallReferrer(context));
        jSONObject.put("android_id", getDeviceId(context));
        jSONObject.put("android_facebook_cookie", "");
        jSONObject.put("app_open_counter", getAppStartCount(context));
        jSONObject.put(TetheredMessageKey.timestamp, convertCurrentUtcIso8601Time(new Date()));
        jSONObject.put("message_id", UUID.randomUUID().toString());
        jSONObject.put("marketplace_id", getObfuscatedMarketplaceId(context));
        jSONObject.put("application_name", getApplicationName());
        jSONObject.put("application_version", getApplicationVersion(context));
        jSONObject.put("operating_system_name", getOperatingSystemName());
        jSONObject.put("operating_system_version", getOperatingSystemVersion());
        jSONObject.put("device_id", getDeviceId(context));
        jSONObject.put(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE, getDeviceType(context));
        jSONObject.put("user_agent", getUserAgent(context));
        jSONObject.put("network_carrier", getNetworkCarrier(context));
        jSONObject.put("network_operator", getNetworkOperator(context));
        jSONObject.put("device_data", getDeviceData(context));
        String str = TAG;
        Log.debug(str, Intrinsics.stringPlus("AppStart event is: ", jSONObject));
        jSONObject.put("device_lvl", getLicenseInfo(context));
        jSONObject.put("install_app_store", getInstallAppStore(context));
        jSONObject.put("preinstall", getPreinstallInfo());
        jSONObject.put("install_referrer", getInstallReferrer(context));
        jSONObject.put("click_ts", getClickTS(context));
        jSONObject.put("install_begin_ts", getInstallBeginTS(context));
        Log.debug(str, Intrinsics.stringPlus("[AppsFlyer] Install app store is ", getInstallAppStore(context)));
        Log.debug(str, Intrinsics.stringPlus("[AppsFlyer] Preinstall info is ", getPreinstallInfo()));
        Log.debug(str, Intrinsics.stringPlus("[AppsFlyer] Play store referrer is ", getInstallReferrer(context)));
        Log.debug(str, Intrinsics.stringPlus("[Appsflyer] Click timestamp is ", Long.valueOf(getClickTS(context))));
        Log.debug(str, "[Appsflyer] Install start timestamp is ", Long.valueOf(getInstallBeginTS(context)));
        return jSONObject;
    }

    private final String getLocale(Context context) {
        String languageTag = context.getResources().getConfiguration().getLocales().get(0).toLanguageTag();
        Intrinsics.checkNotNullExpressionValue(languageTag, "{\n            context.re…toLanguageTag()\n        }");
        return languageTag;
    }

    private final String getInstallDate(Context context) {
        Date date;
        try {
            date = new Date(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime);
        } catch (PackageManager.NameNotFoundException e) {
            Log.error(TAG, "Error on retrieving Install Date", e);
            date = new Date();
        }
        return convertCurrentUtcIso8601TimeWithoutZ(date);
    }

    private final String convertCurrentUtcIso8601TimeWithoutZ(Date dateTime) {
        DateFormat dateFormat = DATE_FORMAT_WITHOUT_Z;
        Intrinsics.checkNotNull(dateFormat);
        dateFormat.setTimeZone(TIME_ZONE);
        DateFormat dateFormat2 = DATE_FORMAT_WITHOUT_Z;
        Intrinsics.checkNotNull(dateFormat2);
        String str = dateFormat2.format(dateTime);
        Intrinsics.checkNotNullExpressionValue(str, "DATE_FORMAT_WITHOUT_Z!!.format(dateTime)");
        return str;
    }

    private final String convertCurrentUtcIso8601Time(Date dateTime) {
        DateFormat dateFormat = DATE_FORMAT_WITH_Z;
        Intrinsics.checkNotNull(dateFormat);
        dateFormat.setTimeZone(TIME_ZONE);
        DateFormat dateFormat2 = DATE_FORMAT_WITH_Z;
        Intrinsics.checkNotNull(dateFormat2);
        String str = dateFormat2.format(dateTime);
        Intrinsics.checkNotNullExpressionValue(str, "DATE_FORMAT_WITH_Z!!.format(dateTime)");
        return str;
    }

    @JvmStatic
    private static final int getAppStartCount(Context context) {
        SharedPreferences sharedPref = INSTANCE.getSharedPref(context);
        int i = sharedPref.getInt("MetricAppOpenCount", 1);
        if (i == 1) {
            sharedPref.edit().putInt("MetricAppOpenCount", i + 1).apply();
        }
        return i;
    }

    private final SharedPreferences getSharedPref(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppsflyerMetric", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
        return sharedPreferences;
    }

    private final String getAndroidInstallReferrer(Context context) {
        String rawReferrer = CampaignMetrics.loadCampaignReferrer(context).getRawReferrer();
        Intrinsics.checkNotNullExpressionValue(rawReferrer, "loadCampaignReferrer(context).rawReferrer");
        return rawReferrer;
    }

    private final String getDeviceId(Context context) {
        String deviceId = AccountCredentialStorage.get(context).getDeviceId();
        Intrinsics.checkNotNullExpressionValue(deviceId, "accountCredentialStorage.deviceId");
        return deviceId;
    }

    private final String getDeviceType(Context context) {
        String deviceType = AccountCredentialStorage.get(context).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "accountCredentialStorage.deviceType");
        return deviceType;
    }

    private final String getUserAgent(Context context) {
        String defaultUserAgent = WebSettings.getDefaultUserAgent(context);
        Intrinsics.checkNotNullExpressionValue(defaultUserAgent, "getDefaultUserAgent(context)");
        return defaultUserAgent;
    }

    private final String getObfuscatedMarketplaceId(Context context) {
        String homeMarketPlaceId = AccountDetailUtil.get(context).getHomeMarketPlaceId();
        Intrinsics.checkNotNullExpressionValue(homeMarketPlaceId, "get(context).homeMarketPlaceId");
        return homeMarketPlaceId;
    }

    private final String getApplicationVersion(Context context) {
        String version = ApplicationAttributes.getInstance(context).getVersion();
        Intrinsics.checkNotNullExpressionValue(version, "getInstance(context).version");
        return version;
    }

    private final String getOperatingSystemVersion() {
        String RELEASE = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
        return RELEASE;
    }

    private final String getNetworkCarrier(Context context) {
        if (!context.getPackageManager().hasSystemFeature("android.hardware.telephony") || context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE) == null) {
            return "";
        }
        Object systemService = context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        TelephonyManager telephonyManager = (TelephonyManager) systemService;
        String networkOperatorName = telephonyManager.getNetworkOperatorName();
        String str = networkOperatorName != null ? networkOperatorName : "";
        return ((str.length() == 0) && 2 == telephonyManager.getPhoneType()) ? "CDMA" : str;
    }

    private final String getNetworkOperator(Context context) {
        if (!context.getPackageManager().hasSystemFeature("android.hardware.telephony") || context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE) == null) {
            return "";
        }
        Object systemService = context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        String simOperatorName = ((TelephonyManager) systemService).getSimOperatorName();
        Intrinsics.checkNotNullExpressionValue(simOperatorName, "manager.simOperatorName");
        return simOperatorName;
    }

    private final String getLicenseInfo(Context context) {
        return new DigitalMusicLicensingService(Random.INSTANCE.nextLong(), context).getLicenseResult();
    }

    private final String getDeviceData(Context context) {
        return DeviceDataCollector.getDeviceData(context);
    }

    private final String getInstallAppStore(Context context) {
        if (Build.VERSION.SDK_INT >= 30) {
            String installingPackageName = context.getPackageManager().getInstallSourceInfo(context.getPackageName()).getInstallingPackageName();
            return installingPackageName == null ? "" : installingPackageName;
        }
        String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        return installerPackageName == null ? "" : installerPackageName;
    }

    private final String getPreinstallInfo() {
        return DeviceDataCollector.getPreinstallInfo();
    }

    private final String getInstallReferrer(Context context) {
        String rawReferrer = CampaignMetrics.loadCampaignReferrer(context).getRawReferrer();
        Intrinsics.checkNotNullExpressionValue(rawReferrer, "loadCampaignReferrer(context).rawReferrer");
        return rawReferrer;
    }

    private final long getClickTS(Context context) {
        Long clickTS = CampaignMetrics.loadCampaignReferrer(context).getClickTS();
        Intrinsics.checkNotNullExpressionValue(clickTS, "loadCampaignReferrer(context).clickTS");
        return clickTS.longValue();
    }

    private final long getInstallBeginTS(Context context) {
        Long installBeginTS = CampaignMetrics.loadCampaignReferrer(context).getInstallBeginTS();
        Intrinsics.checkNotNullExpressionValue(installBeginTS, "loadCampaignReferrer(context).installBeginTS");
        return installBeginTS.longValue();
    }
}
