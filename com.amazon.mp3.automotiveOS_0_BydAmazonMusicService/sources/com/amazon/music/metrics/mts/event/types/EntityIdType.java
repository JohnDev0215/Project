package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum EntityIdType implements MetricValueType {
    ASIN("ASIN"),
    CD_OBJECT_ID("CD_OBJECT_ID"),
    PLAYLIST_ID("PLAYLIST_ID"),
    STATION_KEY("STATION_KEY"),
    STATION_SEED_ID("STATION_SEED_ID"),
    GENRE_NAME("GENRE_NAME"),
    PROGRAM_ID("PROGRAM_ID"),
    LIVE_STREAM_ID("LIVE_STREAM_ID"),
    LIVE_EVENT_ID("LIVE_EVENT_ID"),
    PV_PLAYBACK_ID("PVPlaybackId");

    private final String metricValue;

    EntityIdType(String str) {
        this.metricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.metricValue;
    }
}
