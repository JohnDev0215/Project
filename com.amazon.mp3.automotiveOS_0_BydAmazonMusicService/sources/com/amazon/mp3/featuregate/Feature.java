package com.amazon.mp3.featuregate;

import android.util.Log;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.featuregate.rules.config.ArtistTasteCollectionRuleConfiguration;
import com.amazon.mp3.featuregate.rules.general.UtilContextRule;
import com.amazon.mp3.featuregate.rules.specific.SubscriptionRule;
import com.amazon.mp3.featuregate.rules.specific.SubscriptionType;
import com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton;
import com.amazon.mp3.platform.providers.CachedFeatureFlagProviderImpl;
import com.amazon.mp3.weblab.Weblab;
import com.amazon.music.featuregating.configuration.dataclasses.GatedLocalesInfo;
import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.featuregating.rules.ConfigurationRule;
import com.amazon.music.featuregating.rules.ExperimentalLaunchWeblabRule;
import com.amazon.music.featuregating.rules.ExperimentalWeblabRule;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.featuregate.rules.AndRule;
import com.amazon.music.platform.featuregate.rules.BooleanMethodRule;
import com.amazon.music.platform.featuregate.rules.FailableRule;
import com.amazon.music.platform.featuregate.rules.OrRule;
import com.amazon.music.platform.find.FeatureGateKeys;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.google.firebase.perf.util.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: Feature.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000%\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0003\b¦\u0001\b\u0086\u0001\u0018\u0000 ®\u00012\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0002®\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\tJ\u0010\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bgj\u0002\bhj\u0002\bij\u0002\bjj\u0002\bkj\u0002\blj\u0002\bmj\u0002\bnj\u0002\boj\u0002\bpj\u0002\bqj\u0002\brj\u0002\bsj\u0002\btj\u0002\buj\u0002\bvj\u0002\bwj\u0002\bxj\u0002\byj\u0002\bzj\u0002\b{j\u0002\b|j\u0002\b}j\u0002\b~j\u0002\b\u007fj\u0003\b\u0080\u0001j\u0003\b\u0081\u0001j\u0003\b\u0082\u0001j\u0003\b\u0083\u0001j\u0003\b\u0084\u0001j\u0003\b\u0085\u0001j\u0003\b\u0086\u0001j\u0003\b\u0087\u0001j\u0003\b\u0088\u0001j\u0003\b\u0089\u0001j\u0003\b\u008a\u0001j\u0003\b\u008b\u0001j\u0003\b\u008c\u0001j\u0003\b\u008d\u0001j\u0003\b\u008e\u0001j\u0003\b\u008f\u0001j\u0003\b\u0090\u0001j\u0003\b\u0091\u0001j\u0003\b\u0092\u0001j\u0003\b\u0093\u0001j\u0003\b\u0094\u0001j\u0003\b\u0095\u0001j\u0003\b\u0096\u0001j\u0003\b\u0097\u0001j\u0003\b\u0098\u0001j\u0003\b\u0099\u0001j\u0003\b\u009a\u0001j\u0003\b\u009b\u0001j\u0003\b\u009c\u0001j\u0003\b\u009d\u0001j\u0003\b\u009e\u0001j\u0003\b\u009f\u0001j\u0003\b \u0001j\u0003\b¡\u0001j\u0003\b¢\u0001j\u0003\b£\u0001j\u0003\b¤\u0001j\u0003\b¥\u0001j\u0003\b¦\u0001j\u0003\b§\u0001j\u0003\b¨\u0001j\u0003\b©\u0001j\u0003\bª\u0001j\u0003\b«\u0001j\u0003\b¬\u0001j\u0003\b\u00ad\u0001¨\u0006¯\u0001"}, d2 = {"Lcom/amazon/mp3/featuregate/Feature;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", Constants.ENABLE_DISABLE, "", "featureGateProvider", "Lcom/amazon/mp3/platform/providers/CachedFeatureFlagProviderImpl;", "useCache", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "music_never_stops", "artist_taste_collection", "music_never_stops_prime", "Music_Videos", "BOP_Videos", "Video_Landing_Page", "my_library_v2", "podcasts_toggle_button", "app_display_language", "retail_lop_sync", "shoveler_add_to_playlist", "sonic_rush", "unlimited", "supports_eligibility", "eligibility_based_playback_icon", "t1r_cloud_queue", "remove_bluemoon_web_views", "google_iab_launched_in_territory", "upsell_with_google_iab", "pull_sku_from_deeplink", "artist_upsell", "upsell_tab", "sfa_station", "skip_limit_blocking_upsell", "store_icon", "prime_branding", "unlimited_family_upsell", "hawkfire_all_devices_upsell_dialog_discount_message", "hawkfire_account_upsell_dialog_message", "library_tracks_ad_content", "concurrent_family_upsell_enabled", "updated_profile_edit_page", "basic_profile_edit_page", "prioritize_downloads", "persistent_upsell_prime_sonic_rush_only", "persistent_upgrade_dm_blocking_upsell_amu_home_only", "persistent_upgrade_dm_non_blocking_upsell_amu_home_only", "persistent_upgrade_msf_plan_comparison_amu_home_only", "in_app_survey", "ya_immerse", "ya_immerse_on_large_screen_devices", "cfy_playlists_sorting", "playlists_privacy_enforcement", "playlists_privacy_enforcement_followed_ribbon_feature", "playlists_privacy_enforcement_privacy_status", "playlists_sync_include_follow_count", "artist_commentary", "cq_non_station_playback_restoration", "track_number_capability", "detail_page_upsell", "shuffle_text_visibility", "skip_limits_using_cloud_queue", "row_cta_visibility", "visual_refresh", "playlist_creation_public_toggle", "playlist_recommendations_widget", "is_skips_remaining_tooltip_enabled", "activity_feed_on_react_native", "hide_track_numbers_playlists", "activity_feed_on_react_native_fragment", "Video_Stories", "video_stories_muted_autoPlay", "video_stories_v2_supported", "entity_moderation", "track_moderation", "album_moderation", "cloud_queue_content", "Category_Post_Launch", "display_queue_view", "SR_page_headers", "supports_download_text_button", "supports_legacy_checks_for_capabilities", "library_context_menu_play", "context_menu_play_queue", "context_menu_shuffle", "album_context_menu_add_to_playlist", "playlist_context_menu_download", "track_context_menu_add_to_my_music", "context_menu_delete_from_library", "not_library_or_detail_container_on_demand_context_menu_play", "not_library_or_detail_track_context_menu_add_to_my_music", "not_library_or_detail_track_context_menu_customers_also_listened_to", "casting_v2", "horizontal_row_album_detail_page_station_icon", "playlist_filter_context_menu_button", "album_filter_context_menu_button", "player_context_menu_download_button", "library_songs_page_header_visibility", "locale_supported", "Hide_Mini_Player_PlayQueue", "use_dynamic_messaging_for_non_blocking_upsell", "track_augmented_shuffle", "resume_playqueue_seed_playback", "song_history_toolbar_disabled", "faceted_navigation_browse_home", "faceted_navigation_pin_podcasts_pill", "include_amp_shows", "player_context_menu_view_play_queue_button", "your_ad_privacy_choices_legal_setting_menu", "cookie_consent_eu_workflow", "cookie_consent_ftu_eu_workflow", "prev_button_enabled", "is_car_mode_presets_update_enabled", "hide_artist_context_menu_play_and_download", "hide_song_history_library_page", "casting_previous_button_clickability_override", "haptics_on_playback", "playlist_song_search", "update_purchased_track_asin_substitution", "error_180_flex_event_enabled", "error_cloud_queue_flex_event_enabled", "is_car_mode_play_queue_enabled", "artist_studio_shuffle_sonic_rush", "should_migrate_free_to_cq_for_non_station", "track_shuffle_playback_upsell_nightwing", "track_shuffle_playback_upsell_sonic_rush", "browse_home_playback_upsell", "now_playing_screen_contextual_upsell", "car_mode", "car_mode_auto_play", "car_mode_v2", "car_mode_podcast_episode_list", "car_mode_always_show_disclaimer", "car_mode_additional_disclaimer", "car_mode_auto_launch", "album_detail_performance_improvements_phase1", "album_detail_telemetry_correction", "album_detail_performance_improvements_phase1B", "is_lyrics_feedback_enabled", "feature_play_v2_browse_home", "home_recently_played_from_service", "use_eligibility_recently_played_library_v1", "playlist_import", "video_stories_artist_follow_supported", "pdp_add_songs_button_enabled", "cold_start_animation_logo_perf_improvement", "firebase_performance_monitoring", "use_date_added_default_refinement", "use_optimized_observable_browse_views_factory", "is_all_access_badging_enabled", "early_use_notification", "shell_loading_animation", "user_playlists_eligibility_override", "is_album_on_demand_badging_enabled", "uipageview_should_send_pagesubtype", "bugsnag_breadcrumbs_is_enabled", "preload_associate_tag_without_region_id", "lyrics_sharing", "dragonfly_enabled", "dragonfly_fireos_enabled", "social_profiles_phase3_enabled", "pre_auth_splash_experiment_t1", "pre_auth_splash_experiment_t2", "should_create_entity_item_identifier_by_luid", "android_app_tou_experiment_enabled", "should_show_add_to_library_button", "cfy_firefly_image_upload_enabled", "is_lookup_deprecated", "react_native_performance_profiling", "pdp_follower_count", "add_sharing_to_top_level_action", "search_aap_instant_unlock", "is_all_access_user_playlist_pdp_messaging_enabled", "is_all_access_user_playlist_restrictions_enabled", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum Feature implements com.amazon.music.platform.featuregate.Feature {
    music_never_stops { // from class: com.amazon.mp3.featuregate.Feature.music_never_stops
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            Feature$music_never_stops$getRule$1 feature$music_never_stops$getRule$1 = new Feature$music_never_stops$getRule$1(capabilities);
            boolean z = false;
            int i = 2;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(feature$music_never_stops$getRule$1, z, i, defaultConstructorMarker), new UtilContextRule(Feature$music_never_stops$getRule$2.INSTANCE, z, i, defaultConstructorMarker), new BooleanMethodRule(Feature$music_never_stops$getRule$3.INSTANCE, false), new SubscriptionRule(CollectionsKt__CollectionsKt.listOf((Object[]) new SubscriptionType[]{SubscriptionType.AMU, SubscriptionType.INDIA_FULL_CATALOG_ACCESS}), false, null, 6, null)}), z, i, defaultConstructorMarker);
        }
    },
    artist_taste_collection { // from class: com.amazon.mp3.featuregate.Feature.artist_taste_collection
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new ArtistTasteCollectionRuleConfiguration(null, 1, 0 == true ? 1 : 0).getRules();
        }
    },
    music_never_stops_prime { // from class: com.amazon.mp3.featuregate.Feature.music_never_stops_prime
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            boolean z = false;
            int i = 2;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new UtilContextRule(Feature$music_never_stops_prime$getRule$1.INSTANCE, z, i, defaultConstructorMarker), new BooleanMethodRule(Feature$music_never_stops_prime$getRule$2.INSTANCE, false), new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.PRIME_ONLY), false, null, 6, null), new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.INDIA_FULL_CATALOG_ACCESS), false, null, 4, null)), z, i, defaultConstructorMarker);
        }
    },
    Music_Videos { // from class: com.amazon.mp3.featuregate.Feature.Music_Videos
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            boolean z = false;
            int i = 2;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanMethodRule(new Feature$Music_Videos$getRule$1(capabilities), true), new BooleanMethodRule(Feature$Music_Videos$getRule$2.INSTANCE, true), new FailableRule(new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$Music_Videos$getRule$3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(FeatureFlagsProvider.getFeatureFlagProvider().canAccessVideos());
                }
            }, z, i, defaultConstructorMarker), true, false, 4, null), new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.AMU), true, null, 4, null)), z, i, defaultConstructorMarker);
        }
    },
    BOP_Videos { // from class: com.amazon.mp3.featuregate.Feature.BOP_Videos
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanMethodRule(new Feature$BOP_Videos$getRule$1(capabilities), true), new BooleanMethodRule(Feature$BOP_Videos$getRule$2.INSTANCE, true), new BooleanConfigurationRule("bop_videos")), false, 2, null);
        }
    },
    Video_Landing_Page { // from class: com.amazon.mp3.featuregate.Feature.Video_Landing_Page
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            boolean z = false;
            int i = 2;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanMethodRule(new Feature$Video_Landing_Page$getRule$1(capabilities), true), new BooleanMethodRule(Feature$Video_Landing_Page$getRule$2.INSTANCE, true), new FailableRule(new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$Video_Landing_Page$getRule$3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(FeatureFlagsProvider.getFeatureFlagProvider().isVideoLandingPageEnabled());
                }
            }, z, i, defaultConstructorMarker), true, false, 4, null), new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.AMU), true, null, 4, null)), z, i, defaultConstructorMarker);
        }
    },
    my_library_v2 { // from class: com.amazon.mp3.featuregate.Feature.my_library_v2
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            return new BooleanMethodRule(new Feature$my_library_v2$getRule$1(capabilities), false, 2, null);
        }
    },
    podcasts_toggle_button { // from class: com.amazon.mp3.featuregate.Feature.podcasts_toggle_button
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            return new BooleanMethodRule(new Feature$podcasts_toggle_button$getRule$1(capabilities), false, 2, null);
        }
    },
    app_display_language { // from class: com.amazon.mp3.featuregate.Feature.app_display_language
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(new Feature$app_display_language$getRule$1(capabilities), true), new WeblabRule("DM_MOBILE_UX_LANGUAGE_SELECTOR_684676", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    retail_lop_sync { // from class: com.amazon.mp3.featuregate.Feature.retail_lop_sync
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(new Feature$retail_lop_sync$getRule$1(capabilities), true), new WeblabRule("DM_LPN_RETAIL_LOP_SYNC_688144", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    shoveler_add_to_playlist { // from class: com.amazon.mp3.featuregate.Feature.shoveler_add_to_playlist
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            return new BooleanMethodRule(new Feature$shoveler_add_to_playlist$getRule$1(capabilities), false, 2, null);
        }
    },
    sonic_rush { // from class: com.amazon.mp3.featuregate.Feature.sonic_rush
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            BooleanConfigurationRule booleanConfigurationRule = new BooleanConfigurationRule(FeatureGateKeys.SONIC_RUSH.getId());
            boolean z = false;
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{booleanConfigurationRule, new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(new Feature$sonic_rush$getRule$1(capabilities), true), new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.NIGHTWING_ONLY), true, null, 4, null)}), z, i, defaultConstructorMarker)}), z, i, defaultConstructorMarker);
        }
    },
    unlimited { // from class: com.amazon.mp3.featuregate.Feature.unlimited
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.UNLIMITED.getId());
        }
    },
    supports_eligibility { // from class: com.amazon.mp3.featuregate.Feature.supports_eligibility
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new BooleanConfigurationRule[]{new BooleanConfigurationRule(FeatureGateKeys.SUPPORTS_ELIGIBILITY.getId()), new BooleanConfigurationRule(FeatureGateKeys.RELATED_ENTITY_SEARCH_FREE.getId())}), false, 2, null);
        }
    },
    eligibility_based_playback_icon { // from class: com.amazon.mp3.featuregate.Feature.eligibility_based_playback_icon
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule(FeatureGateKeys.SONIC_RUSH.getId()), new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new BooleanConfigurationRule[]{new BooleanConfigurationRule(FeatureGateKeys.ELIGIBILITY_BASED_PLAYBACK_ICON.getId()), new BooleanConfigurationRule(FeatureGateKeys.RELATED_ENTITY_SEARCH_FREE.getId())}), z, i, defaultConstructorMarker)}), z, i, defaultConstructorMarker);
        }
    },
    t1r_cloud_queue { // from class: com.amazon.mp3.featuregate.Feature.t1r_cloud_queue
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            Feature$t1r_cloud_queue$getRule$1 feature$t1r_cloud_queue$getRule$1 = new Feature$t1r_cloud_queue$getRule$1(capabilities);
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(feature$t1r_cloud_queue$getRule$1, z, i, defaultConstructorMarker), new BooleanConfigurationRule(FeatureGateKeys.T1R_USING_CLOUD_QUEUE.getId())}), z, i, defaultConstructorMarker);
        }
    },
    remove_bluemoon_web_views { // from class: com.amazon.mp3.featuregate.Feature.remove_bluemoon_web_views
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new WeblabRule("DM_BMWV_DEPRECATION_670488", WeblabTreatment.T1)), false, 2, null);
        }
    },
    google_iab_launched_in_territory { // from class: com.amazon.mp3.featuregate.Feature.google_iab_launched_in_territory
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new WeblabRule("DM_ANDROID_IN_APP_BILLING_416333", WeblabTreatment.T1), new BooleanConfigurationRule("google_in_app_billing")), false, 2, null);
        }
    },
    upsell_with_google_iab { // from class: com.amazon.mp3.featuregate.Feature.upsell_with_google_iab
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanMethodRule(Feature$upsell_with_google_iab$getRule$1.INSTANCE, false), new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.NIGHTWING_ONLY), false, null, 6, null), Feature.google_iab_launched_in_territory.getRule()), false, 2, null);
        }
    },
    pull_sku_from_deeplink { // from class: com.amazon.mp3.featuregate.Feature.pull_sku_from_deeplink
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DMUSIC_SUBS_MSS_EVAL_SKU_FROM_DEEP_LINK_689408", WeblabTreatment.T1);
        }
    },
    artist_upsell { // from class: com.amazon.mp3.featuregate.Feature.artist_upsell
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            AccountDetailUtil accountDetailUtil = AccountDetailUtil.get();
            Intrinsics.checkNotNullExpressionValue(accountDetailUtil, "get()");
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new FailableRule(new BooleanMethodRule(new Feature$artist_upsell$getRule$1(accountDetailUtil), true), false, false, 6, null), new BooleanConfigurationRule("artist_upsell")), false, 2, null);
        }
    },
    upsell_tab { // from class: com.amazon.mp3.featuregate.Feature.upsell_tab
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            AccountDetailUtil accountDetailUtil = AccountDetailUtil.get();
            Intrinsics.checkNotNullExpressionValue(accountDetailUtil, "get()");
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new FailableRule(new BooleanMethodRule(new Feature$upsell_tab$getRule$1(accountDetailUtil), true), false, false, 6, null), new BooleanConfigurationRule("upsell_tab")), false, 2, null);
        }
    },
    sfa_station { // from class: com.amazon.mp3.featuregate.Feature.sfa_station
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("sfa_station");
        }
    },
    skip_limit_blocking_upsell { // from class: com.amazon.mp3.featuregate.Feature.skip_limit_blocking_upsell
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("skip_limit_blocking_upsell");
        }
    },
    store_icon { // from class: com.amazon.mp3.featuregate.Feature.store_icon
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("store_icon");
        }
    },
    prime_branding { // from class: com.amazon.mp3.featuregate.Feature.prime_branding
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            AccountDetailUtil accountDetailUtil = AccountDetailUtil.get();
            Intrinsics.checkNotNullExpressionValue(accountDetailUtil, "get()");
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new FailableRule(new BooleanMethodRule(new Feature$prime_branding$getRule$1(accountDetailUtil), false), false, false, 6, null), new BooleanConfigurationRule("prime_branding")), false, 2, null);
        }
    },
    unlimited_family_upsell { // from class: com.amazon.mp3.featuregate.Feature.unlimited_family_upsell
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("unlimited_family_upsell");
        }
    },
    hawkfire_all_devices_upsell_dialog_discount_message { // from class: com.amazon.mp3.featuregate.Feature.hawkfire_all_devices_upsell_dialog_discount_message
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("hawkfire_all_devices_upsell_dialog_discount_message");
        }
    },
    hawkfire_account_upsell_dialog_message { // from class: com.amazon.mp3.featuregate.Feature.hawkfire_account_upsell_dialog_message
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("hawkfire_account_upsell_dialog_message");
        }
    },
    library_tracks_ad_content { // from class: com.amazon.mp3.featuregate.Feature.library_tracks_ad_content
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("library_tracks_ad_content");
        }
    },
    concurrent_family_upsell_enabled { // from class: com.amazon.mp3.featuregate.Feature.concurrent_family_upsell_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(Feature.google_iab_launched_in_territory.getRule(), new OrRule(CollectionsKt__CollectionsKt.mutableListOf(new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.PRIME_ONLY), false, null, 6, null), new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$concurrent_family_upsell_enabled$getRule$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SubscriptionManagerSingleton.isInitialized());
                }
            }, z, i, defaultConstructorMarker), new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$concurrent_family_upsell_enabled$getRule$2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SubscriptionManagerSingleton.getInstance().isOn1PPlan());
                }
            }, z, i, defaultConstructorMarker)), z, i, defaultConstructorMarker)), z, i, defaultConstructorMarker)), z, i, defaultConstructorMarker);
        }
    },
    updated_profile_edit_page { // from class: com.amazon.mp3.featuregate.Feature.updated_profile_edit_page
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ASTERIX_ANDROID_UPDATED_EDIT_PROFILE_DIALOG_462184", WeblabTreatment.T1);
        }
    },
    basic_profile_edit_page { // from class: com.amazon.mp3.featuregate.Feature.basic_profile_edit_page
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_LAUNCH_BASIC_PROFILE_ANDROID_484182", WeblabTreatment.T1);
        }
    },
    prioritize_downloads { // from class: com.amazon.mp3.featuregate.Feature.prioritize_downloads
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("is_prioritize_downloads_supported");
        }
    },
    persistent_upsell_prime_sonic_rush_only { // from class: com.amazon.mp3.featuregate.Feature.persistent_upsell_prime_sonic_rush_only
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            boolean z = false;
            AccountDetailUtil accountDetailUtil = AccountDetailUtil.get();
            Intrinsics.checkNotNullExpressionValue(accountDetailUtil, "get()");
            Feature$persistent_upsell_prime_sonic_rush_only$getRule$1 feature$persistent_upsell_prime_sonic_rush_only$getRule$1 = new Feature$persistent_upsell_prime_sonic_rush_only$getRule$1(accountDetailUtil);
            int i = 2;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("persistent_upsell_prime_sonic_rush_only"), new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.AMU), false, null, 4, null), new BooleanMethodRule(feature$persistent_upsell_prime_sonic_rush_only$getRule$1, z, i, defaultConstructorMarker)), z, i, defaultConstructorMarker);
        }
    },
    persistent_upgrade_dm_blocking_upsell_amu_home_only { // from class: com.amazon.mp3.featuregate.Feature.persistent_upgrade_dm_blocking_upsell_amu_home_only
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.AMU_SDP), false, null, 6, null), new WeblabRule("DMUSIC_SUBS_SDP_PERSISTENT_UPGRADE_ANDROID_695086", WeblabTreatment.T2)), false, 2, null);
        }
    },
    persistent_upgrade_dm_non_blocking_upsell_amu_home_only { // from class: com.amazon.mp3.featuregate.Feature.persistent_upgrade_dm_non_blocking_upsell_amu_home_only
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.AMU_SDP), false, null, 6, null), new WeblabRule("DMUSIC_SUBS_SDP_PERSISTENT_UPGRADE_ANDROID_695086", WeblabTreatment.T3)), false, 2, null);
        }
    },
    persistent_upgrade_msf_plan_comparison_amu_home_only { // from class: com.amazon.mp3.featuregate.Feature.persistent_upgrade_msf_plan_comparison_amu_home_only
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.AMU_SDP), false, null, 6, null), new WeblabRule("DMUSIC_SUBS_SDP_PERSISTENT_UPGRADE_ANDROID_695086", WeblabTreatment.T1)), false, 2, null);
        }
    },
    in_app_survey { // from class: com.amazon.mp3.featuregate.Feature.in_app_survey
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("is_in_app_survey_enabled"), new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$in_app_survey$getRule$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.FALSE;
                }
            }, z, i, defaultConstructorMarker)), z, i, defaultConstructorMarker);
        }
    },
    ya_immerse { // from class: com.amazon.mp3.featuregate.Feature.ya_immerse
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            BooleanConfigurationRule booleanConfigurationRule = new BooleanConfigurationRule(FeatureGateKeys.YA_IMMERSE.getId());
            boolean z = false;
            String string = Weblab.DM_IMMERSE_STAGE_MUSIC_PODCAST_ANDROID.toString();
            Intrinsics.checkNotNullExpressionValue(string, "DM_IMMERSE_STAGE_MUSIC_PODCAST_ANDROID.toString()");
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(booleanConfigurationRule, new WeblabRule(string, WeblabTreatment.T1.getTreatment(), false)), z, 2, null);
        }
    },
    ya_immerse_on_large_screen_devices { // from class: com.amazon.mp3.featuregate.Feature.ya_immerse_on_large_screen_devices
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("ya_immerse_on_large_screen_devices")), false, 2, null);
        }
    },
    cfy_playlists_sorting { // from class: com.amazon.mp3.featuregate.Feature.cfy_playlists_sorting
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("cfy_playlists_sorting"), new WeblabRule("DM_LIBRARY_PLAYLISTS_SUBPAGE_NEW_SORTING_OPTIONS_ANDROID_P1_699259", WeblabTreatment.T1)}), z, i, defaultConstructorMarker), new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$cfy_playlists_sorting$getRule$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.FALSE;
                }
            }, z, i, defaultConstructorMarker)}), z, i, defaultConstructorMarker);
        }
    },
    playlists_privacy_enforcement { // from class: com.amazon.mp3.featuregate.Feature.playlists_privacy_enforcement
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("playlists_privacy_enforcement"), new WeblabRule("MUSIC_PLAYLISTS_PRIVACY_ENFORCEMENT_ANDROID_700186", WeblabTreatment.T1)}), z, i, defaultConstructorMarker), new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$playlists_privacy_enforcement$getRule$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.FALSE;
                }
            }, z, i, defaultConstructorMarker)}), z, i, defaultConstructorMarker);
        }
    },
    playlists_privacy_enforcement_followed_ribbon_feature { // from class: com.amazon.mp3.featuregate.Feature.playlists_privacy_enforcement_followed_ribbon_feature
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new AndRule(CollectionsKt__CollectionsJVMKt.listOf(new BooleanConfigurationRule("playlists_privacy_enforcement_followed_ribbon_feature")), z, i, defaultConstructorMarker), new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$playlists_privacy_enforcement_followed_ribbon_feature$getRule$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.FALSE;
                }
            }, z, i, defaultConstructorMarker)}), z, i, defaultConstructorMarker);
        }
    },
    playlists_privacy_enforcement_privacy_status { // from class: com.amazon.mp3.featuregate.Feature.playlists_privacy_enforcement_privacy_status
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new AndRule(CollectionsKt__CollectionsJVMKt.listOf(new BooleanConfigurationRule("playlists_privacy_enforcement_privacy_status")), z, i, defaultConstructorMarker), new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$playlists_privacy_enforcement_privacy_status$getRule$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.FALSE;
                }
            }, z, i, defaultConstructorMarker)}), z, i, defaultConstructorMarker);
        }
    },
    playlists_sync_include_follow_count { // from class: com.amazon.mp3.featuregate.Feature.playlists_sync_include_follow_count
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new AndRule(CollectionsKt__CollectionsJVMKt.listOf(new BooleanConfigurationRule("playlists_sync_include_follow_count")), z, i, defaultConstructorMarker), new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$playlists_sync_include_follow_count$getRule$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.FALSE;
                }
            }, z, i, defaultConstructorMarker)}), z, i, defaultConstructorMarker);
        }
    },
    artist_commentary { // from class: com.amazon.mp3.featuregate.Feature.artist_commentary
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("artist_commentary"), new WeblabRule("MUSIC_CF_ARTIST_COMMENTARY_ANDROID_633927", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    cq_non_station_playback_restoration { // from class: com.amazon.mp3.featuregate.Feature.cq_non_station_playback_restoration
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            Feature$cq_non_station_playback_restoration$getRule$1 feature$cq_non_station_playback_restoration$getRule$1 = new Feature$cq_non_station_playback_restoration$getRule$1(capabilities);
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanMethodRule(feature$cq_non_station_playback_restoration$getRule$1, z, i, defaultConstructorMarker), new BooleanConfigurationRule("cq_non_station_playback_restoration_v2")), z, i, defaultConstructorMarker);
        }
    },
    track_number_capability { // from class: com.amazon.mp3.featuregate.Feature.track_number_capability
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("track_number_capability");
        }
    },
    detail_page_upsell { // from class: com.amazon.mp3.featuregate.Feature.detail_page_upsell
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("detail_page_upsell_v2");
        }
    },
    shuffle_text_visibility { // from class: com.amazon.mp3.featuregate.Feature.shuffle_text_visibility
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new BooleanConfigurationRule[]{new BooleanConfigurationRule("shuffle_text_visibility"), new BooleanConfigurationRule(FeatureGateKeys.SONIC_RUSH.getId())}), false, 2, null);
        }
    },
    skip_limits_using_cloud_queue { // from class: com.amazon.mp3.featuregate.Feature.skip_limits_using_cloud_queue
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            Feature$skip_limits_using_cloud_queue$getRule$1 feature$skip_limits_using_cloud_queue$getRule$1 = new Feature$skip_limits_using_cloud_queue$getRule$1(capabilities);
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(feature$skip_limits_using_cloud_queue$getRule$1, z, i, defaultConstructorMarker), new BooleanConfigurationRule(FeatureGateKeys.SKIP_LIMITS_USING_CLOUD_QUEUE.getId())}), z, i, defaultConstructorMarker);
        }
    },
    row_cta_visibility { // from class: com.amazon.mp3.featuregate.Feature.row_cta_visibility
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new BooleanConfigurationRule[]{new BooleanConfigurationRule("row_cta_visibility"), new BooleanConfigurationRule(FeatureGateKeys.SONIC_RUSH.getId())}), false, 2, null);
        }
    },
    visual_refresh { // from class: com.amazon.mp3.featuregate.Feature.visual_refresh
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new OrRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$visual_refresh$getRule$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.FALSE;
                }
            }, z, i, defaultConstructorMarker), new BooleanConfigurationRule("is_visual_refresh_enabled")), z, i, defaultConstructorMarker);
        }
    },
    playlist_creation_public_toggle { // from class: com.amazon.mp3.featuregate.Feature.playlist_creation_public_toggle
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_NEW_PLAYLIST_CREATION_FLOW_ANDROID_502663", WeblabTreatment.T1);
        }
    },
    playlist_recommendations_widget { // from class: com.amazon.mp3.featuregate.Feature.playlist_recommendations_widget
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new ExperimentalLaunchWeblabRule(new WeblabRule("DM_CONNECT_PLAYLIST_SUGGESTIONS_LAUNCH_521878", weblabTreatment), new WeblabRule("DM_CONNECT_PLAYLIST_SUGGESTIONS_AA_521421", weblabTreatment), new WeblabRule("DM_CONNECT_PLAYLIST_SUGGESTIONS_AB_521423", weblabTreatment))), false, 2, null);
        }
    },
    is_skips_remaining_tooltip_enabled { // from class: com.amazon.mp3.featuregate.Feature.is_skips_remaining_tooltip_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("is_skips_remaining_tooltip_enabled");
        }
    },
    activity_feed_on_react_native { // from class: com.amazon.mp3.featuregate.Feature.activity_feed_on_react_native
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new ExperimentalLaunchWeblabRule(new WeblabRule("DM_CONNECT_ACTIVITY_FEED_ON_REACT_NATIVE_ANDROID_LAUNCH_505999", weblabTreatment), new WeblabRule("DM_CONNECT_ACTIVITY_FEED_ON_REACT_NATIVE_ANDROID_AA_505998", weblabTreatment), new WeblabRule("DM_CONNECT_ACTIVITY_FEED_ON_REACT_NATIVE_ANDROID_AB_502518", weblabTreatment))), false, 2, null);
        }
    },
    hide_track_numbers_playlists { // from class: com.amazon.mp3.featuregate.Feature.hide_track_numbers_playlists
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("hide_track_numbers_playlists");
        }
    },
    activity_feed_on_react_native_fragment { // from class: com.amazon.mp3.featuregate.Feature.activity_feed_on_react_native_fragment
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_CONNECT_ACTIVITY_FEED_ON_REACT_NATIVE_ANDROID_FRAGMENT_508452", WeblabTreatment.T1);
        }
    },
    Video_Stories { // from class: com.amazon.mp3.featuregate.Feature.Video_Stories
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("video_stories_supported"), new WeblabRule("DM_VIDEO_STORIES_ANDROID_495262", WeblabTreatment.T1)), false, 2, null);
        }
    },
    video_stories_muted_autoPlay { // from class: com.amazon.mp3.featuregate.Feature.video_stories_muted_autoPlay
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("video_stories_muted_autoplay_supported"), new WeblabRule("DM_VIDEO_STORIES_MUTED_AUTOPLAY_ANDROID_632795", WeblabTreatment.T1)), false, 2, null);
        }
    },
    video_stories_v2_supported { // from class: com.amazon.mp3.featuregate.Feature.video_stories_v2_supported
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("video_stories_v2_supported"), new WeblabRule("DM_VIDEO_STORIES_V2_ANDROID_740058", WeblabTreatment.T1)), false, 2, null);
        }
    },
    entity_moderation { // from class: com.amazon.mp3.featuregate.Feature.entity_moderation
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ENTITY_MODERATION_ANDROID_537328", WeblabTreatment.T1);
        }
    },
    track_moderation { // from class: com.amazon.mp3.featuregate.Feature.track_moderation
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_TRACK_MODERATION_ANDROID_679711", WeblabTreatment.T1);
        }
    },
    album_moderation { // from class: com.amazon.mp3.featuregate.Feature.album_moderation
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ALBUM_MODERATION_ANDROID_679713", WeblabTreatment.T1);
        }
    },
    cloud_queue_content { // from class: com.amazon.mp3.featuregate.Feature.cloud_queue_content
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("cloud_queue_content_v2");
        }
    },
    Category_Post_Launch { // from class: com.amazon.mp3.featuregate.Feature.Category_Post_Launch
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("category_page_post"), new WeblabRule("CATEGORY_PAGE_YOUNG_ADULTS_502443", WeblabTreatment.T1)), false, 2, null);
        }
    },
    display_queue_view { // from class: com.amazon.mp3.featuregate.Feature.display_queue_view
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$display_queue_view$getRule$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(new BooleanConfigurationRule(FeatureGateKeys.SONIC_RUSH.getId()).getValue());
                }
            }, false), new BooleanConfigurationRule("display_queue_view")}), false, 2, null);
        }
    },
    SR_page_headers { // from class: com.amazon.mp3.featuregate.Feature.SR_page_headers
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new BooleanConfigurationRule[]{new BooleanConfigurationRule(FeatureGateKeys.SONIC_RUSH.getId()), new BooleanConfigurationRule("SR_page_headers")}), false, 2, null);
        }
    },
    supports_download_text_button { // from class: com.amazon.mp3.featuregate.Feature.supports_download_text_button
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("supports_download_text_button"), new SubscriptionRule(CollectionsKt__CollectionsJVMKt.listOf(SubscriptionType.AMU), false, null, 4, null)}), false, 2, null);
        }
    },
    supports_legacy_checks_for_capabilities { // from class: com.amazon.mp3.featuregate.Feature.supports_legacy_checks_for_capabilities
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("supports_legacy_checks_for_capabilities");
        }
    },
    library_context_menu_play { // from class: com.amazon.mp3.featuregate.Feature.library_context_menu_play
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("library_context_menu_play_v2");
        }
    },
    context_menu_play_queue { // from class: com.amazon.mp3.featuregate.Feature.context_menu_play_queue
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("context_menu_play_queue");
        }
    },
    context_menu_shuffle { // from class: com.amazon.mp3.featuregate.Feature.context_menu_shuffle
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("context_menu_shuffle_v2");
        }
    },
    album_context_menu_add_to_playlist { // from class: com.amazon.mp3.featuregate.Feature.album_context_menu_add_to_playlist
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("album_context_menu_add_to_playlist_v2");
        }
    },
    playlist_context_menu_download { // from class: com.amazon.mp3.featuregate.Feature.playlist_context_menu_download
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("playlist_context_menu_download_v2");
        }
    },
    track_context_menu_add_to_my_music { // from class: com.amazon.mp3.featuregate.Feature.track_context_menu_add_to_my_music
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("track_context_menu_add_to_my_music");
        }
    },
    context_menu_delete_from_library { // from class: com.amazon.mp3.featuregate.Feature.context_menu_delete_from_library
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("context_menu_delete_from_library");
        }
    },
    not_library_or_detail_container_on_demand_context_menu_play { // from class: com.amazon.mp3.featuregate.Feature.not_library_or_detail_container_on_demand_context_menu_play
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("not_library_or_detail_container_on_demand_context_menu_play_v2");
        }
    },
    not_library_or_detail_track_context_menu_add_to_my_music { // from class: com.amazon.mp3.featuregate.Feature.not_library_or_detail_track_context_menu_add_to_my_music
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("not_library_or_detail_track_context_menu_add_to_my_music");
        }
    },
    not_library_or_detail_track_context_menu_customers_also_listened_to { // from class: com.amazon.mp3.featuregate.Feature.not_library_or_detail_track_context_menu_customers_also_listened_to
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("not_library_or_detail_track_context_menu_customers_also_listened_to_v2");
        }
    },
    casting_v2 { // from class: com.amazon.mp3.featuregate.Feature.casting_v2
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule(FeatureGateKeys.CASTING_V2.getId());
        }
    },
    horizontal_row_album_detail_page_station_icon { // from class: com.amazon.mp3.featuregate.Feature.horizontal_row_album_detail_page_station_icon
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("horizontal_row_station_icon");
        }
    },
    playlist_filter_context_menu_button { // from class: com.amazon.mp3.featuregate.Feature.playlist_filter_context_menu_button
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("playlist_filter_context_menu_button");
        }
    },
    album_filter_context_menu_button { // from class: com.amazon.mp3.featuregate.Feature.album_filter_context_menu_button
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("album_filter_context_menu_button");
        }
    },
    player_context_menu_download_button { // from class: com.amazon.mp3.featuregate.Feature.player_context_menu_download_button
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("player_context_menu_download_button");
        }
    },
    library_songs_page_header_visibility { // from class: com.amazon.mp3.featuregate.Feature.library_songs_page_header_visibility
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("library_songs_page_header_visibility_v2");
        }
    },
    locale_supported { // from class: com.amazon.mp3.featuregate.Feature.locale_supported
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new ConfigurationRule("is_locale_supported", Reflection.getOrCreateKotlinClass(GatedLocalesInfo.class), GatedLocalesInfo.INSTANCE.serializer(), new Function1<GatedLocalesInfo, Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$locale_supported$getRule$rule$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(GatedLocalesInfo gatedLocalesInfo) {
                    List<String> locales;
                    String string = AmazonApplication.getDeviceLocale().toString();
                    Intrinsics.checkNotNullExpressionValue(string, "getDeviceLocale().toString()");
                    if ((gatedLocalesInfo == null ? null : gatedLocalesInfo.getLocales()) == null) {
                        return Boolean.FALSE;
                    }
                    if (!((gatedLocalesInfo == null || (locales = gatedLocalesInfo.getLocales()) == null) ? null : Boolean.valueOf(locales.contains(string))).booleanValue()) {
                        return Boolean.TRUE;
                    }
                    if (gatedLocalesInfo == null) {
                        return null;
                    }
                    return Boolean.valueOf(gatedLocalesInfo.getIs_enabled());
                }
            });
        }
    },
    Hide_Mini_Player_PlayQueue { // from class: com.amazon.mp3.featuregate.Feature.Hide_Mini_Player_PlayQueue
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("Hide_Mini_Player_PlayQueue");
        }
    },
    use_dynamic_messaging_for_non_blocking_upsell { // from class: com.amazon.mp3.featuregate.Feature.use_dynamic_messaging_for_non_blocking_upsell
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("use_dynamic_messaging_for_non_blocking_upsell");
        }
    },
    track_augmented_shuffle { // from class: com.amazon.mp3.featuregate.Feature.track_augmented_shuffle
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("track_augmented_shuffle");
        }
    },
    resume_playqueue_seed_playback { // from class: com.amazon.mp3.featuregate.Feature.resume_playqueue_seed_playback
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("resume_playqueue_seed_playback");
        }
    },
    song_history_toolbar_disabled { // from class: com.amazon.mp3.featuregate.Feature.song_history_toolbar_disabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("song_history_toolbar_disabled");
        }
    },
    faceted_navigation_browse_home { // from class: com.amazon.mp3.featuregate.Feature.faceted_navigation_browse_home
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new ExperimentalLaunchWeblabRule(new WeblabRule("DM_ANDROID_FACETED_NAV_463273", weblabTreatment), new WeblabRule("DM_ANDROID_FACETED_NAV_463273", weblabTreatment), new WeblabRule("DIGITAL_MUSIC_ANDROID_FACETED_NAV_494156", new Function1<WeblabTreatment, Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$faceted_navigation_browse_home$getRule$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(WeblabTreatment treatment) {
                    Intrinsics.checkNotNullParameter(treatment, "treatment");
                    return Boolean.valueOf(treatment == WeblabTreatment.T1 || treatment == WeblabTreatment.T2);
                }
            }));
        }
    },
    faceted_navigation_pin_podcasts_pill { // from class: com.amazon.mp3.featuregate.Feature.faceted_navigation_pin_podcasts_pill
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new ExperimentalLaunchWeblabRule(new WeblabRule("DM_ANDROID_FACETED_NAV_463273", weblabTreatment), new WeblabRule("DM_ANDROID_FACETED_NAV_463273", weblabTreatment), new WeblabRule("DIGITAL_MUSIC_ANDROID_FACETED_NAV_494156", weblabTreatment));
        }
    },
    include_amp_shows { // from class: com.amazon.mp3.featuregate.Feature.include_amp_shows
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new WeblabRule("AMP_PLAYBACK_HOME_ANDROID_551254", WeblabTreatment.T1), new BooleanConfigurationRule("is_include_amp_shows_enabled")}), false, 2, null);
        }
    },
    player_context_menu_view_play_queue_button { // from class: com.amazon.mp3.featuregate.Feature.player_context_menu_view_play_queue_button
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("player_context_menu_view_play_queue_button");
        }
    },
    your_ad_privacy_choices_legal_setting_menu { // from class: com.amazon.mp3.featuregate.Feature.your_ad_privacy_choices_legal_setting_menu
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("LEGAL_INFORMATION_YOUR_AD_PRIVACY_CHOICES_551375", WeblabTreatment.T1);
        }
    },
    cookie_consent_eu_workflow { // from class: com.amazon.mp3.featuregate.Feature.cookie_consent_eu_workflow
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule(FeatureGateKeys.COOKIE_CONSENT_EU_WORKFLOW.getId()), new WeblabRule("COOKIE_CONSENT_EU_WORKFLOW_ENABLEMENT_592018", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    cookie_consent_ftu_eu_workflow { // from class: com.amazon.mp3.featuregate.Feature.cookie_consent_ftu_eu_workflow
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule(FeatureGateKeys.COOKIE_CONSENT_EU_WORKFLOW.getId()), new WeblabRule("COOKIE_CONSENT_EU_WORKFLOW_FTU_ENABLEMENT_633948", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    prev_button_enabled { // from class: com.amazon.mp3.featuregate.Feature.prev_button_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("prev_button_enabled");
        }
    },
    is_car_mode_presets_update_enabled { // from class: com.amazon.mp3.featuregate.Feature.is_car_mode_presets_update_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("is_car_mode_presets_update_enabled");
        }
    },
    hide_artist_context_menu_play_and_download { // from class: com.amazon.mp3.featuregate.Feature.hide_artist_context_menu_play_and_download
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("hide_artist_context_menu_play_and_download");
        }
    },
    hide_song_history_library_page { // from class: com.amazon.mp3.featuregate.Feature.hide_song_history_library_page
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            boolean z = false;
            int i = 2;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(Feature$hide_song_history_library_page$getRule$1.INSTANCE, z, i, defaultConstructorMarker), new BooleanConfigurationRule(FeatureGateKeys.SONIC_RUSH.getId()), new BooleanConfigurationRule("hide_song_history_library_page")}), z, i, defaultConstructorMarker);
        }
    },
    casting_previous_button_clickability_override { // from class: com.amazon.mp3.featuregate.Feature.casting_previous_button_clickability_override
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("casting_previous_button_clickability_override");
        }
    },
    haptics_on_playback { // from class: com.amazon.mp3.featuregate.Feature.haptics_on_playback
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new ExperimentalLaunchWeblabRule(new WeblabRule("DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_LAUNCH_572097", weblabTreatment), new WeblabRule("DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_AA_568475", weblabTreatment), new WeblabRule("DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_AB_568495", weblabTreatment));
        }
    },
    playlist_song_search { // from class: com.amazon.mp3.featuregate.Feature.playlist_song_search
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_PLAYLIST_SONG_SEARCH_ANDROID_497901", WeblabTreatment.T1);
        }
    },
    update_purchased_track_asin_substitution { // from class: com.amazon.mp3.featuregate.Feature.update_purchased_track_asin_substitution
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("update_purchased_track_asin_substitution");
        }
    },
    error_180_flex_event_enabled { // from class: com.amazon.mp3.featuregate.Feature.error_180_flex_event_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("error_180_flex_event");
        }
    },
    error_cloud_queue_flex_event_enabled { // from class: com.amazon.mp3.featuregate.Feature.error_cloud_queue_flex_event_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("error_cloud_queue_flex_event");
        }
    },
    is_car_mode_play_queue_enabled { // from class: com.amazon.mp3.featuregate.Feature.is_car_mode_play_queue_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_car_mode_play_queue_enabled"), new WeblabRule("DM_ANDROID_AUTOMOTIVE_CAR_MODE_PLAY_QUEUE_597119", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    artist_studio_shuffle_sonic_rush { // from class: com.amazon.mp3.featuregate.Feature.artist_studio_shuffle_sonic_rush
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("artist_studio_shuffle"), new WeblabRule("DM_ARTIST_STUDIO_SHUFFLE_SONIC_RUSH_ANDROID_602132", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    should_migrate_free_to_cq_for_non_station { // from class: com.amazon.mp3.featuregate.Feature.should_migrate_free_to_cq_for_non_station
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new BooleanMethodRule[]{new BooleanMethodRule(new Feature$should_migrate_free_to_cq_for_non_station$getRule$1(capabilities), true), new BooleanMethodRule(new Feature$should_migrate_free_to_cq_for_non_station$getRule$2(FMPMFeatureGating.FREE_TIER_TO_CQE_NON_STATIONS), true)}), false, 2, null);
        }
    },
    track_shuffle_playback_upsell_nightwing { // from class: com.amazon.mp3.featuregate.Feature.track_shuffle_playback_upsell_nightwing
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_track_shuffle_playback_upsell_nightwing_enabled"), new WeblabRule("DIGITAL_MUSIC_ABC_ALTERNATIVE_UPSELL_CX_NIGHTWING_ANDROID_AB_620098", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    track_shuffle_playback_upsell_sonic_rush { // from class: com.amazon.mp3.featuregate.Feature.track_shuffle_playback_upsell_sonic_rush
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_track_shuffle_playback_upsell_sonic_rush_enabled"), new WeblabRule("DIGITAL_MUSIC_ABC_ALTERNATIVE_UPSELL_CX_SONIC_RUSH_ANDROID_AB_620105", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    browse_home_playback_upsell { // from class: com.amazon.mp3.featuregate.Feature.browse_home_playback_upsell
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_browse_home_playback_upsell_enabled"), new WeblabRule("DIGITAL_MUSIC_ABC_SONG_FOR_YOU_V2_ANDROID_LAUNCH_628218", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    now_playing_screen_contextual_upsell { // from class: com.amazon.mp3.featuregate.Feature.now_playing_screen_contextual_upsell
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_now_playing_screen_contextual_upsell_enabled"), new WeblabRule("DIGITAL_MUSIC_ABC_NPS_CONTEXTUAL_UPSELL_ANDROID_AB_668255", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    car_mode { // from class: com.amazon.mp3.featuregate.Feature.car_mode
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("car_mode");
        }
    },
    car_mode_auto_play { // from class: com.amazon.mp3.featuregate.Feature.car_mode_auto_play
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_AUTOMOTIVE_CAR_MODE_472143", WeblabTreatment.T1);
        }
    },
    car_mode_v2 { // from class: com.amazon.mp3.featuregate.Feature.car_mode_v2
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_car_mode_v2_enabled"), new WeblabRule("DM_CARMODE_ANDROID_V2_641925", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    car_mode_podcast_episode_list { // from class: com.amazon.mp3.featuregate.Feature.car_mode_podcast_episode_list
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(new Feature$car_mode_podcast_episode_list$getRule$1(capabilities), true), new BooleanConfigurationRule("is_car_mode_episode_podcast_list_enabled"), new WeblabRule("DM_CARMODE_ANDROID_V2_708450", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    car_mode_always_show_disclaimer { // from class: com.amazon.mp3.featuregate.Feature.car_mode_always_show_disclaimer
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsJVMKt.listOf(new BooleanConfigurationRule("car_mode_always_show_disclaimer")), false, 2, null);
        }
    },
    car_mode_additional_disclaimer { // from class: com.amazon.mp3.featuregate.Feature.car_mode_additional_disclaimer
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsJVMKt.listOf(new BooleanConfigurationRule("car_mode_additional_disclaimer")), false, 2, null);
        }
    },
    car_mode_auto_launch { // from class: com.amazon.mp3.featuregate.Feature.car_mode_auto_launch
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsJVMKt.listOf(new BooleanConfigurationRule("car_mode_auto_launch")), false, 2, null);
        }
    },
    album_detail_performance_improvements_phase1 { // from class: com.amazon.mp3.featuregate.Feature.album_detail_performance_improvements_phase1
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("album_detail_performance_phase1"), new WeblabRule("DM_ANDROID_ALBUM_DETAIL_PERF_PHASE1_624000", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    album_detail_telemetry_correction { // from class: com.amazon.mp3.featuregate.Feature.album_detail_telemetry_correction
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("album_detail_telemetry_correction");
        }
    },
    album_detail_performance_improvements_phase1B { // from class: com.amazon.mp3.featuregate.Feature.album_detail_performance_improvements_phase1B
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("album_detail_performance_phase1B"), new WeblabRule("DM_ANDROID_ALBUM_DETAIL_PERF_PHASE1_B_631754", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    is_lyrics_feedback_enabled { // from class: com.amazon.mp3.featuregate.Feature.is_lyrics_feedback_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("is_lyrics_feedback_enabled");
        }
    },
    feature_play_v2_browse_home { // from class: com.amazon.mp3.featuregate.Feature.feature_play_v2_browse_home
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new ExperimentalLaunchWeblabRule(new WeblabRule("DM_ANDROID_SMALLER_T1R_632541", weblabTreatment), new WeblabRule("DM_ANDROID_SMALLER_T1R_632541", weblabTreatment), new WeblabRule("DIGITAL_MUSIC_T1R_V2_ANDROID_AB_632515", new Function1<WeblabTreatment, Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$feature_play_v2_browse_home$getRule$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(WeblabTreatment treatment) {
                    Intrinsics.checkNotNullParameter(treatment, "treatment");
                    return Boolean.valueOf(treatment == WeblabTreatment.T1);
                }
            }));
        }
    },
    home_recently_played_from_service { // from class: com.amazon.mp3.featuregate.Feature.home_recently_played_from_service
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("RENDER_RECENTLY_PLAYED_FROM_MUSE_ANDROID_632334", WeblabTreatment.T1);
        }
    },
    use_eligibility_recently_played_library_v1 { // from class: com.amazon.mp3.featuregate.Feature.use_eligibility_recently_played_library_v1
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            boolean z = false;
            int i = 2;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(Feature$use_eligibility_recently_played_library_v1$getRule$1.INSTANCE, z, i, defaultConstructorMarker), new BooleanConfigurationRule(FeatureGateKeys.SONIC_RUSH.getId()), new BooleanConfigurationRule("use_eligibility_recently_played_library_v1")}), z, i, defaultConstructorMarker);
        }
    },
    playlist_import { // from class: com.amazon.mp3.featuregate.Feature.playlist_import
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("playlist_import"), new WeblabRule("DM_ASTERIX_ANDROID_INGRESS_TO_IMPORT_PLAYLIST_LAUNCH_443796", WeblabTreatment.T1)), false, 2, null);
        }
    },
    video_stories_artist_follow_supported { // from class: com.amazon.mp3.featuregate.Feature.video_stories_artist_follow_supported
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("video_stories_artist_follow_supported"), new WeblabRule("DM_VIDEO_STORIES_ARTIST_FOLLOW_ANDROID_641578", WeblabTreatment.T1)), false, 2, null);
        }
    },
    pdp_add_songs_button_enabled { // from class: com.amazon.mp3.featuregate.Feature.pdp_add_songs_button_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.mutableListOf(new BooleanConfigurationRule("pdp_add_songs_button_enabled"), new WeblabRule("DM_ANDROID_PDP_ADD_SONGS_BUTTON_684896", WeblabTreatment.T1)), false, 2, null);
        }
    },
    cold_start_animation_logo_perf_improvement { // from class: com.amazon.mp3.featuregate.Feature.cold_start_animation_logo_perf_improvement
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ANDROID_COLD_START_ANIMATION_PERF_642823", WeblabTreatment.T1);
        }
    },
    firebase_performance_monitoring { // from class: com.amazon.mp3.featuregate.Feature.firebase_performance_monitoring
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ANDROID_FIREBASE_PER_MONITORING_675673", WeblabTreatment.T1);
        }
    },
    use_date_added_default_refinement { // from class: com.amazon.mp3.featuregate.Feature.use_date_added_default_refinement
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("use_date_added_default_refinement");
        }
    },
    use_optimized_observable_browse_views_factory { // from class: com.amazon.mp3.featuregate.Feature.use_optimized_observable_browse_views_factory
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("use_optimized_observable_browse_views_factory"), new WeblabRule("USE_OPTIMIZED_OBSERVABLE_BROWSE_VIEWS_FACTORY_647225", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    is_all_access_badging_enabled { // from class: com.amazon.mp3.featuregate.Feature.is_all_access_badging_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("is_all_access_badging_enabled_v2");
        }
    },
    early_use_notification { // from class: com.amazon.mp3.featuregate.Feature.early_use_notification
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("early_use_notification");
        }
    },
    shell_loading_animation { // from class: com.amazon.mp3.featuregate.Feature.shell_loading_animation
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_ANDROID_HOME_PAGE_SHELL_LOADER_ANIMATION_669978", WeblabTreatment.T1);
        }
    },
    user_playlists_eligibility_override { // from class: com.amazon.mp3.featuregate.Feature.user_playlists_eligibility_override
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("user_playlists_eligibility_override");
        }
    },
    is_album_on_demand_badging_enabled { // from class: com.amazon.mp3.featuregate.Feature.is_album_on_demand_badging_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_album_on_demand_badging_enabled"), new WeblabRule("DIGITAL_MUSIC_ON_DEMAND_BADGING_ALBUMS_ANDROID_672584", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    uipageview_should_send_pagesubtype { // from class: com.amazon.mp3.featuregate.Feature.uipageview_should_send_pagesubtype
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("uipageview_should_send_pagesubtype");
        }
    },
    bugsnag_breadcrumbs_is_enabled { // from class: com.amazon.mp3.featuregate.Feature.bugsnag_breadcrumbs_is_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_MOBILE_FOUNDATIONS_BUGSNAG_BREADCRUMBS_ANDROID_689454", WeblabTreatment.T1.getTreatment(), false);
        }
    },
    preload_associate_tag_without_region_id { // from class: com.amazon.mp3.featuregate.Feature.preload_associate_tag_without_region_id
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("preload_associate_tag_without_region_id");
        }
    },
    lyrics_sharing { // from class: com.amazon.mp3.featuregate.Feature.lyrics_sharing
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("lyrics_sharing"), new WeblabRule("DM_LYRICS_SHARING_ANDROID_701783", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    dragonfly_enabled { // from class: com.amazon.mp3.featuregate.Feature.dragonfly_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(Feature$dragonfly_enabled$getRule$1.INSTANCE, false), new WeblabRule("DM_DEVEX_ANDROID_DRAGONFLY_LAUNCH_695988", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    dragonfly_fireos_enabled { // from class: com.amazon.mp3.featuregate.Feature.dragonfly_fireos_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(Feature$dragonfly_fireos_enabled$getRule$1.INSTANCE, z, i, defaultConstructorMarker), new WeblabRule("DM_DEVEX_ANDROID_DRAGONFLY_FIREOS_LAUNCH_747586", WeblabTreatment.T1)}), z, i, defaultConstructorMarker);
        }
    },
    social_profiles_phase3_enabled { // from class: com.amazon.mp3.featuregate.Feature.social_profiles_phase3_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanMethodRule(Feature$social_profiles_phase3_enabled$getRule$1.INSTANCE, false), new WeblabRule("DIGITAL_MUSIC_ANDROID_RED_ROCKS_PHASE_3_717541", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    pre_auth_splash_experiment_t1 { // from class: com.amazon.mp3.featuregate.Feature.pre_auth_splash_experiment_t1
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            boolean z = false;
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_pre_auth_splash_enabled"), new ExperimentalWeblabRule(new WeblabRule("DIGITAL_MUSIC_ASTERIX_ANDROID_BROWSE_HOME_PREAUTH_SPLASH_AA_712226", weblabTreatment), new WeblabRule("DIGITAL_MUSIC_ASTERIX_ANDROID_BROWSE_HOME_PREAUTH_SPLASH_AB_712219", weblabTreatment))}), z, 2, null);
        }
    },
    pre_auth_splash_experiment_t2 { // from class: com.amazon.mp3.featuregate.Feature.pre_auth_splash_experiment_t2
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            boolean z = false;
            WeblabTreatment weblabTreatment = WeblabTreatment.T2;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_pre_auth_splash_enabled"), new ExperimentalWeblabRule(new WeblabRule("DIGITAL_MUSIC_ASTERIX_ANDROID_BROWSE_HOME_PREAUTH_SPLASH_AA_712226", weblabTreatment), new WeblabRule("DIGITAL_MUSIC_ASTERIX_ANDROID_BROWSE_HOME_PREAUTH_SPLASH_AB_712219", weblabTreatment))}), z, 2, null);
        }
    },
    should_create_entity_item_identifier_by_luid { // from class: com.amazon.mp3.featuregate.Feature.should_create_entity_item_identifier_by_luid
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("should_create_entity_item_identifier_by_luid"), new WeblabRule("DM_SWAT_ON_DEMAND_USER_PLAYLISTS_ANDROID_732951", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    android_app_tou_experiment_enabled { // from class: com.amazon.mp3.featuregate.Feature.android_app_tou_experiment_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            boolean z = false;
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("terms_and_conditions_optimization_supported"), new ExperimentalWeblabRule(new WeblabRule("DIGITAL_MUSIC_ANDROID_APP_TERMS_OF_USE_OPTIMIZATION_AA_704934", weblabTreatment), new WeblabRule("DIGITAL_MUSIC_ASTERIX_ANDROID_APP_TERMS_OF_USE_OPTIMIZATION_AB_722286", weblabTreatment))}), z, 2, null);
        }
    },
    should_show_add_to_library_button { // from class: com.amazon.mp3.featuregate.Feature.should_show_add_to_library_button
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("is_add_to_library_button_enabled");
        }
    },
    cfy_firefly_image_upload_enabled { // from class: com.amazon.mp3.featuregate.Feature.cfy_firefly_image_upload_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsJVMKt.listOf(new WeblabRule("DIGITAL_MUSIC_ANDROID_RED_ROCKS_IMAGE_UPLOADING_677744", WeblabTreatment.T1)), false, 2, null);
        }
    },
    is_lookup_deprecated { // from class: com.amazon.mp3.featuregate.Feature.is_lookup_deprecated
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DIGITAL_MUSIC_ANDROID_LOOKUP_DEPRECATION_EXP_697623", WeblabTreatment.T1);
        }
    },
    react_native_performance_profiling { // from class: com.amazon.mp3.featuregate.Feature.react_native_performance_profiling
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            WeblabTreatment weblabTreatment = WeblabTreatment.T1;
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new WeblabRule[]{new WeblabRule("DM_DEVEX_ANDROID_DRAGONFLY_LAUNCH_695988", weblabTreatment), new WeblabRule("DM_DEVEX_ANDROID_DRAGONFLY_LATENCY_726911", weblabTreatment)}), false, 2, null);
        }
    },
    pdp_follower_count { // from class: com.amazon.mp3.featuregate.Feature.pdp_follower_count
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            int i = 2;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new OrRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("playlist_detail_page_follower_count"), new WeblabRule("DM_PLAYLIST_DETAIL_PAGE_FOLLOWER_COUNT_729363", WeblabTreatment.T1)}), z, i, defaultConstructorMarker), new BooleanMethodRule(new Function0<Boolean>() { // from class: com.amazon.mp3.featuregate.Feature$pdp_follower_count$getRule$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.FALSE;
                }
            }, z, i, defaultConstructorMarker)}), z, i, defaultConstructorMarker);
        }
    },
    add_sharing_to_top_level_action { // from class: com.amazon.mp3.featuregate.Feature.add_sharing_to_top_level_action
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("add_sharing_to_top_level_action"), new WeblabRule("DM_SHARE_BUTTON_AS_TOP_LEVEL_ACTION_736525", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    search_aap_instant_unlock { // from class: com.amazon.mp3.featuregate.Feature.search_aap_instant_unlock
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanMethodRule(new Feature$search_aap_instant_unlock$getRule$1(FMPMFeatureGating.SEARCH_AAP_INSTANT_UNLOCK), true);
        }
    },
    is_all_access_user_playlist_pdp_messaging_enabled { // from class: com.amazon.mp3.featuregate.Feature.is_all_access_user_playlist_pdp_messaging_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_all_access_user_playlist_pdp_messaging_enabled"), new WeblabRule("DM_AAUP_MESSAGING_AND_RESTRICTIONS_ANDROID_739536", WeblabTreatment.T1)}), false, 2, null);
        }
    },
    is_all_access_user_playlist_restrictions_enabled { // from class: com.amazon.mp3.featuregate.Feature.is_all_access_user_playlist_restrictions_enabled
        @Override // com.amazon.mp3.featuregate.Feature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new AndRule(CollectionsKt__CollectionsKt.listOf((Object[]) new Rule[]{new BooleanConfigurationRule("is_all_access_user_playlist_restrictions_enabled"), new WeblabRule("DM_AAUP_MESSAGING_AND_RESTRICTIONS_ANDROID_739536", WeblabTreatment.T1)}), false, 2, null);
        }
    };

    private static final String TAG = com.amazon.music.platform.featuregate.Feature.class.getSimpleName();

    /* synthetic */ Feature(DefaultConstructorMarker defaultConstructorMarker) {
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

    public static /* synthetic */ boolean isEnabled$default(Feature feature, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isEnabled");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        return feature.isEnabled(z);
    }

    public final boolean isEnabled(boolean useCache) {
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
        if (featureGateProvider instanceof CachedFeatureFlagProviderImpl) {
            return isEnabled((CachedFeatureFlagProviderImpl) featureGateProvider, useCache);
        }
        return isEnabled(featureGateProvider);
    }

    public final boolean isEnabled(FeatureGateProvider featureGateProvider) {
        Intrinsics.checkNotNullParameter(featureGateProvider, "featureGateProvider");
        return featureGateProvider.isFeatureAvailable(this);
    }

    public static /* synthetic */ boolean isEnabled$default(Feature feature, CachedFeatureFlagProviderImpl cachedFeatureFlagProviderImpl, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isEnabled");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return feature.isEnabled(cachedFeatureFlagProviderImpl, z);
    }

    public final boolean isEnabled(CachedFeatureFlagProviderImpl featureGateProvider, boolean useCache) {
        Intrinsics.checkNotNullParameter(featureGateProvider, "featureGateProvider");
        return featureGateProvider.isFeatureAvailable(this, useCache);
    }
}
