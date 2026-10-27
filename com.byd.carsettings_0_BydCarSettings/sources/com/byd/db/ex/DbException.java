package com.byd.db.ex;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class DbException extends IOException {
    private static final long serialVersionUID = 1;

    public DbException() {
    }

    public DbException(String str) {
        super(str);
    }

    public DbException(String str, Throwable th) {
        super(str, th);
    }

    public DbException(Throwable th) {
        super(th);
    }
}
