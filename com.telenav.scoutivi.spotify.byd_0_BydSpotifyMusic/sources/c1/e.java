package c1;

import android.database.sqlite.SQLiteStatement;
import b1.f;

/* JADX INFO: compiled from: FrameworkSQLiteStatement.java */
/* JADX INFO: loaded from: classes.dex */
class e extends d implements f {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final SQLiteStatement f3574g;

    e(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.f3574g = sQLiteStatement;
    }

    @Override // b1.f
    public long L() {
        return this.f3574g.executeInsert();
    }

    @Override // b1.f
    public int v() {
        return this.f3574g.executeUpdateDelete();
    }
}
