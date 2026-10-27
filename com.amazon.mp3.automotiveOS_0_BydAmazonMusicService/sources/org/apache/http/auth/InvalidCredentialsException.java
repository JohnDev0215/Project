package org.apache.http.auth;

/* JADX INFO: loaded from: classes7.dex */
public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException() {
    }

    public InvalidCredentialsException(String str) {
        super(str);
    }

    public InvalidCredentialsException(String str, Throwable th) {
        super(str, th);
    }
}
