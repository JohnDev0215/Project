package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public class ConcurrentAccessException extends AccountException {
    public ConcurrentAccessException() {
    }

    public ConcurrentAccessException(String str) {
        super(str);
    }

    public ConcurrentAccessException(Throwable th) {
        super(th);
    }

    public ConcurrentAccessException(String str, Throwable th) {
        super(str, th);
    }
}
