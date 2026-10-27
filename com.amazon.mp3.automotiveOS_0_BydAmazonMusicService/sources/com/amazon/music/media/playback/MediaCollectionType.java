package com.amazon.music.media.playback;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MediaCollectionType {
    private final String name;
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) MediaCollectionType.class);
    private static final Map<String, MediaCollectionType> types = new HashMap();
    public static final MediaCollectionType MATCH = getCollectionType("MATCH");
    public static final MediaCollectionType CONFERENCE = getCollectionType("CONFERENCE");
    public static final MediaCollectionType SHOW = getCollectionType("SHOW");
    public static final MediaCollectionType ALBUM = getCollectionType("ALBUM");
    public static final MediaCollectionType ARTIST = getCollectionType("ARTIST");
    public static final MediaCollectionType AUTO_PLAYLIST = getCollectionType("AUTO_PLAYLIST");
    public static final MediaCollectionType GENRE = getCollectionType("GENRE");
    public static final MediaCollectionType GNO_PRIME_PLAYLIST = getCollectionType("GNO_PRIME_PLAYLIST");
    public static final MediaCollectionType GNO_UNL_PLAYLIST = getCollectionType("GNO_UNL_PLAYLIST");
    public static final MediaCollectionType GOLDEN_PLAYLIST = getCollectionType("GOLDEN_PLAYLIST");
    public static final MediaCollectionType PRIME_PLAYLIST = getCollectionType("PRIME_PLAYLIST");
    public static final MediaCollectionType PRIME_STATION = getCollectionType("PRIME_STATION");
    public static final MediaCollectionType UNLIMITED_PLAYLIST = getCollectionType("UNLIMITED_PLAYLIST");
    public static final MediaCollectionType UNLIMITED_STATION = getCollectionType("UNLIMITED_STATION");
    public static final MediaCollectionType USER_PLAYLIST = getCollectionType("USER_PLAYLIST");
    public static final MediaCollectionType SHARED_PLAYLIST = getCollectionType("SHARED_PLAYLIST");
    public static final MediaCollectionType SIMILARITY = getCollectionType("SIMILARITY");
    public static final MediaCollectionType AMF_STATION_SEED = getCollectionType("AMF_STATION_SEED");
    public static final MediaCollectionType PRIME_STATION_SEED = getCollectionType("PRIME_STATION_SEED");
    public static final MediaCollectionType UNLIMITED_STATION_SEED = getCollectionType("UNL_STATION_SEED");
    public static final MediaCollectionType AMF_STATION = getCollectionType("AMF_STATION");
    public static final MediaCollectionType HTTPS = getCollectionType("HTTPS");
    public static final MediaCollectionType SONGS = getCollectionType("SONGS");

    public static MediaCollectionType getCollectionType(@NonNull String str) {
        MediaCollectionType mediaCollectionType;
        Map<String, MediaCollectionType> map = types;
        synchronized (map) {
            mediaCollectionType = map.get(str);
            if (mediaCollectionType == null) {
                mediaCollectionType = new MediaCollectionType(str);
                logger.warn("getCollectionType is called with an unregistered name: " + str);
            }
        }
        return mediaCollectionType;
    }

    private MediaCollectionType(String str) {
        this.name = (String) Validate.notEmpty(str, "CollectionType name cannot be empty", new Object[0]);
        types.put(str, this);
    }

    public String getMetricsName() {
        return this.name;
    }

    public String toString() {
        return "MediaCollectionType{name='" + this.name + "'}";
    }
}
