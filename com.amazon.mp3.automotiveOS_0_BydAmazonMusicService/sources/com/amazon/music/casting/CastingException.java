package com.amazon.music.casting;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;

/* JADX INFO: loaded from: classes4.dex */
public final class CastingException extends Exception {
    private final Reason reason;

    public enum Reason {
        NETWORK_ERROR,
        PROCESSING_ERROR,
        TIMED_OUT
    }

    public CastingException(Exception exc) {
        super(exc);
        if ((exc instanceof NoConnectionError) || (exc instanceof NetworkError)) {
            this.reason = Reason.NETWORK_ERROR;
        } else if (exc instanceof TimeoutError) {
            this.reason = Reason.TIMED_OUT;
        } else {
            this.reason = Reason.PROCESSING_ERROR;
        }
    }

    public Reason getReason() {
        return this.reason;
    }
}
