package androidx.room;

/* JADX INFO: compiled from: EntityInsertionAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> extends o {
    public c(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    protected abstract void g(b.m.a.f fVar, T t);

    public final long h(T t) {
        b.m.a.f fVarA = a();
        try {
            g(fVarA, t);
            return fVarA.p();
        } finally {
            f(fVarA);
        }
    }
}
