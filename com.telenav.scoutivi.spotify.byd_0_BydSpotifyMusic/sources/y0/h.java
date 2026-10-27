package y0;

import android.database.Cursor;
import b1.c;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: RoomOpenHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends c.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private y0.a f9942b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f9943c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f9944d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f9945e;

    /* JADX INFO: compiled from: RoomOpenHelper.java */
    public static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9946a;

        public a(int i7) {
            this.f9946a = i7;
        }

        protected abstract void a(b1.b bVar);

        protected abstract void b(b1.b bVar);

        protected abstract void c(b1.b bVar);

        protected abstract void d(b1.b bVar);

        protected abstract void e(b1.b bVar);
    }

    public h(y0.a aVar, a aVar2, String str, String str2) {
        super(aVar2.f9946a);
        this.f9942b = aVar;
        this.f9943c = aVar2;
        this.f9944d = str;
        this.f9945e = str2;
    }

    private void h(b1.b bVar) {
        if (j(bVar)) {
            Cursor cursorH = bVar.h(new b1.a("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
            try {
                string = cursorH.moveToFirst() ? cursorH.getString(0) : null;
            } finally {
                cursorH.close();
            }
        }
        if (!this.f9944d.equals(string) && !this.f9945e.equals(string)) {
            throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
        }
    }

    private void i(b1.b bVar) {
        bVar.o("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    private static boolean j(b1.b bVar) {
        Cursor cursorO = bVar.O("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
        try {
            boolean z6 = false;
            if (cursorO.moveToFirst()) {
                if (cursorO.getInt(0) != 0) {
                    z6 = true;
                }
            }
            return z6;
        } finally {
            cursorO.close();
        }
    }

    private void k(b1.b bVar) {
        i(bVar);
        bVar.o(g.a(this.f9944d));
    }

    @Override // b1.c.a
    public void b(b1.b bVar) {
        super.b(bVar);
    }

    @Override // b1.c.a
    public void d(b1.b bVar) {
        k(bVar);
        this.f9943c.a(bVar);
        this.f9943c.c(bVar);
    }

    @Override // b1.c.a
    public void e(b1.b bVar, int i7, int i8) {
        g(bVar, i7, i8);
    }

    @Override // b1.c.a
    public void f(b1.b bVar) {
        super.f(bVar);
        h(bVar);
        this.f9943c.d(bVar);
        this.f9942b = null;
    }

    @Override // b1.c.a
    public void g(b1.b bVar, int i7, int i8) {
        boolean z6;
        List<z0.a> listA;
        y0.a aVar = this.f9942b;
        if (aVar == null || (listA = aVar.f9890d.a(i7, i8)) == null) {
            z6 = false;
        } else {
            Iterator<z0.a> it = listA.iterator();
            while (it.hasNext()) {
                it.next().a(bVar);
            }
            this.f9943c.e(bVar);
            k(bVar);
            z6 = true;
        }
        if (z6) {
            return;
        }
        y0.a aVar2 = this.f9942b;
        if (aVar2 != null && !aVar2.a(i7)) {
            this.f9943c.b(bVar);
            this.f9943c.a(bVar);
            return;
        }
        throw new IllegalStateException("A migration from " + i7 + " to " + i8 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
    }
}
