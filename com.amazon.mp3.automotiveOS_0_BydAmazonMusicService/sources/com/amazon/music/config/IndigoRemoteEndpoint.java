package com.amazon.music.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import com.amazon.music.config.IndigoConfiguration;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
final class IndigoRemoteEndpoint implements IndigoConfiguration.Endpoint {
    private static SharedPreferences mSharedPreferences;
    private static JSONObject sConfig;
    private final Attributes mApplicationAttributes;
    private final Context mContext;
    private final ConfigurationLoader mRemoteConfigLoader;
    private static final Logger LOG = LoggerFactory.getLogger(IndigoRemoteEndpoint.class.getSimpleName());
    private static final long TWELVE_HOURS_IN_MILLIS = TimeUnit.HOURS.toMillis(12);
    private static final long ONE_DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);
    private static long sLastSuccessfulRefresh = 0;

    public static String getStage() {
        return "prod";
    }

    IndigoRemoteEndpoint(Context context, ConfigurationLoader configurationLoader, Attributes attributes) {
        this.mContext = context.getApplicationContext();
        this.mRemoteConfigLoader = configurationLoader;
        this.mApplicationAttributes = attributes;
    }

    public synchronized boolean loaded() {
        return sConfig != null;
    }

    public boolean expired(boolean z) {
        return z ? System.currentTimeMillis() - sLastSuccessfulRefresh > ONE_DAY_IN_MILLIS : System.currentTimeMillis() - getLastSuccessfulRefreshMillis() > TWELVE_HOURS_IN_MILLIS;
    }

    public boolean expired() {
        return System.currentTimeMillis() - getLastSuccessfulRefreshMillis() > TWELVE_HOURS_IN_MILLIS;
    }

    public synchronized boolean isNewConfigVersionRequired() {
        boolean z;
        if (mSharedPreferences == null) {
            mSharedPreferences = this.mContext.getSharedPreferences(this.mContext.getString(R$string.CONFIG_SHARED_PREFERENCE_KEY), 0);
        }
        z = mSharedPreferences.getBoolean(this.mContext.getString(R$string.CONFIG_UPDATE_REQUIRED_KEY), true);
        LOG.debug("is new config update required = " + z);
        return z;
    }

    public static synchronized void clear() {
        sConfig = null;
    }

    @Override // com.amazon.music.config.IndigoConfiguration.Endpoint
    public synchronized String getValue(String str) {
        try {
        } catch (Exception unused) {
            return null;
        }
        return sConfig.getString(str);
    }

    public synchronized void load() {
        JSONObject jSONObjectLoadFromRemoteService = this.mRemoteConfigLoader.loadFromRemoteService(buildUrl());
        if (jSONObjectLoadFromRemoteService != null) {
            try {
                setConfig(jSONObjectLoadFromRemoteService.getJSONObject("config"));
                setLastSuccessfulRefresh();
                Util.saveToFile(this.mRemoteConfigLoader.getEncryptedConfigFile(jSONObjectLoadFromRemoteService), ConfigManager.getCachedRemoteConfigFile(this.mContext));
            } catch (JSONException unused) {
                LOG.error("loadFromRemoteService: got a valid JSON response, but config key missing!");
            }
        }
    }

    private String buildUrl() {
        String carrierName = Util.getCarrierName(this.mContext);
        HashMap map = new HashMap();
        map.put("carrier", carrierName);
        map.put(DeviceInformation.PARAMS_DEVICE_MODEL, Build.MODEL);
        map.put("deviceManufacturer", Build.MANUFACTURER);
        map.put("clientVersionCode", String.valueOf(this.mApplicationAttributes.getVersionCode()));
        map.put("clientVersionName", this.mApplicationAttributes.getVersionShort());
        map.put("clientVersionFull", this.mApplicationAttributes.getVersion());
        map.put("androidVersionName", Build.VERSION.RELEASE);
        map.put("androidVersionCode", Build.VERSION.INCREMENTAL);
        map.put("androidVersionSdkName", Build.VERSION.SDK);
        map.put("androidVersionSdkCode", String.valueOf(Build.VERSION.SDK_INT));
        map.put(Splash.PARAMS_LOCALE, String.format("%s_%s", this.mApplicationAttributes.getLocaleLanguage(), this.mApplicationAttributes.getLocaleCountryCode()));
        StringBuilder sb = new StringBuilder("https://cz15y20kg2.execute-api.us-east-1.amazonaws.com/prod");
        if (!map.entrySet().isEmpty()) {
            sb.append("/?");
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getValue();
                if (str == null) {
                    str = "";
                }
                String str2 = Uri.encode((String) entry.getKey(), null) + "=" + Uri.encode(str, null);
                if (sb2.length() > 0) {
                    sb2.append(Typography.amp);
                }
                sb2.append(str2);
            }
            sb.append((CharSequence) sb2);
        }
        return sb.toString();
    }

    private void setLastSuccessfulRefresh() {
        if (mSharedPreferences == null) {
            mSharedPreferences = this.mContext.getSharedPreferences(this.mContext.getString(R$string.CONFIG_SHARED_PREFERENCE_KEY), 0);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        mSharedPreferences.edit().putLong(this.mContext.getString(R$string.LAST_SUCCESSFUL_REFRESH_KEY), jCurrentTimeMillis).putBoolean(this.mContext.getString(R$string.CONFIG_UPDATE_REQUIRED_KEY), false).apply();
        LOG.info("Config profile successfully loaded at: " + jCurrentTimeMillis);
    }

    private long getLastSuccessfulRefreshMillis() {
        if (mSharedPreferences == null) {
            mSharedPreferences = this.mContext.getSharedPreferences(this.mContext.getString(R$string.CONFIG_SHARED_PREFERENCE_KEY), 0);
        }
        long j = mSharedPreferences.getLong(this.mContext.getString(R$string.LAST_SUCCESSFUL_REFRESH_KEY), 0L);
        LOG.debug("Last successful refresh was " + j);
        return j;
    }

    static void setConfig(JSONObject jSONObject) {
        sConfig = jSONObject;
    }
}
