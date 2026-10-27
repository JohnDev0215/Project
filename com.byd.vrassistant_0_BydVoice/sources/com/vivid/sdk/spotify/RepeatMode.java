package com.vivid.sdk.spotify;

/* JADX INFO: loaded from: classes3.dex */
public enum RepeatMode {
    REPEAT_MODE_NONE(0),
    REPEAT_MODE_ONE(1),
    REPEAT_MODE_ALL(2);

    private int code;

    RepeatMode(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
