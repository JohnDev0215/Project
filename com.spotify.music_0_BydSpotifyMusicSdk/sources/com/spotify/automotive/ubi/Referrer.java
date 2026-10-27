package com.spotify.automotive.ubi;

/* JADX INFO: loaded from: classes.dex */
public enum Referrer {
    BROWSE("browse"),
    HOME("home"),
    LIBRARY("your_library"),
    RECENTS("recently_played"),
    SEARCH("search"),
    VOICE("voice"),
    PARTNER_VOICE("partner_voice");

    private final String id;

    Referrer(String str) {
        this.id = str;
    }

    public final String a() {
        return this.id;
    }
}
