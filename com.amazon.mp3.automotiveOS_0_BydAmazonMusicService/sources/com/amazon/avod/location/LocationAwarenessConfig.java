package com.amazon.avod.location;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.util.URLUtils;
import com.amazonaws.http.HttpHeader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class LocationAwarenessConfig extends ConfigBase {
    private final ConfigurationValue<Float> mAccuracyDeltaSignificanceMeters;
    private final ConfigurationValue<Boolean> mCanRequestPermission;
    private final ConfigurationValue<URL> mCbsLiveTvUrl;
    private final ConfigurationValue<Integer> mInterstitialShownCount;
    private final ConfigurationValue<Boolean> mIsFeatureEnabled;
    private final ConfigurationValue<Boolean> mIsPrefetchingEnabled;
    private final ConfigurationValue<Boolean> mIsWifiCheckDisabledForTesting;
    private final ConfigurationValue<Long> mLocationCallbackTimeoutMillis;
    private final ConfigurationValue<Long> mLocationMaxAgeMillis;
    private final ConfigurationValue<Integer> mMaxTimesCanShowInterstitial;
    private final ConfigurationValue<Long> mTimeDeltaSignificanceMillis;
    private static final long DEFAULT_LOCATION_MAX_AGE_MILLIS = TimeUnit.HOURS.toMillis(2);
    private static final long DEFAULT_CALLBACK_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(20);
    private static final long DEFAULT_TIME_DELTA_SIGNIFICANCE_MILLIS = TimeUnit.MINUTES.toMillis(2);

    private static class SingletonHolder {
        private static final LocationAwarenessConfig INSTANCE = new LocationAwarenessConfig();

        private SingletonHolder() {
        }
    }

    public static LocationAwarenessConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private LocationAwarenessConfig() {
        super(HttpHeader.LOCATION);
        ConfigType configType = ConfigType.SERVER;
        this.mIsFeatureEnabled = newBooleanConfigValue("location_featureEnabled", true, configType);
        this.mIsPrefetchingEnabled = newBooleanConfigValue("location_prefetchingEnabled", true, configType);
        this.mIsWifiCheckDisabledForTesting = newBooleanConfigValue("location_isWifiCheckDisabledForTesting", false, configType);
        this.mLocationCallbackTimeoutMillis = newLongConfigValue("location_callbackTimeout", DEFAULT_CALLBACK_TIMEOUT_MILLIS, configType);
        this.mLocationMaxAgeMillis = newLongConfigValue("location_maxAgeMillis", DEFAULT_LOCATION_MAX_AGE_MILLIS, configType);
        this.mTimeDeltaSignificanceMillis = newLongConfigValue("location_timeDeltaSignificanceMillis", DEFAULT_TIME_DELTA_SIGNIFICANCE_MILLIS, configType);
        this.mAccuracyDeltaSignificanceMeters = newFloatConfigValue("location_accuracyDeltaSignificanceMeters", 200.0f, configType);
        this.mInterstitialShownCount = newIntConfigValue("location_interstitialShownCount", 0, ConfigType.INTERNAL);
        this.mMaxTimesCanShowInterstitial = newIntConfigValue("location_maxTimesCanShowInterstitial", 1, configType);
        this.mCanRequestPermission = newBooleanConfigValue("location_canRequestPermission", true, ConfigType.PERSISTENT);
        this.mCbsLiveTvUrl = newUrlConfigValue("location_cbsLiveTvUrl", URLUtils.createUrlOrThrow("https://www.amazon.com/channels/cbsaalivetv"), configType);
    }

    long getLocationMaxAgeMillis() {
        return this.mLocationMaxAgeMillis.getValue().longValue();
    }

    float getAccuracyDeltaForFixSignificanceMeters() {
        return this.mAccuracyDeltaSignificanceMeters.getValue().floatValue();
    }
}
