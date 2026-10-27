package com.amazon.avod.media.download.plugin.reporting;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleState {
    ENABLED("Enabled", "Enabled"),
    DISABLED("Disabled", "Disabled"),
    LANGUAGE_CHANGED("LanguageChanged", "Enabled");

    private final String mEventSubtype;
    private final String mName;

    SubtitleState(String str, String str2) {
        this.mName = str;
        this.mEventSubtype = str2;
    }

    @Nonnull
    String getQosName() {
        return this.mName;
    }

    @Nonnull
    String getEventSubtype() {
        return this.mEventSubtype;
    }
}
