package com.amazon.music.recommendation;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;

/* JADX INFO: loaded from: classes4.dex */
public final class RecommendationException extends Exception {
    private final Reason reason;

    public enum Reason {
        NETWORK_ERROR,
        PROCESSING_ERROR
    }

    public RecommendationException(Exception exc) {
        super(exc);
        if ((exc instanceof NoConnectionError) || (exc instanceof NetworkError) || (exc instanceof TimeoutError)) {
            this.reason = Reason.NETWORK_ERROR;
        } else {
            this.reason = Reason.PROCESSING_ERROR;
        }
    }
}
