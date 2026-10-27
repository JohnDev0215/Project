package org.apache.shiro.authz;

/* JADX INFO: loaded from: classes.dex */
public class UnauthorizedException extends AuthorizationException {
    public UnauthorizedException() {
    }

    public UnauthorizedException(String str) {
        super(str);
    }

    public UnauthorizedException(Throwable th) {
        super(th);
    }

    public UnauthorizedException(String str, Throwable th) {
        super(str, th);
    }
}
