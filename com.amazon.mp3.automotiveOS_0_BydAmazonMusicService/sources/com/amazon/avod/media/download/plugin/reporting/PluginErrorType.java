package com.amazon.avod.media.download.plugin.reporting;

/* JADX INFO: loaded from: classes2.dex */
public enum PluginErrorType {
    DOWNLOAD("DownloadError"),
    PARSE("ParseError"),
    RUNTIME("RuntimeError");

    private final String mEventSubtype;

    PluginErrorType(String str) {
        this.mEventSubtype = str;
    }

    public String getEventSubtype() {
        return this.mEventSubtype;
    }
}
