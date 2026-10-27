package p;

/* JADX INFO: loaded from: classes.dex */
public final class wl1 implements de2 {
    public final /* synthetic */ xl1 c;

    public wl1(xl1 xl1Var) {
        this.c = xl1Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        xl1 xl1Var = this.c;
        if (v03.b(xl1Var.o, this)) {
            xl1Var.o = null;
        }
    }

    @Override // p.de2
    public final tl2 e() {
        return this.c.c.e();
    }

    @Override // p.de2
    public final long t(fo foVar, long j) {
        v03.j(foVar, "sink");
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
        }
        xl1 xl1Var = this.c;
        if (!v03.b(xl1Var.o, this)) {
            throw new IllegalStateException("closed".toString());
        }
        long jB = xl1Var.b(j);
        if (jB == 0) {
            return -1L;
        }
        return xl1Var.c.t(foVar, jB);
    }
}
