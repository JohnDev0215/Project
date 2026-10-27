package com.amazon.music.find.featuregating;

import android.util.Log;
import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.ExperimentalWeblabRule;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import com.amazon.music.platform.featuregate.rules.FalseRule;
import com.amazon.music.platform.featuregate.rules.OrRule;
import com.amazon.music.platform.find.FeatureGateKeys;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: FindFeatureGating.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0086\u0001\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001%B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$¨\u0006&"}, d2 = {"Lcom/amazon/music/find/featuregating/FindFeatureGating;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", Constants.ENABLE_DISABLE, "", "provider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "BLENDED_SEARCH_HISTORY", "CACHE_SEARCH_RESULTS", "ARTIST_IMAGE_CACHING_IMPROVEMENT", "FIND_LANDING_SKYFIRE_GROUPINGS_CATEGORIES", "RELATED_ENTITY_SEARCH", "RELATED_ENTITY_SEARCH_FREE", "COMMUNITY_PLAYLIST_ENABLED", "COMMUNITY_PLAYLIST_FREE_TIER_ENABLED", "USER_PLAYLIST_FOR_OFFLINE_SEARCH_ENABLED", "USER_PLAYLIST_SHOW_UPSELL_ENABLED", "VIDEO_SEARCH_ENABLED", "VIDEO_PLAYLIST_SEARCH_ENABLED", "FIND_LANDING_REACT_NATIVE", "FIND_DJ_MODE_ENABLED", "FIND_GENRE_ARTWORK_ENABLED", "FIND_MERCH_SHOP_ENABLED", "LANGUAGE_OF_PERFORMANCE_BADGING", "FIND_LANDING_SKYFIRE_FRAGMENT", "SEARCH_SKYFIRE_FRAGMENT", "ALEXA_SEARCH_FIND_BROWSE", "ALEXA_SEARCH_ORDINAL_PLAYBACK", "ALEXA_SEARCH_FIND_BROWSE_CAR_MODE", "INSTANT_SEARCH_API_ENTITIES", "INSTANT_SEARCH_API_ENTITIES_WITH_OVERFLOW", "INSTANT_SEARCH_API_ENTITIES_SR_FT", "HIDE_TRACKS_IN_LOCAL_SUGGESTIONS_AND_HISTORY", "BLUETOOTH_SEARCH_ENABLED", "TRACK_SEARCH_SFA", "UPSELL_ON_FIND_PAGE", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum FindFeatureGating implements Feature {
    BLENDED_SEARCH_HISTORY { // from class: com.amazon.music.find.featuregating.FindFeatureGating.BLENDED_SEARCH_HISTORY
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.BLENDED_SEARCH_HISTORY.getId());
        }
    },
    CACHE_SEARCH_RESULTS { // from class: com.amazon.music.find.featuregating.FindFeatureGating.CACHE_SEARCH_RESULTS
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.CACHE_SEARCH_RESULTS.getId());
        }
    },
    ARTIST_IMAGE_CACHING_IMPROVEMENT { // from class: com.amazon.music.find.featuregating.FindFeatureGating.ARTIST_IMAGE_CACHING_IMPROVEMENT
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.ARTIST_IMAGE_CACHING_IMPROVEMENT.getId());
        }
    },
    FIND_LANDING_SKYFIRE_GROUPINGS_CATEGORIES { // from class: com.amazon.music.find.featuregating.FindFeatureGating.FIND_LANDING_SKYFIRE_GROUPINGS_CATEGORIES
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.FIND_LANDING_SKYFIRE_GROUPINGS_CATEGORIES.getId());
        }
    },
    RELATED_ENTITY_SEARCH { // from class: com.amazon.music.find.featuregating.FindFeatureGating.RELATED_ENTITY_SEARCH
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new BooleanConfigurationRule[]{new BooleanConfigurationRule(FeatureGateKeys.RELATED_ENTITY_SEARCH.getId()), new BooleanConfigurationRule(FeatureGateKeys.RELATED_ENTITY_SEARCH_FREE.getId())}), false, 2, null);
        }
    },
    RELATED_ENTITY_SEARCH_FREE { // from class: com.amazon.music.find.featuregating.FindFeatureGating.RELATED_ENTITY_SEARCH_FREE
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.RELATED_ENTITY_SEARCH_FREE.getId());
        }
    },
    COMMUNITY_PLAYLIST_ENABLED { // from class: com.amazon.music.find.featuregating.FindFeatureGating.COMMUNITY_PLAYLIST_ENABLED
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new WeblabRule(FindWeblab.MUSIC_FIND_ANDROID_COMMUNITY_PLAYLIST_618672.getId(), WeblabTreatment.T1), new BooleanConfigurationRule(FeatureGateKeys.COMMUNITY_PLAYLIST_ENABLED.getId())}), false, 2, null);
        }
    },
    COMMUNITY_PLAYLIST_FREE_TIER_ENABLED { // from class: com.amazon.music.find.featuregating.FindFeatureGating.COMMUNITY_PLAYLIST_FREE_TIER_ENABLED
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new WeblabRule(FindWeblab.MUSIC_FIND_ANDROID_COMMUNITY_PLAYLIST_FREE_TIER_658184.getId(), WeblabTreatment.T1), new BooleanConfigurationRule(FeatureGateKeys.COMMUNITY_PLAYLIST_FREE_TIER_ENABLED.getId())}), false, 2, null);
        }
    },
    USER_PLAYLIST_FOR_OFFLINE_SEARCH_ENABLED { // from class: com.amazon.music.find.featuregating.FindFeatureGating.USER_PLAYLIST_FOR_OFFLINE_SEARCH_ENABLED
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.USER_PLAYLIST_FOR_OFFLINE_SEARCH_ENABLED.getId());
        }
    },
    USER_PLAYLIST_SHOW_UPSELL_ENABLED { // from class: com.amazon.music.find.featuregating.FindFeatureGating.USER_PLAYLIST_SHOW_UPSELL_ENABLED
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.USER_PLAYLIST_SHOW_UPSELL_ENABLED.getId());
        }
    },
    VIDEO_SEARCH_ENABLED { // from class: com.amazon.music.find.featuregating.FindFeatureGating.VIDEO_SEARCH_ENABLED
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.VIDEO_SEARCH.getId());
        }
    },
    VIDEO_PLAYLIST_SEARCH_ENABLED { // from class: com.amazon.music.find.featuregating.FindFeatureGating.VIDEO_PLAYLIST_SEARCH_ENABLED
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.VIDEO_PLAYLIST_SEARCH.getId());
        }
    },
    FIND_LANDING_REACT_NATIVE { // from class: com.amazon.music.find.featuregating.FindFeatureGating.FIND_LANDING_REACT_NATIVE
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(FindWeblab.MUSIC_FIND_ANDROID_REACT_NATIVE_FIND_PAGE_697436.getId(), WeblabTreatment.T1);
        }
    },
    FIND_DJ_MODE_ENABLED { // from class: com.amazon.music.find.featuregating.FindFeatureGating.FIND_DJ_MODE_ENABLED
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(FindWeblab.DM_DJ_MODE_FIND_PAGE_BUTTON_NATIVE_MOBILE_729247.getId(), WeblabTreatment.T1);
        }
    },
    FIND_GENRE_ARTWORK_ENABLED { // from class: com.amazon.music.find.featuregating.FindFeatureGating.FIND_GENRE_ARTWORK_ENABLED
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new FalseRule();
        }
    },
    FIND_MERCH_SHOP_ENABLED { // from class: com.amazon.music.find.featuregating.FindFeatureGating.FIND_MERCH_SHOP_ENABLED
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(FindWeblab.DM_ALPS_MERCH_SHOP_MOBILE_403637.getId(), WeblabTreatment.T1);
        }
    },
    LANGUAGE_OF_PERFORMANCE_BADGING { // from class: com.amazon.music.find.featuregating.FindFeatureGating.LANGUAGE_OF_PERFORMANCE_BADGING
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(FindWeblab.MUSIC_FIND_LOP_ANDROID_664030.getId(), WeblabTreatment.T1);
        }
    },
    FIND_LANDING_SKYFIRE_FRAGMENT { // from class: com.amazon.music.find.featuregating.FindFeatureGating.FIND_LANDING_SKYFIRE_FRAGMENT
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(FindWeblab.DM_FIND_ANDROID_FIND_LANDING_SKYFIRE_FRAGMENT2_430270.getId(), WeblabTreatment.T1);
        }
    },
    SEARCH_SKYFIRE_FRAGMENT { // from class: com.amazon.music.find.featuregating.FindFeatureGating.SEARCH_SKYFIRE_FRAGMENT
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(FindWeblab.DM_FIND_ANDROID_SEARCH_SKYFIRE_PARENT_410082.getId(), WeblabTreatment.T1);
        }
    },
    ALEXA_SEARCH_FIND_BROWSE { // from class: com.amazon.music.find.featuregating.FindFeatureGating.ALEXA_SEARCH_FIND_BROWSE
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(FindWeblab.MUSIC_FIND_SEARCH_FIND_BROWSE_ANDROID_424295.getId(), WeblabTreatment.T1);
        }
    },
    ALEXA_SEARCH_ORDINAL_PLAYBACK { // from class: com.amazon.music.find.featuregating.FindFeatureGating.ALEXA_SEARCH_ORDINAL_PLAYBACK
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new WeblabRule(FindWeblab.MUSIC_FIND_SEARCH_FIND_BROWSE_ANDROID_424295.getId(), WeblabTreatment.T1), new BooleanConfigurationRule(FeatureGateKeys.ALEXA_SEARCH_ORDINAL_PLAYBACK.getId())}), false, 2, null);
        }
    },
    ALEXA_SEARCH_FIND_BROWSE_CAR_MODE { // from class: com.amazon.music.find.featuregating.FindFeatureGating.ALEXA_SEARCH_FIND_BROWSE_CAR_MODE
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.ALEXA_SEARCH_FIND_BROWSE_CAR_MODE.getId());
        }
    },
    INSTANT_SEARCH_API_ENTITIES { // from class: com.amazon.music.find.featuregating.FindFeatureGating.INSTANT_SEARCH_API_ENTITIES
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            String id = FindWeblab.MUSIC_FIND_INSTANT_SEARCH_ENTITIES_614271.getId();
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new WeblabRule[]{new WeblabRule(id, weblabTreatment), new WeblabRule(FindWeblab.MUSIC_FIND_INSTANT_SEARCH_ENTITIES_CHILD_614273.getId(), weblabTreatment)}), false, 2, null);
        }
    },
    INSTANT_SEARCH_API_ENTITIES_WITH_OVERFLOW { // from class: com.amazon.music.find.featuregating.FindFeatureGating.INSTANT_SEARCH_API_ENTITIES_WITH_OVERFLOW
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            String id = FindWeblab.MUSIC_FIND_INSTANT_SEARCH_ENTITIES_614271.getId();
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new WeblabRule[]{new WeblabRule(id, weblabTreatment), new WeblabRule(FindWeblab.DM_FIND_ANDROID_INSTANT_SEARCH_ENTITIES_WITH_OVERFLOW_711468.getId(), weblabTreatment)}), false, 2, null);
        }
    },
    INSTANT_SEARCH_API_ENTITIES_SR_FT { // from class: com.amazon.music.find.featuregating.FindFeatureGating.INSTANT_SEARCH_API_ENTITIES_SR_FT
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(FindWeblab.DM_FIND_ANDROID_SHOW_INSTANT_SEARCH_ENTITIES_SR_FT_723093.getId(), WeblabTreatment.T1);
        }
    },
    HIDE_TRACKS_IN_LOCAL_SUGGESTIONS_AND_HISTORY { // from class: com.amazon.music.find.featuregating.FindFeatureGating.HIDE_TRACKS_IN_LOCAL_SUGGESTIONS_AND_HISTORY
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule(FeatureGateKeys.HIDE_HISTORY_AND_LOCAL_SUGGESTION_TRACK_ENTITIES.getId()), new WeblabRule(FindWeblab.DM_FIND_ANDROID_INSTANT_UNLOCK_744519.getId(), WeblabTreatment.T1)}), false, 2, null);
        }
    },
    BLUETOOTH_SEARCH_ENABLED { // from class: com.amazon.music.find.featuregating.FindFeatureGating.BLUETOOTH_SEARCH_ENABLED
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(FindWeblab.MUSIC_FIND_FIRETABLET_BLUETOOTH_SEARCH_626484.getId(), WeblabTreatment.T1);
        }
    },
    TRACK_SEARCH_SFA { // from class: com.amazon.music.find.featuregating.FindFeatureGating.TRACK_SEARCH_SFA
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule(FindWeblab.MUSIC_FIND_ANDROID_TRACK_SEARCH_SFA_719382.getId(), WeblabTreatment.T1);
        }
    },
    UPSELL_ON_FIND_PAGE { // from class: com.amazon.music.find.featuregating.FindFeatureGating.UPSELL_ON_FIND_PAGE
        @Override // com.amazon.music.find.featuregating.FindFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            String id = FindWeblab.DM_ASTERIX_ANDROID_AA_UPSELLS_ON_FIND_HOME_610631.getId();
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule(FeatureGateKeys.UPSELL_ON_FIND_PAGE.getId()), new ExperimentalWeblabRule(new WeblabRule(id, weblabTreatment), new WeblabRule(FindWeblab.DM_ASTERIX_ANDROID_AB_UPSELLS_ON_FIND_HOME_610633.getId(), weblabTreatment))}), false, 2, null);
        }
    };

    private static final String TAG = Feature.class.getSimpleName();

    /* synthetic */ FindFeatureGating(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();

    public final boolean isEnabled() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FeatureGateProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof FeatureGateProvider)) {
            interfaceProvider = null;
        }
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) interfaceProvider;
        if (featureGateProvider == null) {
            Log.e(TAG, Intrinsics.stringPlus("Error fetching feature gate provider got null: ", name()));
            return false;
        }
        return isEnabled(featureGateProvider);
    }

    public final boolean isEnabled(FeatureGateProvider provider) {
        if (provider == null) {
            return false;
        }
        return provider.isFeatureAvailable(this);
    }
}
