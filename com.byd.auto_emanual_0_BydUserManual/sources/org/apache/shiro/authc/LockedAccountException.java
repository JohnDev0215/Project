package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public class LockedAccountException extends DisabledAccountException {
    public LockedAccountException() {
    }

    public LockedAccountException(String str) {
        super(str);
    }

    public LockedAccountException(Throwable th) {
        super(th);
    }

    public LockedAccountException(String str, Throwable th) {
        super(str, th);
    }
}
