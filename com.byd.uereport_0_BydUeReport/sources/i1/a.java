package i1;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.CancellationSignal;
import android.util.Pair;
import h1.f;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: FrameworkSQLiteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements h1.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f5460d = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f5461e = new String[0];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final SQLiteDatabase f5462f;

    /* JADX INFO: renamed from: i1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FrameworkSQLiteDatabase.java */
    public class C0060a implements SQLiteDatabase.CursorFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h1.e f5463a;

        public C0060a(h1.e eVar) {
            this.f5463a = eVar;
        }

        @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
        public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
            this.f5463a.g(new d(sQLiteQuery));
            return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
        }
    }

    /* JADX INFO: compiled from: FrameworkSQLiteDatabase.java */
    public class b implements SQLiteDatabase.CursorFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h1.e f5465a;

        public b(h1.e eVar) {
            this.f5465a = eVar;
        }

        @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
        public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
            this.f5465a.g(new d(sQLiteQuery));
            return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
        }
    }

    public a(SQLiteDatabase sQLiteDatabase) {
        this.f5462f = sQLiteDatabase;
    }

    @Override // h1.b
    public Cursor J(h1.e eVar, CancellationSignal cancellationSignal) {
        return this.f5462f.rawQueryWithFactory(new b(eVar), eVar.e(), f5461e, null, cancellationSignal);
    }

    @Override // h1.b
    public boolean K() {
        return this.f5462f.inTransaction();
    }

    @Override // h1.b
    public void W() {
        this.f5462f.setTransactionSuccessful();
    }

    @Override // h1.b
    public String a() {
        return this.f5462f.getPath();
    }

    @Override // h1.b
    public void c() {
        this.f5462f.endTransaction();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5462f.close();
    }

    @Override // h1.b
    public void d() {
        this.f5462f.beginTransaction();
    }

    public boolean e(SQLiteDatabase sQLiteDatabase) {
        return this.f5462f == sQLiteDatabase;
    }

    @Override // h1.b
    public List<Pair<String, String>> h() {
        return this.f5462f.getAttachedDbs();
    }

    @Override // h1.b
    public boolean isOpen() {
        return this.f5462f.isOpen();
    }

    @Override // h1.b
    public Cursor j0(String str) {
        return y(new h1.a(str));
    }

    @Override // h1.b
    public void k(String str) throws SQLException {
        this.f5462f.execSQL(str);
    }

    @Override // h1.b
    public f u(String str) {
        return new e(this.f5462f.compileStatement(str));
    }

    @Override // h1.b
    public Cursor y(h1.e eVar) {
        return this.f5462f.rawQueryWithFactory(new C0060a(eVar), eVar.e(), f5461e, null);
    }
}
