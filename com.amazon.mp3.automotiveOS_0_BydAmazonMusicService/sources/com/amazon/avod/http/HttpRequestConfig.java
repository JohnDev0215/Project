package com.amazon.avod.http;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.core.Framework;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class HttpRequestConfig extends ConfigBase {
    private final ConfigurationValue<Long> mBaseRequestRetryIntervalBackoff;
    private final ConfigurationValue<Long> mConnectionTimeout;
    private final ConfigurationValue<Long> mConnectivityTimeout;
    private final ConfigurationValue<Boolean> mEnableRequestResponseLogging;
    private final ConfigurationValue<Boolean> mEnableSoftwareVersionHeader;
    private final ConfigurationValue<String> mFallbackUserAgent;
    private final ConfigurationValue<Integer> mMaxRequestRetryAttempts;
    private final ConfigurationValue<Long> mMaxRequestRetryDuration;
    private final ConfigurationValue<Long> mRequestRetryJitterBackoff;
    private final ConfigurationValue<Boolean> mShouldUseHostHeader;
    private final ConfigurationValue<Long> mSocketTimeout;
    private final ConfigurationValue<String> mSoftwareVersionHeaderKey;

    private static class SingletonHolder {
        private static final HttpRequestConfig INSTANCE = new HttpRequestConfig();

        private SingletonHolder() {
        }
    }

    public static HttpRequestConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    HttpRequestConfig() {
        super("core.httpRequestConfig");
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long millis = timeUnit.toMillis(5L);
        ConfigType configType = ConfigType.SERVER;
        this.mConnectivityTimeout = newLongConfigValue("ConnectivityTimeout", millis, configType);
        this.mConnectionTimeout = newLongConfigValue("ConnectionTimeout", timeUnit.toMillis(7L), configType);
        this.mSocketTimeout = newLongConfigValue("SocketTimeout", timeUnit.toMillis(15L), configType);
        this.mMaxRequestRetryAttempts = newIntConfigValue("maxRequestRetryAttempts", 2, configType);
        this.mMaxRequestRetryDuration = newLongConfigValue("maxRequestRetryDuration", timeUnit.toMillis(30L), configType);
        this.mBaseRequestRetryIntervalBackoff = newLongConfigValue("requestRetryBaseIntervalBackoff", 500L, configType);
        this.mRequestRetryJitterBackoff = newLongConfigValue("requestRetryJitterBackoff", 200L, configType);
        this.mEnableRequestResponseLogging = newBooleanConfigValue("enableRequestResponseLogging", false, ConfigType.INTERNAL);
        this.mFallbackUserAgent = newStringConfigValue("fallbackUserAgent", "AivAndroidPlayer/1.0 (AndroidDevice)", configType);
        this.mShouldUseHostHeader = newBooleanConfigValue("shouldUseHostHeader", true, configType);
        this.mEnableSoftwareVersionHeader = newBooleanConfigValue("enableSoftwareVersionHeader", true, configType);
        this.mSoftwareVersionHeaderKey = newStringConfigValue("softwareVersionHeaderKey", "softwareVersion", configType);
    }

    public long getConnectivityTimeout() {
        return this.mConnectivityTimeout.getValue().longValue();
    }

    public int getSocketTimeout() {
        return this.mSocketTimeout.getValue().intValue();
    }

    public int getConnectionTimeout() {
        return this.mConnectionTimeout.getValue().intValue();
    }

    public int getMaxRequestRetryAttempts() {
        return this.mMaxRequestRetryAttempts.getValue().intValue();
    }

    public long getMaxRequestRetryDuration() {
        return this.mMaxRequestRetryDuration.getValue().longValue();
    }

    public long getBaseRequestRetryIntervalBackoff() {
        return this.mBaseRequestRetryIntervalBackoff.getValue().longValue();
    }

    public long getRequestRetryJitterBackoff() {
        return this.mRequestRetryJitterBackoff.getValue().longValue();
    }

    public boolean isRequestResponseLoggingEnabled() {
        return Framework.isDebugConfigurationEnabled() && this.mEnableRequestResponseLogging.getValue().booleanValue();
    }

    @Nonnull
    public String getFallbackUserAgent() {
        return this.mFallbackUserAgent.getValue();
    }

    public boolean shouldUseHostHeader() {
        return this.mShouldUseHostHeader.getValue().booleanValue();
    }

    public boolean isSoftwareVersionHeaderEnabled() {
        return this.mEnableSoftwareVersionHeader.getValue().booleanValue();
    }

    public String getSoftwareVersionHeaderKey() {
        return this.mSoftwareVersionHeaderKey.getValue();
    }
}
