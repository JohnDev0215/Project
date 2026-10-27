package org.apache.shiro.codec;

import org.apache.shiro.ShiroException;

/* JADX INFO: loaded from: classes.dex */
public class CodecException extends ShiroException {
    public CodecException() {
    }

    public CodecException(String str) {
        super(str);
    }

    public CodecException(Throwable th) {
        super(th);
    }

    public CodecException(String str, Throwable th) {
        super(str, th);
    }
}
