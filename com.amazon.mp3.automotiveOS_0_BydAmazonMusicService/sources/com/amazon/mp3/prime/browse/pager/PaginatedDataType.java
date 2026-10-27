package com.amazon.mp3.prime.browse.pager;

import com.amazon.mp3.environment.Environment;

/* JADX INFO: loaded from: classes4.dex */
public enum PaginatedDataType {
    ALBUM("album"),
    PLAYLIST(Environment.PLAYLIST_PATH),
    TRACK("track"),
    STATION("station");

    private final String value;

    PaginatedDataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
