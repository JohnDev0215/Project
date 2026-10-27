package com.spotify.automotive.auth;

import com.spotify.automotive.auth.model.CoreNativeResponseException;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class LoginException extends Exception {
    private static final long serialVersionUID = 0;
    private final LoginError mError;

    public enum LoginError {
        API_RATE_LIMITED(R.string.login_error_api_rate_limited),
        BAD_CREDENTIALS(R.string.login_error_login_bad_credentials),
        GENERAL_ERROR(R.string.login_error_general_login),
        TRAVEL_RESTRICTION(R.string.login_error_travel_restriction),
        UNKNOWN(R.string.login_error_unknown);

        private final int mStringResId;

        LoginError(int i) {
            this.mStringResId = i;
        }

        public final int a() {
            return this.mStringResId;
        }
    }

    public LoginException() {
        this.mError = LoginError.GENERAL_ERROR;
    }

    public final LoginError a() {
        return this.mError;
    }

    public LoginException(CoreNativeResponseException coreNativeResponseException) {
        super(coreNativeResponseException);
        if (coreNativeResponseException.e()) {
            this.mError = LoginError.TRAVEL_RESTRICTION;
            return;
        }
        if (coreNativeResponseException.d()) {
            this.mError = LoginError.BAD_CREDENTIALS;
        } else if (coreNativeResponseException.b() == 429) {
            this.mError = LoginError.API_RATE_LIMITED;
        } else {
            this.mError = LoginError.GENERAL_ERROR;
        }
    }

    public LoginException(int i) {
        this.mError = LoginError.BAD_CREDENTIALS;
    }
}
