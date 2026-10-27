package b.m.a.g;

import android.database.sqlite.SQLiteStatement;
import b.m.a.f;

/* JADX INFO: compiled from: FrameworkSQLiteStatement.java */
/* JADX INFO: loaded from: classes.dex */
class e extends d implements f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final SQLiteStatement f1924c;

    e(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.f1924c = sQLiteStatement;
    }

    @Override // b.m.a.f
    public int i() {
        return this.f1924c.executeUpdateDelete();
    }

    @Override // b.m.a.f
    public long p() {
        return this.f1924c.executeInsert();
    }
}
