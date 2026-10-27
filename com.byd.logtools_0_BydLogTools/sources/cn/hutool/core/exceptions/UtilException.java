package cn.hutool.core.exceptions;

import c.a.a.d.b;

/* JADX INFO: loaded from: classes.dex */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable th) {
        super(a.a(th), th);
    }

    public UtilException(String str) {
        super(str);
    }

    public UtilException(String str, Object... objArr) {
        super(b.g(str, objArr));
    }

    public UtilException(String str, Throwable th) {
        super(str, th);
    }

    public UtilException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }

    public UtilException(Throwable th, String str, Object... objArr) {
        super(b.g(str, objArr), th);
    }
}
