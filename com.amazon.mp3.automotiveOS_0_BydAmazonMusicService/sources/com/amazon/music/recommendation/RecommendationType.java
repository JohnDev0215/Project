package com.amazon.music.recommendation;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public enum RecommendationType {
    TRACK("TRACK"),
    ALBUM("ALBUM"),
    PLAYLIST("PLAYLIST"),
    STATION("STATION");

    private static Map<String, RecommendationType> lookup = new HashMap(4);
    private String value;

    static {
        for (RecommendationType recommendationType : values()) {
            lookup.put(recommendationType.value, recommendationType);
        }
    }

    RecommendationType(String str) {
        this.value = str;
    }

    public static RecommendationType fromString(String str) {
        return lookup.get(str);
    }
}
