package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
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

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }
}
