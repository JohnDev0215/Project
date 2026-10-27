package com.spotify.automotive.pathfinder.type;

/* JADX INFO: loaded from: classes.dex */
public enum ContentRatingLabel {
    EXPLICIT("EXPLICIT"),
    NINETEEN_PLUS("NINETEEN_PLUS"),
    /* JADX INFO: Fake field, exist only in values array */
    NONE("NONE"),
    /* JADX INFO: Fake field, exist only in values array */
    UNKNOWN("UNKNOWN"),
    UNKNOWN__("UNKNOWN__");

    private final String rawValue;

    ContentRatingLabel(String str) {
        this.rawValue = str;
    }

    public final String a() {
        return this.rawValue;
    }
}
