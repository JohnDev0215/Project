package com.amazon.music.widget.languagepreference;

/* JADX INFO: loaded from: classes4.dex */
public enum MetricConstants {
    DONE("languagePreferenceDone"),
    CLEAR_ALL("languagePreferenceClearAll"),
    LANGUAGE_PREFERENCE_PAGE_TYPE("LANGUAGE_PREFERENCE_WINDOW"),
    SEE_LANGUAGE_PREFERENCE("languagePreference");

    private String name;

    MetricConstants(String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
