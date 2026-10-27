package b.m.a.g;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import b.m.a.c;

/* JADX INFO: compiled from: FrameworkSQLiteOpenHelper.java */
/* JADX INFO: loaded from: classes.dex */
class b implements b.m.a.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f1917a;

    /* JADX INFO: compiled from: FrameworkSQLiteOpenHelper.java */
    static class a extends SQLiteOpenHelper {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final b.m.a.g.a[] f1918b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a f1919c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1920d;

        /* JADX INFO: renamed from: b.m.a.g.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: FrameworkSQLiteOpenHelper.java */
        class C0051a implements DatabaseErrorHandler {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.a f1921a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b.m.a.g.a[] f1922b;

            C0051a(c.a aVar, b.m.a.g.a[] aVarArr) {
                this.f1921a = aVar;
                this.f1922b = aVarArr;
            }

            @Override // android.database.DatabaseErrorHandler
            public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                this.f1921a.c(a.u(this.f1922b, sQLiteDatabase));
            }
        }

        a(Context context, String str, b.m.a.g.a[] aVarArr, c.a aVar) {
            super(context, str, null, aVar.f1903a, new C0051a(aVar, aVarArr));
            this.f1919c = aVar;
            this.f1918b = aVarArr;
        }

        static b.m.a.g.a u(b.m.a.g.a[] aVarArr, SQLiteDatabase sQLiteDatabase) {
            b.m.a.g.a aVar = aVarArr[0];
            if (aVar == null || !aVar.o(sQLiteDatabase)) {
                aVarArr[0] = new b.m.a.g.a(sQLiteDatabase);
            }
            return aVarArr[0];
        }

        @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
        public synchronized void close() {
            super.close();
            this.f1918b[0] = null;
        }

        b.m.a.g.a o(SQLiteDatabase sQLiteDatabase) {
            return u(this.f1918b, sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            this.f1919c.b(o(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.f1919c.d(o(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.f1920d = true;
            this.f1919c.e(o(sQLiteDatabase), i, i2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (this.f1920d) {
                return;
            }
            this.f1919c.f(o(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.f1920d = true;
            this.f1919c.g(o(sQLiteDatabase), i, i2);
        }

        synchronized b.m.a.b v() {
            this.f1920d = false;
            SQLiteDatabase writableDatabase = super.getWritableDatabase();
            if (!this.f1920d) {
                return o(writableDatabase);
            }
            close();
            return v();
        }
    }

    b(Context context, String str, c.a aVar) {
        this.f1917a = d(context, str, aVar);
    }

    private a d(Context context, String str, c.a aVar) {
        return new a(context, str, new b.m.a.g.a[1], aVar);
    }

    @Override // b.m.a.c
    public void a(boolean z) {
        this.f1917a.setWriteAheadLoggingEnabled(z);
    }

    @Override // b.m.a.c
    public b.m.a.b b() {
        return this.f1917a.v();
    }

    @Override // b.m.a.c
    public String c() {
        return this.f1917a.getDatabaseName();
    }
}
