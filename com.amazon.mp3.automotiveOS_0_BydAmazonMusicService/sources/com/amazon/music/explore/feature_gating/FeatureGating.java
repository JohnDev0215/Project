package com.amazon.music.explore.feature_gating;

import androidx.annotation.NonNull;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public enum FeatureGating implements Feature {
    ADPMobileSkillUnificationFeature { // from class: com.amazon.music.explore.feature_gating.FeatureGating.1
        @Override // com.amazon.music.explore.feature_gating.FeatureGating, com.amazon.music.platform.featuregate.Feature
        @NonNull
        public Rule getRule() {
            return new WeblabRule("DM_EXPLORE_SKILL_UNIFICATION_564022", WeblabTreatment.T1);
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(FeatureGating.class.getSimpleName());

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();

    public boolean isEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            logger.error("Error fetching Feature gate provider!!");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(this);
    }
}
