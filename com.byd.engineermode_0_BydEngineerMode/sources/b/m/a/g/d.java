package b.m.a.g;

import android.database.sqlite.SQLiteProgram;

/* JADX INFO: compiled from: FrameworkSQLiteProgram.java */
/* JADX INFO: loaded from: classes.dex */
class d implements b.m.a.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SQLiteProgram f1923b;

    d(SQLiteProgram sQLiteProgram) {
        this.f1923b = sQLiteProgram;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1923b.close();
    }

    @Override // b.m.a.d
    public void f(int i, String str) {
        this.f1923b.bindString(i, str);
    }

    @Override // b.m.a.d
    public void g(int i, long j) {
        this.f1923b.bindLong(i, j);
    }

    @Override // b.m.a.d
    public void l(int i, byte[] bArr) {
        this.f1923b.bindBlob(i, bArr);
    }

    @Override // b.m.a.d
    public void m(int i) {
        this.f1923b.bindNull(i);
    }

    @Override // b.m.a.d
    public void n(int i, double d2) {
        this.f1923b.bindDouble(i, d2);
    }
}
