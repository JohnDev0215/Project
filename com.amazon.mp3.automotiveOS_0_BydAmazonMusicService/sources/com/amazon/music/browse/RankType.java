package com.amazon.music.browse;

/* JADX INFO: loaded from: classes4.dex */
public enum RankType {
    TOP_SELLERS("top-sellers"),
    POPULAR("popularity-rank"),
    NEWLY_RELEASED("newly-released"),
    NEWLY_ADDED("newly-added");

    private final String value;

    RankType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
