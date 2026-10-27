package com.amazon.music.browse.artist;

/* JADX INFO: loaded from: classes4.dex */
public enum SortBy {
    RELEVANCE("relevancerank"),
    SALES("salesrank"),
    TITLE("titlerank"),
    TITLE_DESC("-titlerank"),
    ARTIST_ALBUM("artistalbumrank"),
    ARTIST_ALBUM_DESC("-artistalbumrank"),
    ALBUM("albumrank"),
    ALBUM_DESC("-albumrank"),
    RUNTIME("runtimerank"),
    RUNTIME_DESC("-runtimerank"),
    PRICE("pricerank"),
    PRICE_DESC("-pricerank"),
    REVIEWS_AUTHORITY("reviewrank_authority"),
    RELEASE_DATE_DESC("-releasedate"),
    POPULARITY("popularity-rank"),
    POPULARITY_DESC("-popularity-rank");

    private final String value;

    SortBy(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
