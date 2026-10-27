package org.apache.shiro.authc.pam;

import org.apache.shiro.authc.AuthenticationException;

/* JADX INFO: loaded from: classes.dex */
public class ShortCircuitIterationException extends AuthenticationException {
    public ShortCircuitIterationException() {
    }

    public ShortCircuitIterationException(String str) {
        super(str);
    }

    public ShortCircuitIterationException(Throwable th) {
        super(th);
    }

    public ShortCircuitIterationException(String str, Throwable th) {
        super(str, th);
    }
}
