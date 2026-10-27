package com.amazon.music.recommendation;

import UpsellInterface.v1_0.UpsellType;

/* JADX INFO: loaded from: classes4.dex */
public enum RecommendationsContentSelector {
    PRIME(UpsellType.PRIME),
    MUSIC_SUBSCRIPTION("MUSIC_SUBSCRIPTION"),
    KATANA("KATANA"),
    FREE(UpsellType.FREE);

    public final String value;

    RecommendationsContentSelector(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
