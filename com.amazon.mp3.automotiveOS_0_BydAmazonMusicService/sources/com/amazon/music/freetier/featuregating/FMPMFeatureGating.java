package com.amazon.music.freetier.featuregating;

import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.freetier.featuregating.rules.ExperimentalWeblabWithLoggingRule;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import com.amazon.music.platform.featuregate.rules.OrRule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public enum FMPMFeatureGating implements Feature {
    PLAYLIST_FOLLOW_TEXT_CHANGE { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.1
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPlaylistFollowTextChange);
        }
    },
    AOM_FREE_TIER { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.2
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sAOMFreeTier);
        }
    },
    PROJECT_PHOENIX_DISABLE_UPSELL_CTA { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.3
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sProjectPhoenixDisableUpsellCTA);
        }
    },
    FREE_TIER_TO_CQE_NON_STATIONS { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.4
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sFreeTierToCqeNonStations);
        }
    },
    OVERRIDE_AUGMENTED_SHUFFLE_WITH_SFA { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.5
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            String str = K$Weblabs.sOverrideAugmentedShuffleWithSfaAa;
            WeblabTreatment weblabTreatment = WeblabTreatment.C;
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.sOverrideAugmentedShuffleWithSfa), new ExperimentalWeblabWithLoggingRule(new WeblabRule(str, weblabTreatment), new WeblabRule(K$Weblabs.sOverrideAugmentedShuffleWithSfa, weblabTreatment))), true);
        }
    },
    REROUTE_TO_PLAYLIST_LANDING { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.6
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sRerouteToPlaylistLanding);
        }
    },
    PLAYBACK_CONTAINER { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.7
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sPlaybackContainer);
        }
    },
    SEARCH_ON_DEMAND_RELATED_PLAYLIST_TRACKS_WIDGET { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.8
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sSearchOnDemandRelatedPlaylistTracksWidget);
        }
    },
    ENABLE_PLAYLIST_FOLLOW { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.9
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sEnablePlaylistFollow);
        }
    },
    SHOW_FT_TRACK_DETAILS_PAGE { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.10
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.sShowTrackDetailsPage), new OrRule(Arrays.asList(new WeblabRule(K$Weblabs.sShowFTTrackDetailsPage, WeblabTreatment.T1), new WeblabRule(K$Weblabs.sShowFTTrackDetailsPage, WeblabTreatment.T2)), true)), true);
        }
    },
    SHOW_SR_TRACK_DETAILS_PAGE { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.11
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.sShowTrackDetailsPage), new OrRule(Arrays.asList(new WeblabRule(K$Weblabs.sShowSRTrackDetailsPage, WeblabTreatment.T1), new WeblabRule(K$Weblabs.sShowSRTrackDetailsPage, WeblabTreatment.T2)), true)), true);
        }
    },
    SHOULD_REMOVE_TRACKLIST { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.12
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            String str = K$Weblabs.sHideOrRemoveTracklistAa;
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.sHideOrRemoveTracklist), new ExperimentalWeblabWithLoggingRule(new WeblabRule(str, weblabTreatment), new WeblabRule(K$Weblabs.sHideOrRemoveTracklistAb, weblabTreatment))), true);
        }
    },
    SHOULD_HIDE_TRACKLIST { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.13
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            String str = K$Weblabs.sHideOrRemoveTracklistAa;
            WeblabTreatment weblabTreatment = WeblabTreatment.T2;
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.sHideOrRemoveTracklist), new ExperimentalWeblabWithLoggingRule(new WeblabRule(str, weblabTreatment), new WeblabRule(K$Weblabs.sHideOrRemoveTracklistAb, weblabTreatment))), true);
        }
    },
    ON_DEMAND_PLAYLISTS { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.14
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sOnDemandPlaylists);
        }
    },
    IS_HOTSPOT_ENABLED { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.15
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sHotspots);
        }
    },
    IS_ALL_ACCESS_TITLE_HIGHLIGHT_ENABLED { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.16
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sAllAccessTitleHighlight);
        }
    },
    FREE_TIER_DEEPLINKING_ENABLED { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.17
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sFreeTierDeeplinking);
        }
    },
    IS_HOTSPOT_REFACTOR_ENABLED { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.18
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sHotspotsRefactor);
        }
    },
    IS_ALL_ACCESS_BADGING_ENABLED { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.19
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sAllAccessBadging);
        }
    },
    SHOULD_INJECT_ADS { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.20
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sInjectAds);
        }
    },
    SHOULD_MOVE_PURCHASED_TAB { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.21
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(K$FeatureGateKeys.sMovePurchasedTab);
        }
    },
    SHOW_TRACK_DETAILS_TRACK_SFA { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.22
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            String str = K$Weblabs.sShowTrackDetailsAa;
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.sShowTrackDetailsPageFastFollow), new ExperimentalWeblabWithLoggingRule(new WeblabRule(str, weblabTreatment), new WeblabRule(K$Weblabs.sShowTrackDetailsAb, weblabTreatment))), true);
        }
    },
    SHOW_TRACK_DETAILS_ARTIST_AS { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.23
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            String str = K$Weblabs.sShowTrackDetailsAa;
            WeblabTreatment weblabTreatment = WeblabTreatment.T2;
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.sShowTrackDetailsPageFastFollow), new ExperimentalWeblabWithLoggingRule(new WeblabRule(str, weblabTreatment), new WeblabRule(K$Weblabs.sShowTrackDetailsAb, weblabTreatment))), true);
        }
    },
    SHOW_TRACK_DETAILS_REACT_NATIVE_PAGE { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.24
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.sShowTrackDetailsReactNativePage), new WeblabRule(K$Weblabs.sReactNativeEnabled, WeblabTreatment.T1)), true);
        }
    },
    IS_CTA_PLAY_ICON_ENABLED_FOR_ALL_ACCESS_PLAYLISTS { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.25
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.sIsCtaPlayIconEnabled), new WeblabRule(K$Weblabs.sShouldShowCTAPlayIconAndroid, WeblabTreatment.T1)), true);
        }
    },
    SEARCH_AAP_INSTANT_UNLOCK { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.26
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.sSearchAapInstantUnlock), new WeblabRule(K$Weblabs.sSearchAapInstantUnlock, WeblabTreatment.T1)), true);
        }
    },
    ALL_ACCESS_USER_PLAYLIST_SELECTION_TOGGLE { // from class: com.amazon.music.freetier.featuregating.FMPMFeatureGating.27
        @Override // com.amazon.music.freetier.featuregating.FMPMFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(Arrays.asList(new BooleanConfigurationRule(K$FeatureGateKeys.isAllAccessUserPlaylistSelectionToggleEnabled), new WeblabRule(K$Weblabs.sAAUPAllowSelection, WeblabTreatment.T1)), true);
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(FMPMFeatureGating.class.getSimpleName());

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();

    public boolean isEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            logger.error("Error fetching Feature gate provider!!");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(this);
    }

    public boolean isDisabled() {
        return !isEnabled();
    }
}
