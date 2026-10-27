package org.apache.shiro.authc;

import org.apache.shiro.ShiroException;

/* JADX INFO: loaded from: classes.dex */
public class AuthenticationException extends ShiroException {
    public AuthenticationException() {
    }

    public AuthenticationException(String str) {
        super(str);
    }

    public AuthenticationException(Throwable th) {
        super(th);
    }

    public AuthenticationException(String str, Throwable th) {
        super(str, th);
    }
}
