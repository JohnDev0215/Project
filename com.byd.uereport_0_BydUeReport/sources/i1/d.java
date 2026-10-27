package i1;

import android.database.sqlite.SQLiteProgram;

/* JADX INFO: compiled from: FrameworkSQLiteProgram.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements h1.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SQLiteProgram f5473d;

    public d(SQLiteProgram sQLiteProgram) {
        this.f5473d = sQLiteProgram;
    }

    @Override // h1.d
    public void A(int i8, double d8) {
        this.f5473d.bindDouble(i8, d8);
    }

    @Override // h1.d
    public void U(int i8, long j8) {
        this.f5473d.bindLong(i8, j8);
    }

    @Override // h1.d
    public void b0(int i8, byte[] bArr) {
        this.f5473d.bindBlob(i8, bArr);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5473d.close();
    }

    @Override // h1.d
    public void l(int i8, String str) {
        this.f5473d.bindString(i8, str);
    }

    @Override // h1.d
    public void z(int i8) {
        this.f5473d.bindNull(i8);
    }
}
