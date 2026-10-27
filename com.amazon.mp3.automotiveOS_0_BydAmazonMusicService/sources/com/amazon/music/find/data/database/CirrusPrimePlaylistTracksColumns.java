package com.amazon.music.find.data.database;

import kotlin.Metadata;

/* JADX INFO: compiled from: CirrusDatabaseProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/music/find/data/database/CirrusPrimePlaylistTracksColumns;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "_ID", "ASIN", "TITLE", "SORT_TITLE", "ARTIST", "ALBUM", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum CirrusPrimePlaylistTracksColumns {
    _ID("_id"),
    ASIN("asin"),
    TITLE("title"),
    SORT_TITLE("sort_title"),
    ARTIST("artist"),
    ALBUM("album");

    private final String value;

    CirrusPrimePlaylistTracksColumns(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
