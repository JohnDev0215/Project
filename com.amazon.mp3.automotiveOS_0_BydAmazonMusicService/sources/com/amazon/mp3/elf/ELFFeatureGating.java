package com.amazon.mp3.elf;

import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.OrRule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: ExplicitLanguageManager.java */
/* JADX INFO: loaded from: classes3.dex */
class ELFFeatureGating implements Feature {
    private static String configKey = "explicit_language_filter";
    private static final Logger logger = LoggerFactory.getLogger(ELFFeatureGating.class.getSimpleName());
    private static String weblabName = "ELF_AMAZON_MUSIC_EXPERIENCE_ANDROID_431241";

    ELFFeatureGating() {
    }

    @Override // com.amazon.music.platform.featuregate.Feature
    public Rule getRule() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WeblabRule(weblabName, WeblabTreatment.T1));
        arrayList.add(new BooleanConfigurationRule(configKey));
        return new OrRule(arrayList, true);
    }

    public boolean isEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            logger.error("Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(this) && isTerritoryEnabledForNARowWeblab();
    }

    public boolean isTerritoryEnabledForNARowWeblab() {
        return !AccountDetailUtil.get().getHomeMarketPlace().getObfuscatedId().equals(Marketplace.ROW_NA.getObfuscatedId()) || new ArrayList(Arrays.asList(MusicTerritory.MEXICO.value, MusicTerritory.CANADA.value, MusicTerritory.BRAZIL.value)).contains(AccountDetailUtil.getMusicTerritoryOfResidence());
    }
}
