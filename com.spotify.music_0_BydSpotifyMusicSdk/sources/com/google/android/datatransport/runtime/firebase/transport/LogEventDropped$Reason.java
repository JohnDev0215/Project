package com.google.android.datatransport.runtime.firebase.transport;

import p.py1;

/* JADX INFO: loaded from: classes.dex */
public enum LogEventDropped$Reason implements py1 {
    REASON_UNKNOWN(0),
    MESSAGE_TOO_OLD(1),
    CACHE_FULL(2),
    PAYLOAD_TOO_BIG(3),
    MAX_RETRIES_REACHED(4),
    INVALID_PAYLOD(5),
    SERVER_ERROR(6);

    private final int number_;

    LogEventDropped$Reason(int i) {
        this.number_ = i;
    }

    public final int a() {
        return this.number_;
    }
}
