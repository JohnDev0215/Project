package com.amazon.music.chromecast;

/* JADX INFO: loaded from: classes4.dex */
public enum StreamType {
    HLS("application/x-mpegURL"),
    DASH("application/dash+xml"),
    PROGRESSIVE("");

    private String mimeType;

    StreamType(String str) {
        this.mimeType = str;
    }

    public String getMimeType() {
        return this.mimeType;
    }
}
