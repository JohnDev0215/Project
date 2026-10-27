package com.amazon.music.station;

import UpsellInterface.v1_0.UpsellType;

/* JADX INFO: loaded from: classes4.dex */
public enum StationContentSelector {
    PRIME(UpsellType.PRIME),
    MUSIC_SUBSCRIPTION("MUSIC_SUBSCRIPTION"),
    KATANA("KATANA"),
    FREE(UpsellType.FREE),
    SONIC_RUSH("SONIC_RUSH");

    public final String value;

    StationContentSelector(String str) {
        this.value = str;
    }
}
