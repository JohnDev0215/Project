package org.apache.shiro.util;

import org.apache.shiro.ShiroException;

/* JADX INFO: loaded from: classes.dex */
public class UnknownClassException extends ShiroException {
    public UnknownClassException() {
    }

    public UnknownClassException(String str) {
        super(str);
    }

    public UnknownClassException(Throwable th) {
        super(th);
    }

    public UnknownClassException(String str, Throwable th) {
        super(str, th);
    }
}
