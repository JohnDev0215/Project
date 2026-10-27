package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public class ExcessiveAttemptsException extends AccountException {
    public ExcessiveAttemptsException() {
    }

    public ExcessiveAttemptsException(String str) {
        super(str);
    }

    public ExcessiveAttemptsException(Throwable th) {
        super(th);
    }

    public ExcessiveAttemptsException(String str, Throwable th) {
        super(str, th);
    }
}
