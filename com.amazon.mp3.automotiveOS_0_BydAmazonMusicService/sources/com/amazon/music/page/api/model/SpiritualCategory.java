package com.amazon.music.page.api.model;

/* JADX INFO: loaded from: classes4.dex */
public enum SpiritualCategory {
    ISLAMIAT("ISLAMIAT"),
    QURAN("QURAN");

    private String value;

    SpiritualCategory(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static SpiritualCategory fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        for (SpiritualCategory spiritualCategory : values()) {
            if (spiritualCategory.toString().equals(str)) {
                return spiritualCategory;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
