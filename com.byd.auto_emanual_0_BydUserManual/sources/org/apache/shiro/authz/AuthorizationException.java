package org.apache.shiro.authz;

import org.apache.shiro.ShiroException;

/* JADX INFO: loaded from: classes.dex */
public class AuthorizationException extends ShiroException {
    public AuthorizationException() {
    }

    public AuthorizationException(String str) {
        super(str);
    }

    public AuthorizationException(Throwable th) {
        super(th);
    }

    public AuthorizationException(String str, Throwable th) {
        super(str, th);
    }
}
