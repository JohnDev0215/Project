package com.amazon.mp3.prime.browse.pager;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public enum PaginatedRankType {
    TOP_SELLERS("top-sellers"),
    POPULAR("popularity-rank"),
    RELEVANCE("relevancerank"),
    NEWLY_RELEASED("newly-released"),
    NEWLY_ADDED("newly-added"),
    RECOMMENDED("recommended"),
    SIMILAR("similarity");

    private static Map<String, PaginatedRankType> valueMap = new HashMap();
    private final String value;

    static {
        for (PaginatedRankType paginatedRankType : values()) {
            valueMap.put(paginatedRankType.getValue(), paginatedRankType);
        }
    }

    PaginatedRankType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static PaginatedRankType get(String value) {
        return valueMap.get(value);
    }
}
