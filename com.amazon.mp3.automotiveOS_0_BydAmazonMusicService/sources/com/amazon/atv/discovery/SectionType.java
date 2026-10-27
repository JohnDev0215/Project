package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum SectionType implements NamedEnum {
    TITLE_HEADER("titleHeader"),
    LEFT("left"),
    BOTTOM("bottom"),
    PAGE_NAVIGATION("pageNavigation"),
    GLOBAL_NAVIGATION("globalNavigation"),
    CENTER("center"),
    TITLE_CONTENT("titleContent"),
    TOP("top"),
    ORDERED_SECTION_1("ordered-section-1"),
    ORDERED_SECTION_3("ordered-section-3"),
    ORDERED_SECTION_2("ordered-section-2"),
    ORDERED_SECTION_5("ordered-section-5"),
    RIGHT("right"),
    ORDERED_SECTION_4("ordered-section-4"),
    EXTRAS("extras");

    private final String strValue;

    SectionType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static SectionType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (SectionType sectionType : values()) {
            if (sectionType.strValue.equals(str)) {
                return sectionType;
            }
        }
        return null;
    }
}
