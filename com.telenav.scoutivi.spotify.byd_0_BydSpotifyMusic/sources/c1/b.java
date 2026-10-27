package c1;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import b1.c;

/* JADX INFO: compiled from: FrameworkSQLiteOpenHelper.java */
/* JADX INFO: loaded from: classes.dex */
class b implements b1.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f3567a;

    /* JADX INFO: compiled from: FrameworkSQLiteOpenHelper.java */
    static class a extends SQLiteOpenHelper {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c1.a[] f3568e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final c.a f3569g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f3570h;

        /* JADX INFO: renamed from: c1.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: FrameworkSQLiteOpenHelper.java */
        class C0035a implements DatabaseErrorHandler {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c1.a[] f3571a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ c.a f3572b;

            C0035a(c1.a[] aVarArr, c.a aVar) {
                this.f3571a = aVarArr;
                this.f3572b = aVar;
            }

            @Override // android.database.DatabaseErrorHandler
            public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                c1.a aVar = this.f3571a[0];
                if (aVar != null) {
                    this.f3572b.c(aVar);
                }
            }
        }

        a(Context context, String str, c1.a[] aVarArr, c.a aVar) {
            super(context, str, null, aVar.f3467a, new C0035a(aVarArr, aVar));
            this.f3569g = aVar;
            this.f3568e = aVarArr;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
        public synchronized void close() {
            super.close();
            this.f3568e[0] = null;
        }

        c1.a m(SQLiteDatabase sQLiteDatabase) {
            if (this.f3568e[0] == null) {
                this.f3568e[0] = new c1.a(sQLiteDatabase);
            }
            return this.f3568e[0];
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            this.f3569g.b(m(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.f3569g.d(m(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i7, int i8) {
            this.f3570h = true;
            this.f3569g.e(m(sQLiteDatabase), i7, i8);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (this.f3570h) {
                return;
            }
            this.f3569g.f(m(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i7, int i8) {
            this.f3570h = true;
            this.f3569g.g(m(sQLiteDatabase), i7, i8);
        }

        synchronized b1.b z() {
            this.f3570h = false;
            SQLiteDatabase writableDatabase = super.getWritableDatabase();
            if (!this.f3570h) {
                return m(writableDatabase);
            }
            close();
            return z();
        }
    }

    b(Context context, String str, c.a aVar) {
        this.f3567a = c(context, str, aVar);
    }

    private a c(Context context, String str, c.a aVar) {
        return new a(context, str, new c1.a[1], aVar);
    }

    @Override // b1.c
    public void a(boolean z6) {
        this.f3567a.setWriteAheadLoggingEnabled(z6);
    }

    @Override // b1.c
    public b1.b b() {
        return this.f3567a.z();
    }
}
