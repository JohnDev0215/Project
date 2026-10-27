package org.apache.http.auth;

import org.apache.http.ProtocolException;

/* JADX INFO: loaded from: classes7.dex */
public class AuthenticationException extends ProtocolException {
    public AuthenticationException() {
    }

    public AuthenticationException(String str) {
        super(str);
    }

    public AuthenticationException(String str, Throwable th) {
        super(str, th);
    }
}
