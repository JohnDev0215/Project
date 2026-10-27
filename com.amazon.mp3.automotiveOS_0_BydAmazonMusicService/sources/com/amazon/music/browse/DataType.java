package com.amazon.music.browse;

import com.amazon.mp3.environment.Environment;

/* JADX INFO: loaded from: classes4.dex */
public enum DataType {
    ALBUM("album"),
    PLAYLIST(Environment.PLAYLIST_PATH),
    TRACK("track");

    private final String value;

    DataType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
