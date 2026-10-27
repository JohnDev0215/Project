package com.amazon.music.metrics.mts.event.types.uiinteraction;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum EntityType implements MetricValueType {
    DEVICE("DEVICE"),
    PRIME_PLAYLIST("PRIME_PLAYLIST"),
    VIDEO_PLAYLIST("VIDEO_PLAYLIST"),
    UNLIMITED_PLAYLIST("UNLIMITED_PLAYLIST"),
    USER_PLAYLIST("USER_PLAYLIST"),
    SHARED_PLAYLIST("SHARED_PLAYLIST"),
    COMMUNITY_PLAYLIST("COMMUNITY_PLAYLIST"),
    AUTO_PLAYLIST("AUTO_PLAYLIST"),
    GNO_PRIME_PLAYLIST("GNO_PRIME_PLAYLIST"),
    GNO_UNL_PLAYLIST("GNO_UNL_PLAYLIST"),
    PLAYLIST("PLAYLIST"),
    ALBUM("ALBUM"),
    GENRE("GENRE"),
    ARTIST("ARTIST"),
    TRACK("TRACK"),
    LYRICS("LYRICS"),
    VIDEO("VIDEO"),
    MERCH("MERCH"),
    REFINEMENT("REFINEMENT"),
    PRIME_STATION("PRIME_STATION"),
    UNLIMITED_STATION("UNLIMITED_STATION"),
    STATION("STATION"),
    CUSTOMER_PROFILE("CUSTOMER_PROFILE"),
    LIVE_STREAM("LIVE_STREAM"),
    LIVE_EVENT("LIVE_EVENT"),
    PODCAST_SHOW("PODCAST_SHOW"),
    PODCAST_EPISODE("PODCAST_EPISODE"),
    INVITE("INVITE"),
    UNKNOWN("UNKNOWN");

    private final String metricValue;

    EntityType(String str) {
        this.metricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.metricValue;
    }
}
