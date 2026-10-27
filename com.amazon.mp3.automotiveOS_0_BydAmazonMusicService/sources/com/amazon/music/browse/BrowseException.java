package com.amazon.music.browse;

import com.amazon.musicensembleservice.AuthenticationException;
import com.amazon.musicensembleservice.DeviceNotAuthorizedException;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;

/* JADX INFO: loaded from: classes4.dex */
public final class BrowseException extends Exception {
    private final Reason reason;

    public enum Reason {
        NETWORK_ERROR,
        PROCESSING_ERROR,
        AUTHENTICATION_ERROR
    }

    public BrowseException(Exception exc) {
        super(exc);
        if ((exc instanceof NoConnectionError) || (exc instanceof NetworkError) || (exc instanceof TimeoutError)) {
            this.reason = Reason.NETWORK_ERROR;
        } else if (isAuthenticationException(exc)) {
            this.reason = Reason.AUTHENTICATION_ERROR;
        } else {
            this.reason = Reason.PROCESSING_ERROR;
        }
    }

    private boolean isAuthenticationException(Exception exc) {
        return exc != null && ((exc instanceof AuthenticationException) || (exc instanceof DeviceNotAuthorizedException) || isAuthenticationException(exc.getMessage()));
    }

    private boolean isAuthenticationException(String str) {
        return str != null && (str.contains(AuthenticationException.class.toString()) || str.contains(DeviceNotAuthorizedException.class.toString()));
    }

    public Reason getReason() {
        return this.reason;
    }
}
