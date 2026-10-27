package com.amazon.mp3.capability;

import com.amazon.music.featureflag.FeatureFlagProvider;
import com.amazon.music.featuregating.configuration.Configuration;

/* JADX INFO: loaded from: classes3.dex */
public class FeatureFlagsProvider {
    private static LegacyAppConfigFeatureFlagProvider mFeatureFlagProvider;
    private static NoOpFeatureFlagProvider mNoOpProvider;

    public static synchronized void init(final Configuration configuration) {
        if (mFeatureFlagProvider == null) {
            mFeatureFlagProvider = new LegacyAppConfigFeatureFlagProvider(configuration);
        }
    }

    public static synchronized FeatureFlagProvider getFeatureFlagProvider() {
        LegacyAppConfigFeatureFlagProvider legacyAppConfigFeatureFlagProvider = mFeatureFlagProvider;
        if (legacyAppConfigFeatureFlagProvider != null) {
            return legacyAppConfigFeatureFlagProvider;
        }
        return getNoOpProvider();
    }

    public static synchronized NoOpFeatureFlagProvider getNoOpProvider() {
        if (mNoOpProvider == null) {
            mNoOpProvider = new NoOpFeatureFlagProvider();
        }
        return mNoOpProvider;
    }
}
