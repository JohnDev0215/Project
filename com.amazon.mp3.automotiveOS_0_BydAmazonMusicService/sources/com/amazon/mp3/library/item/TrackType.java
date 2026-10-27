package com.amazon.mp3.library.item;

/* JADX INFO: loaded from: classes3.dex */
public enum TrackType {
    LIBRARY(0),
    PRIME_BROWSE(1),
    CATALOG_PLAYLIST_TRACK(2),
    STATION(3);

    private final int mTypeId;

    TrackType(int typeId) {
        this.mTypeId = typeId;
    }

    public int getTypeId() {
        return this.mTypeId;
    }

    public static TrackType fromTypeId(int typeId) {
        if (typeId == 0) {
            return LIBRARY;
        }
        if (typeId == 1) {
            return PRIME_BROWSE;
        }
        if (typeId == 2) {
            return CATALOG_PLAYLIST_TRACK;
        }
        if (typeId == 3) {
            return STATION;
        }
        throw new IllegalArgumentException("Unknown Track Type");
    }
}
