package p;

import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class u50 implements Closeable {
    public xy1 c;
    public c51 e;
    public xy1 k;
    public o40 l;
    public xy1 m;
    public xy1 n;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final void close() {
        ((j62) ((jk0) this.m.get())).close();
    }
}
