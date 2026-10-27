package com.amazon.avod.config;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import android.net.Uri;
import com.amazon.avod.core.Framework;
import com.amazon.avod.util.BetaConfigProvider;
import com.amazon.avod.util.URLUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.net.URL;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class HttpClientConfig extends ConfigBase {
    private static final URL DEFAULT_FALLBACK_URL = URLUtils.createUrlOrThrow("https://atv-ext.amazon.com");
    private final ConfigurationValue<Boolean> mEnableLatestManifestLogging;
    private final ConfigurationValue<Boolean> mIsResiliencyFeatureEnabled;
    private int mResiliencyFallbackBaseUrlIndex;
    private final ConfigurationValue<List<String>> mResiliencyFallbackBaseUrls;
    private final ConfigurationValue<URL> mTerminatorServiceCallFallbackUrl;
    private final ConfigurationValue<String> mTerminatorServiceCallUrlSuffix;
    private final ConfigurationValue<String> mTerminatorServiceCallUrlSuffixOverride;
    private boolean mUseTerminatorServiceCallFallbackUrl;

    private static class SingletonHolder {
        private static final HttpClientConfig INSTANCE = new HttpClientConfig();

        private SingletonHolder() {
        }
    }

    public static HttpClientConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    HttpClientConfig() {
        super("aiv.clientConfig");
        this.mUseTerminatorServiceCallFallbackUrl = false;
        ConfigType configType = ConfigType.SERVER;
        this.mTerminatorServiceCallUrlSuffix = newStringConfigValue("customerConfig_baseUrl", "api.amazonvideo.com", configType);
        ConfigType configType2 = ConfigType.INTERNAL;
        this.mEnableLatestManifestLogging = newBooleanConfigValue("enableLatestManifestLogging", false, configType2);
        this.mTerminatorServiceCallFallbackUrl = newUrlConfigValue("customerConfig_fallbackUrl", DEFAULT_FALLBACK_URL, configType);
        this.mTerminatorServiceCallUrlSuffixOverride = newStringConfigValue("terminatorServiceCallUrlSuffixOverride", null, configType2);
        this.mIsResiliencyFeatureEnabled = newBooleanConfigValue("isResiliencyFeatureEnabled", false, configType);
        this.mResiliencyFallbackBaseUrls = newStringListConfigValue("resiliencyConfig_orderedFallbackBaseUrls", "", ",", configType);
    }

    public boolean getEnableLatestManifestLogging() {
        return this.mEnableLatestManifestLogging.getValue().booleanValue();
    }

    @Nonnull
    public String getTerminatorServiceCallUrl(@Nonnull String str) {
        String value;
        Preconditions.checkNotNull(str, "terminatorId");
        if (Framework.isDebugConfigurationEnabled() && (value = this.mTerminatorServiceCallUrlSuffixOverride.getValue()) != null) {
            return getTerminatorServiceCallUrl(str, value);
        }
        if (isResiliencyFeatureEnabled() && !this.mResiliencyFallbackBaseUrls.getValue().isEmpty()) {
            return getTerminatorServiceCallUrl(str, this.mResiliencyFallbackBaseUrls.getValue().get(this.mResiliencyFallbackBaseUrlIndex));
        }
        if (this.mUseTerminatorServiceCallFallbackUrl) {
            return this.mTerminatorServiceCallFallbackUrl.getValue().toString();
        }
        return getTerminatorServiceCallUrl(str, this.mTerminatorServiceCallUrlSuffix.getValue());
    }

    @Nonnull
    public String getTerminatorServiceCallUrl(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str, "terminatorId");
        return new Uri.Builder().scheme("https").authority(Joiner.on('.').join(str, str2, new Object[0])).build().toString();
    }

    public boolean isResiliencyFeatureEnabled() {
        return this.mIsResiliencyFeatureEnabled.getValue().booleanValue() || BetaConfigProvider.getInstance().provideBetaConfig().isInternalBeta();
    }
}
