package com.amazon.music.events.types;

/* JADX INFO: loaded from: classes4.dex */
public enum EntityIdType {
    ASIN("ASIN"),
    CD_OBJECT_ID("CD_OBJECT_ID"),
    PLAYLIST_ID("PLAYLIST_ID"),
    STATION_KEY("STATION_KEY"),
    GENRE_NAME("GENRE_NAME"),
    DEVICE_TARGET_ID("DEVICE_TARGET_ID");

    private final String metricValue;

    EntityIdType(String str) {
        this.metricValue = str;
    }

    public String getMetricValue() {
        return this.metricValue;
    }
}
