package com.spotify.automotive.pathfinder.type;

/* JADX INFO: loaded from: classes.dex */
public enum EpisodePlaybackState {
    COMPLETED("COMPLETED"),
    /* JADX INFO: Fake field, exist only in values array */
    IN_PROGRESS("IN_PROGRESS"),
    /* JADX INFO: Fake field, exist only in values array */
    NOT_STARTED("NOT_STARTED"),
    UNKNOWN__("UNKNOWN__");

    private final String rawValue;

    EpisodePlaybackState(String str) {
        this.rawValue = str;
    }

    public final String a() {
        return this.rawValue;
    }
}
