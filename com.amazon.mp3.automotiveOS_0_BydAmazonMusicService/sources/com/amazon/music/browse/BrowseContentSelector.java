package com.amazon.music.browse;

import UpsellInterface.v1_0.UpsellType;

/* JADX INFO: loaded from: classes4.dex */
public enum BrowseContentSelector {
    SONIC_RUSH("SONIC_RUSH"),
    PRIME(UpsellType.PRIME),
    MUSIC_SUBSCRIPTION("MUSIC_SUBSCRIPTION"),
    FREE(UpsellType.FREE),
    KATANA("KATANA"),
    FULL_CATALOG("FULL_CATALOG"),
    ALL_STREAMABLE("ALL_STREAMABLE"),
    PRIME_UPSELL_MS("PRIME_UPSELL_MS");

    public final String value;

    BrowseContentSelector(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
