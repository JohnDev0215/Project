package p;

import java.io.EOFException;

/* JADX INFO: loaded from: classes.dex */
public final class cm implements pd2 {
    @Override // p.pd2
    public final void Y(fo foVar, long j) throws EOFException {
        v03.j(foVar, "source");
        foVar.y(j);
    }

    @Override // p.pd2, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // p.pd2
    public final tl2 e() {
        return tl2.d;
    }

    @Override // p.pd2, java.io.Flushable
    public final void flush() {
    }
}
