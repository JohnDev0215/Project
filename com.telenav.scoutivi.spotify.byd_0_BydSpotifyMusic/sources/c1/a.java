package c1;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.util.Pair;
import b1.f;
import java.util.List;

/* JADX INFO: compiled from: FrameworkSQLiteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
class a implements b1.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String[] f3562g = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String[] f3563h = new String[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final SQLiteDatabase f3564e;

    /* JADX INFO: renamed from: c1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FrameworkSQLiteDatabase.java */
    class C0034a implements SQLiteDatabase.CursorFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b1.e f3565a;

        C0034a(b1.e eVar) {
            this.f3565a = eVar;
        }

        @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
        public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
            this.f3565a.m(new d(sQLiteQuery));
            return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
        }
    }

    a(SQLiteDatabase sQLiteDatabase) {
        this.f3564e = sQLiteDatabase;
    }

    @Override // b1.b
    public Cursor O(String str) {
        return h(new b1.a(str));
    }

    @Override // b1.b
    public String R() {
        return this.f3564e.getPath();
    }

    @Override // b1.b
    public boolean W() {
        return this.f3564e.inTransaction();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f3564e.close();
    }

    @Override // b1.b
    public void f() {
        this.f3564e.endTransaction();
    }

    @Override // b1.b
    public void g() {
        this.f3564e.beginTransaction();
    }

    @Override // b1.b
    public Cursor h(b1.e eVar) {
        return this.f3564e.rawQueryWithFactory(new C0034a(eVar), eVar.z(), f3563h, null);
    }

    @Override // b1.b
    public boolean isOpen() {
        return this.f3564e.isOpen();
    }

    @Override // b1.b
    public List<Pair<String, String>> k() {
        return this.f3564e.getAttachedDbs();
    }

    @Override // b1.b
    public void o(String str) {
        this.f3564e.execSQL(str);
    }

    @Override // b1.b
    public void u() {
        this.f3564e.setTransactionSuccessful();
    }

    @Override // b1.b
    public f w(String str) {
        return new e(this.f3564e.compileStatement(str));
    }
}
