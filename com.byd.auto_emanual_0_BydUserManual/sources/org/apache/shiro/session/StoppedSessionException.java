package org.apache.shiro.session;

/* JADX INFO: loaded from: classes.dex */
public class StoppedSessionException extends InvalidSessionException {
    public StoppedSessionException() {
    }

    public StoppedSessionException(String str) {
        super(str);
    }

    public StoppedSessionException(Throwable th) {
        super(th);
    }

    public StoppedSessionException(String str, Throwable th) {
        super(str, th);
    }
}
