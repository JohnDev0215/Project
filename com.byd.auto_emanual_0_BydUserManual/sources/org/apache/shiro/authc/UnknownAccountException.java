package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public class UnknownAccountException extends AccountException {
    public UnknownAccountException() {
    }

    public UnknownAccountException(String str) {
        super(str);
    }

    public UnknownAccountException(Throwable th) {
        super(th);
    }

    public UnknownAccountException(String str, Throwable th) {
        super(str, th);
    }
}
