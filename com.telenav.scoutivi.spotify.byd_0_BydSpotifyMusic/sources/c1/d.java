package c1;

import android.database.sqlite.SQLiteProgram;

/* JADX INFO: compiled from: FrameworkSQLiteProgram.java */
/* JADX INFO: loaded from: classes.dex */
class d implements b1.d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final SQLiteProgram f3573e;

    d(SQLiteProgram sQLiteProgram) {
        this.f3573e = sQLiteProgram;
    }

    @Override // b1.d
    public void B(int i7, byte[] bArr) {
        this.f3573e.bindBlob(i7, bArr);
    }

    @Override // b1.d
    public void C(int i7) {
        this.f3573e.bindNull(i7);
    }

    @Override // b1.d
    public void E(int i7, double d7) {
        this.f3573e.bindDouble(i7, d7);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f3573e.close();
    }

    @Override // b1.d
    public void q(int i7, String str) {
        this.f3573e.bindString(i7, str);
    }

    @Override // b1.d
    public void r(int i7, long j7) {
        this.f3573e.bindLong(i7, j7);
    }
}
