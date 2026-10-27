package com.amazon.minerva.client.thirdparty.configuration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class AssetFileParser {
    private static final String TAG = "AssetFileParser";
    private long mCheckBatchOpenTimeMillis;
    private int mConnectTimeoutMillis;
    private Context mContext;
    private int mDefaultSamplingRate;
    private int mDefaultThrottleCreditHour;
    private int mDefaultThrottleCreditHourHardLimit;
    private int mDenyListBitSize;
    private String mDenyListBits;
    private JSONObject mDenyListObject;
    private long mExpiryTimeMillis;
    private String mIonFormat;
    private String mKpiRegion;
    private long mMaxBatchEntries;
    private long mMaxBatchOpenTimeMillis;
    private long mMaxBatchSizeBytes;
    private int mMaxKeySizeBytes;
    private int mMaxKeyValuePairCount;
    private int mMaxMetricEventSizeBytes;
    private long mMaxNumberOfBatchFiles;
    private long mMaxStorageSpaceBytes;
    private int mMaxThrottleCredit;
    private int mMaxThrottleCreditHardLimit;
    private int mMaxValueSizeBytes;
    private long mPurgePeriodMillis;
    private int mReadTimeoutMillis;
    private JSONObject mSamplingObject;
    private SharedPreferences mSharedPreferences;
    private JSONObject mStorageObject;
    private JSONObject mThrottleHardLimitsObject;
    private JSONObject mThrottleObject;
    private int mThrottleSwitch;
    private int mThrottleSwitchHardLimit;
    private long mTransmissionPeriodMillis;
    private JSONObject mUploadObject;
    private String mUrlEndpoint;
    private JSONObject mValidationObject;
    private long mWakeLockTimeoutMillis;

    public AssetFileParser(Context context) {
        this.mContext = context;
        this.mSharedPreferences = context.getSharedPreferences("minerva-debug", 0);
        setFallbackConfiguration(parseMetricsConfiguration("{\n  \"devo\": {\n    \"ValidationConfiguration\": {\n      \"MaxKeyValuePairCount\": 256,\n      \"MaxMetricEventSizeBytes\": 4096,\n      \"MaxKeySizeBytes\": 256,\n      \"MaxValueSizeBytes\": 512\n    },\n    \"ThrottleConfiguration\": {\n      \"ThrottleSwitch\": 1,\n      \"MaxThrottleCredit\": 250,\n      \"DefaultThrottleCreditHour\": 150\n    },\n    \"ThrottleConfigurationHardLimits\": {\n      \"ThrottleSwitchHardLimit\": 1,\n      \"MaxThrottleCreditHardLimit\": 13000,\n      \"DefaultThrottleCreditHourHardLimit\": 9000\n    },\n    \"SamplingConfiguration\": {\n      \"DefaultSamplingRate\": 100\n    },\n    \"DenyListConfiguration\": {\n      \"DenyListBits\": \"0000000000\",\n      \"BitsSize\": 10\n    },\n    \"StorageConfiguration\": {\n      \"MaxBatchOpenTimeMillis\": 60000,\n      \"CheckBatchOpenTimeMillis\": 30000,\n      \"MaxBatchEntries\": 504,\n      \"MaxBatchSizeBytes\": 65536,\n      \"MaxStorageSpaceBytes\": 5242880,\n      \"MaxNumberOfBatchFiles\": 2048,\n      \"ExpiryTimeMillis\": 3600000,\n      \"PurgePeriodMillis\": 600000,\n      \"TransmissionPeriodMillis\": 900000,\n      \"UploadTriggeredByStoragePeriodMillis\": 180000\n    },\n    \"UploadConfiguration\": {\n      \"UrlEndpoint\": \"https://{deviceType}.{region}.prod.service.minerva.devices.a2z.com:443/metric-batch\",\n      \"IonFormat\": \"text/x-amzn-ion\",\n      \"ConnectTimeoutMillis\": 10000,\n      \"ReadTimeoutMillis\": 10000,\n      \"WakeLockTimeoutMillis\": 300000,\n      \"KPIRegion\": \"us-east-1\"\n    }\n  },\n  \"prod\": {\n    \"ValidationConfiguration\": {\n      \"MaxKeyValuePairCount\": 256,\n      \"MaxMetricEventSizeBytes\": 4096,\n      \"MaxKeySizeBytes\": 256,\n      \"MaxValueSizeBytes\": 512\n    },\n    \"ThrottleConfiguration\": {\n      \"ThrottleSwitch\": 1,\n      \"MaxThrottleCredit\": 250,\n      \"DefaultThrottleCreditHour\": 150\n    },\n    \"ThrottleConfigurationHardLimits\": {\n      \"ThrottleSwitchHardLimit\": 1,\n      \"MaxThrottleCreditHardLimit\": 1299,\n      \"DefaultThrottleCreditHourHardLimit\": 999\n    },\n    \"SamplingConfiguration\": {\n      \"DefaultSamplingRate\": 100\n    },\n    \"DenyListConfiguration\": {\n      \"DenyListBits\": \"0000000000\",\n      \"BitsSize\": 10\n    },\n    \"StorageConfiguration\": {\n      \"MaxBatchOpenTimeMillis\": 1200000,\n      \"CheckBatchOpenTimeMillis\": 300000,\n      \"MaxBatchEntries\": 504,\n      \"MaxBatchSizeBytes\": 65536,\n      \"MaxStorageSpaceBytes\": 5242880,\n      \"MaxNumberOfBatchFiles\": 2048,\n      \"ExpiryTimeMillis\": 604800000,\n      \"PurgePeriodMillis\": 86400000,\n      \"TransmissionPeriodMillis\": 28800000,\n      \"UploadTriggeredByStoragePeriodMillis\": 7200000\n    },\n    \"UploadConfiguration\": {\n      \"UrlEndpoint\": \"https://{deviceType}.{region}.prod.service.minerva.devices.a2z.com:443/metric-batch\",\n      \"IonFormat\": \"application/x-amzn-ion\",\n      \"ConnectTimeoutMillis\": 10000,\n      \"ReadTimeoutMillis\": 10000,\n      \"WakeLockTimeoutMillis\": 300000,\n      \"KPIRegion\": \"us-east-1\"\n    }\n  }\n}\n"));
    }

    protected JSONObject getFallbackValidationConfiguration() {
        return this.mValidationObject;
    }

    protected JSONObject getFallbackThrottleConfiguration() {
        return this.mThrottleObject;
    }

    protected JSONObject getFallbackThrottleConfigurationHardLimits() {
        return this.mThrottleHardLimitsObject;
    }

    protected JSONObject getFallbackSamplingConfiguration() {
        return this.mSamplingObject;
    }

    protected JSONObject getFallbackDenyListConfiguration() {
        return this.mDenyListObject;
    }

    protected JSONObject getFallbackStorageConfiguration() {
        return this.mStorageObject;
    }

    protected JSONObject getFallbackUploadConfiguration() {
        return this.mUploadObject;
    }

    protected int getFallbackMaxKeyValuePairCount() {
        return this.mMaxKeyValuePairCount;
    }

    protected int getFallbackMaxMetricEventSizeBytes() {
        return this.mMaxMetricEventSizeBytes;
    }

    protected int getFallbackMaxKeySizeBytes() {
        return this.mMaxKeySizeBytes;
    }

    protected int getFallbackMaxValueSizeBytes() {
        return this.mMaxValueSizeBytes;
    }

    protected int getFallbackThrottleSwitch() {
        return this.mThrottleSwitch;
    }

    protected int getFallbackMaxThrottleCredit() {
        return this.mMaxThrottleCredit;
    }

    protected int getFallbackDefaultThrottleCreditHour() {
        return this.mDefaultThrottleCreditHour;
    }

    protected int getFallbackThrottleSwitchHardLimit() {
        return this.mThrottleSwitchHardLimit;
    }

    protected int getFallbackMaxThrottleCreditHardLimit() {
        return this.mMaxThrottleCreditHardLimit;
    }

    protected int getFallbackDefaultThrottleCreditHourHardLimit() {
        return this.mDefaultThrottleCreditHourHardLimit;
    }

    protected int getFallbackDefaultSamplingRate() {
        return this.mDefaultSamplingRate;
    }

    protected String getFallbackDenyListBits() {
        return this.mDenyListBits;
    }

    protected int getFallbackDenyListBitSize() {
        return this.mDenyListBitSize;
    }

    protected long getFallbackMaxBatchOpenTimeMillis() {
        return this.mMaxBatchOpenTimeMillis;
    }

    protected long getFallbackCheckBatchOpenTimeMillis() {
        return this.mCheckBatchOpenTimeMillis;
    }

    protected long getFallbackMaxBatchEntries() {
        return this.mMaxBatchEntries;
    }

    protected long getFallbackMaxBatchSizeBytes() {
        return this.mMaxBatchSizeBytes;
    }

    protected long getFallbackMaxStorageSpaceBytes() {
        return this.mMaxStorageSpaceBytes;
    }

    protected long getFallbackMaxNumberOfBatchFiles() {
        return this.mMaxNumberOfBatchFiles;
    }

    protected long getFallbackExpiryTimeMillis() {
        return this.mExpiryTimeMillis;
    }

    protected long getFallbackPurgePeriodMillis() {
        return this.mExpiryTimeMillis;
    }

    protected long getFallbackTransmissionPeriodMillis() {
        return this.mTransmissionPeriodMillis;
    }

    protected String getFallbackUrlEndpoint() {
        return this.mUrlEndpoint;
    }

    protected String getFallbackIonFormat() {
        return this.mIonFormat;
    }

    protected int getFallbackConnectTimeoutMillis() {
        return this.mConnectTimeoutMillis;
    }

    protected int getFallbackReadTimeoutMillis() {
        return this.mReadTimeoutMillis;
    }

    protected long getFallbackWakeLockTimeoutMillis() {
        return this.mWakeLockTimeoutMillis;
    }

    protected String getFallbackKpiRegion() {
        return this.mKpiRegion;
    }

    private void setFallbackConfiguration(JSONObject jSONObject) {
        try {
            this.mValidationObject = jSONObject.getJSONObject("ValidationConfiguration");
            this.mThrottleObject = jSONObject.getJSONObject("ThrottleConfiguration");
            this.mThrottleHardLimitsObject = jSONObject.getJSONObject("ThrottleConfigurationHardLimits");
            this.mSamplingObject = jSONObject.getJSONObject("SamplingConfiguration");
            this.mDenyListObject = jSONObject.getJSONObject("DenyListConfiguration");
            this.mStorageObject = jSONObject.getJSONObject("StorageConfiguration");
            this.mUploadObject = jSONObject.getJSONObject("UploadConfiguration");
            this.mMaxKeyValuePairCount = this.mValidationObject.getInt("MaxKeyValuePairCount");
            this.mMaxMetricEventSizeBytes = this.mValidationObject.getInt("MaxMetricEventSizeBytes");
            this.mMaxKeySizeBytes = this.mValidationObject.getInt("MaxKeySizeBytes");
            this.mMaxValueSizeBytes = this.mValidationObject.getInt("MaxValueSizeBytes");
            this.mThrottleSwitch = this.mThrottleObject.getInt("ThrottleSwitch");
            this.mMaxThrottleCredit = this.mThrottleObject.getInt("MaxThrottleCredit");
            this.mDefaultThrottleCreditHour = this.mThrottleObject.getInt("DefaultThrottleCreditHour");
            this.mThrottleSwitchHardLimit = this.mThrottleHardLimitsObject.getInt("ThrottleSwitchHardLimit");
            this.mMaxThrottleCreditHardLimit = this.mThrottleHardLimitsObject.getInt("MaxThrottleCreditHardLimit");
            this.mDefaultThrottleCreditHourHardLimit = this.mThrottleHardLimitsObject.getInt("DefaultThrottleCreditHourHardLimit");
            this.mDefaultSamplingRate = this.mSamplingObject.getInt("DefaultSamplingRate");
            this.mDenyListBits = this.mDenyListObject.getString("DenyListBits");
            this.mDenyListBitSize = this.mDenyListObject.getInt("BitsSize");
            this.mMaxBatchOpenTimeMillis = this.mStorageObject.getLong("MaxBatchOpenTimeMillis");
            this.mCheckBatchOpenTimeMillis = this.mStorageObject.getLong("CheckBatchOpenTimeMillis");
            this.mMaxBatchEntries = this.mStorageObject.getLong("MaxBatchEntries");
            this.mMaxBatchSizeBytes = this.mStorageObject.getLong("MaxBatchSizeBytes");
            this.mMaxStorageSpaceBytes = this.mStorageObject.getLong("MaxStorageSpaceBytes");
            this.mMaxNumberOfBatchFiles = this.mStorageObject.getLong("MaxNumberOfBatchFiles");
            this.mExpiryTimeMillis = this.mStorageObject.getLong("ExpiryTimeMillis");
            this.mPurgePeriodMillis = this.mStorageObject.getLong("PurgePeriodMillis");
            this.mTransmissionPeriodMillis = this.mStorageObject.getLong("TransmissionPeriodMillis");
            this.mUrlEndpoint = this.mUploadObject.getString("UrlEndpoint");
            this.mIonFormat = this.mUploadObject.getString("IonFormat");
            this.mConnectTimeoutMillis = this.mUploadObject.getInt("ConnectTimeoutMillis");
            this.mReadTimeoutMillis = this.mUploadObject.getInt("ReadTimeoutMillis");
            this.mWakeLockTimeoutMillis = this.mUploadObject.getLong("WakeLockTimeoutMillis");
            this.mKpiRegion = this.mUploadObject.getString("KPIRegion");
        } catch (Exception e) {
            Log.e(TAG, "Unable to set fallback default config value from Asset file... " + e);
        }
    }

    protected JSONObject parseMetricsConfiguration(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            inputStreamOpen.close();
            return getDomainMetricsConfiguration(new JSONObject(new String(bArr)));
        } catch (IOException e) {
            Log.e(TAG, "An error occurs when reading metrics configuration file: " + str, e);
            return null;
        } catch (JSONException e2) {
            Log.e(TAG, "An error occurs when parsing metrics configuration to Json object.", e2);
            return null;
        }
    }

    protected JSONObject parseMetricsConfiguration(String str) {
        try {
            return getDomainMetricsConfiguration(new JSONObject(str));
        } catch (JSONException e) {
            Log.e(TAG, "An error occurs when parsing metrics configuration to Json object.", e);
            return null;
        }
    }

    protected JSONObject getDomainMetricsConfiguration(JSONObject jSONObject) throws JSONException {
        if (!Build.TYPE.equals("user") && isDebugModeEnabled()) {
            Log.i(TAG, "Debug mode is turned on, and using Devo configuration.");
            return jSONObject.getJSONObject("devo");
        }
        Log.i(TAG, "Debug mode is turned off, and using Prod configuration.");
        return jSONObject.getJSONObject("prod");
    }

    private boolean isDebugModeEnabled() {
        boolean z = this.mSharedPreferences.getBoolean("MinervaClientDebugMode", false);
        if (z) {
            Log.i(TAG, "Minerva debug mode is turned on.");
        } else {
            Log.i(TAG, "Minerva debug mode is turned off.");
        }
        return z;
    }
}
