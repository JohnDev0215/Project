package com.amazon.minerva.client.thirdparty.configuration;

import android.content.Context;
import android.util.Log;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class MetricsConfigurationHelper {
    private static final String TAG = "MetricsConfigurationHelper";
    private AssetFileParser mAssetFileParser;
    private Context mContext;
    private StorageConfiguration mCustomStorageConfiguration;
    private ThrottleConfiguration mCustomThrottleConfiguration;
    private UploadConfiguration mCustomUploadConfiguration;
    private DenyListConfiguration mDenyListConfiguration;
    private SamplingConfiguration mSamplingConfiguration;
    private StorageConfiguration mStorageConfiguration;
    private ThrottleConfiguration mThrottleConfiguration;
    private ThrottleConfiguration mThrottleHardLimitsConfiguration;
    private UploadConfiguration mUploadConfiguration;
    private ValidationConfiguration mValidationConfiguration;

    public MetricsConfigurationHelper(Context context, AssetFileParser assetFileParser) {
        this.mContext = context;
        this.mAssetFileParser = assetFileParser;
        setMetricsConfiguration(assetFileParser.parseMetricsConfiguration("{\n  \"devo\": {\n    \"ValidationConfiguration\": {\n      \"MaxKeyValuePairCount\": 256,\n      \"MaxMetricEventSizeBytes\": 4096,\n      \"MaxKeySizeBytes\": 256,\n      \"MaxValueSizeBytes\": 512\n    },\n    \"ThrottleConfiguration\": {\n      \"ThrottleSwitch\": 1,\n      \"MaxThrottleCredit\": 250,\n      \"DefaultThrottleCreditHour\": 150\n    },\n    \"ThrottleConfigurationHardLimits\": {\n      \"ThrottleSwitchHardLimit\": 1,\n      \"MaxThrottleCreditHardLimit\": 13000,\n      \"DefaultThrottleCreditHourHardLimit\": 9000\n    },\n    \"SamplingConfiguration\": {\n      \"DefaultSamplingRate\": 100\n    },\n    \"DenyListConfiguration\": {\n      \"DenyListBits\": \"0000000000\",\n      \"BitsSize\": 10\n    },\n    \"StorageConfiguration\": {\n      \"MaxBatchOpenTimeMillis\": 60000,\n      \"CheckBatchOpenTimeMillis\": 30000,\n      \"MaxBatchEntries\": 504,\n      \"MaxBatchSizeBytes\": 65536,\n      \"MaxStorageSpaceBytes\": 5242880,\n      \"MaxNumberOfBatchFiles\": 2048,\n      \"ExpiryTimeMillis\": 3600000,\n      \"PurgePeriodMillis\": 600000,\n      \"TransmissionPeriodMillis\": 900000,\n      \"UploadTriggeredByStoragePeriodMillis\": 180000\n    },\n    \"UploadConfiguration\": {\n      \"UrlEndpoint\": \"https://{deviceType}.{region}.prod.service.minerva.devices.a2z.com:443/metric-batch\",\n      \"IonFormat\": \"text/x-amzn-ion\",\n      \"ConnectTimeoutMillis\": 10000,\n      \"ReadTimeoutMillis\": 10000,\n      \"WakeLockTimeoutMillis\": 300000,\n      \"KPIRegion\": \"us-east-1\"\n    }\n  },\n  \"prod\": {\n    \"ValidationConfiguration\": {\n      \"MaxKeyValuePairCount\": 256,\n      \"MaxMetricEventSizeBytes\": 4096,\n      \"MaxKeySizeBytes\": 256,\n      \"MaxValueSizeBytes\": 512\n    },\n    \"ThrottleConfiguration\": {\n      \"ThrottleSwitch\": 1,\n      \"MaxThrottleCredit\": 250,\n      \"DefaultThrottleCreditHour\": 150\n    },\n    \"ThrottleConfigurationHardLimits\": {\n      \"ThrottleSwitchHardLimit\": 1,\n      \"MaxThrottleCreditHardLimit\": 1299,\n      \"DefaultThrottleCreditHourHardLimit\": 999\n    },\n    \"SamplingConfiguration\": {\n      \"DefaultSamplingRate\": 100\n    },\n    \"DenyListConfiguration\": {\n      \"DenyListBits\": \"0000000000\",\n      \"BitsSize\": 10\n    },\n    \"StorageConfiguration\": {\n      \"MaxBatchOpenTimeMillis\": 1200000,\n      \"CheckBatchOpenTimeMillis\": 300000,\n      \"MaxBatchEntries\": 504,\n      \"MaxBatchSizeBytes\": 65536,\n      \"MaxStorageSpaceBytes\": 5242880,\n      \"MaxNumberOfBatchFiles\": 2048,\n      \"ExpiryTimeMillis\": 604800000,\n      \"PurgePeriodMillis\": 86400000,\n      \"TransmissionPeriodMillis\": 28800000,\n      \"UploadTriggeredByStoragePeriodMillis\": 7200000\n    },\n    \"UploadConfiguration\": {\n      \"UrlEndpoint\": \"https://{deviceType}.{region}.prod.service.minerva.devices.a2z.com:443/metric-batch\",\n      \"IonFormat\": \"application/x-amzn-ion\",\n      \"ConnectTimeoutMillis\": 10000,\n      \"ReadTimeoutMillis\": 10000,\n      \"WakeLockTimeoutMillis\": 300000,\n      \"KPIRegion\": \"us-east-1\"\n    }\n  }\n}\n"));
        Log.i(TAG, "set custom starter config from customConfiguration file");
        setCustomMetricsConfiguration(this.mAssetFileParser.parseMetricsConfiguration(context, "customConfiguration"));
    }

    protected void setMetricsConfiguration(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        setValidationConfiguration(jSONObject);
        setThrottleConfiguration(jSONObject);
        setThrottleHardLimitsConfiguration(jSONObject);
        setSamplingConfiguration(jSONObject);
        setDenyListConfiguration(jSONObject);
        setStorageConfiguration(jSONObject);
        setUploadConfiguration(jSONObject);
    }

    protected void setCustomMetricsConfiguration(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        setCustomStorageConfiguration(jSONObject);
        setCustomUploadConfiguration(jSONObject);
        setCustomThrottleConfiguration(jSONObject);
    }

    private void setValidationConfiguration(JSONObject jSONObject) {
        JSONObject fallbackValidationConfiguration;
        if (jSONObject.optJSONObject("ValidationConfiguration") != null) {
            fallbackValidationConfiguration = jSONObject.optJSONObject("ValidationConfiguration");
        } else {
            fallbackValidationConfiguration = this.mAssetFileParser.getFallbackValidationConfiguration();
        }
        this.mValidationConfiguration = new ValidationConfiguration(fallbackValidationConfiguration.optInt("MaxKeyValuePairCount", this.mAssetFileParser.getFallbackMaxKeyValuePairCount()), fallbackValidationConfiguration.optInt("MaxMetricEventSizeBytes", this.mAssetFileParser.getFallbackMaxMetricEventSizeBytes()), fallbackValidationConfiguration.optInt("MaxKeySizeBytes", this.mAssetFileParser.getFallbackMaxKeySizeBytes()), fallbackValidationConfiguration.optInt("MaxValueSizeBytes", this.mAssetFileParser.getFallbackMaxValueSizeBytes()));
    }

    private void setThrottleConfiguration(JSONObject jSONObject) {
        JSONObject fallbackThrottleConfiguration;
        if (jSONObject.optJSONObject("ThrottleConfiguration") != null) {
            fallbackThrottleConfiguration = jSONObject.optJSONObject("ThrottleConfiguration");
        } else {
            fallbackThrottleConfiguration = this.mAssetFileParser.getFallbackThrottleConfiguration();
        }
        this.mThrottleConfiguration = new ThrottleConfiguration(fallbackThrottleConfiguration.optInt("ThrottleSwitch", this.mAssetFileParser.getFallbackThrottleSwitch()), fallbackThrottleConfiguration.optInt("MaxThrottleCredit", this.mAssetFileParser.getFallbackMaxThrottleCredit()), fallbackThrottleConfiguration.optInt("DefaultThrottleCreditHour", this.mAssetFileParser.getFallbackDefaultThrottleCreditHour()));
    }

    private void setThrottleHardLimitsConfiguration(JSONObject jSONObject) {
        JSONObject fallbackThrottleConfigurationHardLimits;
        if (jSONObject.optJSONObject("ThrottleConfigurationHardLimits") != null) {
            fallbackThrottleConfigurationHardLimits = jSONObject.optJSONObject("ThrottleConfigurationHardLimits");
        } else {
            fallbackThrottleConfigurationHardLimits = this.mAssetFileParser.getFallbackThrottleConfigurationHardLimits();
        }
        this.mThrottleHardLimitsConfiguration = new ThrottleConfiguration(fallbackThrottleConfigurationHardLimits.optInt("ThrottleSwitchHardLimit", this.mAssetFileParser.getFallbackThrottleSwitchHardLimit()), fallbackThrottleConfigurationHardLimits.optInt("MaxThrottleCreditHardLimit", this.mAssetFileParser.getFallbackMaxThrottleCreditHardLimit()), fallbackThrottleConfigurationHardLimits.optInt("DefaultThrottleCreditHourHardLimit", this.mAssetFileParser.getFallbackDefaultThrottleCreditHourHardLimit()));
        if (this.mCustomThrottleConfiguration != null) {
            this.mCustomThrottleConfiguration = new ThrottleConfiguration(this.mCustomThrottleConfiguration.getThrottleSwitch(), this.mCustomThrottleConfiguration.getMaxThrottleCredit(), this.mCustomThrottleConfiguration.getDefaultThrottleCreditHour(), this.mThrottleHardLimitsConfiguration);
        }
    }

    private void setSamplingConfiguration(JSONObject jSONObject) {
        JSONObject fallbackSamplingConfiguration;
        if (jSONObject.optJSONObject("SamplingConfiguration") != null) {
            fallbackSamplingConfiguration = jSONObject.optJSONObject("SamplingConfiguration");
        } else {
            fallbackSamplingConfiguration = this.mAssetFileParser.getFallbackSamplingConfiguration();
        }
        this.mSamplingConfiguration = new SamplingConfiguration(fallbackSamplingConfiguration.optInt("DefaultSamplingRate", this.mAssetFileParser.getFallbackDefaultSamplingRate()));
    }

    private void setDenyListConfiguration(JSONObject jSONObject) {
        JSONObject fallbackDenyListConfiguration;
        if (jSONObject.optJSONObject("DenyListConfiguration") != null) {
            fallbackDenyListConfiguration = jSONObject.optJSONObject("DenyListConfiguration");
        } else {
            fallbackDenyListConfiguration = this.mAssetFileParser.getFallbackDenyListConfiguration();
        }
        this.mDenyListConfiguration = new DenyListConfiguration(fallbackDenyListConfiguration.optString("DenyListBits", this.mAssetFileParser.getFallbackDenyListBits()), fallbackDenyListConfiguration.optInt("BitsSize", this.mAssetFileParser.getFallbackDenyListBitSize()));
    }

    private void setStorageConfiguration(JSONObject jSONObject) {
        JSONObject fallbackStorageConfiguration;
        if (jSONObject.optJSONObject("StorageConfiguration") != null) {
            fallbackStorageConfiguration = jSONObject.optJSONObject("StorageConfiguration");
        } else {
            fallbackStorageConfiguration = this.mAssetFileParser.getFallbackStorageConfiguration();
        }
        this.mStorageConfiguration = new StorageConfiguration(fallbackStorageConfiguration.optLong("MaxBatchOpenTimeMillis", this.mAssetFileParser.getFallbackMaxBatchOpenTimeMillis()), fallbackStorageConfiguration.optLong("CheckBatchOpenTimeMillis", this.mAssetFileParser.getFallbackCheckBatchOpenTimeMillis()), fallbackStorageConfiguration.optLong("MaxBatchEntries", this.mAssetFileParser.getFallbackMaxBatchEntries()), fallbackStorageConfiguration.optLong("MaxBatchSizeBytes", this.mAssetFileParser.getFallbackMaxBatchSizeBytes()), fallbackStorageConfiguration.optLong("MaxStorageSpaceBytes", this.mAssetFileParser.getFallbackMaxStorageSpaceBytes()), fallbackStorageConfiguration.optLong("MaxNumberOfBatchFiles", this.mAssetFileParser.getFallbackMaxNumberOfBatchFiles()), fallbackStorageConfiguration.optLong("ExpiryTimeMillis", this.mAssetFileParser.getFallbackExpiryTimeMillis()), fallbackStorageConfiguration.optLong("PurgePeriodMillis", this.mAssetFileParser.getFallbackPurgePeriodMillis()), fallbackStorageConfiguration.optLong("TransmissionPeriodMillis", this.mAssetFileParser.getFallbackTransmissionPeriodMillis()));
    }

    private void setUploadConfiguration(JSONObject jSONObject) {
        JSONObject fallbackUploadConfiguration;
        if (jSONObject.optJSONObject("UploadConfiguration") != null) {
            fallbackUploadConfiguration = jSONObject.optJSONObject("UploadConfiguration");
        } else {
            fallbackUploadConfiguration = this.mAssetFileParser.getFallbackUploadConfiguration();
        }
        this.mUploadConfiguration = new UploadConfiguration(fallbackUploadConfiguration.optString("UrlEndpoint", this.mAssetFileParser.getFallbackUrlEndpoint()), fallbackUploadConfiguration.optString("IonFormat", this.mAssetFileParser.getFallbackIonFormat()), fallbackUploadConfiguration.optInt("ConnectTimeoutMillis", this.mAssetFileParser.getFallbackConnectTimeoutMillis()), fallbackUploadConfiguration.optInt("ReadTimeoutMillis", this.mAssetFileParser.getFallbackReadTimeoutMillis()), fallbackUploadConfiguration.optLong("WakeLockTimeoutMillis", this.mAssetFileParser.getFallbackWakeLockTimeoutMillis()), fallbackUploadConfiguration.optString("KPIRegion", this.mAssetFileParser.getFallbackKpiRegion()));
    }

    private void setCustomStorageConfiguration(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.optJSONObject("StorageConfiguration") == null) {
            return;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("StorageConfiguration");
        this.mCustomStorageConfiguration = new StorageConfiguration(jSONObjectOptJSONObject.optLong("MaxBatchOpenTimeMillis", this.mAssetFileParser.getFallbackMaxBatchOpenTimeMillis()), jSONObjectOptJSONObject.optLong("CheckBatchOpenTimeMillis", this.mAssetFileParser.getFallbackCheckBatchOpenTimeMillis()), jSONObjectOptJSONObject.optLong("MaxBatchEntries", this.mAssetFileParser.getFallbackMaxBatchEntries()), jSONObjectOptJSONObject.optLong("MaxBatchSizeBytes", this.mAssetFileParser.getFallbackMaxBatchSizeBytes()), jSONObjectOptJSONObject.optLong("MaxStorageSpaceBytes", this.mAssetFileParser.getFallbackMaxStorageSpaceBytes()), jSONObjectOptJSONObject.optLong("MaxNumberOfBatchFiles", this.mAssetFileParser.getFallbackMaxNumberOfBatchFiles()), jSONObjectOptJSONObject.optLong("ExpiryTimeMillis", this.mAssetFileParser.getFallbackExpiryTimeMillis()), jSONObjectOptJSONObject.optLong("PurgePeriodMillis", this.mAssetFileParser.getFallbackPurgePeriodMillis()), jSONObjectOptJSONObject.optLong("TransmissionPeriodMillis", this.mAssetFileParser.getFallbackTransmissionPeriodMillis()));
    }

    private void setCustomUploadConfiguration(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.optJSONObject("UploadConfiguration") == null) {
            return;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("UploadConfiguration");
        this.mCustomUploadConfiguration = new UploadConfiguration(jSONObjectOptJSONObject.optString("UrlEndpoint", this.mAssetFileParser.getFallbackUrlEndpoint()), jSONObjectOptJSONObject.optString("IonFormat", this.mAssetFileParser.getFallbackIonFormat()), jSONObjectOptJSONObject.optInt("ConnectTimeoutMillis", this.mAssetFileParser.getFallbackConnectTimeoutMillis()), jSONObjectOptJSONObject.optInt("ReadTimeoutMillis", this.mAssetFileParser.getFallbackReadTimeoutMillis()), jSONObjectOptJSONObject.optLong("WakeLockTimeoutMillis", this.mAssetFileParser.getFallbackWakeLockTimeoutMillis()), jSONObjectOptJSONObject.optString("KPIRegion", this.mAssetFileParser.getFallbackKpiRegion()));
    }

    public void setCustomThrottleConfiguration(JSONObject jSONObject) {
        JSONObject fallbackThrottleConfiguration;
        if (jSONObject.optJSONObject("ThrottleConfiguration") != null) {
            fallbackThrottleConfiguration = jSONObject.optJSONObject("ThrottleConfiguration");
        } else {
            fallbackThrottleConfiguration = this.mAssetFileParser.getFallbackThrottleConfiguration();
        }
        this.mCustomThrottleConfiguration = new ThrottleConfiguration(fallbackThrottleConfiguration.optInt("ThrottleSwitch", this.mAssetFileParser.getFallbackThrottleSwitch()), fallbackThrottleConfiguration.optInt("MaxThrottleCredit", this.mAssetFileParser.getFallbackMaxThrottleCredit()), fallbackThrottleConfiguration.optInt("DefaultThrottleCreditHour", this.mAssetFileParser.getFallbackDefaultThrottleCreditHour()), this.mThrottleHardLimitsConfiguration);
    }

    public ValidationConfiguration getValidationConfiguration() {
        return this.mValidationConfiguration;
    }

    public ThrottleConfiguration getThrottleConfiguration() {
        ThrottleConfiguration throttleConfiguration = this.mCustomThrottleConfiguration;
        return throttleConfiguration != null ? throttleConfiguration : this.mThrottleConfiguration;
    }

    public StorageConfiguration getStorageConfiguration() {
        StorageConfiguration storageConfiguration = this.mCustomStorageConfiguration;
        return storageConfiguration != null ? storageConfiguration : this.mStorageConfiguration;
    }

    public UploadConfiguration getUploadConfiguration() {
        UploadConfiguration uploadConfiguration = this.mCustomUploadConfiguration;
        return uploadConfiguration != null ? uploadConfiguration : this.mUploadConfiguration;
    }
}
