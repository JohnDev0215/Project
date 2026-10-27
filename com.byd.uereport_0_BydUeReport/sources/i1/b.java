package i1;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import h1.c;

/* JADX INFO: compiled from: FrameworkSQLiteOpenHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements h1.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f5467a;

    /* JADX INFO: compiled from: FrameworkSQLiteOpenHelper.java */
    public static class a extends SQLiteOpenHelper {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final i1.a[] f5468d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final c.a f5469e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f5470f;

        /* JADX INFO: renamed from: i1.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: FrameworkSQLiteOpenHelper.java */
        public class C0061a implements DatabaseErrorHandler {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ c.a f5471a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ i1.a[] f5472b;

            public C0061a(c.a aVar, i1.a[] aVarArr) {
                this.f5471a = aVar;
                this.f5472b = aVarArr;
            }

            @Override // android.database.DatabaseErrorHandler
            public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                this.f5471a.c(a.g(this.f5472b, sQLiteDatabase));
            }
        }

        public a(Context context, String str, i1.a[] aVarArr, c.a aVar) {
            super(context, str, null, aVar.f5122a, new C0061a(aVar, aVarArr));
            this.f5469e = aVar;
            this.f5468d = aVarArr;
        }

        public static i1.a g(i1.a[] aVarArr, SQLiteDatabase sQLiteDatabase) {
            i1.a aVar = aVarArr[0];
            if (aVar == null || !aVar.e(sQLiteDatabase)) {
                aVarArr[0] = new i1.a(sQLiteDatabase);
            }
            return aVarArr[0];
        }

        @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
        public synchronized void close() {
            super.close();
            this.f5468d[0] = null;
        }

        public i1.a e(SQLiteDatabase sQLiteDatabase) {
            return g(this.f5468d, sQLiteDatabase);
        }

        public synchronized h1.b i() {
            this.f5470f = false;
            SQLiteDatabase writableDatabase = super.getWritableDatabase();
            if (!this.f5470f) {
                return e(writableDatabase);
            }
            close();
            return i();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            this.f5469e.b(e(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.f5469e.d(e(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i8, int i9) {
            this.f5470f = true;
            this.f5469e.e(e(sQLiteDatabase), i8, i9);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (this.f5470f) {
                return;
            }
            this.f5469e.f(e(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i8, int i9) {
            this.f5470f = true;
            this.f5469e.g(e(sQLiteDatabase), i8, i9);
        }
    }

    public b(Context context, String str, c.a aVar) {
        this.f5467a = d(context, str, aVar);
    }

    @Override // h1.c
    public void a(boolean z7) {
        this.f5467a.setWriteAheadLoggingEnabled(z7);
    }

    @Override // h1.c
    public h1.b b() {
        return this.f5467a.i();
    }

    @Override // h1.c
    public String c() {
        return this.f5467a.getDatabaseName();
    }

    public final a d(Context context, String str, c.a aVar) {
        return new a(context, str, new i1.a[1], aVar);
    }
}
