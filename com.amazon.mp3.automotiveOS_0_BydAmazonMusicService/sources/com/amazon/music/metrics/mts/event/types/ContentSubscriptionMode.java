package com.amazon.music.metrics.mts.event.types;

import UpsellInterface.v1_0.PlaybackMode;
import UpsellInterface.v1_0.UpsellType;

/* JADX INFO: loaded from: classes4.dex */
public enum ContentSubscriptionMode {
    NONE(PlaybackMode.NONE),
    AMF("AMF"),
    PRIME(UpsellType.PRIME),
    UNLIMITED(UpsellType.UNLIMITED),
    KATANA("UNLIMITED_HD"),
    PRIME_MUSIC("PM"),
    HAWKFIRE("HF");

    private final String mAttribute;

    ContentSubscriptionMode(String str) {
        this.mAttribute = str;
    }

    public final String getAttribute() {
        return this.mAttribute;
    }
}
