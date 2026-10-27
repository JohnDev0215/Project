package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public class AccountException extends AuthenticationException {
    public AccountException() {
    }

    public AccountException(String str) {
        super(str);
    }

    public AccountException(Throwable th) {
        super(th);
    }

    public AccountException(String str, Throwable th) {
        super(str, th);
    }
}
