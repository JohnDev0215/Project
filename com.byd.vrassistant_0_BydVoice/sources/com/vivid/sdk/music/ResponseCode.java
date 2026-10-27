package com.vivid.sdk.music;

/* JADX INFO: loaded from: classes3.dex */
public enum ResponseCode {
    UNKNOW_ERROR(-1),
    OK(0),
    SERVICE_DISCONNECTED(1),
    USER_NOT_LOGIN(2),
    SPOTIFY_NOT_AVAILABLE(3),
    MODE_UNSUPPORT(4),
    CURRENT_MEDIA_IS_NULL(5),
    MEDIA_UNSUPPORT_FAVORIT(6),
    REQUEST_TIMEOUT(7),
    NO_NEXT_SONG(8),
    MEDIA_IS_COLLECTED(9),
    MEDIA_IS_NOT_COLLECTED(10),
    REPEAT_SET_MODE(11),
    UNSUPPORT_OPERATION(12),
    NO_PREV_SONG(13);

    private int code;

    ResponseCode(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
