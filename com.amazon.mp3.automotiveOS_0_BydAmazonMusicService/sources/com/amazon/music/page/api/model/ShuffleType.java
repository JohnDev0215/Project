package com.amazon.music.page.api.model;

import UpsellInterface.v1_0.PlaybackMode;

/* JADX INFO: loaded from: classes4.dex */
public enum ShuffleType {
    INLINE("INLINE"),
    MIXED("MIXED"),
    NONE(PlaybackMode.NONE);

    private String value;

    ShuffleType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ShuffleType fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        for (ShuffleType shuffleType : values()) {
            if (shuffleType.toString().equals(str)) {
                return shuffleType;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
