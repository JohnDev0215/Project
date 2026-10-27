package p;

/* JADX INFO: loaded from: classes.dex */
public final class y02 implements x90 {
    public final ca0 c;

    public y02(ca0 ca0Var) {
        this.c = ca0Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.c.close();
    }
}
