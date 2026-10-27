package com.amazon.mp3.library.provider.source.nowplaying;

/* JADX INFO: loaded from: classes3.dex */
public class PlayQueueException extends Exception {
    private Reason mReason;

    public enum Reason {
        PLAYQUEUE_FULL,
        TRACKS_NOT_AVAILABLE,
        DB_NULL
    }

    public PlayQueueException(String str, Reason reason) {
        super(str);
        this.mReason = reason;
    }

    public Reason getReason() {
        return this.mReason;
    }
}
