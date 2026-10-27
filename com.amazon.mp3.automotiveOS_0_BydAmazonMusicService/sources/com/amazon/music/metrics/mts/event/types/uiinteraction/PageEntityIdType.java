package com.amazon.music.metrics.mts.event.types.uiinteraction;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum PageEntityIdType implements MetricValueType {
    ASIN("ASIN"),
    AD_ID("AD_ID"),
    AD_INSTANCE_ID("AD_INSTANCE_ID"),
    BID_RESPONSE_ID("BID_RESPONSE_ID"),
    CD_OBJECT_ID("CD_OBJECT_ID"),
    COMPETITION_ID("COMPETITION_ID"),
    CONFIDENCE_SCORE("CONFIDENCE_SCORE"),
    CUSTOMER_ID("CUSTOMER_ID"),
    DEVICE_TARGET_ID("DEVICE_TARGET_ID"),
    DEVICE_TYPE("DEVICE_TYPE"),
    FIND_CATEGORY_ID("FIND_CATEGORY_ID"),
    FIND_GROUPING_ID("FIND_GROUPING_ID"),
    GENRE_ID("GENRE_ID"),
    GENRE_NAME("GENRE_NAME"),
    INTERLUDE_ID("INTERLUDE_ID"),
    LIVE_STREAM_ID("LIVE_STREAM_ID"),
    NAVIGATION_TARGET("NAVIGATION_TARGET"),
    PLAYLIST_ID("PLAYLIST_ID"),
    PODCAST_BITE_ID("PODCAST_BITE_ID"),
    PODCAST_CATEGORY_ID("PODCAST_CATEGORY_ID"),
    PODCAST_EPISODE_ID("PODCAST_EPISODE_ID"),
    PODCAST_SHOW_ID("PODCAST_SHOW_ID"),
    PROGRAM_ID("PROGRAM_ID"),
    PV_PLAYBACK_ID("PV_PLAYBACK_ID"),
    SCHEDULE_ID("SCHEDULE_ID"),
    SEARCH_SUGGESTION_ID("SEARCH_SUGGESTION_ID"),
    SEARCH_QUERY_ID("SEARCH_QUERY_ID"),
    SHARED_PLAYLIST_ID("SHARED_PLAYLIST_ID"),
    STATION_KEY("STATION_KEY"),
    STATION_SEED_ID("STATION_SEED_ID");

    private final String mMetricValue;

    PageEntityIdType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
