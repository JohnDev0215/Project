package com.amazon.music.page.api.model;

/* JADX INFO: loaded from: classes4.dex */
public enum SeedType {
    ARTIST("ARTIST"),
    CUSTOMER("CUSTOMER"),
    GENRE("GENRE");

    private String value;

    SeedType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static SeedType fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        for (SeedType seedType : values()) {
            if (seedType.toString().equals(str)) {
                return seedType;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
