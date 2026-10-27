package com.vivid.sdk.spotify;

/* JADX INFO: loaded from: classes5.dex */
public enum ShuffleMode {
    SHUFFLE_MODE_NONE(0),
    SHUFFLE_MODE_ALL(1);

    private int code;

    ShuffleMode(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
