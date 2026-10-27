package com.amazon.music.push.config;

import android.util.Log;
import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.providers.FeatureGateProvider;

/* JADX INFO: loaded from: classes4.dex */
public class PushMtsEnabledFeature implements Feature {
    private static final String TAG = "PushMtsEnabledFeature";

    @Override // com.amazon.music.platform.featuregate.Feature
    public Rule getRule() {
        return new BooleanConfigurationRule("is_push_mts_enabled");
    }

    public boolean isEnabled() {
        FeatureGateProvider featureGateProvider = ConfigProvider.getFeatureGateProvider();
        if (featureGateProvider == null) {
            Log.e(TAG, "Feature gate provider is null");
            return false;
        }
        return featureGateProvider.isFeatureAvailable(this);
    }
}
