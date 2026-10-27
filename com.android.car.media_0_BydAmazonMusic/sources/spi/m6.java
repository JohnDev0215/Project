package spi;

import java.io.IOException;

/* JADX INFO: compiled from: MalformedJsonException.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m6 extends IOException {
    private static final long serialVersionUID = 1;

    public m6(String str) {
        super(str);
    }

    public m6(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public m6(Throwable th) {
        initCause(th);
    }
}
