package androidx.room;

import android.database.Cursor;
import b.m.a.c;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: RoomOpenHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends c.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private androidx.room.a f1351b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f1352c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f1353d;
    private final String e;

    /* JADX INFO: compiled from: RoomOpenHelper.java */
    public static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1354a;

        public a(int i) {
            this.f1354a = i;
        }

        protected abstract void a(b.m.a.b bVar);

        protected abstract void b(b.m.a.b bVar);

        protected abstract void c(b.m.a.b bVar);

        protected abstract void d(b.m.a.b bVar);

        protected abstract void e(b.m.a.b bVar);

        protected abstract void f(b.m.a.b bVar);

        protected abstract b g(b.m.a.b bVar);
    }

    /* JADX INFO: compiled from: RoomOpenHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f1355a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f1356b;

        public b(boolean z, String str) {
            this.f1355a = z;
            this.f1356b = str;
        }
    }

    public k(androidx.room.a aVar, a aVar2, String str, String str2) {
        super(aVar2.f1354a);
        this.f1351b = aVar;
        this.f1352c = aVar2;
        this.f1353d = str;
        this.e = str2;
    }

    private void h(b.m.a.b bVar) {
        if (!k(bVar)) {
            b bVarG = this.f1352c.g(bVar);
            if (bVarG.f1355a) {
                this.f1352c.e(bVar);
                l(bVar);
                return;
            } else {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + bVarG.f1356b);
            }
        }
        Cursor cursorK = bVar.k(new b.m.a.a("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
        try {
            String string = cursorK.moveToFirst() ? cursorK.getString(0) : null;
            cursorK.close();
            if (!this.f1353d.equals(string) && !this.e.equals(string)) {
                throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
            }
        } catch (Throwable th) {
            cursorK.close();
            throw th;
        }
    }

    private void i(b.m.a.b bVar) {
        bVar.e("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    private static boolean j(b.m.a.b bVar) {
        Cursor cursorQ = bVar.q("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z = false;
            if (cursorQ.moveToFirst()) {
                if (cursorQ.getInt(0) == 0) {
                    z = true;
                }
            }
            return z;
        } finally {
            cursorQ.close();
        }
    }

    private static boolean k(b.m.a.b bVar) {
        Cursor cursorQ = bVar.q("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
        try {
            boolean z = false;
            if (cursorQ.moveToFirst()) {
                if (cursorQ.getInt(0) != 0) {
                    z = true;
                }
            }
            return z;
        } finally {
            cursorQ.close();
        }
    }

    private void l(b.m.a.b bVar) {
        i(bVar);
        bVar.e(j.a(this.f1353d));
    }

    @Override // b.m.a.c.a
    public void b(b.m.a.b bVar) {
        super.b(bVar);
    }

    @Override // b.m.a.c.a
    public void d(b.m.a.b bVar) {
        boolean zJ = j(bVar);
        this.f1352c.a(bVar);
        if (!zJ) {
            b bVarG = this.f1352c.g(bVar);
            if (!bVarG.f1355a) {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + bVarG.f1356b);
            }
        }
        l(bVar);
        this.f1352c.c(bVar);
    }

    @Override // b.m.a.c.a
    public void e(b.m.a.b bVar, int i, int i2) {
        g(bVar, i, i2);
    }

    @Override // b.m.a.c.a
    public void f(b.m.a.b bVar) {
        super.f(bVar);
        h(bVar);
        this.f1352c.d(bVar);
        this.f1351b = null;
    }

    @Override // b.m.a.c.a
    public void g(b.m.a.b bVar, int i, int i2) {
        boolean z;
        List<androidx.room.q.a> listC;
        androidx.room.a aVar = this.f1351b;
        if (aVar == null || (listC = aVar.f1320d.c(i, i2)) == null) {
            z = false;
        } else {
            this.f1352c.f(bVar);
            Iterator<androidx.room.q.a> it = listC.iterator();
            while (it.hasNext()) {
                it.next().a(bVar);
            }
            b bVarG = this.f1352c.g(bVar);
            if (!bVarG.f1355a) {
                throw new IllegalStateException("Migration didn't properly handle: " + bVarG.f1356b);
            }
            this.f1352c.e(bVar);
            l(bVar);
            z = true;
        }
        if (z) {
            return;
        }
        androidx.room.a aVar2 = this.f1351b;
        if (aVar2 != null && !aVar2.a(i, i2)) {
            this.f1352c.b(bVar);
            this.f1352c.a(bVar);
            return;
        }
        throw new IllegalStateException("A migration from " + i + " to " + i2 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
    }
}
