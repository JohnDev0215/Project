package b.m.a.g;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.CancellationSignal;
import android.util.Pair;
import b.m.a.f;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: FrameworkSQLiteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
class a implements b.m.a.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f1910b = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String[] f1911c = new String[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final SQLiteDatabase f1912d;

    /* JADX INFO: renamed from: b.m.a.g.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FrameworkSQLiteDatabase.java */
    class C0050a implements SQLiteDatabase.CursorFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.m.a.e f1913a;

        C0050a(b.m.a.e eVar) {
            this.f1913a = eVar;
        }

        @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
        public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
            this.f1913a.u(new d(sQLiteQuery));
            return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
        }
    }

    /* JADX INFO: compiled from: FrameworkSQLiteDatabase.java */
    class b implements SQLiteDatabase.CursorFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.m.a.e f1915a;

        b(b.m.a.e eVar) {
            this.f1915a = eVar;
        }

        @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
        public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
            this.f1915a.u(new d(sQLiteQuery));
            return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
        }
    }

    a(SQLiteDatabase sQLiteDatabase) {
        this.f1912d = sQLiteDatabase;
    }

    @Override // b.m.a.b
    public void a() {
        this.f1912d.endTransaction();
    }

    @Override // b.m.a.b
    public void b() {
        this.f1912d.beginTransaction();
    }

    @Override // b.m.a.b
    public boolean c() {
        return this.f1912d.isOpen();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f1912d.close();
    }

    @Override // b.m.a.b
    public List<Pair<String, String>> d() {
        return this.f1912d.getAttachedDbs();
    }

    @Override // b.m.a.b
    public void e(String str) throws SQLException {
        this.f1912d.execSQL(str);
    }

    @Override // b.m.a.b
    public void h() {
        this.f1912d.setTransactionSuccessful();
    }

    @Override // b.m.a.b
    public f j(String str) {
        return new e(this.f1912d.compileStatement(str));
    }

    @Override // b.m.a.b
    public Cursor k(b.m.a.e eVar) {
        return this.f1912d.rawQueryWithFactory(new C0050a(eVar), eVar.o(), f1911c, null);
    }

    boolean o(SQLiteDatabase sQLiteDatabase) {
        return this.f1912d == sQLiteDatabase;
    }

    @Override // b.m.a.b
    public Cursor q(String str) {
        return k(new b.m.a.a(str));
    }

    @Override // b.m.a.b
    public String r() {
        return this.f1912d.getPath();
    }

    @Override // b.m.a.b
    public Cursor s(b.m.a.e eVar, CancellationSignal cancellationSignal) {
        return this.f1912d.rawQueryWithFactory(new b(eVar), eVar.o(), f1911c, null, cancellationSignal);
    }

    @Override // b.m.a.b
    public boolean t() {
        return this.f1912d.inTransaction();
    }
}
