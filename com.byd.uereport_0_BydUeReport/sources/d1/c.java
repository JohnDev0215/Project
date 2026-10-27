package d1;

import androidx.room.RoomDatabase;

/* JADX INFO: compiled from: EntityInsertionAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> extends o {
    public c(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    public abstract void g(h1.f fVar, T t7);

    public final void h(T t7) {
        h1.f fVarA = a();
        try {
            g(fVarA, t7);
            fVarA.i0();
        } finally {
            f(fVarA);
        }
    }
}
