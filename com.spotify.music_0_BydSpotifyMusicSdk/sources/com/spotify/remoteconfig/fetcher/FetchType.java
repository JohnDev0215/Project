package com.spotify.remoteconfig.fetcher;

/* JADX INFO: loaded from: classes.dex */
public enum FetchType {
    /* JADX INFO: Fake field, exist only in values array */
    UNKNOWN(0),
    /* JADX INFO: Fake field, exist only in values array */
    BACKGROUND_SYNC(1),
    /* JADX INFO: Fake field, exist only in values array */
    BLOCKING(2),
    ASYNC(3),
    /* JADX INFO: Fake field, exist only in values array */
    PUSH_INITIATED(4),
    /* JADX INFO: Fake field, exist only in values array */
    RECONNECT(5),
    /* JADX INFO: Fake field, exist only in values array */
    UNRECOGNIZED(-1);

    private final int number;

    FetchType(int i) {
        this.number = i;
    }
}
