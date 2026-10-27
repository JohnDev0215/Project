package com.amazon.music.account;

import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class SonicRushFeature implements Feature {
    private static final Logger LOG = LoggerFactory.getLogger(SonicRushFeature.class.getSimpleName());

    @Override // com.amazon.music.platform.featuregate.Feature
    public Rule getRule() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FireOSSonicRushFeature().getRule());
        arrayList.add(new BooleanConfigurationRule(K$FeatureGatingKeys.sSonicRushEnabled));
        return new AndRule(arrayList, true);
    }

    public boolean isEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            LOG.error("Error fetching FeatureGateProvider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(this);
    }
}
