package p;

/* JADX INFO: loaded from: classes.dex */
public final class x20 implements pd2 {
    public final pd2 c;
    public long e;

    public x20(cm cmVar) {
        this.c = cmVar;
    }

    @Override // p.pd2
    public final void Y(fo foVar, long j) {
        v03.j(foVar, "source");
        this.c.Y(foVar, j);
        this.e += j;
    }

    @Override // p.pd2, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.c.close();
    }

    @Override // p.pd2
    public final tl2 e() {
        return this.c.e();
    }

    @Override // p.pd2, java.io.Flushable
    public final void flush() {
        this.c.flush();
    }
}
