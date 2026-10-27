package com.amazon.music.search;

import UpsellInterface.v1_0.UpsellType;

/* JADX INFO: loaded from: classes4.dex */
public enum SearchContentTier {
    PRIME(UpsellType.PRIME),
    SONIC_RUSH("SONIC_RUSH"),
    NIGHTWING_ONDEMAND_PLAYABLE("NIGHTWING_ONDEMAND_PLAYABLE"),
    UNLIMITED(UpsellType.UNLIMITED);

    private final String value;

    SearchContentTier(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
