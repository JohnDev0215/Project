package org.apache.shiro.session;

/* JADX INFO: loaded from: classes.dex */
public class UnknownSessionException extends InvalidSessionException {
    public UnknownSessionException() {
    }

    public UnknownSessionException(String str) {
        super(str);
    }

    public UnknownSessionException(Throwable th) {
        super(th);
    }

    public UnknownSessionException(String str, Throwable th) {
        super(str, th);
    }
}
