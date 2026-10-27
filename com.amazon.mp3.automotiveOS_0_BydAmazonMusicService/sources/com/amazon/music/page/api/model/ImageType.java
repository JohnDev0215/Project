package com.amazon.music.page.api.model;

/* JADX INFO: loaded from: classes4.dex */
public enum ImageType {
    BANNER("BANNER"),
    DARK_BACKGROUND("DARK_BACKGROUND"),
    FOUR_BY_ONE("FOUR_BY_ONE"),
    HERO("HERO"),
    ICON("ICON"),
    LIGHT_BACKGROUND("LIGHT_BACKGROUND"),
    ORIGINAL_ART("ORIGINAL_ART"),
    SIXTEEN_BY_NINE("SIXTEEN_BY_NINE");

    private String value;

    ImageType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ImageType fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        for (ImageType imageType : values()) {
            if (imageType.toString().equals(str)) {
                return imageType;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
