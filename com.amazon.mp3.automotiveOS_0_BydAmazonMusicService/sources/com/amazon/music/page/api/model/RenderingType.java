package com.amazon.music.page.api.model;

/* JADX INFO: loaded from: classes4.dex */
public enum RenderingType {
    BUTTON_NAVIGATOR("BUTTON_NAVIGATOR"),
    DESCRIPTIVE_LISTING("DESCRIPTIVE_LISTING"),
    DESCRIPTIVE_SHOVELER("DESCRIPTIVE_SHOVELER"),
    DESCRIPTIVE_SHOWCASE("DESCRIPTIVE_SHOWCASE"),
    FEATURED_BANNER("FEATURED_BANNER"),
    FEATURED_CARD("FEATURED_CARD"),
    FEATURED_CAROUSEL("FEATURED_CAROUSEL"),
    FEATURED_PLAY("FEATURED_PLAY"),
    FEATURED_SHOVELER("FEATURED_SHOVELER"),
    LINK_NAVIGATOR("LINK_NAVIGATOR"),
    PILL_NAVIGATOR("PILL_NAVIGATOR"),
    TEXT_DESCRIPTION("TEXT_DESCRIPTION"),
    THUMBNAIL_NAVIGATOR("THUMBNAIL_NAVIGATOR"),
    THUMBNAIL_LISTING("THUMBNAIL_LISTING"),
    THUMBNAIL_SHOVELER("THUMBNAIL_SHOVELER"),
    THUMBNAIL_SHOWCASE("THUMBNAIL_SHOWCASE"),
    VISUAL_LISTING("VISUAL_LISTING"),
    VISUAL_SHOVELER("VISUAL_SHOVELER"),
    VISUAL_SHOWCASE("VISUAL_SHOWCASE"),
    GENRE_DETAIL_HEADER("GENRE_DETAIL_HEADER"),
    UHD_DETAIL_HEADER("UHD_DETAIL_HEADER"),
    THREED_DETAIL_HEADER("THREED_DETAIL_HEADER"),
    PLAY_BUTTON_HEADER("PLAY_BUTTON_HEADER"),
    PRESET_SINGLE_ENTITY("PRESET_SINGLE_ENTITY"),
    PRESET_MULTI_ENTITY("PRESET_MULTI_ENTITY"),
    CARMODE_VISUAL_SHOVELER("CARMODE_VISUAL_SHOVELER"),
    CARMODE_THUMBNAIL_LISTING("CARMODE_THUMBNAIL_LISTING"),
    CARMODE_BUTTON_NAVIGATOR("CARMODE_BUTTON_NAVIGATOR"),
    CARMODE_SINGLE_LIST("CARMODE_SINGLE_LIST"),
    PREVIEW_CARD("PREVIEW_CARD");

    private String value;

    RenderingType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static RenderingType fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        for (RenderingType renderingType : values()) {
            if (renderingType.toString().equals(str)) {
                return renderingType;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
