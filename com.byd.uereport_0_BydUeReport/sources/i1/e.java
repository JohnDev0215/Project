package i1;

import android.database.sqlite.SQLiteStatement;
import h1.f;

/* JADX INFO: compiled from: FrameworkSQLiteStatement.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends d implements f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SQLiteStatement f5474e;

    public e(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.f5474e = sQLiteStatement;
    }

    @Override // h1.f
    public long i0() {
        return this.f5474e.executeInsert();
    }

    @Override // h1.f
    public int s() {
        return this.f5474e.executeUpdateDelete();
    }
}
