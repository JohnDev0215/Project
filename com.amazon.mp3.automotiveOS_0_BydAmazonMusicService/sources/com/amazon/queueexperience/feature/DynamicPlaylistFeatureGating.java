package com.amazon.queueexperience.feature;

import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import com.amazon.music.platform.featuregate.rules.OrRule;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DynamicPlaylistFeatureGating.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/queueexperience/feature/DynamicPlaylistFeatureGating;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", "supports_dynamic_playlists", "dynamic_playlists_t1", "dynamic_playlists_t2", "dynamic_playlists_t3", "DMAndroidQueueExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum DynamicPlaylistFeatureGating implements Feature {
    supports_dynamic_playlists { // from class: com.amazon.queueexperience.feature.DynamicPlaylistFeatureGating.supports_dynamic_playlists
        @Override // com.amazon.queueexperience.feature.DynamicPlaylistFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new WeblabRule[]{new WeblabRule("MUSICSTATIONS_ENTITY_PLAYLIST_INGRESS_446122", WeblabTreatment.T1), new WeblabRule("MUSICSTATIONS_ENTITY_PLAYLIST_INGRESS_446122", WeblabTreatment.T2), new WeblabRule("MUSICSTATIONS_ENTITY_PLAYLIST_INGRESS_446122", WeblabTreatment.T3)}), false, 2, null), new BooleanConfigurationRule("is_entity_playlist_feature_enabled")}), false, 2, null);
        }
    },
    dynamic_playlists_t1 { // from class: com.amazon.queueexperience.feature.DynamicPlaylistFeatureGating.dynamic_playlists_t1
        @Override // com.amazon.queueexperience.feature.DynamicPlaylistFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new WeblabRule("MUSICSTATIONS_ENTITY_PLAYLIST_INGRESS_446122", WeblabTreatment.T1), new BooleanConfigurationRule("is_entity_playlist_feature_enabled")}), false, 2, null);
        }
    },
    dynamic_playlists_t2 { // from class: com.amazon.queueexperience.feature.DynamicPlaylistFeatureGating.dynamic_playlists_t2
        @Override // com.amazon.queueexperience.feature.DynamicPlaylistFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new WeblabRule("MUSICSTATIONS_ENTITY_PLAYLIST_INGRESS_446122", WeblabTreatment.T2), new BooleanConfigurationRule("is_entity_playlist_feature_enabled")}), false, 2, null);
        }
    },
    dynamic_playlists_t3 { // from class: com.amazon.queueexperience.feature.DynamicPlaylistFeatureGating.dynamic_playlists_t3
        @Override // com.amazon.queueexperience.feature.DynamicPlaylistFeatureGating, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new WeblabRule("MUSICSTATIONS_ENTITY_PLAYLIST_INGRESS_446122", WeblabTreatment.T3), new BooleanConfigurationRule("is_entity_playlist_feature_enabled")}), false, 2, null);
        }
    };

    /* synthetic */ DynamicPlaylistFeatureGating(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();
}
