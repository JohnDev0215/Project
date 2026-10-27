package com.amazon.music.recents;

import com.amazon.cirrus.shared.model.AccountNotFoundException;
import com.amazon.cirrus.shared.model.AuthenticationException;
import com.amazon.cirrus.shared.model.ServiceException;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;

/* JADX INFO: loaded from: classes4.dex */
public final class RecentsException extends Exception {
    private final Reason reason;

    public enum Reason {
        NETWORK_ERROR,
        SERVICE_ERROR,
        PROCESSING_ERROR,
        AUTHENTICATION_EXCEPTION,
        ACCOUNT_NOT_FOUND_EXCEPTION
    }

    public RecentsException(Throwable th) {
        super(th);
        if ((th instanceof NoConnectionError) || (th instanceof NetworkError) || (th instanceof TimeoutError)) {
            this.reason = Reason.NETWORK_ERROR;
            return;
        }
        if (th instanceof AuthenticationException) {
            this.reason = Reason.AUTHENTICATION_EXCEPTION;
            return;
        }
        if (th instanceof AccountNotFoundException) {
            this.reason = Reason.ACCOUNT_NOT_FOUND_EXCEPTION;
        } else if (th instanceof ServiceException) {
            this.reason = Reason.SERVICE_ERROR;
        } else {
            this.reason = Reason.PROCESSING_ERROR;
        }
    }

    public Reason getReason() {
        return this.reason;
    }
}
