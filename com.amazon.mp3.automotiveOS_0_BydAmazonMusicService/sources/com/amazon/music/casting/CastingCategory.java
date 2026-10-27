package com.amazon.music.casting;

/* JADX INFO: loaded from: classes4.dex */
public enum CastingCategory {
    UNDEFINED("UNDEFINED"),
    ALEXA_1P("ALEXA_1P"),
    ALEXA_WHA("ALEXA_WHA"),
    ALEXA_3P("ALEXA_3P"),
    FIRE_TV("FIRE_TV"),
    FIRE_TABLET("FIRE_TABLET"),
    CHROMECAST("CHROMECAST");

    private String mName;

    CastingCategory(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public static CastingCategory fromString(String str) {
        for (CastingCategory castingCategory : values()) {
            if (castingCategory.mName.equals(str)) {
                return castingCategory;
            }
        }
        return UNDEFINED;
    }
}
