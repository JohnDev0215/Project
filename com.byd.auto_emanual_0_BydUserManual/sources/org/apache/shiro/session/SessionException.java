package org.apache.shiro.session;

import org.apache.shiro.ShiroException;

/* JADX INFO: loaded from: classes.dex */
public class SessionException extends ShiroException {
    public SessionException() {
    }

    public SessionException(String str) {
        super(str);
    }

    public SessionException(Throwable th) {
        super(th);
    }

    public SessionException(String str, Throwable th) {
        super(str, th);
    }
}
