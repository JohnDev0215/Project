package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum TemplateIdEnum implements NamedEnum {
    FEATURED_OFFERS_A("FEATURED_OFFERS_A"),
    TV_B("TV_B"),
    HERO_A("HERO_A"),
    TV_A("TV_A"),
    LIVE_A("LIVE_A"),
    MIXED_A("MIXED_A"),
    MOVIE_C("MOVIE_C"),
    MOVIE_A("MOVIE_A"),
    MOVIE_B("MOVIE_B");

    private final String strValue;

    TemplateIdEnum(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static TemplateIdEnum forValue(String str) {
        Preconditions.checkNotNull(str);
        for (TemplateIdEnum templateIdEnum : values()) {
            if (templateIdEnum.strValue.equals(str)) {
                return templateIdEnum;
            }
        }
        return null;
    }
}
