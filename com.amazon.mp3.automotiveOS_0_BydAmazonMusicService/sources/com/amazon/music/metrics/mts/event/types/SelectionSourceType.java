package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum SelectionSourceType implements MetricValueType {
    PRIME_PLAYLIST("PRIME_PLAYLIST"),
    PRIME_STATION("PRIME_STATION"),
    UNLIMITED_PLAYLIST("UNLIMITED_PLAYLIST"),
    UNLIMITED_STATION("UNLIMITED_STATION"),
    USER_PLAYLIST("USER_PLAYLIST"),
    AUTO_PLAYLIST("AUTO_PLAYLIST"),
    ALBUM("ALBUM"),
    GENRE("GENRE"),
    ARTIST("ARTIST"),
    SONGS("SONGS"),
    WIDGET("WIDGET"),
    SHARED_PLAYLIST("SHARED_PLAYLIST"),
    GOLDEN_PLAYLIST("GOLDEN_PLAYLIST"),
    AMF_STATION_SEED("AMF_STATION_SEED"),
    PRIME_STATION_SEED("PRIME_STATION_SEED"),
    UNLIMITED_STATION_SEED("UNL_STATION_SEED"),
    AMF_STATION("AMF_STATION"),
    AMF_PLAYLIST_ENT("AMF_PLAYLIST_ENT"),
    PRIME_PLAYLIST_ENT("PRIME_PLAYLIST_ENT"),
    AMU_PLAYLIST_ENT("AMU_PLAYLIST_ENT"),
    PERSONALIZE_PLAYLIST("PERSONALIZE_PLAYLIST");

    private String mMetricValue;

    SelectionSourceType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }

    public static SelectionSourceType fromValue(String str) {
        str.getClass();
        for (SelectionSourceType selectionSourceType : values()) {
            if (str.equals(selectionSourceType.getMetricValue())) {
                return selectionSourceType;
            }
        }
        throw new IllegalArgumentException("Invalid selectionSourceType " + str);
    }
}
