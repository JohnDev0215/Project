package com.amazon.music.destination;

import com.amazon.music.destination.parser.ParserUtil;

/* JADX INFO: loaded from: classes4.dex */
public enum TargetType {
    TRACK(ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME),
    ALBUM("albumAsin"),
    PLAYLIST("playlistAsin"),
    ARTIST("artistAsin"),
    STATION("stationKey");

    private final String idKey;

    TargetType(String str) {
        this.idKey = str;
    }

    public String getIdKey() {
        return this.idKey;
    }
}
