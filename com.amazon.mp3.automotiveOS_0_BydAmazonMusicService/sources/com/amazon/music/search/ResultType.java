package com.amazon.music.search;

/* JADX INFO: loaded from: classes4.dex */
public enum ResultType {
    Catalog("catalog_%s"),
    Library("library_%s"),
    Sports("livesports_%s"),
    Community("community_%s"),
    Podcast_Catalog("catalog_podcast_%s"),
    LiveEvent("live_event");

    private final String mResultLabelFormat;

    ResultType(String str) {
        this.mResultLabelFormat = str;
    }

    public String getResultLabelFormat() {
        return this.mResultLabelFormat;
    }

    public boolean isLibrary() {
        return this == Library;
    }
}
