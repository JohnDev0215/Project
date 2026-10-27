package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class ow0 implements pd2 {
    private final pd2 c;

    public ow0(pd2 pd2Var) {
        v03.j(pd2Var, "delegate");
        this.c = pd2Var;
    }

    @Override // p.pd2
    public void Y(fo foVar, long j) {
        v03.j(foVar, "source");
        this.c.Y(foVar, j);
    }

    @Override // p.pd2, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.c.close();
    }

    @Override // p.pd2
    public tl2 e() {
        return this.c.e();
    }

    @Override // p.pd2, java.io.Flushable
    public void flush() {
        this.c.flush();
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.c + ')';
    }
}
