package org.apache.shiro.session;

/* JADX INFO: loaded from: classes.dex */
public class ExpiredSessionException extends StoppedSessionException {
    public ExpiredSessionException() {
    }

    public ExpiredSessionException(String str) {
        super(str);
    }

    public ExpiredSessionException(Throwable th) {
        super(th);
    }

    public ExpiredSessionException(String str, Throwable th) {
        super(str, th);
    }
}
