package y0;

/* JADX INFO: compiled from: EntityInsertionAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> extends j {
    public c(f fVar) {
        super(fVar);
    }

    protected abstract void g(b1.f fVar, T t6);

    public final long h(T t6) {
        b1.f fVarA = a();
        try {
            g(fVarA, t6);
            return fVarA.L();
        } finally {
            f(fVarA);
        }
    }
}
