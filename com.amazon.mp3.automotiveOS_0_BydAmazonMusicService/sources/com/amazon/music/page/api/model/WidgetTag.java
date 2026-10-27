package com.amazon.music.page.api.model;

/* JADX INFO: loaded from: classes4.dex */
public enum WidgetTag {
    FALLBACK("FALLBACK"),
    MUSE("MUSE"),
    MUSICAL_MEMORIES("MUSICAL_MEMORIES"),
    RECENT_SEARCHES("RECENT_SEARCHES"),
    RECENTLY_PLAYED("RECENTLY_PLAYED"),
    RECOMMENDATION("RECOMMENDATION");

    private String value;

    WidgetTag(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static WidgetTag fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        for (WidgetTag widgetTag : values()) {
            if (widgetTag.toString().equals(str)) {
                return widgetTag;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
