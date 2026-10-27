package com.amazon.avod.media.download.plugin;

/* JADX INFO: loaded from: classes2.dex */
public enum PluginLogConstants$DownloadError {
    NO_DATA_CONNECTION("No data connection"),
    NULL_FILE("Null File"),
    NULL_URL("Null Url"),
    INVALID_FILE("Invalid File"),
    EMPTY_URL("Empty Url"),
    INVALID_URL("Invalid Url");

    private final String mError;

    PluginLogConstants$DownloadError(String str) {
        this.mError = str;
    }

    public String getError() {
        return this.mError;
    }
}
