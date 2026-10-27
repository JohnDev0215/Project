package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public class DisabledAccountException extends AccountException {
    public DisabledAccountException() {
    }

    public DisabledAccountException(String str) {
        super(str);
    }

    public DisabledAccountException(Throwable th) {
        super(th);
    }

    public DisabledAccountException(String str, Throwable th) {
        super(str, th);
    }
}
