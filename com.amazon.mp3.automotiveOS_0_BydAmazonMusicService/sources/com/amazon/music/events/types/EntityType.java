package com.amazon.music.events.types;

/* JADX INFO: loaded from: classes4.dex */
public enum EntityType {
    DEVICE("DEVICE"),
    PRIME_PLAYLIST("PRIME_PLAYLIST"),
    UNLIMITED_PLAYLIST("UNLIMITED_PLAYLIST"),
    USER_PLAYLIST("USER_PLAYLIST"),
    SHARED_PLAYLIST("SHARED_PLAYLIST"),
    AUTO_PLAYLIST("AUTO_PLAYLIST"),
    GNO_PRIME_PLAYLIST("GNO_PRIME_PLAYLIST"),
    GNO_UNL_PLAYLIST("GNO_UNL_PLAYLIST"),
    ALBUM("ALBUM"),
    GENRE("GENRE"),
    ARTIST("ARTIST"),
    TRACK("TRACK"),
    PRIME_STATION("PRIME_STATION"),
    UNLIMITED_STATION("UNLIMITED_STATION"),
    VIDEO("VIDEO"),
    VIDEO_PLAYLIST("VIDEO_PLAYLIST"),
    PODCAST_SHOW("PODCAST_SHOW"),
    PODCAST_EPISODE("PODCAST_EPISODE");

    private final String metricValue;

    EntityType(String str) {
        this.metricValue = str;
    }

    public String getMetricValue() {
        return this.metricValue;
    }
}
