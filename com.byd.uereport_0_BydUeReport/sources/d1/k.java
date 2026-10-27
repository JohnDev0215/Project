package d1;

import android.database.Cursor;
import h1.c;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: RoomOpenHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends c.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d1.a f4398b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f4399c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f4400d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f4401e;

    /* JADX INFO: compiled from: RoomOpenHelper.java */
    public static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f4402a;

        public a(int i8) {
            this.f4402a = i8;
        }

        public abstract void a(h1.b bVar);

        public abstract void b(h1.b bVar);

        public abstract void c(h1.b bVar);

        public abstract void d(h1.b bVar);

        public abstract void e(h1.b bVar);

        public abstract void f(h1.b bVar);

        public abstract b g(h1.b bVar);
    }

    /* JADX INFO: compiled from: RoomOpenHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f4403a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f4404b;

        public b(boolean z7, String str) {
            this.f4403a = z7;
            this.f4404b = str;
        }
    }

    public k(d1.a aVar, a aVar2, String str, String str2) {
        super(aVar2.f4402a);
        this.f4398b = aVar;
        this.f4399c = aVar2;
        this.f4400d = str;
        this.f4401e = str2;
    }

    public static boolean j(h1.b bVar) {
        Cursor cursorJ0 = bVar.j0("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z7 = false;
            if (cursorJ0.moveToFirst()) {
                if (cursorJ0.getInt(0) == 0) {
                    z7 = true;
                }
            }
            return z7;
        } finally {
            cursorJ0.close();
        }
    }

    public static boolean k(h1.b bVar) {
        Cursor cursorJ0 = bVar.j0("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
        try {
            boolean z7 = false;
            if (cursorJ0.moveToFirst()) {
                if (cursorJ0.getInt(0) != 0) {
                    z7 = true;
                }
            }
            return z7;
        } finally {
            cursorJ0.close();
        }
    }

    @Override // h1.c.a
    public void b(h1.b bVar) {
        super.b(bVar);
    }

    @Override // h1.c.a
    public void d(h1.b bVar) {
        boolean zJ = j(bVar);
        this.f4399c.a(bVar);
        if (!zJ) {
            b bVarG = this.f4399c.g(bVar);
            if (!bVarG.f4403a) {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + bVarG.f4404b);
            }
        }
        l(bVar);
        this.f4399c.c(bVar);
    }

    @Override // h1.c.a
    public void e(h1.b bVar, int i8, int i9) {
        g(bVar, i8, i9);
    }

    @Override // h1.c.a
    public void f(h1.b bVar) {
        super.f(bVar);
        h(bVar);
        this.f4399c.d(bVar);
        this.f4398b = null;
    }

    @Override // h1.c.a
    public void g(h1.b bVar, int i8, int i9) {
        boolean z7;
        List<e1.a> listA;
        d1.a aVar = this.f4398b;
        if (aVar == null || (listA = aVar.f4337d.a(i8, i9)) == null) {
            z7 = false;
        } else {
            this.f4399c.f(bVar);
            Iterator<e1.a> it = listA.iterator();
            while (it.hasNext()) {
                it.next().a(bVar);
            }
            b bVarG = this.f4399c.g(bVar);
            if (!bVarG.f4403a) {
                throw new IllegalStateException("Migration didn't properly handle: " + bVarG.f4404b);
            }
            this.f4399c.e(bVar);
            l(bVar);
            z7 = true;
        }
        if (z7) {
            return;
        }
        d1.a aVar2 = this.f4398b;
        if (aVar2 != null && !aVar2.a(i8, i9)) {
            this.f4399c.b(bVar);
            this.f4399c.a(bVar);
            return;
        }
        throw new IllegalStateException("A migration from " + i8 + " to " + i9 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
    }

    public final void h(h1.b bVar) {
        if (!k(bVar)) {
            b bVarG = this.f4399c.g(bVar);
            if (bVarG.f4403a) {
                this.f4399c.e(bVar);
                l(bVar);
                return;
            } else {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + bVarG.f4404b);
            }
        }
        Cursor cursorY = bVar.y(new h1.a("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
        try {
            String string = cursorY.moveToFirst() ? cursorY.getString(0) : null;
            cursorY.close();
            if (!this.f4400d.equals(string) && !this.f4401e.equals(string)) {
                throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
            }
        } catch (Throwable th) {
            cursorY.close();
            throw th;
        }
    }

    public final void i(h1.b bVar) {
        bVar.k("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    public final void l(h1.b bVar) {
        i(bVar);
        bVar.k(j.a(this.f4400d));
    }
}
