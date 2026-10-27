package d1;

import androidx.room.RoomDatabase;

/* JADX INFO: compiled from: EntityDeletionOrUpdateAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T> extends o {
    public b(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    public abstract void g(h1.f fVar, T t7);

    public final int h(T t7) {
        h1.f fVarA = a();
        try {
            g(fVarA, t7);
            return fVarA.s();
        } finally {
            f(fVarA);
        }
    }
}
