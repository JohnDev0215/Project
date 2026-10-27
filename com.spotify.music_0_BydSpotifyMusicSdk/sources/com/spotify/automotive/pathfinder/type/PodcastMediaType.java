package com.spotify.automotive.pathfinder.type;

/* JADX INFO: loaded from: classes.dex */
public enum PodcastMediaType {
    /* JADX INFO: Fake field, exist only in values array */
    AUDIO("AUDIO"),
    /* JADX INFO: Fake field, exist only in values array */
    MIXED("MIXED"),
    /* JADX INFO: Fake field, exist only in values array */
    UNKNOWN("UNKNOWN"),
    VIDEO("VIDEO"),
    UNKNOWN__("UNKNOWN__");

    private final String rawValue;

    PodcastMediaType(String str) {
        this.rawValue = str;
    }

    public final String a() {
        return this.rawValue;
    }
}
