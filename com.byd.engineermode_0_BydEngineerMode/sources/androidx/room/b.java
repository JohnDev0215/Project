package androidx.room;

/* JADX INFO: compiled from: EntityDeletionOrUpdateAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T> extends o {
    public b(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    protected abstract void g(b.m.a.f fVar, T t);

    public final int h(T t) {
        b.m.a.f fVarA = a();
        try {
            g(fVarA, t);
            return fVarA.i();
        } finally {
            f(fVarA);
        }
    }
}
