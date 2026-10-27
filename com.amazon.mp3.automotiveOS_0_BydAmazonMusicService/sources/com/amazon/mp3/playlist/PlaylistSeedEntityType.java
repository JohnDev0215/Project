package com.amazon.mp3.playlist;

/* JADX INFO: loaded from: classes3.dex */
public enum PlaylistSeedEntityType {
    ALBUM("ALBUM"),
    TRACK("TRACK"),
    ARTIST("ARTIST"),
    PLAYLIST("PLAYLIST"),
    GENRE("GENRE");

    private final String strValue;

    PlaylistSeedEntityType(String strValue) {
        this.strValue = strValue;
    }

    public String getValue() {
        return this.strValue;
    }
}
