package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class pw0 implements de2 {
    public final de2 c;

    public pw0(de2 de2Var) {
        v03.j(de2Var, "delegate");
        this.c = de2Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.c.close();
    }

    @Override // p.de2
    public final tl2 e() {
        return this.c.e();
    }

    @Override // p.de2
    public long t(fo foVar, long j) {
        v03.j(foVar, "sink");
        return this.c.t(foVar, j);
    }

    public final String toString() {
        return getClass().getSimpleName() + '(' + this.c + ')';
    }
}
