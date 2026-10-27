package org.apache.shiro.subject;

import org.apache.shiro.ShiroException;

/* JADX INFO: loaded from: classes.dex */
public class ExecutionException extends ShiroException {
    public ExecutionException(Throwable th) {
        super(th);
    }

    public ExecutionException(String str, Throwable th) {
        super(str, th);
    }
}
