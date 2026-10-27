package org.apache.shiro.authz;

/* JADX INFO: loaded from: classes.dex */
public class UnauthenticatedException extends AuthorizationException {
    public UnauthenticatedException() {
    }

    public UnauthenticatedException(String str) {
        super(str);
    }

    public UnauthenticatedException(Throwable th) {
        super(th);
    }

    public UnauthenticatedException(String str, Throwable th) {
        super(str, th);
    }
}
