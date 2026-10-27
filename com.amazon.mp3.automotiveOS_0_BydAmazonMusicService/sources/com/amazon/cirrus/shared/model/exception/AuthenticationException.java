package com.amazon.cirrus.shared.model.exception;

import com.amazon.cirrus.shared.model.exception.CirrusBaseException;

/* JADX INFO: loaded from: classes2.dex */
public class AuthenticationException extends CirrusBaseException {
    private static final long serialVersionUID = -1;
    private String reason;

    public static class Builder extends CirrusBaseException.Builder {
    }

    public AuthenticationException() {
    }

    public AuthenticationException(String str) {
        super(str);
    }

    public AuthenticationException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public AuthenticationException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }
}
