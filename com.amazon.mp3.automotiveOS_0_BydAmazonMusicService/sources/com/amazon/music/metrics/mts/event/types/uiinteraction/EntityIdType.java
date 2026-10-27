package com.amazon.music.metrics.mts.event.types.uiinteraction;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum EntityIdType implements MetricValueType {
    ASIN("ASIN"),
    CD_OBJECT_ID("CD_OBJECT_ID"),
    FEED_BASE_ITEM_ID("FEED_BASE_ITEM_ID"),
    PLAYLIST_ID("PLAYLIST_ID"),
    CPL_PLAYLIST_ID("CPL_PLAYLIST_ID"),
    STATION_KEY("STATION_KEY"),
    STATION_SEED_ID("STATION_SEED_ID"),
    GENRE_NAME("GENRE_NAME"),
    REFINEMENT_ID("REFINEMENT_ID"),
    SHARED_PLAYLIST_ID("SHARED_PLAYLIST_ID"),
    PROGRAM_ID("PROGRAM_ID"),
    COMPETITION_ID("COMPETITION_ID"),
    DEVICE_TARGET_ID("DEVICE_TARGET_ID"),
    PROFILE_ID("PROFILE_ID"),
    URL("URL"),
    SFA_ARTIST_SEED("SFA_ARTIST_SEED"),
    SFA_TRACK_SEED("SFA_TRACK_SEED"),
    SFA_ALBUM_SEED("SFA_ALBUM_SEED"),
    SFA_PLAYLIST_SEED("SFA_PLAYLIST_SEED"),
    SFA_TRACK_LIST_SEED("SFA_TRACK_LIST_SEED"),
    SFA_STATION_SEED_ID("SFA_STATION_SEED_ID"),
    LIVE_STREAM_ID("LIVE_STREAM_ID"),
    LIVE_EVENT_ID("LIVE_EVENT_ID"),
    PODCAST_SHOW_ID("PODCAST_SHOW_ID"),
    PODCAST_EPISODE_ID("PODCAST_EPISODE_ID"),
    PODCAST_BITE_ID("PODCAST_BITE_ID");

    private final String mMetricValue;

    EntityIdType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
