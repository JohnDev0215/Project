package com.amazon.podcast.featuregating;

import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import com.amazon.music.platform.featuregate.rules.OrRule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.podcast.Podcast;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public enum PodcastFeatureGating implements Feature {
    PODCAST { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.1
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            String strMusicTerritory = Podcast.getUserInfoProvider().musicTerritory();
            ArrayList arrayList = new ArrayList();
            arrayList.add("PE");
            arrayList.add("EC");
            arrayList.add("CR");
            arrayList.add("BO");
            arrayList.add("GT");
            arrayList.add("NI");
            arrayList.add("PA");
            arrayList.add("SV");
            arrayList.add("UY");
            arrayList.add("PY");
            arrayList.add("DO");
            arrayList.add("HN");
            if (arrayList.contains(strMusicTerritory)) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new BooleanConfigurationRule(K$FeatureGateKeys.sPodcast));
                arrayList2.add(new WeblabRule(K$Weblabs.sPodcastLatamLaunch, WeblabTreatment.T1));
                return new AndRule(arrayList2, true);
            }
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcast);
        }
    },
    CURATED_CONTENT { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.2
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastCuratedContent);
        }
    },
    USER_CONTENT { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.3
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastUserContent);
        }
    },
    FEATURE_PLAY { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.4
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastFeaturePlay);
        }
    },
    SHARING { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.5
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastSharing);
        }
    },
    TASTE_COLLECTION { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.6
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastTasteCollection);
        }
    },
    HEARTBEAT { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.7
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastHeartbeat);
        }
    },
    LOCAL_HEARTBEAT { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.8
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastLocalHeartbeat);
        }
    },
    REQUEST_FILTER { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.9
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastRequestsFilter);
        }
    },
    SCRUB_TRANSCRIPTION { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.10
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastScrubTranscription);
        }
    },
    ON_PAGE_VIEWED_INCREMENTAL_SYNC { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.11
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastOnPageViewedIncrementalSync);
        }
    },
    ON_PAGE_VIEWED_INCREMENTAL_SYNC_WITH_OFFLINE_FIX { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.12
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastOnPageViewedIncrementalSyncWithOfflineFix);
        }
    },
    PLAYER_BOOKMARK_DB_BINDINGS { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.13
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastPlayerBookmarkDbBindings);
        }
    },
    INCREMENTAL_SYNC_ON_MINI_PLAYER_VIEWED { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.14
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastIncrementalSyncOnMiniplayerViewed);
        }
    },
    PODCAST_COMPRESSED_REQUEST { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.15
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastCompressedRequest);
        }
    },
    FOLLOW_ENHANCEMENTS_LIBRARY_ARCUS { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.16
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastFollowEnhancementsLibrary);
        }
    },
    FOLLOW_ENHANCEMENTS_LIBRARY_WEBLAB { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.17
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            String str = K$Weblabs.sPodcastFollowEnhancementsAA;
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            arrayList.add(new WeblabRule(str, weblabTreatment));
            arrayList.add(new WeblabRule(K$Weblabs.sPodcastFollowEnhancementsAA, WeblabTreatment.C));
            OrRule orRule = new OrRule(arrayList, true);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(orRule);
            arrayList2.add(new WeblabRule(K$Weblabs.sPodcastFollowEnhancementsAB, weblabTreatment));
            return new AndRule(arrayList2, true);
        }
    },
    PODCAST_ON_HARLEY { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.18
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastOnHarley);
        }
    },
    PODCAST_ON_HARLEY_WEBLAB { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.19
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            ArrayList arrayList = new ArrayList();
            String str = K$Weblabs.sPodcastOnHarleyLaunch;
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            arrayList.add(new WeblabRule(str, weblabTreatment));
            arrayList.add(new WeblabRule(K$Weblabs.sPodcastOnHarleyAB, weblabTreatment));
            return new AndRule(arrayList, true);
        }
    },
    PODCAST_YA_IMMERSE_NPS { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.20
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(K$Weblabs.sPodcastYAImmerseNPSLaunch, WeblabTreatment.T1);
        }
    },
    IS_VISUAL_REFRESH_ENABLED { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.21
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sIsVisualRefreshEnabled);
        }
    },
    IS_SONIC_RUSH { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.22
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sIsSonicRushEnabled);
        }
    },
    PODCAST_ADD_TO_PRESETS { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.23
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPodcastAddToPreset);
        }
    },
    PODCAST_BITES_FROM_LATEST { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.24
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(K$Weblabs.sPodcastBitesOnLatest, WeblabTreatment.T1);
        }
    },
    PODCAST_BITES_FROM_PLAYLIST { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.25
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(K$Weblabs.sPodcastBitesOnPlaylist, WeblabTreatment.T1);
        }
    },
    PODCAST_EPISODE_REPORTING { // from class: com.amazon.podcast.featuregating.PodcastFeatureGating.26
        @Override // com.amazon.podcast.featuregating.PodcastFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(K$Weblabs.sPodcastEpisodeReporting, WeblabTreatment.T1);
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(PodcastFeatureGating.class.getSimpleName());

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
