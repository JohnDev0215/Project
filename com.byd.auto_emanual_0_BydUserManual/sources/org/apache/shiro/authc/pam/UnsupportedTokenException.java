package org.apache.shiro.authc.pam;

import org.apache.shiro.authc.AuthenticationException;

/* JADX INFO: loaded from: classes.dex */
public class UnsupportedTokenException extends AuthenticationException {
    public UnsupportedTokenException() {
    }

    public UnsupportedTokenException(String str) {
        super(str);
    }

    public UnsupportedTokenException(Throwable th) {
        super(th);
    }

    public UnsupportedTokenException(String str, Throwable th) {
        super(str, th);
    }
}
