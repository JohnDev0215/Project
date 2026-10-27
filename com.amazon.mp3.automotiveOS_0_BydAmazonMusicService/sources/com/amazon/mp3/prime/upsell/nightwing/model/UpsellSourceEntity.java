package com.amazon.mp3.prime.upsell.nightwing.model;

/* JADX INFO: loaded from: classes4.dex */
public enum UpsellSourceEntity {
    ALBUM("ALBUM"),
    TRACK("TRACK"),
    ARTIST("ARTIST"),
    PLAYLIST("PLAYLIST"),
    STATION("STATION"),
    GENRE("GENRE"),
    ALEXA_HISTORY("ALEXA_HISTORY"),
    ALBUM_PAGE("ALBUM_PAGE"),
    PLAYLIST_DETAIL("PLAYLIST_DETAIL"),
    PERSISTENT_UPSELL_INGRESS("PERSISTENT_UPSELL_INGRESS"),
    UNKNOWN_ENTITY("UNKNOWN_ENTITY");

    private final String value;

    UpsellSourceEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
