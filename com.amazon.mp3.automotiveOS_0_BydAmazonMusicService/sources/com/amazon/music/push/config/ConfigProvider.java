package com.amazon.music.push.config;

import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;

/* JADX INFO: loaded from: classes4.dex */
public class ConfigProvider {
    public static String getString(String str) {
        return getConfiguration().string(str);
    }

    public static FeatureGateProvider getFeatureGateProvider() {
        return (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
    }

    public static Configuration getConfiguration() {
        return (Configuration) Providers.INSTANCE.get(ConfigurationProvider.class);
    }
}
