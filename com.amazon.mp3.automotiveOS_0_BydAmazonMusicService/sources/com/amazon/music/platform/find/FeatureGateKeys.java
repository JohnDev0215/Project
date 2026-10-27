package com.amazon.music.platform.find;

import UpsellInterface.v1_0.UpsellType;
import kotlin.Metadata;

/* JADX INFO: compiled from: FeatureGateKeys.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b.\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0¨\u00061"}, d2 = {"Lcom/amazon/music/platform/find/FeatureGateKeys;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "BLENDED_SEARCH_HISTORY", "SEARCH_HISTORY_TTL_DAYS", "SEARCH_HISTORY_EVICTION_CADENCE_DAYS", "CACHE_SEARCH_RESULTS", "ARTIST_IMAGE_CACHING_IMPROVEMENT", "FIND_LANDING_SKYFIRE_GROUPINGS_CATEGORIES", "RELATED_ENTITY_SEARCH", "RELATED_ENTITY_SEARCH_FREE", "COMMUNITY_PLAYLIST_ENABLED", "COMMUNITY_PLAYLIST_FREE_TIER_ENABLED", "USER_PLAYLIST_FOR_OFFLINE_SEARCH_ENABLED", "USER_PLAYLIST_SHOW_UPSELL_ENABLED", "ALEXA_SEARCH_ORDINAL_PLAYBACK", "ALEXA_SEARCH_FIND_BROWSE_CAR_MODE", "INSTANT_SEARCH_MAX_SUGGESTION_RESULTS", "INSTANT_SEARCH_MIN_ENTITY_QUERY_LENGTH", "INSTANT_SEARCH_ENTITY_QUERY_SIZE", "INSTANT_SEARCH_ENTITY_RESULT_COUNT_BASIS", "INSTANT_SEARCH_RECENT_ENTITY_DATE_RANGE", "INSTANT_SEARCH_SUGGESTIONS_PARAMS_WITHOUT_ENTITIES", "INSTANT_SEARCH_SUGGESTIONS_PARAMS_WITH_ENTITIES", "INSTANT_SEARCH_SUGGESTIONS_PARAMS_WITH_ENTITIES_INCLUDING_SR_AND_FT", "INSTANT_SEARCH_API_LEVEL", "INSTANT_SEARCH_API_LEVEL_ENTITIES", "INSTANT_SEARCH_API_LEVEL_ENTITIES_SR_FT", "HIDE_HISTORY_AND_LOCAL_SUGGESTION_TRACK_ENTITIES", "SEARCH_HISTORY_DATA_EVICTION_POLICY", "VIDEO_SEARCH", "VIDEO_PLAYLIST_SEARCH", "HIDE_OFFLINE_LIBRARY_SEARCH_RESULTS", "SHOULD_CHECK_ELIGIBILITY_AVAILABILITY", "IS_PODCAST_ENABLED", "YA_IMMERSE", "SONIC_RUSH", UpsellType.UNLIMITED, "SUPPORTS_ELIGIBILITY", "ELIGIBILITY_BASED_PLAYBACK_ICON", "T1R_USING_CLOUD_QUEUE", "SKIP_LIMITS_USING_CLOUD_QUEUE", "CASTING_V2", "COOKIE_CONSENT_EU_WORKFLOW", "UPSELL_ON_FIND_PAGE", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum FeatureGateKeys {
    BLENDED_SEARCH_HISTORY("blended_search_history"),
    SEARCH_HISTORY_TTL_DAYS("search_history_ttl_days"),
    SEARCH_HISTORY_EVICTION_CADENCE_DAYS("search_history_eviction_cadence_days"),
    CACHE_SEARCH_RESULTS("cache_search_results"),
    ARTIST_IMAGE_CACHING_IMPROVEMENT("artist_image_caching_improvement"),
    FIND_LANDING_SKYFIRE_GROUPINGS_CATEGORIES("find_landing_skyfire"),
    RELATED_ENTITY_SEARCH("related_entity_search"),
    RELATED_ENTITY_SEARCH_FREE("related_entity_search_free"),
    COMMUNITY_PLAYLIST_ENABLED("community_playlist_enabled"),
    COMMUNITY_PLAYLIST_FREE_TIER_ENABLED("community_playlist_free_tier_enabled"),
    USER_PLAYLIST_FOR_OFFLINE_SEARCH_ENABLED("user_playlist_for_offline_search_enabled"),
    USER_PLAYLIST_SHOW_UPSELL_ENABLED("user_playlist_show_upsell_enabled"),
    ALEXA_SEARCH_ORDINAL_PLAYBACK("alexa_search_ordinal_playback"),
    ALEXA_SEARCH_FIND_BROWSE_CAR_MODE("alexa_search_find_browse_car_mode"),
    INSTANT_SEARCH_MAX_SUGGESTION_RESULTS("instant_search_max_suggestion_results"),
    INSTANT_SEARCH_MIN_ENTITY_QUERY_LENGTH("instant_search_min_entity_query_length"),
    INSTANT_SEARCH_ENTITY_QUERY_SIZE("instant_search_entity_query_size"),
    INSTANT_SEARCH_ENTITY_RESULT_COUNT_BASIS("instant_search_entity_result_count_basis"),
    INSTANT_SEARCH_RECENT_ENTITY_DATE_RANGE("instant_search_recent_entity_date_range"),
    INSTANT_SEARCH_SUGGESTIONS_PARAMS_WITHOUT_ENTITIES("instant_search_suggestions_params_without_entities"),
    INSTANT_SEARCH_SUGGESTIONS_PARAMS_WITH_ENTITIES("instant_search_suggestions_params_with_entities"),
    INSTANT_SEARCH_SUGGESTIONS_PARAMS_WITH_ENTITIES_INCLUDING_SR_AND_FT("instant_search_suggestions_params_with_entities_including_sr_and_ft"),
    INSTANT_SEARCH_API_LEVEL("instant_search_api_level"),
    INSTANT_SEARCH_API_LEVEL_ENTITIES("instant_search_api_level_entities"),
    INSTANT_SEARCH_API_LEVEL_ENTITIES_SR_FT("instant_search_api_level_entities_including_sr_ft"),
    HIDE_HISTORY_AND_LOCAL_SUGGESTION_TRACK_ENTITIES("hide_history_and_local_suggestion_track_entities"),
    SEARCH_HISTORY_DATA_EVICTION_POLICY("search_history_data_eviction_policy"),
    VIDEO_SEARCH("video_search"),
    VIDEO_PLAYLIST_SEARCH("video_playlist_search"),
    HIDE_OFFLINE_LIBRARY_SEARCH_RESULTS("hide_offline_library_search_results"),
    SHOULD_CHECK_ELIGIBILITY_AVAILABILITY("check_playmode_eligibility_fields_availability"),
    IS_PODCAST_ENABLED("is_podcasts_enabled"),
    YA_IMMERSE("ya_immerse"),
    SONIC_RUSH("sonic_rush"),
    UNLIMITED("unlimited"),
    SUPPORTS_ELIGIBILITY("supports_eligibility_v2"),
    ELIGIBILITY_BASED_PLAYBACK_ICON("eligibility_based_playback_icon_v2"),
    T1R_USING_CLOUD_QUEUE("t1r_using_cloud_queue"),
    SKIP_LIMITS_USING_CLOUD_QUEUE("skip_limits_using_cloud_queue"),
    CASTING_V2("casting_v2"),
    COOKIE_CONSENT_EU_WORKFLOW("cookie_consent_eu_workflow"),
    UPSELL_ON_FIND_PAGE("upsell_on_find_page");

    private final String id;

    FeatureGateKeys(String str) {
        this.id = str;
    }

    public final String getId() {
        return this.id;
    }
}
