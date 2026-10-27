package com.amazon.mrn.v1;

import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;

/* JADX INFO: compiled from: MrnIdentifierType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/mrn/v1/MrnIdentifierType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "toString", "ASIN_LIST", "PLAYLIST_ID", "ASIN", "TITLESET_ASIN", "OBJECT_ID", "STATION_KEY", "STATION_SEED_ID", "UUID", "TAG", "AmazonMusicMediaResourceNameKotlin"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum MrnIdentifierType {
    ASIN_LIST("asins"),
    PLAYLIST_ID("playlistid"),
    ASIN("asin"),
    TITLESET_ASIN("titlesetasin"),
    OBJECT_ID("objectid"),
    STATION_KEY("key"),
    STATION_SEED_ID("seedid"),
    UUID("uuid"),
    TAG(ParserUtil.TAG_QUERY_PARAM_NAME);

    private final String value;

    MrnIdentifierType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
