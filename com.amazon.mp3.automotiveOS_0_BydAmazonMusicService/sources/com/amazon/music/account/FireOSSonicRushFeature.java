package com.amazon.music.account;

import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import com.amazon.music.platform.featuregate.rules.OrRule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class FireOSSonicRushFeature implements Feature {
    private static final Logger LOG = LoggerFactory.getLogger(FireOSSonicRushFeature.class.getSimpleName());
    public static String sUseSonicRushWebLab = "use_22_14_10_weblab";

    @Override // com.amazon.music.platform.featuregate.Feature
    public Rule getRule() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BooleanConfigurationRule(sUseSonicRushWebLab));
        arrayList.add(getWeblabTreatmentRule());
        return new AndRule(arrayList, false);
    }

    public Rule getWeblabTreatmentRule() {
        ArrayList arrayList = new ArrayList();
        WeblabTreatment weblabTreatment = WeblabTreatment.C;
        arrayList.add(new WeblabRule("DM_FIRETABLET_22_14_10_LAUNCH_635744", weblabTreatment));
        arrayList.add(new WeblabRule("FIRETABLET_22_14_10_EXPOSURE_642781", weblabTreatment));
        return new OrRule(arrayList, true);
    }

    public boolean isEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            LOG.error("Error fetching FeatureGateProvider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(this);
    }
}
