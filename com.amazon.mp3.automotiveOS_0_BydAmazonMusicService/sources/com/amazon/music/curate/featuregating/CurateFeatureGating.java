package com.amazon.music.curate.featuregating;

import com.amazon.music.curate.featuregating.K;
import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.ExperimentalLaunchWeblabRule;
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
public enum CurateFeatureGating implements Feature {
    LIKES_EVERYWHERE_ANDROID { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.1
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            String str = K.Weblabs.curateAndroidLikesEverywhereAA;
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            arrayList.add(new WeblabRule(str, weblabTreatment));
            arrayList.add(new WeblabRule(K.Weblabs.curateAndroidLikesEverywhereAA, WeblabTreatment.C));
            OrRule orRule = new OrRule(arrayList, true);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(orRule);
            arrayList2.add(new WeblabRule(K.Weblabs.curateAndroidLikesEverywhereAB, weblabTreatment));
            return new AndRule(arrayList2, true);
        }
    },
    LIKES_EVERYWHERE_FIREOS { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.2
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            String str = K.Weblabs.curateFireOSLikesEverywhereAA;
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            arrayList.add(new WeblabRule(str, weblabTreatment));
            arrayList.add(new WeblabRule(K.Weblabs.curateFireOSLikesEverywhereAA, WeblabTreatment.C));
            OrRule orRule = new OrRule(arrayList, true);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(orRule);
            arrayList2.add(new WeblabRule(K.Weblabs.curateFireOSLikesEverywhereAB, weblabTreatment));
            return new AndRule(arrayList2, true);
        }
    },
    NATIVE_PROFILE_MY_LIBRARY_PAGE_ANDROID { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.3
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            arrayList.add(new WeblabRule(K.Weblabs.curateAndroidNativeProfileAA, weblabTreatment));
            arrayList.add(new WeblabRule(K.Weblabs.curateAndroidNativeProfileAA, WeblabTreatment.C));
            OrRule orRule = new OrRule(arrayList, true);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(orRule);
            arrayList2.add(new WeblabRule(K.Weblabs.curateAndroidNativeProfileAB, weblabTreatment));
            return new AndRule(arrayList2, true);
        }
    },
    IMPORT_PLAYLIST { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.4
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new BooleanConfigurationRule("playlist_import"));
            String str = K.Weblabs.importPlaylistLaunch;
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            arrayList.add(new ExperimentalLaunchWeblabRule(new WeblabRule(str, weblabTreatment), new WeblabRule(K.Weblabs.importPlaylistAA, weblabTreatment), new WeblabRule(K.Weblabs.importPlaylistAB, WeblabTreatment.T2)));
            return new AndRule(arrayList, true);
        }
    },
    SHOW_ONE_CLICK_BUTTON_PLAYLIST { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.5
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            String str = K.Weblabs.oneClickPlaylistExperimentLaunch;
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            arrayList.add(new ExperimentalLaunchWeblabRule(new WeblabRule(str, weblabTreatment), new WeblabRule(K.Weblabs.oneClickPlaylistExperimentAA, weblabTreatment), new WeblabRule(K.Weblabs.oneClickPlaylistExperimentAB, weblabTreatment)));
            return new AndRule(arrayList, true);
        }
    },
    SHOW_NEW_PLAYLIST_BUTTON_LIBRARY { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.6
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("show_new_playlist_button_library");
        }
    },
    LIBRARY_FOLLOWED_ARTIST { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.7
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new WeblabRule(K.Weblabs.curateLibraryFollowedArtist, WeblabTreatment.T1));
            return new AndRule(arrayList, true);
        }
    },
    TRUE_LIKES_AND_FOLLOWS_ANDROID { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.8
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new WeblabRule(K.Weblabs.curateTrueLikesAndFollowsAndroid, WeblabTreatment.T1));
            return new AndRule(arrayList, true);
        }
    },
    TRUE_LIKES_AND_FOLLOWS_FIRE_OS { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.9
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new WeblabRule(K.Weblabs.curateTrueLikesAndFollowsFireOS, WeblabTreatment.T1));
            return new AndRule(arrayList, true);
        }
    },
    DISABLE_SONG_HISTORY_LINK { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.10
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new BooleanConfigurationRule(K.FeatureGateKeys.curateSongHistory));
            return new AndRule(arrayList, true);
        }
    },
    NATIVE_PROFILE_USERNAME_ANDROID { // from class: com.amazon.music.curate.featuregating.CurateFeatureGating.11
        @Override // com.amazon.music.curate.featuregating.CurateFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new WeblabRule(K.Weblabs.curateAndroidNativeProfileUsername, WeblabTreatment.T1));
            return new AndRule(arrayList, true);
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(CurateFeatureGating.class.getSimpleName());

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();

    public boolean isEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            logger.error("Error fetching Curate FeatureGateProvider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(this);
    }
}
