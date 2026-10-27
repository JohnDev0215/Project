package cn.hutool.core.exceptions;

import c.a.a.d.b;

/* JADX INFO: compiled from: ExceptionUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(Throwable th) {
        return th == null ? "null" : b.g("{}: {}", th.getClass().getSimpleName(), th.getMessage());
    }
}
