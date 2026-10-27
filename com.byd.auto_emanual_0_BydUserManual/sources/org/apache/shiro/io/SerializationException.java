package org.apache.shiro.io;

import org.apache.shiro.ShiroException;

/* JADX INFO: loaded from: classes.dex */
public class SerializationException extends ShiroException {
    public SerializationException() {
    }

    public SerializationException(String str) {
        super(str);
    }

    public SerializationException(Throwable th) {
        super(th);
    }

    public SerializationException(String str, Throwable th) {
        super(str, th);
    }
}
