package com.vivid.sdk.spotify;

/* JADX INFO: loaded from: classes5.dex */
public enum PlayState {
    STATE_NONE(0),
    STATE_STOPPED(1),
    STATE_PAUSED(2),
    STATE_PLAYING(3),
    STATE_FAST_FORWARDING(4),
    STATE_REWINDING(5),
    STATE_BUFFERING(6),
    STATE_ERROR(7),
    STATE_CONNECTING(8),
    STATE_SKIPPING_TO_PREVIOUS(9),
    STATE_SKIPPING_TO_NEXT(10),
    STATE_SKIPPING_TO_QUEUE_ITEM(11);

    private int code;

    PlayState(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    public static PlayState getPlayState(int i) {
        switch (i) {
            case 0:
                return STATE_NONE;
            case 1:
                return STATE_STOPPED;
            case 2:
                return STATE_PAUSED;
            case 3:
                return STATE_PLAYING;
            case 4:
                return STATE_FAST_FORWARDING;
            case 5:
                return STATE_REWINDING;
            case 6:
                return STATE_BUFFERING;
            case 7:
                return STATE_ERROR;
            case 8:
                return STATE_CONNECTING;
            case 9:
                return STATE_SKIPPING_TO_PREVIOUS;
            case 10:
                return STATE_SKIPPING_TO_NEXT;
            case 11:
                return STATE_SKIPPING_TO_QUEUE_ITEM;
            default:
                return STATE_NONE;
        }
    }
}
